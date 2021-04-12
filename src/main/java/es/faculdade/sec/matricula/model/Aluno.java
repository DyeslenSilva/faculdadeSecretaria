package es.faculdade.sec.matricula.model;

import lombok.Data;

@Data
public class Aluno {

	private Integer matricula;
	private String nomeDoAluno;
	private String endereco;
	private String celular;
	private String email;
}
