package com.dao;

import com.model.Item;

import java.util.List;

public interface Dao {
	public void addItem(Item item);
	public void updateItem(Item item);
	public void removeItem(int id);
	public Item getItemById(int id);
	public List<Item> listItems();
}