package org.sid.dao;

import org.sid.entities.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
@RepositoryRestResource
public interface CategoryRepository extends MongoRepository<Category, String>{

}
