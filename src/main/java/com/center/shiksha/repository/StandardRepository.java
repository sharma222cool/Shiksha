package com.center.shiksha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.center.shiksha.model.Standard;
import com.center.shiksha.model.Section;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RepositoryRestResource(collectionResourceRel = "standards", path="standards")
public interface StandardRepository extends JpaRepository<Standard, Integer>{

}
