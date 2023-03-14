package Seven;

public class Question7A {

    // Inner class that represents a worker thread
    private int[][] A; // 2 x 4

    private int[][] B; // 4 x 2
    private int[][] C; // 2 x 2
    private int n;
    private int numThreads;

    // Constructor that initializes the instance variables
    public Question7A(int[][] A, int[][] B, int n, int numThreads) {
        this.A = A;
        this.B = B;
        this.n = n;
        this.numThreads = numThreads;
        this.C = new int[n][n];
    }

    // Method to perform matrix multiplication using multiple threads
    public void multiply() {
        int numRowsPerThread = n / numThreads;
        Thread[] threads = new Thread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            int startRow = i * numRowsPerThread;
            int endRow = (i == numThreads - 1) ? n : startRow + numRowsPerThread;
            threads[i] = new Thread(new Worker(startRow, endRow));
            threads[i].start();
        }
        for (int i = 0; i < numThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Method that performs matrix multiplication for the chunk of matrix A assigned to the worker thread

    private class Worker implements Runnable {
        private int startRow;
        private int endRow;

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

    //Main Method
        public static void main(String[] args) {
            int[][] A = {{1, 2, 3, 4}, {5, 6, 7, 8}};
            int[][] B = {{1, 0}, {0, 1}, {1, 1}, {1, 1}};
            int n = 2;
            int numThreads = 2;
            Question7A matrixMultiplier = new Question7A(A, B, n, numThreads);
            matrixMultiplier.multiply();
            int[][] C = matrixMultiplier.C;
            for (int i = 0; i < C.length; i++) {
                for (int j = 0; j < C[0].length; j++) {
                    System.out.print(C[i][j] + " ");
                }
                System.out.println();
            }
        }
    }

