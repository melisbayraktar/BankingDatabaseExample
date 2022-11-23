package entity;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="transactions")
@ToString
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique=true)
    private long transactionId;
    @Enumerated(EnumType.STRING)
    private Process process;
    private String date;
    @Column(unique=true)
    private long amount;

    @ManyToOne
    @JoinColumn(name="bankaccount_id", nullable = false, referencedColumnName ="id")
    private BankAccountNo bankAccount;

    public Transactions(long transactionId, Process process, String date, long amount) {
    	
    	this.transactionId = transactionId;
    	this.process = process;
    	this.date = date;
    	this.amount = amount;
    	
    	
    }
    

}