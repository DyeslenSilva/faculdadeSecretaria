package es.faculdade.sec.vestibular.model;

import es.faculdade.vestibular.igts.model.Candidato;
import es.faculdade.vestibular.igts.model.Inscricao;
import es.faculdade.vestibular.igts.model.Prova;
import lombok.Data;

@Data
public class InscricaoVestibularMod {
	
	private Candidato candidato;
	private Inscricao inscricao;
	private Prova prova;
}
