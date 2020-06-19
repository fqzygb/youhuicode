<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>领取优惠码管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">

	</script>
</head>
<body>

<!-- <form id="form" action="" method="post"> -->
<div class="aui-ver-form">
	<h2>领取优惠码</h2>
	<div class="aui-flex">
		<div class="aui-flex-box">
			<input id="serial_number" type="text" autocomplete="off" placeholder="江门联通手机号码" />
		</div>
	</div>
	<div class="aui-flex">
		<div class="aui-flex-box">
			<input id="pspt_id" type="text" autocomplete="off" placeholder="开户身份证号码"  />
		</div>
	</div>

	<div class="aui-ver-button">
		<button id="btn" >领取</button>
<%--            <button  onclick="gets()">领取 </button>--%>
		<!-- <button onClick="bind()">登录 </button> -->
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

			}
		})

	})

</script>
</body>



</html>
