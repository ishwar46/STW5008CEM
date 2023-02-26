package Five;

//You are given a 2D array containing coordinates and height of rectangle such that height[i]=[xi,yi,hi],
// where xi the x coordinate of left edge, yi represents x coordinate of right edge of rectangle and hi
// represents the height of the peaks of each rectangle. If you want to construct a border line over the
// peaks of rectangle represented in bar chart, return the key coordinates required to build a border line
// that contacts the peaks of the given chart.
//Note: key points are the left coordinates of shape representing peaks where you need to draw boarder line.

//Input: height={{1,4,10},{2,5,15},{5,8,12},{9,11,1},{11,13,15}}
        //Output: {{1,10},{2,15},{5,12},{8,0},{9,1},{11,15},{13,0}}

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Question5A {
    public static List<int[]> findSubarrays(int[] arr) {
        List<int[]> subArr = new ArrayList<>();
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int len = j - i + 1;
                int[] subarray = new int[len];
                for (int k = 0; k < len; k++) {
                    subarray[k] = arr[i + k];
                }
                subArr.add(subarray);
            }
        }
        return subArr;
    }

    public static void main(String[] args) {
        int[][] arr = new int[][]{{1, 4, 10}, {2, 5, 15}, {5, 8, 12}, {9, 11, 1}, {11, 13, 15}};
        int[] x = new int[arr.length];
        int[] y = new int[arr.length];
        int[] h = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            x[i] = arr[i][0];
            y[i] = arr[i][1];
            h[i] = arr[i][2];
        }
        List<int[]> subarraysX = findSubarrays(x);
        List<int[]> subarraysY = findSubarrays(y);
        List<int[]> subarraysH = findSubarrays(h);
        int lenght = subarraysX.size();
        int[] productArrayX = new int[lenght];
        int[] productArrayY = new int[lenght];
        int[] productArrayH = new int[lenght];
        int i = 0;
        for (int[] arrP : subarraysX) {
            int product = 1;
            for (int element : arrP) {
                product *= element;
            }
            productArrayX[i] = product;
            i++;
        }
        i = 0;
        for (int[] arrP : subarraysY) {
            int product = 1;
            for (int element : arrP) {
                product *= element;
            }
            productArrayY[i] = product;
            i++;
        }
        i = 0;
        for (int[] arrP : subarraysH) {
            int product = 1;
            for (int element : arrP) {
                product *= element;
            }
            productArrayH[i] = product;
            i++;
        }
        System.out.println(Arrays.toString(productArrayX));
        System.out.println(Arrays.toString(productArrayY));
        System.out.println(Arrays.toString(productArrayH));
    }
}



