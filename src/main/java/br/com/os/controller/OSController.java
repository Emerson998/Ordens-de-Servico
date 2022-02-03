package br.com.os.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.os.domain.OS;
import br.com.os.dto.OSDto;
import br.com.os.service.OSService;

@RestController
@RequestMapping(value = "/os")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class OSController {

	@Autowired
	private OSService osService;

	@GetMapping
	public ResponseEntity<List<OSDto>> searchAll() {
		ResponseEntity<List<OSDto>> resposta = null;
		resposta = ResponseEntity.ok(osService.searchAll());
		return resposta;
	}

	@GetMapping({ "/{id}", "/" })
	public ResponseEntity<OS> searchByCode(@PathVariable(required = false) Long id) {
		ResponseEntity<OS> resposta = null;

		resposta = ResponseEntity.ok(osService.searchById(id));
		return resposta;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<OSDto> save(@Valid @RequestBody OSDto osDto) {
		OSDto resposta = null;

		resposta = osService.save(osDto);
		return ResponseEntity.ok(resposta);

	}

	@PutMapping({ "/{id}", "/" })
	public OSDto update(@PathVariable Long id, @RequestBody OSDto os) {
		return osService.update(os, id);
	}

}