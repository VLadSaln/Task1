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


public class FifthTasks {

    public static void main(String[] args) {
        encrypt("Hello");
        System.out.println(decrypt(new int[] { 72, 33, -73, 84, -12, -3, 13, -13, -68 }));
        System.out.println(canMove("Rook", "A8", "H8"));
        System.out.println(canComplete("butl", "beautiful"));
        System.out.println(sumDigProd(16, 28));
        sameVowelGroup(new String[] { "toe", "ocelot", "maniac" });
        System.out.println(validateCard(1234567890123456l));
        System.out.println(numToEng(126));
        System.out.println(getSha256Hash("password123"));
        System.out.println(correctTitle("jOn SnoW, kINg IN thE noRth."));
        System.out.println(hexLattice(7));
        

    }

private static void encrypt(String string) {
        int len = string.length();
        int code = 0;
        int[] arrEncrypt = new int[len];
        for (int j = 0; j < len; j++) {
            arrEncrypt[j] = string.charAt(j) - code;
            code = string.charAt(j);
            System.out.print(arrEncrypt[j]);
            if (j > 0) {
                System.out.print(" ");
            }
        }
    }

    private static String decrypt(int[] numbers) {
        String string = "";
        int code = 0;
        for (int j = 0; j < numbers.length; j++) {
            string += (char) (numbers[j] + code);
            code = numbers[j] + code;
        }
        return string;
    }

    private static boolean canMove(String p, String c, String t) {
        if (p.equals("Пешка")) {
            if (c.charAt(0) == t.charAt(0)) {

                if (c.charAt(1) == '2' && t.charAt(1) == '4') {
                    return true;
                }

                if (c.charAt(1) == '7' && t.charAt(1) == '5') {
                    return true;
                }

                if (Math.abs((int) c.charAt(1) - (int) t.charAt(1)) == 1) {
                    return true;
                }
            }
        }

        if (p.equals("Слон")) {
            if (Math.abs((int) c.charAt(0) - (int) t.charAt(0)) == Math.abs((int) c.charAt(1) - (int) t.charAt(1))) {
                return true;
            }
        }

        if (p.equals("Ладья")) {
            if (c.charAt(0) == t.charAt(0)
                    || c.charAt(1) == t.charAt(1)) {
                return true;
            }
        }

        if (p.equals("Конь")) {

            if (Math.abs((int) c.charAt(0) - (int) t.charAt(0)) == 2
                    && Math.abs((int) c.charAt(1) - (int) t.charAt(1)) == 1) {
                return true;
            }

            if (Math.abs((int) c.charAt(1) - (int) t.charAt(1)) == 2
                    && Math.abs((int) c.charAt(0) - (int) t.charAt(0)) == 1) {
                return true;
            }
        }
        if (p.equals("Король")) {
            if (Math.abs((int) c.charAt(0) - (int) t.charAt(0)) <= 1
                    && Math.abs((int) c.charAt(1) - (int) t.charAt(1)) <= 1) {
                return true;
            }
        }
        if (p.equals("Королева")) {
            if (Math.abs((int) c.charAt(0) - (int) t.charAt(0)) == Math.abs((int) c.charAt(1) - (int) t.charAt(1))) {
                return true;
            }

            if (c.charAt(0) == t.charAt(0)
                    || c.charAt(1) == t.charAt(1)) {
                return true;
            }

        }

        return false;
    }

    private static boolean canComplete(String start, String string) {
        int in = 0;

        for (int w = 0; w < string.length(); w++) {
            if (string.charAt(w) == start.charAt(in))
                in++;
        }

        if (in == start.length())
            return true;
        else
            return false;
    }

    private static int sumDigProd(int... i) {
        int s = 0;
        for (int n : i)
            s += n;
        if (s < 10)
            return s;
        int p = 1;
        while (s > 0) {
            p *= s % 10;
            s /= 10;
        }
        return sumDigProd(p);
    }

