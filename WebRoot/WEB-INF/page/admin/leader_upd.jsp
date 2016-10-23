<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="pageContent">
	<form method="post" action="/leaderInfo/upd?id=${leader.leaderid}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input hidden="true" name="navTabId" value="leaderPage">
		<div class="pageFormContent" layoutH="50">
			<p>
				<label>姓名：</label>
				<input name="name" class="required" type="text" size="30" value="${leader.leader_name }" alt="请输入名称"/>
			</p>
			<p>
				<label>职位：</label>
				<input name="position" class="required" type="text" size="30"  value="${leader.leader_position }" alt="请输入位置"/>
			</p>
			<p>
				<label>性别：</label>
		<%-- 	<c:if test="${leader.leader_sex =='女' }">
				<input name="sex" class="required" type="radio" value="男"/>男
				<input name="sex" class="required" type="radio" value="女" checked/>女
				</c:if> --%>
				
				<input name="sex" class="required" type="radio" value="男" checked/>男
				<input name="sex" class="required" type="radio" value="女"/>女
				
			</p>
			<p>
				<label>年龄：</label>
				<input name="age" class="required" type="text" size="30" alt="请输入大小"  value="${leader.leader_age }"/>
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