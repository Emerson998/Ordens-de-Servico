package br.com.os.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import br.com.os.domain.Cliente;

public class ClienteDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "o campo nome e obrigatorio")
	private String nome;
	@NotBlank(message = "o campo cpf e obrigatorio")
	private String cpf;
	@NotBlank(message = "o campo telefone e obrigatorio")
	private String telefone;

	public ClienteDto() {
		super();
	}

	public ClienteDto(Cliente obj) {
		super();
		this.nome = obj.getNome();
		this.cpf = obj.getCpf();
		this.telefone = obj.getTelefone();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}
