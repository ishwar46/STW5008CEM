// import necessary packages
package Six;
import java.util.*;

public class Question6A {

    // define the Huffman Node class
    class HuffmanNode {
        int data;
        char c;
        HuffmanNode left;
        HuffmanNode right;
    }

    // method to print the encoded huffman code
    public void printCode(HuffmanNode root, String s) {
        // check if a leaf node is reached, then print the huffman code for the letter
        if (root.left == null && root.right == null && Character.isLetter(root.c)) {
            System.out.println(root.c + ":" + s);
            return;
        }
        // traverse the left sub-tree, add 0 to the code and continue traversing
        printCode(root.left, s + "0");
        // traverse the right sub-tree, add 1 to the code and continue traversing
        printCode(root.right, s + "1");
    }

    // encoding the input string using huffman algorithm
    public HuffmanNode encode(char[] charArray, int[] charFreq) {
        int n = charArray.length;
        // initialize a priority queue
        PriorityQueue<HuffmanNode> q = new PriorityQueue<>(n, new MyComparator());
        // add each character and its frequency to the priority queue
        for (int i = 0; i < n; i++) {
            HuffmanNode hn = new HuffmanNode();
            hn.c = charArray[i];
            hn.data = charFreq[i];
            hn.left = null;
            hn.right = null;
            q.add(hn);
        }
        HuffmanNode root = null;
        // construct the huffman tree
        while (q.size() > 1) {
            // get the two nodes with the smallest frequencies
            HuffmanNode x = q.peek();
            q.poll();
            HuffmanNode y = q.peek();
            q.poll();
            // create a new node with the sum of frequencies of the two nodes
            HuffmanNode f = new HuffmanNode();
            f.data = x.data + y.data;
            f.c = '-';
            // make the two nodes as left and right children of the new node
            f.left = x;
            f.right = y;
            root = f;
            // add the new node to the priority queue
            q.add(f);
        }
        // print the huffman code for each letter
        printCode(root, "");
        return root;
    }

    // decoding the input string using the huffman tree
    public void decode(HuffmanNode root, String str) {
        ArrayList<Character> characters = new ArrayList<>();
        ArrayList<Integer> frequency = new ArrayList<>();
        int i = 0;
        while (i < str.length()) {
            HuffmanNode current = root;
            // traverse the huffman tree to find the letter
            while (current.c == '-') {
                if (str.charAt(i) == '0') {
                    current = current.left;
                    i++;
                } else {
                    current = current.right;
                    i++;
                }
            }
            char c = current.c;
            int f = current.data;
            characters.add(current.c);
            frequency.add(current.data);
        }
        // print the decoded letters and their frequencies
        printDecode(characters, frequency);
    }

    public void printDecode(ArrayList<Character> characters, ArrayList<Integer> frequencies) {
        for (int i = 0; i < characters.size(); i++) {
            System.out.println(characters.get(i) + ":" + frequencies.get(i));
        }
    }

    // comparator class for sorting the huffman nodes based on their frequency
    class MyComparator implements Comparator<HuffmanNode> {
        public int compare(HuffmanNode x, HuffmanNode y) {
            // used to sort the character in the sequence of r
            return x.data - y.data;
        }
    }

    // driver method
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of characters: ");
        int n = scanner.nextInt();

        char[] ch = new char[n];
        int[] freq = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter character " + (i + 1) + ": ");
            ch[i] = scanner.next().charAt(0);

            System.out.print("Enter frequency of character " + ch[i] + ": ");
            freq[i] = scanner.nextInt();
        }

        Question6A h = new Question6A();
        HuffmanNode hn = h.encode(ch, freq);
        System.out.println(hn.data);

        System.out.print("Enter the Huffman code to decode: ");
        String str = scanner.next();
        h.decode(hn, str);
    }
}
