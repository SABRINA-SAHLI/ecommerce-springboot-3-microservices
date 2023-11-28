package com.ecommerce.categoryservice.model;


import jakarta.persistence.*;
import lombok.*;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//@Builder
public class Product {

    @Id
    private Long id;
    private String title;


    @Column(name="selling_price")
    private Double sellingPrice;

   /* @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "categ_id"
    )*/
   @ManyToOne
   @JoinColumn(name="categ_id", nullable=false)
    private Category category;
}
// // referencedColumnName = "id"