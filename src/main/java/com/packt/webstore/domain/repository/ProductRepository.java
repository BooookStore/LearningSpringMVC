package com.packt.webstore.domain.repository;

import java.util.List;

import com.packt.webstore.domain.Product;

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
	 * @param productId 更新対象の商品
	 * @param noOfUnits 更新後のストック数
	 */
	void updateStock(String productId, long noOfUnits);

}
