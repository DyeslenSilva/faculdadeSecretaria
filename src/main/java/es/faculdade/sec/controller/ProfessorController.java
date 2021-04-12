package es.faculdade.sec.controller;

import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("secProfessorAPI")
public class ProfessorController {

	private es.faculdade.sec.repo.ProfessorRepo professorRepo;
	
	@PostMapping("/cadProfSec")
	public Mono<es.faculdade.sec.model.Professor> cadastroProfessor(@PathVariable es.faculdade.sec.model.Professor professor){
		return professorRepo.save(professor);
	}
	
	@GetMapping("/secProfessores")
	public Flux<es.faculdade.sec.model.Professor> getProfessores(){
		return professorRepo.findAll();
	}
	
	@GetMapping("/secProfessor/{idProfessor}")
	public Mono<es.faculdade.sec.model.Professor> getProfessor(@PathVariable Integer idProfessor){
		return professorRepo.findById(idProfessor);
	}
	
	@PutMapping("/secProfessor/{idProfessor}")
	public Mono<es.faculdade.sec.model.Professor> updateProfessor(@PathVariable Integer idProfessor){
		return professorRepo.updateProfessor(idProfessor);
	}
	
	@DeleteMapping("/secProfessor/{idProfessor}")
	public Mono<Void> deleteProfessor(@PathVariable Integer idProfessor){
		return professorRepo.deleteById(idProfessor);
	}
}
