package io.greenerpastures.rest.subscription.model;


import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Table(name = "subscriber")
public class Subscriber implements Serializable {

    @Id
    private String emailAddress;

    private String firstName;

    private String lastName;

    @CreationTimestamp
    private Date subscriptionStartDate;

    private Date unsubscribeDate;

    public Subscriber(String firstName, String lastName, String emailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
    }

    public Subscriber() {
    }

    public void unsubscribe() {
        this.unsubscribeDate = new Date();
    }

}
