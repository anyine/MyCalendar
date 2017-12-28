<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form id="pagerForm" method="post" action="/room/getPageListSch">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${page.pageSize}" />
</form>

<div class="pageHeader">
   			   <form id="" action="/room/getPageListSch" method="post" onsubmit="return navTabSearch(this)">  
				<input type="hidden" name="pageNum" value="1" />
             	<input type="hidden" name="numPerPage" value="${page.pageSize}" />
             	
				<div class="searchBar">
				      <div class="searchContent">
				           	会议开始的时间范围：<input type="text" name="sdate" class="date" dateFmt="yyyyMMdd" />~
					          <input type="text" name="edate" class="date" dateFmt="yyyyMMdd" /></label>
							审批状态：<input type="radio" name="status" value="1"/>已审批 
							<input type="radio" name="status" value="0" checked/>未审批   
						       会议室名称：<input type="text" name="roomname" >
				            </select>
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
			<!-- <li><a class="add" href="../room/goAdd" target="dialog" mask=true minable=false ref="dlg_room_add" title="新增" width="550" height="300"><span>审批</span></a></li> -->
			<li><a class="edit" href="../room/updateSta/{roomsch_id}" target="ajaxTodo" title="确定通过吗?"><span>批准</span></a></li>
			<li><a class="edit" href="../room/goRefuse/{roomsch_id}" target="dialog" mask=true minable=false ref="dlg_roomSchedule_edit" title="确定拒绝吗?"><span>拒绝</span></a></li>
		</ul>
	</div>
	<div class="grid">
		<table class="table" width="100%" layoutH="130" >
		<thead>
			<tr>
				<th width="80">主题</th>
				<th width="80">开始时间</th>
				<th width="80">结束时间</th>
				<th width="80">会议室</th>
				<th width="80">发起人</th>
				<th width="80">e-mail</th>
				<th width="80">审批人</th>
				<th width="80">审批状态</th>
				<th width="80">审批意见</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="item">
				<tr target="roomsch_id" rel="${item.id}">
					<td>${item.subject}</td>
					<td>${item.start}</td>
					<td>${item.end}</td>
					<td>${item.roomname}</td>
					<td>${item.username}</td>
					<td>${item.email}</td>
					<td>${item.checkname}</td>
					<c:if test="${item.room_sh_status == '0' || item.room_sh_status == null}"><td>未审批</td></c:if>
					<c:if test="${item.room_sh_status == '1'}"><td>已审批</td></c:if>
					<c:if test="${item.room_sh_status == '2'}"><td>拒绝</td></c:if>
					<td>${item.room_idea}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
	<jsp:include page="paginationBar.jsp"></jsp:include>
</div>
