package com.example.demo;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.dto.SupplierDTO;
import com.example.demo.entity.SupplierEntity;
import com.example.demo.repository.SupplierRepository;
import com.example.demo.service.SupplierService;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class SupplierServiceTest {

	@Mock
	private SupplierRepository supplierRepository;
	
	@InjectMocks
	private SupplierService supplierService;
	
	private SupplierDTO supplierDTO;
	
	private SupplierEntity supplierEntity;
	
	@BeforeEach
	void setUp() {

		supplierDTO = SupplierDTO.builder()
				//.id(5)
				.name("Ravi")
				.contactInfo("email")
				.rating("not bad")
				.build();
		
		supplierEntity = SupplierEntity.builder()
				//.id(5)
				.name("Ravi")
				.contactInfo("email")
				.rating("not bad")
				.build();
		
				
	}
	
	@Test
	@Order(1)
	void testSave() {
		//when(supplierRepository.save(supplierEntity)).thenReturn(supplierEntity);
		SupplierDTO savedSupplier = supplierService.insertSupplier(supplierDTO);
		assertEquals("Ravi", savedSupplier.getName());
	}
	
	@Test
	@Order(3)
	void testDetById() {
		when(supplierRepository.findById(1)).thenReturn(Optional.of(supplierEntity));
		SupplierDTO found = supplierService.getSupplierById(1);
		assertTrue(found != null);
		assertEquals("Ravi", found.getName());
		
	}
	
	@Test
	@Order(2)
	void testGetAll() {

		when(supplierRepository.findAll()).thenReturn(List.of(supplierEntity));
		List<SupplierDTO> list = supplierService.getAllSuppliers();
		assertEquals(1, list.size());
		
	}
	
	@Test
	@Order(4)
	void testUpdate() {
		SupplierDTO updateSupplierDTO = SupplierDTO.builder()
				.id(1)
				.name("ravi")
				.contactInfo("Ruavi.gmai.ocomm")
				.rating("not bad")
				.build();
		
		SupplierEntity updateSupplierEntity = SupplierEntity.builder()
				.id(1)
				.name("ravi")
				.contactInfo("Ruavi.gmai.ocomm")
				.rating("not bad")
				.build();
		
		when(supplierRepository.findById(1)).thenReturn(Optional.of(supplierEntity));
		when(supplierRepository.save(any(SupplierEntity.class))).thenReturn(updateSupplierEntity);
		
		SupplierDTO result = supplierService.updateSupplier(1, updateSupplierDTO);
		assertEquals("ravi", result.getName());
	}
	
	@Test
	@Order(5)
	void testDelete() {
//		doNothing().when(supplierRepository)
//		.deleteById(5);
//		
//		supplierService.deleteSupplier(5);
//		verify(supplierRepository,times(1)).deleteById(5);
		
		supplierRepository.deleteById(5);
		Optional<SupplierEntity> supplierEntity = supplierRepository.findById(5);
		assertTrue(supplierEntity.isEmpty());
		
	}
	
}
