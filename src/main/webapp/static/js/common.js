//打开字滑入效果
window.onload = function(){
	$(".connect p").eq(0).animate({"left":"0%"}, 600);
	$(".connect p").eq(1).animate({"left":"0%"}, 400);
};
//jquery.validate表单验证
$(document).ready(function(){
	//登陆表单验证
	$("#loginForm").validate({
		rules:{
			phoneNumber:{
				required:true,//必填
				minlength:11, //最少6个字符
				maxlength:11,//最多11个字符
			},
			psptId:{
				required:true,
				minlength:18,//
				maxlength:18,
			},
		},
		//错误信息提示
		messages:{
			phoneNumber:{
				required:"必须填写手机号码",
				minlength:"手机号码为11位",
				maxlength:"手机号码为11位",

			},
			psptId:{
				required:"必须填写开户身份证号码",
				minlength:"请填写18位身份证号码",
				maxlength:"请填写18身份证号码",
			},
		},

	});

	//添加自定义验证规则,就是在表单之外添加验证规则
	jQuery.validator.addMethod("phoneNumber", function(value, element) {
		var length = value.length;
		var phoneNumber = /^[1][3|4|5|6|7|8|9][0-9]{9}$/;
		return this.optional(element) || (length == 11 && phoneNumber.test(value));
	}, "手机号码格式错误");


	jQuery.validator.addMethod("psptId", function(value, element) {
		var length = value.length;
		var psptId = /^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/;
		return this.optional(element) || (length == 18 && psptId.test(value));
	}, "身份证格式错误");
});
