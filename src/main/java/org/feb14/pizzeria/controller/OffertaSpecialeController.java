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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/offerte")
public class OffertaSpecialeController {
	// iniettiamo automaticamente
	private @Autowired PizzaRepository pizzaRepository;
	private @Autowired OffertaSpecialeRepository offertaRepository;

	@GetMapping("/create")
	public String create(@RequestParam(name = "pizzaId", required = true) Integer pizzaId, Model model) {
		Optional<Pizza> res = pizzaRepository.findById(pizzaId);
		OffertaSpeciale offerta = new OffertaSpeciale();
		offerta.setPizza(res.get());
		model.addAttribute("offerta", offerta);
		return "offerte/create";
	}

	@PostMapping("/store") // gestirà le richieste di tipo POST di tipo /books/create
	public String store(@Valid @ModelAttribute("offerta") OffertaSpeciale formOfferta, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "offerte/create";

		offertaRepository.save(formOfferta);
		return "redirect:/pizze/" + formOfferta.getPizza().getId();

	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {
		OffertaSpeciale offerta = offertaRepository.getReferenceById(id);
		model.addAttribute("offerta", offerta);
		return "offerte/edit";
	}

	@PostMapping("/update/{id}")
	public String update(@Valid @ModelAttribute OffertaSpeciale formOfferta, BindingResult result) {
		if (result.hasErrors())
			return "offerte/edit";

		offertaRepository.save(formOfferta);
		return "redirect:/pizze/" + formOfferta.getPizza().getId();
	}

	@PostMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id, HttpServletRequest request) {
		offertaRepository.deleteById(id);
		String referer = request.getHeader("Referer"); // ritorno l'URI di provienienza, + dinamic
		return "redirect:" + referer;
	}
}
