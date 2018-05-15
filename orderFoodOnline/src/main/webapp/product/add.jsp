<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="container">
<div class="row">
	<div class="col-md-12">
	<form  class="form-horizontal" role="form" id="addProductForm"  method='post' enctype="multipart/form-data">
	<input type="hidden" name="shopId" value="${shopId}"/>
	<input type='hidden' name="productId" value="${productId}"/>
		<div class="form-group">
			<label for="name" class="control-label col-sm-2">商品名称</label> 
		<div class="col-sm-3">
		<input type="text" class="form-control" id='name' name='productName' placeholder="请输入商品名称">
		</div>
		</div>
		
		<div class="form-group">
			<label for="type"  class="control-label col-sm-2">商品类型</label> 
			<div class="col-sm-3">
			<select name="productTypeId" id="type">
			<option value="-1">请选择商品类型</option>
			</select>
			
			</div>
		</div>

            
			<div class="form-group">
				<label for="productImg" class="control-label control-label col-sm-2">商品图片</label>
				<div class="col-sm-3">
					<input type="file" name="picture" id="productImg"
						placeholder="请上传商品图片">
				</div>
			</div>

<div class="form-group">
		<label for="stock" class="control-label col-sm-2" placeholder="请输入库存">库存</label>
		<div class="col-sm-3">
			<input type="text" id="stock" name="stock">
		</div>
	</div>

	<div class="form-group">
		<label for="price" class="control-label col-sm-2" placeholder="请输入原价">原价</label>
		<div class="col-sm-3">
			<input type="text" id="price" name="price">元
		</div>
	</div>

				<div class="form-group">
					<label for="discount" class="control-label control-label col-sm-2">折扣</label>
					<div class="col-sm-3">
						<input type="text" id="discount" name='discount'
							placeholder="请输入折扣（百分比）"><span>%</span>
					</div>
				</div>
			
				<div class=class="form-group">
				<div class="col-md-2"></div>
				<div class="col-md-2">
				<button type="button" class="btn btn-primary col-sm-offset-2"
						id='addProduct'>保存</button>
					<button type="reset" class="btn btn-primary col-sm-offset-2">重置</button>
					<!-- <button type="button" class="btn btn-primary col-sm-offset-2" onclick="returnList()">返回</button> -->
				</div>
				</div>
                <div class="col-md-8"></div>
			</form>
	</div>
</div>
</div>



<script type="text/javascript" >
	var productId='${productId}';
	$('#addProduct').on('click',function(){
		if(productId){
			var  url=path+"/product/modify.do?";
		}
		else {
			var  url=path+'/product/add.do';
		}
		var data=new FormData($('#addProductForm')[0]);
	$.ajax({
		url:url,
		data:data,
		type:'post',
		processData:false,
		contentType:false,
		success:function(data){
			if(data.isSuccess){
				var url=path+'/product/index.do';
				$('#pageContent').load(url);
			}

		}
	});
	});	
	

	
	//编辑时回显数据
	
	function laodProduct() {
		if (productId) {
			var url = path + "/product/getProduct.do?productId=" + productId;
			$.ajax({
						url : url,
						type : 'get',
						success : function(data) {
							data = $.parseJSON(data);
							$('#name').val(data.productName);
							$('#type').val(data.productName);
							$('#price').val(data.price);
							$('#discount').val(data.discount);
							$('#stock').val(data.stock);
							$.ajax({
										url : path+ '/producttype/getProductType.do?productTypeId='+data.productTypeId,
										dataType : 'json',
										success : function(res) {
											$('#type').val(res.productTypeId);
										}
									});
						}
					});
		}
	}

	//加载商品类型
	function loadProductType() {
		var url = path + '/producttype/list.do?shopId=${shopId}';
		$.ajax({
			url : url,
			dataType : 'json',
			data:{
			pageSize:0,
			pageNumber:0
			},
			success : function(data) {
				var list = data.rows;
				var options = "";
				var length = list.length;
				for (var i = 0; i < length; i++) {
					options = options
							+ ' <option value=\''+list[i].productTypeId+'\'>'
							+ list[i].productTypeName + '</option>';
				}
				console.log("op" + options);
				$('#type').append(options);
			}
		});

	}
	loadProductType();

	laodProduct();
/* 	function returnList(){
		var url=
		$('#pageContent').load(url);
	} */
</script>
