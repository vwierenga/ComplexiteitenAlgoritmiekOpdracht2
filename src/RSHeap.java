import com.sun.org.apache.xpath.internal.SourceTree;

/**
 * Created by Vincent on 29/11/2016.
 */
public class RSHeap {
    private int[] memory;
    private int heapSize;
    private int deadspace;

    public RSHeap(int memorySize) {
        this.memory = new int[memorySize];
        this.heapSize = 0;
        this.deadspace = 0;

        buildHeap();

    }

    public void buildHeap(){
        int[] testintarray = new int[] {29,7,26,82,75,10,66,76,7,95,78,28,29,67,69};

        /*
        insert(2);

        insert(3);

        insert(4);

        insert(1);
        */

        for(int integer : testintarray){
            insert(integer);
        }


        System.out.println("Memory ");
        for(int integer : memory){
            System.out.print(integer + " ");
        }
        System.out.println("");
        deleteMin();
        deleteMin();
        deleteMin();
        System.out.println("Memory ");
        for(int integer : memory){
            System.out.print(integer + " ");
        }
        System.out.println("");
    }

    public void insert(int element){
        if(heapSize < (memory.length - deadspace)){

            memory[heapSize] = element;
            int elementLocation = heapSize;

            int parentLocation;
            if(elementLocation % 2 == 0 && elementLocation != 0) {
                parentLocation = (elementLocation / 2) - 1;
            } else {
                parentLocation = (elementLocation / 2);
            }

            while(element < memory[parentLocation]){
                swap(elementLocation, parentLocation);
                elementLocation = parentLocation;
                if(elementLocation % 2 == 0 && elementLocation != 0) {
                    parentLocation = (elementLocation / 2) - 1;
                } else {
                    parentLocation = (elementLocation / 2);
                }
            }

            heapSize++;
        }
    }

    public void swap(int elementOne, int elementTwo){
        int temp = memory[elementOne];
        memory[elementOne] = memory[elementTwo];
        memory[elementTwo] = temp;
    }

    public int findMin(){
        return memory[0];
    }

    public void deleteMin(){
        if(heapSize > 0) {
            int emptylocation = 0;
            memory[emptylocation] = 0;
            int childTwoLocation = (emptylocation + 1) * 2;
            int childOneLocation = childTwoLocation - 1;

            while(childOneLocation < (heapSize - 1)){
                if ((memory[childOneLocation] < memory[childTwoLocation]) || !(childTwoLocation < (heapSize - 1))) {
                    swap(emptylocation, childOneLocation);
                    emptylocation = childOneLocation;

                } else {
                    swap(emptylocation, childTwoLocation);
                    emptylocation = childTwoLocation;
                }
                childTwoLocation = (emptylocation + 1) * 2;
                childOneLocation = childTwoLocation - 1;
            }

            swap(emptylocation, (heapSize - 1));
            int elementLocation = emptylocation;
            heapSize--;

            int parentLocation;
            if(elementLocation % 2 == 0 && elementLocation != 0) {
                parentLocation = (elementLocation / 2) - 1;
            } else {
                parentLocation = (elementLocation / 2);
            }

            while(memory[elementLocation] < memory[parentLocation]){
                swap(elementLocation, parentLocation);
                elementLocation = parentLocation;
                if(elementLocation % 2 == 0 && elementLocation != 0) {
                    parentLocation = (elementLocation / 2) - 1;
                } else {
                    parentLocation = (elementLocation / 2);
                }
            }
        }
    }


}
