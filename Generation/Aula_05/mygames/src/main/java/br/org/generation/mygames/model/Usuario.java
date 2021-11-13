package br.org.generation.mygames.model;


import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "tb_usuarios")
public class Usuario {
	
	//Atributos
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Size(min = 5, max = 255, message = "O atributo Nome deve conter no mínimo 5 caracteres e no máximo 255!")
	@NotNull(message = "O atributo Nome é Obrigatório!")
	private String nome;
	
	@Column(name = "data_nascimento")
	@NotBlank(message = "O atributo data não deve ter espaçoes em branco!")
	@JsonFormat(pattern = "dd/mm/yyyy")
	private LocalDate dataNascimento;

	@NotNull(message = "O atributo Usuário é Obrigatório!")
	@Email(message = "O atributo Usuário deve ser um email válido!")
	@Size(max = 255, message = "O usuário deve conter no máximo 255!")
	private String usuario;
	
	@NotBlank(message = "O atributo Senha não deve conter espaços em branco!") //o atributo não deve ser nulo e/ou conter espaços em branco.
	@Size(min = 8, message = "A Senha deve ter no mínimo 8 caracteres")
	private String senha;
	
	// Getters and Setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	/*public List<Produto> getPostagem() {
		return produto;
	}

	public void setPostagem(List<Produto> postagem) {
		this.produto = produto;
	}*/

}