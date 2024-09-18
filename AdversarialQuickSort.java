import java.util.Arrays;

public class AdversarialQuickSort {
    private static int[] val;   // Array holding the frozen values
    private static int ncmp;    // Number of comparisons
    private static int nsolid;  // Number of solid items (frozen items)
    private static int candidate; // Current pivot candidate index
    private static int gas;     // Placeholder for unfrozen elements

    /**
     * Compares two indices in the 'val' array, potentially freezing one.
     * This simulates the behavior of `cmp` function in C.
     *
     * @param x Index of the first element
     * @param y Index of the second element
     * @return Difference between the values at indices x and y
     */
    private static int compare(int x, int y) {
        ncmp++;  // Increment the comparison count
        if (val[x] == gas && val[y] == gas) {
            if (x == candidate) {
                freeze(x);
            } else {
                freeze(y);
            }
        }
        if (val[x] == gas)
            candidate = x;
        else if (val[y] == gas)
            candidate = y;

        return val[x] - val[y];
    }

    /**
     * Freezes the value at a given index by setting it to nsolid.
     *
     * @param x Index to be frozen
     */
    private static void freeze(int x) {
        val[x] = nsolid++;
    }

    /**
     * Generates a worst-case input for QuickSort using an adversarial comparator.
     *
     * @param n Length of the array to be generated
     * @param a Array where the worst-case input will be stored
     * @return Number of comparisons made during sorting
     */
    public static int antiQsort(int n, int[] a) {
        // Initialize the global variables
        val = a;
        gas = n - 1;
        nsolid = ncmp = candidate = 0;

        // Array of indices
        Integer[] ptr = new Integer[n];

        // Fill the arrays
        for (int i = 0; i < n; i++) {
            ptr[i] = i;
            val[i] = gas;
        }
        // using method reference to compare array
        Arrays.sort(ptr, AdversarialQuickSort::compare);

        return ncmp;  // Return the number of comparisons made
    }

    public static void main(String[] args) {
        int n = 100;  // Array length
        int[] arr = new int[n];  // Array to hold the adversarial values

        // Run the antiQsort to generate worst-case input and get comparison count
        int comparisonCount = antiQsort(n, arr);

        // Output the result
        System.out.println("Adversarial array: " + Arrays.toString(arr));
        System.out.println("Number of comparisons: " + comparisonCount);
    }
}
