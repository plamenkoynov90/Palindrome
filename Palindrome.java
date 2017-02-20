import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class Palindrome {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        Map<Character, Integer> letterCounts = new TreeMap<>();
        countLetters(input, letterCounts);
        Character middle = null;
        boolean haveMiddle = input.length() % 2 != 0;
        StringBuilder result = new StringBuilder();
        StringBuilder resultLeftPart = new StringBuilder();
        for (Map.Entry<Character, Integer> letterCount : letterCounts.entrySet()) {
            Character currentLetter = letterCount.getKey();
            Integer currentLetterCount = letterCount.getValue();
            if (haveMiddle) {
                if (currentLetterCount % 2 != 0) {
                    middle = currentLetter;
                    currentLetterCount--;
                    haveMiddle = false;
                }
            }
            resultLeftPart = appendCurrentLetter(resultLeftPart, currentLetter, currentLetterCount);
        }
        result = buildNewPalindrome(middle, result, resultLeftPart);
        System.out.println(result);
    }

    private static StringBuilder appendCurrentLetter(StringBuilder resultLeftPart, Character currentLetter, Integer currentLetterCount) {
        for (int i = 0; i < currentLetterCount / 2; i++) {
            resultLeftPart = resultLeftPart.append(currentLetter);
        }
        return resultLeftPart;
    }


    private static StringBuilder buildNewPalindrome(Character middle, StringBuilder result, StringBuilder resultLeftPart) {
        result = result.append(resultLeftPart);
        if (middle != null) {
            result = result.append(middle);
        }
        result = result.append(resultLeftPart.reverse());
        return result;
    }

    private static void countLetters(String input, Map<Character, Integer> letterCounts) {
        for (int i = 0; i < input.length(); i++) {
            char currentLetter = input.charAt(i);
            if (!letterCounts.containsKey(currentLetter)) {
                letterCounts.put(currentLetter, 0);
            }
            letterCounts.put(currentLetter, letterCounts.get(currentLetter) + 1);
        }
    }
}
