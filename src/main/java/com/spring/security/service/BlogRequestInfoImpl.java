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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class BlogRequestInfoImpl implements BlogInfoService {

    private static final Logger logger = LoggerFactory.getLogger(BlogRequestInfoImpl.class);
    
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
                logger.warn("Blog with titleUrl '{}' already exists.", blogRequest.getTitleUrl());
                return null;
            }

            blogRequest.setStatus("Active");
            // Trim the trailing hyphen from the titleUrl
            blogRequest.setTitleUrl(trimTrailingHyphen(blogRequest.getTitleUrl()));
            blogRequest.setPostTime(new Timestamp(System.currentTimeMillis()));

            if (!file.isEmpty()) {
                blogRequest.setImgUrl(CommanUtility.uploadFile(file));
            }

            mapData = modelMapper.map(blogRequest, BlogInfoDetail.class);
        } catch (Exception e) {
            logger.error("Error occurred while adding blog post");
        }
        return modelMapper.map(infoRepository.save(mapData), BlogInfoRequest.class);
    }

    @Override
    public BlogInfoRequest getBlogById(@NotNull String id) {
        BlogInfoDetail blogData = infoRepository.findById(id).orElse(null);
        if (blogData == null) {
            logger.warn("Blog with ID '{}' not found.", id);
            return null;
        }
        return modelMapper.map(blogData, BlogInfoRequest.class);
    }

    @Override
    public BlogInfoRequest updateBlog(String id, MultipartFile file, BlogInfoRequest blogRequest) {
        BlogInfoDetail mapData = new BlogInfoDetail();
        try {
            blogRequest.setId(id);
            blogRequest.setUpdateTime(new Timestamp(System.currentTimeMillis()));
            BlogInfoRequest blogById = getBlogById(id);

            if (file.isEmpty()) {
                blogRequest.setImgUrl(blogById.getImgUrl());
            } else {
                blogRequest.setImgUrl(CommanUtility.uploadFile(file));
            }

            mapData = modelMapper.map(blogRequest, BlogInfoDetail.class);
        } catch (Exception e) {
            logger.error("Error occurred while updating blog post with ID '{}'", id);
        }
        return modelMapper.map(infoRepository.save(mapData), BlogInfoRequest.class);
    }

    @Override
    public void deleteBlogById(@NotNull String id) {
        try {
            infoRepository.deleteById(id);
            logger.info("Blog with ID '{}' successfully deleted.", id);
        } catch (Exception e) {
            logger.error("Error occurred while deleting blog post with ID '{}'", id);
        }
    }

    @Override
    public List<BlogInfoRequest> findAllBlogByStatus() {
        List<BlogInfoDetail> findAll = infoRepository.findByStatus("Active");
        return findAll.stream().map(x -> modelMapper.map(x, BlogInfoRequest.class)).toList();
    }

    @Override
    public List<BlogInfoRequest> findAllBlogByStatusAndPageUrl(String pageUrl) {
        List<BlogInfoDetail> findAll = infoRepository.findByStatusAndTitleUrl("Active", pageUrl);
        return findAll.stream().map(x -> modelMapper.map(x, BlogInfoRequest.class)).toList();
    }

    private String trimTrailingHyphen(String titleUrl) {
        if (titleUrl != null && titleUrl.endsWith("-")) {
            return titleUrl.substring(0, titleUrl.length() - 1);
        }
        return titleUrl;
    }
}
