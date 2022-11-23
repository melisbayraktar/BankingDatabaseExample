package entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

	@Getter
	@Setter
	@NoArgsConstructor
	@Entity
	@Table(name="accounts")
	@ToString
	public class Accounts {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private long id;
	    @Column(unique=true)
	    private long accountId;
	    private String accountType;

	    @OneToOne(mappedBy="account")
	    private BankAccountNo bankAccountNo;


	}

