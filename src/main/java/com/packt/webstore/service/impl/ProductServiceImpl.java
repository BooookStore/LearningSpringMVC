package com.packt.webstore.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.packt.webstore.domain.Product;
import com.packt.webstore.domain.repository.ProductRepository;
import com.packt.webstore.service.ProductService;

/**
 * 商品情報に対して処理を行うサービスクラス
 * 
 * @author bookstore
 *
 */
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	/**
	 * 在庫数が500以下の商品情報を＋1000します。
	 */
	@Override
	public void updateAllStock() {
		List<Product> allProducts = productRepository.getAllProducts();
		for (Product product : allProducts) {
			if (product.getUnitsInStock() < 500) {
				productRepository.updateStock(product.getProductId(), product.getUnitsInStock() + 1000);
			}
		}
	}

	/**
	 * すべての商品情報を返します。
	 */
	@Override
	public List<Product> getAllProducts() {
		return productRepository.getAllProducts();
	}

	/**
	 * categoryで指定されたカテゴリーの商品情報を取得する。
	 * 
	 * @param category
	 * @return 商品情報
	 */
	@Override
	public List<Product> getProductsByCategory(String category) {
		return productRepository.getProductByCategory(category);
	}

	/**
	 * 商品をfilterParamでフィルタリングし、商品情報を返します。
	 * 
	 * @param filterParam
	 * @return
	 */
	@Override
	public List<Product> getProductByFilter(Map<String, List<String>> filterParam) {
		return productRepository.getProductsByFilter(filterParam);
	}

	/**
	 * productIdで指定された商品情報を取得する。
	 * 
	 * @param productId
	 * @return
	 */
	@Override
	public Product getProductById(String productId) {
		return productRepository.getProductById(productId);
	}

	/**
	 * 商品情報を追加する
	 * 
	 * @param product
	 */
	@Override
	public void addProduct(Product product) {
		productRepository.addProduct(product);
	}

}
