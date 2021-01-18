package br.com.guiareze.prospectsresttemplate.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import br.com.guiareze.prospectsresttemplate.model.Prospects;
import br.com.guiareze.prospectsresttemplate.service.ProspectsService;

@Service
public class ProspectsServiceImpl implements ProspectsService{
	
	@Autowired
	public RestTemplate restTemplate;
	public final String url = "https://prospects-api.herokuapp.com/prospects/";

	@Override
	public List<Prospects> findAll() {
		ResponseEntity<List<Prospects>> prospectsResponse = 
				restTemplate.exchange(url,
									  HttpMethod.GET,
									  null,
									  new ParameterizedTypeReference <List<Prospects>>() {}); //lista fortemente tipada
		List<Prospects> propectsList = prospectsResponse.getBody();
		return propectsList;
	}

	@Override
	public Prospects create(Prospects prospect) {
		try {
			HttpEntity<Prospects> request = new HttpEntity<>(new Prospects(prospect));
			ResponseEntity<Prospects> prospectsResponse = 
					restTemplate.exchange(url,
										  HttpMethod.POST,
										  request,
										  Prospects.class);
			Prospects prospectsNew = prospectsResponse.getBody();
			return prospectsNew;
		} catch (HttpClientErrorException e) {
			throw new ResponseStatusException(e.getRawStatusCode(), e.getMessage(), e);
		}
	}
	
	@Override
	public Prospects update(String id, Prospects prospect) {
		try {
			HttpEntity<Prospects> request = new HttpEntity<>(new Prospects(prospect));
			ResponseEntity<Prospects> prospectsResponse = 
					restTemplate.exchange(url + id,
										  HttpMethod.PUT,
										  request,
										  Prospects.class);
			Prospects prospectsUpdated = prospectsResponse.getBody();
			return prospectsUpdated;
		} catch (HttpClientErrorException e) {
			throw new ResponseStatusException(e.getRawStatusCode(), e.getMessage(), e);
		}	
	}

	@Override
	public void delete(String id) {
		try {
			String entityUrl = url + id;
			restTemplate.delete(entityUrl);
		} catch (HttpClientErrorException e) {
			throw new ResponseStatusException(e.getRawStatusCode(), e.getMessage(), e);
		}	
	}
	
	

}
