package org.xjulio.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.xjulio.demo.model.Repo;

@Repository
public interface RepoRepository extends JpaRepository<Repo, Long> {
}