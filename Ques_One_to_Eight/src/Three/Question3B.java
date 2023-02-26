package Three;

public class Question3B {
    public static boolean matchesPattern(String a, String pattern) {
        int i = 0, j = 0, n = a.length(), m = pattern.length();
        while (i < n && j < m) {
            if (pattern.charAt(j) == '@') {
                int k = j + 1;
                while (k < m && pattern.charAt(k) != '@' && pattern.charAt(k) != '#') {
                    k++;
                }
                String sub = pattern.substring(j + 1, k);
                int idx = a.indexOf(sub, i);
                if (idx == -1) {
                    return false;
                }
                i = idx + sub.length();
                j = k;
            } else if (pattern.charAt(j) == '#') {
                i++;
                j++;
            } else if (a.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else {
                return false;
            }
        }
        return i == n && j == m;
    }

    public static void main(String[] args) {
        System.out.println(matchesPattern("tt", "@")); // true
        System.out.println(matchesPattern("ta", "t")); // false
        System.out.println(matchesPattern("ta", "t#")); // true
    }
}


