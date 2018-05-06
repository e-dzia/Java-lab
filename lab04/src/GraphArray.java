import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class GraphArray {
    int numberofCities;
    int array[];

    public boolean loadFromFile(String filename){
        Scanner in;
        try {
            in = new Scanner(new FileReader(filename));
        } catch (FileNotFoundException e){
            return false;
        }
        this.numberofCities = in.nextInt();
        this.array = new int[numberofCities*numberofCities];

        for (int i = 0; i < numberofCities; i++){
            for (int j = 0; j < numberofCities; j++){
                this.array[j*numberofCities+i] = in.nextInt();
            }
        }

        return true;
    }

    public void print(){
        for (int i = 0; i < numberofCities; i++){
            for (int j = 0; j < numberofCities; j++){
                System.out.print(this.array[j*numberofCities + i] + "\t");
            }
            System.out.println();
        }
    }

    public int getNumberofCities() {
        return numberofCities;
    }

    public int[] getArray() {
        return array;
    }

}
