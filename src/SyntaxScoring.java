
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class SyntaxScoring {

    public static void main(String[] args) throws IOException {

        Stack<String> charStack;

        int count = 0;
        List<BigInteger> scoreTotal = new ArrayList<>();


        // Read input from input.txt file
        File file = new File("C:\\Users\\prave\\IdeaProjects\\CodingTest\\src\\input.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String stringFromFile;
        while ((stringFromFile = br.readLine()) != null) {
            String[] charArr = stringFromFile.trim().split("");

            boolean isValid = true;

            charStack = new Stack<>();
            /*Solution for Part 1 starts here*/
            mainForLoop:
            for (String c : charArr) {
                switch (c) {
                    case "(", "[", "{", "<" -> charStack.push(c);

                    case ")" -> {
                        if (!charStack.peek().equals("(")) {
                            count += 3;
                            isValid = false;
                            break mainForLoop;
                        }
                        charStack.pop();
                    }

                    case "]" -> {
                        if (!charStack.peek().equals("[")) {
                            count += 57;
                            isValid = false;
                            break mainForLoop;
                        }
                        charStack.pop();
                    }

                    case "}" -> {
                        if (!charStack.peek().equals("{")) {
                            count += 1197;
                            isValid = false;
                            break mainForLoop;
                        }
                        charStack.pop();
                    }

                    case ">" -> {
                        if (!charStack.peek().equals("<")) {
                            count += 25137;
                            isValid = false;
                            break mainForLoop;
                        }
                        charStack.pop();
                    }
                }
            }
            /*Solution for Part 1 Ends here*/

            /*Solution for Part 2 starts here*/
            if (isValid) {
                BigInteger countValid = BigInteger.ZERO;

                while (charStack.size() > 0) {
                    String s = charStack.pop();
                    countValid = countValid.multiply(new BigInteger("5"));
                    countValid = switch (s) {
                        case "(" -> countValid.add(new BigInteger("1"));
                        case "[" -> countValid.add(new BigInteger("2"));
                        case "{" -> countValid.add(new BigInteger("3"));
                        case "<" -> countValid.add(new BigInteger("4"));
                        default -> countValid;
                    };
                }
                scoreTotal.add(countValid);
            }
            /*Solution for Part 2 Ends here*/
        }

        Collections.sort(scoreTotal);
        System.out.println("Part 1 :" + count);
        System.out.println("Part 2 :" + scoreTotal.get(Math.floorDiv(scoreTotal.size(), 2)));

    }

}
