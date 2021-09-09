package br.fatec.filmes.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.fatec.filmes.model.Ator;
import br.fatec.filmes.service.AtorService;

@RestController
@RequestMapping(value = "/atores")
public class AtorController implements ControllerInterface<Ator> {

	@Autowired
	private AtorService service;
	
	@Override
	@GetMapping
	public ResponseEntity<List<Ator>> get() {		
		return ResponseEntity.ok(service.findAll());
	}

	@Override
	@GetMapping(value = "{id}")
	public ResponseEntity<?> getById(@PathVariable("id") Long id) {
		Ator _ator = service.findById(id);
		if (_ator != null) {
			return ResponseEntity.ok(_ator);
		}		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@GetMapping(value = "/page")
	public ResponseEntity<Page<Ator>> get(Pageable pageable) {
		return ResponseEntity.ok(service.findAll(pageable));
	}
	
	@GetMapping(value = "/filme/{id}")
	public ResponseEntity<Page<Ator>> getByFilme(Pageable pageable, @PathVariable("id") Long filmeId) {
		return ResponseEntity.ok(service.findByFilme(pageable, filmeId));
	}

	@GetMapping(value = "/nacionalidade/{id}")
	public ResponseEntity<Page<Ator>> getByNacionalidade(Pageable pageable, @PathVariable("id") Long nacionalidadeId) {
		return ResponseEntity.ok(service.findByNacionalidade(pageable, nacionalidadeId));
	}
	
	@GetMapping(value = "/count/{id}")
	public ResponseEntity<Long> countByNacionalidade(@PathVariable("id") Long nacionalidadeId) {
		return ResponseEntity.ok(service.countByNacionalidade(nacionalidadeId));
	}
	
	@Override
	@PostMapping
	public ResponseEntity<Ator> post(@RequestBody Ator obj) {
		service.create(obj);
		URI uri = ServletUriComponentsBuilder
		        .fromCurrentRequest().path("/{id}")
		        .buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}

	@Override
	@PutMapping
	public ResponseEntity<?> put(@RequestBody Ator obj) {
		if (service.update(obj)) {
			return ResponseEntity.ok(obj);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@Override
	@DeleteMapping(value = "{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		if (service.delete(id)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
		
}
