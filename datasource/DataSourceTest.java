package com.keti.iam.idthub.datasource;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

@SpringBootTest
public class DataSourceTest {
    
    @Autowired
    private DataSource dataSource;
    
    @Test
    @DisplayName("데이터 소스 테스트")
    public void dbSourceTest() throws SQLException {
        Properties clientInfo = dataSource.getConnection().getClientInfo();
        String applicationName = clientInfo.getProperty("ApplicationName");

        Assertions.assertThat("PostgreSQL JDBC Driver").isEqualTo(applicationName);
    }
}
