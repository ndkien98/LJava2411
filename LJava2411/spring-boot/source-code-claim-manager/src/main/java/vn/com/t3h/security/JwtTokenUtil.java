package vn.com.t3h.security;

import io.jsonwebtoken.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import vn.com.t3h.entity.UserEntity;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class JwtTokenUtil {

    private final String jwtSecret = "Tifj1AUa+LFpiOhR0vCqvmbpB4O0g1H8ZbQbnQPx4oc=";
    private final long jwtExpirationInMs = 604800000L; // 7 days

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();

        // Lấy danh sách role (authorities) dưới dạng List<String>
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        claims.put("roles", roles);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }


    public String generateRefreshToken(UserEntity userEntity, Date currentDate) {
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationInMs);
        Map<String,Object> claim = new HashMap<>();
//        claim.put("role",userEntity.getRoles());
        claim.put("username",userEntity.getUsername());
        // Tạo chuỗi json web token từ id của user.
        return Jwts.builder()
                .setSubject(Long.toString(userEntity.getId()))
                .setAudience(userEntity.getUsername())
                .setIssuedAt(currentDate)
                .setClaims(claim)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    public List<String> extractRoles(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return (List<String>) claims.get("roles");
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}

