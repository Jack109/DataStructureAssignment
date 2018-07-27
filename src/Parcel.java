import java.util.List;

public class Parcel {
	double weight;
	int id;

	public Parcel(double weight) {
		this.weight = weight;
	}

	public String toString() {
		return "(Weight = " + this.weight + ")";
	}
}
