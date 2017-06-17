package com.packt.webstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	/**
	 * ホームが画面を表示する
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/welcome")
	public String welcome(Model model) {

		model.addAttribute("greeting", "Welcome to Web Store.");
		model.addAttribute("tagline", "The one and only amazing web store");

		return "redirect:/welcome/greeting";
	}

	@RequestMapping("/welcome/greeting")
	public String greeting() {
		return "welcome";
	}

}
