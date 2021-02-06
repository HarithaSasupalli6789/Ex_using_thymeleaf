package com.javatpoint.repository;

import org.springframework.data.repository.CrudRepository;
import com.javatpoint.model.ProducerModel;

public interface ProducerRepository extends CrudRepository<ProducerModel, Integer> {
}