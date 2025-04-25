package com.spring.security.service;

import java.sql.Timestamp;
import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.spring.security.entity.CategoryInfoDetail;
import com.spring.security.interfaces.CategoryInfoService;
import com.spring.security.repositories.CategoryRepository;
import com.spring.security.request.CategoryInfoRequest;
import com.spring.security.utility.CommanUtility;

@Service
public class CategoryServiceImpl implements CategoryInfoService {

	private final ModelMapper modelMapper;

	private final CategoryRepository infoRepository;

	public CategoryServiceImpl(CategoryRepository infoRepository, ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
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
			CategoryInfoDetail urlContent = infoRepository.findBycategoryUrl(request.getCategoryUrl());
			if (!ObjectUtils.isEmpty(urlContent)) {
				return null;
			}
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
			request.setCreatedAt(new Timestamp(System.currentTimeMillis()));
			request.setImgUrl(file.getOriginalFilename());
			CategoryInfoRequest blogById = getCategoryById(id);
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
		List<CategoryInfoDetail> findAll = infoRepository.findByStatus("Active");
		return findAll.stream().map(x -> modelMapper.map(x, CategoryInfoRequest.class)).toList();
	}

	@Override
	public List<CategoryInfoRequest> findAllCategoryByStatusAndCategoryUrl(String categoryUrl) {
		List<CategoryInfoDetail> findAll = infoRepository.findByStatusAndCategoryUrl("Active", categoryUrl);
		return findAll.stream().map(x -> modelMapper.map(x, CategoryInfoRequest.class)).toList();
	}
}
