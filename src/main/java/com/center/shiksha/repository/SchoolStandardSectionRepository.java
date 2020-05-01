package com.center.shiksha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.center.shiksha.model.School;
import com.center.shiksha.model.SchoolStandardSection;

public interface SchoolStandardSectionRepository extends JpaRepository<SchoolStandardSection, Integer>{

	
}
