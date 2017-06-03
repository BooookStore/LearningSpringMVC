package com.packt.webstore.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * デフォルトのディスパッチャーサーブレット。
 * 
 * @author honyaryousuke
 * @since 2017/06/03
 */
public class DispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return null;
	}

	/**
	 * ディスパッチャーサーブレットのアプリケーションコンテキストを設定。
	 * 
	 * ディスパッチャーサーブレットに対して設定クラスを設定し、
	 * コントローラークラスとviewファイルを設定する。
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { WebApplicationContextConfig.class };
	}

	/**
	 * サーブレットマッピングを設定
	 */
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" }; // ウェブアプリ全体に対するリクエストを処理すると設定
	}

}
