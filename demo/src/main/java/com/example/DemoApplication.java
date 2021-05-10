package com.example;

import com.example.entity.Person;
import com.example.repository.PersonRepository;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


@SpringBootApplication
@MapperScan("com.example.mapper")
public class DemoApplication {

    public static void main(String[] args) {
        
        SpringApplication.run(DemoApplication.class, args);
    }

}
