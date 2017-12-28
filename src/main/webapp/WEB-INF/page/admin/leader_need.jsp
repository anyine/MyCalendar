<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form id="pagerForm" method="post" action="/leaderInfo/getPageListSch">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${page.pageSize}" />
</form>

<div class="pageHeader">
   			   <form id="" action="/leaderInfo/getPageListSch" method="post" onsubmit="return navTabSearch(this)">  
				<input type="hidden" name="pageNum" value="1" />
             	<input type="hidden" name="numPerPage" value="${page.pageSize}" />
				<div class="searchBar">
				      <div class="searchContent">
				           	 事记开始的时间范围：<input type="text" name="sdate" class="date" dateFmt="yyyyMMdd" />~
					          <input type="text" name="edate" class="date" dateFmt="yyyyMMdd" /></label>
							 事迹类型: <select name="type">
					                        <option value="0" selected>会议</option>
					                        <option value="1">调研</option>
					                        <option value="2">出差</option>
					                        <option value="3">接待</option>
					                  </select>
						       领导名字：<input type="text" name="name" >
				      </div>
				      <div class="subBar">
				                  <ul>
				                        <li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
				                  </ul>
				      </div>
				</div>
				</form>
</div>

<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="../leaderInfo/goLeaderScheduleAdd" target="dialog" mask=true minable=false ref="dlg_leaderInfo_add" title="新增" width="550" height="300"><span>新增</span></a></li>
			<li><a class="delete" href="../leaderInfo/delLeaderSchedule/{leadersch_id}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
			<li><a class="edit" href="../leaderInfo/goLeaderScheduleUpd/{leadersch_id}" target="dialog" mask=true minable=false ref="dlg_leaderSchedule_edit" title="修改" width="550" height="300"><span>修改</span></a></li>
	</div>
	<div class="grid">
		<table class="table" width="100%" layoutH="130" >
		<thead>
			<tr>
				<th width="80">主题</th>
				<th width="80">开始时间</th>
				<th width="80">结束时间</th>
				<th width="80">事记类型</th>
				<th width="80">名字</th>
				<th width="80">职务</th>
				<th width="80">记录人</th>
				<th width="80">记录时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="item">
				<tr target="leadersch_id" rel="${item.id}">
					<td>${item.subject}</td>
					<td>${item.start}</td>
					<td>${item.end}</td>
					<c:if test="${item.sj_type == '0'}"><td>会议</td></c:if>
					<c:if test="${item.sj_type == '1'}"><td>调研</td></c:if>
					<c:if test="${item.sj_type == '2'}"><td>出差</td></c:if>
					<c:if test="${item.sj_type == '3'}"><td>接待</td></c:if>
					<td>${item.leader_name}</td>
					<td>${item.leader_position}</td>
					<td>${item.username}</td>
					<td>${item.create_date}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
	<jsp:include page="paginationBar.jsp"></jsp:include>
</div>
