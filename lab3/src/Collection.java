import java.util.ArrayList;

public class Collection {
    public static int deletedObjects = 0;
    ArrayList<Double> collection = new ArrayList<>();

    public Collection(int grain){
        long size = 5000000 + grain*100;
        for (int i = 0; i < size; i++){
            collection.add(grain * 200 + 2000.0 * i + 0.5);
        }
    }

    public void finalize(){
        //System.out.println("\t\t\t\t%Usuwam: " + (collection.size()-1000000)/100);
        deletedObjects++;
    }

    public Double countMinimum(){
        Double min = Double.MAX_VALUE;
        for (Double d : collection){
            if (d < min){
                min = d;
            }
        }
        return min;
    }

    public Double countMaximum(){
        Double max = Double.MIN_VALUE;
        for (Double d : collection){
            if (d > max){
                max = d;
            }
        }
        return max;
    }
}
