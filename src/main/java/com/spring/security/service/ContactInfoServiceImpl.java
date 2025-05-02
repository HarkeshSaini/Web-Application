package com.spring.security.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
	public ResponseEntity<Void> deleteById(String id) {
		Optional<ContactInfoDetail> contactInfoDetail = contactInfoRepository.findById(id);
		if (contactInfoDetail.isPresent()) {
			contactInfoRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@Override
	public ResponseEntity<String> submitContact(ContactInfoRequest request) {
		try {
			request.setPostTime(new Timestamp(System.currentTimeMillis()));
			request.setStatus("Active");
			request.setLang_code("en");
			ContactInfoDetail data = modelMapper.map(request, ContactInfoDetail.class);
			ContactInfoDetail savedData = contactInfoRepository.save(data);
			if (savedData == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong. Please try again.");
			}
			return ResponseEntity.status(HttpStatus.CREATED).body("Thank you for contacting us!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<ContactInfoRequest> findByIdContactUs(String id) {
		Optional<ContactInfoDetail> findById = contactInfoRepository.findById(id);
		if (findById.isPresent()) {
			ContactInfoRequest requestData = modelMapper.map(findById.get(), ContactInfoRequest.class);
			return ResponseEntity.status(HttpStatus.OK).body(requestData);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@Override
	public ResponseEntity<Boolean> updateStatusOfContactUsByStatus(String id, String value) {
		Optional<ContactInfoDetail> findById = contactInfoRepository.findById(id);
		if (findById.isPresent()) {
			ContactInfoDetail contactInfoDetail = findById.get();
			String status = value.equals("1") ? "Active" : "InActive";
			contactInfoDetail.setStatus(status);
			contactInfoDetail.setUpdateTime(System.currentTimeMillis());
			contactInfoRepository.save(contactInfoDetail);

			return ResponseEntity.status(HttpStatus.OK).body(value.equals("1"));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
		}
	}
}
