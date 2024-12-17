package com.eprit.ecommerce.Repository;


import com.eprit.ecommerce.Model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
