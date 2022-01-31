package br.com.os.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.os.domain.Cliente;
import br.com.os.domain.OS;
import br.com.os.domain.Tecnico;
import br.com.os.domain.enums.Prioridade;
import br.com.os.domain.enums.Status;
import br.com.os.repository.ClienteRepository;
import br.com.os.repository.OSRepository;
import br.com.os.repository.TecnicoRepository;

@Service
public class DBService {
	
	@Autowired
	private TecnicoRepository tecnicoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private OSRepository osRepository;

	public void instanciaDB() {

		Tecnico t1 = new Tecnico(null, "Emerson Andrade", "929.745.520-48", "11 9322-58211");
		Cliente c1 = new Cliente(null, "Ana Paula", "351.283.530-95", "11 9322-58219");
		OS os1 = new OS(null, Prioridade.ALTA, "Teste create OS", Status.ANDAMENTO, t1, c1);

		t1.getList().add(os1);
		c1.getList().add(os1);

		tecnicoRepository.saveAll(Arrays.asList(t1));
		clienteRepository.saveAll(Arrays.asList(c1));
		osRepository.saveAll(Arrays.asList(os1));

	}
}
