import java.util.Random;

public class Main {

    public static void main(String[] args) {
	// write your code here
        //int[] array = new int[] {29,7,26,82,75,10,66,76,7,95,78,28,29,67,69};
        int[] array = generateIntArray();

        Random rand = new Random();
        int memorySize = rand.nextInt((10 - 3) + 1) + 3;

        InputDisk in = new InputDisk(array);
        OutputDisk out = new OutputDisk(array.length);

        RSHeap testheap = new RSHeap(memorySize, in, out);
        out.printDiskContents();
    }

    public static int[] generateIntArray(){
        Random rand = new Random();
        int arrayLenth = rand.nextInt((100 - 1) + 1) + 1;
        int[] array = new int[arrayLenth];

        for(int i = 0; i < arrayLenth; ++i){
            int randomNumber = rand.nextInt((100 - 1) + 1) + 1;
            array[i] = randomNumber;
        }

        return array;
    }
}
