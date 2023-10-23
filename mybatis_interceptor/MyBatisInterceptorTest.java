package com.keti.iam.idthub.mybatis_interceptor;

import com.keti.iam.idthub.dto.user.UserInfoResponseDto;
import com.keti.iam.idthub.mapper.UserMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class MyBatisInterceptorTest {

    @Autowired
    UserMapper userMapper;

    final String testId = "a3e0e937-a23a-4af5-a09a-388deb3cf14f";

    @Test
    @DisplayName("인터셉터 테스트")
    public void interceptorTest(){

        Optional<UserInfoResponseDto> userInfoResponseDto = userMapper.userInfoFindById(testId);
        userInfoResponseDto.ifPresent(user ->
                Assertions.assertThat(testId).isEqualTo(user.getId())
        );
    }
}
