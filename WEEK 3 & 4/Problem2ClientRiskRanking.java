import java.util.*;

class Client {
    String name;
    int riskScore;
    double accountBalance;

    Client(String name, int riskScore, double accountBalance) {
        this.name = name;
        this.riskScore = riskScore;
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString() {
        return name + "(" + riskScore + ")";
    }
}

public class Problem2ClientRiskRanking {

    // Bubble Sort ascending by riskScore
    public static void bubbleSort(Client[] clients) {
        int n = clients.length;
        int swaps = 0, passes = 0;

        for (int i = 0; i < n - 1; i++) {
            passes++;
            for (int j = 0; j < n - i - 1; j++) {
                if (clients[j].riskScore > clients[j + 1].riskScore) {
                    Client temp = clients[j];
                    clients[j] = clients[j + 1];
                    clients[j + 1] = temp;
                    swaps++;
                }
            }
        }
        System.out.println("Bubble (asc): " + Arrays.toString(clients) +
                " // Swaps: " + swaps + ", Passes: " + passes);
    }

    // Insertion Sort descending by riskScore, tie-breaker accountBalance
    public static void insertionSort(Client[] clients) {
        for (int i = 1; i < clients.length; i++) {
            Client key = clients[i];
            int j = i - 1;

            while (j >= 0 && (clients[j].riskScore < key.riskScore ||
                    (clients[j].riskScore == key.riskScore &&
                            clients[j].accountBalance < key.accountBalance))) {
                clients[j + 1] = clients[j];
                j--;
            }
            clients[j + 1] = key;
        }
        System.out.println("Insertion (desc): " + Arrays.toString(clients));
    }

    // Print top N highest risk clients
    public static void printTopRisks(Client[] clients, int n) {
        System.out.println("Top " + n + " risks:");
        for (int i = 0; i < Math.min(n, clients.length); i++) {
            System.out.println(clients[i].name + "(" + clients[i].riskScore + ")");
        }
    }

    public static void main(String[] args) {
        Client[] clients = {
                new Client("clientC", 80, 2000.0),
                new Client("clientA", 20, 5000.0),
                new Client("clientB", 50, 3000.0)
        };

        // Bubble Sort ascending
        bubbleSort(Arrays.copyOf(clients, clients.length));

        // Insertion Sort descending
        Client[] copy = Arrays.copyOf(clients, clients.length);
        insertionSort(copy);

        // Top 3 risks
        printTopRisks(copy, 3);
    }
}
