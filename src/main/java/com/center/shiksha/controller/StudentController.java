package com.center.shiksha.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.center.shiksha.model.Standard;
import com.center.shiksha.dto.SchoolStandardSectionJoin;
import com.center.shiksha.model.School;
import com.center.shiksha.model.Section;
import com.center.shiksha.model.student.Student;
import com.center.shiksha.model.student.StudentExcel;
import com.center.shiksha.repository.StandardRepository;
import com.center.shiksha.repository.SchoolRepository;
import com.center.shiksha.repository.SectionRepository;
import com.center.shiksha.repository.StudentRepository;

@RestController
public class StudentController {

	@Autowired
	StudentRepository repository;
	@Autowired
	SchoolRepository schoolRepository;
	@Autowired
	StandardRepository standardRepository;
	@Autowired
	SectionRepository sectionRepository;

	@PostMapping("/importStudents")
	public List<Student> mapReapExcelDatatoDB(StudentExcel studentExcel) throws IOException {

		InputStream inputStream = studentExcel.getFile().getInputStream();
		Workbook workbook = new XSSFWorkbook(inputStream);
		
		long count = repository.getCount(studentExcel.getSchoolId(), studentExcel.getStandardId(), studentExcel.getSectionId());
		SchoolStandardSectionJoin result = repository.getSchoolStandardSection(studentExcel.getSectionId(), studentExcel.getStandardId(), studentExcel.getSchoolId());
		System.out.println(result);
//		School school = schoolRepository.findById(studentExcel.getSchoolId()).orElse(null);
//		Standard standard = standardRepository.findById(studentExcel.getStandardId()).orElse(null);
//		Section section = sectionRepository.findById(studentExcel.getSectionId()).orElse(null);
		
//		Sheet sheet = workbook.getSheetAt(0);
//		List<Student> students = new ArrayList<>(sheet.getPhysicalNumberOfRows());
//		
//		for (Row row : sheet) {
//			long rollNo = ++count;
//			Student student = getStudent(school, standard, section, rollNo);
//			for (Cell cell : row) {
//				switch (cell.getCellType()) {
//				case STRING:
//					student.setName(cell.getStringCellValue());
//					break;
//				default:
//					break;
//				}
//			}
//			students.add(student);
//		}
//		workbook.close();
//
//		repository.saveAll(students);

		return repository.findAll();
	}

	private Student getStudent(School school, Standard standard, Section section, long rollNo) {
		int year = getCurrentYear();
		String studentCode = new StringBuilder(2).append(school.getCode())
				.append(standard.getCode()).append(year).append(rollNo).toString();
		Student student = new Student();
		student.setSchoolId(school.getId());
		student.setStandardId(standard.getId());
		student.setSectionId(section.getId());
		student.setRollNumber(rollNo);
		student.setCode(studentCode);
		return student;
	}

	private int getCurrentYear() {
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		return year;
	}
}
