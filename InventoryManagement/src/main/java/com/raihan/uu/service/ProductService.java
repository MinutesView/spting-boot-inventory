package com.raihan.uu.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.raihan.uu.exception.RecordNotFoundException;
import com.raihan.uu.model.ProductEntity;
import com.raihan.uu.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository repository;
	
	public List<ProductEntity> getAllProducts()
	{
		List<ProductEntity> result = (List<ProductEntity>) repository.findAll();
		
		if(result.size() > 0) {
			return result;
		} else {
			return new ArrayList<ProductEntity>();
		}
	}
	
	public ProductEntity getProductById(Long id) throws RecordNotFoundException
	{
		Optional<ProductEntity> product = repository.findById(id);
		
		if(product.isPresent()) {
			return product.get();
		} else {
			throw new RecordNotFoundException("No product record exist for given id");
		}
	}
	
	public ProductEntity createOrUpdateProduct(ProductEntity entity)
	{
		if(entity.getId()  == null) 
		{
			entity = repository.save(entity);
			
			return entity;
		} 
		else 
		{
			Optional<ProductEntity> product = repository.findById(entity.getId());
			
			if(product.isPresent())
			{
				ProductEntity newEntity = product.get();
				newEntity.setPrice(entity.getPrice());
				newEntity.setProductName(entity.getProductName());
				newEntity.setShortDescription(entity.getShortDescription());

				newEntity = repository.save(newEntity);
				
				return newEntity;
			} else {
				entity = repository.save(entity);
				
				return entity;
			}
		}
	} 
	
	public void deleteProductById(Long id) throws RecordNotFoundException
	{
		Optional<ProductEntity> product = repository.findById(id);
		
		if(product.isPresent())
		{
			repository.deleteById(id);
		} else {
			throw new RecordNotFoundException("No product record exist for given id");
		}
	} 
}