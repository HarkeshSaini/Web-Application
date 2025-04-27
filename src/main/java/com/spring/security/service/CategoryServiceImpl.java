package com.spring.security.service;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.xpath;

import java.sql.Timestamp;
import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.spring.security.entity.Category;
import com.spring.security.entity.CategoryInfoDetail;
import com.spring.security.interfaces.CategoryInfoService;
import com.spring.security.repositories.CategoryInfoRepository;
import com.spring.security.repositories.CategoryRepository;
import com.spring.security.request.CategoryInfoRequest;
import com.spring.security.request.CategoryReq;
import com.spring.security.utility.CommanUtility;

@Service
public class CategoryServiceImpl implements CategoryInfoService {
	
	private final ModelMapper modelMapper;
	
	private final CategoryRepository repository;

	private final CategoryInfoRepository infoRepository;

	public CategoryServiceImpl(CategoryInfoRepository infoRepository, ModelMapper modelMapper, CategoryRepository repository) {
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
	public CategoryInfoRequest addCategory(CategoryInfoRequest request, MultipartFile file) {
		CategoryInfoDetail mapData = new CategoryInfoDetail();
		try {
			CategoryInfoDetail urlContent = infoRepository.findByPageUrl(request.getPageUrl());
			if (!ObjectUtils.isEmpty(urlContent)) {
				return null;
			}
			request.setFeatured(true);
			request.setCreatedAt(new Timestamp(System.currentTimeMillis()));
			request.setStatus("Active");
			if (!file.isEmpty()) {
				request.setImgUrl(CommanUtility.uploadFile(file));
			}
			mapData = modelMapper.map(request, CategoryInfoDetail.class);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return modelMapper.map(infoRepository.save(mapData), CategoryInfoRequest.class);
	}

	@Override
	public CategoryInfoRequest getCategoryById(@NotNull String id) {
		CategoryInfoDetail data = infoRepository.findById(id).orElse(null);
		return modelMapper.map(data, CategoryInfoRequest.class);
	}

	@Override
	public CategoryInfoRequest updateCategory(String id, MultipartFile file, CategoryInfoRequest request) {
		CategoryInfoDetail mapData = new CategoryInfoDetail();
		try {
			request.setId(id);
			CategoryInfoRequest blogById = getCategoryById(id);
			request.setCreatedAt(blogById.getCreatedAt());
			request.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
			request.setFeatured(blogById.isFeatured());
			request.setCreatedBy(blogById.getCreatedBy());
			if (file.isEmpty()) {
				request.setImgUrl(blogById.getImgUrl());
			} else {
				request.setImgUrl(CommanUtility.uploadFile(file));
			}
			mapData = modelMapper.map(request, CategoryInfoDetail.class);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return modelMapper.map(infoRepository.save(mapData), CategoryInfoRequest.class);
	}

	@Override
	public void deleteCategoryById(@NotNull String id) {
		infoRepository.deleteById(id);
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
		Category category = modelMapper.map(request, Category.class);
		Category url = repository.findByUrl(request.getUrl());
		Category name = repository.findByName(request.getName());
		if(url!=null || name!=null) {
			ResponseEntity.ok(HttpStatus.BAD_REQUEST);
			return ResponseEntity.ok("Category already exists!");
		}
		category.setPostTime(System.currentTimeMillis());
		category.setStatus("Active");
		repository.save(category);
		ResponseEntity.ok(HttpStatus.CREATED);
		return ResponseEntity.ok("Category added successfully!");
	}

	@Override
	public List<CategoryReq> getAllInfoCategory() {
		List<Category> findByStatus = repository.findByStatus("Active");
		return findByStatus.stream().map(x-> modelMapper.map(x, CategoryReq.class)).toList();
	}

	@Override
	public void deleteCategory(@NotNull String id) {
		repository.deleteById(id);
	}

	@Override
	public List<CategoryInfoRequest> findCatergory(String url, String categoryUrl) {
		List<CategoryInfoDetail> findAll = infoRepository.findByCategoryUrlAndPageUrl(url,categoryUrl);
		return findAll.stream().map(x -> modelMapper.map(x, CategoryInfoRequest.class)).toList();
	}

	@Override
	public CategoryReq getCategoryByUrl(String url) {
		Category category= repository.findByStatusAndUrl("Active",url);
		return modelMapper.map(category,CategoryReq.class);
	}

	 

}
