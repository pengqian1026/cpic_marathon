package com.cpic.mia;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cpic.mia.**.mapper")
public class MiaApplication {
	public static void main(String[] args) {
		SpringApplication.run(MiaApplication.class, args);
	}

}
