package com.sidie88.IndocyberTest.entity;

import java.math.BigDecimal;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCT")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_PRODUCT")
	private Long productId;
	
	@Column(name = "PRODUCT_NAME")
    private String productName;
    
    @Column(name = "PRICE")
    private BigDecimal price;
    
    @Column(name = "STOCK")
    private Integer stock;
    
	
	public Long getProductId() {
        return productId;
   }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Product(String productName, BigDecimal price, Integer stock) {
        this.productName = productName;
        this.price = price;
        this.stock = stock;
    }

    public Product() {
    }
}
