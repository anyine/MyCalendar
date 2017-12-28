<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<div class="pageContent">
	<form method="post" action="/usermenu/add" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input hidden="true" name="navTabId" value="usermenuPage">
		<div class="pageFormContent" layoutH="50">
			<p>
				<label>菜单名称：</label>
				<input name="title" class="required" type="text" maxlength="10" size="20" alt="请输入单位名" remote="/usermenu/isUnique"/>
			</p>
			<p>
				<label>菜单URL：</label>
				<input name="name" class="required" type="text" maxlength="30" size="20" alt="请输入单位名"/>
				<input name="userid"  type="hidden"  value="${userId }"/>
				<input name="username"  type="hidden"  value="${username }"/>
				<%-- <input name="parentid"  type="hidden"  value="${param.parentid?param.parentid:0 }"/> --%>
			</p>
			<p>
				<label>父节点：</label>
				<input name="parentid" class="required" type="text" maxlength="10" size="20" alt="请输入节点id" />
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