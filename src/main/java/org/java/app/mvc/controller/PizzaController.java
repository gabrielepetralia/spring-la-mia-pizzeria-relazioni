package org.java.app.mvc.controller;

import java.util.List;

import org.java.app.db.pojo.Pizza;
import org.java.app.db.serv.PizzaService;
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

import jakarta.validation.Valid;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

	@Autowired
	private PizzaService pizzaService;
	
	@GetMapping
	public String getIndex(Model model, @RequestParam(required = false) String name) {
		List<Pizza> pizzas = name == null 
					? pizzaService.findAll()
					: pizzaService.findByName(name);
		
		model.addAttribute("pizzas", pizzas);
		model.addAttribute("name", name);
		
		return "pizza-index";
	}
	
	@GetMapping("/{id}")
	public String getShow(@PathVariable int id, Model model) {
		
		Pizza pizza = pizzaService.findById(id);
		model.addAttribute("pizza", pizza);
		
		return "pizza-show";
	}
	
	@GetMapping("/create")
	public String getCreateForm(Model model) {
		
		model.addAttribute("pizza", new Pizza());
		
		return "pizza-create-edit";
	}
	
	@PostMapping("/create")
	public String storePizza(
			@Valid @ModelAttribute Pizza pizza,
			BindingResult bindingResult,
			Model model
			) {
		
		if (bindingResult.hasErrors()) {
			System.out.println("Error:");
			bindingResult.getAllErrors().forEach(System.out::println);
			
			return "pizza-create";
		} else 
			System.out.println("No error");
		
		try {
			pizzaService.save(pizza);
		} catch (Exception e) {
			
			// CONSTRAIN VALIDATION (unique)
			System.out.println("Errore constrain: " + e.getClass().getSimpleName());
			
			model.addAttribute("name_unique", "Nome già presente nel menù");
			
			return "pizza-create-edit";
		}
		
		return "redirect:/pizzas";
	}
	
	@GetMapping("/update/{id}")
	public String getEditForm(
			@PathVariable int id,
			Model model
		) {
		
		Pizza pizza = pizzaService.findById(id);
		model.addAttribute("pizza", pizza);
		
		return "pizza-create-edit";
	}
	
	@PostMapping("/update/{id}")
	public String updatePizza(
			@Valid @ModelAttribute Pizza pizza,
			BindingResult bindingResult,
			Model model
			) {
		
		if (bindingResult.hasErrors()) {
			System.out.println("Error:");
			bindingResult.getAllErrors().forEach(System.out::println);
			
			return "pizza-create";
		} else 
			System.out.println("No error");
		
		try {
			pizzaService.save(pizza);
		} catch (Exception e) {
			
			// CONSTRAIN VALIDATION (unique)
			System.out.println("Errore constrain: " + e.getClass().getSimpleName());
			
			model.addAttribute("name_unique", "Nome già presente nel menù");
			
			return "pizza-create-edit";
		}
		
		return "redirect:/pizzas";
	}
	
	@PostMapping("/delete/{id}")
	public String deleteBook(@PathVariable int id) {
		Pizza pizza = pizzaService.findById(id);
		pizzaService.delete(pizza);
		
		return "redirect:/pizzas";
	}
}