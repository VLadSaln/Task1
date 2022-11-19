import java.util.Arrays;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ThirdTasks {
    public static void main(String[] args) {
        System.out.println(solutions(1, 0, -1));
        System.out.println(findZip("all zip files are zipped"));
        System.out.println(checkPerfect(496));
        System.out.println(flipEndChars("Cat, dog, and mouse."));
        System.out.println(isValidHexCode("#CD5C5C"));
        System.out.println(same(new int[] { 1, 3, 4, 4, 4 }, new int[] { 2, 5, 7 }));
        System.out.println(isKaprekar(297));
        System.out.println(longestZero("01100001011000"));
        System.out.println(nextPrime(24));
        System.out.println(rightTriangle(145, 105, 100));

    }

    private static Integer solutions(int a, int b, int c) {
        double d = b * b - 4 * a * c;
        if (d > 0) {
            return 2;
        }
        if (d == 0) {
            return 1;
        }
        return 0;
    }

    private static Integer findZip(String str) {
        boolean flag = false;
        for (int i = 0; i < str.length() - 2; ++i) {
            if (str.substring(i, i + 3).equals("zip")) {
                if (flag) {
                    return i;
                }
                flag = true;
            }
        }
        return -1;
    }

    private static Boolean checkPerfect(int x) {
        int sum = 0;
        for (int i = 1; i <= x / 2; ++i) {
            sum += x % i == 0 ? i : 0;
        }
        return sum == x;
    }

    private static String flipEndChars(String str) {
        if (str.length() < 2) {
            return "Incompatible.";
        }
        if (str.charAt(0) == (str.charAt(str.length() - 1))) {
            return "Two's a pair.";
        }

        return str.charAt(str.length() - 1) + str.substring(1, str.length() - 1) + str.charAt(0);
    }

    private static Boolean isValidHexCode(String str) {
        Pattern pattern = Pattern.compile("#[A-Fa-f0-9]{6}");
        return pattern.matcher(str).find();
    }

    private static Boolean same(int[] arr1, int[] arr2) {
        Set<Integer> set1 = Arrays.stream(arr1).boxed().collect(Collectors.toSet());
        Set<Integer> set2 = Arrays.stream(arr2).boxed().collect(Collectors.toSet());

        return set1.size() == set2.size();
    }

    private static Boolean isKaprekar(int x) {
        int sqrt_x = x * x;
        String strX = Integer.toString(sqrt_x);
        Integer left;
        Integer right;
        if (strX.length() == 1) {
            return sqrt_x == x;
        }
        left = Integer.parseInt(strX.substring(0, strX.length() / 2));
        right = Integer.parseInt(strX.substring(strX.length() / 2));
        return left + right == x;
    }

    private static String longestZero(String str) {
        String count = "";
        String maxCount = "";
        for (int i = 0; i < str.length(); ++i) {
            if (str.charAt(i) == '0') {
                count += "0";
            } else {
                maxCount = count.length() > maxCount.length() ? count : maxCount;
                count = "";
            }
        }
        return maxCount;
    }

    private static Integer nextPrime(int x) {
        int i = x;
        while (true) {
            if (isPrime(i))
                return i;
            ++i;
        }
    }

    private static Boolean isPrime(int x) {
        for (int i = 2; i < Math.sqrt((double) x + 1); ++i) {
            if (x % i == 0)
                return false;
        }
        return true;
    }

    private static Boolean rightTriangle(int a, int b, int c) {
        return a * a + b * b == c * c || a * a + c * c == b * b || b * b + c * c == a * a;
    }
}