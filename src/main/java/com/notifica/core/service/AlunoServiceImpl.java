package com.notifica.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.notifica.core.entities.Aluno;
import com.notifica.core.repository.AlunoRepository;

@Service
public class AlunoServiceImpl implements AlunoService {

	@Autowired
	private AlunoRepository alunoRepository;

	@Override
	public Page<Aluno> buscarTodos(Specification<Aluno> specification, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Aluno> buscarTodos() {
		return alunoRepository.findAll();
	}

	@Override
	public Aluno salvar(Aluno t) {
		return alunoRepository.save(t);
	}

	@Override
	public Aluno buscar(Integer id) {
		return alunoRepository.findOne(id);
	}

	@Override
	public void deletar(Aluno t) {
		alunoRepository.delete(t);
	}

}
