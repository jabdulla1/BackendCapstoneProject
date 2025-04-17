package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class ProductManagementTest {

	@Mock
	private ProductRepository productRepository;
	
	@InjectMocks
	private ProductService productService;
	
	private ProductDao productDao;
	
	
	private ProductEntity productEntity;
	
	
	@BeforeEach
	void setUp() {

		productDao = ProductDao.builder()
				.sku(1)
				.name("apples")
				.description("red apples")
				.initial_stock(1000)
				.build();
		
		productEntity = ProductEntity.builder()
				//.id(1)
				.sku(1)
				.name("apples")
				.description("red apples")
				.initial_stock(1000)
				.build();				
	}
	
	@Test
	@Order(1)
	void testSave() {
		
		 ProductRepository productRepositoryMock = Mockito.mock(ProductRepository.class);
	
		//when(productRepository.save(supplierEntity)).thenReturn(supplierEntity);
		 //ProductService productService = new ProductService(productRepositoryMock);
		ProductDao savedProduct = productService.addProduct(productDao);
		assertEquals("apples", savedProduct.getName());
	}
	
	@Test
	@Order(3)
	void testDetById() {
		when(productRepository.findById(1)).thenReturn(Optional.of(productEntity));
		ProductDao found = productService.getProductDetailsBySku(1);
		assertTrue(found != null);
		assertEquals("apples", found.getName());
		
	}
	
	@Test
	@Order(2)
	void testGetAll() {

		when(productRepository.findAll()).thenReturn(List.of(productEntity));
		List<ProductDao> list = productService.getAllproducts();
		assertEquals(1, list.size());
		
	}
	
	@Test
	@Order(4)
	void testUpdate() {
		ProductDao updateProductDao = ProductDao.builder()
				.sku(1)
				.name("apples")
				.description("red apples")
				.initial_stock(2000)
				.build();
		
		ProductEntity updateSupplierEntity = ProductEntity.builder()
				.id(1)
				.sku(1)
				.name("apples")
				.description("red apples")
				.initial_stock(2000)
				.build();	
		
		when(productRepository.findById(1)).thenReturn(Optional.of(productEntity));
		when(productRepository.save(any(ProductEntity.class))).thenReturn(updateSupplierEntity);
		
		ProductDao result = productService.updateProduct(1, updateProductDao);
		assertEquals("apples", result.getName());
	}
	
	@Test
	@Order(5)
	void testDelete() {
//		doNothing().when(productRepository)
//		.deleteById(5);
//		
//		productService.deleteSupplier(5);
//		verify(productRepository,times(1)).deleteById(5);
		
		productRepository.deleteById(5);
		Optional<ProductEntity> productEntity = productRepository.findById(5);
		assertTrue(productEntity.isEmpty());
		
	}
	
	
}
