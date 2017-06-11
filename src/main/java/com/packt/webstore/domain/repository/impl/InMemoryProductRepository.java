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
	 * categoryで指定されたカテゴリーの商品情報を取得する。
	 * 
	 * @param category
	 * @return 商品情報
	 */
	@Override
	public List<Product> getProductByCategory(String category) {
		String sql = "SELECT * FROM products WHERE category = :category";

		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("category", category);

		return jdbcTemplate.query(sql, param, new ProductMapper());
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

	/**
	 * 商品情報をfilterParamsで指定された内容でフィルタリングし、商品情報を取得する。
	 * 
	 * @param filterParams
	 * @return
	 */
	@Override
	public List<Product> getProductsByFilter(Map<String, List<String>> filterParams) {
		String sql = "SELECT * FROM products WHERE category IN ( :categories ) AND MANUFACTURER IN ( :brands )";
		return jdbcTemplate.query(sql, filterParams, new ProductMapper());
	}

	/**
	 * productIdで指定された商品情報を取得する。
	 * 
	 * @param productId
	 * @return
	 */
	@Override
	public Product getProductById(String productId) {
		String sql = "SELECT * FROM products WHERE id = :id";
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("id", productId);
		return jdbcTemplate.queryForObject(sql, param, new ProductMapper());
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

	/**
	 * 商品情報を追加する。
	 * 
	 * @param product
	 */
	@Override
	public void addProduct(Product product) {

		// SQL文を用意
		String sql = "INSERT INTO PRODUCTS (ID, "
				+ "NAME,"
				+ "DESCRIPTION,"
				+ "UNIT_PRICE,"
				+ "MANUFACTURER,"
				+ "CATEGORY,"
				+ "CONDITION,"
				+ "UNITS_IN_STOCK,"
				+ "UNITS_IN_ORDER,"
				+ "DISCOUNTINUED) "
				+ "VALUES (:id, :name, :desc, :price, :manufacture, :category, :condition, :inStock,"
				+ " :inOrder, :discountinued)";

		// プレースホルダーの内容を用意
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id", product.getProductId());
		param.put("name", product.getName());
		param.put("desc", product.getDescription());
		param.put("price", product.getUnitPrice());
		param.put("manufacture", product.getManufacturer());
		param.put("category", product.getCategory());
		param.put("condition", product.getCondition());
		param.put("inStock", product.getUnitsInStock());
		param.put("inOrder", product.getUnitsInOrder());
		param.put("discountinued", product.isDiscountinued());

		// 内容を更新
		jdbcTemplate.update(sql, param);

	}

	/**
	 * カテゴリーがTabletとなっている商品を、filterParamsに基づいてフィルタリングし、商品情報を取得する。
	 * 
	 * @param filterParams
	 * @return
	 */
	@Override
	public List<Product> getTabletByFilter(Map<String, List<String>> filterParams, String brand) {
		String sql = "SELECT * FROM products WHERE category = 'Tablet' "
				+ "AND manufacturer = :brand AND unit_price BETWEEN :low AND :high";

		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("low", filterParams.get("low"));
		params.put("high", filterParams.get("high"));
		params.put("brand", brand);
		
		return jdbcTemplate.query(sql, params, new ProductMapper());
	}

}
