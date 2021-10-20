package com.redsky.productservice.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;

public class ProductDTO {
	@JsonIgnore
	@JsonProperty("data")
	private JsonNode data;

	@JsonProperty("id")
	private long id;

	@JsonProperty("name")
	private String name;

	@JsonProperty("current_price")
	private float price;

	@JsonProperty("currency_code")
	private String currency;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonIgnore
	public JsonNode getData() {
		return data;
	}

	public void setData(JsonNode data) {
		this.setId(Long.parseLong(data.get("product").get("tcin").textValue()));
		this.setName(data.get("product").get("item").get("product_description").get("title").textValue());
		this.data = data;
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

	@JsonCreator
	private static void Create(JsonNode node) throws JsonMappingException {
		JsonNode _n = node;
	}

	@Override
	public String toString() {
		return "ProductDTO{" + "data=" + data + ", id=" + id + ", price=" + price + ",  name='" + name + '\'' + '}';
	}
}