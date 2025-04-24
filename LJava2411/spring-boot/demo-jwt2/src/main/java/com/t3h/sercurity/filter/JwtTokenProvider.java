package com.t3h.sercurity.filter;

import com.t3h.entity.UserEntity;
import com.t3h.sercurity.CustomUserDetail;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenProvider {

    // Đoạn JWT_SECRET này là bí mật, chỉ có phía server biết
    private final String JWT_SECRET = "zegatea";

    private final String JWT_REFRESH_SECRET = "zegatea0001";

    //Thời gian có hiệu lực của chuỗi jwt
//    private final long JWT_EXPIRATION = 15 * 24 * 60 * 60 * 1000L;
//
//    private final long JWT_REFRESH_TOKEN_EXPIRATION = (15 * 24 * 60 * 60 * 1000L) * 3;
    private final long JWT_EXPIRATION = 2 * 60 * 1000L; // 2 phút

    private final long JWT_REFRESH_TOKEN_EXPIRATION = 20 * 60 * 1000L; // 5 phút
    // Tạo ra jwt từ thông tin user
    public String generateToken(UserEntity userEntity,Date currentDate) {
        Date expireDate = new Date(currentDate.getTime() + JWT_EXPIRATION);
        Map<String,Object> claim = new HashMap<>();
        claim.put("role",userEntity.getRoleEntities());
        claim.put("username",userEntity.getUsername());
        // Tạo chuỗi json web token từ id của user.
        return Jwts.builder()
                .setSubject(Long.toString(userEntity.getId()))
                .setAudience(userEntity.getUsername())
                .setIssuedAt(currentDate)
                .setClaims(claim)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    public String generateRefreshToken(UserEntity userEntity,Date currentDate) {
        Date expireDate = new Date(currentDate.getTime() + JWT_REFRESH_TOKEN_EXPIRATION);
        Map<String,Object> claim = new HashMap<>();
        claim.put("role",userEntity.getRoleEntities());
        claim.put("username",userEntity.getUsername());
        // Tạo chuỗi json web token từ id của user.
        return Jwts.builder()
                .setSubject(Long.toString(userEntity.getId()))
                .setAudience(userEntity.getUsername())
                .setIssuedAt(currentDate)
                .setClaims(claim)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, JWT_REFRESH_SECRET)
                .compact();
    }

    // Lấy thông tin user từ jwt
    public Long getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
        Long subject = Long.parseLong(claims.getSubject());

        return subject;
    }

    public String  getJwtFormRequest(HttpServletRequest request) {
        String bearerString = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerString) && bearerString.startsWith("Bearer ")) {
            return bearerString.substring(7);
        }
        return null;
    }

    public String getUserName(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
        return (String) claims.get("username");
    }

    public String getUserNameRefreshToken(String refreshToken) {
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_REFRESH_SECRET)
                .parseClaimsJws(refreshToken)
                .getBody();
        return (String) claims.get("username");
    }

    public Claims getTokenRaw(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException ex) {
            System.out.println("Invalid JWT token: {}" +  ex.getMessage());
        } catch (ExpiredJwtException ex) {
            System.out.println("Expired JWT token: {}" + ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            System.out.println("Unsupported JWT token: {}" + ex.getMessage());
        } catch (IllegalArgumentException ex) {
            System.out.println("JWT claims string is empty: {}" + ex.getMessage());
        }
        return false;
    }

    public boolean validateRefreshToken(String refreshToken) {
        try {
            Jwts.parser().setSigningKey(JWT_REFRESH_SECRET).parseClaimsJws(refreshToken);
            return true;
        } catch (MalformedJwtException ex) {
            System.out.println("Invalid JWT token: {}" +  ex.getMessage());
        } catch (ExpiredJwtException ex) {
            System.out.println("Expired JWT token: {}" + ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            System.out.println("Unsupported JWT token: {}" + ex.getMessage());
        } catch (IllegalArgumentException ex) {
            System.out.println("JWT claims string is empty: {}" + ex.getMessage());
        }
        return false;
    }

}

