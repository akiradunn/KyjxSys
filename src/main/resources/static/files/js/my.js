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
				window.location.href = "/main.html";
			} else {
				var warning = document.getElementById('reminder');
				warning.innerHTML = "账号或密码错误";
				warning.style.color = "red";
			}
		},
		error: function(result) { //失败的话
			alert("网络问题!");
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
//-------------------------用户模块start---------------------------
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
			console.log("请检查网络问题!");
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
			}else{
				console.log("apply kyxm : "+k_id+" failed!");
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

//-------------------------用户模块end---------------------------





//-------------------------管理员模块start---------------------------
function chooseShowAdmin(show) { //页面切换
	var status = document.getElementById(show).style.display;
	if (status == "none") {
		document.getElementById("applyingKyxmListSection").style.display = "none";
		document.getElementById("applyingCompletingKyxmListSection").style.display = "none";
		document.getElementById("applyingScoreKyxmListSection").style.display = "none";
		document.getElementById("visitListSection").style.display = "none";
		document.getElementById("editVisitSection").style.display = "none";
		document.getElementById("kyxmListSection").style.display = "none";
		document.getElementById("editKyxmSection").style.display = "none";
		document.getElementById(show).style.display = "";
	}
}

//审批课题立项start
function admin_getApplyingKyxmList() {//管理员查看课题申报列表
	$.ajax({
		type:"GET",
		url: "/api/userkyxm/getApplyingKyxmList",
		async: true,
		success: function(retResult) {
			vueMain.applyingKyxmTableData = retResult;
		},
		error: function(retResult) {
			alert("Request error");
		}
	})
}
function showApplyingKyxmListSection() {//切换页面至审核课题立项模块
	chooseShowAdmin("applyingKyxmListSection");
	admin_getApplyingKyxmList();
}
function passApplyingKyxm(k_id) {
	$.ajax({
		type:"PUT",
		url: "/api/userkyxm/passApplyingKyxm/" + k_id,
		async: true,
		success: function(retResult) {
			vueMain.applyingKyxmTableData = retResult;
			console.log("通过课题:"+k_id+"的立项申请!");
		},
		error: function(retResult) {
			console.log("课题:"+k_id+"的立项申请处理异常!");
		}
	})
}
function rejectApplyingKyxm(k_id) {
	$.ajax({
		type:"PUT",
		url: "/api/userkyxm/rejectApplyingKyxm/" + k_id,
		async: true,
		success: function(retResult) {
			vueMain.applyingKyxmTableData = retResult;
			console.log("拒绝课题:"+k_id+"的立项申请!");
		},
		error: function(retResult) {
			console.log("课题:"+k_id+"的立项申请处理异常!");
		}
	})
}
//审批课题立项end

//审批课题结项start
function admin_getApplyingCompletingKyxmList() {
	$.ajax({
		type:"GET",
		url: "/api/userkyxm/getApplyingCompletingKyxmList",
		async: true,
		success: function(retResult) {
			vueMain.applyingCompletingKyxmTableData = retResult;
		},
		error: function(retResult) {
			alert("Request error");
		}
	})
}
function showApplyingCompletingKyxmListSection() {//切换页面至审核课题结项模块
	chooseShowAdmin("applyingCompletingKyxmListSection");
	admin_getApplyingCompletingKyxmList();
}
function passApplyingCompletingKyxm(k_id) {
	$.ajax({
		type:"PUT",
		url: "/api/userkyxm/passApplyingCompletingKyxm/" + k_id,
		async: true,
		success: function(retResult) {
			vueMain.applyingCompletingKyxmTableData = retResult;
			console.log("通过课题:"+k_id+"的结项申请!");
		},
		error: function(retResult) {
			console.log("课题:"+k_id+"的结项申请处理异常!");
		}
	})
}
function rejectApplyingCompletingKyxm(k_id) {
	$.ajax({
		type:"PUT",
		url: "/api/userkyxm/rejectApplyingCompletingKyxm/" + k_id,
		async: true,
		success: function(retResult) {
			vueMain.applyingCompletingKyxmTableData = retResult;
			console.log("拒绝课题:"+k_id+"的结项申报申请!");
		},
		error: function(retResult) {
			console.log("课题:"+k_id+"的结项申请处理异常!");
		}
	})
}
//审批课题结项end

//审批课题积分start
function admin_getApplyingScoreKyxmList() {
	$.ajax({
		type:"GET",
		url: "/api/userkyxm/getApplyingScoreKyxmList",
		async: true,
		success: function(retResult) {
			vueMain.applyingScoreKyxmTableData = retResult;
		},
		error: function(retResult) {
			alert("Request error");
		}
	})
}
function showApplyingScoreKyxmListSection() {//切换页面至审核课题积分模块
	chooseShowAdmin("applyingScoreKyxmListSection");
	admin_getApplyingScoreKyxmList();
}
function passApplyingScoreKyxm(k_id) {
	$.ajax({
		type:"PUT",
		url: "/api/userkyxm/passApplyingScoreKyxm/" + k_id,
		async: true,
		success: function(retResult) {
			vueMain.applyingScoreKyxmTableData = retResult;
			console.log("通过课题:"+k_id+"的积分申请!");
		},
		error: function(retResult) {
			console.log("课题:"+k_id+"的积分申请处理异常!");
		}
	})
}
function rejectApplyingScoreKyxm(k_id) {
	$.ajax({
		type:"PUT",
		url: "/api/userkyxm/rejectApplyingScoreKyxm/" + k_id,
		async: true,
		success: function(retResult) {
			vueMain.applyingCompletingKyxmTableData = retResult;
			console.log("拒绝课题:"+k_id+"的结项申报申请!");
		},
		error: function(retResult) {
			console.log("课题:"+k_id+"的结项申请处理异常!");
		}
	})
}
//审批课题积分end


//用户模块start
function admin_getVisitList() {
	$.ajax({
		type:"GET",
		url: "/api/visit/getVisitList",
		async: true,
		success: function(retResult) {
			vueMain.visitTableData = retResult;
			console.log("获取所有visit列表!");
		},
		error: function(retResult) {
			console.log("获取所有visit列表失败");
		}
	})
}
function showVisitListSection() {
	chooseShowAdmin("visitListSection");
	admin_getVisitList();
}
function editVisit(u_id,username) {
	vueMain.visitItem_oldusername = username;
	$.ajax({
		type:"GET",
		url: "/api/visit/editVisit/"+u_id+"&&"+username,
		async: true,
		success: function(retResult) {
			chooseShowAdmin("editVisitSection");
			vueMain.visitItemFormData = retResult;
			vueMain.visitItem_oldusername = username;
			console.log("编辑visit:"+u_id+" "+username+" ok!");
		},
		error: function(retResult) {
			console.log("编辑visit:"+u_id+" "+username+" 失败");
		}
	})
}
function cancelEditVisit() {
	vueMain.markadd = false;
	chooseShowAdmin("visitListSection");
}
function saveEditVisit() {//markadd为true表示添加状态,用户访问visitListSection页面是通过add函数进入,false表示为更新状态,
						  //用户是通过edit函数进入visitListSection页面
	$.ajax({
		 type: "PUT", //提交的方法
		 url: "/api/visit/saveEditVisit&&"+vueMain.visitItem_oldusername+"&&"+vueMain.markadd, //提交的地址 
		 async: true,
		 data: vueMain.visitItemFormData,//表单的数据在输入后自动获取
		 success: function(retResult) { //成功
		 	chooseShowAdmin("visitListSection");
		 	vueMain.visitTableData = retResult;
		 	vueMain.markadd = false;
		 	console.log("原始的username="+vueMain.visitItem_oldusername);
		 	console.log("保存账号信息成功!");
		 },
		 error: function() {
		 	chooseShowAdmin("visitListSection");
		 	vueMain.markadd = false;
		 	console.log("保存账号信息失败!");
		 }
		});
}

function deleteVisit(u_id,username) {
	$.ajax({
		 type: "DELETE", //提交的方法
		 url: "/api/visit/deleteVisit/"+u_id+"&&"+username, //提交的地址 
		 async: true,
		 success: function(retResult) { //成功
		 	vueMain.visitTableData = retResult;
		 	console.log("删除账号: "+u_id+" "+username+" ok!");
		 },
		 error: function() {
		 	console.log("删除账号信息失败!");
		 }
		});
}

function addVisit() {
	vueMain.markadd = true;
	vueMain.visitItemFormData = {"u_id":0,"r_id":2,"username":"请输入新的用户名","password":"请输入新的密码"};
	chooseShowAdmin("editVisitSection");
}
//用户模块end


//课题管理模块start
function showKyxmListSection() {
	chooseShowAdmin("kyxmListSection");
	admin_getKyxmList();
}
function admin_getKyxmList() {
	$.ajax({
		type:"GET",
		url: "/api/kyxm",
		async: true,
		success: function(retResult) {
			vueMain.kyxmListTableData = retResult;
		},
		error: function(retResult) {
			alert("Request error");
		}
	});
}
function editKyxm(k_id) {
	$.ajax({
		type:"GET",
		url: "/api/kyxm/editKyxm/"+k_id,
		async: true,
		success: function(retResult) {
			chooseShowAdmin("editKyxmSection");
			if (retResult=="" || retResult=='' || retResult==null) {//添加状态
				vueMain.kyxmItemFormData = {"k_id":0,"k_name":"请输入新的课题名称","k_category":"请输入课题类别","k_score":0}
			}else{
				vueMain.kyxmItemFormData = retResult;
			}
			console.log("正在编辑课题:"+k_id+"!");
		},
		error: function(retResult) {
			console.log("编辑课题:"+k_id+"失败!");
		}
	});
}
function cancelEditKyxm() {
	chooseShowAdmin("kyxmListSection");
	vue.markadd = false;
}
function saveEditKyxm() {
	$.ajax({
		 type: "PUT", //提交的方法
		 url: "/api/kyxm/saveEditKyxm&&"+vueMain.markadd, //提交的地址 
		 async: true,
		 data: vueMain.kyxmItemFormData,
		 success: function(retResult) { //成功
		 	chooseShowAdmin("kyxmListSection");
		 	vueMain.kyxmListTableData = retResult;
		 	vueMain.markadd = false;
		 	console.log("保存课题信息成功!");
		 },
		 error: function() {
		 	chooseShowAdmin("kyxmListSection");
		 	vueMain.markadd = false;
		 	console.log("保存课题信息失败!");
		 }
		});
}
function deleteKyxm(k_id) {
	$.ajax({
		 type: "DELETE", //提交的方法
		 url: "/api/kyxm/deleteKyxm/"+k_id, //提交的地址 
		 async: true,
		 success: function(retResult) { //成功
		 	vueMain.visitTableData = retResult;
		 	console.log("删除课题: "+k_id+"成功!");
		 },
		 error: function() {
		 	console.log("课题正在被引用!");
		 }
		});
}
function addKyxm() {
	vueMain.markadd = true;
	editKyxm(0);//k_id=0表示添加课题的操作
}
//课题管理模块end

//-------------------------管理员模块end---------------------------