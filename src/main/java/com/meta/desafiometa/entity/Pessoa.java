package com.meta.desafiometa.entity;

import java.sql.Date;
import java.sql.Timestamp;

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

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Jessé Sampaio
 *
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pessoa")
@EqualsAndHashCode(callSuper = true)
public class Pessoa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;
	
	@Column(name = "nome", nullable = false, length = 80)
	@Size(max = 80, message = "O nome não deve ultrapassar 80 caracteres")
	@NotBlank(message = "Nome deve ser informado")
	private String nome;
	
	@Column(name = "sexo", nullable = false, length = 15)
	@NotNull(message = "Sexo deve ser informado") 
	private String sexo;
	
	@Column(name = "email", length = 100)
	@Size(max = 100, message = "O e-mail não deve ultrapassar 100 caracteres")
	@Email(message = "E-mail inválido")
	private String email;
	
	@Column(name = "dataNascimento", nullable = false)
	@NotNull(message = "Data de nascimento deve ser informada")
	private Date dataNascimento;
	
	@Column(name = "naturalidade", length = 40)
	private @Size(max = 40) String naturalidade;
	
	@Column(name = "nacionalidade", length = 40)
	private @Size(max = 40) String nacionalidade;
	
	@CPF
	@Column(name = "cpf", nullable = false, length = 11)
	@NotBlank(message = "CPF deve ser informado")
	private String cpf;
	
	@Column(name = "endereco", length = 200)
	@Size(max = 200)
	private String endereco;
	
	@Column(name = "dataCriacao", nullable = false, updatable = false)
	@CreatedDate
	@Setter(AccessLevel.PROTECTED)
	private Timestamp dataCriacao;

	@Column(name = "dataAlteracao", nullable = false)
	@LastModifiedDate
	@Setter(AccessLevel.PROTECTED)
	private Timestamp dataAlteracao;
		
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
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getNaturalidade() {
		return naturalidade;
	}
	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
	}
	public String getNacionalidade() {
		return nacionalidade;
	}
	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public Timestamp getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Timestamp dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Timestamp getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Timestamp dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}
	
	
}
