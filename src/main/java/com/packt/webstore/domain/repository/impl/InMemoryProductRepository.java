package com.packt.webstore.domain.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.packt.webstore.domain.Product;
import com.packt.webstore.domain.repository.ProductRepository;

/**
 * 組み込みデータベース、HSQLのプロダクトテーブルに対するデータベースアクセスクラス
 * 
 * @author bookstore
 *
 */
@Repository
public class InMemoryProductRepository implements ProductRepository {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	/**
	 * すべての商品情報を取得する。
	 * 
	 * @return すべての商品情報
	 */
	@Override
	public List<Product> getAllProducts() {
		HashMap<String, Object> params = new HashMap<>();

		List<Product> result = jdbcTemplate.query("SELECT * FROM PRODUCTS", params, new ProductMapper());
		return result;
	}

	/**
	 * productIdで指定された商品情報のストック数をnoOfUnitsで書き換える。
	 * 
	 * @param productId
	 *            更新対象の商品
	 * @param noOfUnits
	 *            更新後のストック数
	 */
	@Override
	public void updateStock(String productId, long noOfUnits) {

		// 実行対象のSQL文
		String SQL = "UPDATE PRODUCTS SET UNITS_IN_STOCK = :unitsInStock WHERE ID = :id";

		// SQLのプレースホルダーの値を決定するマップを作成
		Map<String, Object> param = new HashMap<>();
		param.put("unitsInStock", noOfUnits);
		param.put("id", productId);

		// 更新処理
		jdbcTemplate.update(SQL, param);
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
