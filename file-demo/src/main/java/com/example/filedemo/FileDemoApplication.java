package com.example.filedemo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
@SpringBootApplication
@ComponentScan(basePackages = "com.example.filedemo")

	public class FileDemoApplication {
		public static void main(String[] args) {
			SpringApplication.run(FileDemoApplication.class, args);
		}
}

