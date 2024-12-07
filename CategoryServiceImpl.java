package com.example.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.model.Category;
import com.example.repository.CategoryRepository;
import com.example.service.CategoryService;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Page<Category> getAllCategories(int page) {
		return categoryRepository.findAll(PageRequest.of(page, 10));
	}

	@Override
	public Category createCategory(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public Optional<Category> getCategoryById(Long id) {
		return categoryRepository.findById(id);
	}

	@Override
	public Category updateCategory(Long id, Category categoryDetails) {
		Optional<Category> categoryOpt = categoryRepository.findById(id);
		if (categoryOpt.isPresent()) {
			Category category = categoryOpt.get();
			category.setName(categoryDetails.getName());
			return categoryRepository.save(category);
		}
		return null;
	}

	@Override
	public void deleteCategory(Long id) {
		categoryRepository.deleteById(id);
	}
}
