package com.github.rafm.personproj.rest;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.rafm.personproj.model.Person;
import com.github.rafm.personproj.repository.PersonRepository;

@RestController
@RequestMapping("/person")
public class PersonRestController {

	@Autowired private PersonRepository personRepository;
	
	@GetMapping
	public Iterable<Person> findAll(@RequestParam(defaultValue="0") @Min(0) int page,
									@RequestParam(defaultValue="20") @Min(1) @Max(100) int size,
									@RequestParam(defaultValue="ASC") Direction sort,
									@RequestParam(defaultValue="name") String... sortingFields) {
		return personRepository.findAll(PageRequest.of(page, size, sort, sortingFields)).getContent();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Person> findById(@PathVariable @Min(1) Integer id) {
		Optional<Person> result = personRepository.findById(id);
		return result.isPresent() ? ResponseEntity.ok(result.get()) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<String> save(@RequestBody @Valid Person person) {
		if (person.getId() != null) {
			return ResponseEntity.badRequest().build();
		}
		
		personRepository.save(person);
		return ResponseEntity.created(URI.create("/person/"+person.getId())).build();
	}
	
	// TODO Concurrency
	// TODO ETag HTTP 204
	@PutMapping("/{id}")
	public ResponseEntity<String> update(@PathVariable @Min(1) Integer id, @RequestBody @Valid Person person) {
		Optional<Person> result = personRepository.findById(id);
		if (!result.isPresent()) {
			return ResponseEntity.notFound().build(); 
		}
		
		person.setId(id);
		
		personRepository.save(person);
		return ResponseEntity.noContent().build();
	}
	
	// TODO Concurrency
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable @Min(1) Integer id) {
		if (personRepository.existsById(id)) {
			personRepository.deleteById(id);
		}
		
		return ResponseEntity.ok().build();
	}
}
