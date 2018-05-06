
public class BestOfAlgorithm implements Algorithm {
    int array[];
    int numberOfCities;
    int path[];
    int minLength;

    public BestOfAlgorithm(int[] array){
        this.array = array;
        this.numberOfCities = (int) Math.sqrt(array.length);
    }

    @Override
    public void algorithm() {
        BruteForceAlgorithm bruteForce = new BruteForceAlgorithm(this.array);
        GreedyAlgorithm greedy = new GreedyAlgorithm(this.array);

        bruteForce.algorithm();
        greedy.algorithm();

        int minBruteForce = bruteForce.getMinLength();
        int minGreedy = greedy.getMinLength();

        if (minGreedy < minBruteForce){
            this.path = greedy.getPath();
            this.minLength = minGreedy;
        }
        else {
            this.path = bruteForce.getPath();
            this.minLength = minBruteForce;
        }
    }

    @Override
    public String print() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Dlugosc: " + minLength + "\n");
        for (int i = 0; i < numberOfCities; i++){
            stringBuilder.append(path[i] + " ");
        }
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

    @Override
    public String getInfo() {
        return "Wykonuje dwa algorytmy - przeszukiwanie zupelne oraz algorytm zachlnny. " +
                "Nastepnie wybiera rozwiazanie, ktre jest lepsze i zwraca jego wynik.";
    }

    public int[] getPath() {
        return path;
    }

    public int getMinLength() {
        return minLength;
    }
}
