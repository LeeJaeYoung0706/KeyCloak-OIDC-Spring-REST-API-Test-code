package com.keti.iam.idthub.bean_check;

import com.keti.iam.idthub.config.SecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;

import javax.servlet.Filter;
import java.util.List;

@SpringBootTest
public class BeanCheckTest {
    @Test
    @DisplayName("BeanCheckTest Security Configuration Annotation")
    public void beanAnnotationTest(){
        SecurityContext context = SecurityContextHolder.getContext();

    }
}
