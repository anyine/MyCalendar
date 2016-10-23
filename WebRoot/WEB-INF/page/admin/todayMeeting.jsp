<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>今日会议</h1>
<div>
    <table class="table" width="100%" layoutH="75">
		<thead>
			<tr>
				<th width="80">主题</th>
				<th width="80">开始时间</th>
				<th width="80">结束时间</th>
				<th width="80">会议室</th>
				<th width="80">发起人</th>
				<th width="80">e-mail</th>
				<th width="80">审批状态</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${meetingList}" var="item">
			 
				<tr <c:if test="${item.room_sh_status==0 }">bgcolor="#CCCC66"</c:if>>
					<td>${item.subject}</td>
					<td>${item.start}</td>
					<td>${item.end}</td>
					<td>${item.roomname}</td>
					<td>${item.username}</td>
					<td>${item.email}</td>
					<c:if test="${item.room_sh_status == '0'}"><td>未审批</td></c:if>
					<c:if test="${item.room_sh_status == '1'}"><td>已审批</td></c:if>
				</tr>
			 
			</c:forEach>
		</tbody>
	</table>
</div>