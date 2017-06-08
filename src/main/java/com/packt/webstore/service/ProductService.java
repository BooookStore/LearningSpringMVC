package com.packt.webstore.service;

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
	
}
