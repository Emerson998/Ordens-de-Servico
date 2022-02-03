package br.com.os.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.os.domain.Tecnico;

@Repository
public interface TecnicoRepository extends JpaRepository<Tecnico, Long> {

	Optional<Tecnico> findByCpf(String cpf);

	void deleteByCpf(String cpf);

}
