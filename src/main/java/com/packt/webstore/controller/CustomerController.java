package com.packt.webstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.packt.webstore.service.CustomerSerivce;

@Controller
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerSerivce customerService;

	@RequestMapping
	public String allCustomers(Model model) {
		model.addAttribute("customers", customerService.getAllCustomers());
		return "customers";
	}

}
