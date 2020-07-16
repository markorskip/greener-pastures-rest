package io.greenerpastures.rest.subscription.controller;

import io.greenerpastures.rest.subscription.repository.SubscriberRepository;
import io.greenerpastures.rest.subscription.model.Subscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Optional;

@RestController
public class SubscriberController {

    private final SubscriberRepository repository;

    @Autowired
    public SubscriberController(SubscriberRepository repository) {
        this.repository = repository;
    }

    // TODO disabled from production
    @GetMapping("/bulkload")
    public String bulkload() {
        repository.saveAll(
                Arrays.asList(
                        new Subscriber("test1","testlast","asdfasd@asdfasd.com"),
                        new Subscriber("test1","testlast","asdf123@asdfasd.com")
                ));
        return "Subscribers added";
    }

    // TODO secure this endpoint
    // TODO make endpoint for showing only active
    // TODO make endpoint showing metrics
    @GetMapping("/all")
    public Iterable<Subscriber> all() {
        return repository.findAll();
    }

    // TODO test how this works when the user hit subscribe multiple times, how it affects createdDate, and how
    // it works for someone who has unsubscribed
    @PostMapping(value = "/subscribe", consumes = "application/json", produces = "application/json")
    public Subscriber upsertSubscriber(@RequestBody Subscriber requestSubscriber) {
        Optional<Subscriber> findSubscriber = repository.findById(requestSubscriber.getEmailAddress());

        if (!findSubscriber.isPresent()) {
            System.out.println(requestSubscriber);
            return repository.save(requestSubscriber);
        }
        Subscriber foundSubscriber = findSubscriber.get();

        // If user already exists but had unsubscribed, wipe out unsubscribe date
        if (foundSubscriber.getUnsubscribeDate() != null) {
            foundSubscriber.setUnsubscribeDate(null);
            repository.save(foundSubscriber);
        }

        if (requestSubscriber.getFirstName() != null) {
            foundSubscriber.setFirstName(requestSubscriber.getFirstName());
            repository.save(foundSubscriber);
        }

        if (requestSubscriber.getLastName() != null) {
            foundSubscriber.setLastName(requestSubscriber.getLastName());
            repository.save(foundSubscriber);
        }

        return findSubscriber.get();
    }

    // TODO test how this works with the user doesn't exist, does exist, or has already unsubscribed
    @PutMapping("/unsubscribe")
    public Subscriber unsubscribe(@RequestBody Subscriber unsubscribe) {
        Optional<Subscriber> findSubscriber = repository.findById(unsubscribe.getEmailAddress());

        if (findSubscriber.isPresent()) {
            findSubscriber.get().unsubscribe();
            return repository.save(findSubscriber.get());
        } else {
            return null;
        }

    }

}
