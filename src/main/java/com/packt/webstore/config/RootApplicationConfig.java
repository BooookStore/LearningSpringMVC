package com.packt.webstore.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

/**
 * アプリケーションのルートコンテキスト
 * 
 * @author bookstore
 *
 */
@Configuration
@ComponentScan("com.packt.webstore")
public class RootApplicationConfig {

	/**
	 * 組み込みデータベースのBean設定
	 * 
	 * @return 組み込みデータベース
	 */
	@Bean
	public DataSource dataSource() {
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.HSQL)
				.addScript("db/sql/create-table.sql")
				.addScript("db/sql/insert-data.sql")
				.build();
		return db;
	}

	@Bean
	public NamedParameterJdbcTemplate getJdbcTemplate() {
		return new NamedParameterJdbcTemplate(dataSource());
	}

}
