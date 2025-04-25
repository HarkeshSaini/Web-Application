package com.spring.security.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.spring.security.entity.Category;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {

	List<Category> findByStatus(String string);

	Category findByUrl(String url);

	Category findByName(String name);

}
