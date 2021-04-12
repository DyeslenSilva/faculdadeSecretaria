package es.faculdade.sec.matricula.repo;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import es.faculdade.sec.matricula.model.Aluno;
import reactor.core.publisher.Mono;

public interface AlunoRepo extends R2dbcRepository<Aluno, Integer> {
	
	public Mono<Aluno> updateAluno(Integer idAluno);

}
