public class Main {

    public static void main(String[] args) {
	// write your code here
        int[] array = new int[] {29,7,26,82,75,10,66,76,7,95,78,28,29,67,69};
        InputDisk in = new InputDisk(array);
        OutputDisk out = new OutputDisk(array.length);

        RSHeap testheap = new RSHeap(5, in, out);
        out.printDiskContents();
    }
}
