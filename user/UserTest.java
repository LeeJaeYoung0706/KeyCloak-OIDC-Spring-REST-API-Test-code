package com.keti.iam.idthub.user;

import com.keti.iam.idthub.dto.user.UserInfoResponseDto;
import com.keti.iam.idthub.dto.user.UserSaveRequestDto;
import com.keti.iam.idthub.mapper.UserMapper;
import com.keti.iam.idthub.service.user.UserService;
import com.keti.iam.idthub.util.exception.RestException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@SpringBootTest
public class UserTest {

    @Autowired
    UserService userService;
    @Autowired
    UserMapper userMapper;

    private final String TEST_ID = "a3e0e937-a23a-4af5-a09a-388deb3cf14f";

    @Test
    @DisplayName("유저 존재하는지 체크")
    void userCountTestByKeyCloakId(){
        int i = userMapper.countFindById(TEST_ID);

        Assertions.assertThat(i).isEqualTo(1);
    }

    @Test
    @DisplayName("유저 저장 테스트")
    void userSaveTest() throws RestException {
        UserSaveRequestDto user = new UserSaveRequestDto();
        user.setCompany("testCompany");
        user.setLocation("testLocation");
        user.setName("testName");
        user.setFirstLoginRequest(true);
        user.setWebSite("www.test.co.kr");



        userService.initSave(user, new Authentication() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public Object getCredentials() {
                return null;
            }

            @Override
            public Object getDetails() {
                return null;
            }

            @Override
            public Object getPrincipal() {
                return null;
            }

            @Override
            public boolean isAuthenticated() {
                return false;
            }

            @Override
            public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

            }

            @Override
            public String getName() {
                return "a3e0e937-a23a-4af5-a09a-388deb3cf14f";
            }
        });
        int i = userMapper.countFindById("a3e0e937-a23a-4af5-a09a-388deb3cf14f");


        Assertions.assertThat(i).isEqualTo(1);
    }

    @Test
    @DisplayName("유저 아이디로 유저 정보 찾기")
    void findByIdTest(){
        UserInfoResponseDto userInfoResponseDto = userService.userInfoFindById(TEST_ID);

        Assertions.assertThat(TEST_ID).isEqualTo(userInfoResponseDto.getId());
        org.junit.jupiter.api.Assertions.assertFalse(userInfoResponseDto.isDeactivated());
        Assertions.assertThat("testName").isEqualTo(userInfoResponseDto.getName());
    }
}
