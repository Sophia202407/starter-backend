package com.library.lms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import com.library.lms.config.JwtConfig;
import com.library.lms.config.LmsConfig;

@SpringBootApplication
@EnableConfigurationProperties({JwtConfig.class, LmsConfig.class})
public class LibraryManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementSystemApplication.class, args);
	}

}
