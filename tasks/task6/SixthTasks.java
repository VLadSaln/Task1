import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class SixthTasks {
    public static void main(String[] args) {
        System.out.println(bell(3));
        System.out.println(translateWord("apple"));
        System.out.println(translateSentence("I like to eat honey waffles."));
        System.out.println(validColor("rgb(255,256,255)"));
        System.out.println(stripUrlParams("https://edabit.com?a=1&b=2&a=2", new String[] { "b" }));
        System.out.println(Arrays.toString(getHashtags("Hey Parents, Surprise, Fruit Juice Is Not Fruit")));
        System.out.println(longestNonRepeatingString("abcabcbb"));
        System.out.println(convertToRoman(312));
        System.out.println(formula("4+2=6=3+3"));
        System.out.println(palindromeDescendant(11211230));
    }

    private static int bell(int n) {
        int[][] bell = new int[n + 1][n + 1];
        bell[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            bell[i][0] = bell[i - 1][i - 1];
            for (int j = 1; j <= i; j++)
                bell[i][j] = bell[i - 1][j - 1] + bell[i][j - 1];
        }
        return bell[n][0];
    }

    private static boolean isVowel(char ch) {
        return (ch == 'A' || ch == 'a' || ch == 'E' || ch == 'e' || ch == 'I' || ch == 'i' || ch == 'O' || ch == 'o'
                || ch == 'U' || ch == 'u');
    }

    private static String translateWord(String string) {
        int stringlength = string.length();
        int index = -1;
        if (isVowel(string.charAt(0)))
            return string + "yay";
        for (int i = 0; i < stringlength; i++) {
            if (isVowel(string.charAt(i))) {
                index = i;
                break;
            }
        }
        if (index == -1)
            return "-1";
        return string.substring(index) + string.substring(0, index) + "ay";
    }

    private static String translateSentence(String string) {
        String[] words = string.split(" ");
        String newSentence = "";
        boolean isCapitalFlag;
        boolean isDotFlag;
        for (String string2 : words) {
            isCapitalFlag = false;
            isDotFlag = false;
            if (Character.isUpperCase(string2.charAt(0))) {
                isCapitalFlag = true;
                string2 = string2.toLowerCase();
            }
            if (string2.charAt(string2.length() - 1) == '.') {
                string2 = string2.substring(0, string2.length() - 1);
                isDotFlag = true;
            }
            String translated = translateWord(string2);
            if (isCapitalFlag)
                translated = translated.substring(0, 1).toUpperCase() + translated.substring(1);
            if (isDotFlag)
                translated = translated + ".";
            newSentence += translated + " ";
        }
        return newSentence;
    }

    private static boolean validColor(String str) {
        Pattern pattern;
        String rgb_color_regex = "^rgb\\(\\s*(0|[1-9]\\d?|1\\d\\d?|2[0-4]\\d|25[0-5])%?\\s*,\\s*(0|[1-9]\\d?|1\\d\\d?|2[0-4]\\d|25[0-5])%?\\s*,\\s*(0|[1-9]\\d?|1\\d\\d?|2[0-4]\\d|25[0-5])%?\\s*\\)$";
        String rgba_color_regex = "^rgba\\(\\s*(0|[1-9]\\d?|1\\d\\d?|2[0-4]\\d|25[0-5])%?\\s*,\\s*(0|[1-9]\\d?|1\\d\\d?|2[0-4]\\d|25[0-5])%?\\s*,\\s*(0|[1-9]\\d?|1\\d\\d?|2[0-4]\\d|25[0-5])%?\\s*,\\s*((0.[1-9])|[01])\\s*\\)$";
        if (str.contains("rgba"))
            pattern = Pattern.compile(rgba_color_regex);
        else
            pattern = Pattern.compile(rgb_color_regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    private static String stripUrlParams(String url, String[] paramsToStrip) {
        if (!url.contains("?"))
            return url;
        String[] parts = url.split("\\?");
        StringBuilder sb = new StringBuilder(parts[0]);
        String[] params = parts[1].split("&");
        HashMap<String, String> map = new HashMap();
        for (String param : params) {
            String[] parm = param.split("=");
            map.put(parm[0], parm[1]);
        }

        LinkedHashSet<String> strip = new LinkedHashSet<>();

        if (paramsToStrip != null)
            strip.addAll(Arrays.asList(paramsToStrip));

        sb.append("?");
        int k = 1;

        for (String key : map.keySet()) {
            if (!strip.contains(key)) {
                if (k > 1)
                    sb.append("&");
                sb.append(key).append("=").append(map.get(key));
                k++;
            }
        }
        return sb.toString();
    }

    private static String[] getHashtags(String str) {
        int count = 3;
        while (str.indexOf(",") != -1)
            str = str.replace(",", "");
        if (str.split(" ").length < 3)
            count = str.split(" ").length;
        String[] toReturn = new String[count];
        for (int i = 0; i < count; i++) {
            String longest = Arrays.stream(str.split(" "))
                    .max(Comparator.comparingInt(String::length))
                    .orElse(null);
            str = str.replace(longest, "");
            toReturn[i] = "#" + longest.toLowerCase();
        }
        return toReturn;
    }

    private static int ulam(int n) {
        Vector<Integer> arr = new Vector<Integer>();

        arr.add(1);

        arr.add(2);

        for (int i = 3; i < 10000; i++) {

            int count = 0;

            for (int j = 0; j < arr.size() - 1; j++) {

                for (int k = j + 1; k < arr.size(); k++) {

                    if (arr.get(j) + arr.get(k) == i) {

                        count++;
                    }
                    if (count > 1)
                        break;
                }
                if (count > 1)
                    break;
            }

            if (count == 1) {
                arr.add(i);
            }
        }
        return arr.get(n) - 2;
    }

    private static String longestNonRepeatingString(String input) {
        Map<Character, Integer> visited = new HashMap<>();
        String output = "";
        for (int start = 0, end = 0; end < input.length(); end++) {
            char currChar = input.charAt(end);
            if (visited.containsKey(currChar)) {
                start = Math.max(visited.get(currChar) + 1, start);
            }
            if (output.length() < end - start + 1) {
                output = input.substring(start, end + 1);
            }
            visited.put(currChar, end);
        }
        return output;
    }

    private final static TreeMap<Integer, String> map = new TreeMap<Integer, String>();
    static {
        map.put(1000, "M");
        map.put(900, "CM");
        map.put(500, "D");
        map.put(400, "CD");
        map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");
    }

    private static String convertToRoman(int number) {
        int l = map.floorKey(number);
        if (number == l) {
            return map.get(number);
        }
        return map.get(l) + convertToRoman(number - l);
    }

    public static int calc(String a) { // processing mathematics
        String newA = a.replaceAll(" ", "");
        String[] val;
        if (newA.contains("*")) {
            val = newA.split("\\*");
            return (Integer.parseInt(val[0]) * Integer.parseInt(val[1]));
        }
        if (newA.contains("+")) {
            val = newA.split("\\+");
            return (Integer.parseInt(val[0]) + Integer.parseInt(val[1]));
        }
        if (newA.contains("/")) {
            val = newA.split("/");
            return (Integer.parseInt(val[0]) / Integer.parseInt(val[1]));
        }
        if (newA.contains("-")) {
            val = newA.split("\\-");
            return (Integer.parseInt(val[0]) - Integer.parseInt(val[1]));
        }
        else return Integer.parseInt(newA);
    }

    public static boolean formula(String a) { // check formula to rightness
        String[] values = a.split("=");
        for (int i=0; i<values.length-1; i++) {
            // condition to return
            if (calc(values[i]) != calc(values[i+1]))
                return false;
        }
        return true;
    }

    private static boolean palindromeDescendant(int num) {
        String ns = Integer.toString(num);
        String rs = "";
        for (int i = 0; i < ns.length(); i++)
            rs = ns.charAt(i) + rs;

        for (int i = 0; ns.length() >= 2; i++) {
            if (ns.equals(rs))
                return true;
            ns = "";
            for (int y = rs.length() - 1; y > 0; y = y - 2)
                ns += Character.getNumericValue(rs.charAt(y)) + Character.getNumericValue(rs.charAt(y - 1));
            rs = "";
            for (int x = 0; x < ns.length(); x++)
                rs = ns.charAt(x) + rs;
        }
        return false;
    }
}
