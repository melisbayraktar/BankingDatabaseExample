package com.dao;

import java.util.List;

import org.hibernate.Session;

import entity.User;
import jakarta.persistence.TypedQuery;


public class UserDAO implements IRepository<User> {

	@Override
	public void create(User entity) {
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
	public void update(long id, User entity) {
		
		Session session = null;
		try {
			System.out.println(find(id));
			User updateCustomer = find(id);
			updateCustomer.setUserId(entity.getUserId()); 
			updateCustomer.setName(entity.getName()); 
			updateCustomer.setLastname(entity.getLastname()); 
	
			session = databaseConnection();
			session.getTransaction().begin();
			session.merge(updateCustomer);
			session.getTransaction().commit();
			System.out.println("Successfully updated.");
			
		} catch (Exception e) {
		e.printStackTrace();
		System.out.println("Some problem occured while UPDATING customer data.");
		}finally {
			session.close();
		}	
	}

	@Override
	public void delete(long id) {
		Session session= null;
		
		try {
			User deleteUser = find(id);
			if(deleteUser != null) {
				session = databaseConnection();
				session.getTransaction().begin();
				session.remove(deleteUser);
				session.getTransaction().commit();
				
				System.out.println("Successfully deleted");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Some problem occured while DELETING user data.");		
		} finally {
			session.close();
		}
	}

	@Override
	public List<User> listAll() {
		Session session = databaseConnection();

		String hql = "select adr from User as adr";

		TypedQuery<User> typedQuery = session.createQuery(hql, User.class);
		List<User> addressList = typedQuery.getResultList();

		return addressList;
	}

	@Override
	public User find(long id) {
		User user = null;
		Session session = databaseConnection();
		
		try {
			user = session.find(User.class, id);
			
			if(user != null) {
				System.out.println("Found user : " + user);
			}else {
				System.out.println("User not found");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Some problem occured while FIND user data.");
		}finally {
			session.close();
		}
		return user;
	}

}