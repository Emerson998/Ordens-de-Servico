package br.com.os.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.os.domain.Cliente;
import br.com.os.dto.ClienteDto;
import br.com.os.service.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping
	public ResponseEntity<List<ClienteDto>> searchAll() {
		ResponseEntity<List<ClienteDto>> resposta = null;
		resposta = ResponseEntity.ok(clienteService.searchAll());
		return resposta;
	}

	@GetMapping({ "/{cpf}", "/" })
	public ResponseEntity<Cliente> searchByCode(@PathVariable(required = false) String cpf) {
		ResponseEntity<Cliente> resposta = null;

		resposta = ResponseEntity.ok(clienteService.searchByCpf(cpf));
		return resposta;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ClienteDto> save(@Valid @RequestBody ClienteDto clienteDto) {
		ClienteDto resposta = null;

		resposta = clienteService.save(clienteDto);
		return ResponseEntity.ok(resposta);

	}

	@PutMapping({ "/{cpf}", "/" })
	public ClienteDto update(@PathVariable String cpf, @RequestBody ClienteDto cliente) {
		return clienteService.update(cliente, cpf);
	}

	@DeleteMapping({ "/{cpf}", "/" })
	public ResponseEntity<ClienteDto> deleteByCpf(@PathVariable String cpf) {
		return clienteService.deleteByCpf(cpf);
	}

}