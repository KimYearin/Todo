<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TodoForm</title>
</head>
<style>
	section{
  background-color: white;
  margin-left: 15%;
  margin-right: 15%;
}

body{
  background-color: rgb(214,214,214);
}

section>h2{
  text-align: center;
  padding-top: 25px;
}

.contents{
  padding-left: 20%;
  padding-top: 20px;
  padding-right: 20%
}

section > div > div> label{ /*대표 타이틀 회색*/
  color : rgb(98,98,98);
  font-size: 18px;
}

.input-text{ /*텍스트상자*/
  width: 100%;
  height: 31px;
  font-size: 19px;
}

.content{ /*content별로 간격 띄우기*/
  padding-top: 26px;
}

#radiobtn2, #radiobtn3{ /*라디오버튼 간격띄우기*/
  margin-left: 50px;
}
/*버튼*/
.btns{
  padding-top: 40px;
  padding-bottom: 40px;
}

#before{
  background-color: white;
  width: 130px;
  text-decoration: underline;
  font-size: 19px;
  height: 36px;
}

#clear, #submit{
  width: 130px;
  background-color: rgb(21,163,247);
  font-size: 16px;
  color: white;
  float: right;
  height: 36px;
  border-style: none;
}

#submit{
  margin-right: 10px;
}
	
</style>
<body>
<section>
<h2>할일 등록</h2>
<form action="TodoAddServlet" method="post">
	<div class="contents">
		<div class="content"><!--내용  -->
			<label>어떤일인가요?</label><br>
			<input class="input-text" type="text" name="title" value="swift 공부하기(24자까지)" maxlength="24" onFocus="this.value=''; return true;"/>
		</div>
			
		<div class="content"><!--이름  -->
			<label for="name">누가 할일인가요?</label><br>
			<input class="input-text" type="text" name="name" value="홍길동" onFocus="this.value=''; return true;"/>
		</div>	
		
		<div class="content"> <!--라디오 버튼  -->
			<label>우선순위를 선택하세요</label><br>
			<input id="radiobtn1" type="radio" name="sequence" value="1"/>1순위
			<input id="radiobtn2" type="radio" name="sequence" value="2"/>2순위
			<input id="radiobtn3" type="radio" name="sequence" value="3"/>3순위
		</div>
			
		<div class="btns">
			<input id="before" type="button" value="<이전" onClick="history.go(-1)"/> <!--이전 페이지로 이동  -->
			<input id="clear" type="reset" onclick="clear()" value="내용지우기"/> <!--내용 지우기  -->
			<input id="submit" type="submit" value="제출"/><!--submit  -->
		</div>
		
	</div>
</form>
</section>
</body>
</html>