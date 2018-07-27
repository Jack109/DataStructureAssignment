import java.util.List;

public class Parcel implements Comparable {
	double weight;
	int id;

	public Parcel(double weight) {
		this.weight = weight;
	}

	public String toString() {
		return "(Weight = " + this.weight + ")";
	}

	@Override
	public int compareTo(Object o) {
		Parcel other = (Parcel) o;
		if(this.weight > other.weight) return 1;
		else if(this.weight < other.weight) return -1;
		else return 0;

	}
}
