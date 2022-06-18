package model;

public class Contato {
	
	// ATRIBUTOS
	private String idCon;
	private String nome;
	private String telefone;
	private String email;
	
	// CONSTRUTORES
	public Contato() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Contato(String idCon, String nome, String telefone, String email) {
		super();
		this.idCon = idCon;
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
	}
	
	// SETTERS E GETTERS
	public String getIdCon() {
		return idCon;
	}
	public void setIdCon(String idCon) {
		this.idCon = idCon;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
