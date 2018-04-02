/**
 * 
 */
	function checkLogin() {//这里出错,没有细心把每一步做好,经常容易每一小步就出纰漏,函数定义}没有加对
		$.ajax({
			type : "POST", //提交的方法
			url : "/verifyLogin",//提交的地址 
			data : $('#loginForm').serialize(),// 序列化表单值  
			async : true,
			success : function(result) { //成功
				if (result == "success") {
					window.location.href = "/main.html";
				} else {
					var warning = document.getElementById('reminder');
					warning.innerHTML = "账号或密码错误";
					warning.style.color = "red";
				}
			},
			error : function(result) { //失败的话
				alert("Request error!");
			}
		});
	}
	function checkUserRole(){
		        $.ajax({
					type : "GET", //提交的方法
					url : "/checkUserRole",//提交的地址  
					async : true,
					success : function(result) { //成功
						checkRoleDeal(result);
					},
					error : function(result) { //失败的话
						alert("Request error!");
					}
				});
	}
	function checkRoleDeal(result) {//检查身份角色
		var warning = document.getElementById('reminder');
		var user = result;
		if (user.role == "ROLE_ADMIN") {
			warning.innerHTML = "管理员:" + user.username;
		} else {
			warning.innerHTML = "用户:" + user.username;
		}
		warning.style.color = "red";
	}
	function chooseShow(show) {//页面切换
		var status = document.getElementById(show).style.display;
		if(status == "none"){
			document.getElementById("myInfo").style.display = "none";
			document.getElementById("myClass").style.display = "none";
			document.getElementById("classSquare").style.display = "none";
			document.getElementById("searchResults").style.display = "none";
			document.getElementById(show).style.display = "";	
		}
	}
	function addMyKyxmA(k_id) {
    		// alert("hello");
    		$.ajax({
			type : "POST", //提交的方法
			url : "/api/usersKyxm/id/"+k_id,//提交的地址 
			async : true,
			success : function(retResult) {
				if(retResult == "add ok"){
					alert("add ok!");
				}
			},
			error : function(retResult) {
				console.log(retResult);
				alert("Request error!");
			}
		});
	}