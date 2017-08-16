//CircularQueue.java
//isFull, isEmpty, size(), remove(), peek(), insert()

public class CircularQueue
{
	public static void main(String[] args) {
		CircularQueueClass myQueue = new CircularQueueClass(10);
		myQueue.insert(11);
		myQueue.insert(22);
		myQueue.insert(33);
		myQueue.insert(44);
		myQueue.insert(55);
		myQueue.insert(66);
		myQueue.insert(77);
		myQueue.insert(88);
		myQueue.insert(99);
		myQueue.insert(100);
		myQueue.insert(110);
		myQueue.display();
		myQueue.remove();
		myQueue.remove();
		myQueue.remove();
		myQueue.display();
		myQueue.insert(120);
		myQueue.insert(130);
		myQueue.display();
		myQueue.insert(140);
		myQueue.insert(150);
		System.out.println("peek: "+myQueue.peek());
		
		myQueue.display();
	}
}

class CircularQueueClass
{
	private int t_size;
	private int front;
	private int rear;
	private int num_elems;
	private int [] theArr;

	public CircularQueueClass(int n)
	{
		t_size = n;
		front = num_elems;
		rear =-1;
		theArr = new int [t_size];
	}
	public boolean isFull() {return num_elems == t_size;}
	public boolean isEmpty() {return num_elems == 0;}
	public int size() {return num_elems;}

	public boolean insert(int n)
	{
		int pos;
		boolean result = false;
		if (!isFull()) {
			++rear;
			pos = rear%t_size;
			theArr[pos] = n;
			rear = rear%t_size;
			num_elems++;
			result = true;
			System.out.println("inserted "+ n +", rear = "+rear+", num_elems = "+num_elems);
		}
		else {
			System.out.println("Could not insert "+ n+", array full.");
		}
		return result;
	}
	public int peek(){
		if (!isEmpty()) {
			return theArr[front];
		}
		return -1;
	}
	public void remove(){
		if (!isEmpty()) {
			front++;
			front = front%t_size;
			num_elems--;
		}
	}
	public void display()
	{
		String ch;
		int pos= 0;

		if (isEmpty()) {
			System.out.println("Queue is empty!");
		}
		else {
			for ( int i = 0;i < num_elems ;++i ) {
				pos = (front+i)%t_size;
				ch = (pos == rear) ? "" : ", ";
				System.out.print(theArr[pos]+ch);
			}
		}
		System.out.println();
		System.out.println("front: "+ front+", rear: "+rear);
	}
}