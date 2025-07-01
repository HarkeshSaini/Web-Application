package com.spring.security.interfaces;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.spring.security.request.CategoryInfoRequest;
import com.spring.security.request.CategoryReq;

public interface CategoryInfoService {

	List<CategoryInfoRequest> getAllCategoryContant();

	ResponseEntity<CategoryInfoRequest> addCategory(CategoryInfoRequest defaultRequest, MultipartFile file);

	ResponseEntity<CategoryInfoRequest> getCategoryById(@NotNull int id);

	ResponseEntity<CategoryInfoRequest> updateCategory(int id, MultipartFile file, CategoryInfoRequest infoRequest);

	ResponseEntity<Void> deleteCategoryById(@NotNull int id);

	List<CategoryInfoRequest> findAllCategoryByStatus();

	List<CategoryInfoRequest> findAllCategoryByStatusAndCategoryUrl(String categoryUrl);

	ResponseEntity<String> addCategory(CategoryReq request);

	List<CategoryReq> getAllInfoCategory();

	ResponseEntity<String> deleteCategory(@NotNull int id);

	List<CategoryInfoRequest> findCategory(String url, String categoryUrl);

	ResponseEntity<CategoryReq> getCategoryByUrl(String url);

}
