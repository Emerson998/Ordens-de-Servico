package br.com.os.enums;

public enum HttpEnum {

	HEADER_MESSAGE("mensagem");

	private String descricao;

	HttpEnum(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return this.descricao;
	}
}
