package com.center.shiksha.repository;

import java.sql.ResultSet;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.center.shiksha.model.Section;
import com.center.shiksha.model.student.Student;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RepositoryRestResource(collectionResourceRel = "students", path="students")
public interface StudentRepository extends JpaRepository<Student, Integer>{
	
	@Query(value = "select  count(*) as count from student where student.school_id = ?1 and student.standard_id = ?2 and student.section_id = ?3", nativeQuery = true)
	int getCount(int schoolId, int standardId, int sectionId);
	
	
}
