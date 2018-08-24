import java.util.ArrayList;

public class PointingList<T> {
	private int index = 0;
	private int size = 0;
	private T[] elements;
	private final int MAX_SIZE = 20;
	
	public PointingList() {
		this.elements = (T[])new Object[MAX_SIZE];
	}
	
	int getSize() {
		return this.size;
	}
	
	void moveToNext() {
		this.index++;
	}
	void moveToPrevious() {
		this.index--;
	}
	public T getCurrent() {		
		if(this.index == this.getSize()) {
			return null;
		}else {
			return this.elements[this.index];
		}
	}
	void add(T newElement) {
		//if(this.size == this.elements.length) {
		//	throw new Exception("Max length is reached.");
		//}
		this.elements[this.size] = newElement;
		this.size++;
	}
	void moveToFirst() {
		this.index = 0;
	}
	public void moveToLast() {
		this.index = this.getSize() - 1;
	}
	
	public String toString() {
		String result = "Number of bins used = " + this.getSize() + "\n";
		for(int i = 0; i < this.getSize(); i++) {
			result += "(" + i +")" + this.elements[i].toString() + "\n";
		}
		return result;
	}
}
