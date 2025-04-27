package com.spring.security.interfaces;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.spring.security.request.CategoryInfoRequest;
import com.spring.security.request.CategoryReq;

public interface CategoryInfoService {

	List<CategoryInfoRequest> getAllCategoryContant();

	CategoryInfoRequest addCategory(CategoryInfoRequest defaultRequest, MultipartFile file);

	CategoryInfoRequest getCategoryById(@NotNull String id);

	CategoryInfoRequest updateCategory(String id, MultipartFile file, CategoryInfoRequest infoRequest);

	void deleteCategoryById(@NotNull String id);

	List<CategoryInfoRequest> findAllCategoryByStatus();

	List<CategoryInfoRequest> findAllCategoryByStatusAndCategoryUrl(String categoryUrl);

	ResponseEntity<String> addCategory(CategoryReq request);

	List<CategoryReq> getAllInfoCategory();

	void deleteCategory(@NotNull String id);

	List<CategoryInfoRequest> findCatergory(String url, String categoryUrl);

}
