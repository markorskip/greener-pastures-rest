package io.greenerpastures.rest;

import io.greenerpastures.rest.subscription.model.Subscriber;
import io.greenerpastures.rest.subscription.repository.SubscriberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    SubscriberRepository repository;

    @PostConstruct
    public void init() {
        System.out.println("Init logic");
        Subscriber subscriber = new Subscriber("MArk", "Sorenson", "markorskip@gmail.com");
        repository.save(subscriber);
        Iterable<Subscriber> list = repository.findAll();

        for (Subscriber s: list) {
            System.out.println(s);
        }

        // TODO - in the init check if the tables are empty, if so initialize them
    }

}
