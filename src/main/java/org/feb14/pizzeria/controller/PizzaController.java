package org.feb14.pizzeria.controller;

import java.util.List;

import org.feb14.pizzeria.model.Pizza;
import org.feb14.pizzeria.repository.PizzaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pizze")
public class PizzaController {
	// iniettiamo automaticamente 
		private @Autowired PizzaRepository pizzaRepository;
		
		@GetMapping
		public String index(Model modIndex) {
			List<Pizza> pizzaList = pizzaRepository.findAll(); // restituisce un elenco di istanze libro
			modIndex.addAttribute("pizze", pizzaList);
			return "pizza/index";
			}
}
