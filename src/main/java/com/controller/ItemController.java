package com.controller;

import com.model.Item;
import com.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
public class ItemController {
	private ItemService itemService;
	
	@Autowired(required = true)
	@Qualifier(value = "ItemService")
	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}
	
	@RequestMapping(value = "items", method = RequestMethod.GET)
	public String listItems(Model model){
		if(this.itemService.currentList().size() == 0) this.itemService.list();
		model.addAttribute("fullList", this.itemService.getFullList());
		model.addAttribute("listAll", this.itemService.currentList());
		model.addAttribute("item", new Item());
		model.addAttribute("listItems", this.itemService.listItems());
		model.addAttribute("countComputers", this.itemService.countOfComputer());
		return "items";
	}
	
	@RequestMapping("redirect")
	public String initialize() {
		this.itemService.list();
		this.itemService.redirectFrom();
		return "redirect:/items";
	}
	
	@RequestMapping(value = "/items/add", method = RequestMethod.POST)
	public String addItem(@ModelAttribute("item") Item item) {
		if(item.getId() == 0){
			if(item.isValid()) this.itemService.addItem(item);
		}else {
			this.itemService.updateItem(item);
		}
		return "redirect:/redirect";
	}
	
	@RequestMapping(value = "/items/update", method = RequestMethod.POST)
	public String updateItem(@ModelAttribute("item") Item item) {
			this.itemService.updateItem(item);
		return "redirect:/redirect";
	}
	
	@RequestMapping(value = "/items/search", method = RequestMethod.POST)
	public String getItemByName(@ModelAttribute("item") Item item, Model model) {
		model.addAttribute("item", itemService.getItemByName(item.getName()));
		return "search";
	}
	
	@RequestMapping("/remove/{id}")
	public String removeItem(@PathVariable("id") int id){
		this.itemService.removeItem(id);
		return "redirect:/redirect";
	}
	
	@RequestMapping("next")
	public String nextList() {
		return "redirect:/items";
	}
	
	@RequestMapping("previous")
	public String previousList() {
		this.itemService.listPrevious();
		return "redirect:/index";
	}
	
	@RequestMapping(value = "/items/selectNeeded", method = RequestMethod.GET)
	public String selectNeeded() {
		this.itemService.selectNeeded();
		return "redirect:/items";
	}
	@RequestMapping(value = "/items/selectNotNeeded", method = RequestMethod.GET)
	public String selectNotNeeded() {
		this.itemService.selectNotNeeded();
		return "redirect:/index";
	}
	
	@RequestMapping(value = "/edit/{id}")
	public String editItem(@PathVariable("id") int id, Model model) {
	model.addAttribute("item", this.itemService.getItemById(id));
	model.addAttribute("listItems", this.itemService.listItems());
	return "items";
	}
}