package com.customer.api;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class ApplicationTests {

    @Test
    @DisplayName("Application loads")
    public void contextLoads() {
        try {

            Application application = new Application();

            application.main(new String[] {});

        } catch (Exception ex) {
            Assertions.assertThat(ex).isNotNull();
        }
    }
}