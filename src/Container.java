import java.util.ArrayList;
import java.util.List;

public class Container<T extends Weightable> {
	private final double maxCapacity;
	private List<T> elements;
	private double usedCapacity;
	
	public Container(double maxCapacity) {
		this.maxCapacity = maxCapacity;
		this.usedCapacity = 0;
		this.elements = new ArrayList<T>();
	}

	public boolean canFit(T newElement) throws Exception {
		validateWeight(newElement);
		return this.maxCapacity  - 
			   this.usedCapacity - 
			   newElement.getWeight() >= 0;
	}

	/**
	 * NOTE: getScore method is only used by Best Fit algorithm
	 * Negative score indicates this container cannot fit newElement
	 * Zero score indicates newElement can perfectly fit in this container without leaving any spaces
	 * Positive score indicates the spaces left after newElement is put into this container
	 * So, lower score (which is non-negative) is better
	 * */
	public double getScore(T newElement) throws Exception {
		validateWeight(newElement);
		return this.maxCapacity  - 
			   this.usedCapacity - 
			   newElement.getWeight();
	}

	public void add(T newElement) throws Exception {
		validateWeight(newElement);
		this.elements.add(newElement);
		this.usedCapacity += newElement.getWeight();
	}

	public String toString() {
		return "Total weight = " + this.usedCapacity
				+ " " + this.elements;
	}
	
	private void validateWeight(T newElement) throws Exception {
		if(newElement.getWeight() > this.maxCapacity) {
			throw new Exception("The max capacity is " + this.maxCapacity + " but the new element has weight of " + newElement.getWeight());
		}
	}
}
