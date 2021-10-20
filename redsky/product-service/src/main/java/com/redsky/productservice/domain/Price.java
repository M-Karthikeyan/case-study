package com.redsky.productservice.domain;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Table("price")
public class Price {
	
    @PrimaryKey
    @JsonProperty("id")
    private long product_Id;
    
    @JsonProperty("current_price")
    private float price;
    
    @JsonProperty("currency_code")
    private String currency;
    
    public Price(long product_Id, float price, String currency) {
		this.product_Id = product_Id;
		this.price = price;
		this.currency = currency;
	}
    
	public long getProduct_Id() {
		return product_Id;
	}
	
	public void setProduct_Id(long product_Id) {
		this.product_Id = product_Id;
	}
	
	public float getPrice() {
		return price;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}
	
	public String getCurrency() {
		return currency;
	}
	
	public void setCurrency(String currency) {
		this.currency = currency;
	}
    
}
