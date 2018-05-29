package com.skt.finaltask.managementApp.managementApp.controller;

import com.skt.finaltask.commonLibrary.model.Product;
import com.skt.finaltask.managementApp.managementApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    ProductService service;

    @GetMapping("/new-product")
    public ModelAndView showNewProductForm(ModelAndView modelAndView){
        modelAndView.setViewName("/newProductForm");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }

    @GetMapping("/products")
    public ModelAndView showProducts(ModelAndView modelAndView){
        modelAndView.setViewName("/products");
        modelAndView.addObject("products", service.getProducts());
        return modelAndView;
    }

    @PostMapping("/new-product")
    public ModelAndView addProduct (ModelAndView modelAndView,
                                    @Valid @ModelAttribute("product")Product product,
                                    BindingResult result){
        if (result.hasErrors()){
            modelAndView = error("/new-product", result.getAllErrors());
            return modelAndView;
        }
        modelAndView.setViewName("/newProductForm");
        modelAndView.addObject(new Product());
        service.addProduct(product);
        return modelAndView;
    }


}
