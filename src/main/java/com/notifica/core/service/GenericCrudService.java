package com.notifica.core.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface GenericCrudService<T> {
	
    public Page<T> buscarTodos(Specification<T> specification, Pageable pageable) ;
	public List<T> buscarTodos() ;
	public T salvar(T t) ;
	public T buscar(Integer id);
	public void deletar(T t) ;
}
