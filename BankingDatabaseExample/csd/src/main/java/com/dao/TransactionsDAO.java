package com.dao;

import java.util.List;

import org.hibernate.Session;

import entity.Transactions;
import jakarta.persistence.TypedQuery;

public class TransactionsDAO implements IRepository<Transactions> {
	@Override
	public void create(Transactions entity) {
		Session session = null;

		try {
			session = databaseConnection();
			session.getTransaction().begin();
			System.out.println(session);
			session.persist(entity);
			session.getTransaction().commit();
			System.out.println("Transaction data is added to the database");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Some problem occured while adding transaction data.");
		} finally {
			session.close();
		}
	}

	@Override
	public void update(long id, Transactions entity) {

		Session session = null;
		try {
			System.out.println(find(id));
			Transactions updateTransaction = find(id);
			updateTransaction.setTransactionId(entity.getTransactionId());
			updateTransaction.setProcess(entity.getProcess());
			updateTransaction.setAmount(entity.getAmount());

			session = databaseConnection();
			session.getTransaction().begin();
			session.merge(updateTransaction);
			session.getTransaction().commit();
			System.out.println("Successfully updated.");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Some problem occured while UPDATING transaction data.");
		} finally {
			session.close();
		}
	}

	@Override
	public void delete(long id) {
		Session session = null;

		try {
			Transactions deleteTransactions = find(id);
			if (deleteTransactions != null) {
				session = databaseConnection();
				session.getTransaction().begin();
				session.remove(deleteTransactions);
				session.getTransaction().commit();

				System.out.println("Successfully deleted");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Some problem occured while DELETING transaction data.");
		} finally {
			session.close();
		}
	}

	@Override
	public List<Transactions> listAll() {
		Session session = databaseConnection();

		String hql = "select transactionId from transactions transaction";

		TypedQuery<Transactions> typedQuery = session.createQuery(hql, Transactions.class);
		List<Transactions> addressList = typedQuery.getResultList();

		return addressList;
	}

	@Override
	public Transactions find(long id) {

		Transactions acc = null;
		Session session = databaseConnection();

		try {
			acc = session.find(Transactions.class, id);

			if (acc != null) {
				System.out.println("Found transaction : " + acc);
			} else {
				System.out.println("Transaction not found");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Some problem occured while FIND Transaction data.");
		} finally {
			session.close();
		}
		return acc;
	}

}
