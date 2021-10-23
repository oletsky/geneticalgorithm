package mathcomp.oletsky.genalg;

import mathcomp.oletsky.mathhelper.VectMatr;

import java.util.Collections;
import java.util.HashMap;
import java.util.TreeSet;

public class PoolTester {
    public static void main(String[] args) {
        //Initializing
        final double PROB=0.1;
        final int N=20;
        final int CAPACITY=10;
        SolutionCreator.init(N, PROB);

        System.out.println("Initial population: ");

        Population population=new Population(CAPACITY);
        var coll = population.getColl();
        Collections.sort(coll,(s1,s2)->(int)(s2.getFitness()-s1.getFitness()));
        for (Solution sol:coll) {
            System.out.print(sol.getId()+" - ");
            int[] arr = sol.getChromosoma();
            for (var c:arr) System.out.print(c+" ");
            System.out.println(" - "+sol.getFitness());
        }

        System.out.println("Fitness: "+population.getPopulationFitness());
        //--------------------------
        System.out.println("Parent pool:");
        Population pool = population.formParentPool();
        var listPool=pool.getColl();
        HashMap<Integer,Integer> count =new HashMap<>();
        for (var item:listPool) {
            int id=item.getId();
            if (!count.containsKey(id)) count.put(id,1);
            else count.put(id,count.get(id)+1);
            System.out.print(id+" ");
        }
        System.out.println();
        System.out.println("Counts:");
        var entryset = count.entrySet();
        for (var k:entryset) {
            System.out.println(k.getKey()+" - "+k.getValue());
        }
        System.out.println("Fitness: "+pool.getPopulationFitness());

    }
}
