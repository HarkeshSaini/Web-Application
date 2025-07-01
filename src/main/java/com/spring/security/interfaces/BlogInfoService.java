package com.spring.security.interfaces;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.springframework.web.multipart.MultipartFile;

import com.spring.security.request.BlogInfoRequest;

public interface BlogInfoService {

	List<BlogInfoRequest> getAllBlog();

	BlogInfoRequest addBlog(BlogInfoRequest blogRequest, MultipartFile file);

	BlogInfoRequest getBlogById(@NotNull int id);

	BlogInfoRequest updateBlog(int id, MultipartFile file, BlogInfoRequest infoRequest);

	void deleteBlogById(@NotNull int id);

	List<BlogInfoRequest> findAllBlogByStatus();

	List<BlogInfoRequest> findAllBlogByStatusAndPageUrl(String pageUrl);

	List<BlogInfoRequest> getLatestBlog(int blogValue);


}
