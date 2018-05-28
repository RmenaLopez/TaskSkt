package com.skt.finaltask.managementApp.managementApp.controller;

import com.skt.finaltask.commonLibrary.model.Product;
import com.skt.finaltask.managementApp.managementApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ProductController {

    @Autowired
    ProductService service;

    @GetMapping("/new-product")
    public ModelAndView showNewProductForm(ModelAndView modelAndView){
        modelAndView.setViewName("newProductForm");
        return modelAndView;
    }

    @GetMapping("/products")
    public ModelAndView showProducts(ModelAndView modelAndView){
        modelAndView.setViewName("products");
        modelAndView.addObject("products", service.getProducts());
        return modelAndView;
    }

    @PostMapping("/new-product")
    public ModelAndView addProduct (ModelAndView modelAndView,
                                 @RequestParam String description,
                                 @RequestParam Double price,
                                 @RequestParam String brand){
        modelAndView.setViewName("newProductForm");
        service.addProduct(new Product(description, price, brand));
        return modelAndView;
    }


}
