<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>领取优惠码管理</title>
	<meta name="decorator" content="default"/>
	<meta name="viewport" content="width=device-width, initial-scale=1.4, minimum-scale=1.0, maximum-scale=2.0"/>
	<script type="text/javascript">

	</script>
</head>
<body>


<!-- <form id="form" action="" method="post"> -->

<div class="aui-ver-form" align="center"  style="background: url(${ctxStatic}/static/images/success.jpg);background-size:100% 100%;height:600px;" >
	<h2>领取优惠码</h2>
	<div class="aui-flex" align="center">
		<div class="aui-flex-box" >
			<label class="control-label">手机号码：</label>
			<input id="serial_number" type="text" autocomplete="off" placeholder="江门联通手机号码" />
		</div>
	</div>
	<div class="aui-flex" align="center">
		<div class="aui-flex-box">
			<label class="control-label">证件号码：</label>
			<input id="pspt_id" type="text" autocomplete="off" placeholder="开户身份证号码"  />
		</div>
	</div>

	<div class="aui-ver-button" align="center">
		<button id="btn" style="color:blue;text-align:center">领取</button>
<%--            <button  onclick="gets()">领取 </button>--%>
		<!-- <button onClick="bind()">登录 </button> -->
	</div>

	<div id="msgContent">

	</div>

</div>
	<div class="pagination">${page}</div>
<script type="text/javascript">

	$("#btn").click(function () {
		//console.log("aaaaaaaaa");
		//alert("bbbbbbbb");

		var serial_number = $("#serial_number").val();
		var pspt_id = $("#pspt_id").val();

		//alert(serial_number);
		//alert(pspt_id);
		var reg =/^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/;
		if(!reg.test(pspt_id)){
			alert("身份证号码有误，请重填");
			return false;
		}

		if(!(/^[1][3|4|5|6|7|8|9][0-9]{9}$/.test(serial_number))) {
			alert("手机号码有误，请重填");
			return false;
		}

		$.ajax({
			type:"POST",
			dateType:"JSON",
			url:"${ctx}/youhuicode/userInfo/code1",
			data:{
				"serial_number":serial_number,
				"pspt_id":pspt_id,
				//"type":type
			},
			//contentType: "application/json;charset=UTF-8",
			success:function(data){
				//这里的data，就是你重后台传来的那个对象，根据这个对象的那个flg，你就能判断到底是哪种情况了msg?是的好的，
				console.log(data);
				var flg =  data.flg;
				var msgContent = data.msgContent;
				console.log(flg);
				console.log(msgContent);
				//这里不就拿到了那个状态值和那个码了吗，然后根据那个状态值，去判断，根据不同的状态给出不同的提示。
				//那前面那些正则表达式的提示还要吗？要啊，那个直接就是，这个是后台处理之后？我先打个电话
				//就和你


				var html = "";
				html +="<span  style=\"color:red;font-size: 20px\">"+msgContent+"</span>"
				$("#msgContent").html(html);  //在html页面id=msgContent的标签里显示html内容

				//flg :0 未领取，1 已领取 ，2 用户信息有误  ，3 活动未开始 ， 4活动已结束 ，5 码已领完
				if(flg === "0"){
				 	alert("领取成功,您的优惠码为："+msgContent)
				}
				 if(flg === '1'){
				 	alert(msgContent)
				 }
				 if(flg === '2'){
				 	alert(msgContent)
				 }
				 if(flg === '3'){
				 	alert(msgContent)
				 }
				 if(flg === '4'){
				 	alert(msgContent)
				 }
				 if(flg === '5'){
				 	alert(msgContent)
				 }
				 if(flg === '-1'){
				 	alert(msgContent)
				 }

			}
		})

	})

</script>
</body>



</html>
