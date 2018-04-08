/**
 * element-ui -version my.js
 */
//通用模块start
function checkLogin() { //这里出错,没有细心把每一步做好,经常容易每一小步就出纰漏,函数定义}没有加对
$.ajax({
		type: "POST", //提交的方法
		url: "/verifyLogin", //提交的地址 
		data: $('#loginForm').serialize(), // 序列化表单值  
		async: true,
		success: function(result) { //成功
			if (result == "success") {
				window.location.href = "/elemain.html";
			} else {
				var warning = document.getElementById('reminder');
				warning.innerHTML = "账号或密码错误";
				warning.style.color = "red";
			}
		},
		error: function(result) { //失败的话
			alert("Request error!");
		}
	});
}
function chooseShow(show) { //页面切换
	var status = document.getElementById(show).style.display;
	if (status == "none") {
		document.getElementById("myInfo").style.display = "none";
		document.getElementById("myClass").style.display = "none";
		document.getElementById("classSquare").style.display = "none";
		document.getElementById("searchRegion").style.display = "none";
		document.getElementById("searchResults").style.display = "none";
		document.getElementById(show).style.display = "";
	}
}
function logout() {//退出登录
	window.location.href='/logout'
}
//通用模块end

//我的信息模块start
function showMyInfoElement() {//用户查看个人信息
	chooseShow("myInfo");
	$.ajax({
		type:"GET",
		url: "/api/users/now_users",
		async: true,
		success: function(retResult) {
			vueMain.myInfoFormData = retResult;
		},
		error: function(retResult) {
			alert("Request error");
		}
	})
}
function editMyInfo() {//用户编辑个人信息
	vueMain.show = true;
}
function saveMyInfo() {//用户保存个人信息
	$.ajax({
		 type: "PUT", //提交的方法
		 url: "/api/addition/update_usersvisit", //提交的地址 
		 async: true,
		 data: vueMain.myInfoFormData,
		 success: function(retResult) { //成功
		 	vueMain.myInfoFormData = retResult;
		 	window.location.href='/logout';
		 },
		 error: function() {
		 	alert("update failed");
		 }
		});
		vueMain.show = false; //必须在发送请求之后执行此操作，因为如果dom节点改变，ajax就获取不到表单了
}
//我的信息模块end


//我的课题模块start
function showMyClassElement() {//用户查看我的课题
	chooseShow("myClass");
	$.ajax({
		type:"GET",
		url: "/api/usersKyxm/myclass",
		async: true,
		success: function(retResult) {
			vueMain.myClassTableData = retResult;
		},
		error: function(retResult) {
			alert("Request error");
		}
	})
}
function applySetMyKyxm(k_id) {//用户申请课题结项
	$.ajax({
		type: "PUT", //提交的方法
		url: "/api/userkyxm/applySetMyKyxm/" + k_id, //提交的地址 
		async: true,
		success: function(retResult) {
			if (retResult == "success") {
				console.log("applySetMyKyxm kyxm : "+k_id+" ok!");
			}else{
				console.log("applySetMyKyxm kyxm : "+k_id+" failed!");
			}
		},
		error: function(retResult) {
			console.log("applySetMyKyxm kyxm : "+k_id+" failed!");
			alert("Request error!");
		}
	});
}
function applyMyKyxmScore(k_id) {//用户申请课题积分
	$.ajax({
		type: "PUT", //提交的方法
		url: "/api/userkyxm/applyMyKyxmScore/" + k_id, //提交的地址 
		async: true,
		success: function(retResult) {
			if (retResult == "success") {
				console.log("applyMyKyxmScore kyxm : "+k_id+" ok!");
			}else{
				console.log("applyMyKyxmScore kyxm : "+k_id+" failed!");
			}
		},
		error: function(retResult) {
			console.log("applyMyKyxmScore kyxm : "+k_id+" failed!");
		}
	});
}
//我的课题模块end

//课题广场模块start
function showClassSquareElement() {//用户查看课题广场所有课题
	chooseShow("classSquare");
	$.ajax({
		type:"GET",
		url: "/api/kyxm",
		async: true,
		success: function(retResult) {
			vueMain.classSquareTableData = retResult;
		},
		error: function(retResult) {
			alert("Request error");
		}
	})
}
function addMyKyxm(k_id) {//用户添加课题至我的列表-课题广场,搜索模块
	$.ajax({
		type:"POST",
		url: "/api/usersKyxm/addMyKyxm/" + k_id,
		async: true,
		success: function(retResult) {
			if (retResult == "success") {
				console.log("apply kyxm : "+k_id+" ok!");
			}
		},
		error: function(retResult) {
			console.log("apply kyxm : "+k_id+" failed!");
		}
	})
}
//课题广场模块end

//搜索课题模块start
function searchKyxm() {
	//默认一切操作搜索结果页面都是不显示的，在进入搜索页面的时候没有搜索结果表格显示，当用户执行搜索时就会显示表格
	var k_name = vueMain.searchForm.k_name;
	console.log("searching kyxm:"+k_name+"~");
	$.ajax({
		type:"GET",
		url: "/api/kyxm/" + k_name,
		async: true,
		success: function(retResult) {
			vueMain.searchResultsTableData = retResult;
			document.getElementById("searchResults").style.display = "";
		},
		error: function(retResult) {
			alert("Request error");
		}
	})
}
//搜索课题模块end