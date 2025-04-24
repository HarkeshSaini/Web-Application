package com.spring.security.interfaces;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.springframework.web.multipart.MultipartFile;

import com.spring.security.request.DefaultInfoRequest;

public interface DefaultInfoService {

	List<DefaultInfoRequest> getAllDefaultContant();

	DefaultInfoRequest addDefault(DefaultInfoRequest defaultRequest, MultipartFile file);

	DefaultInfoRequest getDefaultById(@NotNull String id);

	DefaultInfoRequest updateDefault(String id, MultipartFile file, DefaultInfoRequest infoRequest);

	void deleteDefaultById(@NotNull String id);

	List<DefaultInfoRequest> findAllDefaultByStatus();

	List<DefaultInfoRequest> findAllDefaultByStatusAndPageUrl(String pageUrl);

}
