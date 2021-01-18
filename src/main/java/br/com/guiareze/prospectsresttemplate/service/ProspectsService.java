package br.com.guiareze.prospectsresttemplate.service;

import java.util.List;

import br.com.guiareze.prospectsresttemplate.model.Prospects;

public interface ProspectsService {
	
	List<Prospects> findAll();
	Prospects create(Prospects prospect);
	Prospects update(String id, Prospects prospect);
	void delete(String id);

}
