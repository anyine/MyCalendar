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
<script src="/layer/layer.js"></script>
<!--自定义 -->

<script>

var roomId;
$(document).ready(function() {
	
	roomId = $("#leader1").attr("leaderId");
	$("#room1").css("backgroundColor", "#003399");

	 var datetime = $.fullCalendar.parseDate($("#severdate").val());

	/* initialize the calendar
	-----------------------------------------------------------------*/
	
	$('#calendar').fullCalendar({
		theme: true,
		year: datetime.getFullYear(),
		month: datetime.getMonth(),
		date: datetime.getDate(),
		header: {
		    left: 'prev,next today',
		    center: 'title',
		    right: 'month,agendaWeek,agendaDay'
		},
		defaultView:'agendaWeek',
		handleWindowResize:true,
		firstDay:1,
		allDaySlot: false,
		allDayDefault: false,
		aspectRatio:0.1,
		buttonText: {
		     prev:     '上翻',
		     next:     '下翻',
		     prevYear: '去年',
		     nextYear: '明年',
		     today:    '今天',
		     month:    '月',
		     week:     '周',
		     day:      '日'
		},
		monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
		monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'],
		dayNames: ['周日', '周一', '周二', '周三', '周四', '周五', '周六'],
		dayNamesShort: ['周日', '周一', '周二', '周三', '周四', '周五', '周六'],
		timeFormat: {
			agenda: 'h:mmtt{ - h:mmtt}',
			'': 'h(:mm)tt'
		},
		columnFormat: {
			month: 'ddd',
			week: 'MM/dd ddd',
			day: 'ddd'
		},
		titleFormat: {
			month: 'yyyy 年 MMM 月',
			week: "yyyy.MM.dd{ '&#8212;' yyyy.MM.dd}",
			day: 'ddd'
		},
		events: function( start, end, callback ) {
		   // alert("events");
			$.getJSON("/leaderSchedule/getDurationEvent", { roomId: roomId,start:Math.round(start.getTime()/1000), end:Math.round(end.getTime()/1000)}, function(events){
				callback(events);
			});
		},
		snapMinutes: 5,
		eventResize: function( event, dayDelta, minuteDelta, revertFunc, jsEvent, ui, view ) {
			 layer.confirm('确定更改吗？', {icon: 3, title:'提示'}, function(index){
			    //alert('a');
				layer.close(index);
				$.getJSON("/leaderSchedule/updateleaderEvent",{id: event.id, start: event.start.getTime(), end: event.end.getTime(), roomId: roomId}, function(data){
					if(!data.isSuccess){
						//alert(data.msg);
						layer.open({
							  title: '错误提示',
							  content: data.msg,
							  skin: 'layui-layer-molv',
							  icon: 2
							});
						revertFunc();
					}
					//this.events( start, end, callback );
				});
			 }, 
			 function(index){
				 revertFunc();
				 layer.close(index);
				 });
		},
		eventDrop: function( event, dayDelta, minuteDelta, allDay, revertFunc, jsEvent, ui, view ) {
			 layer.confirm('确定更改吗？', {icon: 3, title:'提示'}, function(index){
				layer.close(index);
				$.getJSON("/leaderSchedule/updateleaderEvent",{id: event.id, start: event.start.getTime(), end: event.end.getTime(), roomId: roomId}, function(data){
					if(!data.isSuccess){
						//alert(data.msg);
						layer.open({
							  title: '错误提示',
							  content: data.msg,
							  skin: 'layui-layer-molv',
							  icon: 2
							});
						revertFunc();
					}
					//events( start, end, callback );
				});
			 }, function(index){
				 revertFunc();
				 layer.close(index);
				 });
		},
		selectable: true,
		selectHelper: true,
		select: function(start, end, allDay) {
			if(start.getTime()<=new Date().getTime()){
				//alert("开始时间已过期!");
				layer.open({
					  title: '错误提示',
					  content: '开始时间已过期!',
					  skin: 'layui-layer-molv',
					  icon: 2
					});
				
			}else{
				var title = '';
				layer.prompt({  value: '事记内容',title: '添加事记录'},function(value, index, elem){
					  title = value;
					  layer.close(index);
					  
						if (title) {
							$.getJSON("/leaderSchedule/addleaderEvent",{start: start.getTime(), end: end.getTime(), roomId: roomId, title: title}, function(data){
								if(data.isSuccess){
									$('#calendar').fullCalendar('refetchEvents');
								}else{
									//alert(data.msg);
									layer.open({
										  title: '错误提示',
										  content: data.msg,
										  skin: 'layui-layer-molv',
										  icon: 2
										});
								}
							});
						}else{
							layer.open({
								  title: '错误提示',
								  content: '请输入会议内容！',
								  skin: 'layui-layer-molv',
								  icon: 2
								});
						}
					  
					});
				
			}
			$('#calendar').fullCalendar('unselect');

		},
		eventRender: function(event, element) {
// 			console.log(element.html());
			element.qtip({ 
				id: event.id,
				style: {
					classes: 'qtip-bootstrap qtip-shadow'
				},
				content: {
					title: event.leaderName,
					text: getTipContent(event.userName, event.start, event.end, event.title, event.email, event.status).clone()
				},
				position: {
					my: 'right center',
					at: 'left center'
				}
			});
		},
	/*	eventClick: function(event, jsEvent, view) {
// 			console.log(element.html());
			//alert('asd');
			$('#qTipContent').qtip({ 
				id: event.id,
				style: {
					classes: 'qtip-bootstrap qtip-shadow'
				},
				content: {
					title: event.roomName,
					text: getTipContent(event.userName, event.start, event.end, event.title, event.email, event.status).clone()
				},
				position: {
					my: 'right center',
					at: 'left center'
				}
			});
		},*/
		loading: function(bool) {
			if (bool) $('#loading').show();
			else $('#loading').hide();
		}
	});
	
	
});

