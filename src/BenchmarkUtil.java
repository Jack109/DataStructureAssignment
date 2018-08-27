import java.util.Random;
import java.util.Stack;

public class BenchmarkUtil {
    private static Random rand = new Random();
    public static Stack<Parcel> getRandomParcels(double maximumCapacity, int count) {
        Stack<Parcel> parcels = new Stack<Parcel>();
        for (int j = 0; j < count; j++) {
            parcels.push(new Parcel(getRandomWeight(maximumCapacity)));
        }
        return parcels;
    }

    private static double getRandomWeight(double maximumCapacity) {
        while (true) {
            double weight = rand.nextDouble();
            if (weight <= maximumCapacity) {
                return weight;
            }
        }
    }

}
