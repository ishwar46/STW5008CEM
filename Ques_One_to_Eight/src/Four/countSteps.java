package Four;

import java.util.LinkedList;

public class countSteps {
    public int countStepsToSort(LinkedList<Integer> linkedList) {
        int steps = 0;
        int size = linkedList.size();
        int[] array = new int[size];

        // Convert the linked list to an array
        for (int i = 0; i < size; i++) {
            array[i] = linkedList.get(i);
        }

        boolean sorted = false;
        while (!sorted) {
            sorted = true;
            for (int i = 1; i < size; i++) {
                if (array[i - 1] > array[i]) {
                    sorted = false;
                    // Remove the element at index i
                    for (int j = i; j < size - 1; j++) {
                        array[j] = array[j + 1];
                    }
                    size--;
                    i--;
                }
            }
            if (!sorted) {
                steps++;
            }
        }

        return steps;
    }

    public static void main(String[] args) {
        // Create a linked list of integers
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(5);
        linkedList.add(1);
        linkedList.add(4);
        linkedList.add(2);
        linkedList.add(3);

        // Count the steps required to sort the linked list
        countSteps countSteps = new countSteps();

        // Print the result
        System.out.println("Steps required to sort the linked list: " + countSteps.countStepsToSort(linkedList));
    }


}
