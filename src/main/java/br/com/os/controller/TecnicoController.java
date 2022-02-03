package br.com.os.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.os.dto.TecnicoDto;
import br.com.os.service.TecnicoService;

@RestController
@RequestMapping(value = "/tecnicos")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class TecnicoController {

	@Autowired
	private TecnicoService tecnicoService;

	@GetMapping
	public ResponseEntity<List<TecnicoDto>> searchAll() {
		ResponseEntity<List<TecnicoDto>> resposta = null;

		resposta = ResponseEntity.ok(tecnicoService.searchAll());
		return resposta;
	}

	@GetMapping({ "/{id}", "/" })
	public ResponseEntity<TecnicoDto> searchByCode(@PathVariable(required = false) Long id) {
		ResponseEntity<TecnicoDto> resposta = null;

		resposta = ResponseEntity.ok(tecnicoService.searchById(id));
		return resposta;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<TecnicoDto> save(@RequestBody TecnicoDto dto) {
		ResponseEntity<TecnicoDto> resposta = null;

		return resposta = ResponseEntity.ok(tecnicoService.save(dto));

	}

}