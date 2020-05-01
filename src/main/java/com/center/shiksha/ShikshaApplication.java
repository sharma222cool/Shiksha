package com.center.shiksha;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import com.center.shiksha.controller.SchoolController;
import com.center.shiksha.model.School;
import com.center.shiksha.model.SchoolStandardSection;
import com.center.shiksha.model.Section;
import com.center.shiksha.model.Standard;
import com.center.shiksha.repository.SchoolRepository;
import com.center.shiksha.repository.SectionRepository;
import com.center.shiksha.repository.StandardRepository;

import net.bytebuddy.implementation.bytecode.collection.ArrayAccess;

@SpringBootApplication
@EntityScan(basePackages = {"com.center.shiksha.model"})
public class ShikshaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShikshaApplication.class, args);
		
	}


}
