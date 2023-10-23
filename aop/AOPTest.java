package com.keti.iam.idthub.aop;

import com.keti.iam.idthub.controller.user.UserController;
import com.keti.iam.idthub.dto.user.UserInfoResponseDto;
import com.keti.iam.idthub.mapper.UserMapper;
import com.keti.iam.idthub.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class AOPTest {

    @Autowired
    UserService userService;

    final String testId = "dd4919da-efe4-4c65-a212-681885cc34f4";

    @Test
    @DisplayName("aop proxy 적용 여부")
    public void aopInfo(){
        Assertions.assertTrue(AopUtils.isAopProxy(userService));
    }

    @Test
    @DisplayName("aop test userTimerTransaction")
    public void testUserTimerTransaction(){
        UserInfoResponseDto userInfoResponseDto = userService.userInfoFindById(testId);
        String id = userInfoResponseDto.getId();

        org.assertj.core.api.Assertions.assertThat(id).isEqualTo(testId);
    }

}
