package com.l3.p4.services;

import com.l3.p4.dto.ProductDTO;

import java.util.List;

public interface ProductService {
	
// create product
    ProductDTO createProduct(ProductDTO productDTO);
// update product
    ProductDTO updateProduct(Long id, ProductDTO productDTO);
    
// get product by id
    ProductDTO getProductById(Long id);
    // get all product list

    List<ProductDTO> getAllProducts();
  // delete product
    void deleteProduct(Long id);
    
// create stock or update
    void updateStock(Long productId, int quantity); 
}

