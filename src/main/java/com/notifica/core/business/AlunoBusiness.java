package com.notifica.core.business;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.notifica.core.entities.Aluno;
import com.notifica.core.service.AlunoService;

@Component
public class AlunoBusiness implements GenericCrudBusiness<Aluno> {

	private static final Logger LOGGER = LoggerFactory.getLogger(AlunoBusiness.class);

	@Autowired
	private AlunoService alunoService;

	@Override
	public Page<Aluno> listarTodos(Specification<Aluno> specification, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Aluno> listarTodos() {
		return alunoService.buscarTodos();
	}

	@Override
	public Aluno salvar(Aluno t) {
		return alunoService.salvar(t);
	}

	@Override
	public Aluno buscar(Integer id) {
		return alunoService.buscar(id);
	}

	@Override
	public void deletar(Aluno t) {
		alunoService.deletar(t);
	}

}
