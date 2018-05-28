package com.skt.finaltask.microservice;

import com.skt.finaltask.commonLibrary.model.Product;
import com.skt.finaltask.microservice.repository.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductTest {

    @Autowired
    ProductRepository repository;

    @Before
    public void setUp() throws Exception {

        Product product1 = new Product("Laptop chingona", 100.50, "Lenovo");
        Product product2 = new Product("Termino chingon", 5.50, "Termus");

        //save user, verify has ID value after save
        assertNull(product1.getId());
        assertNull(product2.getId());//null before save
        repository.addProduct(product1.getDescription(), product1.getPrice(), product1.getBrand());
        repository.addProduct(product2.getDescription(), product2.getPrice(), product2.getBrand());
    }

    @Test
    public void testFetchData(){
        // TODO: 26/05/18 Implement test for stored procedure getAllUsers()
        int count = 0;

        Iterable<Product> products = repository.findAll();

        for(Product product: products){
            count++;
        }

        assertEquals(count, 2);
    }


}
