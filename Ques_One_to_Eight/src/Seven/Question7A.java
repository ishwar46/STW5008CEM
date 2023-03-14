package Seven;

public class Question7A {
    public static void main(String[] args) {
        // Define two matrices A and B
        int[][] A = {{1, 2, 3, 4}, {5, 6, 7, 8}}; // 2 x 4
        int[][] B = {{9, 10}, {11, 12}, {13, 14}, {15, 16}}; // 4 x 2

        // Initialize a result matrix C
        int[][] C = new int[2][2]; // 2 x 2

        // Set the number of threads to be used for matrix multiplication
        int numThreads = 2; // 2 threads

        // Create an instance of the Question7A class with A, B, C, and numThreads as arguments
        Question7A matrixMultiplication = new Question7A(A, B, C, numThreads);

        // Call the multiply() method to perform matrix multiplication
        matrixMultiplication.multiply();

        // Print the resulting matrix C on the console
        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < C[0].length; j++) {
                System.out.print(C[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Instance variables for matrix A, B, C, and the number of threads
    private int[][] A;
    private int[][] B;
    private int[][] C;
    private int numThreads;

    // Constructor that initializes the instance variables
    public Question7A(int[][] A, int[][] B, int[][] C, int numThreads) {
        this.A = A;
        this.B = B;
        this.C = C;
        this.numThreads = numThreads;
    }

    // Method to perform matrix multiplication using multiple threads
    public void multiply() {
        // Divide matrix A into equal-sized chunks based on the number of threads specified
        int numRowsPerThread = A.length / numThreads;

        // Create an array of worker threads
        Thread[] threads = new Thread[numThreads];

        // Create a worker thread for each chunk of matrix A and start the threads
        for (int i = 0; i < numThreads; i++) {
            int startRow = i * numRowsPerThread;
            int endRow = (i == numThreads - 1) ? A.length : startRow + numRowsPerThread;
            threads[i] = new Thread(new Worker(startRow, endRow));
            threads[i].start();
        }

        // Wait for all the worker threads to finish
        for (int i = 0; i < numThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Inner class that represents a worker thread
    private class Worker implements Runnable {
        private int startRow;
        private int endRow;

        // Constructor that initializes the starting and ending row indices for the chunk of matrix A
        public Worker(int startRow, int endRow) {
            this.startRow = startRow;
            this.endRow = endRow;
        }

        // Method that performs matrix multiplication for the chunk of matrix A assigned to the worker thread
        @Override
        public void run() {
            for (int i = startRow; i < endRow; i++) {
                for (int j = 0; j < B[0].length; j++) {
                    for (int k = 0; k < B.length; k++) {
                        C[i][j] += A[i][k] * B[k][j];
                    }
                }
            }
        }
    }
}
