package com.packt.webstore.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.util.UrlPathHelper;

/**
 * ウェブアプリケーションコンテキスト
 * 
 * @author honyaryousuke
 *
 */

/*
 * Beanアノテーションが付与されたメソッドがあることを宣言する。
 */
@Configuration

/*
 * ３つの重要な機能を持つ。
 * 
 * （１）このアノテーションは、ControllerやRequestMappingのアノテーションを使用するために必要になる。
 * 
 * （２）以下のBeanの設定をすることを宣言する。 1. DefaultAnnotationHandlerMapping 2.
 * AnnotationMethodHandlerAdapter 3. ExceptionHandlerExceptionResolver
 * 
 * （３）いくつかの便利なアノテーションを含んでいるため、いくつかの便利な機能が有効化される。
 */
@EnableWebMvc

/*
 * Controller,services,repositoryなどを、自動抽出する。 base-packageプロパティに指定したパッケージ配下が対象。
 */
@ComponentScan("com.packt.webstore")
public class WebApplicationContextConfig extends WebMvcConfigurerAdapter {

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	/**
	 * ハンドラマッピングのパスマッチングオプションを設定します。
	 */
	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {

		// マトリックス変数のサポートを有効化
		UrlPathHelper urlPathHelper = new UrlPathHelper();
		urlPathHelper.setRemoveSemicolonContent(false);

		configurer.setUrlPathHelper(urlPathHelper);
	}

	/**
	 * 静的ファイルをURL指定で取得できるように設定します。
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/img/**").addResourceLocations("/resources/images/");
	}

	/**
	 * NOTE : Spring MVC が正常に動作する最低限の Bean が、ViewResolverを実装すること。
	 * 
	 * @return
	 */
	@Bean
	public InternalResourceViewResolver getInternalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setViewClass(JstlView.class);
		resolver.setPrefix("/WEB-INF/jsp/");
		resolver.setSuffix(".jsp");

		return resolver;
	}

	/**
	 * リクエスト内にマルチパートコンテンツが存在するかどうかを決定し、マルチパートファイルとパラメーターをパースする。
	 * 
	 * @return
	 */
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setDefaultEncoding("UTF-8");
		return resolver;
	}

	/**
	 * JSPで使用される文字列を外部ファイルにまとめるための設定です。
	 * message.propertiesファイルから文字列を取得するよう、設定しています。
	 * 
	 * @return
	 */
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource resource = new ResourceBundleMessageSource();
		resource.setBasename("message");
		return resource;
	}

}
