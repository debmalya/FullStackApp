package com.rakuten.fullstackrecruitmenttest;

import com.rakuten.fullstackrecruitmenttest.configuration.UploadConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({UploadConfiguration.class})
public class FullStackRecruitmentTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(FullStackRecruitmentTestApplication.class, args);
	}
}
