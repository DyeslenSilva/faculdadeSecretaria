package es.faculdade.sec.matricula.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.faculdade.sec.matricula.model.Aluno;
import es.faculdade.sec.matricula.repo.AlunoRepo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/alunoSecAPI")
public class AlunoController {

	@Autowired
	private AlunoRepo alunoRepo;

	@PostMapping("/cadastroAluno")
	public Mono<Aluno> cadastroAluno(@PathVariable Aluno aluno){
		return alunoRepo.save(aluno);
	}
	
	@GetMapping("/secAluno")
	public Flux<Aluno> getAlunos(){
		return alunoRepo.findAll();
	}
	
	@GetMapping("/secAluno/{idAluno}")
	public Mono<Aluno> getAluno(@PathVariable Integer idAluno){
		return alunoRepo.findById(idAluno);
	}
	
	@PutMapping("/secAluno/{idAluno}")
	public Mono<Aluno> updateAluno(@PathVariable Integer idAluno){
		return alunoRepo.updateAluno(idAluno);
	}
	
	@DeleteMapping("/secAluno/{idAluno}")
	public Mono<Void> deleteAluno(@PathVariable Integer idAluno){
		return alunoRepo.deleteById(idAluno);
	}
	
}
