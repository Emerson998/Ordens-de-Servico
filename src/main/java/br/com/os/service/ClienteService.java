package br.com.os.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.os.controller.exceptions.DataViolationException;
import br.com.os.domain.Cliente;
import br.com.os.dto.ClienteDto;
import br.com.os.exception.NegocioException;
import br.com.os.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public List<ClienteDto> searchAll() {
		List<ClienteDto> dto = clienteRepository.findAll().stream().map(item -> convertToDto(item))
				.collect(Collectors.toList());
		return dto;

	}

	@Transactional
	public Cliente searchByCpf(String cpf) {
		Cliente entity = null;

		entity = clienteRepository.findByCpf(cpf)
				.orElseThrow(() -> new NegocioException("CLIENTE NAO FOI ENCONTRADO: " + cpf));
		return entity;

	}

	@Transactional
	public ClienteDto save(ClienteDto clienteDto) {
		ClienteDto clienteRetorno = null;
		boolean cpfEmUso = clienteRepository.findByCpf(clienteDto.getCpf()).stream()
				.anyMatch(item -> !item.equals(clienteDto));
		if (cpfEmUso) {
			throw new DataViolationException(
					"CPF JA CADASTRADO VERIFIQUE OS CAMPOS E TENTE NOVAMENTE :" + clienteDto.getCpf());
		}

		try {
			validatingCpf(clienteDto.getCpf());
			Cliente entity = convertToEntity(clienteDto);
			Cliente clienteSave = clienteRepository.save(entity);
			clienteRetorno = convertToDto(clienteSave);
			return clienteRetorno;
		} catch (DataViolationException ne) {
			throw new DataViolationException("CPF INVALIDO");
		}

	}

	@Transactional
	public ClienteDto update(ClienteDto dto, String cpf) {

		Cliente entity = clienteRepository.findByCpf(cpf).get();
		ClienteDto dtoOld = convertToDto(entity);
		BeanUtils.copyProperties(dto, dtoOld, "dto");
		Cliente cfoEntity = convertToEntity(dto);
		cfoEntity.setId(entity.getId());
		Cliente update = clienteRepository.save(cfoEntity);
		return convertToDto(update);
	}

	@Transactional
	public ResponseEntity<ClienteDto> deleteByCpf(String cpf) {
		clienteRepository.deleteByCpf(cpf);
		return ResponseEntity.noContent().build();
	}

	public boolean validatingCpf(String cpf) {
		CPFValidator cpfValid = new CPFValidator();
		cpfValid.assertValid(cpf);
		return false;
	}

	private ClienteDto convertToDto(Cliente tec) {
		ClienteDto dto = new ClienteDto();
		dto.setCpf(tec.getCpf());
		dto.setNome(tec.getNome());
		dto.setTelefone(tec.getTelefone());
		return dto;
	}

	private Cliente convertToEntity(ClienteDto dto) {
		Cliente tec = new Cliente();
		tec.setCpf(dto.getCpf());
		tec.setNome(dto.getNome());
		tec.setTelefone(dto.getTelefone());
		return tec;

	}

}
