package es.faculdade.sec.matricula.repo;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import es.faculdade.sec.matricula.model.Professor;
import reactor.core.publisher.Mono;

public interface ProfessorRepo extends R2dbcRepository<Professor, Integer> {
	
	public Mono<Professor> updateProfessor(Integer idProfessor);

}
