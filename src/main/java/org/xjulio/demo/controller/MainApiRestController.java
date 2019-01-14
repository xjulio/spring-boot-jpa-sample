package org.xjulio.demo.controller;

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
import org.springframework.web.bind.annotation.RestController;
import org.xjulio.demo.exception.ActorNotFoundException;
import org.xjulio.demo.exception.DuplicatedEventException;
import org.xjulio.demo.model.Actor;
import org.xjulio.demo.model.Event;
import org.xjulio.demo.service.impl.ApiServiceImpl;

@RestController
/**
 * @author <a href="mailto:xjulio@gmail.com">Julio Cesar Damasceno</a>
 */
public class MainApiRestController {

	@Autowired
	private ApiServiceImpl eventService;

	@PostMapping("/events")
	public synchronized ResponseEntity<?> saveEvent(@RequestBody Event event) {
		try {
			eventService.saveEvent(event);
		} catch (DuplicatedEventException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@DeleteMapping("/erase")
	public ResponseEntity<?> deleteAll() {
		eventService.deleteAll();

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/events")
	public ResponseEntity<?> getAllEvents() {
		List<Event> events = eventService.retrieveEvents();

		return new ResponseEntity<>(events, HttpStatus.OK);
	}

	@GetMapping("/events/actors/{actorID}")
	public ResponseEntity<?> getAllEventsByActor(@PathVariable Long actorID) {
		List<Event> events = eventService.getByActor(actorID);

		return new ResponseEntity<>(events, HttpStatus.OK);
	}
	
	@PutMapping("/actors")
	public synchronized ResponseEntity<?> updateActor(@RequestBody Actor actor) {
		try {
			eventService.updateActor(actor);
		} catch (ActorNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/actors")
	public ResponseEntity<?> getActorsEvents() {
		List<Actor> actors = eventService.getActorsOrderedByEvents();

		return new ResponseEntity<>(actors, HttpStatus.OK);
	}
	
	@GetMapping("/actors/streak")
	public ResponseEntity<?> getActorsStreak() {
		List<Actor> actors = eventService.getActorsOrderedByEvents();

		return new ResponseEntity<>(actors, HttpStatus.OK);
	}	
}