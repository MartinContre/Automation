package utils;

import aquality.selenium.core.logging.Logger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RandomCreator {

    /**
     * Creates a list of random indexes.
     *
     * @param n        The number of random indexes to create.
     * @param numItems The total number of items to choose random indexes from.
     * @return A list of random indexes.
     */
    public static List<Integer> createRandomIndexes(int n, int numItems) {
        Random random = new Random();
        Logger.getInstance().info("Selecting " + n + " random indexes from " + numItems + " items");
        List<Integer> selectedIndexes = new ArrayList<>();
        Set<Integer> selectedSet = new HashSet<>();

        while (selectedSet.size() < n) {
            int randomIndex = random.nextInt(numItems);
            if (selectedSet.add(randomIndex)) {
                selectedIndexes.add(randomIndex);
            }
        }

        Logger.getInstance().info("Selected indexes: " + selectedIndexes);

        return selectedIndexes;
    }
}
