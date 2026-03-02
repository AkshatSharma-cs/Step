/**
 * MAIN CLASS - UC06PallindromeCheckerApp
 *
 * Use Case 6: Queue + Stack Fairness Check
 *
 * Description:
 * This class demonstrates palindrome validation using
 * two different data structures:
 * - Queue (FIFO - First In First Out)
 * - Stack (LIFO - Last In First Out)
 *
 * Characters are inserted into both structures and then
 * compared by removing from the front of the queue and
 * the top of the stack.
 *
 * If all characters match, the input string is confirmed
 * as a palindrome.
 *
 * This use case helps understand how FIFO and LIFO
 * behaviors can be combined for symmetric comparison.
 *
 * @author Akshat
 * @version 6.0
 */

import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Stack;

public class PallindromeCheckerApp {

    /**
     * Application entry point for UC6.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("======================================");
        System.out.println("   UC6 - Queue + Stack Palindrome Check");
        System.out.println("======================================");

        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        // Convert to lowercase for case-insensitive comparison
        input = input.toLowerCase();

        // Create Queue and Stack
        Queue<Character> queue = new LinkedList<>();
        Stack<Character> stack = new Stack<>();

        // Insert characters into both structures
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            queue.add(ch);   // FIFO
            stack.push(ch);  // LIFO
        }

        boolean isPalindrome = true;

        // Compare elements
        while (!queue.isEmpty()) {
            char fromQueue = queue.remove();   // Front element
            char fromStack = stack.pop();      // Top element

            if (fromQueue != fromStack) {
                isPalindrome = false;
                break;
            }
        }

        System.out.println("--------------------------------------");

        if (isPalindrome) {
            System.out.println("\"" + input + "\" is a PALINDROME.");
        } else {
            System.out.println("\"" + input + "\" is NOT a palindrome.");
        }

        System.out.println("--------------------------------------");
        System.out.println("Explanation:");
        System.out.println("Queue removes characters in FIFO order.");
        System.out.println("Stack removes characters in LIFO order.");
        System.out.println("Matching both confirms symmetric structure.");

        scanner.close();
    }
}