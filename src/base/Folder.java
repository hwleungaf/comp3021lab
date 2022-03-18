package base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Collections;

public class Folder implements Comparable<Folder>, Serializable{
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
	
	public int compareTo(Folder o) {
		return this.getName().compareTo(o.getName());
	}
	
	public void sortNotes() {
		Collections.sort(this.notes);
	}
	
	public List<Note> searchNotes(String keywords) {
		String[] tokens = keywords.split(" ", 0);
		List<Note> noteList = new ArrayList<Note>();
		ArrayList<String> searchNames = new ArrayList<String>();
		ArrayList<String> searchOrNames = new ArrayList<String>();
		for (int i = 0; i < tokens.length-1; i++) {
			if (tokens[i+1]. equalsIgnoreCase("or") || tokens[i+1].equals("OR") ) {
				searchOrNames.add(tokens[i]);
				searchOrNames.add(tokens[i+2]);
				i += 2;
			}
			else {
				searchNames.add(tokens[i]);
			}
		}
		
		for (Note o : this.notes) {
			boolean matched = true;
			boolean orMatched = true;
			
			for (String words : searchNames) {
				if ( o.getTitle().indexOf(words) == -1 ) {
					matched = false;
				}
				if (o instanceof TextNote) {
					if ( ((TextNote) o).getContent().indexOf(words) != -1) {
						matched = true;
					}
				}
				if (!matched) {
					break;
				}
			}
			
			if (searchOrNames.isEmpty()) {
				orMatched = true;
			}else {
				for (int i = 0; i < searchOrNames.size(); i+=2) {
					
					if ( (o.getTitle().toLowerCase()).indexOf(searchOrNames.get(i).toLowerCase()) == -1 &&
							(o.getTitle().toLowerCase()).indexOf(searchOrNames.get(i+1).toLowerCase()) == -1) {
						orMatched = false;

					}
					if (o instanceof TextNote) {
						if ( (((TextNote) o).getContent().toLowerCase()).indexOf(searchOrNames.get(i).toLowerCase()) != -1 ||
								(((TextNote) o).getContent().toLowerCase()).indexOf(searchOrNames.get(i+1).toLowerCase()) != -1) {
							orMatched = true;
							
							
						}
					}
					if (!orMatched) {
						break;
					}
				}
			}
			
			if (matched && orMatched) {
				noteList.add(o);
	
			}
		}
		return noteList;
	}
}
