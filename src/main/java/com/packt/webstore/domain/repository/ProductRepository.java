package com.packt.webstore.domain.repository;

import java.util.List;
import java.util.Map;

import com.packt.webstore.domain.Product;

/**
 * 商品情報リポジトリに対して操作を行う。
 * 
 * @author bookstore
 *
 */
public interface ProductRepository {

	/**
	 * すべての商品情報を取得する。
	 * 
	 * @return すべての商品情報
	 */
	List<Product> getAllProducts();

	/**
	 * productIdで指定された商品情報のストック数をnoOfUnitsで書き換える。
	 * 
	 * @param productId
	 *            更新対象の商品
	 * @param noOfUnits
	 *            更新後のストック数
	 */
	void updateStock(String productId, long noOfUnits);

	/**
	 * categoryで指定されたカテゴリーの商品情報を取得する。
	 * 
	 * @param category
	 * @return 商品情報
	 */
	List<Product> getProductByCategory(String category);

	/**
	 * 商品情報をfilterParamsで指定された内容でフィルタリングし、商品情報を取得する。
	 * 
	 * @param filterParams
	 * @return
	 */
	List<Product> getProductsByFilter(Map<String, List<String>> filterParams);

	/**
	 * カテゴリーがTabletとなっている商品を、filterParamsに基づいてフィルタリングし、商品情報を取得する。
	 * 
	 * @param filterParams
	 * @return
	 */
	List<Product> getTabletByFilter(Map<String, List<String>> filterParams,String brand);

	/**
	 * productIdで指定された商品情報を取得する。
	 * 
	 * @param productId
	 * @return
	 */
	Product getProductById(String productId);

	/**
	 * 商品情報を追加する。
	 * 
	 * @param product
	 */
	void addProduct(Product product);
}
