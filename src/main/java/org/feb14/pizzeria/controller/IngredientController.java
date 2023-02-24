package org.feb14.pizzeria.controller;

import org.feb14.pizzeria.model.Ingredient;
import org.feb14.pizzeria.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {
	
	private @Autowired IngredientRepository ingredientRepository;
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, Model model, HttpServletRequest request) {
		String referer = request.getHeader("Referer"); // qui mistero ritorna URL completo
		Ingredient ingredient = ingredientRepository.getReferenceById(id); 
		model.addAttribute("ingredient", ingredient);
		model.addAttribute("referer", referer.substring(referer.indexOf('/'))); //infatti rimuovo il dominio e lo passo al form che poi lo passa al update
		return "ingredients/edit";
	}
	
	@PostMapping("/update/{id}")
	public String update(@Valid @ModelAttribute Ingredient formIngredient, @PathVariable("id") Integer id, @RequestParam("referer") String referer) {
		ingredientRepository.save(formIngredient);
		return "redirect:" + referer;
	}
	
	@PostMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id, HttpServletRequest request) {
		ingredientRepository.deleteById(id);
		String referer = request.getHeader("Referer"); // ritorno l'URI di provienienza, + dinamic
		return "redirect:" + referer;//WARN, se sei su un detail futuro di ingrediente.. e lo cancelli non esiste più la pagina, da gestire
	}
	
	
}
