
var roomId;
$(document).ready(function() {
	
	roomId = $("#room1").attr("roomId");
	$("#room1").css("backgroundColor", "#003399");

	 var datetime = $.fullCalendar.parseDate($("#severdate").val());
	 var userid = $("#userid").val();

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
			$.getJSON("/roomSchedule/getDurationEvent", { roomId: roomId,start:Math.round(start.getTime()/1000), end:Math.round(end.getTime()/1000), userid:userid}, function(events){
				callback(events);
			});
		},
		snapMinutes: 5,
		eventResize: function( event, dayDelta, minuteDelta, revertFunc, jsEvent, ui, view ) {
			 layer.confirm('确定更改吗？', {icon: 3, title:'提示'}, function(index){
			    //alert('a');
				layer.close(index);
				$.getJSON("/roomSchedule/updateRoomEvent",{id: event.id, start: event.start.getTime(), end: event.end.getTime(), roomId: roomId, userid:userid}, function(data){
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
				$.getJSON("/roomSchedule/updateRoomEvent",{id: event.id, start: event.start.getTime(), end: event.end.getTime(), roomId: roomId, userid:userid}, function(data){
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
				layer.prompt({  value: '会议内容',title: '申请会议室'},function(value, index, elem){
					  title = value;
					  layer.close(index);
					  
						if (title) {
							$.getJSON("/roomSchedule/addRoomEvent",{start: start.getTime(), end: end.getTime(), roomId: roomId, title: title, userid:userid}, function(data){
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
					title: event.roomName,
					text: getTipContent(event.userName, event.start, event.end, event.title, event.email, event.status, event.roomIdea).clone()
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
	$.getJSON("/roomSchedule/getDurationEvent", { roomId: roomId,start:Math.round(start.getTime()/1000), end:Math.round(end.getTime()/1000), userid:userid}, function(events){
		  return events;
	});
}
function changeRoom(id){
	$(".external-event").each(function(i){
		if(this.id==id){
			$(this).css("backgroundColor", "#003399");
			roomId = $(this).attr("roomId");
		}else{
			$(this).css("backgroundColor", "#3366CC");
		}
	});
	$('#calendar').fullCalendar('refetchEvents');
}
function getTipContent(initiator, start, end, subject, contact, status, idea){
	
	if(null==idea) {
		idea = "等待管理员审批";
	    if('1'==status){
	    	idea = "已通过审批";
	    }
	}
	//匹配会议室状态
	if('0'==status){status='未审批';}else if('1'==status) {status='已审批';}else if('2'==status) {status='拒绝';}else {status = "无";}
	
	$("#initiator").text(initiator);
	$("#contact").text(contact);
	$("#tb").text($.fullCalendar.formatDate(start,'hh:mmtt')+" - "+$.fullCalendar.formatDate(end,'hh:mmtt'));
	$("#subject").text(subject);
	$("#status").text(status);
	$("#idea").text(idea);
	return $('#qTipContent');
}