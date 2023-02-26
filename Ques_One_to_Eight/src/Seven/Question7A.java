package Seven;


public class Question7A {
    public static void main(String[] args) {
        int[][] A = {{1, 2, 3, 4}, {5, 6, 7, 8}};
        int[][] B = {{9, 10}, {11, 12}, {13, 14}, {15, 16}};
        int[][] C = new int[2][2];
        int numThreads = 2;

        Question7A matrixMultiplication = new Question7A(A, B, C, numThreads);
        matrixMultiplication.multiply();

        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < C[0].length; j++) {
                System.out.print(C[i][j] + " ");
            }
            System.out.println();
        }
    }

    private int[][] A;
    private int[][] B;
    private int[][] C;
    private int numThreads;

    public Question7A(int[][] A, int[][] B, int[][] C, int numThreads) {
        this.A = A;
        this.B = B;
        this.C = C;
        this.numThreads = numThreads;
    }

    public void multiply() {
        int numRowsPerThread = A.length / numThreads;
        Thread[] threads = new Thread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            int startRow = i * numRowsPerThread;
            int endRow = (i == numThreads - 1) ? A.length : startRow + numRowsPerThread;
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

    private class Worker implements Runnable {
        private int startRow;
        private int endRow;

        public Worker(int startRow, int endRow) {
            this.startRow = startRow;
            this.endRow = endRow;
        }

        @Override
        public void run() {
            for (int i = startRow; i < endRow; i++) {
                for (int j = 0; j < B[0].length; j++) {
                    for (int k = 0; k < A[0].length; k++) {
                        C[i][j] += A[i][k] * B[k][j];
                    }
                }
            }
        }
    }
}


