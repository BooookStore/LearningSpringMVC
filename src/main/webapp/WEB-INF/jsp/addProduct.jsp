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
		<form:form method="POST" modelAttribute="newProduct" class="form-horizontal">
			<fieldset>
				<legend>Add new Product</legend>

				<!-- productId -->
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for="productId">Product Id</label>
					<div class="col-lg-10">
						<!-- form:input タグライブラリを使用して、　path に newProduct のフィールドを記述することで、入力された情報をフィールドにセットする -->
						<form:input id="productId" path="productId" type="text" class="form:input-large" />
					</div>
				</div>

				<!-- name -->
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for="name">Name</label>
					<div class="col-lg-10">
						<form:input id="name" path="name" type="text" class="form:input-large" />
					</div>
				</div>

				<!-- unitPrice -->
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for="unitPrice">UnitPrice</label>
					<div class="col-lg-10">
						<form:input id="unitPrice" path="unitPrice" type="text" class="form:input-large" />
					</div>
				</div>

				<!-- manufacture -->
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for="manufacturer">Manufacturer</label>
					<div class="col-lg-10">
						<form:input id="manufacturer" path="manufacturer" type="text" class="form:input-large" />
					</div>
				</div>

				<!-- Category -->
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for="category">Category</label>
					<div class="col-lg-10">
						<form:input id="category" path="category" type="text" class="form:input-large" />
					</div>
				</div>

				<!-- unitsInStock -->
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for="unitsInStock">Units In Stock</label>
					<div class="col-lg-10">
						<form:input id="unitsInStock" path="unitsInStock" type="text" class="form:input-large" />
					</div>
				</div>

				<!-- unitsInOrder -->
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for="unitsInOrder">Units In Order</label>
					<div class="col-lg-10">
						<form:input id="unitsInOrder" path="unitsInOrder" type="text" class="form:input-large" />
					</div>
				</div>

				<!-- description -->
				<div class="form-group">
					<label class="control-label col-lg-2" for="description">Description</label>
					<div class="col-lg-10">
						<form:textarea id="description" path="description" row="2" />
					</div>
				</div>

				<!-- Discountinued -->
				<div class="form-group">
					<label class="control-label col-lg-2" for="discountinued">Discountinued</label>
					<div class="col-lg-10">
						<form:checkbox id="discountinued" path="discountinued" />
					</div>
				</div>

				<!-- Condition -->
				<div class="form-group">
					<label class="control-label col-lg-2" for="condition">Condition</label>
					<div class="col-lg-10">
						<form:checkbox id="condition" path="discountinued" />New
						<form:checkbox id="condition" path="discountinued" />Old
						<form:checkbox id="condition" path="discountinued" />Refurbished
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-lg-offset-2 col-lg-10">
						<input type="submit" id="btnAdd" class="btn btn-primary" value="Add" />
					</div>
				</div>
			</fieldset>
		</form:form>
	</section>
</body>
</html>