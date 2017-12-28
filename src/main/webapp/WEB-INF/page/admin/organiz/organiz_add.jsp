<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<div class="pageContent">
	<form method="post" action="/organiz/add" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input hidden="true" name="navTabId" value="organizPage">
		<div class="pageFormContent" layoutH="50">
			<p>
				<label>所属单位：</label>
			    <select name="employerid" class="required">
                        <option value="">全部单位</option>
                        <c:forEach items="${list }" var="item">
                        <option value="${item.id}">${item.user_employer_name}</option>
                        </c:forEach>
                </select> 
			</p>
			<p>
				<label>机构名称：</label>
				<input name="name" class="required" type="text" maxlength="10" size="20" alt="请输入单位名" remote="/organiz/isUnique"/>
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