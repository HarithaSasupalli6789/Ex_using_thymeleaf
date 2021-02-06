package com.javatpoint.controller;

import java.util.HashMap;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

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
import com.javatpoint.model.ProducerModel;
import com.javatpoint.service.ProducerServices;


@RestController
@Controller
public class ProducerController {
@Autowired
ProducerServices services;

public ProducerController(ProducerServices services) {
    this.services = services;
}

//index page
@RequestMapping(value = "/index")
public String index() {
   return "index";
}

//POST 
//@RequestMapping(value="/save", method=RequestMethod.POST) 
@PostMapping(path = "/save")

public ModelAndView save(@ModelAttribute ProducerModel user)  
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
List<ProducerModel> display = services.getAllStudent();    
Map<String, Object> params = new HashMap<>();
params.put("display", display);
return new ModelAndView("display", params);
}

//PUT 
@PutMapping("/change/{id}")
public String updateEmployee(@PathVariable(value = "id") int employeeId, @RequestBody ProducerModel employeeDetails) throws UserNotFoundException
{
	ProducerModel employee = services.getStudentById(employeeId);
	employee.setAge(employeeDetails.getAge());
	services.upload(employee);
	return "Record updated successfully";
}

//DELETE BY ID
@RequestMapping(value = "/deleteemp", method = RequestMethod.GET)
public String deleteStudent(@RequestParam("id") int id, Model model) throws UserNotFoundException   
{  
	services.delete(id); 
	return "Record deleted successfully";

}  


//GET BY ID
@RequestMapping(value = "/authenticate", method = RequestMethod.GET)
public ModelAndView authenticateUser(@RequestParam("id") int id, Model model) throws UserNotFoundException
{
	ProducerModel display = services.getStudentById(id);
	Map<String, Object> params = new HashMap<>();
	params.put("display", display);
	return new ModelAndView("display", params);
}

}