package com.skt.finaltask.commonLibrary.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "products_tbl")
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "getAllProducts",
                procedureName = "find_all_products", resultClasses = Product.class),
        @NamedStoredProcedureQuery(name = "addProduct",
                procedureName = "insert_new_product",
                parameters = {
                        @StoredProcedureParameter(name = "description", type = String.class, mode = ParameterMode.IN),
                        @StoredProcedureParameter(name = "price", type = Double.class, mode = ParameterMode.IN),
                        @StoredProcedureParameter(name = "brand", type = String.class, mode = ParameterMode.IN)
                })
})
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private Double price;
    @Column(name = "brand")
    private String brand;

    public Product(){}

    public Product(String description, Double price, String brand) {
        this.description = description;
        this.price = price;
        this.brand = brand;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
