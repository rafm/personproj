package com.daitangroup.initproj.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.daitangroup.initproj.model.Person;

@Repository
public interface PersonRepository extends PagingAndSortingRepository<Person, Integer> {}