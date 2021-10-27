package br.org.generation.objetivos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping

public class ObjetivosController {
	@GetMapping
	public String objetivosSemana() {
		return "Meus objetivos de aprendizagem para essa semana:"
				+ "\n - Mais JAVA;"
				+ "\n - Spring;"
				+ "\n - Mais atenção aos detalhes;"
				+ "\n - Organização.";
	}
}