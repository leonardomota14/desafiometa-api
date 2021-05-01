package com.meta.desafiometa.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meta.desafiometa.converter.PessoaConverter;
import com.meta.desafiometa.dto.PessoaDTO;
import com.meta.desafiometa.entity.Pessoa;
import com.meta.desafiometa.service.PessoaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author Jessé Sampaio
 *
 */
@RestController
@RequestMapping("/pessoas")
@Api(tags = { "Manutenção de pessoas" })
@Transactional(rollbackFor = Exception.class)
public class PessoaController {
	
	private final PessoaService pessoaService;
	private final PessoaConverter pessoaConverter;

	public PessoaController(final PessoaService pessoaService, final PessoaConverter pessoaConverter) {
		this.pessoaService = pessoaService;
		this.pessoaConverter = pessoaConverter;
	}

	protected PessoaDTO retornaInstanciaDTO() {
		return new PessoaDTO();
	}

	protected Pessoa retornaInstanciaEntidade() {
		return new Pessoa();
	}
	
	@GetMapping("/buscar/{id}")
	@ApiOperation(value = "Retorna um registro específico.")
	@ApiResponses(value = { //
	@ApiResponse(code = 200, message = "Indica que encontrou o registro no banco de dados"),
	@ApiResponse(code = 204, message = "Indica que não encontrou o registro no banco de dados"), })
	public ResponseEntity<PessoaDTO> buscar(//
			@ApiParam(value = "Identificador do registro a ser exibido") //
			@PathVariable final Integer id) {
		return ResponseEntity.ok(
				pessoaConverter.converterEntidadeParaDTO(pessoaService.retornarRegistro(id), retornaInstanciaDTO()).orElseThrow(null));
	}
	
	@GetMapping("listar")
	@ApiOperation(value = "Retorna uma lista de registros paginados.")
	@ApiResponses(value = { //
	@ApiResponse(code = 200, message = "Indica que encontrou registro(s) no banco de dados"),
	@ApiResponse(code = 204, message = "Indica que não encontrou nenhum registro no banco de dados"), })
	public ResponseEntity<Page<PessoaDTO>> listar(
			@ApiIgnore @PageableDefault(page = 0, size = 10) final Pageable page) {

		final Page<PessoaDTO> listaRegistros = pessoaService.listarRegistros(page)
				.map(item -> pessoaConverter.converterEntidadeParaDTO(item, retornaInstanciaDTO()).orElse(null));

		if (!listaRegistros.isEmpty())
			return ResponseEntity.ok(listaRegistros);

		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/adicionar")
	@ApiOperation(value = "Inclui um registro.")
	@ApiResponses(value = { //
	@ApiResponse(code = 200, message = "Indica que o objeto persistido"),
	@ApiResponse(code = 204, message = "Indica que não foi possível fazer a desserialização"),
	@ApiResponse(code = 500, message = "Indica que ocorreu um erro interno na aplicação"), })
	public ResponseEntity<PessoaDTO> adicionar(//
			@ApiParam(value = "Registro a ser persistido no banco de dados") //
			@RequestBody(required = true) final @Valid PessoaDTO pessoaDTO) {

		final Optional<Pessoa> pessoa = pessoaConverter.converterDTOParaEntidade(pessoaDTO, retornaInstanciaEntidade());

		if (pessoa.isPresent()) {
			final Optional<PessoaDTO> pessoaDtoRet = pessoaConverter.converterEntidadeParaDTO(pessoaService.inserirRegistro(pessoa.get()),
					retornaInstanciaDTO());

			if (pessoaDtoRet.isPresent())
				return new ResponseEntity<>(pessoaDtoRet.get(), HttpStatus.CREATED);
		}
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(path = "/atualizar/{id}")
	@ApiOperation(value = "Atualiza um registro no banco de dados.")
	@ApiResponses(value = { //
	@ApiResponse(code = 200, message = "Indica que o objeto foi atualizado"),
	@ApiResponse(code = 204, message = "Indica que não foi possível fazer a desserialização"),
	@ApiResponse(code = 500, message = "Indica que ocorreu um erro interno na aplicação"), })
	public ResponseEntity<PessoaDTO> atualizar(//
		@ApiParam(value = "Identificador do registro a ser atualizado") //
		@PathVariable(name = "id", required = true) final @Valid Integer id, //
		@ApiParam(value = "Registro a ser atualizado no banco de dados") //
		@RequestBody(required = true) @Valid final PessoaDTO pessoaDTO) {

		final Optional<Pessoa> pessoa = pessoaConverter.converterDTOParaEntidade(pessoaDTO, pessoaService.retornarRegistro(id));

		if (pessoa.isPresent())
			return ResponseEntity
					.ok(pessoaConverter.converterEntidadeParaDTO(pessoaService.atualizarRegistro(pessoa.get()), retornaInstanciaDTO())
							.orElseThrow(null));

		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/excluir/{id}")
	@ApiOperation(value = "Remove um registro do banco de dados.")
	@ApiResponses(value = { //
	@ApiResponse(code = 200, message = "Indica que o registro foi removido do banco de dados"),
	@ApiResponse(code = 400, message = "Indica que não encontrou o registro no banco de dados"), })
	public void excluir(//
			@ApiParam(value = "Identificador do registro a ser exibido") //
			@PathVariable final Integer id) {
		pessoaService.excluirRegistroPorId(pessoaService.retornarRegistro(id).getId());
	}

}
