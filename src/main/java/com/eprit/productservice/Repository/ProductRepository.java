package com.eprit.productservice.Repository;


import com.eprit.productservice.Model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
