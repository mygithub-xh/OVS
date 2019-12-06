<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<title>日志列表</title>
	<link rel="stylesheet" type="text/css" href="../easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="../easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="../easyui/css/demo.css">
	<script type="text/javascript" src="../easyui/jquery.min.js"></script>
	<script type="text/javascript" src="../easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="../easyui/js/validateExtends.js"></script>
	<script type="text/javascript">
		$(function() {
			var table;
			//datagrid初始化
			$('#dataList').datagrid({
				title:'日志列表',
				iconCls:'icon-more',//图标
				border: true,
				collapsible:false,//是否可折叠的
				fit: true,//自动大小
				method: "post",
				url:"get_list?t="+new Date().getTime(),
				idField:'id',
				singleSelect:false,//是否单选
				pagination:true,//分页控件
				rownumbers:true,//行号
				sortName:'id',
				sortOrder:'DESC',
				remoteSort: false,
				columns: [[
					{field:'chk',checkbox: true,},
					{field:'id',title:'ID',width:50, sortable: true},
					{field:'userName',title:'操作人',width:100, sortable: true},
					{field:'mpDescribe',title:'描述',width:300},
					{field:'createTime',title:'时间',width:150},
					{field:'remark',title:'备注',width:150},
				]],
				toolbar: "#toolbar"
			});
			//设置分页控件
			var p = $('#dataList').datagrid('getPager');
			$(p).pagination({
				pageSize: 10,//每页显示的记录条数，默认为10
				pageList: [10,20,30,50,100],//可以设置每页记录条数的列表
				beforePageText: '第',//页数文本框前显示的汉字
				afterPageText: '页    共 {pages} 页',
				displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',
			});


			//删除
			$("#delete").click(function(){
				var selectRows = $("#dataList").datagrid("getSelections");
				var selectLength = selectRows.length;
				if(selectLength == 0){
					$.messager.alert("消息提醒", "请选择数据进行删除!", "warning");
				} else{
					var ids = [];
					$(selectRows).each(function(i, row){
						ids[i] = row.id;
					});
					$.messager.confirm("消息提醒", "将删除与用户相关的所有数据，确认继续？", function(r){
						if(r){
							$.ajax({
								type: "post",
								url: "delete",
								data:{'ids[]':ids},
								dataType:'json',
								success: function(data){
									if(data.type == "success"){
										$.messager.alert("消息提醒","删除成功!","info");
										//刷新表格
										$("#dataList").datagrid("reload");
										$("#dataList").datagrid("uncheckAll");
									} else{
										$.messager.alert("消息提醒",data.msg,"warning");
										return;
									}
								}
							});
						}
					});
				}
			});

			//搜索按钮
			$("#search-btn").click(function(){
				$('#dataList').datagrid('reload',{
					username:$("#search-username").textbox('getValue')
				});
			});
		});
	</script>
</head>
<body>
<!-- 数据列表 -->
<table id="dataList" cellspacing="0" cellpadding="0" style="text-align:center ">

</table>
<!-- 工具栏 -->
<div id="toolbar">
	<div style="float: left;" class="datagrid-btn-separator"></div>
	<div style="float: left;" class="datagrid-btn-separator"></div>
	<div>
		<a id="delete" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-some-delete',plain:true">删除</a>
		用户名：<input id="search-username" class="easyui-textbox" />
		<a id="search-btn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">搜索</a>
	</div>
</div>


</body>
</html>