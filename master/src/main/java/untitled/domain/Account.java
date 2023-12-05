package untitled.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import untitled.MasterApplication;
import untitled.domain.AccountRegistered;

@Entity
@Table(name = "Account_table")
@Data
//<<< DDD / Aggregate Root
public class Account {

    @Id
    private String businessRegistrationNumber;

    private String accountName;

    private String representative;

    @Embedded
    private Address address;

    private String industry;

    private String category;

    private String customerType;

    @PostPersist
    public void onPostPersist() {
        AccountRegistered accountRegistered = new AccountRegistered(this);
        accountRegistered.publishAfterCommit();
    }

    public static AccountRepository repository() {
        AccountRepository accountRepository = MasterApplication.applicationContext.getBean(
            AccountRepository.class
        );
        return accountRepository;
    }
}
//>>> DDD / Aggregate Root
