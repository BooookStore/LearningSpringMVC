package com.packt.webstore.service;

import java.util.List;

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

}
