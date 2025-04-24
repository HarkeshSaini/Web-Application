package com.spring.security.service;

import java.sql.Timestamp;
import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.spring.security.entity.DefaultInfoDetail;
import com.spring.security.interfaces.DefaultInfoService;
import com.spring.security.repositories.DefaultInfoRepository;
import com.spring.security.request.DefaultInfoRequest;
import com.spring.security.utility.CommanUtility;

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
	public DefaultInfoRequest addDefault(DefaultInfoRequest defaultRequest, MultipartFile file) {
		DefaultInfoDetail mapData = new DefaultInfoDetail();
		try {
			DefaultInfoDetail urlContent = infoRepository.findByCategory(defaultRequest.getCategory());
			if (!ObjectUtils.isEmpty(urlContent)) {
				return null;
			}
			defaultRequest.setStatus("Active");
			defaultRequest.setPostTime(new Timestamp(System.currentTimeMillis()));
			if (!file.isEmpty()) {
				defaultRequest.setImgUrl(CommanUtility.uploadFile(file));
			}
			mapData = modelMapper.map(defaultRequest, DefaultInfoDetail.class);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return modelMapper.map(infoRepository.save(mapData), DefaultInfoRequest.class);
	}

	@Override
	public DefaultInfoRequest getDefaultById(@NotNull String id) {
		DefaultInfoDetail defaultData = infoRepository.findById(id).orElse(null);
		return modelMapper.map(defaultData, DefaultInfoRequest.class);
	}

	@Override
	public DefaultInfoRequest updateDefault(String id, MultipartFile file, DefaultInfoRequest defaultRequest) {
		DefaultInfoDetail mapData = new DefaultInfoDetail();
		try {
			defaultRequest.setId(id);
			defaultRequest.setUpdateTime(new Timestamp(System.currentTimeMillis()));
			defaultRequest.setImgUrl(file.getOriginalFilename());
			DefaultInfoRequest DefaultById = getDefaultById(id);
			if (file.isEmpty()) {
				defaultRequest.setImgUrl(DefaultById.getImgUrl());
			}else {
				defaultRequest.setImgUrl(CommanUtility.uploadFile(file));
			}
			mapData = modelMapper.map(defaultRequest, DefaultInfoDetail.class);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return modelMapper.map(infoRepository.save(mapData), DefaultInfoRequest.class);
	}

	@Override
	public void deleteDefaultById(@NotNull String id) {
		infoRepository.deleteById(id);
	}

	@Override
	public List<DefaultInfoRequest> findAllDefaultByStatus() {
		List<DefaultInfoDetail> findAll = infoRepository.findByStatus("Active");
		return findAll.stream().map(x -> modelMapper.map(x, DefaultInfoRequest.class)).toList();
	}

	@Override
	public List<DefaultInfoRequest> findAllDefaultByStatusAndPageUrl(String category) {
		List<DefaultInfoDetail> findAll = infoRepository.findByStatusAndCategory("Active",category);
		return findAll.stream().map(x -> modelMapper.map(x, DefaultInfoRequest.class)).toList();
	}

}
