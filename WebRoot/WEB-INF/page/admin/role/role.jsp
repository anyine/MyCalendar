<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form id="pagerForm" method="post" action="/role/getPageList">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${page.pageSize}" />
</form>


<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="../role/goAdd" target="dialog" mask=true minable=false ref="dlg_role_add" title="新增" width="400" height="580"><span>新增</span></a></li>
			<li><a class="delete" href="../role/del/{id}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="75">
		<thead>
			<tr>
				<th width="150">角色名称</th>
				<th width="150">角色跳转地址</th>
				<th width="80">创建人</th>
				<th width="80">创建日期</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="item">
				<tr target="id" rel="${item.id}">
					<td>${item.user_role_name}</td>
					<td>${item.user_role_login_url}</td>
					<td>${item.cuser_name}</td>
					<td>${item.cdate}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<jsp:include page="paginationBar.jsp"></jsp:include>
</div>
