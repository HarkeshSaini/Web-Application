package com.spring.security.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.spring.security.entity.ContactInfoDetail;
import com.spring.security.interfaces.ContactInfoService;
import com.spring.security.repositories.ContactInfoRepository;
import com.spring.security.request.ContactInfoRequest;

@Service
public class ContactInfoServiceImpl implements ContactInfoService {

	private final ModelMapper modelMapper;

	private final ContactInfoRepository contactInfoRepository;

	public ContactInfoServiceImpl(ContactInfoRepository contactInfoRepository, ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
		this.contactInfoRepository = contactInfoRepository;
	}

	@Override
	public List<ContactInfoRequest> showAllContactInfo() {
		List<ContactInfoDetail> findAll = contactInfoRepository.findAll();
		return findAll.stream().map(x -> modelMapper.map(x, ContactInfoRequest.class)).toList();
	}

	@Override
	public void deleteById(@NotNull String id) {
		contactInfoRepository.deleteById(id);
		
	}

	@Override
	public ResponseEntity<ContactInfoRequest> submitContact(ContactInfoRequest request) {
		request.setPostTime(new Timestamp(System.currentTimeMillis()));
		request.setStatus("Active");
		request.setLang_code("en");
		ContactInfoDetail data = modelMapper.map(request, ContactInfoDetail.class);
		ContactInfoDetail saveData = contactInfoRepository.save(data);
		if(ObjectUtils.isEmpty(saveData)) {
			ResponseEntity.ok(HttpStatus.BAD_REQUEST);
			return ResponseEntity.ok(null);
		}
		ContactInfoRequest requestData = modelMapper.map(saveData, ContactInfoRequest.class);
		ResponseEntity.ok(HttpStatus.CREATED);
		return ResponseEntity.ok(requestData);
	}

	@Override
	public ResponseEntity<ContactInfoRequest> findByIdContactUs(String id) {
		Optional<ContactInfoDetail> findById = contactInfoRepository.findById(id);
		ContactInfoDetail orElse = findById.orElse(null);
		if(ObjectUtils.isEmpty(orElse)) {
			ResponseEntity.ok(HttpStatus.BAD_REQUEST);
			return ResponseEntity.ok(null);
		}
		ContactInfoRequest requestData = modelMapper.map(orElse, ContactInfoRequest.class);
		ResponseEntity.ok(HttpStatus.OK);
		return ResponseEntity.ok(requestData);
	}

	@Override
	public ResponseEntity<Boolean> updateStatusOfContactUsByStatus(String id, String value) {
		Optional<ContactInfoDetail> findById = contactInfoRepository.findById(id);
		ContactInfoDetail orElse = findById.orElse(null);
		if(ObjectUtils.isEmpty(orElse)) {
			ResponseEntity.ok(HttpStatus.BAD_REQUEST);
			return ResponseEntity.ok(false);
		}
		orElse.setStatus(value.equals("1") ? "Active" : "InActive");
		orElse.setUpdateTime(System.currentTimeMillis());
		contactInfoRepository.save(orElse);
		ResponseEntity.ok(HttpStatus.OK);
		return ResponseEntity.ok(value.equals("1") ? true : false);
	}


}
