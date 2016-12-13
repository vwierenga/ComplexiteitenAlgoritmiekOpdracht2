/**
 * Created by Vincent on 29/11/2016.
 */
public class InputDisk {
    //The numbers on the disk.
    private int[] array;

    //value to check which array number we should read next.
    private int arrayEnd;

    //The inputdisk is empty when this is true.
    private boolean empty;

    public InputDisk(int[] array){
        this.array = array;
        arrayEnd = array.length;
    }

    public int read(){
        --arrayEnd;
        if(arrayEnd == 0){
            empty = true;
        }
        return array[arrayEnd];
    }

    public boolean isEmpty(){
        return empty;
    }
}
