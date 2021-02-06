package com.javatpoint.service;

import java.util.Optional;

import java.util.stream.Collectors;
 
import java.util.stream.Stream;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.Mockito.verify;
import com.javatpoint.controller.UserNotFoundException;
import com.javatpoint.model.ProducerModel;
import com.javatpoint.repository.ProducerRepository;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class ProducerServicesTest {

	@InjectMocks
	private ProducerServices service;
	
	@Mock
	private ProducerRepository repo;


	
	//GET ALL
	@Test
	void getAllStudentTest()
	{
		when(repo.findAll()).thenReturn(Stream
				.of(new ProducerModel(1,"haritha","sasupalli",21,"TCS",532421)).collect(Collectors.toList()));
		assertEquals(1, service.getAllStudent().size());
	}
	
	//GETBYID
	@Test
	 void getStudentByIdTest() throws UserNotFoundException {
		int id=1;
		ProducerModel user = new ProducerModel(1,"haritha","sasupalli",21,"TCS",532421);
		when(service.exists(Mockito.anyInt())).thenReturn(true);
		when(repo.findById(id))
				.thenReturn(Optional.of(user));
		assertEquals(user.getId(), service.getStudentById(id).getId());
	}
	//DELETE
	@Test
	 void deleteTest() throws UserNotFoundException {
		ProducerModel user = new ProducerModel(1,"haritha","sasupalli",21,"TCS",532421);
		service.delete(1);
		verify(repo, times(1)).delete(user);
	}
	
	//POST
	@Test
	 void uploadTest() {
		ProducerModel user = new ProducerModel(1,"haritha","sasupalli",21,"TCS",532421);
		when(repo.save(user)).thenReturn(user);
		assertEquals(user, service.upload(user));
	}
	
}
