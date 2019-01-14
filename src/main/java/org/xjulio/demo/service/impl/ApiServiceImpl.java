/*
 * @(#)ApiServiceImpl.java
 *
 * Copyright 2019 Xjulio
 */
package org.xjulio.demo.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.xjulio.demo.exception.ActorNotFoundException;
import org.xjulio.demo.exception.DuplicatedEventException;
import org.xjulio.demo.exception.InvalidFieldUpdateException;
import org.xjulio.demo.model.Actor;
import org.xjulio.demo.model.Event;
import org.xjulio.demo.repository.ActorRepository;
import org.xjulio.demo.repository.EventRepository;
import org.xjulio.demo.service.ApiService;

/**
 * @author <a href="mailto:xjulio@gmail.com">Julio Cesar Damasceno</a>
 */
@Service
public class ApiServiceImpl implements ApiService {

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private ActorRepository actorRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hackerrank.github.service.EventService#retrieveEvents()
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Event> retrieveEvents() {
		List<Event> events = eventRepository.findAll();

		if (!CollectionUtils.isEmpty(events)) {
			events.sort(Comparator.comparing(Event::getId));

			return events;
		} else {
			return new ArrayList<Event>();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hackerrank.github.service.EventService#getEvent(java.lang.Long)
	 */
	@Override
	@Transactional(readOnly = true)
	public Event getEvent(Long eventId) {
		return eventRepository.findOne(eventId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hackerrank.github.service.EventService#saveEvent(com.hackerrank.github.model.Event)
	 */
	@Override
	@Transactional(readOnly = false)
	public void saveEvent(Event event) {
		Event existingEvent = getEvent(event.getId());
		if (existingEvent == null) {
			eventRepository.save(event);
		} else {
			throw new DuplicatedEventException();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hackerrank.github.service.EventService#deleteEvent(java.lang.Long)
	 */
	@Override
	@Transactional(readOnly = false)
	public void deleteEvent(Long eventId) {
		eventRepository.delete(eventId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hackerrank.github.service.EventService#deleteAll()
	 */
	@Override
	@Transactional(readOnly = false)
	public void deleteAll() {
		eventRepository.deleteAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hackerrank.github.service.EventService#updateEvent(com.hackerrank.github.model.Event)
	 */
	@Override
	public void updateEvent(Event event) {
		eventRepository.save(event);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hackerrank.github.service.EventService#getByActor(java.lang.Long)
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Event> getByActor(Long actorId) {
		List<Event> events = eventRepository.findByActor_Id(actorId);

		if (!CollectionUtils.isEmpty(events)) {
			events.sort(Comparator.comparing(Event::getId));

			return events;
		} else {
			return new ArrayList<Event>();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hackerrank.github.service.EventService#updateActor(com.hackerrank.github.model.Actor)
	 */
	@Override
	@Transactional(readOnly = false)
	public void updateActor(Actor eventActor) {
		Actor actor = actorRepository.getOne(eventActor.getId());

		if (actor == null) {
			throw new ActorNotFoundException();
		}

		if (!actor.getLogin().equals(eventActor.getLogin())) {
			throw new InvalidFieldUpdateException();
		}

		actor.setAvatar(eventActor.getAvatar());

		actorRepository.save(actor);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hackerrank.github.service.EventService#getActorsOrderedByEvents()
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Actor> getActorsOrderedByEvents() {
		List<Event> events = eventRepository.findAll();

		//Grouping
        LinkedHashMap<Long, List<Event>> m = events.stream()
        		.sorted(Comparator.comparing(Event::getCreatedAt).reversed().thenComparing(e -> e.getActor().getLogin()))
            .collect(Collectors.groupingBy(p -> p.getActor().getId(),  LinkedHashMap::new,
                    Collectors.toList()));
        
        //Sorting
		LinkedHashMap<Long, List<Event>> ms = m.entrySet().stream()
				 .sorted(Comparator.comparingInt(e->e.getValue().size()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));


		List<Long> keys = new ArrayList<>(ms.keySet());
		Set<Actor> actors  = new LinkedHashSet<>();
		for (int i = keys.size()-1; i >= 0; i--) {
			List<Event> evs = ms.get(keys.get(i));
			for (Event event : evs) {
				actors.add(event.getActor());
			}
		}
		
		return new ArrayList<>(actors);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hackerrank.github.service.EventService#getActorsOrderedByEvents()
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Actor> getActorsStreak() {
		List<Event> events = eventRepository.findAll();

		//Grouping
        LinkedHashMap<Long, List<Event>> m = events.stream()
        		.sorted(Comparator.comparing(Event::getCreatedAt).reversed().thenComparing(e -> e.getActor().getLogin()))
            .collect(Collectors.groupingBy(p -> p.getActor().getId(),  LinkedHashMap::new,
                    Collectors.toList()));
        
        //Sorting
		LinkedHashMap<Long, List<Event>> ms = m.entrySet().stream()
				 .sorted(Comparator.comparingInt(e->e.getValue().size()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));


		List<Long> keys = new ArrayList<>(ms.keySet());
		Set<Actor> actors  = new LinkedHashSet<>();
		for (int i = keys.size()-1; i >= 0; i--) {
			List<Event> evs = ms.get(keys.get(i));
			for (Event event : evs) {
				actors.add(event.getActor());
			}
		}
		
		return new ArrayList<>(actors);
	}	
}
