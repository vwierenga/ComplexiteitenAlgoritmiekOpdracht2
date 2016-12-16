import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int[] memorySizes = new int[] {2,4,8,16,32};
        for (int memorySize : memorySizes) {
            int[] inputFileLengths = new int[] {memorySize*2, memorySize*4, memorySize*8, memorySize*16, memorySize*32};
            for(int inputFileLenght : inputFileLengths){
                int[] array = generateIntArray(inputFileLenght);

                //Random rand = new Random();
                //int memorySize = rand.nextInt((10 - 3) + 1) + 3;

                generateOutputFile(array, memorySize);
            }
        }

        int[] bestCase = new int[] {2,4,8,16,32,64,128,256};
        int[] worstCase = new int[] {256,128,64,32,16,8,4,2};

        generateOutputFile(bestCase, 2);
        generateOutputFile(worstCase, 2);
    }

    /**
     * Generates an int array of a random length.
     * @return an int array of a random lenght.
     */
    private static int[] generateIntArray(){
        Random rand = new Random();
        int arrayLenth = rand.nextInt((100 - 1) + 1) + 1;
        int[] array = new int[arrayLenth];

        for(int i = 0; i < arrayLenth; ++i){
            int randomNumber = rand.nextInt((100 - 1) + 1) + 1;
            array[i] = randomNumber;
        }

        return array;
    }

    /**
     * Generates an int array with a lenght of arrayLength.
     * @param arrayLength The lenght of the output array.
     * @return an int array with a lenght of arrayLength.
     */
    private static int[] generateIntArray(int arrayLength){
        int[] array = new int[arrayLength];

        Random rand = new Random();
        for(int i = 0; i < arrayLength; ++i){
            int randomNumber = rand.nextInt((100 - 1) + 1) + 1;
            array[i] = randomNumber;
        }

        return array;
    }

    private static void generateOutputFile(int[] array, int memorySize){
        System.out.println(" ");
        System.out.println("----------------------------------------------------");
        System.out.println(" ");

        InputDisk in = new InputDisk(array);
        OutputDisk out = new OutputDisk(array.length);

        RSHeap testheap = new RSHeap(memorySize, in, out);
        out.printDiskContents();

        System.out.println("Input file size (N): " + array.length);
        System.out.println("Memory size (H): " + memorySize);

        testheap.printRuns();
    }
}
