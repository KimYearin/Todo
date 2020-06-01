<%@page import="Tododb.TodoDto"%>
<%@page import="Tododb.TodoDao"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Todo</title>
</head>

<style>
#myTodoThings{ /*나의 해야할 일들*/
  transform: rotate(-35deg);
  color : rgb(35,95,114);
  font-weight: 900;
  width: 300px;
  float: left;
  font-size: 24px;
}

#addBtn{ /*새로운 todo등록 버튼*/
  float: right;
  background-color: rgb(21,163,247);
  color : white;
  width: 200px;
  height: 40px;
  font-size: 15px;
  margin-right: 70px;
  margin-top: 20px;
}

li{ /*점없애기*/
  list-style: none;
}

ul{
  padding-left: 10px;
}

.Todo, .Doing, .done{ /*세칸준비*/
  float:left;
  width: 365px;
  }

body>section>div>ul>li{
    width : 335px;
    height: 90px;
    background-color: rgb(163,219,228);
    padding-left: 15px;
    margin-top: 10px;
    padding-top: 15px;
    line-height: 200%;
  }

.todoLi, .doingLi, .doneLi { /*todo, doing, done블록*/
  background-color: rgb(44,78,101);
  color : white;
  font-size: 20px;
  height: 30px;
  padding-top: 20px;
  padding-bottom: 30px;
  padding-left: 25px;
  width: 325px;
}

body>section{
  float:left;
  width: 1110px;
  margin-left: 100px;
  margin-right: 70px;
}

section > div > ul >li >strong{
  font-size: 20px;
}

body>section>div>ul>li>button{
  margin-right: 10px;
}

.next{
  float : right;
  margin-right: 10px;
}

</style>
<body>
<header>
  <p id="myTodoThings">나의 해야할 일들</p>
  
  	<button onclick="location.href='/Todo/todoForm.jsp';" id="addBtn" >새로운 TODO 등록</button>
</header>
<%      /*리스트 받기  */
        List<TodoDto> todo = new ArrayList<>();
        List<TodoDto> doing = new ArrayList<>();
        List<TodoDto> done = new ArrayList<>();
        
        todo = (List<TodoDto>)request.getAttribute("todo");
        doing = (List<TodoDto>)request.getAttribute("doing");
        done = (List<TodoDto>)request.getAttribute("done");
        	%>

   <section> 	<!--Todo출력 부분  -->
    <div class="Todo">
        <ul class="todoUl">
        <li class="todoLi">
        	<strong>TODO</strong>
        </li>
    	<%
    	for(TodoDto item:todo){
    	
    	%>
    		<li id="<%=item.getId()%>">
    			<strong><%=item.getTitle() %></strong><br>
    			<span>등록날짜:<%=item.getRegDate() %>,<%=item.getName() %>, 우선순위 <%=item.getSequence() %><button class="next" name="arrow" <%-- onclick="javascript:document.getElementById('todo_<%=item.getId()%>').submit();" --%>>-></button>
    			<%-- <td>
    			<form id="<%=item.getId() %>" action="TodoTypeServlet" method="post">
    			<input type="hidden" name="id" value="<%=item.getId() %>"/>
    			<input type="hidden" name="type" value="<%=item.getType() %>"/>
    			</form></td> --%></span>
    		</li>
    	<% }%>
    	</ul>
     </div>
      
   <div class="Doing"> <!--Doing 출력부분  -->
        <ul class="doingUl">
        <li class="doingLi">
        	<strong>DOING</strong>
        </li>
    	<%
    	for(TodoDto item:doing){
    	
    	%>
    		<li id="<%=item.getId()%>">
    			<strong><%=item.getTitle() %></strong><br>
    			<span>등록날짜:<%=item.getRegDate() %>,<%=item.getName() %>, 우선순위 <%=item.getSequence() %><button class="next" name="arrow" <%-- onclick="javascript:document.getElementById('doing_<%=item.getId()%>').submit();" --%>>-></button>
    			<%-- <td>
    			<form id="<%=item.getId() %>" action="TodoTypeServlet" method="post">
    			<input type="hidden" name="id" value="<%=item.getId() %>"/>
    			<input type="hidden" name="type" value="<%=item.getType() %>"/>
    			</form></td>< --%></span>
    		</li>
    	<% }%>
    	</ul>
     </div>
   
   <div class="done"> <!--done 출력부분  -->
        <ul class="doneUl">
        <li class="doneLi">
        	<strong>DONE</strong>
        </li>
    	<%
    	for(TodoDto item:done){
    	
    	%>
    		<li id="doneItem_<%=item.getId()%>">
    			<strong><%=item.getTitle() %></strong><br>
    			<span>등록날짜:<%=item.getRegDate() %>,<%=item.getName() %>, 우선순위 <%=item.getSequence() %>
    			</span>
    		</li>
    	<% }%>
    	</ul>
     </div>
</section>



</body>
<script>/*Ajax이용해서 카드 이동부분  */
function ajaxTodo(id, type, addrow){

       var oReq = new XMLHttpRequest();

       oReq.addEventListener("load", function() {
             console.log("doing으로 이동");

          var todoul=document.querySelector(".todoUl");
          var doingul=document.querySelector(".doingUl");

           //카드 삭제 및 이동
           todoul.removeChild(addrow);
           doingul.insertAdjacentHTML('beforeend',addrow.outerHTML);
        });
		//update할 항목 TodoTypeServlet으로 전송
         oReq.open("POST", "TodoTypeServlet", true);
         oReq.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
         oReq.send(id+"&"+type);
}

var todoul = document.querySelector(".todoUl");
todoul.addEventListener("click",function(e) {
    if (e.target.className === "next") {
      var id=e.target.parentElement.parentElement.getAttribute('id');
      console.log(id);
      var type="TODO";
      var addrow=e.target.parentElement.parentElement;
      ajaxTodo(id, type, addrow);
    }});


function ajaxDoing(id, type, addrow){

       var oReq = new XMLHttpRequest();

       oReq.addEventListener("load", function() {
             console.log("done으로 이동");

          var doingul=document.querySelector(".doingUl");
          var doneul=document.querySelector(".doneUl");
          
          
         
           //카드 제거
           doingul.removeChild(addrow);
           
           
           //버튼 삭제
           var span = addrow.childNodes[4];
           var delBtn = span.children[0];
           
           span.removeChild(delBtn);
           var spanContent = span.innerHTML; //내용만 얻어오기
           //내용변경
           addrow.childNodes[4].innerHTML = spanContent; 
           //카드이동
           doneul.insertAdjacentHTML('beforeend',addrow.outerHTML);
        });
     //update할 항목 TodoTypeServlet으로 전송
         oReq.open("POST", "TodoTypeServlet", true);
         oReq.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
         oReq.send(id+"&"+type);
}

var doingul = document.querySelector(".doingUl");
doingul.addEventListener("click",function(e) {
    if (e.target.className === "next") {
      var id=e.target.parentElement.parentElement.getAttribute('id');
      console.log(id);
      var type="DOING";
      var addrow=e.target.parentElement.parentElement;
      ajaxDoing(id, type, addrow);
    }});
</script>
</html>



