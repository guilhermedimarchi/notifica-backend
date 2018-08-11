package com.notifica.core.entities;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="cf_responsavel")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Responsavel {

		@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name = "id_responsavel")
		private Integer id;
		
		@Column(name="nome")
		private String nome;
		
		@Column(name="cpf")
		private String cpf;
		
		@Column(name="rg")
		private String rg;
		
		@Column(name="idade")
		private Integer idade;
		
		@Column(name="telefone")
		private String telefone;
		
		@Column(name="telefone2")
		private String telefone2;
		
		@Column(name="email")
		private String email;
		
		@Column(name="endereco")
		private String endereco;
		
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
		
		@ManyToMany(mappedBy = "lstResponsaveis")
		private Set<Aluno> lstAlunos = new HashSet<>();
}
