package br.com.os.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.os.domain.Tecnico;
import br.com.os.repository.TecnicoRepository;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository tecnicoRepository;

	public Optional<Tecnico> searchById(Long id) {
		Optional<Tecnico> tecnico = tecnicoRepository.findById(id);
		return tecnico;
	}
}
