package vn.com.t3h.config;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import vn.com.t3h.security.AuthSuccessHandler;
import vn.com.t3h.security.JwtTokenUtil;
import vn.com.t3h.security.interceptor.JwtAuthenticationFilter;
import vn.com.t3h.utils.Constant;

import javax.crypto.SecretKey;
import java.security.SecureRandom;
import java.util.Base64;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtFilter;
    private final AuthSuccessHandler authSuccessHandler;

    public SecurityConfig(AuthSuccessHandler authSuccessHandler) {
        this.authSuccessHandler = authSuccessHandler;
    }

    /*
    PasswordEncoder:
        - tạo bean để spring security biết lên sử dụng giải thuật nào để mã hóa password, khi đó spring sẽ mã hóa password ở
        font-end với giải thuật này và so sánh giá trị đã mã hóa ra xem có giống với password của user entity trong database không
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /*
    SecurityFilterChain:
        class config chính đảm nhận tất cả các config của spring security
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> { // config các request
                    request.requestMatchers("/cms/**").hasAnyRole("ADMIN") // tất cả các đường đãn bắt từ từ /cms/ đều phải có role ADMIN mới được truy cập vào
                            .requestMatchers("/","/home","/login","/logout","/process_login").permitAll()// tất cả các đường dẫn này có thể truy cạp mà không càn login
                            .requestMatchers(
                                    "/assets/**", "/fonts/**", "/homeguest_files/**",
                                    "/js/**", "/libs/**", "/loginmetlife/**",
                                    "/page404/**", "/scss/**", "/tasks/**", "/css/**", "/images/**","/cms-rs/**","/file/**").permitAll()
                            .requestMatchers("/resource/**").permitAll()
                            .requestMatchers("/auth/**").permitAll()
                            .requestMatchers("/kaira/**").permitAll()
                            .anyRequest().authenticated();
                })
                .formLogin( // custom form login, không sử dụng form login mặc định của spring security
                        form ->
                                form.loginPage("/login")// page login được custom
                                .loginProcessingUrl("/process_login") // url để view gửi username, password lên cho server, config tại form login ở page login
//                                .defaultSuccessUrl("/process-after-login-success",true) // /process-after-login-success url được điều hướng đến khi login thành công, sử dụng để xử lý tự động điều hướng page theo role, ví dụ role Admin -> /cms/home, role user -> /home
                                        .successHandler(authSuccessHandler) // Use custom success handler
                                        .failureUrl("/login?error=true")
                )
                .logout(logout ->
                        logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .deleteCookies("JWT")
                                .permitAll());
        // Thêm filter kiểm tra JWT trước khi vào controller
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println("password admin = " + passwordEncoder.encode("admin"));
        System.out.println("password user = " + passwordEncoder.encode("user"));
        System.out.println("key: " + generateSafeToken());

    }

    private static String generateSafeToken() {
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256); // Secure 256-bit key
        return java.util.Base64.getEncoder().encodeToString(key.getEncoded());
    }

}
