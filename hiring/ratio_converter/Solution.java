/**
 * You need to implement a converter class with the following interface:
 * setRate(String from, String to, double rate);
 * getRate(String from, String to);
 *
 * Example 1:
 * setRate("USD", "MXN", 10);
 * setRate("Miles", "Kilometers", 1.6);
 * getRate("USD", "MXN"); // returns 10
 * getRate("MXN", "USD"); // returns 0.1
 * getRate("Miles", "Kilometers"); // returns 1.6
 * getRate("Kilometers", "Miles"); // returns 0.625
 *
 * Example 2:
 * setRate("USD", "MXN", 10);
 * setRate("MXN", "EUR", 0.05);
 * getRate("USD", "EUR");        // returns 0.5

 * */
public class Solution {

    /**
     * note:
     * - we can convert multiple different significatives/marks e.g. currency, distance, weight, etc.
     * - we can convert indirectly "USD" to "EUR" when there is no direct rate between them.
     *
     * It's important to ask interviewer how many layers of indirect conversion we might have.
     *
     *
     * The solution is to use Graph to represent such model. Each Node is has a value and connected to other nodes that have
     * a relationship between them.
     * We can use HashMap<String, Node> for a quick search of specific significative.
     * We can use BFS to find conversion result or return exception if conversion is not possible.
     *
     * */
}

