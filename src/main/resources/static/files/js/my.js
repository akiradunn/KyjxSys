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
	function sendUsersKyxmRequest(k_id){//这里出错,没有细心把每一步做好,经常容易每一小步就出纰漏,函数定义}没有加对
		$.ajax({
			type : "POST", //提交的方法
			url : "/api/usersKyxm/id/"+k_id,//提交的地址 
			async : true,
			success : function(result) { //成功
				if (result == "ok") {
					//插入成功要做的事情，把button变色且不可被选
				}
			},
			error : function(result) { //失败的话
				alert("Request error!");
			}
		});
	}