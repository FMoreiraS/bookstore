package com.mindblow.bookstore.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "CART_ITEMS")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(value = AccessLevel.NONE)
    private Long id;
    
    @Column(name = "QUANTITY", nullable = false)
    private Long quantity;

    @OneToOne(optional = false)
    @JoinColumn(name = "PRODUCT_ID")
    private Book product;
}
