package com.ecommerce.productservice.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    long id;
    String name;
    String reference;
    String title;
    String description;

    @Column(name="selling_price")
    Double sellingPrice;
    Double weight;
    Double size;
    String color;

    @Column(name="categ_id")
    Long categoryId;
    Date created_at;
    Date updated_at;

}
