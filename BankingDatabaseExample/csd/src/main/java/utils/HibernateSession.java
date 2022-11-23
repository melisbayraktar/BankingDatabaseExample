package utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import entity.Accounts;
import entity.BankAccountNo;
import entity.Branch;
import entity.Transactions;
import entity.User;

public class HibernateSession {

    public class sessionFactory {

	}
	private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {

        if(HibernateSession.sessionFactory == null ) {
            HibernateSession.sessionFactory = createSessionFactory();
            }
        return HibernateSession.sessionFactory;


    }
    private static SessionFactory createSessionFactory() {
        Configuration conf = new Configuration();


        conf.addAnnotatedClass(Accounts.class);
        conf.addAnnotatedClass(BankAccountNo.class);
        conf.addAnnotatedClass(Branch.class);
        conf.addAnnotatedClass(Transactions.class);
        conf.addAnnotatedClass(User.class);

        SessionFactory sessionFactory = conf.configure("Hibernate.cfg.xml").buildSessionFactory();
        System.out.println("Connection to PostgreSQL via Hibernate is successful.");
        return sessionFactory;
    }


}