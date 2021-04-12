package es.faculdade.sec.repo;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface ProfessorRepo extends R2dbcRepository<es.faculdade.sec.model.Professor, Integer> {
	
	public Mono<es.faculdade.sec.model.Professor> updateProfessor(Integer idProfessor);

}
