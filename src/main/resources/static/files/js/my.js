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
//包含全局vue实例的js函数才可以独立出来
function showMyInfo() {//查看个人信息
		chooseShow("myInfo");
		var username = document.getElementById('reminder').text.split(":")[1];//根据reminder标签获取当前登录名
		$.ajax({
			type : "GET", //提交的方法
			url : "/api/users/others/"+username,//提交的地址 
			async : true,
			success : function(retResult) {
				var vmMyinfo = new Vue({
    				el: '#vueMyInfo',
    				data: {
        				MyBassicInfoStore: retResult,
        				MyAccountInfoStore: {"currentUsername": username},
        				show: true
    				},
    				methods:{
    					save: function(){
    						$.ajax({
										type : "PUT", //提交的方法
										url : "/api/users/others/"+username,//提交的地址 
										data : $('#updateMyInfoForm').serialize(),// 序列化表单值  
										async : true,
										success : function(retResult) { //成功
											vmMyinfo.MyBassicInfoStore = retResult;
										},
										error : function(){
											alert("update failed");
										}
								});
    						vmMyinfo.show = true;//必须在发送请求之后执行此操作，因为如果dom节点改变，ajax就获取不到表单了
    					},
    					edited: function(){
    									vmMyinfo.show = false;
								}
    					}
				});
			},
			error : function(retResult) {
				alert("Request error!");
			}
		});
	}
	function showMyClass(){//查询我的课题
		chooseShow("myClass");
		var username = document.getElementById('reminder').text.split(":")[1];//根据reminder标签获取当前登录名
		$.ajax({
			type : "GET", //提交的方法
			url : "/api/kyxm/others/"+username,//提交的地址 
			async : true,
			success : function(retResult) { 
				var vmMyClass = new Vue({
    				el: '#vueMyClass',
    				data: {
        				myClassStore: retResult
    				}
				});
				document.getElementById("myClassReminder").innerHTML = "--------以下是您的课题信息--------";
			},
			error : function(retResult) {
				alert("Request error!");
			}
		});
	}
	function showClassSquare(){//查询课题广场
		chooseShow("classSquare");
		$.ajax({
			type : "GET", //提交的方法
			url : "/api/kyxm",
			async : true,
			success : function(retResult) {
				var vmClassSquare = new Vue({
    				el: '#vueClassSquare',
    				data: {
        				classSquareStore: retResult
    				},
    				methods:{
    					addMyKyxm: function(k_id){
    									addMyKyxmA(k_id);
									}
    					}
				});
				if(retResult == "" || retResult == null){
					document.getElementById("classSquareReminder").innerHTML = "一共有0条课题记录";
				}else if(retResult instanceof Array){//返回的JSON数据可能是一个对象也可能是一个数组
					 			document.getElementById("classSquareReminder").innerHTML = "一共有"+retResult.length+"条课题记录";
						}
				},
			error : function(retResult) {
				alert("Request error!");
			}
		});
	}
	function showSearchResults() {//搜索结果
		//alert("test");
		chooseShow("searchResults");
		//直接获取input元素的值不行,因为获取到的是页面加载之后,填写表单之前的元素的值，必然为空，获取输入的值要通过form表单进行获取
		var k_name = document.getElementById("searchForm").k_name.value;
		if(k_name == "" || k_name == null){
			k_name = "blank";
		}
		$.ajax({
			type : "GET", //提交的方法
			url : "/api/kyxm/"+k_name,//提交的地址 
			async : true,
			success : function(retResult) {
				var vmSearchResults = new Vue({
    				el: '#vueSearchResults',
    				data: {
        				searchResultsStore: retResult
    				},
    				methods:{
    					addMyKyxm: function(k_id){
    									addMyKyxmA(k_id);
									}
    					}
				});
				if(retResult == "" || retResult == null){
					document.getElementById("searchResultsReminder").innerHTML = "一共有0条课题记录";
				}else if(retResult instanceof Array){//返回的一个JSON数组
					 			document.getElementById("searchResultsReminder").innerHTML = "一共有"+retResult.length+"条课题记录";
						}
				},
				error : function(retResult) {
					alert("Request error!");
				}
			});
		}