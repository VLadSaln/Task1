import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Set;

public class FourthTasks {

    public static void main(String[] args) {
         System.out.println(textProcessor("10 7 hello my name is Bessie and this is my essay"));
         System.out.println(split("((()))"));
         System.out.println(toCamelCase("hello_edabit"));
         System.out.println(toSnakeCase("helloEdabit"));
         System.out.println(overTime(new String[] { "9", "17", "30", "1.5" }));
         System.out.println(BMI(new String[] { "205", "pounds", "73", "inches" }));
         System.out.println(bugger("999"));
         System.out.println(toStarShorthand("77777geff"));
         System.out.println(doesRhyme("Sam I am!", "Green eggs and ham."));
         System.out.println(trouble("451999277", "41177722899"));
         System.out.println(countUniqueBooks(new String[] { "AZYWABBCATTTA", "A" }));

        

    }

    private static String textProcessor(String str) {
        String[] words = str.split(" ");
        int symbolCount = Integer.parseInt(words[1]);
        String outputStr = "";
        int currentCount = 0;

        for (int i = 2; i < words.length; i++) {

            if (currentCount + words[i].length() <= symbolCount) {
                outputStr += words[i] + " ";
                currentCount += words[i].length();
            } else {
                currentCount = words[i].length();
                outputStr += "\n" + words[i] + " ";
            }
        }
        return outputStr;
    }

    private static String split(String line) {
        int leftCase = 0;
        int rightCase = 0;
        String outputLine = "";
        String currentLine = "";

        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '(') {
                leftCase += 1;
                currentLine += line.substring(i, i + 1);
            } else if (line.charAt(i) == ')') {
                rightCase += 1;
                currentLine += line.substring(i, i + 1);
            }

            if (leftCase == rightCase) {
                outputLine += currentLine + "\n";
                currentLine = "";
                leftCase = 0;
                rightCase = 0;
            }
        }
        return outputLine;
    }

    private static String toCamelCase(String word) {
        String outputString = "";
        int beginIndex = 0;
        int currentIndex = 0;

        while (currentIndex < word.length() - 1) {
            if (word.substring(currentIndex, currentIndex + 1).equals("_")) {
                outputString += word.substring(beginIndex, currentIndex)
                        + word.substring(currentIndex + 1, currentIndex + 2).toUpperCase();
                currentIndex += 2;
                beginIndex = currentIndex;
                if (word.indexOf("_", currentIndex + 2) == -1) {
                    return outputString += word.substring(currentIndex, word.length());
                }
            } else
                currentIndex += 1;
        }
        return outputString;
    }

    private static String toSnakeCase(String word) {
        String outputString = "";
        int currentIndex = 0;
        int beginIndex = 0;

        while (currentIndex < word.length() - 1) {
            if (word.substring(currentIndex, currentIndex + 1).matches("^[A-Z]+$")) {
                outputString += word.substring(beginIndex, currentIndex) + "_"
                        + word.substring(currentIndex, currentIndex + 1).toLowerCase();
                currentIndex += 1;
                beginIndex = currentIndex;

                if (!word.substring(currentIndex, word.length() -
                        1).chars().anyMatch(Character::isUpperCase)) {
                    return outputString += word.substring(currentIndex, word.length());
                }
            } else
                currentIndex += 1;
        }
        return outputString;
    }

    private static String overTime(String[] args) {
        double beginWork = Double.parseDouble(args[0]);
        double endWork = Double.parseDouble(args[1]);
        double hourlyRate = Double.parseDouble(args[2]);
        double overRate = Double.parseDouble(args[3]);

        if (endWork > 17.00)
            return "$" + String.valueOf((17.00 - beginWork) * hourlyRate + (endWork -
                    17.00) * hourlyRate * overRate);
        else
            return "$" + String.valueOf((endWork - beginWork) * hourlyRate);
    }

    private static String BMI(String[] args) {
        double weight = Double.parseDouble(args[0]);
        double height = Double.parseDouble(args[2]);
        double indexBMI = 0;

        if (args[1].equals("pounds"))
            weight *= 0.45;
        if (args[3].equals("inches")) {
            height *= 2.54;
            height /= 100;
        }

        indexBMI = Math.ceil((weight / (height * height)));

        if (indexBMI > 25)
            return indexBMI + " Overweight";
        else if (indexBMI < 18.5)
            return indexBMI + " Underweight";
        else
            return indexBMI + " Normal weight";
    }

    private static int bugger(String str) {
        int currentNum = toCalculate(str);
        int itterations = 1;

        if (str.length() == 1) {
            return 0;
        }
        while (currentNum > 9) {
            itterations += 1;
            currentNum = toCalculate(Integer.toString(currentNum));
        }
        return itterations;
    }

    public static int toCalculate(String str) {
        int outputNum = 1;
        int arraySum[] = new int[str.length()];

        for (int i = 0; i < str.length(); i++) {
            arraySum[i] = Integer.parseInt(str.substring(i, i + 1));
        }
        for (int i = 0; i < arraySum.length; i++) {
            outputNum *= arraySum[i];
        }
        return outputNum;
    }

    private static String toStarShorthand(String lines) {
        String outputStr = "";
        int counter = 0;
        String line = lines + "NN";

        for (int i = 0; i < line.length() - 1; i++) {
            if (!line.substring(i, i + 1).equals(line.substring(i + 1, i + 2)) && counter == 0) {
                outputStr += line.substring(i, i + 1);
            }

            else if (line.substring(i, i + 1).equals(line.substring(i + 1, i + 2))) {
                counter += 1;
            } else {
                outputStr += line.substring(i, i + 1) + "*" + (counter + 1);
                counter = 0;
            }
        }
        return outputStr;
    }

    private static Boolean doesRhyme(String line1, String line2) {
        String lineCheck1 = line1;
        String lineCheck2 = line2;
        if (lineCheck1.substring(lineCheck1.length() - 1,
                lineCheck1.length()).matches("[?!.]($|\\s)")) {
            lineCheck1 = lineCheck1.substring(0, lineCheck1.length() - 1);
        }

        if (lineCheck2.substring(lineCheck2.length() - 1,
                lineCheck2.length()).matches("[?!.]($|\\s)")) {
            lineCheck2 = lineCheck2.substring(0, lineCheck2.length() - 1);
        }

        if (lineCheck1.substring(lineCheck1.length() - 2, lineCheck1.length())
                .equals(lineCheck2.substring(lineCheck2.length() - 2, lineCheck2.length()))) {
            return true;
        } else
            return false;
    }

    private static Boolean trouble(String first, String second) {
        int numChar = 0;
        for (int i = 0; i < first.length() - 2; i++) {
            if ((first.charAt(i) == first.charAt(i + 1)) && (first.charAt(i) == first.charAt(i + 2))) {
                numChar = first.charAt(i);
            }
        }
        if (numChar == 0) {
            return false;
        }

        for (int i = 0; i < second.length() - 1; i++) {
            if ((second.charAt(i) == numChar) && (second.charAt(i + 1) == numChar)) {
                return true;
            }
        }
        return false;
    }

    private static int countUniqueBooks(String[] args) {
        String[] strArray = args[0].split("");
        Set<String> s = new HashSet<String>();
        int counter = 0;

        for (int i = 0; i < strArray.length; i++) {
            if (strArray[i].equals(args[1])) {
                counter += 1;
            }
            if (counter % 2 != 0) {
                s.add(strArray[i]);
            }
        }
        return s.size() - 1;
    }
}