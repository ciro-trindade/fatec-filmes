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

import br.fatec.filmes.model.Nacionalidade;
import br.fatec.filmes.service.NacionalidadeService;

@RestController
@RequestMapping(value = "/nacionalidades")
public class NacionalidadeController implements ControllerInterface<Nacionalidade> {

	@Autowired
	private NacionalidadeService service;
	
	@Override
	@GetMapping
	public ResponseEntity<List<Nacionalidade>> get() {		
		return ResponseEntity.ok(service.findAll());
	}

	@Override
	@GetMapping(value = "{id}")
	public ResponseEntity<?> getById(@PathVariable("id") Long id) {
		Nacionalidade _nac = service.findById(id);
		if (_nac != null) {
			return ResponseEntity.ok(_nac);
		}		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@GetMapping(value = "/page")
	public ResponseEntity<Page<Nacionalidade>> getAll(Pageable pageable) {
		return ResponseEntity.ok(service.findAll(pageable));
	}

	@GetMapping(value = "/page/{letra}")
	public ResponseEntity<Page<Nacionalidade>> getByLetra(Pageable pageable, @PathVariable("letra") Character letra) {
		return ResponseEntity.ok(service.findByPais(pageable, letra));
	}
	
	@Override
	@PostMapping
	public ResponseEntity<Nacionalidade> post(@RequestBody Nacionalidade obj) {
		service.create(obj);
		URI uri = ServletUriComponentsBuilder
		        .fromCurrentRequest().path("/{id}")
		        .buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}

	@Override
	@PutMapping
	public ResponseEntity<?> put(@RequestBody Nacionalidade obj) {
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
