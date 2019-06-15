package com.service;

import com.dao.Dao;
import com.model.Item;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
	private Dao dao;
	private List<Item> currentList = new ArrayList();
	List<Item> editList = new ArrayList();
	private int limit = 10;
	private int from = 0;
	
	public void setDao(Dao dao) {
		this.dao = dao;
	}
	
	@Override
	@Transactional
	public void addItem(Item item) {
		this.dao.addItem(item);
		from = 0;
	}
	
	@Override
	@Transactional
	public void updateItem(Item item) {
		this.dao.updateItem(item);
		from = 0;
	}
	
	@Override
	@Transactional
	public void removeItem(int id) {
		this.dao.removeItem(id);
		from = 0;
	}
	
	@Override
	@Transactional
	public Item getItemById(int id) {
		return this.dao.getItemById(id);
	}
	
	@Override
	@Transactional
	public List<Item> list() {
		currentList = this.dao.listItems();
		return currentList;
	}
	
	@Override
	@Transactional
	public List<Item> listItems() {
		if(from >= currentList.size()) return editList;
		editList.clear();
		for(int i = from; i < (from + limit); i++) {
			if(i < currentList.size()) editList.add(currentList.get(i));
		}
		from += limit;
		return editList;
	}
	
	@Override
	@Transactional
	public List<Item> currentList() {
		return currentList;
	}
	
	@Override
	@Transactional
	public List<Item> listNext() {
		return listItems();
	}
	
	@Override
	@Transactional
	public void listPrevious() {
		int x = from - 2*limit;
		if(x < 0) from = 0;
		else from = x;
	}
	
	@Override
	@Transactional
	public List<Item> selectNeeded(){
		currentList.clear();
		List<Item> list = this.dao.listItems();
		for(Item item : list) {
			if(item.getNeeded() == 1) currentList.add(item);
		}
		from = 0;
		return currentList;
	}
	
	@Override
	@Transactional
	public List<Item> selectNotNeeded(){
		currentList.clear();
		List<Item> list = this.dao.listItems();
		for(Item item : list) {
			if(item.getNeeded() == 0) currentList.add(item);
		}
		from = 0;
		return currentList;
	}
	
	@Override
	@Transactional
	public int countOfComputer() {
		List<Item> list = this.dao.listItems();
		List<Item> neededList = new ArrayList();
		for(Item item : list) {
			if(item.getNeeded() == 1) neededList.add(item);
		}
		int res = neededList.get(0).getQuantity();
		for(Item item : neededList) {
			if(res > item.getQuantity()) res = item.getQuantity();
		}
		return res;
	}
	@Transactional
	public Item getItemByName(String name) {
		List<Item> list = this.dao.listItems();
		for(Item item : list) {
			if(item.getName().equals(name)) return item;
		}
		Item item = new Item();
		item.setName("Неверное наименование");
		from = 0;
		return item;
	}
	
	@Override
	@Transactional
	public List<Item> getFullList() {
		return dao.listItems();
	}
	
	@Override
	public void redirectFrom() {
		from = 0;
	}
}
