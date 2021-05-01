package com.meta.desafiometa.converter;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.meta.desafiometa.dto.PessoaDTO;
import com.meta.desafiometa.entity.Pessoa;

/**
 * @author Jessé Sampaio
 *
 */
@Component
public class PessoaConverter extends PessoaDTO  {

	protected Optional<Pessoa> mapearDTOParaEntidade(final PessoaDTO pessoaDto, final Pessoa pessoa) {
		pessoa.setNome(pessoaDto.getNome());
		pessoa.setSexo(pessoaDto.getSexo());
		pessoa.setEmail(pessoaDto.getEmail());
		pessoa.setDataNascimento(Date.valueOf(pessoaDto.getDataNascimento()));
		pessoa.setNaturalidade(pessoaDto.getNaturalidade());
		pessoa.setNacionalidade(pessoaDto.getNacionalidade());
		pessoa.setCpf(pessoaDto.getCpf());
		pessoa.setEndereco(pessoaDto.getEndereco());
		pessoa.setDataCriacao(Timestamp.valueOf(LocalDateTime.now()));
		pessoa.setDataAlteracao(Timestamp.valueOf(LocalDateTime.now()));
		
		return Optional.of(pessoa);
	}

	protected Optional<PessoaDTO> mapearEntidadeParaDTO(final Pessoa pessoa, final PessoaDTO pessoaDto) {
		pessoaDto.setNome(pessoa.getNome());
		pessoaDto.setSexo(pessoa.getSexo());
		pessoaDto.setEmail(pessoa.getEmail());
		pessoaDto.setDataNascimento(pessoa.getDataNascimento().toLocalDate());
		pessoaDto.setNaturalidade(pessoa.getNaturalidade());
		pessoaDto.setNacionalidade(pessoa.getNacionalidade());
		pessoaDto.setCpf(pessoa.getCpf());
		pessoaDto.setEndereco(pessoa.getEndereco());
		
		return Optional.of(pessoaDto);
	}
	
	public final Optional<PessoaDTO> converterEntidadeParaDTO(final Pessoa pessoa, final PessoaDTO pessoaDto) {
		pessoaDto.setId(pessoa.getId());
		pessoaDto.setDataCriacao(LocalDateTime.now());
		pessoaDto.setDataAlteracao(pessoa.getDataAlteracao().toLocalDateTime());
		
		return mapearEntidadeParaDTO(pessoa, pessoaDto);
	}

	public final Optional<Pessoa> converterDTOParaEntidade(final PessoaDTO pessoaDto, final Pessoa pessoa) {
		return mapearDTOParaEntidade(pessoaDto, pessoa);
	}

}
