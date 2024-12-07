package com.example.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.model.Product;
import com.example.repository.ProductRepository;
import com.example.service.ProductService;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
	
//--------------------------------------------------------------------

	@Autowired
	private ProductRepository productRepository;

//--------------------------------------------------------------------
	@Override
	public Page<Product> getAllProducts(int page) {
		return productRepository.findAll(PageRequest.of(page, 10));
	}

	@Override
	public Product createProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Optional<Product> getProductById(Long id) {
		return productRepository.findById(id);
	}

	@Override
	public Product updateProduct(Long id, Product productDetails) {
		Optional<Product> productOpt = productRepository.findById(id);
		if (productOpt.isPresent()) {
			Product product = productOpt.get();
			product.setName(productDetails.getName());
			product.setPrice(productDetails.getPrice());
			return productRepository.save(product);
		}
		return null;
	}

	@Override
	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}
//--------------------------------------------------------------------
}
