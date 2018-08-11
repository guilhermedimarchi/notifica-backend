package com.notifica.core.business;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;


@Component
public interface GenericCrudBusiness<T> {	
	public abstract Page<T> listarTodos(Specification<T> specification, Pageable pageable) ;
	public abstract List<T> listarTodos() ;
	public abstract T salvar(T t) ;
	public abstract T buscar(Integer id) ;
	public abstract void deletar(T t);
}
