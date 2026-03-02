import java.util.*;

/**
 * UC13PallindromeCheckerApp
 *
 * Use Case 13:
 * Algorithm Benchmarking & Complexity Analysis
 *
 * This class executes all palindrome implementations
 * from UC02 → UC12 and compares execution time
 * using System.nanoTime().
 *
 * Key Concepts:
 * - Algorithm benchmarking
 * - Time complexity observation
 * - Runtime comparison
 *
 * NOTE:
 * PalindromeService (UC11)
 * PalindromeStrategy & StackStrategy (UC12)
 * are reused — NOT redefined.
 *
 * @author Akshat
 * @version 13.0
 */

public class PalindromeCheckerApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("===== UC13 - Full Algorithm Comparison =====");
        System.out.print("Enter String -> ");
        String input = sc.nextLine();

        System.out.println("\nRunning All Algorithms...\n");

        // UC02 / UC03
        run("UC02/UC03 - Basic Loop", input,
                UC13PallindromeCheckerApp::basicLoop);

        // UC04
        run("UC04 - Character Array", input,
                UC13PallindromeCheckerApp::charArrayMethod);

        // UC05
        run("UC05 - Stack", input,
                UC13PallindromeCheckerApp::stackMethod);

        // UC06
        run("UC06 - Queue + Stack", input,
                UC13PallindromeCheckerApp::queueStackMethod);

        // UC07
        run("UC07 - Deque", input,
                UC13PallindromeCheckerApp::dequeMethod);

        // UC08
        run("UC08 - LinkedList", input,
                UC13PallindromeCheckerApp::linkedListMethod);

        // UC09
        run("UC09 - Recursive", input,
                UC13PallindromeCheckerApp::recursiveMethod);

        // UC10
        run("UC10 - Normalized", input,
                UC13PallindromeCheckerApp::normalizedMethod);

        // UC11 (Reuse Service Class)
        PalindromeService service = new PalindromeService();
        run("UC11 - OOP Service", input,
                service::checkPalindrome);

        // UC12 (Reuse Strategy Pattern)
        PalindromeStrategy strategy = new StackStrategy();
        run("UC12 - Strategy Pattern (Stack)", input,
                strategy::check);

        sc.close();
    }

    // ================= BENCHMARK RUNNER =================

    private static void run(String name,
                            String input,
                            PalindromeAlgorithm algorithm) {

        long startTime = System.nanoTime();
        boolean result = algorithm.check(input);
        long endTime = System.nanoTime();

        long duration = endTime - startTime;

        System.out.println(name);
        System.out.println("Result : " + result);
        System.out.println("Time   : " + duration + " ns");
        System.out.println("-------------------------------------");
    }

    interface PalindromeAlgorithm {
        boolean check(String input);
    }

    // ================= UC02 / UC03 =================
    private static boolean basicLoop(String input) {

        int length = input.length();

        for (int i = 0; i < length / 2; i++) {
            if (input.charAt(i) != input.charAt(length - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    // ================= UC04 =================
    private static boolean charArrayMethod(String input) {

        char[] array = input.toCharArray();
        int start = 0;
        int end = array.length - 1;

        while (start < end) {
            if (array[start] != array[end]) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    // ================= UC05 =================
    private static boolean stackMethod(String input) {

        Stack<Character> stack = new Stack<>();

        for (char c : input.toCharArray()) {
            stack.push(c);
        }

        for (char c : input.toCharArray()) {
            if (c != stack.pop()) {
                return false;
            }
        }
        return true;
    }

    // ================= UC06 =================
    private static boolean queueStackMethod(String input) {

        Queue<Character> queue = new LinkedList<>();
        Stack<Character> stack = new Stack<>();

        for (char c : input.toCharArray()) {
            queue.add(c);
            stack.push(c);
        }

        while (!queue.isEmpty()) {
            if (queue.remove() != stack.pop()) {
                return false;
            }
        }
        return true;
    }

    // ================= UC07 =================
    private static boolean dequeMethod(String input) {

        Deque<Character> deque = new ArrayDeque<>();

        for (char c : input.toCharArray()) {
            deque.addLast(c);
        }

        while (deque.size() > 1) {
            if (deque.removeFirst() != deque.removeLast()) {
                return false;
            }
        }
        return true;
    }

    // ================= UC08 =================
    private static boolean linkedListMethod(String input) {

        LinkedList<Character> list = new LinkedList<>();

        for (char c : input.toCharArray()) {
            list.add(c);
        }

        while (list.size() > 1) {
            if (list.removeFirst() != list.removeLast()) {
                return false;
            }
        }
        return true;
    }

    // ================= UC09 =================
    private static boolean recursiveMethod(String input) {
        return recursiveCheck(input, 0, input.length() - 1);
    }

    private static boolean recursiveCheck(String s,
                                          int start,
                                          int end) {

        if (start >= end)
            return true;

        if (s.charAt(start) != s.charAt(end))
            return false;

        return recursiveCheck(s, start + 1, end - 1);
    }

    // ================= UC10 =================
    private static boolean normalizedMethod(String input) {

        String normalized =
                input.replaceAll("[^a-zA-Z0-9]", "")
                        .toLowerCase();

        return basicLoop(normalized);
    }
}