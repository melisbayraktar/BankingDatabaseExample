package com.dao;

import java.util.List;

import org.hibernate.Session;

import entity.Accounts;
import jakarta.persistence.TypedQuery;

public class AccountDAO implements IRepository<Accounts> {
	@Override
	public void create(Accounts entity) {
		Session session = null;

		try {
			session = databaseConnection();
			session.getTransaction().begin();
			System.out.println(session);
			session.persist(entity);
			session.getTransaction().commit();
			System.out.println("Customer data is added to the database");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Some problem occured while adding customer data.");
		} finally {
			session.close();
		}
	}

	@Override
	public void update(long id, Accounts entity) {

		Session session = null;
		try {
			System.out.println(find(id));
			Accounts updateCustomer = find(id);
			updateCustomer.setAccountId(entity.getAccountId());
			updateCustomer.setAccountType(entity.getAccountType());
			updateCustomer.setBankAccountNo(entity.getBankAccountNo());
			

			
			session = databaseConnection();
			session.getTransaction().begin();
			session.merge(updateCustomer);
			session.getTransaction().commit();
			System.out.println("Successfully updated.");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Some problem occured while UPDATING customer data.");
		} finally {
			session.close();
		}
	}

	@Override
	public void delete(long id) {
		Session session = null;

		try {
			Accounts deleteAccount = find(id);
			if (deleteAccount != null) {
				session = databaseConnection();
				session.getTransaction().begin();
				session.remove(deleteAccount);
				session.getTransaction().commit();

				System.out.println("Successfully deleted");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Some problem occured while DELETING account data.");
		} finally {
			session.close();
		}
	}

	@Override
	public List<Accounts> listAll() {
		Session session = databaseConnection();

		String hql = "select acc from Accounts as acc";

		TypedQuery<Accounts> typedQuery = session.createQuery(hql, Accounts.class);
		List<Accounts> addressList = typedQuery.getResultList();

		return addressList;
	}

	@Override
	public Accounts find(long id) {

		Accounts acc = null;
		Session session = databaseConnection();

		try {
			acc = session.find(Accounts.class, id);

			if (acc != null) {
				System.out.println("Found account : " + acc);
			} else {
				System.out.println("User not found");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Some problem occured while FIND account data.");
		} finally {
			session.close();
		}
		return acc;
	}

}
