package com.notifica.core.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notifica.core.business.AlunoBusiness;
import com.notifica.core.business.GenericCrudBusiness;
import com.notifica.core.entities.Aluno;

@RestController
@RequestMapping("aluno")
public class AlunoController extends GenericCrudController<Aluno> {

	private static final Logger LOGGER = LoggerFactory.getLogger(AlunoController.class);
	public static final String JSON = "application/json";

	@Autowired
	private AlunoBusiness alunoBusiness;

	@Override
	public GenericCrudBusiness<Aluno> getBusinessClass() {
		return alunoBusiness;
	}

}
