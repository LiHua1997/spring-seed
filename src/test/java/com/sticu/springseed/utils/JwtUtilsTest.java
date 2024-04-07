package com.sticu.springseed.utils;

import com.sticu.springseed.SpringseedTestMockito;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("JwtUtils 单元测试")
class JwtUtilsTest extends SpringseedTestMockito {

    @ParameterizedTest(name = "测试创建 JWT - 基本功能 - subject: {0}")
    @CsvSource({"testSubject1", "testSubject2"})
    void testCreateJWT(String subject) {
        String jwt = JwtUtils.createJWT(subject);

        assertNotNull(jwt);
        assertFalse(jwt.isEmpty());
    }

    @ParameterizedTest(name = "测试创建 JWT - 设置过期时间 - subject: {0}, expirationTime: {1}")
    @CsvSource({"testSubject1,3600000", "testSubject2,7200000"})
    void testCreateJWTWithExpiration(String subject, Long expirationTime) {
        String jwt = JwtUtils.createJWT(subject, expirationTime);

        assertNotNull(jwt);
        assertFalse(jwt.isEmpty());

        Claims claims = JwtUtils.parseJWT(jwt);
        assertNotNull(claims);

        // 检查过期时间是否在容许误差范围内
        long expirationMillis = claims.getExpiration().getTime();
        long currentMillis = System.currentTimeMillis();
        long allowableDeviation = 1000L; // 容许的误差为1秒
        assertTrue(expirationMillis - currentMillis >= expirationTime - allowableDeviation);
        assertTrue(expirationMillis - currentMillis <= expirationTime + allowableDeviation);
    }

    @ParameterizedTest(name = "测试创建 JWT - 自定义UUID - subject: {0}, customUUID: {1}")
    @CsvSource({"testSubject1,customUUID1", "testSubject2,customUUID2"})
    void testCreateJWTWithCustomUUID(String subject, String customUUID) {
        String jwt = JwtUtils.createJWT(customUUID, subject, 10000L);

        assertNotNull(jwt);
        assertFalse(jwt.isEmpty());

        Claims claims = JwtUtils.parseJWT(jwt);
        assertNotNull(claims);
        assertEquals(customUUID, claims.getId());
    }
}
