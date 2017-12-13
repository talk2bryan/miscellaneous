import java.util.Arrays;

public class MinHeapInts {
	public static void main(String[] args) {
		MinHeap heap = new MinHeap();
		System.out.println( heap.add(7) );
        System.out.println( heap.add(6));
        System.out.println( heap.add(3));
        System.out.println( heap.add(1));
        System.out.println( heap.add(5));
        System.out.println( heap.add(4));
        System.out.println( heap.add(2));
        heap.display();
	}
}

// MeanHeap implementation using an array DT.
// for each index,
// 		left_child_index = index * 2 + 1
// 		right_child_index = index * 2 + 2
// 		parent_index = (index - 2) / 2
class MinHeap {
    private static final int INITIAL_CAPACITY = 10;
    private int array[];
    private int pile[];
    private int size, num_items;
    private int capacity;

    public MinHeap(){
        capacity = INITIAL_CAPACITY;
        pile = new int[capacity];
        array = new int[capacity];
        size = num_items = 0;
    }

    // Ensures that there is always space to perform
    // insertion.
    private void ensure_capacity(){
        if(size == capacity){
            capacity *= 2;
            int [] new_pile_array = new int[capacity];
            int [] new_heap_array = new int[capacity];
            System.arraycopy(pile, 0, new_pile_array, 0, size);
            System.arraycopy(array, 0, new_heap_array, 0, size);
            pile = new_pile_array;
            array = new_heap_array;
        }
    }

    private int get_parent_index(int index){
        int result = (index - 1) / 2;

        if (result < 0 ) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return result;
    }

    private int get_left_child_index(int index){
        int result = (index * 2) + 1;

        if (result < 0 ) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return result;
    }

    private int get_right_child_index(int index){
        int result = (index * 2) + 2;

        if (result < 0 ) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return result;
    }

    private boolean has_parent(int index){
        return ( ((index - 1) / 2) >= 0);
    }

    private void swap(int from, int to){
        int temp = array[from];
        array[from] = array[to];
        array[to] = temp;
    }

    private void heapify_up(){
        int curr_index = size-1;

        while ( has_parent(curr_index) && array[get_parent_index(curr_index)] > array[curr_index]) {
            swap(curr_index, get_parent_index(curr_index));
            curr_index = get_parent_index(curr_index);
        }


    }

    private void heapify_down(){
        int curr_index = 0;
        int smaller_index;

        int left_child_index, right_child_index;
        loop:
        while( (left_child_index = get_left_child_index(curr_index)) < size ){
            smaller_index = left_child_index;
            if ((right_child_index = get_right_child_index(curr_index)) < size) {
                // There is a right child
                smaller_index = (array[left_child_index] < array[right_child_index]) ?
                        left_child_index : right_child_index;
            }
            if (array[curr_index] < array[smaller_index]) {
                break loop; // In the right position.
            }
            else {
                swap(curr_index, smaller_index);
            }
            curr_index = smaller_index;
        }
    }


    public double add(int n){
        ensure_capacity();
        pile[size] = n;
        array[size++] = n;
        num_items++;
        heapify_up();

        return median();
    }

    public int poll(){
        if(size == 0)
            throw new ArrayIndexOutOfBoundsException();
        int result = array[0];
        array[0] = array[--size];
        heapify_down();
        return result;
    }

    private double median(){
        if (size == 0)
            throw new IllegalStateException();
        int [] tmp = new int[num_items];

        for (int i = 0; i < num_items; i++) {
            tmp[i] = poll();
        }
//        System.out.println(Arrays.toString(tmp));
        double result = (tmp[num_items/2] + tmp[(num_items-1)/2])/2.0;
        System.arraycopy(tmp, 0, array, 0, num_items);
        size = num_items;
        return  result;
    }

    public int peek() {
        return  array[0];
    }

    public void display(){
        System.out.println(Arrays.toString(array));
    }
}
