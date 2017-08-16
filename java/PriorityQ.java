public class PriorityQ
{
	public static void main(String[] args) {
		PriorityQueueClass myQueue = new PriorityQueueClass(10);
		myQueue.insert(33);
		myQueue.insert(55);
		myQueue.insert(11);
		myQueue.insert(44);
		myQueue.insert(22);
		myQueue.insert(88);
		myQueue.insert(66);
		myQueue.insert(100);
		myQueue.insert(99);
		myQueue.insert(77);
		myQueue.insert(110);

		while (!myQueue.isEmpty()) {
			int item = myQueue.remove();
			System.out.print(item + " ");
		}
		System.out.println("");
	}
}

class PriorityQueueClass
{
	private int max_size;
	private int array[];
	private int num_items;

	public PriorityQueueClass(int n) {
		max_size = n;
		num_items = 0;
		array = new int [max_size];
	}
	
	public boolean isEmpty(){return num_items==0;}
	public boolean isFull(){return num_items==max_size;}

	public void insert(int n){
		if (!isFull()) {
			if (isEmpty()) {
				array[num_items++] = n;
			}
			else {
				int j = num_items-1;
				while(j>=0 && array[j] < n){
					array[j+1] = array[j];
					j--;
				}
				array[j+1] = n;
				num_items++;
			}
		}
	}
	public int remove(){
		if (!isEmpty()) {
			return array[--num_items];
		}
		return -1;
	}
}