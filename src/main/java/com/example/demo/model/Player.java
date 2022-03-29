package com.example.demo.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String name;

    @Column(nullable = false)
    private Integer salary;

    @ManyToMany
    private List<MatchResults> matchResults;

    public Player() {}

    public Player(String name, Integer salary) {
        this.name = name;
        this.salary = salary;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public List<MatchResults> getMatchResults() {
        return matchResults;
    }

    public void setMatchResults(List<MatchResults> matchResults) {
        this.matchResults = matchResults;
    }
}
