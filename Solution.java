package mathcomp.oletsky.genalg;

import mathcomp.oletsky.mathhelper.VectMatr;

import java.util.Random;

class Solution {
    static int count=0;
    int id;
    int[] chromosoma;
    {
        count++;
        this.id=count;
    }



    Solution(int n, double prob) {
        Random rand = new Random();
        chromosoma = new int[n];
        for (int i = 0; i < n; i++) {
            double r = rand.nextDouble();
            if (r<prob) chromosoma[i] = 1;
            else chromosoma[i]=0;
        }
    }

    Solution(int[] arr) {
        this.chromosoma = arr;
    }

    public int[] getChromosoma() {
        return chromosoma;
    }

    public void setChromosoma(int[] chromosoma) {
        this.chromosoma = chromosoma;
    }

    public double getFitness() {
        int count = 0;
        for (int i = 0; i < chromosoma.length; i++) {
            count += chromosoma[i];
        }
        return count;
    }

    public void showSolution() {
        VectMatr.showIntegerArray(this.getChromosoma());
    }

    int getSolutionSize() {
        return this.chromosoma.length;
    }

    public int getId() {
        return id;
    }

    public static Tuple crossover(Solution s1, Solution s2) {
        Random r = new Random();
        int n = s1.getSolutionSize();
        int brk = r.nextInt(n);

        int[] child1 = new int[n];
        int[] child2 = new int[n];

        //Creating children
        for (int i = 0; i < brk; i++) {
            child1[i] = s1.getChromosoma()[i];
            child2[i] = s2.getChromosoma()[i];
        }
        for (int i = brk; i < n; i++) {
            child1[i] = s2.getChromosoma()[i];
            child2[i] = s1.getChromosoma()[i];

        }

        Tuple tuple = new Tuple(
                new Solution(child1),
                new Solution(child2)
        );

        tuple.breakpoint = brk;

        return tuple;
    }

    static class Tuple {
        int breakpoint;
        Solution s1;
        Solution s2;

        Tuple(Solution s1, Solution s2) {
            this.s1 = s1;
            this.s2 = s2;
        }

        public Solution getS1() {
            return s1;
        }

        public Solution getS2() {
            return s2;
        }

        public int getBreakpoint() {
            return breakpoint;
        }


    }
}
