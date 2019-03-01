package com.katonarobert.webpontbackend.model;


import javax.persistence.*;

@Entity
public class Barcode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String barcodePath;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public Barcode() {
    }

    public Barcode(String barcodePath, Product product) {
        this.barcodePath = barcodePath;
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

}
