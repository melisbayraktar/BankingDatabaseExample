package com.dao;

import java.util.List;

import org.hibernate.Session;

import entity.Branch;
import jakarta.persistence.TypedQuery;

public class BranchDAO implements IRepository<Branch> {
	@Override
	public void create(Branch entity) {
		Session session = null;

		try {
			session = databaseConnection();
			session.getTransaction().begin();
			System.out.println(session);
			session.persist(entity);
			session.getTransaction().commit();
			System.out.println("Branch data is added to the database");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Some problem occured while adding Branch data.");
		} finally {
			session.close();
		}
	}

	@Override
	public void update(long id, Branch entity) {

		Session session = null;
		try {
			System.out.println(find(id));
			Branch updateBranch = find(id);
			updateBranch.setBranchId(entity.getBranchId());
			updateBranch.setBranchLocation(entity.getBranchLocation());
			updateBranch.setBankAccountNo(entity.getBankAccountNo());

			session = databaseConnection();
			session.getTransaction().begin();
			session.merge(updateBranch);
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
			Branch deleteBranch = find(id);
			if (deleteBranch != null) {
				session = databaseConnection();
				session.getTransaction().begin();
				session.remove(deleteBranch);
				session.getTransaction().commit();

				System.out.println("Successfully deleted");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Some problem occured while DELETING branch data.");
		} finally {
			session.close();
		}
	}

	@Override
	public List<Branch> listAll() {
		Session session = databaseConnection();

		String hql = "select br from Branch as br";

		TypedQuery<Branch> typedQuery = session.createQuery(hql, Branch.class);
		List<Branch> addressList = typedQuery.getResultList();

		return addressList;
	}

	@Override
	public Branch find(long id) {
		Branch br = null;
		Session session = databaseConnection();

		try {
			br = session.find(Branch.class, id);

			if (br != null) {
				System.out.println("Found branch : " + br);
			} else {
				System.out.println("Branch not found");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Some problem occured while FIND branch data.");
		} finally {
			session.close();
		}
		return br;
	}

}
