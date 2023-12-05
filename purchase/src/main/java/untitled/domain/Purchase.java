package untitled.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import untitled.PurchaseApplication;
import untitled.domain.FishSold;
import untitled.domain.PurchaseCreated;

@Entity
@Table(name = "Purchase_table")
@Data
//<<< DDD / Aggregate Root
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long purchaseNo;

    private String purchaseType;

    private LocalDate purchaseDate;

    private LocalDate warehouseArrivalDate;

    private LocalDate storageFeePaymentDate;

    private Boolean storageFeePaymentStatus;

    private String mainShipName;

    private String productName;

    @Embedded
    private AccountId accountId;

    @ElementCollection
    private List<PurchaseDetail> purchaseDetails;

    @PostPersist
    public void onPostPersist() {
        PurchaseCreated purchaseCreated = new PurchaseCreated(this);
        purchaseCreated.publishAfterCommit();

        FishSold fishSold = new FishSold(this);
        fishSold.publishAfterCommit();
    }

    @PrePersist
    public void onPrePersist() {}

    public static PurchaseRepository repository() {
        PurchaseRepository purchaseRepository = PurchaseApplication.applicationContext.getBean(
            PurchaseRepository.class
        );
        return purchaseRepository;
    }

    //<<< Clean Arch / Port Method
    public void sale(SaleCommand saleCommand) {
        //implement business logic here:

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
