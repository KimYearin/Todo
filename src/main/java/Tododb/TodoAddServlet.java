package Tododb;

import java.io.IOException;
import Tododb.TodoDao;
import Tododb.TodoDto;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TodoAddServlet
 */
public class TodoAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TodoAddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String name = request.getParameter("name");
		int sequence = Integer.parseInt(request.getParameter("sequence"));
		
		TodoDto dto = new TodoDto(name,title,sequence);
		TodoDao dao = new TodoDao();
		
		int insertcount = dao.addTodo(dto); //dao를 이용해서 데이터베이스에 새로운 할일 추가
		
		System.out.println(insertcount);
		response.sendRedirect("http://localhost:8080/Todo/mainServlet"); //mainServlet호출
		
		/* response.sendRedirect("/Todo/Tododb/mainServlet.java"); */
		
	}

}
