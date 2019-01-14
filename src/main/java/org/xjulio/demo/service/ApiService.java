/*
 * @(#)ApiService.java
 *
 * Copyright 2019 Xjulio
 */
package org.xjulio.demo.service;

import java.util.List;

import org.xjulio.demo.model.Actor;
import org.xjulio.demo.model.Event;

/**
 * @author <a href="mailto:xjulio@gmail.com">Julio Cesar Damasceno</a>
 */
public interface ApiService {
	public List<Event> retrieveEvents();

	public Event getEvent(Long eventId);
	
	public List<Event> getByActor(Long actorId);

	public void saveEvent(Event event);

	public void deleteEvent(Long eventId);
	
	public void deleteAll();

	public void updateEvent(Event event);

	public void updateActor(Actor actor);

	public List<Actor> getActorsOrderedByEvents();

	List<Actor> getActorsStreak();
}