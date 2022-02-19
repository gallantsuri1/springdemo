package com.example.springdemo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.boot.SpringApplication;

import static org.mockito.Mockito.mockStatic;

@ExtendWith(MockitoExtension.class)
class SpringdemoApplicationTests {

	@Test
	void main() {
		MockedStatic<SpringApplication> mock1 = mockStatic(SpringApplication.class);
		mock1.when(() -> SpringApplication.run(SpringdemoApplication.class, null))
				.thenAnswer((Answer<Void>) invocation -> null);
		SpringdemoApplication.main(null);
	}

}