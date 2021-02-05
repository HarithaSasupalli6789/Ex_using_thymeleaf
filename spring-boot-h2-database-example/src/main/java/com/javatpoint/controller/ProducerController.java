package com.javatpoint.controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.javatpoint.Producer;
import com.javatpoint.model.producerModel;

import com.javatpoint.service.producerServices;


@RestController
@Controller
public class producerContoller {
@Autowired
producerServices services;

public producerContoller(producerServices services) {
    this.services = services;
}

//index page
@RequestMapping(value = "/index")
public String index() {
   return "index";
}

//POST 
@RequestMapping(value="/save", method=RequestMethod.POST)    
public ModelAndView save(@ModelAttribute producerModel user)  
{    
ModelAndView modelAndView = new ModelAndView();    
modelAndView.setViewName("user-data");        
modelAndView.addObject("user", user);      
services.upload(user);
return modelAndView;    
}   

//GET ALL
@GetMapping("/retrieve")
public ModelAndView retrieve()
{
List<producerModel> display = services.getAllStudent();
//ModelAndView modelAndView = new ModelAndView();    
Map<String, Object> params = new HashMap<>();
params.put("display", display);
return new ModelAndView("display", params);
}

//PUT 
@PutMapping("/change/{id}")
public String updateEmployee(@PathVariable(value = "id") int employeeId, @RequestBody producerModel employeeDetails) throws UserNotFoundException
{
	producerModel employee = services.getStudentById(employeeId);
	employee.setAge(employeeDetails.getAge());
	services.upload(employee);
	return "Record updated successfully";
}

//DELETE BY ID
@RequestMapping(value = "/deleteemp", method = RequestMethod.GET)
private String deleteStudent(@RequestParam("id") int id, Model model) throws UserNotFoundException   
{  
	services.delete(id); 
	return "Record deleted successfully";

}  


//GET BY ID
@RequestMapping(value = "/authenticate", method = RequestMethod.GET)
public ModelAndView authenticateUser(@RequestParam("id") int id, Model model) throws UserNotFoundException
{
	producerModel display = services.getStudentById(id);
	Map<String, Object> params = new HashMap<>();
	params.put("display", display);
	return new ModelAndView("display", params);
    //return output;
}




/*
@RequestMapping(value="/retrievebyid", method=RequestMethod.POST)  
public ResponseEntity<Object> retrievebyid(@RequestParam("id") int id)
{
if(!services.exists(id)) return
ResponseEntity.status(HttpStatus.NOT_FOUND).body("Record not found");

producerModel output = services.getStudentById(id);
return ResponseEntity.status(HttpStatus.OK).body(output);
}
*/

/*
@PostMapping("/upload")
private ResponseEntity<producerModel> upload(@RequestBody producerModel student) {
services.upload(student);
return ResponseEntity.status(HttpStatus.CREATED).body(student);
}
@GetMapping("/student")
private List<producerModel> getAllStudent()
{
return services.getAllStudent();
}
@GetMapping("/student/{id}")
private ResponseEntity<Object> getStudent(@PathVariable("id") int id)
{
if(!services.exists(id)) return
ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not present");
producerModel output = services.getStudentById(id);
return ResponseEntity.status(HttpStatus.OK).body(output);
}*/
}