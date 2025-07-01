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
import com.spring.security.entity.SubscribeInfoDetail;
import com.spring.security.interfaces.ContactInfoService;
import com.spring.security.repositories.ContactInfoRepository;
import com.spring.security.repositories.SubscribeRepository;
import com.spring.security.request.ContactInfoRequest;
import com.spring.security.request.SubscribeInfoRequest;

@Service
public class ContactInfoServiceImpl implements ContactInfoService {

	private final ModelMapper modelMapper;
	private final ContactInfoRepository contactInfoRepository;
	private final SubscribeRepository subscribeRepo;

	public ContactInfoServiceImpl(ContactInfoRepository contactInfoRepository, ModelMapper modelMapper,SubscribeRepository subscribeRepo) {
		this.modelMapper = modelMapper;
		this.contactInfoRepository = contactInfoRepository;
		this.subscribeRepo = subscribeRepo;
	}

	@Override
	public List<ContactInfoRequest> showAllContactInfo() {
		List<ContactInfoDetail> findAll = contactInfoRepository.findAll();
		return findAll.stream().map(x -> modelMapper.map(x, ContactInfoRequest.class)).toList();
	}

	@Override
	public ResponseEntity<Void> deleteById(int id) {
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
			if (request.getEmail() == null ||request.getName() == null) {
		        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("All fields are required. Please fill in all fields and try again.");
			}
		}  catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		request.setPostTime(new Timestamp(System.currentTimeMillis()));
		request.setStatus("Active");
		request.setLang_code("en");
		ContactInfoDetail data = modelMapper.map(request, ContactInfoDetail.class);
		ContactInfoDetail savedData = contactInfoRepository.save(data);
		try {
			if (savedData == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong. Please try again.");
			}
			return ResponseEntity.status(HttpStatus.CREATED).body("Thank you for contacting us!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<ContactInfoRequest> findByIdContactUs(int id) {
		Optional<ContactInfoDetail> findById = contactInfoRepository.findById(id);
		if (findById.isPresent()) {
			ContactInfoRequest requestData = modelMapper.map(findById.get(), ContactInfoRequest.class);
			return ResponseEntity.status(HttpStatus.OK).body(requestData);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@Override
	public ResponseEntity<Boolean> updateStatusOfContactUsByStatus(int id, String value) {
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

	@Override
	public ResponseEntity<String> subscribe(@NotNull SubscribeInfoRequest request) {
		try {
			request.setPostTime(new Timestamp(System.currentTimeMillis()));
			request.setStatus("InActive");
			SubscribeInfoDetail subscribeInfoDetail = modelMapper.map(request, SubscribeInfoDetail.class);
			SubscribeInfoDetail saveData = subscribeRepo.save(subscribeInfoDetail);
			if (ObjectUtils.isEmpty(saveData)) {
				return ResponseEntity.badRequest().body("Something went wrong. Please try again.");
			}
			return ResponseEntity.accepted().body("Thank you for subscribe us!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@Override
	public ResponseEntity<Boolean> updateStatusOfSubscribeInfoByStatus(int id, String value) {
		Optional<SubscribeInfoDetail> findById = subscribeRepo.findById(id);
		if (findById.isPresent()) {
			SubscribeInfoDetail contactInfoDetail = findById.get();
			String status = value.equals("1") ? "Active" : "InActive";
			contactInfoDetail.setStatus(status);
			contactInfoDetail.setUpdateTime(System.currentTimeMillis());
			subscribeRepo.save(contactInfoDetail);
			return ResponseEntity.status(HttpStatus.OK).body(value.equals("1"));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
		}
	}

	@Override
	public List<SubscribeInfoRequest> showAllSubscribeInfo() {
		List<SubscribeInfoDetail> findAll = subscribeRepo.findAll();
		return findAll.stream().map(x-> modelMapper.map(x, SubscribeInfoRequest.class)).toList();
	}

	@Override
	public ResponseEntity<Object> deleteSubscribeInfoById(int id) {
		Optional<SubscribeInfoDetail> optionalData = subscribeRepo.findById(id);
		if (optionalData.isPresent()) {
			subscribeRepo.deleteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}
