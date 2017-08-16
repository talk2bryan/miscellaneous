//StackApp.java
import java.io.*;
public class StackApp
{
	public static void main(String[] args) throws IOException {
		Stack theStack = new Stack(10);
		theStack.push(20);
		theStack.push(40);
		theStack.push(60);
		theStack.push(80);

		while (!theStack.isEmpty()) {
			int value = theStack.pop();
			System.out.print(value);
			System.out.print(" ");
		}
		System.out.println();

		//String Stack implementation.
string_stack:
		while( true ){
			String input,output;
			System.out.print("Enter a String. Q to quit: ");
			System.out.flush();
			input = getInput();

			if (input.equalsIgnoreCase("q")) {
				break string_stack;
			}
			int count = input.length();
			int i = 0;
			StringStack strStack = new StringStack(10);

			while(i < count)
			{
				strStack.push(input.charAt(i));
				i++;
			}
			output = getReverse(strStack);
			System.out.println(output);
		}
	}

	public static String getReverse(StringStack stack)
	{
		String result = "";
		while(!stack.isEmpty())
		{
			result+= stack.pop();
		}
		return result;
	}

	public static String getInput() throws IOException
	{
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String str = br.readLine();
		return str;
	}
}


class StringStack
{
	private int size;
	private char[] array;
	private int numElems;

	public StringStack(int n)
	{
		size = n;
		numElems = 0;
		array = new char[size];
	}

	public void push(char n)
	{
		if(!isFull())
		{
			array[numElems++] = n;
		}
	}
	public char pop()
	{
		if(!isEmpty())
		{
			return array[--numElems];
		}
		return '\0';
	}
	public boolean isEmpty()
	{
		return numElems == 0;
	}
	public boolean isFull()
	{
		return numElems == size;
	}
	public char peek()
	{
		if(!isEmpty())
			return array[numElems];
		return '\0';
	}
}
class Stack
{
	private int size;
	private int[] array;
	private int numElems;

	public Stack(int n)
	{
		size = n;
		numElems = 0;
		array = new int[size];
	}

	public void push(int n)
	{
		if(!isFull())
		{
			array[numElems++] = n;
		}
	}
	public int pop()
	{
		if(!isEmpty())
		{
			return array[--numElems];
		}
		return -1;
	}
	public boolean isEmpty()
	{
		return numElems == 0;
	}
	public boolean isFull()
	{
		return numElems == size;
	}
	public int peek()
	{
		if(!isEmpty())
			return array[numElems];
		return -1;
	}
}