<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<div class="pageContent">
	<form method="post" action="/role/add" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input hidden="true" name="navTabId" value="rolePage">
		<div class="pageFormContent" layoutH="50">
			<p>
				<label>角色名称：</label>
				<input name="rolename" class="required" type="text" maxlength="10" size="20" alt="请输入角色名" remote="/role/isUnique"/>
			</p>
			<p>
				<label>角色登录跳转地址：</label>
				<input name="url" class="required" type="text" maxlength="10" size="20" alt="请输入角色名" />
			    <input name="userid"  type="hidden"  value="${userId }"/>
				<input name="username"  type="hidden"  value="${username }"/>
			</p>
			<p>
				<label>角色授权：</label>
				<input type="hidden" name="ids" id="ids"/>
				<ul class="tree treeFolder treeCheck required" oncheck="kkk">
	
					<li><a href="#" tvalue="${page.id }">${page.id}.${page.text }:${page.url } 
					</a>
	                    
	                    <c:forEach items="${page.children}" var="item">
						<ul>
							<li><a href="#" tname="name" tvalue="${item.id }">${item.text}:${item.url }</a>
							
			                    <c:forEach items="${item.children}" var="item1">
								<ul>
									<li><a href="#"  tname="name" tvalue="${item1.id }">${item1.text
											}:${item1.url } </a></li>
			
								</ul>
								</c:forEach>						   
							</li>
						</ul>
						</c:forEach>
					</li>
				</ul>
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


<script type="text/javascript">

function kkk(){
	var json = arguments[0], result="";
	//alert(json.checked);

	$(json.items).each(function(i){
	   if(result !=""){result += ",";}
		result += this.value;
	});
	//alert(result);
	$("#ids").val(result); 
} 

</script>