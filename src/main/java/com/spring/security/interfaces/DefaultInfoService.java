package com.spring.security.interfaces;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.spring.security.request.DefaultInfoRequest;

public interface DefaultInfoService {

	List<DefaultInfoRequest> getAllDefaultContant();

	ResponseEntity<DefaultInfoRequest> addDefault(DefaultInfoRequest defaultRequest, MultipartFile file);

	ResponseEntity<DefaultInfoRequest> getDefaultById(@NotNull int id);

	ResponseEntity<DefaultInfoRequest> updateDefault(int id, MultipartFile file, DefaultInfoRequest infoRequest);

	ResponseEntity<Void> deleteDefaultById(@NotNull int id);

	List<DefaultInfoRequest> findAllDefaultByStatus();

	List<DefaultInfoRequest> findAllDefaultByStatusAndPageUrl(String pageUrl);

}
