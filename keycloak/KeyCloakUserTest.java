package com.keti.iam.idthub.keycloak;

import com.keti.iam.idthub.util.exception.RestException;
import com.keti.iam.idthub.util.keycloak.KeyCloakResourceAPI;
import com.keti.iam.idthub.util.keycloak.KeyCloakUser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class KeyCloakUserTest extends KeyCloakTest{

    @Autowired
    private KeyCloakUser keyCloakUser;

    @Autowired
    private KeyCloakResourceAPI keyCloakResourceAPI;




    @Test
    @DisplayName("아이디로 유저 정보 가져오기")
    public void userInfoFindById() throws RestException {
        UserResource userResource = keyCloakUser.keyCloakUserInfoFindById(TEST_ID);
        UserRepresentation userRepresentation = userResource.toRepresentation();

        String id = userRepresentation.getId();
        String firstName = userRepresentation.getFirstName();
        String email = userRepresentation.getEmail();

        Assertions.assertThat(id).isEqualTo(TEST_ID);
        Assertions.assertThat(email).isEqualTo(TEST_EMAIL);
        Assertions.assertThat(firstName).isEqualTo("jaeYoung");
    }

    @Test
    @DisplayName("로그인 이메일로 아이디 찾기 검증")
    public void idFindByEmail() throws RestException {
        List<UserRepresentation> userRepresentations = keyCloakUser.keyCloakUserFindByLoginId(LOGIN_ID);
        String id = userRepresentations.get(0).getId();
        String email = userRepresentations.get(0).getEmail();


        Assertions.assertThat(id).isEqualTo(TEST_ID);
        Assertions.assertThat(email).isEqualTo(TEST_EMAIL);
    }

    @Test
    @DisplayName("유저가 가지고 있는 그룹")
    public void userHaveAGroup() throws RestException {
        List<String> groupIdList = keyCloakUser.userGroupIdList("wodud4019@gmail.com" , true);
        for (String groupId : groupIdList) {
            Assertions.assertThat(groupIdList).contains(groupId);
        }
        List<UserRepresentation> userRepresentations = keyCloakUser.keyCloakUserFindByLoginId("wodud4019@gmail.com");
        String id = userRepresentations.get(0).getId();

        List<String> groupNameList = keyCloakUser.userGroupNameList(id , false);
        for (String groupName : groupNameList) {
            Assertions.assertThat(groupNameList).contains(groupName);
        }
    }
}
