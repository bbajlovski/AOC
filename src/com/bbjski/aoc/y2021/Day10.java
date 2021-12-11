package com.bbjski.aoc.y2021;

import java.util.*;

public class Day10 {

    public static void main(String[] args) {

        System.out.println(">>> Enter subsystem:");

        Scanner input = new Scanner(System.in);
        ArrayList<String> syntaxes = new ArrayList<>();
        int sum1 = 0;
        long sum2 = 0;
        ArrayList<Long> scores = new ArrayList<>();
        while (input.hasNextLine()){
            String syntax = input.nextLine();
            syntaxes.add(syntax);

            boolean error = false;
            Stack<Character> closures = new Stack<>();
            for (Character chunk : syntax.toCharArray()) {
                switch (chunk) {
                    case '(': {
                        closures.push(')');
                        break;
                    }
                    case '[': {
                        closures.push(']');
                        break;
                    }
                    case '{': {
                        closures.push('}');
                        break;
                    }
                    case '<': {
                        closures.push('>');
                        break;
                    }
                    default: {
                        Character closure = closures.peek();
                        if (closure != chunk) {
                            error = true;
                            switch (chunk) {
                                case ')': {
                                    sum1 += 3;
                                    break;
                                }
                                case ']': {
                                    sum1 += 57;
                                    break;
                                }
                                case '}': {
                                    sum1 += 1197;
                                    break;
                                }
                                case '>': {
                                    sum1 += 25137;
                                    break;
                                }
                            }
                        } else {
                            closures.pop();
                        }
                    }
                }
                if (error) {
                    break;
                }
            }

            if (!error && closures.size() > 0) {
                long completitionScore = 0;
                while (closures.size() > 0) {
                    Character closure = closures.pop();
                    switch (closure) {
                        case ')': {
                            completitionScore = 5 * completitionScore + 1;
                            break;
                        }
                        case ']': {
                            completitionScore = 5 * completitionScore + 2;
                            break;
                        }
                        case '}': {
                            completitionScore = 5 * completitionScore + 3;
                            break;
                        }
                        case '>': {
                            completitionScore = 5 * completitionScore + 4;
                            break;
                        }
                    }
                }
                scores.add(completitionScore);
            }
        }

        Collections.sort(scores, Comparator.comparing(Long::longValue));
        Collections.reverse(scores);
        sum2 = scores.get((scores.size() - 1)/2);


        System.out.println(">>> [part 1]: " + sum1);
        System.out.println(">>> [part 2]: " + sum2);
    }
}
