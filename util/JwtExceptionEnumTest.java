package com.keti.iam.idthub.util;

import com.keti.iam.idthub.util.exception.JwtExceptionEnum;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class JwtExceptionEnumTest {

    @Test
    @DisplayName("익셉션 분기 처리")
    void jwtExceptionEnumTest(){

        JwtExceptionEnum 유효하지않은토큰 = JwtExceptionEnum.findByErrorReason("An error occurred while attempting to decode the Jwt: Signed JWT rejected: Invalid signature");
        int 유효하지않은토큰코드 = 유효하지않은토큰.getCode(); // 301
        String 유효하지않은토큰에러 = 유효하지않은토큰.getERROR();//"만료된 토큰"
        JwtExceptionEnum 인증되지않은사용자 = JwtExceptionEnum.findByErrorReason("Full authentication is required to access this resource");
        int 인증되지않은사용자코드 = 인증되지않은사용자.getCode();
        String 인증되지않은사용자에러 = 인증되지않은사용자.getERROR();

        JwtExceptionEnum 만료된토큰 = JwtExceptionEnum.findByErrorReason("An error occurred while attempting to decode the Jwt: Jwt expired at 2023-02-10T02:30:06Z");
        int 만료된토큰코드 = 만료된토큰.getCode();
        String 만료된토큰에러 = 만료된토큰.getERROR();



        Assertions.assertThat(유효하지않은토큰코드).isEqualTo(300);
        Assertions.assertThat(유효하지않은토큰에러).isEqualTo("유효하지 않은 토큰");

        Assertions.assertThat(인증되지않은사용자코드).isEqualTo(401);
        Assertions.assertThat(인증되지않은사용자에러).isEqualTo("인증되지 않은 사용자");


        Assertions.assertThat(만료된토큰코드).isEqualTo(301);
        Assertions.assertThat(만료된토큰에러).isEqualTo("만료된 토큰");
    }
}
