package com.packt.webstore.service;

import java.util.List;
import java.util.Map;

import com.packt.webstore.domain.Product;

/**
 * 商品情報に対して処理を行うサービスクラス
 * 
 * @author bookstore
 *
 */
public interface ProductService {

	/**
	 * 商品の在庫数を更新します。
	 */
	void updateAllStock();

	/**
	 * すべての商品情報を返します。
	 */
	List<Product> getAllProducts();

	/**
	 * categoryで指定されたカテゴリーの商品情報を取得します。
	 * 
	 * @param category
	 * @return 商品情報
	 */
	List<Product> getProductsByCategory(String category);

	/**
	 * 商品をfilterParamでフィルタリングし、商品情報を返します。
	 * 
	 * @param filterParam
	 * @return
	 */
	List<Product> getProductByFilter(Map<String, List<String>> filterParam);

	/**
	 * productIdで指定された商品情報を取得する。
	 * 
	 * @param productId
	 * @return
	 */
	Product getProductById(String productId);

	/**
	 * 商品情報を追加する
	 * 
	 * @param product
	 */
	void addProduct(Product product);

	/**
	 * カテゴリーがTabletとなっている商品を、filterParamsに基づいてフィルタリングし、商品情報を取得する。
	 * 
	 * @param filterParams
	 * @return
	 */
	List<Product> getTabletByFilter(Map<String, List<String>> filterParams,String category, String brand);
}
