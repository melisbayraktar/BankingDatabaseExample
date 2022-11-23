package com.dao;

import java.util.List;

import org.hibernate.Session;

import entity.BankAccountNo;
import jakarta.persistence.TypedQuery;
import entity.Branch;

public class BankAccountNoDAO implements IRepository<BankAccountNo> {

	public void create(BankAccountNo entity) {

		Session session = null;
		try {
			session = databaseConnection();
			session.getTransaction().begin();
			session.persist(entity);
			session.getTransaction().commit();
			System.out.println("BankAccountNo data is added to Db.");
		} catch (Exception e) {
			e.getMessage();
			System.err.println("Some problem occured while adding BankAccountNo data.");
		} finally {
			session.close();
		}

	}

	public void update(long id, BankAccountNo entity) {

		Session session = null;
		try {
			BankAccountNo updateBankAccountNo = find(id);
			updateBankAccountNo.setAccountNo(entity.getAccountNo());
			updateBankAccountNo.setUser(entity.getUser());
			updateBankAccountNo.setAccount(entity.getAccount());
			updateBankAccountNo.setBranch(entity.getBranch());

			session = databaseConnection();
			session.getTransaction().begin();
			session.merge(updateBankAccountNo);
			session.getTransaction().commit();
			System.out.println("Successfully updated.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Some problem occured while UPDATING User data.");
		} finally {
			session.close();
		}

	}

	public void delete(long id) {

		Session session = null;

		try {
			BankAccountNo deleteBankAccountNo = find(id);
			if (deleteBankAccountNo != null) {
				session = databaseConnection();
				session.getTransaction().begin();
				session.remove(deleteBankAccountNo);
				session.getTransaction().commit();

				System.out.println("Successfully deleted");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Some problem occured while DELETING BankAccountNo data.");
		} finally {
			session.close();
		}

	}

	public List<BankAccountNo> listAll() {

		Session session = databaseConnection();
		String hql = "select accountNo from BankAccountNo as accountNo";

		TypedQuery<BankAccountNo> typedQuery = session.createQuery(hql, BankAccountNo.class);
		List<BankAccountNo> bankAccountNoList = typedQuery.getResultList();

		return bankAccountNoList;

	}

	public BankAccountNo find(long id) {

		BankAccountNo bankAccountNo = null;
		Session session = databaseConnection();

		try {
			bankAccountNo = session.find(BankAccountNo.class, id);

			if (bankAccountNo != null) {
				System.out.println("Found BankAccountNo : " + bankAccountNo);
			} else {
				System.out.println("BankAccountNo not found");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Some problem occured while FIND BankAccountNo data.");
		} finally {
			session.close();
		}
		return bankAccountNo;

	}

}