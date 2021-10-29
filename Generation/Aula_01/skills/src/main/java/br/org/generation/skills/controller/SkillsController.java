package br.org.generation.skills.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class SkillsController {
	
	@GetMapping
	public String Skills() {
		return "Habilidades e Mentalidades:"
				+ "<p> - Peresistência;"
				+ "<p> - Atenção aos Detalhes;"
				+ "<p> - Mentalidade de Crescimento;";
	}
}
