package com.spring.security.service;

import java.sql.Timestamp;
import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.spring.security.entity.BlogInfoDetail;
import com.spring.security.interfaces.BlogInfoService;
import com.spring.security.repositories.BlogInfoRepository;
import com.spring.security.request.BlogInfoRequest;

@Service
public class BlogRequestInfoImpl implements BlogInfoService {

	private final ModelMapper modelMapper;

	private final BlogInfoRepository infoRepository;

	public BlogRequestInfoImpl(ModelMapper modelMapper, BlogInfoRepository infoRepository) {
		this.modelMapper = modelMapper;
		this.infoRepository = infoRepository;
	}

	@Override
	public List<BlogInfoRequest> getAllBlog() {
		List<BlogInfoDetail> findAll = infoRepository.findAll();
		return findAll.stream().map(x -> modelMapper.map(x, BlogInfoRequest.class)).toList();
	}

	@Override
	public BlogInfoRequest addBlog(BlogInfoRequest blogRequest, MultipartFile file) {
		blogRequest.setPostTime(new Timestamp(System.currentTimeMillis()));
		BlogInfoDetail mapData = new BlogInfoDetail();
		try {
			BlogInfoDetail urlContent = infoRepository.findByTitleUrl(blogRequest.getTitleUrl());
			if (!ObjectUtils.isEmpty(urlContent)) {
				return null;
			}
			if (!file.isEmpty()) {
				blogRequest.setImgUrl(file.getOriginalFilename());
			}
			mapData = modelMapper.map(blogRequest, BlogInfoDetail.class);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return modelMapper.map(infoRepository.save(mapData), BlogInfoRequest.class);
	}

	@Override
	public BlogInfoRequest getBlogById(@NotNull String id) {
		BlogInfoDetail blogData = infoRepository.findById(id).orElse(null);
		return modelMapper.map(blogData, BlogInfoRequest.class);
	}

	@Override
	public BlogInfoRequest updateBlog(String id, MultipartFile file, BlogInfoRequest blogRequest) {
		BlogInfoDetail mapData = new BlogInfoDetail();
		try {
			blogRequest.setId(id);
			blogRequest.setPostTime(new Timestamp(System.currentTimeMillis()));
			blogRequest.setImgUrl(file.getOriginalFilename());
			if (file.isEmpty()) {
				BlogInfoRequest blogById = getBlogById(id);
				blogRequest.setImgUrl(blogById.getImgUrl());
			}
			mapData = modelMapper.map(blogRequest, BlogInfoDetail.class);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return modelMapper.map(infoRepository.save(mapData), BlogInfoRequest.class);
	}

	@Override
	public void deleteBlogById(@NotNull String id) {
		infoRepository.deleteById(id);
	}

}
