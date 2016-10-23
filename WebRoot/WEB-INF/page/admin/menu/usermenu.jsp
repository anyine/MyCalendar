<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form id="pagerForm" method="post" action="/usermenu">
</form>

<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="../usermenu/goAdd" target="dialog"
				mask=true minable=false ref="dlg_usermenu_add" title="新增"
				width="500" height="200"><span>新增</span> </a></li>
			<li><a class="delete" id="delete" href="/usermenu/del/{id}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span> </a></li>
		</ul>
	</div>
	<div class="accordion">

		<div class="accordionHeader">

			<h2>
				<span>icon</span>权限管理
			</h2>

		</div>

		<div class="accordionContent" id ="t2" style="height:500px">


           
			<ul class="tree treeCheck" oncheck="kkk">

				<li><a href="#" tvalue="${page.id }">${page.id}.${page.text }:${page.url } 
				</a>
                    
                    <c:forEach items="${page.children}" var="item">
					<ul>
						<li><a href="#" tname="name" tvalue="${item.id }">${item.id }.${item.text}:${item.url }</a>
						
		                    <c:forEach items="${item.children}" var="item1">
							<ul>
								<li><a href="#"  tname="name" tvalue="${item1.id }">${item1.id }.${item1.text
										}:${item1.url } </a></li>
		
							</ul>
							</c:forEach>						   
						</li>
					</ul>
					</c:forEach>
				</li>
			</ul>


		</div>


	</div>


</div>

<script type="text/javascript">
//选择复选框，更改delete的路径
function kkk(){
	var json = arguments[0], result="";
	//alert(json.checked);

	$(json.items).each(function(i){
	   if(result !=""){result += ",";}
		result += this.value;
	});
	//alert(result);
	$("#delete").attr("href","/usermenu/del/"+result); 
} 

</script>
