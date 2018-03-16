
public class BruteForceAlgorithm {
    int array[];
    int numberOfCities;
    int path[];
    int minLength;

    public BruteForceAlgorithm(int array[]){
        this.array = array;
        this.numberOfCities = (int) Math.sqrt(array.length);
    }

    public void print(){
        System.out.println("Dlugosc: " + minLength);
        for (int i = 0; i < numberOfCities; i++){
            System.out.print(path[i] + "\t");
        }
        System.out.println();
    }

    public void algorithm(){
        int permutation[] = new int[numberOfCities];
        for (int i = 0; i < numberOfCities; i++){
            permutation[i] = i;
        }
        path = new int[numberOfCities];

        Integer min = Integer.MAX_VALUE;
        permute(permutation, 1, numberOfCities - 1, min, path);
        minLength = min;

    }

    void permute(int[] permutation, int left, int right, Integer min, int[] result) {
        if (left == right){
            int length = countPath(permutation);
            if (length < min){
                min = length;
                for (int i = 0; i < numberOfCities; i++){
                    result[i] =  permutation[i];
                }
            }
        }
        else
        {
            for (int i = left; i <= right; i++)
            {
                int tmp = permutation[i];
                permutation[i] = permutation[left];
                permutation[left] = tmp;
                permute(permutation, left + 1, right, min, result);
                tmp = permutation[i];
                permutation[i] = permutation[left];
                permutation[left] = tmp;//powrót do poprzedniego
            }
        }

    }

    int countPath(int[] permutation) {
        int length = 0;
        int end = 0;
        for (int i = 1; i < numberOfCities; i++){
            length += array[permutation[i]*numberOfCities + permutation[i-1]];
            end = i;
        }
        length += array[permutation[0]*numberOfCities + permutation[end]];

        return length;
    }



    boolean allVisited(boolean[] visited) {
        for (int i = 0; i < numberOfCities; i++){
            if (!visited[i]) return false;
        }
        return true;
    }
}
