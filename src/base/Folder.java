package base;

import java.util.ArrayList;
import java.util.Objects;

public class Folder {
	private ArrayList<Note>notes;
	private String name;
	
	public Folder(String name) {
		notes = new ArrayList<Note>();
		this.name = name;
	}
	
	public void addNote(Note note) {
		notes.add(note);
	}
	
	public String getName() {
		return name;
	}
	
	public ArrayList<Note> getNotes() {
		return notes;
	}
	
	@Override
	public String toString() {
		int nText = 0;
		int nImage = 0;
		
		for ( Note note : notes) {
			if (note instanceof TextNote) {
				nText++;
			}
			else if (note instanceof ImageNote) {
				nImage++;
			}
		}
		return name + ":" + nText + ":" + nImage;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if(!(obj instanceof Folder)) {
			return false;
		}
		Folder other = (Folder) obj;
		return Objects.equals(name, other.name);
	}
}