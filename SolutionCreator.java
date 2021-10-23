package mathcomp.oletsky.genalg;

import java.util.Random;

public class SolutionCreator {
    static int n;
    static double prob;

    static void init(int sn, double sprob) {
        SolutionCreator.n=sn;
        SolutionCreator.prob=sprob;
    }

    static Solution createSolution() {
        Random rand = new Random();
        int[] chromosoma = new int[n];
        for (int i = 0; i < n; i++) {
            double r = rand.nextDouble();
            if (r < prob) chromosoma[i] = 1;
            else chromosoma[i] = 0;

        }
        return new Solution(chromosoma);
    }
}
