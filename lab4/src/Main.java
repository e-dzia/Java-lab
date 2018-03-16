
public class Main {

    public static void main(String[] args) {
        GraphArray graphArray = new GraphArray();
        graphArray.loadFromFile("tsp10.txt");
        graphArray.print();

        System.out.println("Trasa:");

        GreedyAlgorithm greedy = new GreedyAlgorithm(graphArray.getArray());
        greedy.algorithm();
        greedy.print();

        BruteForceAlgorithm bruteForce = new BruteForceAlgorithm(graphArray.getArray());
        bruteForce.algorithm();
        bruteForce.print();
    }
}
