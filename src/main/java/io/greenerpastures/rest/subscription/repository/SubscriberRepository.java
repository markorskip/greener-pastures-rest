package io.greenerpastures.rest.subscription.repository;

import io.greenerpastures.rest.subscription.model.Subscriber;
import org.springframework.data.repository.CrudRepository;

public interface SubscriberRepository extends CrudRepository<Subscriber, String> {
}
