package br.com.guiareze.prospectsresttemplate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.guiareze.prospectsresttemplate.model.Prospects;
import br.com.guiareze.prospectsresttemplate.service.ProspectsService;

@RestController
@RequestMapping("/prospects")
public class ProspectsController {
	
	@Autowired
	public ProspectsService prospectsService;
	
	@GetMapping
	public List<Prospects> findAll(){
		return prospectsService.findAll();
	}
	
	@PostMapping
	public Prospects create(@RequestBody Prospects prospect) {
		return prospectsService.create(prospect);
	}
	
	@PutMapping("/{id}")
	public Prospects update(@PathVariable(name = "id") String id, @RequestBody Prospects prospects) {
		return prospectsService.update(id, prospects);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable(name = "id") String id) {
		prospectsService.delete(id);
	}

}
