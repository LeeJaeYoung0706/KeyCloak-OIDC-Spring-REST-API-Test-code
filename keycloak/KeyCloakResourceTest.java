package com.keti.iam.idthub.keycloak;

import com.keti.iam.idthub.util.keycloak.KeyCloakResourceAPI;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.keycloak.admin.client.resource.ClientsResource;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.representations.idm.ClientRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class KeyCloakResourceTest {

    @Autowired
    private KeyCloakResourceAPI keyCloakResourceAPI;

    @Test
    @DisplayName("리얼엠 리소스 테스트")
    public void realmTest(){
        RealmResource realmResource = keyCloakResourceAPI.getRealmResource();
        ClientsResource clients = realmResource.clients();
        List<ClientRepresentation> keti_iam_server_client = clients.findByClientId("test");

        for (ClientRepresentation clientRepresentation : keti_iam_server_client) {
            Assertions.assertThat(clientRepresentation.getClientId()).isEqualTo("test");
        }
    }
}
