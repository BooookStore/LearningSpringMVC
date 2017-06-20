<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html >
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
			<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
				<title>Products</title>
			</head>
			<body>
				<section>
					<div class="jumbotron">
						<div class="container">
							<h1>Products</h1>
							<p>Add products</p>
						</div>
					</div>
				</section>
				<section class="container">

					<!-- バインドした newProduct オブジェクトに対して、値をセットする -->
					<form:form method="POST" modelAttribute="newProduct" class="form-horizontal" enctype="multipart/form-data">
						<fieldset>
							<legend>Add new Product</legend>

							<!-- productId -->
							<div class="form-group">
								<label class="control-label col-lg-2 col-lg-2" for="productId">
									<!-- spring:messageタグを使用することで、プロパティファイルから文字列を取得することができる。 -->
									<spring:message code="addProduct.form.productId.label"/>
								</label>
								<div class="col-lg-10">
									<!-- form:input タグライブラリを使用して、　path に newProduct のフィールドを記述することで、入力された情報をフィールドにセットする -->
									<form:input id="productId" path="productId" type="text" class="form:input-large"/>
								</div>
							</div>

							<!-- name -->
							<div class="form-group">
								<label class="control-label col-lg-2 col-lg-2" for="name">
									<spring:message code="addProduct.form.name.label"/>
								</label>
								<div class="col-lg-10">
									<form:input id="name" path="name" type="text" class="form:input-large"/>
								</div>
							</div>

							<!-- unitPrice -->
							<div class="form-group">
								<label class="control-label col-lg-2 col-lg-2" for="unitPrice">
									<spring:message code="addProduct.form.unitPrice.label"/>
								</label>
								<div class="col-lg-10">
									<form:input id="unitPrice" path="unitPrice" type="text" class="form:input-large"/>
								</div>
							</div>

							<!-- manufacture -->
							<div class="form-group">
								<label class="control-label col-lg-2 col-lg-2" for="manufacturer">
									<spring:message code="addProduct.form.manufacturer.label"/>
								</label>
								<div class="col-lg-10">
									<form:input id="manufacturer" path="manufacturer" type="text" class="form:input-large"/>
								</div>
							</div>

							<!-- Category -->
							<div class="form-group">
								<label class="control-label col-lg-2 col-lg-2" for="category">
									<spring:message code="addProduct.form.category.label"/>
								</label>
								<div class="col-lg-10">
									<form:input id="category" path="category" type="text" class="form:input-large"/>
								</div>
							</div>

							<!-- unitsInStock -->
							<div class="form-group">
								<label class="control-label col-lg-2 col-lg-2" for="unitsInStock">
									<spring:message code="addProduct.form.unitsInStock.label"/>
								</label>
								<div class="col-lg-10">
									<form:input id="unitsInStock" path="unitsInStock" type="text" class="form:input-large"/>
								</div>
							</div>

							<!-- description -->
							<div class="form-group">
								<label class="control-label col-lg-2" for="description">
									<spring:message code="addProduct.form.description.label"/>
								</label>
								<div class="col-lg-10">
									<form:textarea id="description" path="description" row="2"/>
								</div>
							</div>

							<!-- Condition -->
							<div class="form-group">
								<label class="control-label col-lg-2" for="condition">
									<spring:message code="addProduct.form.condition.label"/>
								</label>
								<div class="col-lg-10">
									<form:checkbox path="condition" value="New"/>
									New
									<form:checkbox path="condition" value="Old"/>
									Old
									<form:checkbox path="condition" value="Refurbished"/>
									Refurbished
								</div>
							</div>
							
							<!-- ProductImage -->
							<div class="form-group">
								<label class="control-label col-lg-2" for="productImage">
									<spring:message code="addProduct.form.productImage.label" />
								</label>
								
								<div class="col-lg-10">
									<form:input id="productImage" path="productImage" type="file" class="form:input-large" />
								</div>
							</div>

							<div class="form-group">
								<div class="col-lg-offset-2 col-lg-10">
									<input type="submit" id="btnAdd" class="btn btn-primary" value="Add"/>
								</div>
							</div>
						</fieldset>
					</form:form>
				</section>
			</body>
		</html>
