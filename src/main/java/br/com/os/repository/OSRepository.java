package br.com.os.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.os.domain.OS;
import br.com.os.dto.OSDto;

@Repository
public interface OSRepository extends JpaRepository<OS, Long> {

	Optional<OS> save(OSDto dto);

}
