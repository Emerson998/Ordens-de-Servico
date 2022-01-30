package br.com.os.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Cliente extends Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy = "cliente")
	private List<OS> lista = new ArrayList<>();

	public Cliente() {
		super();
	}

	public Cliente(Long id, String nome, String cpf, String telefone) {
		super(id, nome, cpf, telefone);
	}

	public List<OS> getLista() {
		return lista;
	}

	public void setLista(List<OS> lista) {
		this.lista = lista;
	}

}
