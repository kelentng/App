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
	
	
}
