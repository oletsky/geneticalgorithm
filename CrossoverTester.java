package mathcomp.oletsky.genalg;

import mathcomp.oletsky.mathhelper.VectMatr;

public class CrossoverTester {
    public static void main(String[] args) {
        int[] m1 = {1, 0, 0, 1, 0, 0, 0, 1};
        int[] m2 = {0, 1, 0, 0, 1, 1, 1, 0};
        System.out.println("Before crossover:");
        VectMatr.showIntegerArray(m1);
        VectMatr.showIntegerArray(m2);

        //Doing crossover
        var tuple = Solution.crossover(
                new Solution(m1),
                new Solution(m2)
        );
        int[] c1=tuple.getS1().getChromosoma();
        int[] c2=tuple.getS2().getChromosoma();
        System.out.println("After crossover:");
        System.out.println("Breakpoint was: "+tuple.getBreakpoint());
        VectMatr.showIntegerArray(c1);
        VectMatr.showIntegerArray(c2);
    }
}
