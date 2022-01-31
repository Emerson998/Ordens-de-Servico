package br.com.os.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.os.domain.OS;

@Repository
public interface OSRepository extends JpaRepository<OS, Long> {

}
