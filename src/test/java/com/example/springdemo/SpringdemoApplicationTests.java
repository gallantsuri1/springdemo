package com.example.springdemo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.*;

import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

//@SpringBootTest
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

//@Configuration
//class TestingDataSourceConfig {
//
//	@Bean
//	@Primary
//	public DataSource dataSource() {
//		return new EmbeddedDatabaseBuilder()
//				.generateUniqueName(true)
//				.setType(EmbeddedDatabaseType.H2)
//				.build();
//	}
//}