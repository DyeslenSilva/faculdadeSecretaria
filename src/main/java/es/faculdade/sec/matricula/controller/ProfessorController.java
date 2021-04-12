package es.faculdade.sec.matricula.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.faculdade.sec.matricula.model.Professor;
import es.faculdade.sec.matricula.repo.ProfessorRepo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("secProfessorAPI")
public class ProfessorController {

	private ProfessorRepo professorRepo;
	
	@PostMapping("/cadProfSec")
	public Mono<Professor> cadastroProfessor(@PathVariable Professor professor){
		return professorRepo.save(professor);
	}
	
	@GetMapping("/secProfessores")
	public Flux<Professor> getProfessores(){
		return professorRepo.findAll();
	}
	
	@GetMapping("/secProfessor/{idProfessor}")
	public Mono<Professor> getProfessor(@PathVariable Integer idProfessor){
		return professorRepo.findById(idProfessor);
	}
	
	@PutMapping("/secProfessor/{idProfessor}")
	public Mono<Professor> updateProfessor(@PathVariable Integer idProfessor){
		return professorRepo.updateProfessor(idProfessor);
	}
	
	@DeleteMapping("/secProfessor/{idProfessor}")
	public Mono<Void> deleteProfessor(@PathVariable Integer idProfessor){
		return professorRepo.deleteById(idProfessor);
	}
}
