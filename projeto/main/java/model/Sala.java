package model;

public class Sala {
	private Integer id;
	private String nome;
	private Integer capacidade;
	private String localizacao;

	public Sala() {
	}

	public Sala(Integer id, String nome, Integer capacidade, String localizacao) {
		this.id = id;
		this.nome = nome;
		this.capacidade = capacidade;
		this.localizacao = localizacao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(Integer capacidade) {
		this.capacidade = capacidade;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}
}
