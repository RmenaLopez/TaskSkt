package com.skt.finaltask.managementApp.managementApp;

import com.skt.finaltask.commonLibrary.model.Product;
import com.skt.finaltask.managementApp.managementApp.controller.ProductController;
import com.skt.finaltask.managementApp.managementApp.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @MockBean
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @Autowired
    private MockMvc mvc;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        this.mvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    public void addValidProduct() throws Exception{

        Product product = new Product("Big round bouncing ball", 150.50, "Bouncies");

        mvc.perform(post("/new-product")
        .param("description", product.getDescription())
        .param("price", Double.toString(product.getPrice()))
        .param("brand", product.getBrand())
        .flashAttr("product",new Product())).andExpect(status().isCreated());
    }
    @Test
    public void addInvalidProduct() throws Exception{

        Product productWithInvalidDescription = new Product("", 150.50, "Bouncies");
        Product productWithInvalidPrice = new Product("Big round bouncing ball", -4.50, "Bouncies");
        Product productWithInvalidBrand = new Product("Big round bouncing ball", 100.50, "");
        Product invalidProduct = new Product("", -50.5, "");

        mvc.perform(post("/new-product")
        .param("description", productWithInvalidDescription.getDescription())
        .param("price", Double.toString(productWithInvalidDescription.getPrice()))
        .param("brand", productWithInvalidDescription.getBrand())
        .flashAttr("product",new Product())).andExpect(status().isBadRequest());

        mvc.perform(post("/new-product")
        .param("description", productWithInvalidBrand.getDescription())
        .param("price", Double.toString(productWithInvalidBrand.getPrice()))
        .param("brand", productWithInvalidBrand.getBrand())
        .flashAttr("product",new Product())).andExpect(status().isBadRequest());

        mvc.perform(post("/new-product")
        .param("description", productWithInvalidPrice.getDescription())
        .param("price", Double.toString(productWithInvalidPrice.getPrice()))
        .param("brand", productWithInvalidPrice.getBrand())
        .flashAttr("product",new Product())).andExpect(status().isBadRequest());

        mvc.perform(post("/new-product")
        .param("description", invalidProduct.getDescription())
        .param("price", Double.toString(invalidProduct.getPrice()))
        .param("brand", invalidProduct.getBrand())
        .flashAttr("product",new Product())).andExpect(status().isBadRequest());
    }

    @Test
    public void viewProductForm() throws  Exception{

        mvc.perform(get("/new-product"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("product"))
                .andExpect(view().name("/newProductForm"));
    }

    @Test
    public void viewProductsList() throws  Exception{

        mvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("products"))
                .andExpect(view().name("/productsList"));
    }



}
