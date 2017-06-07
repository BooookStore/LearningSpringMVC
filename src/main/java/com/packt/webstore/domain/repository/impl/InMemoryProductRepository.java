package com.packt.webstore.domain.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.packt.webstore.domain.Product;
import com.packt.webstore.domain.repository.ProductRepository;

@Repository
public class InMemoryProductRepository implements ProductRepository {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public List<Product> getAllProducts() {
		HashMap<String, Object> params = new HashMap<>();
		List<Product> result = jdbcTemplate.query("SELECT * FROM PRODUCTS", params, new ProductMapper());
		return result;
	}

	private static final class ProductMapper implements RowMapper<Product> {

		@Override
		public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
			Product product = new Product();
			product.setProductId(rs.getString("ID"));
			product.setName(rs.getString("NAME"));
			product.setDescription(rs.getString("DESCRIPTION"));
			product.setUnitPrice(rs.getBigDecimal("UNIT_PRICE"));
			product.setManufacturer(rs.getString("MANUFACTURER"));
			product.setCategory(rs.getString("CATEGORY"));
			product.setCondition(rs.getString("CONDITION"));
			product.setUnitsInStock(rs.getInt("UNITS_IN_STOCK"));
			product.setUnitsInOrder(rs.getInt("UNITS_IN_ORDER"));
			product.setDiscountinued(rs.getBoolean("DISCOUNTINUED"));
			return product;
		}

	}

}
