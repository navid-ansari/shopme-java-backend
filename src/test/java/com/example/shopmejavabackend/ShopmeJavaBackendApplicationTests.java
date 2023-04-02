package com.example.shopmejavabackend;

import com.example.shopmejavabackend.controllers.HealthCheckController;
import com.example.shopmejavabackend.controllers.ProductsController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@DisplayName("AppicationContext Test")
class ShopmeJavaBackendApplicationTests {

	@Autowired
	private HealthCheckController healthCheckController;

	@Autowired
	private ProductsController productsController;

	@Test
	@DisplayName("Application context")
	void contextLoads() {
		assertThat(healthCheckController).isNotNull();
		assertThat(productsController).isNotNull();
	}

	private Calculator calculator = new Calculator();

	@Test
	void testSum() {
		int expectedResult = 10;
		int actualResult = calculator.sum(5, 5);
		assertThat(actualResult).isEqualTo(expectedResult);
	}

}
