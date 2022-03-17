package com.raihan.uu.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.raihan.uu.exception.RecordNotFoundException;
import com.raihan.uu.model.ProductEntity;
import com.raihan.uu.service.ProductService;

@Controller
@RequestMapping("/")
public class ProductController
{
	@Autowired
	ProductService service;

	@RequestMapping
	String home(){return "welcome";}

	@RequestMapping("/show")
	public String getAllProducts(Model model)
	{
		List<ProductEntity> list = service.getAllProducts();

		model.addAttribute("products", list);
		return "allProduct";
	}

	@RequestMapping(path = {"/add", "/add/{id}"})
	public String editProductById(Model model, @PathVariable("id") Optional<Long> id)
							throws RecordNotFoundException 
	{
		if (id.isPresent()) {
			ProductEntity entity = service.getProductById(id.get());
			model.addAttribute("product", entity);
		} else {
			model.addAttribute("product", new ProductEntity());
		}
		return "addProduct";
	}
	
	@RequestMapping(path = "/delete/{id}")
	public String deleteProductById(Model model, @PathVariable("id") Long id)
							throws RecordNotFoundException 
	{
		service.deleteProductById(id);
		return "redirect:/show";
	}

	@RequestMapping(path = "/createProduct", method = RequestMethod.POST)
	public String createOrUpdateProduct(ProductEntity product)
	{
		service.createOrUpdateProduct(product);
		return "redirect:/show";
	}
}
