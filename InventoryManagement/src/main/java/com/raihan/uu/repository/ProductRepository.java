package com.raihan.uu.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.raihan.uu.model.ProductEntity;

@Repository
public interface ProductRepository
			extends CrudRepository<ProductEntity, Long> {

}
