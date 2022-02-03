package br.com.os.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.os.domain.OS;
import br.com.os.domain.enums.Status;
import br.com.os.dto.OSDto;
import br.com.os.exception.NegocioException;
import br.com.os.repository.OSRepository;

@Service
public class OSService {

	@Autowired
	private OSRepository repository;

	public List<OSDto> searchAll() {
		List<OSDto> dto = repository.findAll().stream().map(item -> convertToDto(item)).collect(Collectors.toList());
		return dto;

	}

	@Transactional
	public OS searchById(Long id) {
		OS dto = null;

		dto = repository.findById(id).orElseThrow(() -> new NegocioException("ORDEM DE SERVICO NAO FOI ENCONTRADA: "));
		return dto;

	}

	@Transactional
	public OSDto save(OSDto osDto) {
		OSDto osRetorno = null;

		OS entity = convertToEntity(osDto);
		OS osSave = repository.save(entity);
		osRetorno = convertToDto(osSave);
		return osRetorno;
	}

	@Transactional
	public OSDto update(OSDto dto, Long id) {

		OS entity = repository.findById(id).get();
		dto.setStatus(Status.ENCERRADO);
		dto.setDataFechamento(LocalDateTime.now());
		OSDto osOld = convertToDto(entity);
		BeanUtils.copyProperties(dto, osOld, "dto");
		OS osEntity = convertToEntity(dto);
		osEntity.setId(entity.getId());

		OS update = repository.save(osEntity);
		return convertToDto(update);
	}

	private OSDto convertToDto(OS osEntity) {
		OSDto osDto = new OSDto();
		osDto.setCliente(osEntity.getCliente());
		osDto.setDataAbertura(LocalDateTime.now());
		osDto.setDataFechamento(osEntity.getDataFechamento());
		osDto.setId(osEntity.getId());
		osDto.setObservacoes(osEntity.getObservacoes());
		osDto.setPrioridade(osEntity.getPrioridade().getCod());
		osDto.setStatus(osEntity.getStatus());
		osDto.setTecnico(osEntity.getTecnico());
		return osDto;
	}

	private OS convertToEntity(OSDto dto) {
		OS osEntity = new OS();
		osEntity.setCliente(dto.getCliente());
		osEntity.setDataAbertura(dto.getDataAbertura());
		osEntity.setDataFechamento(dto.getDataFechamento());
		osEntity.setId(dto.getId());
		osEntity.setObservacoes(dto.getObservacoes());
		osEntity.setTecnico(dto.getTecnico());
		return osEntity;
	}

}
