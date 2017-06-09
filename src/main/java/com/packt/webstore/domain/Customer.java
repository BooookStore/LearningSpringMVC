package com.packt.webstore.domain;

import java.io.Serializable;

import lombok.Data;

/**
 * 顧客情報を保持する Bean
 * 
 * @author bookstore
 *
 */
@Data
public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;

	private String customerId;

	private String name;

	private String address;

}
