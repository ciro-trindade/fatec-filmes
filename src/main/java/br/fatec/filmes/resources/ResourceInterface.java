package br.fatec.filmes.resources;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface ResourceInterface<T> {
	ResponseEntity<List<T>> get();
	ResponseEntity<?> getById(Long id);
	ResponseEntity<T> post(T obj);
	ResponseEntity<?> put(T obj);
	ResponseEntity<?> delete(Long id);
}
