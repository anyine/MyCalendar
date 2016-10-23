<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="pageContent">
	<form method="post" action="/room/updateStaF" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input hidden="true" name="navTabId" value="roomSchPage">
		<div class="pageFormContent" layoutH="50">
			<p>
				<label>拒绝理由：</label>
				<input name="idea" class="required" type="text" maxlength="224" size="40" alt="请输入拒绝原因" />
				<input type="hidden" name="id" value="${roonSchedule.id }">
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