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
import com.javatpoint.model.producerModel;
import com.javatpoint.repository.producerRepository;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class producerServicesTest {


	@InjectMocks
	private producerServices service;
	
	@Mock
	private producerRepository repo;

	
	//GET ALL
	@Test
	public void getAllStudentTest()
	{
		when(repo.findAll()).thenReturn(Stream
				.of(new producerModel(1,"haritha","sasupalli",21,"TCS",532421)).collect(Collectors.toList()));
		assertEquals(1, service.getAllStudent().size());
	}
	
	//GETBYID
	@Test
	public void getStudentByIdTest() throws UserNotFoundException {
		int id=1;
		producerModel user = new producerModel(1,"haritha","sasupalli",21,"TCS",532421);
		when(service.exists(Mockito.anyInt())).thenReturn(true);
		when(repo.findById(id))
				.thenReturn(Optional.of(user));
		assertEquals(user.getId(), service.getStudentById(id).getId());
	}
	//DELETE
	@Test
	public void deleteTest() throws UserNotFoundException {
		producerModel user = new producerModel(1,"haritha","sasupalli",21,"TCS",532421);
		service.delete(1);
		verify(repo, times(1)).delete(user);
	}
	
	//POST
	@Test
	public void uploadTest() {
		producerModel user = new producerModel(1,"haritha","sasupalli",21,"TCS",532421);
		when(repo.save(user)).thenReturn(user);
		assertEquals(user, service.upload(user));
	}
	
}
