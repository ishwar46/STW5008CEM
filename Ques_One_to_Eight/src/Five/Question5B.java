package Five;
//Assume an electric vehicle must go from source city s to destination city d. You can locate many
// service centers along the journey that allow for the replacement of batteries; however, each service
//center provides batteries with a specific capacity. You are given a 2D array in which servicecenter[i]=[xi,yj]
//indicates that the ith service center is xi miles from the source city and offers yj miles after the automobile
//can travel after replacing batteries at specific service centers. Return the number of times the car's
// batteries need to be replaced before arriving at the destination.
//Input: serviceCenters = [{10,60},{20,30},{30,30},{60,40}], targetMiles= 100, startChargeCapacity = 10
//Output: 2
//Explanation: The car can go 10 miles on its initial capacity; after 10 miles, the car replaces batteries
// with a capacity of 60 miles; and after travelling 50 miles, at position 60 we change batteries with a
// capacity of 40 miles; and ultimately, we may arrive at our destination.
public class Question5B {
    public static int batteryReplacement(int[][] serviceCenters, int targetMiles, int startChargeCapacity) {
        // Variables
        int batteriesReplaced = 0; // Number of batteries replaced
        int currentPosition = 0; // Current position of the car
        int remainingCapacity = startChargeCapacity; // Remaining capacity of the current battery

        int i = 0;
        // Loop through the service centers until the car reaches the target miles or runs out of service centers
        while (currentPosition < targetMiles && i < serviceCenters.length) {
            // Get the next service center
            int[] serviceCenter = serviceCenters[i];

            // Calculate the distance to the next service center
            int distance = serviceCenter[0] - currentPosition;

            // If the distance is greater than the remaining capacity of the battery, replace the battery and reset the remaining capacity
            if (distance > remainingCapacity) {
                batteriesReplaced++; // Increment the number of batteries replaced
                remainingCapacity = serviceCenter[1]; // Reset the remaining capacity to the new battery's capacity
            } else {
                // If the distance is less than or equal to the remaining capacity, subtract the distance from the remaining capacity and update it to the maximum of the remaining capacity and the next service center's capacity
                remainingCapacity -= distance;
                remainingCapacity = Math.max(remainingCapacity, serviceCenter[1]);
                i++; // Move to the next service center
            }

            currentPosition = serviceCenter[0]; // Update the current position to the position of the current service center
        }

        // If the car has not reached the target miles and the remaining capacity is not enough to reach the target miles, replace the battery
        if (currentPosition < targetMiles && remainingCapacity < targetMiles - currentPosition) {
            batteriesReplaced++; // Increment the number of batteries replaced
        }

        return batteriesReplaced; // Return the number of batteries replaced
    }


    public static void main(String[] args) {
        int[][] arr = new int[][]{{10, 60}, {20, 30}, {30, 30}, {60, 40}}; // service centers
        int targetMiles = 100; // destination
        int startChargeCapacity = 10; // initial battery capacity
        int count = 0; // number of battery replacements
        int currentPosition = 0; // current position of the vehicle
        int currentCharge = startChargeCapacity; // current battery capacity
        // iterate through all the service centers
        for (int[] serviceCenter : arr) {
            int distance = serviceCenter[0] - currentPosition; // calculate the distance to the next service center
            int remainingDistance = distance - currentCharge; // calculate the remaining distance that can be covered with the current battery capacity
            // if the remaining distance is positive, i.e., the battery is not enough to reach the next service center
            if (remainingDistance > 0) {
                int requiredCharge = remainingDistance; // initialize the required charge
                // iterate through the previous service centers to find the one that offers the maximum capacity
                for (int i = 0; i < count; i++) {
                    int[] previousServiceCenter = arr[i];
                    if (previousServiceCenter[1] > requiredCharge) {
                        requiredCharge = previousServiceCenter[1];
                    }
                }
                // if the required charge is greater than the current battery capacity, replace the battery
                if (requiredCharge > currentCharge) {
                    count++;
                    currentCharge = requiredCharge; // update the current battery capacity
                } else { // otherwise, consume the battery
                    currentCharge -= remainingDistance;
                }
            }
            currentPosition = serviceCenter[0]; // update the current position of the vehicle
            currentCharge -= distance; // consume the battery
            // if the battery is not enough to reach the next service center
            if (currentCharge < 0) {
                count++; // replace the battery
                currentCharge = serviceCenter[1]; // update the current battery capacity
            }
            // if the vehicle has reached the destination
            if (currentPosition == targetMiles) {
                break;
            }
        }
        System.out.println(count); // print the number of battery replacements
    }
}

