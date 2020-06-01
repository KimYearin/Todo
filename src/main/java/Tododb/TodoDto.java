package Tododb;

public class TodoDto {
	private long id; //번호 
	private String name; //이름
	private String regDate; //시간, 자동생성
	private int sequence; //우선순위(1, 2, 3 중선택)
	private String title; // 내용
	private String type; //todo, doing, done, todo가 default
	
	public TodoDto() {
		
	}
	
	public TodoDto(String name, String title, int sequence) { //이름, 내용, 우선순위를 입력받아서 생성
		super();
		this.name = name;
		this.title = title;
		this.sequence = sequence;
	}
	
	public TodoDto(String name, String title, int sequence, String regDate, String type, long id) { //이름, 내용, 우선순위를 입력받아서 생성
		super();
		this.name = name;
		this.title = title;
		this.sequence = sequence;
		this.regDate = regDate;
		this.type = type;
		this.id = id;
		}

	public TodoDto(long id, String type) {
		super();
		this.id = id;
		this.type = type;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "TodoDto [id=" + id + ", name=" + name + ", regDate=" + regDate + ", sequence=" + sequence + ", title="
				+ title + ", type=" + type + "]";
	}
	
	
}



