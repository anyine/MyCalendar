<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form id="pagerForm" method="post" action="/leaderInfo/getPageList">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${page.pageSize}" />
</form>


<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="../leaderInfo/goAdd" target="dialog" mask=true minable=false ref="dlg_leaderInfo_add" title="新增" width="550" height="300"><span>新增</span></a></li>
			<li><a class="delete" href="../leaderInfo/del/{leader_id}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
			<li><a class="edit" href="../leaderInfo/goUpd/{leader_id}" target="dialog" mask=true minable=false ref="dlg_leaderInfo_edit" title="修改" width="550" height="300"><span>修改</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="75">
		<thead>
			<tr>
				<th width="150">名称</th>
				<th width="150">职位</th>
				<th width="150">年龄</th>
				<th width="150">性别</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="item">
				<tr target="leader_id" rel="${item.leaderid}">
					<td>${item.leader_name}</td>
					<td>${item.leader_position}</td>
					<td>${item.leader_age}</td>
					<td>${item.leader_sex}</td>

				</tr>
			</c:forEach>
		</tbody>
	</table>
	<jsp:include page="paginationBar.jsp"></jsp:include>
</div>
