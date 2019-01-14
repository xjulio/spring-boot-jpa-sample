package org.xjulio.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.xjulio.demo.model.Actor;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {
}