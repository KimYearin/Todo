package Tododb;

import java.io.IOException;
import java.util.stream.Collectors;

import Tododb.TodoDao;
import Tododb.TodoDto;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TodoTypeServlet
 */
public class TodoTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TodoTypeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if ("POST".equalsIgnoreCase(request.getMethod())) 
		{
			//System.out.println(request.getReader().lines().collect(Collectors.joining(System.lineSeparator())));
			//Ajax로 보낸 메세지 받기
			String msg = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		   System.out.println(msg);
		   //id와 type추출
		   long id = Long.parseLong(msg.split("&")[0]);
		   System.out.println(id);
		   String type = msg.split("&")[1];
		   TodoDto dto = new TodoDto(id, type);
		   TodoDao dao = new TodoDao();
		   //dao를 이용해서 update
		   int updateCount = dao.updateTodo(dto);
			System.out.println(updateCount);
		}
		
		
		 
		  
		 
		 
		/* response.sendRedirect("http://localhost:8080/Todo/mainServlet"); */
		
	}

}
