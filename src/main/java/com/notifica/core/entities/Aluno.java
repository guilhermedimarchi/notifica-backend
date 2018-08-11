package com.notifica.core.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="cf_aluno")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Aluno {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_aluno")
	private Integer id;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="cpf")
	private String cpf;
	
	@Column(name="rg")
	private String rg;
	
	@Column(name="idade")
	private Integer idade;
	
	@Column(name="ra")
	private String ra;
	
	@Column(name="fl_ativo")
	private Integer flAtivo;
	
	@Column(name="data_criacao")
    @Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date dataCriacao;
	
	@Column(name="data_atualizacao")
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date dataAtualizacao;
	
	@ManyToOne(cascade = {CascadeType.REFRESH},
			   fetch= FetchType.LAZY,
			   targetEntity=Turma.class)
	@JoinColumn(name="id_turma",referencedColumnName="id_turma")
	private Turma turma;
	
	 @ManyToMany(cascade = { CascadeType.ALL })
	    @JoinTable(
	        name = "cf_aluno_responsavel", 
	        joinColumns = { @JoinColumn(name = "id_aluno") }, 
	        inverseJoinColumns = { @JoinColumn(name = "id_responsavel") }
	    )
	    Set<Responsavel> lstResponsaveis= new HashSet<>();
	

}
