package com.spring.security.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spring.security.entity.Category;
import com.spring.security.entity.CategoryInfoDetail;
import com.spring.security.interfaces.CategoryInfoService;
import com.spring.security.repositories.CategoryInfoRepository;
import com.spring.security.repositories.CategoryRepository;
import com.spring.security.request.CategoryInfoRequest;
import com.spring.security.request.CategoryReq;
import com.spring.security.utility.CommonUtility;

@Service
public class CategoryServiceImpl implements CategoryInfoService {

	private final ModelMapper modelMapper;
	private final CategoryRepository repository;
	private final CategoryInfoRepository infoRepository;

	public CategoryServiceImpl(CategoryInfoRepository infoRepository, ModelMapper modelMapper,
			CategoryRepository repository) {
		this.modelMapper = modelMapper;
		this.repository = repository;
		this.infoRepository = infoRepository;
	}

	@Override
	public List<CategoryInfoRequest> getAllCategoryContant() {
		List<CategoryInfoDetail> findAll = infoRepository.findAll();
		return findAll.stream().map(x -> modelMapper.map(x, CategoryInfoRequest.class)).toList();
	}

	@Override
	public ResponseEntity<CategoryInfoRequest> addCategory(CategoryInfoRequest request, MultipartFile file) {
		try {
			// Check if the page URL already exists
			CategoryInfoDetail urlContent = infoRepository.findByPageUrl(request.getPageUrl());
			if (urlContent != null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			}

			// Set default values
			request.setFeatured(true);
			request.setCreatedAt(new Timestamp(System.currentTimeMillis()));
			request.setStatus("Active");

			if (!file.isEmpty()) {
				request.setImgUrl(CommonUtility.uploadFile(file));
			}

			// Map and save the data
			CategoryInfoDetail mapData = modelMapper.map(request, CategoryInfoDetail.class);
			CategoryInfoDetail savedData = infoRepository.save(mapData);
			CategoryInfoRequest response = modelMapper.map(savedData, CategoryInfoRequest.class);

			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@Override
	public ResponseEntity<CategoryInfoRequest> getCategoryById(String id) {
		Optional<CategoryInfoDetail> data = infoRepository.findById(id);
		if (data.isPresent()) {
			return ResponseEntity.ok(modelMapper.map(data.get(), CategoryInfoRequest.class));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@Override
	public ResponseEntity<CategoryInfoRequest> updateCategory(String id, MultipartFile file,
			CategoryInfoRequest request) {
		try {
			request.setId(id);
			Optional<CategoryInfoRequest> existingCategory = Optional.ofNullable(getCategoryById(id).getBody());

			if (existingCategory.isPresent()) {
				CategoryInfoRequest existingData = existingCategory.get();

				// Preserve old values where necessary
				request.setCreatedAt(existingData.getCreatedAt());
				request.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
				request.setFeatured(existingData.isFeatured());
				request.setCreatedBy(existingData.getCreatedBy());

				// Handle file upload
				if (!file.isEmpty()) {
					request.setImgUrl(CommonUtility.uploadFile(file));
				} else {
					request.setImgUrl(existingData.getImgUrl());
				}

				CategoryInfoDetail mapData = modelMapper.map(request, CategoryInfoDetail.class);
				CategoryInfoDetail updatedData = infoRepository.save(mapData);
				CategoryInfoRequest response = modelMapper.map(updatedData, CategoryInfoRequest.class);

				return ResponseEntity.status(HttpStatus.OK).body(response);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@Override
	public ResponseEntity<Void> deleteCategoryById(String id) {
		Optional<CategoryInfoDetail> category = infoRepository.findById(id);
		if (category.isPresent()) {
			infoRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@Override
	public List<CategoryInfoRequest> findAllCategoryByStatus() {
		List<CategoryInfoDetail> findByStatus = infoRepository.findByStatus("Active");
		return findByStatus.stream().map(x -> modelMapper.map(x, CategoryInfoRequest.class)).toList();
	}

	@Override
	public List<CategoryInfoRequest> findAllCategoryByStatusAndCategoryUrl(String categoryUrl) {
		List<CategoryInfoDetail> findAll = infoRepository.findByStatusAndCategoryUrl("Active", categoryUrl);
		return findAll.stream().map(x -> modelMapper.map(x, CategoryInfoRequest.class)).toList();
	}

	@Override
	public ResponseEntity<String> addCategory(CategoryReq request) {
		try {
			Category category = modelMapper.map(request, Category.class);

			// Check for duplicates
			if (repository.findByUrl(request.getUrl()) != null || repository.findByName(request.getName()) != null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Category already exists!");
			}

			category.setPostTime(System.currentTimeMillis());
			category.setStatus("Active");
			repository.save(category);
			return ResponseEntity.status(HttpStatus.CREATED).body("Category added successfully!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding category.");
		}
	}

	@Override
	public List<CategoryReq> getAllInfoCategory() {
		List<Category> findByStatus = repository.findByStatus("Active");
		return findByStatus.stream().map(x -> modelMapper.map(x, CategoryReq.class)).toList();
	}

	@Override
	public ResponseEntity<String> deleteCategory(String id) {
		Optional<Category> category = repository.findById(id);
		if (category.isPresent()) {
			repository.deleteById(id);
			return ResponseEntity.ok("Successfully deleted category by provided id.");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found by provided id.");
	}

	@Override
	public List<CategoryInfoRequest> findCategory(String url, String categoryUrl) {
		List<CategoryInfoDetail> findAll = infoRepository.findByCategoryUrlAndPageUrl(url, categoryUrl);
		return findAll.stream().map(x -> modelMapper.map(x, CategoryInfoRequest.class)).toList();
	}

	@Override
	public ResponseEntity<CategoryReq> getCategoryByUrl(String url) {
		Optional<Category> category = repository.findByStatusAndUrl("Active", url);
		return category.map(c -> ResponseEntity.ok(modelMapper.map(c, CategoryReq.class)))
				.orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CategoryReq()));
	}
}
