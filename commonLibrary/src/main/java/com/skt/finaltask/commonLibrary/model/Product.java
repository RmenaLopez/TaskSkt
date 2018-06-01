package com.skt.finaltask.commonLibrary.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "product_tbl")
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
    @NotNull
    @Size(min = 1, message = "Product description can't be blank.")
    @Column(name = "description")
    private String description;
    @NotNull(message = "Product's price can't be blank")
    @DecimalMin(value = "0.00", message = "Product's price must be positive.")
    @Column(name = "price")
    private Double price;
    @NotNull
    @Size(min = 1, message = "Product brand can't be blank.")
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
