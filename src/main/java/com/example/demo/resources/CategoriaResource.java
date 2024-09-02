package com.example.demo.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/categoria")
public class CategoriaResource {
	
	@GetMapping
	public String listar() {
		return "Rest está funcionando";
	}
		
		
}