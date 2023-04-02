package com.example.shopmejavabackend.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@DisplayName("HealthCheckService Test")
public class HealthCheckServiceTest {

    @Test
    @DisplayName("check Healthcheck API")
    void testGetHealthCheck() {
        String expectedResult = "Healthcheck successfull";
        HealthCheckService healthCheckService = new HealthCheckService();
        String actualResult = healthCheckService.getHealthCheck();
        assertThat(actualResult).isEqualTo(expectedResult);
    }
}
