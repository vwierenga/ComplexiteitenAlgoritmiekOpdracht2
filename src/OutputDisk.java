import java.util.ArrayList;

/**
 * Created by Vincent on 29/11/2016.
 */
public class OutputDisk {
    //The numbers on the disk.
    private int[] array;

    //value to check which array number we should read next.
    private int arrayIndex;

    public OutputDisk(int size) {
        this.array = new int[size];
        arrayIndex = 0;
    }

    public boolean write(int number){
        if (arrayIndex < array.length) {
            array[arrayIndex] = number;
            ++arrayIndex;
            return true;
        }
        return false;
    }

    public void printDiskContents(){
        System.out.println("Disk contents: ");
        for(int number : array){
            System.out.print(number + " ");
        }
        System.out.println(" ");
        System.out.println(" ");
    }

    public int[] getDiskContents(){
        return array;
    }
}
