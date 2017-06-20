package com.packt.webstore.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	private String productId;

	private String name;

	private BigDecimal unitPrice;

	private String description;

	private String manufacturer;

	private String category;

	private long unitsInStock;

	private long unitsInOrder;

	private boolean discountinued;

	private String condition;
	
	private MultipartFile productImage;

	public Product() {
		super();
	}

	public Product(String productId, String name, BigDecimal unitPrice) {
		this.productId = productId;
		this.name = name;
		this.unitPrice = unitPrice;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (productId == null) {
			if (other.productId != null)
				return false;

		} else if (!productId.equals(other.productId))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((productId == null) ? 0 : productId.hashCode());
		return result;
	}

}
