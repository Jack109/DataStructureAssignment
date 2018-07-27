import java.util.ArrayList;

public class MyLinkedList<T> {
	private int index;
	private ArrayList<T> elements = new ArrayList<T>();
	
	int getSize() {
		return this.elements.size();
	}
	
	void moveToNext() {
		this.index++;
	}
	void moveToPrevious() {
		this.index--;
	}
	public T getCurrent() {		
		if(this.index == this.elements.size()) {
			return null;
		}else {
			return this.elements.get(this.index);
		}
	}
	void add(T newElement) {
		this.elements.add(newElement);
	}
	void moveToFirst() {
		this.index = 0;
	}
	public void moveToLast() {
		this.index = elements.size() - 1;
	}
	
	public String toString() {
		String result = "";
		int index = 0;
		for (T element : elements) {
			result += "(" + index +")" + element.toString() + "\n";
			index++;
		}
		return result;
	}
}
