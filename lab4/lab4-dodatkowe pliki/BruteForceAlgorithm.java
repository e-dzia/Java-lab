
public class BruteForceAlgorithm implements Algorithm {
    private int array[];
    private int numberOfCities;
    private int path[];
    private int minLength;

    public BruteForceAlgorithm(int array[]) {
        this.array = array;
        this.numberOfCities = (int) Math.sqrt(array.length);
    }

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
        return "Algorytm przeszukiwania zupelnego - przeszukuje wszystkie mozliwe rozwiazania " +
                "i wybiera to, ktore jest najkrotsze. Dzieki temu znajduje rozwiazanie, ktore zawsze jest optymalne.";
    }

    public void algorithm() {
        int permutation[] = new int[numberOfCities];
        for (int i = 0; i < numberOfCities; i++) {
            permutation[i] = i;
        }
        path = new int[numberOfCities];

        Integer min = Integer.MAX_VALUE;
        min = permute(permutation, 1, numberOfCities - 1, min, path);
        minLength = min;

    }

    int permute(int[] permutation, int left, int right, Integer min, int[] result) {
        if (left == right) {
            Integer length = countPath(permutation);
            if (length < min) {
                min = length;
                for (int i = 0; i < numberOfCities; i++) {
                    result[i] = permutation[i];
                }
            }
        } else {
            for (int i = left; i <= right; i++) {
                int tmp = permutation[i];
                permutation[i] = permutation[left];
                permutation[left] = tmp;

                min = permute(permutation, left + 1, right, min, result);

                tmp = permutation[i];
                permutation[i] = permutation[left];
                permutation[left] = tmp;
            }
        }
        return min;
    }

    int countPath(int[] permutation) {
        int length = 0;
        int end = 0;
        for (int i = 1; i < numberOfCities; i++) {
            length += array[permutation[i] * numberOfCities + permutation[i - 1]];
            end = i;
        }
        length += array[permutation[0] * numberOfCities + permutation[end]];

        return length;
    }

    public int[] getPath() {
        return path;
    }

    public int getMinLength() {
        return minLength;
    }

}
