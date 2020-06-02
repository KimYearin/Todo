# Todo

< 웹프론트엔드 기술요구사항 >

- 총 2개의 화면이 존재합니다.

    - 할 일 목록 화면 (리스트)
    - 할 일 등록 화면 (쓰기)

- CSS는 기획서와 동일한 수준으로 만들어야 합니다.
- 따라서 HTML 엘리먼트간의 배치와 간격은 일정하고 반듯해야 합니다.
- 글자의 크기는 일정한 수준을 유지합니다.
- CSS는 외부 라이브러리(부트스트랩)을 사용하지 않습니다.
- jQuery를 사용하지 않고, querySelector, addEventListener, innerHTML을 사용해서 DOM, EVENT 처리를 합니다.
- Ajax는 XMLHTTPRequest를 사용합니다.


****

< 웹백엔드 기술요구사항 >

- 프로젝트는 maven프로젝트로 생성합니다.
- 제공된 테이블 생성 SQL을 이용해서 테이블을 생성합니다.
- TodoDto 클래스와 TodoDao클래스를 주어진 스펙에 맞게 작성합니다.
- 메인화면을 보여주기 위한 MainServlet과 main.jsp를 작성합니다.
- MainServlet은 TodoDao를 이용해 결과를 조회해서 main.jsp 에 전달합니다.
- 새로운todo등록 버튼을 클릭하면 해당 요청을 서블릿이 받아서 jsp로 포워딩하여 할 일 등록 화면을 보여줍니다.
- 할일등록폼에서 값을 입력하고 제출 버튼을 누르면 post 방식으로 요청하게 합니다.
- 해당 요청을 서블릿이 받아서 처리하게하고, 요청에 대한 모든 일이 끝나면 메인화면으로 리다이렉트 합니다.
- 메인화면에서 todo 상태변경 버튼(->)을 클릭하여 요청을 보낼 때, Todo 의 Id와 상태값을 전달하여 다음 상태로 (현재 상태가 Todo라면 doing으로 doing 이라면 done) 상태를 나타내는 컬럼값을 변경하고 응답결과로 "success"를 보냅니다.



< main page > 

![Todo_01](https://user-images.githubusercontent.com/51191647/83464023-9b974880-a4aa-11ea-8997-4b780f33e4f5.JPG)



< 할 일 항목의 화살표를 클릭했을 때(DOINg의 마지막 항목)>

![Todo_02](https://user-images.githubusercontent.com/51191647/83464027-9fc36600-a4aa-11ea-881a-673245b6e542.JPG)



< 새로운 TODO 등록 화면 >

![Todo_03](https://user-images.githubusercontent.com/51191647/83464046-abaf2800-a4aa-11ea-81a0-74c771db1b98.JPG)



< 데이터베이스 다이어그램>

![DB_00](https://user-images.githubusercontent.com/51191647/83464753-bf5b8e00-a4ac-11ea-9443-0e3403ec0789.JPG)



< Todo 테이블 구조>

![DB_01](https://user-images.githubusercontent.com/51191647/83464053-b10c7280-a4aa-11ea-81a7-96667ea00c2e.JPG)



< Todo 테이블 내용>

![DB_02](https://user-images.githubusercontent.com/51191647/83464056-b36ecc80-a4aa-11ea-95a5-21784be9ef8c.JPG)


****
- 백엔드 구성 

 1. TodoDto.java : id, name, regDate, sequence, title, type
 
 2. TodoDao.java
    - addTodo() : 추가기능
    - getTodos() : 목록전송
    - int updateTodo(TodoDto dto) : 할일 항목을 클릭했을때 상태(Type)변화
    
 3. MainServlet.java : 초기 화면을 구성하기 위한 데이터 전송
    - doGet 구현
    - List<TodoDto> list()를 이용해서 전체 목록 받아오기
    - Type에 따라 TODO/DOING/DONE 나눠서 리스트저장
    - forward로 전송
    - 프로그램 시작할 때 MainServlet부터 시작
  
 4. Main.jsp : 할일 목록 출력화면(메인 화면)
    - 할일 리스트 받기
    - 반복문 이용해서 출력
    - '새로운 TODO 등록' 버튼을 클릭하면 todoForm.jsp로 포워딩
    
  5. TodoForm.jsp : '새로운 TODO 등록' 입력폼
    - 새로운 할일 항목 입력받기
    - 이전버튼을 누르면 이전페이지로 이동(history.go(-1))
    - '내용지우기'버튼을 클릭했을 때 내용 지우기(onFocus="this.value=''; return true;")
    - 제출다하면 submit -> TodoAddServlet으로 POST방식 이용해서 전송
    
  6. TodoAddServlet.java : 할일 추가 서블릿
    - Dao를 이용해서 새로운 할일 추가
    - redirect로 mainServlet 호출
    
  7. TodoTypeServlet.java : type변환 서블릿
    - doPost에 작성
    - 자바스크립트로 Ajax 이용해서 보낸 메세지 받는 방법
      : request.getReader().lines().collect(Collectors.joining(System.lineseparator()));
    - id와 type을 받아서 type 바꾸기(dao.updateTodo 이용)
  
  ****
  - 프론트앤드 구성
  
  1. CSS를 활용해서 main.jsp와 todoForm 꾸미기
  
  2. Ajax를 이용해서 동적 표현 : 다른 것 먼저 처리하고 나중에 load 실행
  
  
  ****
  - 느낀점 및 아쉬운점
  
  1. Ajax를 활용하면서 원리를 익힐 수 있었다.
  
  2. MVC모델을 약간 활용했다. : jsp로 웹사이트를 표현하고 servlet으로 내부적인 조작을 했다.
  
  3. 이클립스상의 오류때문에 jstl과 El을 적용할 수 없어서 아쉬웠다.
  
  
  
