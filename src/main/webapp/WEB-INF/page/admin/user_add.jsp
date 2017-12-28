<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<div class="pageContent">
	<form method="post" action="/user/add" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input hidden="true" name="navTabId" value="userPage">
		<div class="pageFormContent" layoutH="50">
		
		    <p>
				<label>所属单位：</label>
			    <select name="employerid" class="combox required"  ref="combox_org" refUrl="/employer/jil?employerid={value}">
                        <option value="">全部单位</option>
                        <c:forEach items="${list }" var="item">
                        <option value="${item.id}">${item.user_employer_name}</option>
                        </c:forEach>
                </select> 
			</p>
			<p>
				<label>所属机构：</label>
			    <select name="organizid" class="combox required" id="combox_org">
                        <option value="">全部机构</option>                       
                </select> 
			</p>
		    <p>
				<label>用户角色：</label>
			    <select name="roleid" class="required" style=" width: 212px;">
                        <option value="">全部角色</option>
                        <c:forEach items="${role }" var="item">
                        <option value="${item.id}">${item.user_role_name}</option>
                        </c:forEach>
                </select> 
			</p>			
			<p>
				<label>真实姓名：</label>
				<input name="name" class="required" type="text" size="30" alt="请输入真实姓名"/>
			</p>
			<p>
				<label>登录名：</label>
				<input name="loginName" class="required" type="text" size="30" alt="请输入登录名" remote="/user/isUnique"/>
			</p>
			<p>
				<label>密码：</label>
				<input id="w_validation_pwd" type="password" name="password" class="required alphanumeric" minlength="6" maxlength="16" size="30"/>
			</p>
			<p>
				<label>确认密码：</label>
				<input type="password" name="repassword" class="required" equalto="#w_validation_pwd" size="30"/>
			</p>
			<p>
				<label>TEL：</label>
				<input name="tel" class="required phone" type="text" size="30" alt="请输入电话号码"/>
			</p>
			<p>
				<label>邮箱：</label>
				<input name="email" class="required email" type="text" size="30" alt="请输入邮箱地址"/>
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