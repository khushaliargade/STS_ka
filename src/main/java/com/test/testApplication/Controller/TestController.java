package com.test.testApplication.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.testApplication.dto.StudentDto;
import com.test.testApplication.dto.TestResponse;
import com.test.testApplication.entity.Student;
import com.test.testApplication.service.TestService;

@RestController
@RequestMapping("/hello")

public class TestController {

	@Autowired

	private TestService testService;

	@PostMapping(value = "/save")
	public ResponseEntity<TestResponse<?>> saveUsers(@RequestBody StudentDto studentDto) {
		TestResponse<StudentDto> testResponse = new TestResponse<>();
		StudentDto savedUserResponse = testService.save(studentDto);
		testResponse.setHttpsStatus(HttpStatus.OK);
		testResponse.setMessage("saves successfully");
		testResponse.setData(savedUserResponse);
		return new ResponseEntity<>(testResponse, HttpStatus.OK);
	}

	@PostMapping(value = "/validateEmail/{email}")
	public ResponseEntity<TestResponse<String>> Validateemail(@PathVariable String email) {
		TestResponse<String> testResponse = new TestResponse<>();
		String rsponse = testService.validateEamil(email);
		testResponse.setHttpsStatus(HttpStatus.OK);
		testResponse.setMessage("email already exists");
		testResponse.setData(rsponse);
		return new ResponseEntity<>(testResponse, HttpStatus.OK);
	}
	
	/*@PostMapping(value = "/validatePassword/{password}")
	public ResponseEntity<TestResponse<String>> Validatepassword(@PathVariable String password) {
		TestResponse<String> testResponse = new TestResponse<>();
		String rsponse = testService.validateEamil(password);
		testResponse.setHttpsStatus(HttpStatus.OK);
		testResponse.setMessage("password ");
		testResponse.setData(rsponse);
		return new ResponseEntity<>(testResponse, HttpStatus.OK);*/
	

	@GetMapping("/getUsers")

	public List<StudentDto> getAllList() {
		return this.testService.getAllList();
	}
	
	

	/*@PostMapping("/register")
	public String save(@Validated StudentDto studentDto, BindingResult bindingResult ) {
		
 {
		if (TestService.userExists(StudentDto.getEmail())) {
			bindingResult.addError(new FieldError( objectName: "StudentDto", field:"email",defaultMessage: "Email address already in use"));
	}
		
		if(bindingResult.hasErrors())
			return "register";
		
	}
	log.info(">> StudentDTO : {}",  StudentDtoString());
	return "redirect:/login";*/
	
}
