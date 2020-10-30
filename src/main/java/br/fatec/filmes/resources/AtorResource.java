package br.fatec.filmes.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.fatec.filmes.model.Ator;
import br.fatec.filmes.service.AtorService;

@RestController
@RequestMapping(value = "/atores")
public class AtorResource implements ResourceInterface<Ator> {

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

	@Override
	@PostMapping
	public ResponseEntity<Ator> post(@RequestBody Ator obj) {		
		return ResponseEntity.ok(service.create(obj));
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

	@GetMapping(value = "/ordenado")
	public ResponseEntity<List<Ator>> getByOrdenadoPorNome() {
		return ResponseEntity.ok(service.listAtorOrdenadoPorNome());
	}
	
	@GetMapping(value = "/filme/{id}")
	public ResponseEntity<List<Ator>> getByFilme(@PathVariable("id") Long filmeId) {
		return ResponseEntity.ok(service.findByFilme(filmeId));
	}
	
	@GetMapping(value = "/nacionalidade/{id}")
	public ResponseEntity<List<Ator>> getByNacionalidade(@PathVariable("id") Long nacionalidadeId) {
		return ResponseEntity.ok(service.findByNacionalidade(nacionalidadeId));
	}

	@GetMapping(value = "/count/{id}")
	public ResponseEntity<Long> getCountByNacionalidade(@PathVariable("id") Long nacionalidadeId) {
		return ResponseEntity.ok(service.countAtorByNacionalidade(nacionalidadeId));
	}

}
