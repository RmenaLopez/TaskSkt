package com.skt.finaltask.managementApp.managementApp.controller;

import com.skt.finaltask.commonLibrary.model.Product;
import com.skt.finaltask.managementApp.managementApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

import static com.skt.finaltask.managementApp.managementApp.controller.ErrorController.error;

@RestController
public class ProductController {
    private final String PRODUCT_FORM_URL = "/new-product";
    private final String PRODUCT_LIST_URL = "/products";

    @Autowired
    private ProductService service;

    @GetMapping(PRODUCT_FORM_URL)
    public ModelAndView showNewProductForm(ModelAndView modelAndView){
        modelAndView.setViewName("/newProductForm");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }

    @GetMapping(PRODUCT_LIST_URL)
    public ModelAndView showProducts(ModelAndView modelAndView){
        modelAndView.setViewName("/productsList");
        modelAndView.addObject("products", service.getProducts());
        return modelAndView;
    }

    @PostMapping(PRODUCT_FORM_URL)
    public ModelAndView addProduct (ModelAndView modelAndView,
                                    @Valid @ModelAttribute("product")Product product,
                                    BindingResult result){
        if (result.hasErrors()){
            modelAndView = error(PRODUCT_FORM_URL, result.getAllErrors());
            return modelAndView;
        }
        modelAndView.setViewName("/newProductForm");
        modelAndView.addObject(new Product());
        modelAndView.setStatus(HttpStatus.CREATED);
        service.addProduct(product);
        return modelAndView;
    }
}
