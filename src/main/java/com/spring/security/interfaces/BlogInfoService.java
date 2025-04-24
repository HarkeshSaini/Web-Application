package com.spring.security.interfaces;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.springframework.web.multipart.MultipartFile;

import com.spring.security.request.BlogInfoRequest;
import com.spring.security.request.DefaultInfoRequest;

public interface BlogInfoService {

	List<BlogInfoRequest> getAllBlog();

	BlogInfoRequest addBlog(BlogInfoRequest blogRequest, MultipartFile file);

	BlogInfoRequest getBlogById(@NotNull String id);

	BlogInfoRequest updateBlog(String id, MultipartFile file, BlogInfoRequest infoRequest);

	void deleteBlogById(@NotNull String id);

	List<BlogInfoRequest> findAllBlogByStatus();

	List<BlogInfoRequest> findAllBlogByStatusAndPageUrl(String pageUrl);


}
