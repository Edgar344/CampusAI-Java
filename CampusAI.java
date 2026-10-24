/**
 * CampusAI.java
 * Evaluates and compares AI assistant performance across academic categories.
 * Demonstrates 2D array traversal, linear searching, and selection sorting.
 */
public class CampusAI {

    public static void main(String[] args) {

        String[] models = {
            "ChatGPT", "Gemini", "Claude",
            "Copilot", "Perplexity"
        };

        String[] tasks = {
            "Writing", "Research", "Coding",
            "Math", "Creativity"
        };

        int[][] scores = {
            {92, 88, 95, 90, 86}, // ChatGPT
            {89, 94, 87, 85, 91}, // Gemini
            {95, 90, 89, 92, 84}, // Claude
            {85, 82, 96, 88, 90}, // Copilot
            {91, 97, 80, 86, 93}  // Perplexity
        };

        System.out.println("=== CAMPUSAI PERFORMANCE REPORT ===\n");

        // PART 1: Display the performance matrix
        displayScores(models, tasks, scores);
        System.out.println();

        // PART 2: Display model averages
        System.out.println("MODEL AVERAGES");
        for (int i = 0; i < models.length; i++) {
            double avg = averageModel(scores, i);
            System.out.printf("%-12s: %.2f%n", models[i], avg);
        }
        System.out.println();

        // Display category averages
        System.out.println("CATEGORY AVERAGES");
        for (int j = 0; j < tasks.length; j++) {
            double avg = averageTask(scores, j);
            System.out.printf("%-12s: %.2f%n", tasks[j], avg);
        }
        System.out.println();

        // PART 3: Searching
        System.out.println("BEST MODEL BY CATEGORY");
        for (int j = 0; j < tasks.length; j++) {
            int bestIndex = findBestModel(scores, j);
            System.out.printf("%-12s: %s (%d)%n", tasks[j], models[bestIndex], scores[bestIndex][j]);
        }
        System.out.println();

        System.out.println("SEARCH RESULTS");
        // Search demonstration 1: Successful search (case-insensitive)
        String searchTarget1 = "Claude";
        int index1 = searchModel(models, searchTarget1);
        if (index1 != -1) {
            System.out.println(searchTarget1 + " found at index " + index1);
        } else {
            System.out.println(searchTarget1 + " not found");
        }

        // Search demonstration 2: Unsuccessful search
        String searchTarget2 = "UnknownAI";
        int index2 = searchModel(models, searchTarget2);
        if (index2 != -1) {
            System.out.println(searchTarget2 + " found at index " + index2);
        } else {
            System.out.println(searchTarget2 + " not found");
        }
        System.out.println();

        // PART 4: Sorting
        // Create parallel arrays for the leaderboard to preserve original data
        String[] leaderboardModels = new String[models.length];
        double[] leaderboardAverages = new double[models.length];

        for (int i = 0; i < models.length; i++) {
            leaderboardModels[i] = models[i];
            leaderboardAverages[i] = averageModel(scores, i);
        }

        // Sort the leaderboard using Selection Sort
        sortLeaderboard(leaderboardModels, leaderboardAverages);

        // Display sorted leaderboard
        System.out.println("FINAL LEADERBOARD");
        for (int i = 0; i < leaderboardModels.length; i++) {
            System.out.printf("%d. %-12s %.2f%n", (i + 1), leaderboardModels[i], leaderboardAverages[i]);
        }
        System.out.println();
    }

    /**
     * Displays the 2D array formatted as a table with column and row headers.
     */
    public static void displayScores(String[] models, String[] tasks, int[][] scores) {
        // Print column headers
        System.out.printf("%-12s", "AI Model");
        for (String task : tasks) {
            System.out.printf("%-12s", task);
        }
        System.out.println();

        // Outer loop traverses models (rows)
        for (int i = 0; i < models.length; i++) {
            System.out.printf("%-12s", models[i]);
            // Inner loop traverses scores (columns)
            for (int j = 0; j < scores[i].length; j++) {
                System.out.printf("%-12d", scores[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * Calculates and returns the average score for a single AI model across all tasks.
     */
    public static double averageModel(int[][] scores, int row) {
        int sum = 0;
        for (int j = 0; j < scores[row].length; j++) {
            sum += scores[row][j];
        }
        return (double) sum / scores[row].length;
    }

    /**
     * Calculates and returns the average score for a single academic category across all models.
     */
    public static double averageTask(int[][] scores, int column) {
        int sum = 0;
        for (int i = 0; i < scores.length; i++) {
            sum += scores[i][column];
        }
        return (double) sum / scores.length;
    }

    /**
     * Performs a case-insensitive linear search for an AI model name in the array.
     */
    public static int searchModel(String[] models, String target) {
        for (int i = 0; i < models.length; i++) {
            if (models[i].equalsIgnoreCase(target)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Finds the row index of the highest scoring model in a specified task column.
     */
    public static int findBestModel(int[][] scores, int taskIndex) {
        int bestRowIndex = 0;
        int maxScore = scores[0][taskIndex];

        for (int i = 1; i < scores.length; i++) {
            if (scores[i][taskIndex] > maxScore) {
                maxScore = scores[i][taskIndex];
                bestRowIndex = i;
            }
        }
        return bestRowIndex;
    }

    /**
     * Sorts the model names and average scores in descending order using Selection Sort.
     * Swaps corresponding entries in both arrays to maintain alignment.
     */
    public static void sortLeaderboard(String[] models, double[] averages) {
        int n = averages.length;

        for (int i = 0; i < n - 1; i++) {
            int maxIdx = i;

            // Find the index of the highest remaining average score
            for (int j = i + 1; j < n; j++) {
                if (averages[j] > averages[maxIdx]) {
                    maxIdx = j;
                }
            }

            // Swap the highest average found with the current position
            double tempAvg = averages[maxIdx];
            averages[maxIdx] = averages[i];
            averages[i] = tempAvg;

            // Swap corresponding model names to maintain parallel association
            String tempModel = models[maxIdx];
            models[maxIdx] = models[i];
            models[i] = tempModel;
        }
    }
}