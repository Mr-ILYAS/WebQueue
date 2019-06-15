package com.service;

import com.model.Item;

import java.util.List;

public interface ItemService {
	public void addItem(Item item);
	
	public void updateItem(Item item);
	
	public void removeItem(int id);
	
	public Item getItemById(int id);
	
	public List<Item> listItems();
	
	public List<Item> selectNeeded();
	
	public List<Item> selectNotNeeded();
	
	public int countOfComputer();
	
	public Item getItemByName(String name);
	
	public List<Item> listNext();
	
	public void listPrevious();
	
	public List list();
	
	public List currentList();
	
	public List getFullList();
	
	public void redirectFrom();
	
}
