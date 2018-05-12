<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="container">
<div class="row">
    <div class="col-md-1"></div>
	<div class="col-md-10">
	<form  class="form-horizontal" role="form" id="addProductTypeForm"  method='post' enctype="multipart/form-data" action="#">
	<input type="hidden" name="shopId" value="${shopId}"/>
	<input type='hidden' name="productTypeId" value="${productTypeId}"/>
		
		<div class="form-group">
			<label for="type"  class="control-label col-sm-2">商品类型名称</label> 
			<div class="col-sm-3">
			<input type="text" class="form-control" id="type" name='productTypeName'  id="name" placeholder="请输入商品类型名称">
			</div>
		</div>
				<div class=class="form-group">
					<button type="button" class="btn btn-primary" id='addProducttype'>提交</button>
					<button type="reset" class="btn btn-primary">重置</button>
				</div>

			</form>
	</div>
	<div class="col-md-1"></div>
</div>
</div>



<script type="text/javascript" >
	var productTypeId='${productTypeId}';
	
	
	$('#addProducttype').on('click', function() {
		if (productTypeId) {
			var url = path + "/producttype/modify.do";
		} else {
			var url = path + '/producttype/add.do';
		}
		var data = $('#addProductTypeForm').serialize();
		$.ajax({
			url : url,
			data : data,
			type : 'post',
			success : function(data) {
				if (data.isSuccess) {
					var url = path + '/producttype/index.do?shopIdp=${shopId}';
					$('#pageContent').load(url);
				}
			}
		});
	});

	//编辑时回显数据
	function laodProduct() {
		alert(productTypeId);
		if (productTypeId) {
			var url = path + "/producttype/getProductType.do?productTypeId="
					+ productTypeId;
			$.ajax({
				url : url,
				type : 'get',
				success : function(data) {
					data1 = $.parseJSON(data);
					$('#type').val(data1.productTypeName);
					console.log(data);
				}
			});
		}
	}

	laodProduct();
</script>
