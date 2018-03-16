public class GreedyAlgorithm implements Algorithm {
    private int array[];
    private int numberOfCities;
    private int path[];
    private int minLength;

    public GreedyAlgorithm(int array[]){
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
        boolean visited[] = new boolean[numberOfCities];
        path = new int[numberOfCities];
        int length = 0;

        for (int i = 0; i < numberOfCities; i++){
            visited[i] = false;
            path[i] = -1;
        }
        int start = 0;
        int i = start;

        while(!allVisited(visited)){
            visited[i] = true;
            int min = Integer.MAX_VALUE;
            int position = -1;
            for (int j = 0; j < numberOfCities; j++){
                if (!visited[j] && array[j*numberOfCities + i] < min){
                    min = array[j*numberOfCities + i];
                    position = j;
                }
            }
            if (min != Integer.MAX_VALUE)
                length += min;
            else {
                length += array[start*numberOfCities + i];
            }
            if (position != -1){
                path[i] = position;
                i = position;
            }
        }
        path[i] = start;
        minLength = length;
    }

    boolean allVisited(boolean[] visited) {
        for (int i = 0; i < numberOfCities; i++){
            if (!visited[i]) return false;
        }
        return true;
    }


    public int[] getPath() {
        return path;
    }

    public int getMinLength() {
        return minLength;
    }
}
