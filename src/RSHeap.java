import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.Arrays;

/**
 * Created by Vincent on 29/11/2016.
 */
public class RSHeap {
    private int[] memory;
    private int heapSize;
    private int deadspace;

    public RSHeap(int memorySize, InputDisk in, OutputDisk out) {
        this.memory = new int[memorySize];
        this.heapSize = 0;
        this.deadspace = 0;

        //Read memorySize elements from in to array and build a minheap.
        int[] array = new int[memorySize];
        for(int i = 0; i < memorySize; ++i){
            array [i] = in.read();
        }
        buildHeap(array);

        System.out.println("Deadspace: " + deadspace + " Heap: " + heapSize);
        System.out.println("Memory contents at start: ");
        for(int number : memory){
            System.out.print(number + " ");
        }
        System.out.println("");
        out.printDiskContents();

        while (!in.isEmpty()){
            System.out.println("Deadspace: " + deadspace + " Heap: " + heapSize);
            System.out.println("Memory contents: ");
            for(int number : memory){
                System.out.print(number + " ");
            }
            System.out.println("");
            out.printDiskContents();

            int smallest = findMin();
            deleteMin();
            out.write(smallest);
            int next = in.read();
            if(next >= smallest){ //Checks if next fits in the current run.
                insert(next); //Add next to heap.
            } else { //Add next to deadspace
                ++deadspace;
                memory[memorySize - deadspace] = next;
            }
            if(heapSize == 0){ //Free the deadspace and add it's contents to the heap.
                deadspace = 0;
                buildHeap(memory);
            }
        }
        while (heapSize > 0){ //Write the rest of the heap to out.
            System.out.println("Deadspace: " + deadspace + " Heap: " + heapSize);
            System.out.println("Memory contents in is empty: ");
            for(int number : memory){
                System.out.print(number + " ");
            }
            System.out.println("");
            out.printDiskContents();

            out.write(findMin());
            deleteMin();
        }
        if (deadspace > 0){ //Build a heap with the contents of the deadspace
            System.out.println("Deadspace: " + deadspace + " Heap: " + heapSize);
            System.out.println("Memory contents : ");
            for(int number : memory){
                System.out.print(number + " ");
            }
            System.out.println("");
            out.printDiskContents();

            int[] tempArray = Arrays.copyOfRange(memory, memory.length - deadspace, memory.length);
            buildHeap(tempArray);
            deadspace = 0;

            System.out.println("Deadspace: " + deadspace + " Heap: " + heapSize);
            System.out.println("Memory contents : ");
            for(int number : memory){
                System.out.print(number + " ");
            }
            System.out.println("");
            out.printDiskContents();
        }
        while (heapSize > 0){ //Write the remaining numbers in the heap to out.
            out.write(findMin());
            deleteMin();
        }
    }

    public void buildHeap(int[] array){
        for(int integer : array){ //Add every integer to the heap.
            System.out.println("Memory Buildheap : ");
            for(int number : memory){
                System.out.print(number + " ");
            }
            System.out.println("");

            insert(integer);
        }
    }

    public boolean insert(int element){
        if(heapSize < (memory.length - deadspace)){
            memory[heapSize] = element;
            int elementLocation = heapSize;

            int parentLocation;
            if(elementLocation % 2 == 0 && elementLocation != 0) {
                parentLocation = (elementLocation / 2) - 1;
            } else {
                parentLocation = (elementLocation / 2);
            }

            while(element < memory[parentLocation]){ //Repair the heap (percolate up).
                swap(elementLocation, parentLocation);
                elementLocation = parentLocation;
                if(elementLocation % 2 == 0 && elementLocation != 0) {
                    parentLocation = (elementLocation / 2) - 1;
                } else {
                    parentLocation = (elementLocation / 2);
                }
            }

            ++heapSize;
            return true;
        }
        System.out.println("problems");
        return false;
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

            //Delete the smallest number.
            memory[emptylocation] = 0;
            int childTwoLocation = (emptylocation + 1) * 2;
            int childOneLocation = childTwoLocation - 1;

            while(childOneLocation < (heapSize - 1)){
                //Check which element should be at the top of the heap.
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
            --heapSize;

            //Repair the heap (percolate down).
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
