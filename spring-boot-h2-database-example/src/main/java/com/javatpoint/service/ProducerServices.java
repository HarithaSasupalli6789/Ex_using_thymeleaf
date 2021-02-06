package com.javatpoint.service;

import java.util.ArrayList;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javatpoint.controller.UserNotFoundException;
import com.javatpoint.model.ProducerModel;
import com.javatpoint.repository.ProducerRepository;

@Service
public class ProducerServices {
@Autowired
ProducerRepository commurepository;

public ProducerModel upload(ProducerModel student)
{
commurepository.save(student);
return student;
}
public List<ProducerModel> getAllStudent()
{
List<ProducerModel> students = new ArrayList<>();
commurepository.findAll().forEach(student -> students.add(student));
return students;
}
public ProducerModel getStudentById(int id) throws UserNotFoundException
{
	if(exists(id))
		return commurepository.findById(id).get();
	else
		throw new UserNotFoundException("The given id is not present");
}
public boolean exists(int id)
{
return commurepository.existsById(id);
}

public String delete(int id) throws UserNotFoundException
{  
	if(exists(id))
	{
	commurepository.deleteById(id);  
	return "Deleted Successfully";
	}
	else
	{
		throw new UserNotFoundException("The given id is not present");
	}	
} 
}