package com.l3.p4.Controller;



import com.l3.p4.dto.ProductDTO;
import com.l3.p4.entity.Product;
import com.l3.p4.exception.ProductNotFoundException;
import com.l3.p4.repository.ProductRepository;
import com.l3.p4.services.ProductService;
import com.l3.p4.services.impl.ProductServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class ProductServiceIntigrationTest {
	
    @Mock
    private ProductRepository productRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;
    private ProductDTO productDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialize mock objects
        product = new Product();
        product.setId(1L);
        product.setName("Test Product");

        productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("Test Product DTO");
    }

    @Test
    void testGetProductById() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(modelMapper.map(product, ProductDTO.class)).thenReturn(productDTO);

        ProductDTO result = productService.getProductById(1L);

        assertEquals("Test Product DTO", result.getName());
    }

    @Test
    void testGetProductById_ProductNotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.getProductById(1L));
    }

    @Test
    void testCreateProduct() {
        when(modelMapper.map(productDTO, Product.class)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);
        when(modelMapper.map(product, ProductDTO.class)).thenReturn(productDTO);

        ProductDTO result = productService.createProduct(productDTO);

        assertEquals("Test Product DTO", result.getName());
        verify(productRepository, times(1)).save(product);
    }
}




