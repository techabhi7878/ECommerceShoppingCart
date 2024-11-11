package com.l3.p4.services.impl;

import java.util.List;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.l3.p4.dto.ProductDTO;
import com.l3.p4.entity.Product;
import com.l3.p4.exception.InsufficientStockException;
import com.l3.p4.exception.ProductNotFoundException;
import com.l3.p4.repository.ProductRepository;
import com.l3.p4.services.ProductService;
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ProductRepository productRepository;
	@Override
	public ProductDTO createProduct(ProductDTO productDTO) {
		Product product = this.modelMapper.map(productDTO, Product.class);
		Product savedProduct = this.productRepository.save(product);
		return  modelMapper.map(savedProduct, ProductDTO.class);
	}
	
	

	
	
	

	@Override
	public ProductDTO getProductById(Long id)  {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found"));
        return modelMapper.map(product, ProductDTO.class);
    }

	@Override
	public List<ProductDTO> getAllProducts() {
		List<Product>products=this.productRepository.findAll();		
	List<ProductDTO>allProductDTOs=	 products.stream()
        .map(product -> modelMapper.map(product, ProductDTO.class))
        .collect(Collectors.toList());
		return allProductDTOs;
	}

	@Override
	public void deleteProduct(Long id) {
		Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found"));
		this.productRepository.delete(product);
		
	}

	 @Override
	    public void updateStock(Long productId, int quantity) {
	        Product product = productRepository.findById(productId)
	                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + productId + " not found"));

	        int updatedStock = product.getStockQuantity() - quantity;
	        if (updatedStock < 0) {
	            throw new InsufficientStockException("Insufficient stock for product: " + product.getName());
	        }

	        product.setStockQuantity(updatedStock);
	        productRepository.save(product);
	    }







	 @Override
	    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
	        // Find the product by id or throw an exception if not found
	        Product product = productRepository.findById(id)
	                .orElseThrow(() -> new ProductNotFoundException("Product with ID " + id + " not found"));

	        modelMapper.map(productDTO, product);  

	        Product updatedProduct = productRepository.save(product);

	        
	        return modelMapper.map(updatedProduct, ProductDTO.class);
	    }
	}



