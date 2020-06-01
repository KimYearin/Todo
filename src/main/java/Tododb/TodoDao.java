package Tododb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Tododb.TodoDto;

public class TodoDao {

	private static String dburl = "jdbc:mysql://localhost:3306/connectdb?serverTimezone=Asia/Seoul&useSSL=false";
	private static String dbUser = "connectuser";
	private static String dbpassword = "connect123!@#";
	

	public int addTodo(TodoDto dto) { //할일 추가기능
		int insertcount = 0;
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		String sql = "INSERT INTO Todo(title,name,sequence) VALUES(?,?,?)";
		try(Connection conn = DriverManager.getConnection(dburl,dbUser, dbpassword);
				PreparedStatement ps = conn.prepareStatement(sql);){
			ps.setString(1, dto.getTitle());
			ps.setString(2, dto.getName());
			ps.setInt(3, dto.getSequence());
			insertcount=ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return insertcount;
	}
	
	public List<TodoDto> getTodos(){ //현재 목록 출력
		List<TodoDto> list = new ArrayList<>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		String sql = "SELECT title, regDate, name, sequence, type, id FROM Todo ORDER BY regDate ASC";
		try (Connection conn = DriverManager.getConnection(dburl,dbUser,dbpassword);
				PreparedStatement ps = conn.prepareStatement(sql)){
			try(ResultSet rs = ps.executeQuery()){
				while(rs.next()) {
					String title = rs.getString("title");
					String regDate = rs.getString("regDate").split(" ")[0];
					String name = rs.getString("name");
					int sequence = rs.getInt("sequence");
					String type = rs.getString("type");
					long id = rs.getLong("id");
					
					TodoDto dto = new TodoDto(name, title, sequence, regDate, type, id);
					list.add(dto);

				}
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public int updateTodo(TodoDto dto) { //TODO->DOING, DOING->DONE으로 TYPE변환하는 기능
		int updateCount = 0;
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		String sql = "UPDATE Todo SET type=? WHERE id=?";
		try(Connection conn = DriverManager.getConnection(dburl,dbUser, dbpassword);
				PreparedStatement ps = conn.prepareStatement(sql);){
			if(dto.getType().equals("TODO")) {
				ps.setString(1, "DOING");
				ps.setLong(2, dto.getId());
			}else if(dto.getType().equals("DOING")) {
			ps.setString(1, "DONE");
			ps.setLong(2, dto.getId());
			}
			
			updateCount = ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return updateCount;
	}
}
