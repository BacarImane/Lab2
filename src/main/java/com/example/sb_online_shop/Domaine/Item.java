package com.example.sb_online_shop.Domaine;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity(name ="Item")
public class Item {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id ;

    private int quantity;
    private int price ; 

    @ManyToOne(fetch =FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private Order order ;

    @ManyToOne(fetch =FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;
    
    
    public Item() {
    }
    public Item( int quantity, int price, Order order, Product product) {
        super();
        this.quantity = quantity;
        this.price = price;
        this.order = order;
        this.product = product;
    }
    public long getId() {
        return id;
    }
    public int getQuantity() {
        return quantity;
    }
    public int getPrice() {
        return price;
    }
    public Order getOrder() {
        return order;
    }
    public Product getProduct() {
        return product;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public void setOrder(Order order) {
        this.order = order;
    }
    public void setProduct(Product product) {
        this.product = product;
    }

}
