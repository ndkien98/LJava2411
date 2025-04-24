package com.t3h.config;


import com.t3h.sercurity.CustomPasswordEncoder;
import com.t3h.sercurity.CustomUserDetail;
import com.t3h.sercurity.CustomUserDetailService;
import com.t3h.sercurity.filter.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true, // (1)
        securedEnabled = true, // (2)
        jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService(){
        return new CustomUserDetailService();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new CustomPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(){
        return new JwtAuthenticationFilter();
    }


    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**").antMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**");
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();

        // chi dinh su dung class BCryptPasswordEncoder de ma hoa password tu fromlogin gui len va so sanh voi password da duoc ma hoa trong dataabase
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        authenticationProvider.setUserDetailsService(userDetailsService());
        return authenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

//    // builder
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/admin/**").authenticated() // tat cac page bat dau bang /admin/ deu phai login moi duoc vao
////                .antMatchers("/admin/**").hasAuthority("Admin")
////                .antMatchers("/user/**").hasAnyAuthority("User")
//                .antMatchers("/user/**").hasAnyRole("ROLE_User")
//                .antMatchers("/","/login","/logout","/home","/swagger-ui.html","/process_register","/register").permitAll() // config cac page khong can phai login cung co the truy cap
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .loginProcessingUrl("/login_security") // khi click dang nhap se gui username,password len url nay
//                .usernameParameter("username")
//                .passwordParameter("password")
//                .defaultSuccessUrl("/admin/users") // config page mac dinh se dieu huong toi sau khi login thanh cong
//                .permitAll()
////                .and()
////                .logout() // config su dung chuc nang logout cua spring security da cung cap
////                .logoutSuccessUrl("/")// config sau khi logout thanh cong se dieu huong ra pgae co url ;la : "/"
//                .permitAll()
//                .and().exceptionHandling().accessDeniedPage("/403");
//
//    }

    /**
        I) tại from login user nhập username,pass
            khi click login gửi request lên server
            -> request sẽ đia qua bước số 1
            -> tại tầng filter -> lấy ra username, password
            -> tạo ra đối tượng Authention chứa username,pass ng dùng gửi lên

            -> gọi tới AuthentionManager ( đã được config tại dòng 44 để chỉ định thuật toán nào sẽ mã hóa pass và class service nào lấy user từ database
            -> 5) gửi username sang Authention Provider
            -> Authention Provider goi toi CustomUserDetailService tai dòng 38
            -> CustomUserDetailService query user từ trong database bằng method loadUserByUsername
            -> sau khi lấy được user từ database và tạo ra UserDetail (Class được tạo sẵn từ spring security)
            -> truyền userDetail lên Authention Provider tại (bước 7 trong hình)
            -> so sánh lấy username, pass từ userDetail trong database để so sánh với username,pass được gửi lên từ page login
            -> nếu thành công spring sẽ lưu lái session và tạo ra context security với userDetail được lấy trong db
            ->
     */

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().authenticationEntryPoint(((httpServletRequest, httpServletResponse, e) -> httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED)))
                .and()
                .authorizeRequests()
                .antMatchers("/auth","/logout","/auth/refresh-token").permitAll()
//                .antMatchers("/v2/api-docs",
//                        "/configuration/ui",
//                        "/swagger-resources/**",
//                        "/configuration/security",
//                        "/swagger-ui.html",
//                        "/webjars/**").permitAll()
                .anyRequest()
                .authenticated();
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }



    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new CustomPasswordEncoder();

        System.out.println(passwordEncoder.encode("admin"));
    }
}
