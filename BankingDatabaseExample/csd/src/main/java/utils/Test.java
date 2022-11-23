package utils;

import java.util.List;

import com.dao.AccountDAO;
import com.dao.BankAccountNoDAO;
import com.dao.BranchDAO;
import com.dao.TransactionsDAO;
import com.dao.UserDAO;

import entity.Accounts;
import entity.BankAccountNo;
import entity.Branch;
import entity.Process;
import entity.Transactions;
import entity.User;

public class Test {

	public static void main(String[] args) {

		User usr = new User();
		usr.setName("Ahmet");
		usr.setLastname("Kara");
		usr.setUserId(100);
		User usr2 = new User();
		usr2.setName("Ayşe");
		usr2.setLastname("Sarı");
		usr2.setUserId(101);

		Branch br = new Branch();
		br.setBranchId(67);
		br.setBranchLocation("Zonguldak");
		Branch br2 = new Branch();
		br2.setBranchId(74);
		br2.setBranchLocation("Bartın");
		Branch br3 = new Branch();
		br3.setBranchId(78);
		br3.setBranchLocation("Karabük");

		Accounts acc = new Accounts();
		acc.setAccountId(10);
		acc.setAccountType("TL");
		Accounts acc2 = new Accounts();
		acc2.setAccountId(11);
		acc2.setAccountType("Euro");
		Accounts acc3 = new Accounts();
		acc3.setAccountId(12);
		acc3.setAccountType("Dolar");
		
		

		
		Transactions tr1 = new Transactions(100001,Process.DEPOSIT,"02-01*2022",400);
		Transactions tr2 = new Transactions(100002,Process.WITHDRAW,"25-01-2022",200);
		Transactions tr3 = new Transactions(100003,Process.WITHDRAW,"20-01-2022",89);
		Transactions tr4 = new Transactions(100025,Process.DEPOSIT,"15-01-2022",200);
		Transactions tr5 = new Transactions(100058,Process.WITHDRAW,"02-02-2022",100);
		Transactions tr6 = new Transactions(101005,Process.DEPOSIT,"20-01-2022",200);
		Transactions tr7 = new Transactions(101048,Process.WITHDRAW,"20-01-2022",115);

		BankAccountNo bnk = new BankAccountNo();
		bnk.setAccountNo(74_100_10);
		bnk.setUser(usr);
		BankAccountNo bnk2 = new BankAccountNo();
		bnk2.setAccountNo(67_100_11);
		bnk2.setUser(usr);
		BankAccountNo bnk3 = new BankAccountNo();
		bnk3.setAccountNo(78_101_12);
		bnk3.setUser(usr2);
		BankAccountNo bnk4 = new BankAccountNo();
		bnk4.setAccountNo(74_101_10);
		bnk4.setUser(usr2);
		
		

		UserDAO dao = new UserDAO();
		dao.create(usr);
		dao.create(usr2);
		List<User> userList = dao.listAll();
		for (User user : userList) {
			System.out.println(user);
		}
		
		
		BankAccountNoDAO dao4 = new BankAccountNoDAO();
		dao4.create(bnk);
		dao4.create(bnk2);
		dao4.create(bnk3);
		dao4.create(bnk4);
		List<BankAccountNo> banka = dao4.listAll();
		for (BankAccountNo bank : banka) {
			System.out.println(bank);
		}
		

		BranchDAO dao2 = new BranchDAO();
		dao2.create(br);
		dao2.create(br2);
		dao2.create(br3);
		List<Branch> branchList = dao2.listAll();
		for (Branch bpbp : branchList) {
			System.out.println(bpbp);
		}

		AccountDAO dao3 = new AccountDAO();
		dao3.create(acc);
		dao3.create(acc2);
		dao3.create(acc3);
		List<Accounts> accountList = dao3.listAll();
		for (Accounts aco : accountList) {
			System.out.println(aco);
		}


//		TransactionsDAO dao5 = new TransactionsDAO();
//		dao5.create(tr1);
//		dao5.create(tr2);
//		dao5.create(tr3);
//		dao5.create(tr4);
//		dao5.create(tr5);
//		dao5.create(tr6);
//		dao5.create(tr7);
//		List<Transactions> tro = dao5.listAll();
//		for (Transactions tt : tro) {
//			System.out.println(tt);
//		}

		
	}
}
