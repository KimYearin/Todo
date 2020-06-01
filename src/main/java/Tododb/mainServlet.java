package Tododb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Tododb.TodoDao;
import Tododb.TodoDto;

/**
 * Servlet implementation class mainServlet
 */
public class mainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public mainServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		List<TodoDto> list = new ArrayList<>();
		TodoDao dao = new TodoDao();
		List<TodoDto> todo = new ArrayList<>();
		List<TodoDto> doing = new ArrayList<>();
		List<TodoDto> done = new ArrayList<>();
		//목록 받아오기
		list = dao.getTodos();
		//Type별로 나눠서 저장
		  for(TodoDto list_one:list) {
			  if(list_one.getType().equals("TODO")) {
			  todo.add(list_one); 
			  }else if(list_one.getType().equals("DOING")) {
				  doing.add(list_one); 
		  }else if(list_one.getType().equals("DONE")) {
		  done.add(list_one); } }
		 

		
		/*
		 * for(TodoDto item:todo) { System.out.println(item); } for(TodoDto item:doing)
		 * { System.out.println(item); } for(TodoDto item:done) {
		 * System.out.println(item); }
		 */
		  //전송
		  request.setAttribute("todo", todo); 
		  request.setAttribute("doing", doing);
		  request.setAttribute("done", done);
		 

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/main.jsp");
		requestDispatcher.forward(request, response);
		System.out.println("jsp로 전송 완료");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
