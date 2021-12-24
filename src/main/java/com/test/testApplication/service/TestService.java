package com.test.testApplication.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.test.testApplication.Controller.TestController;
import com.test.testApplication.dto.StudentDto;
import com.test.testApplication.entity.Student;
import com.test.testApplication.repository.TestRepository;

import ch.qos.logback.classic.Logger;

@Service
@Component
public class TestService {

	private static List<Student> list = new ArrayList<>();
	//private static final Logger log = LoggerFactory.getLogger(TestController.class)
	//private final TestService testService;

	@Autowired
	TestRepository testRepository;

	public StudentDto save(StudentDto studentDto) {

		Student student = new Student();
		BeanUtils.copyProperties(studentDto, student);
		testRepository.save(student);
		Student savedStudent = testRepository.save(student);
		BeanUtils.copyProperties(savedStudent, studentDto);
		return studentDto;

	}

	public List<StudentDto> getAllList() {

		List<StudentDto> result = new ArrayList<>();

		List<Student> list = testRepository.findAll();
		list.stream().forEach(singleStudent -> {

			StudentDto studentDto = new StudentDto();

			BeanUtils.copyProperties(singleStudent, studentDto);
			result.add(studentDto);

		});
		return result;

	}

	public String validateEamil(String Email) {
		try {

			Student student = testRepository.findByEmail(Email);
			if (student != null) {
				return "email already exists";
			} else {
				return "email doesn't exists";
			}
			
			/*if(StudentDto.getPassword() != null && StudentDto.getPassword() !=null) {
				if(!userDto.getPassword().equals(userDto.getPassword())) {
					
				}*/
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return Email;
	
	}

}
