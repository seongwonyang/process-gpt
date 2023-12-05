package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class ItemRegistered extends AbstractEvent {

    private Long id;
    private String itemName;
    private String itemCode;
    private ItemUnit itemUnit;

    public ItemRegistered(Item aggregate) {
        super(aggregate);
    }

    public ItemRegistered() {
        super();
    }
}
//>>> DDD / Domain Event
