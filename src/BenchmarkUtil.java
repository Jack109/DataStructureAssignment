import java.util.Random;
import java.util.Scanner;
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

    public static int readPositiveInteger() {
        while(true) {
            try {
                Scanner scanner = new Scanner( System.in );
                int result = scanner.nextInt();
                if(result <= 0) {
                    System.out.println("The number must be positive.");
                    throw new Exception();
                }
                scanner.close();
                return result;
            } catch(Exception ex) {
                System.out.println("Please enter a valid integer: ");
            }
        }
    }

}
