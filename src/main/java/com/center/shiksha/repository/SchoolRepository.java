package com.center.shiksha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.center.shiksha.model.School;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RepositoryRestResource(collectionResourceRel = "schools", path="schools")
public interface SchoolRepository extends JpaRepository<School, Integer>{

	String findByCode(int id);
}
