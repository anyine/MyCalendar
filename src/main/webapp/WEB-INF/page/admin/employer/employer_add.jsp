<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<div class="pageContent">
	<form method="post" action="/employer/add" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input hidden="true" name="navTabId" value="employerPage">
		<div class="pageFormContent" layoutH="50">
			<p>
				<label>单位名称：</label>
				<input name="name" class="required" type="text" maxlength="10" size="20" alt="请输入单位名" remote="/employer/isUnique"/>
			    <input name="severtime"  type="hidden"  value="${ServerTime }"/>
			    <input name="userid"  type="hidden"  value="${userId }"/>
				<input name="username"  type="hidden"  value="${username }"/>
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