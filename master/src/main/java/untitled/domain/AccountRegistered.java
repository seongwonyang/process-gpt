package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class AccountRegistered extends AbstractEvent {

    private String businessRegistrationNumber;
    private String accountName;
    private String representative;
    private Address address;
    private String industry;
    private String category;
    private String customerType;

    public AccountRegistered(Account aggregate) {
        super(aggregate);
    }

    public AccountRegistered() {
        super();
    }
}
//>>> DDD / Domain Event
