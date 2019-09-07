package pl.pocket.myPocket.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.transaction.Transactional;

@SpringBootApplication
public class MyPocketApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyPocketApplication.class, args);
	}

}
