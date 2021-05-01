package com.meta.desafiometa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.meta.desafiometa.entity.Pessoa;

/**
 * @author Jessé Sampaio
 *
 */
@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

	@Query("select count(tb) from Pessoa tb where tb.id <> coalesce(:id, 0) and tb.cpf = :cpf")
	Long quantasPessoasComCPF(@Param("id") Integer id, @Param("cpf") String cpf);
}