package org.feb14.pizzeria.controller;

import java.util.Optional;

import org.feb14.pizzeria.model.OffertaSpeciale;
import org.feb14.pizzeria.model.Pizza;
import org.feb14.pizzeria.repository.OffertaSpecialeRepository;
import org.feb14.pizzeria.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/offerte")
public class OffertaSpecialeController {
	// iniettiamo automaticamente
	private @Autowired PizzaRepository pizzaRepository;
	private @Autowired OffertaSpecialeRepository offertaRepository;

	/*
	 * @GetMapping public String index(@RequestParam(name = "keyword", required =
	 * false) String keyword, Model modList) { //
	 * http://localhost:8080/pizze?keyword=margh List<Pizza> pizzaList; if (keyword
	 * == null) { pizzaList = pizzaRepository.findAll(); // restituisce un elenco di
	 * istanze libro } else { pizzaList = pizzaRepository.findByNameLike("%" +
	 * keyword + "%"); } modList.addAttribute("pizze", pizzaList); return
	 * "pizze/index"; }
	 * 
	 * @GetMapping("/{id}") public String show(@PathVariable("id") Integer id, Model
	 * modShow) {
	 * 
	 * 
	 * Optional<Pizza> pizza = pizzaRepository.findById(id); // restituisce
	 * un'istanza Optional con dentro // forse una pizza
	 * modShow.addAttribute("pizza", pizza.get()); return "pizze/detail";
	 * 
	 * 
	 * 
	 * }
	 */

	@GetMapping("/create")
	public String create(@RequestParam(name = "pizzaId", required = true) Integer pizzaId, Model model) {
		Optional<Pizza> res = pizzaRepository.findById(pizzaId);
		OffertaSpeciale offerta = new OffertaSpeciale();
		offerta.setPizza(res.get());
		model.addAttribute("offerta", offerta);
		return "offerte/create";
	}

	@PostMapping("/store") // gestirà le richieste di tipo POST di tipo /books/create
	public String store(@Valid @ModelAttribute("offerta") OffertaSpeciale formOfferta, BindingResult bindingResult,
			Model modelCreate) {
		if (bindingResult.hasErrors())
			return "offerte/create";

		offertaRepository.save(formOfferta);
		return "redirect:/offerte"; // genera un altro get e il ciclo si chiude

	}

	/*@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {
		Pizza pizza = pizzaRepository.getReferenceById(id);
		model.addAttribute("pizza", pizza);
		return "pizze/update";
	}

	@PostMapping("/update/{id}")
	public String update(@Valid @ModelAttribute Pizza formPizza, BindingResult result, Model model) {
		if (result.hasErrors())
			return "pizze/update";

		pizzaRepository.save(formPizza);
		return "redirect:/pizze";
	}

	@DeleteMapping("deletejs/{id}")
	public ResponseEntity<String> deletePizza(@PathVariable("id") Integer id) {

		pizzaRepository.deleteById(id);
		return ResponseEntity.ok("Pizza deleted successfully");
	}

	@PostMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {

		pizzaRepository.deleteById(id);

		return "redirect:/pizze";
	}*/
}
