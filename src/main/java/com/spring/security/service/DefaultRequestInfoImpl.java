package com.spring.security.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.spring.security.entity.DefaultInfoDetail;
import com.spring.security.interfaces.DefaultInfoService;
import com.spring.security.repositories.DefaultInfoRepository;
import com.spring.security.request.DefaultInfoRequest;
import com.spring.security.utility.CommonUtility;

@Service
public class DefaultRequestInfoImpl implements DefaultInfoService {

	private final ModelMapper modelMapper;
	private final DefaultInfoRepository infoRepository;

	public DefaultRequestInfoImpl(ModelMapper modelMapper, DefaultInfoRepository infoRepository) {
		this.modelMapper = modelMapper;
		this.infoRepository = infoRepository;
	}

	@Override
	public List<DefaultInfoRequest> getAllDefaultContant() {
		List<DefaultInfoDetail> findAll = infoRepository.findAll();
		return findAll.stream().map(x -> modelMapper.map(x, DefaultInfoRequest.class)).toList();
	}

	@Override
	public ResponseEntity<DefaultInfoRequest> addDefault(DefaultInfoRequest defaultRequest, MultipartFile file) {
		try {
			// Check if the category already exists
			DefaultInfoDetail existingCategory = infoRepository.findByCategory(defaultRequest.getCategory());
			if (existingCategory != null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			}

			// Set default values
			defaultRequest.setStatus("Active");
			defaultRequest.setPostTime(new Timestamp(System.currentTimeMillis()));
			if (!file.isEmpty()) {
				defaultRequest.setImgUrl(CommonUtility.uploadFile(file));
			}

			// Convert to entity and save
			DefaultInfoDetail mapData = modelMapper.map(defaultRequest, DefaultInfoDetail.class);
			DefaultInfoDetail savedData = infoRepository.save(mapData);

			DefaultInfoRequest savedRequest = modelMapper.map(savedData, DefaultInfoRequest.class);
			return ResponseEntity.status(HttpStatus.CREATED).body(savedRequest);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@Override
	public ResponseEntity<DefaultInfoRequest> getDefaultById(String id) {
		Optional<DefaultInfoDetail> findById = infoRepository.findById(id);
		if (findById.isPresent()) {
			DefaultInfoRequest requestData = modelMapper.map(findById.get(), DefaultInfoRequest.class);
			return ResponseEntity.status(HttpStatus.OK).body(requestData);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@Override
	public ResponseEntity<DefaultInfoRequest> updateDefault(String id, MultipartFile file,
			DefaultInfoRequest defaultRequest) {
		try {
			Optional<DefaultInfoDetail> existingData = infoRepository.findById(id);
			if (!existingData.isPresent()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			}

			// Set values for update
			defaultRequest.setId(id);
			defaultRequest.setUpdateTime(new Timestamp(System.currentTimeMillis()));

			if (file != null && !file.isEmpty()) {
				defaultRequest.setImgUrl(CommonUtility.uploadFile(file));
			} else {
				// Keep the existing image URL if no file is uploaded
				defaultRequest.setImgUrl(existingData.get().getImgUrl());
			}

			DefaultInfoDetail mapData = modelMapper.map(defaultRequest, DefaultInfoDetail.class);
			DefaultInfoDetail updatedData = infoRepository.save(mapData);
			DefaultInfoRequest updatedRequest = modelMapper.map(updatedData, DefaultInfoRequest.class);

			return ResponseEntity.status(HttpStatus.OK).body(updatedRequest);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@Override
	public ResponseEntity<Void> deleteDefaultById(String id) {
		try {
			Optional<DefaultInfoDetail> defaultInfoDetail = infoRepository.findById(id);
			if (defaultInfoDetail.isPresent()) {
				infoRepository.deleteById(id);
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@Override
	public List<DefaultInfoRequest> findAllDefaultByStatus() {
		List<DefaultInfoDetail> findAll = infoRepository.findByStatus("Active");
		return findAll.stream().map(x -> modelMapper.map(x, DefaultInfoRequest.class)).toList();
	}

	@Override
	public List<DefaultInfoRequest> findAllDefaultByStatusAndPageUrl(String category) {
		List<DefaultInfoDetail> findAll = infoRepository.findByStatusAndCategory("Active", category);
		return findAll.stream().map(x -> modelMapper.map(x, DefaultInfoRequest.class)).toList();
	}
}
