package mathcomp.oletsky.genalg;

import mathcomp.oletsky.mathhelper.VectMatr;


public class SimpleGenalg {
    public static void main(String[] args) {
        //Initializing
        final double PROB=0.5;
        final int N=20;
        final int CAPACITY=100;
        final int KOL_ITER=300;

        SolutionCreator.init(N, PROB);
        Population population=new Population(CAPACITY);
        System.out.println("Initial population:");
        showPopulationInfo(population);
        System.out.println("Initial fitness: "+population.getPopulationFitness()+
                "       Max fitness: "+population.getMaxFitness());

        System.out.println("-------------------");
        System.out.println("Iterations:");
        for (int iter=1; iter<=KOL_ITER; iter++) {
            //Selection to parent pool
            Population pool = population.formParentPool();
            //System.out.println("Parent pool:");
            //showPopulationInfo(pool);

            //Genetic operators
            Population next = pool.formNextPopulation();


            //Forming new population
            population = next;
            System.out.println( "Iteration "+iter+". Average fitness:  "+population.getPopulationFitness()
            +"     Max fitness: "+population.getMaxFitness());
            //System.out.println("Next generation:");
            //showPopulationInfo(next);
        }
        //Final population:
        System.out.println("Final population:");
        showPopulationInfo(population);
        System.out.println( " Average fitness:  "+population.getPopulationFitness()
                +"     Max fitness: "+population.getMaxFitness());
    }

    static void showPopulationInfo(Population population) {
        population.showPopulation();
        System.out.println("Size: "+population.getPopulationSize());
        System.out.println("Fitnesses:");
        VectMatr.paginatedOutputVector(population.formFitnesses());
        System.out.println("Population fitness: "+population.getPopulationFitness());
        //System.out.println("----------------------------------------");
    }


}

