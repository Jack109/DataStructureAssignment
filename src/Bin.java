import java.util.ArrayList;
import java.util.List;

public class Bin {
	double maxWeight;
	List<Parcel> parcels;
	double currentWeight;

	public Bin(double weight) {
		this.maxWeight = weight;
		this.currentWeight = 0;
		this.parcels = new ArrayList<Parcel>();
	}

	public boolean canFit(Parcel newParcel) {
		return this.maxWeight - this.currentWeight - newParcel.weight >= 0;
	}

	public double getScore(Parcel currentParcel) {
		return this.maxWeight - this.currentWeight - currentParcel.weight;
	}

	public void add(Parcel currentParcel) {
		this.parcels.add(currentParcel);
		this.currentWeight += currentParcel.weight;
	}

	public String toString() {
		return "Total weight = " + this.currentWeight
				+ " " + this.parcels;
	}
}
