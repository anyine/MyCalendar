<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="pageContent">
	<form method="post" action="/leaderInfo/updLeaderSchedule" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input hidden="true" name="navTabId" value="leaderSchedulePage"/>
		<div class="pageFormContent" layoutH="50">
			<p>
				<label>领导人：</label>
				<select name="oop.leaderid" >
                        <option value="">全部领导人</option>
                        <c:forEach items="${leaderInfo_list }" var="item">
                        <option value="${item.leaderid}" ${LeaderSchedule.leaderid eq item.leaderid?"selected":""}>${item.leader_name}</option>
                        </c:forEach>
                </select>
			</p>
			<p>
				<label>事迹类型：</label> 
				<select name="oop.sj_type" class="required">
					<option value="0" ${LeaderSchedule.sj_type eq "0"?"selected":""}>会议</option>
					<option value="1" ${LeaderSchedule.sj_type eq "1"?"selected":""}>调研</option>
					<option value="2" ${LeaderSchedule.sj_type eq "2"?"selected":""}>出差</option>
					<option value="3" ${LeaderSchedule.sj_type eq "3"?"selected":""}>接待</option>
				</select>
			</p>
			<p>
				<label>事迹内容：</label>
				<input name="oop.subject" class="required" value="${LeaderSchedule.subject}" />
			</p>
			<p>
				<label>开始时间：</label>
				<input name="oop.start" class="date required" type="text" dateFmt="yyyy-MM-dd HH:mm:ss" style="width:180px" value="${LeaderSchedule.start}"/>
				<a class="inputDateButton" href="javascript:void(0);"></a> 
			</p>
			<p>
				<label>结束时间：</label>
				<input name="oop.end" class="date required" type="text" dateFmt="yyyy-MM-dd HH:mm:ss" style="width:180px" value="${LeaderSchedule.end}"/>
				<a class="inputDateButton" href="javascript:void(0);"></a> 
				<input name="oop.id"  type="hidden"  value="${LeaderSchedule.id }"/>
				<input name="oop.create_date"  type="hidden"  value="${ServerTime }"/>
				<input name="oop.userid"  type="hidden"  value="${userId }"/>
			</p>			
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>