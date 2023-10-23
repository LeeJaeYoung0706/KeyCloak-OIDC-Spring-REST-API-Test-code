package com.keti.iam.idthub.keycloak;

import com.keti.iam.idthub.util.exception.RestException;
import com.keti.iam.idthub.util.keycloak.KeyCloakGroup;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.keycloak.representations.idm.GroupRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class KeyCloakGroupTest extends KeyCloakTest{

    @Autowired
    private KeyCloakGroup keyCloakGroup;

    @Test
    @DisplayName("키클록 그룹 생성 테스트") // 1sec 880 ms
    public void createGroupTest() throws RestException {
        GroupRepresentation createGroupTest = keyCloakGroup.createKeyCloakGroups("createGroupTest1", "/createGroupTest1");

        Assertions.assertThat(createGroupTest.getName()).isEqualTo("createGroupTest1");
        Assertions.assertThat(createGroupTest.getPath()).isEqualTo("/createGroupTest1");
    }


    @Test
    @DisplayName("하위 그룹 생성 테스트")
    public void createSubGroupTest() throws RestException {
        GroupRepresentation createGroupTest = keyCloakGroup.createKeyCloakGroups("createGroupTest2", "/createGroupTest2");
        GroupRepresentation groupRepresentation = keyCloakGroup.addSubGroup(createGroupTest, "createGroupTest2/sub", "/createGroupTest2/sub");

        Assertions.assertThat(groupRepresentation.getName()).isEqualTo("createGroupTest2/sub");
        Assertions.assertThat(groupRepresentation.getPath()).isEqualTo("/createGroupTest2/sub");

        GroupRepresentation groupRepresentation1 = keyCloakGroup.addSubGroup(groupRepresentation, "createGroupTest2/sub/sub", "/createGroupTest2/sub/sub");

        Assertions.assertThat(groupRepresentation1.getName()).isEqualTo("createGroupTest2/sub/sub");
        Assertions.assertThat(groupRepresentation1.getPath()).isEqualTo("/createGroupTest2/sub/sub");
    }

    @Test
    @DisplayName("키클록 그룹 존재 체크")
    public void existGroupCheck() throws RestException {
        GroupRepresentation keyCloakGroups = keyCloakGroup.createKeyCloakGroups("groupFindTest", "groupFindTest");

        String id = keyCloakGroups.getId();
        boolean exist = keyCloakGroup.keyCloakGroupExist(id);
        boolean exist1 = keyCloakGroup.keyCloakGroupExist("test Not Exist");

        org.junit.jupiter.api.Assertions.assertTrue(exist);
        org.junit.jupiter.api.Assertions.assertFalse(exist1);
    }
}
