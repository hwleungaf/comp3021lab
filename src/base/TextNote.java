package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class TextNote extends Note implements Serializable{
	private String content;
	
	public TextNote(String title) {
		super(title);
		this.content = null;
	}
	
	public TextNote(String title, String content) {
		super(title);
		this.content = content;
	}
	
	public TextNote(File f) {
		super(f.getName());
		this.content = getTextFromFile(f.getAbsolutePath());
	}
	
	private String getTextFromFile(String absolutePath) {
		String result = "";
		// TODO
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream(absolutePath);
			in = new ObjectInputStream(fis);
			TextNote object= (TextNote)in.readObject();
			result = object.content;
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public void exportTextToFile(String pathFolder) {
		//TODO
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		File file = new File( pathFolder + File.separator + this.getTitle().replaceAll(" ", "_")+ ".txt");
		// TODO
		try {
			fos = new FileOutputStream(file.getName());
			out = new ObjectOutputStream(fos);
			out.writeObject(this);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
		
	public String getContent() {
		return this.content;
	}

}
