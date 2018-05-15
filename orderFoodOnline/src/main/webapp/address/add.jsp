<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <style type="text/css" rel="stylesheet">
  .myRow{
  margin-bottom:10px;
  margin-top: 10px;
  }
  </style>
    
<div class="container">
<div class="row myRow">
    <div class="col-md-1"></div>
	<div class="col-md-10">
	<form  class="form-horizontal" role="form" id="addAddressForm"  method='post' action="#">
	<input type="hidden" name="userId" value="${userId}"/>
	<input type='hidden' name="addressId" value="${addressId}"/>
	<input type="hidden" name="flag" value="1"/>
	
	    <div class="form-group">
			<label for="type"  class="control-label col-sm-2">省份</label> 
			<div class="col-sm-3">
			<input type="text" class="form-control" id="province" name='province'  id="province" placeholder="请输入省份">
			</div>
		</div>
		  <div class="form-group">
			<label for="type"  class="control-label col-sm-2">城市</label> 
			<div class="col-sm-3">
			<input type="text" class="form-control" id="city" name='city'  id="city" placeholder="请输入城市">
			</div>
		</div>
		
		  <div class="form-group">
			<label for="type"  class="control-label col-sm-2">详细地址</label>
			<div class="col-sm-3">
			<input type="text" class="form-control" id="detailAddress" name='detailAddress'  id="detailAddress" placeholder="请输入详细地址">
			</div>
		  </div>
		  
		  <div class="form-group">
			<label for="type"  class="control-label col-sm-2">联系方式</label>
			<div class="col-sm-3">
			<input type="text" class="form-control" id="contact" name='contact'  id="contact" placeholder="请输入联系方式">
			</div>
		  </div>
		  
		   <div class="form-group">
			<label for="type"  class="control-label col-sm-2">是否设为默认地址</label>
			<div class="col-sm-3">
			是&nbsp;<input type="radio" name="defaultAddr"  id="defaultAddrYes" value="1">
			否&nbsp;<input type="radio" id="defaultAddrNo" name='defaultAddr' checked="checked" value="-1">
			</div>
		  </div>
		
		     <div>
		     <div class="col-md-2"></div>
				<div class="form-group">
					<button type="button" class="btn btn-primary" id='addAdress'>保存</button>
					<button type="reset" class="btn btn-primary">重置</button>
				</div>
			<div class="col-md-9"></div>
			</div>

			</form>
	</div>
	<div class="col-md-1"></div>
</div>
</div>



<script type="text/javascript" >
	var addressId='${addressId}';
	
	
	$('#addAdress').on('click', function() {
		if (addressId) {
			var url = path + "/address/modify.do?flag=-1";
		} else {
			var url = path + '/address/add.do';
		}
		var data = $('#addAddressForm').serialize();
		$.ajax({
			url : url,
			data : data,
			type : 'post',
			success : function(data) {
				if (data.isSuccess) {
					var url = path + '/address/index.do?userId=${userId}';
					$('#pageContent').load(url);
				}
			}
		});
	});

	//编辑时回显数据
	
	console.log("asdfasdf"+addressId);
	function laodAddress() {
		if (addressId) {
			var url = path + "/address/getOneAddresss.do?addressId="
					+ addressId;
			$.ajax({
				url : url,
				type : 'get',
				dataType:'json',
				success : function(data) {;
					$('#province').val(data.province);
					$('#city').val(data.city);
					$('#detailAddress').val(data.detailAddress);
					$('#contact').val(data.contact);
				    $("input:radio[value='"+data.defaultAddress+"']").attr('checked','true');
				}
			});
		}
	}

	laodAddress();
</script>
