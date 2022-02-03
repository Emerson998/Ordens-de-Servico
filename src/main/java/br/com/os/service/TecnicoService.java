package br.com.os.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.os.domain.Tecnico;
import br.com.os.dto.TecnicoDto;
import br.com.os.exception.NegocioException;
import br.com.os.repository.TecnicoRepository;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository tecnicoRepository;

	public List<TecnicoDto> searchAll() {
		List<TecnicoDto> dto = tecnicoRepository.findAll().stream().map(item -> convertToDto(item))
				.collect(Collectors.toList());
		return dto;

	}

	@Transactional
	public TecnicoDto searchById(Long id) {
		TecnicoDto dto = null;

		Tecnico entity = tecnicoRepository.findById(id)
				.orElseThrow(() -> new NegocioException("TECNICO NAO FOI ENCONTRADO: " + id));
		dto = convertToDto(entity);
		return dto;

	}

	@Transactional
	@ResponseStatus(HttpStatus.CREATED)
	public TecnicoDto save(TecnicoDto dto) {
		TecnicoDto dtoRetorno = null;

		Tecnico tecnico = convertToEntity(dto);
		Tecnico tecnicoSave = tecnicoRepository.save(tecnico);
		dtoRetorno = convertToDto(tecnicoSave);

		return null;

	}

	private TecnicoDto convertToDto(Tecnico tec) {
		TecnicoDto dto = new TecnicoDto();
		dto.setCpf(tec.getCpf());
		dto.setId(tec.getId());
		dto.setNome(tec.getNome());
		dto.setTelefone(tec.getTelefone());
		return dto;
	}

	private Tecnico convertToEntity(TecnicoDto dto) {
		Tecnico tec = new Tecnico();
		tec.setCpf(dto.getCpf());
		tec.setId(dto.getId());
		tec.setNome(dto.getNome());
		tec.setTelefone(dto.getTelefone());
		return tec;

	}

}
