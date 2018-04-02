package model;

public class Message {
	private String title;
	private String contents;
	public Message() {
		super();
	}
	public Message(String title, String contents) {
		super();
		this.title = title;
		this.contents = contents;
	}
	public Message(Message message) {
		super();
		this.title = message.title;
		this.contents = message.contents;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	
	
}
