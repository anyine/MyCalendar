<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>会议室预定</title>
<link href='/css/style.css' rel='stylesheet' />
<link href='/css/jquery-ui.min.css' rel='stylesheet' />
<link href='/css/fullcalendar.css' rel='stylesheet' />
<link href='/css/fullcalendar.print.css' rel='stylesheet' media='print' />
<link href='/css/jquery.qtip.min.css' rel='stylesheet' />
<script src='/js/jquery.min.js'></script>
<script src='/js/jquery-ui.custom.min.js'></script>
<script src='/js/jquery.qtip.min.js'></script>
<script src='/js/fullcalendar.min.js'></script>
<!--自定义 -->
<script src='/js/bookroom.js'></script>
<script src="/layer/layer.js"></script>
</head>
<body>
	<div id='wrap'>
	    
	    <div class="top-titl"><h1 align="center">xxxx管理系统</h1></div>
	    <div class="tools-bar">
	       <div class="li-bar"><h3><a href="#">会议室预约 </a></h3></div>|
	       <div class="li-bar"><h3><a href="/leaderSchedule/getJsp">领导事记</a></h3></div>
	    </div>
		<input id="severdate" type="hidden" value="${ServerTime }">
		<input id="userid" type="hidden" value="${userId }">
		<div id="left_con">
			<div id='exit'>
				<a href="/exit">退出</a>
			</div>
			<div id='external-events'>
				<h4 align="center">会议室</h4>
				<c:forEach items="${roomList}" var="room" varStatus="status">
					<div class='external-event' id="room${status.count}"
						roomId="${room.id}" onclick="changeRoom(this.id)">
						[名称：${room.name}]<br>[位置：${room.room_size}室 &nbsp;
						大小:${room.room_position}人]
					</div>
				</c:forEach>
				<div class="tip_col">
					<div class="text">过期的预约</div>
					<div class="color t01"></div>
				</div>
				<div class="tip_col">
					<div class="text">成功审批的预约</div>
					<div class="color t02"></div>
				</div>
				<div class="tip_col">
					<div class="text">当前用户的预约</div>
					<div class="color t03"></div>
				</div>
<!-- 				<div class="tip_col">
					<div class="text">未过期的预约</div>
					<div class="color t04"></div>
				</div> -->
				<div class="tip_col">
					<div class="text">以拒绝的预约</div>
					<div class="color t05"></div>
				</div>
			</div>
		</div>
		<div id='calendar'></div>
		<div style='clear: both'></div>
	</div>
	<div id='loading'>loading...</div>
	<div id="qTipContent">
		<table>
			<tr>
				<td>发起人：</td>
				<td><span id="initiator"></span></td>
			</tr>
			<tr>
				<td>E-Mail：</td>
				<td><span id="contact"></span></td>
			</tr>
			<tr>
				<td>时间：</td>
				<td><span id="tb"></span></td>
			</tr>
			<tr>
				<td>主题：</td>
				<td><span id="subject"></span></td>
			</tr>
			<tr>
				<td>状态：</td>
				<td><span id="status"></span></td>
			</tr>
			<tr>
				<td>审批意见：</td>
				<td><span id="idea"></span></td>
			</tr>
		</table>
	</div>

</body>

</html>
