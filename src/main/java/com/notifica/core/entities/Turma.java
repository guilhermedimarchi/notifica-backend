package com.notifica.core.entities;


import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="cf_turma")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Turma {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_turma")
	private Integer id;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="data_criacao")
	private Date dataCriacao;
	
	@OneToMany(mappedBy="turma")
    private Set<Aluno> lstAlunos;

}
