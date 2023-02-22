package org.feb14.pizzeria.controller;

import org.feb14.pizzeria.repository.OffertaSpecialeRepository;
import org.feb14.pizzeria.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/offerte")
public class OffertaSpecialeController {
	// iniettiamo automaticamente
	private @Autowired PizzaRepository pizzaRepository;
	private @Autowired OffertaSpecialeRepository offertaRepository;

	/*@GetMapping
	public String index(@RequestParam(name = "keyword", required = false) String keyword, Model modList) {
		// http://localhost:8080/pizze?keyword=margh
		List<Pizza> pizzaList;
		if (keyword == null) {
			pizzaList = pizzaRepository.findAll(); // restituisce un elenco di istanze libro
		} else {
			pizzaList = pizzaRepository.findByNameLike("%" + keyword + "%");
		}
		modList.addAttribute("pizze", pizzaList);
		return "pizze/index";
	}

	@GetMapping("/{id}")
	public String show(@PathVariable("id") Integer id, Model modShow) {
		
						
				Optional<Pizza> pizza = pizzaRepository.findById(id); // restituisce un'istanza Optional con dentro
																			// forse una pizza
				modShow.addAttribute("pizza", pizza.get());
				return "pizze/detail";
			
			
		
	}

	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("pizza", new Pizza());
		return "pizze/create";
	}

	@PostMapping("/store") // gestirà le richieste di tipo POST di tipo /books/create
	public String store(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, // TODO : approfondire BindingResult
			Model modelCreate) {

		if (bindingResult.hasErrors())
			return "pizze/create";

		pizzaRepository.save(formPizza);
		return "redirect:/pizze"; // genera un altro get e il ciclo si chiude

	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {
		Pizza pizza = pizzaRepository.getReferenceById(id);
		model.addAttribute("pizza",pizza);
		return "pizze/update";
	}
	
	@PostMapping("/update/{id}")
	public String update(
			@Valid @ModelAttribute Pizza formPizza,
			BindingResult result, Model model
			) {
		if(result.hasErrors())
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
