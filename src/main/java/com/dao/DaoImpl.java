package com.dao;

import com.model.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class DaoImpl implements Dao {
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	@Transactional
	public void addItem(Item item) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(item);
	}
	
	@Override
	@Transactional
	public void updateItem(Item item) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(item);
	}
	
	@Override
	@Transactional
	public void removeItem(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Item item = session.load(Item.class, new Integer(id));
		if(item !=null){
			session.delete(item);
		}
	}
	
	@Override
	@Transactional
	public Item getItemById(int id) {
		Session session =this.sessionFactory.getCurrentSession();
		Item item = session.get(Item.class, new Integer(id));
		return item;
	}
	
	@Override
	@Transactional
	public List<Item> listItems() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Item> itemsList = session.createQuery("from Item").list();
		return itemsList;
	}
}