    private static void sameVowelGroup(String[] strings) {

        char[] chars = strings[0].toCharArray();
        Set<Character> v = new HashSet<>();
        for (char _char : chars) {
            if (_char == 'a' || _char == 'e' || _char == 'i' || _char == 'o' || _char == 'u') {
                v.add(_char);
            }
        }
        for (String _string : strings) {
            boolean isValid = true;
            for (char c : _string.toCharArray()) {
                if ((c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')) {
                    if (!v.contains(c)) {
                        isValid = false;
                    }
                }
            }
            if (isValid) {
                System.out.println(_string + " ");
            }
        }

    }

    private static boolean validateCard(long numero) {

        int strLen = String.valueOf(numero).length();
        if ((strLen < 14) || (strLen > 19))
            return false;
        int last_digit = (int) (numero % 10);
        StringBuilder stringNumber = new StringBuilder(String.valueOf(numero / 10)).reverse();
        int temp = 0;
        for (int i = 0; i < stringNumber.length(); i = i + 2) {
            temp = Integer.parseInt(stringNumber.charAt(i) + "") * 2;
            if (temp / 10 > 0) {
                temp = temp / 10 + temp % 10;
            }
            stringNumber.replace(i, i + 1, String.valueOf(temp));
        }
        temp = 0;
        for (char x : stringNumber.toString().toCharArray()) {
            temp += Integer.parseInt(x + "");
        }

        return ((10 - (temp % 10)) == last_digit);

    }

    private static String numToEng(int n) {
        String[] d0_9 = { "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine" };
        String[] d10_19 = { "ten", "eleven", "twelve", "thirten", "fourten", "fifteen", "sixteen", "seventeen", "eighten", "nineteen" };
        String[] d20_90 = { "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety" };
        String num = Integer.toString(n);

        int dCount = num.length();

        // for 1 length
        if (dCount == 1) {return d0_9[Integer.parseInt(num)];}
        // for 2 length
        if ((dCount == 2) && (n < 20)) {return d10_19[Integer.parseInt(num.substring(1, 2))];}
        //for other 2 length
        if ((dCount == 2) && (num.charAt(1) == '0') && (n>19)) {return d20_90[Integer.parseInt(num.substring(0, 1))-1];}
        // for 3 length
        if (dCount == 3) {
            StringBuilder sb = new StringBuilder();
            int[] digits = new int[3];
            int k = 0;
            // separating on each letter
            while (n != 0) {
                digits[k] = n % 10;
                n = n / 10;
                k++;
            }
            // creating ENG for hundred position
            sb.append(d0_9[digits[2]]).append(" hundred ");
            if (digits[1] == 1) {
                int c = digits[1] + digits[2];
                sb.append(d10_19[c]);
                return sb.toString();
            }
            // creating ENG for ten and one position
            if (digits[1] > 1)
                sb.append(d20_90[digits[1] - 1]).append(" ");
            if (digits[0] > 0)
                sb.append(d0_9[digits[0]]);
            return sb.toString();
        }
        return "" ;
    }

    private static String getSha256Hash(String str) {
        return hash(str, "SHA-256");
    }

    private static String hash(String msg, String algo) {
        String result = "";
        MessageDigest md;
        try {
            md = MessageDigest.getInstance(algo);
            byte[] hashes = md.digest(msg.getBytes());
            for (int i = 0; i < hashes.length; i++) {
                String hex = Integer.toHexString(0xff & hashes[i]);
                if (hex.length() == 1)
                    result += 0;
                result += hex;
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static String correctTitle(String str) {
        String[] strings = str.split(" ");
        String output = "";
        for (int i = 0; i < strings.length; i++) {
            if (i > 0) {
                output += " ";
            }
            String[] _strings = strings[i].split("-");
            for (int j = 0; j < _strings.length; j++) {
                if (j > 0) {
                    output += "-";
                }
                if (_strings[j].equalsIgnoreCase("and")
                        || _strings[j].equalsIgnoreCase("the")
                        || _strings[j].equalsIgnoreCase("of")
                        || _strings[j].equalsIgnoreCase("in")) {
                    output += _strings[j].toLowerCase();
                } else {
                    output += _strings[j].substring(0, 1).toUpperCase();
                    output += _strings[j].substring(1).toLowerCase();
                }
            }
        }

        return output;
    }

    private static String hexLattice(int n) {
        int i = 0;
        boolean isHex = false;
        while (3 * i * (i + 1) + 1 <= n) {
            if (3 * i * (i + 1) + 1 == n)
                isHex = true;
            i++;
        }
        String str = "";
        if (isHex) {
            int l = i;
            int m = i;
            String str2;
            for (int j = 0; j < 2 * i - 1; j++) {
                str += "\n";
                str2 = "";
                for (int k = 1; k < m; k++) {
                    str2 += " ";
                }
                str += str2;
                for (int k = 0; k < l; k++) {
                    str += " o";
                }
                str += str2 + " ";
                l += (j < i - 1) ? 1 : -1;
                m += (j < i - 1) ? -1 : 1;
            }
            str = str.replaceFirst("\n", "");
            return str;
        } else
            return "Invalid";
    }
}