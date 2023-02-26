package org.feb14.pizzeria.controller;

import org.feb14.pizzeria.model.Ingredient;
import org.feb14.pizzeria.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
	public String edit(@PathVariable("id") Integer id, Model model, HttpServletRequest request) { //// https://docs.oracle.com/javaee/7/api/javax/servlet/http/HttpServletRequest.html
		String referer = request.getHeader(HttpHeaders.REFERER); // VERSIONE PERFORMANTE non richiede di splittare il risultato in base al contesto del service
		// utilizzando la costante HttpHeaders.REFERER, si ha la garanzia che il nome dell'header corrisponde al nome utilizzato dalle specifiche HTTP/1.1
		// da console lo vedi in network protocol
		Ingredient ingredient = ingredientRepository.getReferenceById(id); 
		model.addAttribute("ingredient", ingredient);
		if(referer != null)
		model.addAttribute("referer", referer);
		else model.addAttribute("referer", "/");
		// else model.addAttribute("referer", "/ingredients/show/" + id); future show
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
		String referer = request.getHeader(HttpHeaders.REFERER); // ritorno l'URI di provienienza, + dinamic
		return "redirect:" + referer;//WARN, se sei su un detail futuro di ingrediente.. e lo cancelli non esiste più la pagina, da gestire
	}
	
	
}
