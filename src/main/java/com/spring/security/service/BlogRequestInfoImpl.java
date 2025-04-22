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
import com.spring.security.utility.CommanUtility;

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
		BlogInfoDetail mapData = new BlogInfoDetail();
		try {
			BlogInfoDetail urlContent = infoRepository.findByTitleUrl(blogRequest.getTitleUrl());
			if (!ObjectUtils.isEmpty(urlContent)) {
				return null;
			}
			blogRequest.setPostTime(new Timestamp(System.currentTimeMillis()));
			blogRequest.setStatus("Active");
			if (blogRequest.getTitleUrl().endsWith("-")) {
				blogRequest.setTitleUrl(blogRequest.getTitleUrl().substring(0, blogRequest.getTitleUrl().length() - 1)); 
		    }
			blogRequest.setPostTime(new Timestamp(System.currentTimeMillis()));
			if (!file.isEmpty()) {
				blogRequest.setImgUrl(CommanUtility.uploadFile(file));
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
			blogRequest.setUpdateTime(new Timestamp(System.currentTimeMillis()));
			blogRequest.setImgUrl(file.getOriginalFilename());
			BlogInfoRequest blogById = getBlogById(id);
			if (file.isEmpty()) {
				blogRequest.setImgUrl(blogById.getImgUrl());
			}else {
				blogRequest.setImgUrl(CommanUtility.uploadFile(file));
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

	@Override
	public List<BlogInfoRequest> findAllBlogByStatus() {
		List<BlogInfoDetail> findAll = infoRepository.findByStatus("Active");
		return findAll.stream().map(x -> modelMapper.map(x, BlogInfoRequest.class)).toList();
	}

}
