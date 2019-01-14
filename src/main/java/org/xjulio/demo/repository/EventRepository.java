package org.xjulio.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.xjulio.demo.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
	List<Event> findByActor_Id(Long id);
}