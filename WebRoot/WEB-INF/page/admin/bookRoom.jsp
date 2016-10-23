<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div id='wrap'>
		<input id="severdate" type="hidden" value="${ServerTime }">
		<div id="left_con">
			<div id='exit'>
				<a href="/exit">退出</a>
			</div>
			<div id='external-events'>
				<h4 align="center">会议室</h4>
				<c:forEach items="${roomList}" var="room" varStatus="status">
					<div class='external-event' id="room${status.count}"
						roomId="${room.id}" onclick="changeRoom(this.id)">
						${room.name}<br>[位置：${room.room_size}室 &nbsp;
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
				<div class="tip_col">
					<div class="text">未过期的预约</div>
					<div class="color t04"></div>
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
				<td><span id="initiator"></span>
				</td>
			</tr>
			<tr>
				<td>E-Mail：</td>
				<td><span id="contact"></span>
				</td>
			</tr>
			<tr>
				<td>时间：</td>
				<td><span id="tb"></span>
				</td>
			</tr>
			<tr>
				<td>主题：</td>
				<td><span id="subject"></span>
				</td>
			</tr>
			<tr>
				<td>状态：</td>
				<td><span id="status"></span>
				</td>
			</tr>
		</table>
	</div>