function getEvents(start, end){
	$.getJSON("/leaderSchedule/getDurationEvent", { roomId: roomId,start:Math.round(start.getTime()/1000), end:Math.round(end.getTime()/1000)}, function(events){
		  return events;
	});
}
function changeRoom(id){
	$(".external-event").each(function(i){
		if(this.id==id){
			$(this).css("backgroundColor", "#003399");
			roomId = $(this).attr("leaderId");
		}else{
			$(this).css("backgroundColor", "#3366CC");
		}
	});
	$('#calendar').fullCalendar('refetchEvents');
}
function getTipContent(initiator, start, end, subject, contact, status){
	$("#initiator").text(initiator);
	$("#contact").text(contact);
	$("#tb").text($.fullCalendar.formatDate(start,'hh:mmtt')+" - "+$.fullCalendar.formatDate(end,'hh:mmtt'));
	$("#subject").text(subject);
	
	if('0'==status) status='会议';
	if('1'==status) status='出差';
	if('2'==status) status='调研';
	if('3'==status) status='接待';
	
	
	$("#status").text(status);
	return $('#qTipContent');
}

</script>

</head>
<body>
	<div id='wrap'>
	    
	    <div class="top-titl"><h1 align="center">xxxx管理系统</h1></div>
	   
		<input id="severdate" type="hidden" value="${ServerTime }">
		<div id="left_con">
			<div id='exit'>
				<a href="/exit">退出</a>
			</div>
			<div id='external-events'>
				<h4 align="center">领导列表</h4>
				<c:forEach items="${leaderList}" var="leader" varStatus="status">
					<div class='external-event' id="leader${status.count}"
						leaderId="${leader.leaderid}" onclick="changeRoom(this.id)">
						${leader.leader_name}<br>[职位：${leader.leader_position} ]<br>
						[性别：${leader.leader_sex}&nbsp;
						年龄：${leader.leader_age}]
					</div>
				</c:forEach>
			</div>
		</div>
		<div id='calendar'></div>
		<div style='clear: both'></div>
	</div>
	<div id='loading'>loading...</div>
	<div id="qTipContent">
		<table>
			<tr>
				<td>时间：</td>
				<td><span id="tb"></span></td>
			</tr>
			<tr>
				<td>事记类型：</td>
				<td><span id="status"></span></td>
			</tr>
			<tr>
				<td>事记内容：</td>
				<td><span id="subject"></span></td>
			</tr>
			<tr>
				<td>记录人：</td>
				<td><span id="initiator"></span></td>
			</tr>
			<tr>
				<td>E-Mail：</td>
				<td><span id="contact"></span></td>
			</tr>
		</table>
	</div>

</body>

</html>
