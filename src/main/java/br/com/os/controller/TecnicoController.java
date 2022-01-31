package br.com.os.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.os.domain.Tecnico;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoController {

	@GetMapping("{id}")
	public ResponseEntity<Tecnico> searchById(@PathVariable Long id) {
		return searchById(id);
	}
}
