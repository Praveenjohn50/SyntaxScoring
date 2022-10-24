
import java.math.BigInteger;
import java.util.*;

public class SyntaxScoring {

    public static final String INPUT = "[({(<(())[]>[[{[]{<()<>>\n" +
            "[(()[<>])]({[<{<<[]>>(\n" +
            "{([(<{}[<>[]}>{[]{[(<()>\n" +
            "(((({<>}<{<{<>}{[]{[]{}\n" +
            "[[<[([]))<([[{}[[()]]]\n" +
            "[{[{({}]{}}([{[{{{}}([]\n" +
            "{<[[]]>}<{[{[{[]{()[[[]\n" +
            "[<(<(<(<{}))><([]([]()\n" +
            "<{([([[(<>()){}]>(<<{{\n" +
            "<{([{{}}[<[[[<>{}]]]>[]]";


    public static void main(String[] args) {

        Stack<String> charStack = new Stack<>();

        int count = 0;
        List<BigInteger> scoreTotal = new ArrayList<>();

        for (String line : INPUT.split("\n")) {
            String[] charArr = line.trim().split("");

            boolean isValid = true;

            charStack = new Stack<>();
            mainForLoop:
            for (String c : charArr) {
                switch (c) {
                    case "(":
                    case "[":
                    case "{":
                    case "<":
                        charStack.push(c);
                        break;

                    case ")":
                        if (!charStack.peek().equals("(")) {
                            count += 3;
                            isValid = false;
                            break mainForLoop;
                        }
                        charStack.pop();
                        break;

                    case "]":
                        if (!charStack.peek().equals("[")) {
                            count += 57;
                            isValid = false;
                            break mainForLoop;
                        }
                        charStack.pop();
                        break;

                    case "}":
                        if (!charStack.peek().equals("{")) {
                            count += 1197;
                            isValid = false;
                            break mainForLoop;
                        }
                        charStack.pop();
                        break;

                    case ">":
                        if (!charStack.peek().equals("<")) {
                            count += 25137;
                            isValid = false;
                            break mainForLoop;
                        }
                        charStack.pop();
                        break;
                }
            }

            if (isValid) {
                BigInteger countValid = BigInteger.ZERO;

                while (charStack.size() > 0) {
                    String s = charStack.pop();
                    countValid = countValid.multiply(new BigInteger("5"));
                    switch (s) {
                        case "(":
                            countValid = countValid.add(new BigInteger("1"));
                            break;
                        case "[":
                            countValid = countValid.add(new BigInteger("2"));
                            break;
                        case "{":
                            countValid = countValid.add(new BigInteger("3"));
                            break;
                        case "<":
                            countValid = countValid.add(new BigInteger("4"));
                            break;
                    }
                }
                scoreTotal.add(countValid);
            }
        }

        Collections.sort(scoreTotal);

        System.out.println(scoreTotal.get(Math.floorDiv(scoreTotal.size(), 2)));

    }

}
