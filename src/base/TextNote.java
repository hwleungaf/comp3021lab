package base;

public class TextNote extends Note {
	private String content;
	
	public TextNote(String title) {
		super(title);
		this.content = null;
	}
	
	public TextNote(String title, String content) {
		super(title);
		this.content = content;
	}
	
	public String getContent() {
		return this.content;
	}

}
