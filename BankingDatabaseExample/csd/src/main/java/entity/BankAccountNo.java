package entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.ToString.Exclude;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="bank_account_no")
@ToString (exclude = {"user"})
public class BankAccountNo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique=true)
    private long accountNo;

    @OneToOne
    @JoinColumn(name="branch_id", referencedColumnName="id")
    private Branch branch;

    @OneToOne
    @JoinColumn(name="account_id", referencedColumnName="id")
    private Accounts account;
   
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false, referencedColumnName = "id")
    private User user;
    
    @OneToMany(mappedBy="bankAccount")
    private List<Transactions> transactionList;



}