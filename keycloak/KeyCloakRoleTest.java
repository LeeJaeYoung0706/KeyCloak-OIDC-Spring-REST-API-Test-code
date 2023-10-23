package com.keti.iam.idthub.keycloak;

import com.keti.iam.idthub.util.exception.RestException;
import com.keti.iam.idthub.util.keycloak.KeyCloakRole;
import com.keti.iam.idthub.util.keycloak.KeyCloakUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.ws.rs.NotFoundException;

@SpringBootTest
public class KeyCloakRoleTest extends KeyCloakTest{

    @Autowired
    KeyCloakRole keyCloakRole;

    @Autowired
    KeyCloakUser keyCloakUser;

    @Test
    @DisplayName("키클락 롤 생성")
    public void keyCloakCreateRoleTest() throws RestException {
        keyCloakRole.createRole("USER_001");

        RoleRepresentation roleRepresentation = keyCloakRole.clientRoleFind("USER_001");
        org.assertj.core.api.Assertions.assertThat(roleRepresentation.getName().toString()).isEqualTo("USER_001");
    }


    @Test
    @DisplayName("롤 부여")
    public void keyCloakRoleAuthorization() throws RestException {
        keyCloakRole.userRoleSetting(TEST_ID , "USER_001");
        boolean user_001 = keyCloakRole.isUserRoleFindByRoleName(TEST_ID, "USER_001");

        Assertions.assertTrue(user_001);
    }


    @Test
    @DisplayName("유저가 가진 권한")
    public void keyCloakRoleFind() throws RestException {
        boolean user_001 = keyCloakRole.isUserRoleFindByRoleName(TEST_ID, "USER_001");
        RestException notFoundException = Assertions.assertThrows(RestException.class, () -> {
            keyCloakRole.clientRoleFind("USER_002");
        });

        org.assertj.core.api.Assertions.assertThat("KeyCloak Error Client Role Not Found").isEqualTo(notFoundException.getMessage());
        Assertions.assertTrue(user_001);
    }
}
