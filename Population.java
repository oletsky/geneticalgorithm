package mathcomp.oletsky.genalg;

import mathcomp.oletsky.randomchooser.RandomChooser;

import java.util.ArrayList;
import java.util.Random;

class Population {
    ArrayList<Solution> coll = new ArrayList< >();

    Population() {

    }

    Population(int capacity) {
        for (int i = 0; i < capacity; i++) {
            coll.add(SolutionCreator.createSolution());
        }
    }

    public void showPopulation() {
        for (Solution s : coll) {
            s.showSolution();
        }
    }

    public double getPopulationFitness() {
        double s = 0;
        for (Solution sol : coll) {
            s += sol.getFitness();
        }
        return s / coll.size();
    }

    public double getMaxFitness() {
        double max = Double.NEGATIVE_INFINITY;
        for (Solution sol : coll) {
            double fCurr= sol.getFitness();
            if (fCurr>max) max=fCurr;
        }
        return max;
    }

    double[] formFitnesses() {
        int n = this.coll.size();
        double[] fitnesses = new double[n];
        for (int i = 0; i < n; i++) {
            fitnesses[i] = this.coll.get(i).getFitness();
        }
        return fitnesses;
    }

    Population formParentPool() {
        Population pool = new Population();
        double[] fitnesses = this.formFitnesses();
        //Simple roulette
        //double[] rouletteProbs = RandomChooser.getProbsByValues(fitnesses);
        //Exponential choose
        double beta = 3.;
        double[] rouletteProbs = RandomChooser.getExponentProbs(beta, fitnesses);
        for (int i = 0; i < this.coll.size(); i++) {
            int fallen = RandomChooser.chooseByProps(rouletteProbs);
            if (fallen >= this.coll.size()) throw
                    new RuntimeException("Exceeding choice");
            pool.addSolution(this.coll.get(fallen));
        }
        return pool;
    }

    void addSolution(Solution solution) {
        this.coll.add(solution);
    }

    void removeSolution(Solution solution) {
        this.coll.remove(solution);
    }

    Solution getSolution(int index) {
        return this.coll.get(index);
    }

    int getPopulationSize() {
        return this.coll.size();
    }

    public ArrayList<Solution> getColl() {
        return coll;
    }

    Population formNextPopulation() {
        Random r = new Random();
        Population pool = this;
        Population next = new Population();
        int n = pool.getPopulationSize();
        int nCur = n;

        for (int i = 0; i < n / 2; i++) {
            int k1 = r.nextInt(nCur);
            if (k1 >= pool.getPopulationSize()) throw new RuntimeException(
                    "Illegal current item for crossover"
            );
            Solution s1 = pool.getSolution(k1);
            pool.removeSolution(s1);
            nCur--;


            int k2 = r.nextInt(nCur);
            if (k2 >= pool.getPopulationSize()) throw new RuntimeException(
                    "Illegal current item for crossover"
            );
            Solution s2 = pool.getSolution(k2);
            pool.removeSolution(s2);
            nCur--;
            Solution.Tuple tuple = Solution.crossover(s1, s2);
            next.addSolution(tuple.getS1());
            next.addSolution(tuple.getS2());
        }
        return next;
    }



}
