import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PointingList<T> {
	private int index = 0;
	private int size = 0;
	private T[] elements;
	private final int MAX_SIZE = 5;
	
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
	public T current() {		
		if(this.index == this.getSize()) {
			return null;
		} else {
			return this.elements[this.index];
		}
	}
	void add(T newElement) {
		if(this.size == this.elements.length) {
			T[] newArray = (T[])new Object[this.elements.length * 2];
			System.arraycopy(this.elements, 0, newArray, 0, this.elements.length);
			this.elements = newArray;
		}
		
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
	
	public List<T> toList() {
		ArrayList<T> result = new ArrayList<T>();
		for(int i = 0; i < this.getSize(); i++)
			result.add(this.elements[i]);
		return result;
	}
}
