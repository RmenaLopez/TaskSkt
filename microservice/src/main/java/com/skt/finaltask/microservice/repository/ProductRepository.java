package com.skt.finaltask.microservice.repository;

import com.skt.finaltask.commonLibrary.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface ProductRepository extends CrudRepository<Product, Integer> {

    @Procedure( name = "addProduct")
        void addProduct(@Param("description") String description,
                        @Param("price") Double price,
                        @Param("brand") String brand);

    // TODO: 26/05/18 Make this procedure work!!
    @Query(nativeQuery = true, value = "find_all_products")
        List<Product> getAllProducts();
}
