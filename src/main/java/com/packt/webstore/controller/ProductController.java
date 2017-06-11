package com.packt.webstore.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.packt.webstore.domain.Product;
import com.packt.webstore.service.ProductService;

@Controller
@RequestMapping("market")
public class ProductController {

	@Autowired
	private ProductService productService;

	/**
	 * 全商品情報を表示します
	 * 
	 * @param model
	 * @return 商品情報のビューを指すURL
	 */
	@RequestMapping("/products")
	public String list(Model model) {
		model.addAttribute("products", productService.getAllProducts());
		return "products";
	}

	/**
	 * 商品のストックを返し、全商品情報を表示します
	 * 
	 * @param model
	 * @return リダイレクト先URL
	 */
	@RequestMapping("/update/stock")
	public String updateStock(Model model) {
		productService.updateAllStock();
		return "redirect:/market/products";
	}

	/**
	 * カテゴリーに基づいて商品情報を取得し、表示します
	 * 
	 * @param model
	 * @param productCategory
	 * @return
	 */
	@RequestMapping("/products/{category}")
	public String getProductsByCategory(Model model, @PathVariable("category") String productCategory) {
		model.addAttribute("products", productService.getProductsByCategory(productCategory));
		return "products";
	}

	/**
	 * 商品情報をフィルタリングし、表示します
	 * 
	 * @param filterParams
	 * @param model
	 * @return
	 */
	@RequestMapping("/products/filter/{params}")
	public String getProductsByFilter(
			@MatrixVariable(pathVar = "params") Map<String, List<String>> filterParams,
			Model model) {

		model.addAttribute("products", productService.getProductByFilter(filterParams));
		return "products";
	}

	/**
	 * Tabletの情報をフィルタリングし、表示します。
	 * 
	 * フィルタリング内容 low : 最低価格 high : 最高価格 brand : ブランド名
	 * 
	 * @param filterParams
	 * @return
	 */
	@RequestMapping("/products/{category}/price/{params}")
	public String filterProduct(@PathVariable String category,
			@MatrixVariable(pathVar = "params") Map<String, List<String>> filterParams,
			@RequestParam("brand") String brand,
			Model model) {

		System.out.println(category);
		
		filterParams.entrySet().stream().forEach(e -> {
			System.out.println(e.getKey() + ":" + e.getValue());
		});
		
		model.addAttribute("products", productService.getTabletByFilter(filterParams, brand));

		return "products";
	}

	/**
	 * productId で指定された商品情報の購入画面を表示します。
	 * 
	 * @param productId
	 * @param model
	 * @return
	 */
	@RequestMapping("/product")
	public String getProductById(@RequestParam("id") String productId, Model model) {
		model.addAttribute("product", productService.getProductById(productId));
		return "product";
	}

	/**
	 * 商品情報を追加するフォームを表示する。
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/products/add", method = RequestMethod.GET)
	public String getAddNewProductForm(Model model) {
		Product product = new Product();
		model.addAttribute("newProduct", product);
		return "addProduct";
	}

	/**
	 * 商品情報を追加し、すべての商品情報を表示する。
	 * 
	 * @param product
	 * @return
	 */
	@RequestMapping(value = "/products/add", method = RequestMethod.POST)
	public String proccessAddNewProduct(@ModelAttribute("newProduct") Product product, BindingResult result) {

		// 許可されていないデータがバインドされていた場合、例外を発生させる。
		String[] suppressedFields = result.getSuppressedFields();
		if (suppressedFields.length > 0) {
			throw new RuntimeException("Attempting to bind desallowed fields: " +
					StringUtils.arrayToCommaDelimitedString(suppressedFields));
		}

		// 商品情報を追加
		productService.addProduct(product);

		return "redirect:/market/products";
	}

	/**
	 * WebDataBinderにより、バインドするデータの設定を行う。
	 * 
	 * @param webDataBinder
	 */
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {

		// 列挙したパラメータ変数のバインドを許可する
		// 許可されていないパラメータ変数がリクエストパラメータとして送信されてきた場合は
		// BindingResultに詳細データをセットする
		// NOTE : setDisallowedFieldsメソッドを使用して、許可しない変数を列挙することもできる
		webDataBinder.setAllowedFields("productId",
				"name",
				"unitPrice",
				"description",
				"manufacturer",
				"category",
				"unitsInStock",
				"condition");

		// WebDateは、HTTPパラメータ変数を java.beans.PropertyEditor を使用して
		// ターゲットとなる型に変換できる。
		// 以下はDateクラスに変換する例
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, YYYY");
		CustomDateEditor orderDateEditor = new CustomDateEditor(dateFormat, true);
		webDataBinder.registerCustomEditor(Date.class, orderDateEditor);
	}
}
