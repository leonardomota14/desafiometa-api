package com.meta.desafiometa.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.meta.desafiometa.entity.Pessoa;
import com.meta.desafiometa.exceptions.DeleteError;
import com.meta.desafiometa.exceptions.DuplicateEntry;
import com.meta.desafiometa.exceptions.EntityNotFound;
import com.meta.desafiometa.exceptions.InsertError;
import com.meta.desafiometa.exceptions.InternalServerError;
import com.meta.desafiometa.exceptions.UpdateError;
import com.meta.desafiometa.repository.PessoaRepository;

/**
 * @author Jessé Sampaio
 *
 */
@Service
public class PessoaService {

	private final PessoaRepository pessoaRepository;

	public Page<Pessoa> listarRegistros(final Pageable pageRequest) {
		return pessoaRepository.findAll(pageRequest);
	}

	public Optional<Pessoa> listarRegistro(final Integer id) {
		try {
			return pessoaRepository.findById(id);
		} catch (final Exception e) {
			throw InternalServerError.of(e.getMessage());
		}
	}

	public Pessoa retornarRegistro(final Integer id) {
		if (id == null)
			throw registroNaoEncontrado(id);

		return listarRegistro(id).orElseThrow(() -> registroNaoEncontrado(id));
	}

	private EntityNotFound registroNaoEncontrado(final Integer id) {
		return EntityNotFound.of("Registro com código {0} não encontrado.", id);
	}

	public Pessoa inserirRegistro (final Pessoa pessoa) {
		try {
			return pessoaRepository.save(antesDeSavar(pessoa));
		} catch (final Exception e) {
			if (e instanceof DuplicateEntry)
				throw e;
			else
				throw InsertError.of(e.getMessage());
		}
	}

	public Pessoa atualizarRegistro(final Pessoa pessoa) {
		try {
			return pessoaRepository.save(antesDeSavar(pessoa));
		} catch (final Exception e) {
			throw UpdateError.of(e.getMessage());
		}
	}

	public boolean excluirRegistroPorId(final Integer id) {
		try {
			pessoaRepository.deleteById(id);
			return true;
		} catch (final RuntimeException e) {
			throw DeleteError.of(e.getMessage());
		}
	}


	public PessoaService(final PessoaRepository repository) {
		pessoaRepository = repository;
	}

	
	protected Pessoa antesDeSavar(final Pessoa pessoa) {
		if (pessoaRepository.quantasPessoasComCPF(pessoa.getId(), pessoa.getCpf()) > 0)
			throw DuplicateEntry.of("Já existe um cliente com o CPF informado");

		return pessoa;
	}	

}
