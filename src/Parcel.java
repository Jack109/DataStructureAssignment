public class Parcel implements Weightable {
	private double weight;
	private int id;

	public double getWeight() {
		return weight;
	}

	public int id() {
		return id;
	}

	public Parcel(double weight) {
		this.weight = weight;
	}

	public String toString() {
		return "(Weight = " + this.weight + ")";
	}

	@Override
	public int compareTo(Object o) {
		Parcel other = (Parcel) o;
		if (this.weight > other.weight)
			return 1;
		else if (this.weight < other.weight)
			return -1;
		else
			return 0;
	}
}
