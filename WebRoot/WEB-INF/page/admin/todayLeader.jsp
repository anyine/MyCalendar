<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>今日领导事记</h1>
<div>
    <table class="table" width="100%" layoutH="75">
		<thead>
			<tr>
				<th width="80">事记内容</th>
				<th width="80">开始时间</th>
				<th width="80">结束时间</th>
				<th width="80">领导人</th>
				<th width="80">记录人</th>
				<th width="80">e-mail</th>
				<th width="80">事记类型</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${leaderList}" var="item">
				<tr>
					<td>${item.subject}</td>
					<td>${item.start}</td>
					<td>${item.end}</td>
					<td>${item.leadername}</td>
					<td>${item.username}</td>
					<td>${item.email}</td>
					<c:if test="${item.sj_type == '0'}"><td>开会</td></c:if>
					<c:if test="${item.sj_type == '1'}"><td>调研</td></c:if>
					<c:if test="${item.sj_type == '2'}"><td>出差</td></c:if>
					<c:if test="${item.sj_type == '3'}"><td>接待</td></c:if>
				</tr>
			 
			</c:forEach>
		</tbody>
	</table>
</div>