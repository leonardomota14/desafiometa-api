package com.meta.desafiometa.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Jessé Sampaio
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PessoaDTO {
	
	@Positive
	@ApiModelProperty(value = "Identificador único do registro (somente leitura)", required = false, example = "1")
	private Integer id;
	
	@NotBlank @Size(max = 80)
	@ApiModelProperty(value = "Nome ", required = true, example = "João Silva", allowableValues = "range[1, 80]")
	private String nome;
	
	@NotNull
	@ApiModelProperty(value = "Sexo", required = true, example = "MASCULINO")
	private String sexo;
	
	@Email
	@ApiModelProperty(value = "E-mail", example = "email@dominio.com.br", allowableValues = "range[1, 100]")
	private @Size(max = 100)  String email;

	@NotNull 
	@PastOrPresent 
	@ApiModelProperty(value = "Data de nascimento", required = true, example = "2020-01-01")
	private LocalDate dataNascimento;
	
	@Size(max = 40)
	@ApiModelProperty(value = "Naturalidade", example = "Fortaleza", allowableValues = "range[0, 40]")
	private String naturalidade;
	
	@Size(max = 40)
	@ApiModelProperty(value = "Nacionalidade", example = "Brasileiro", allowableValues = "range[0, 40]")
	private String nacionalidade;
	
	@CPF 
	@NotBlank @Size(max = 11) 
	@ApiModelProperty(value = "CPF", example = "00006400000", allowableValues = "range[1, 11]")
	private String cpf;
	
	@ApiModelProperty(value = "Data de criação do registro (somente leitura)", required = false, example = "2020-09-23T13:04:00.726998")
	private LocalDateTime dataCriacao;

	@ApiModelProperty(value = "Data de alteração do registro (somente leitura)", required = false, example = "2020-09-23T13:04:00.726998")
	private LocalDateTime dataAlteracao;
	
	@NotBlank 
	@Size(max = 200)
	@ApiModelProperty(value = "Endereço", example = "Rua das graças, 36, Santa Felicidade, Curitiba, PR", allowableValues = "range[1, 200]")
	private String endereco;

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

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
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

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public LocalDateTime getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(LocalDateTime dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	
	
}
