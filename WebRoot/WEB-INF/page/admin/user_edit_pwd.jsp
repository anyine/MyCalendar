<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<div class="pageContent">
	<form method="post" action="/user/editPasswd" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		
		<div class="pageFormContent" layoutH="50">
			<p>
				<label>旧密码：</label>
				<input type="password" name="opwd" class="required"  size="30" remote="/user/isPasswd"/>
			</p>
			<p>
				<label>新密码：</label>
				<input id="w_validation_pwd" type="password" name="npwd" class="required alphanumeric" minlength="6" maxlength="16" size="30"/>
			</p>
			<p>
				<label>确认密码：</label>
				<input type="password" name="repassword" class="required" equalto="#w_validation_pwd" size="30"/>
				<input type="hidden" name="id" value="${userId }"/>
				<input type="hidden" name="username" value="${loginname }"/>
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