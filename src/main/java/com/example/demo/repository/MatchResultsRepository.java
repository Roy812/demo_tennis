package com.example.demo.repository;

import com.example.demo.model.MatchResults;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchResultsRepository extends JpaRepository<MatchResults, Long> {
}
