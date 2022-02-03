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
	public Tecnico searchByCpf(String cpf) {
		Tecnico dto = null;

		dto = tecnicoRepository.findByCpf(cpf)
				.orElseThrow(() -> new NegocioException("TECNICO NAO FOI ENCONTRADO: " + cpf));
		return dto;

	}

	@Transactional
	public TecnicoDto save(TecnicoDto tecnicoDto) {
		TecnicoDto tecnicoRetorno = null;
		boolean cpfEmUso = tecnicoRepository.findByCpf(tecnicoDto.getCpf()).stream()
				.anyMatch(item -> !item.equals(tecnicoDto));
		if (cpfEmUso) {
			throw new DataViolationException(
					"CPF JA CADASTRADO VERIFIQUE OS CAMPOS E TENTE NOVAMENTE :" + tecnicoDto.getCpf());
		}

		try {
			validatingCpf(tecnicoDto.getCpf());
			Tecnico entity = convertToEntity(tecnicoDto);
			Tecnico tecnicoSave = tecnicoRepository.save(entity);
			tecnicoRetorno = convertToDto(tecnicoSave);
			return tecnicoRetorno;
		} catch (DataViolationException ne) {
			throw new DataViolationException("CPF INVALIDO");
		}

	}

	@Transactional
	public TecnicoDto update(TecnicoDto dto, String cpf) {

		Tecnico entity = tecnicoRepository.findByCpf(cpf).get();
		TecnicoDto tecnicoOld = convertToDto(entity);
		BeanUtils.copyProperties(dto, tecnicoOld, "dto");
		Tecnico tecEntity = convertToEntity(dto);
		tecEntity.setId(entity.getId());
		Tecnico update = tecnicoRepository.save(tecEntity);
		return convertToDto(update);
	}

	@Transactional
	public ResponseEntity<TecnicoDto> deleteByCpf(String cpf) {
		tecnicoRepository.deleteByCpf(cpf);
		return ResponseEntity.noContent().build();
	}

	public boolean validatingCpf(String cpf) {
		CPFValidator cpfValid = new CPFValidator();
		cpfValid.assertValid(cpf);
		return false;
	}

	private TecnicoDto convertToDto(Tecnico tec) {
		TecnicoDto dto = new TecnicoDto();
		dto.setCpf(tec.getCpf());
		dto.setNome(tec.getNome());
		dto.setTelefone(tec.getTelefone());
		return dto;
	}

	private Tecnico convertToEntity(TecnicoDto dto) {
		Tecnico tec = new Tecnico();
		tec.setCpf(dto.getCpf());
		tec.setNome(dto.getNome());
		tec.setTelefone(dto.getTelefone());
		return tec;

	}

}
