package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import dto.Customer;

public class MyDao {
EntityManagerFactory factory=Persistence.createEntityManagerFactory("dev");
EntityManager manager=factory.createEntityManager();
EntityTransaction transaction=manager.getTransaction();

public void save(Customer customer)
{
	transaction.begin();
	manager.persist(customer);
	transaction.commit();
}

public Customer fetchByEmail(String Email)
{
	
	List<Customer> list=manager.createQuery("select x from Customer x where Email=?1").setParameter(1, Email)
							.getResultList();
	if(list.isEmpty()) 
	{
		return null;
	}else {
		return list.get(0);
	}
}

public Customer fetchByMobile(long Mobile)
{
	List<Customer> list=manager.createQuery("select x from Customer x where Mobile=?1").setParameter(1,Mobile)
							.getResultList();
	if(list.isEmpty()) 
	{
		return null;
	}else {
		return list.get(0);
	}

	
}
}
