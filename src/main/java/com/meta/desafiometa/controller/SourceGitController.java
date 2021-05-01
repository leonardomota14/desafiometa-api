package com.meta.desafiometa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

/**
 * @author Jessé Sampaio
 *
 */
@RestController
@Api(tags = { "Repositório com o código fonte" })
public class SourceGitController {
	@GetMapping(path = "source")
	public String getHome() {
		return "https://github.com/jessesampaio.ti/desafio-meta-api";
	}
}
