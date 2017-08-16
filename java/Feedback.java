//comopare_grades
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.*;
import java.util.Scanner;
import java.util.*;



public class Feedback{
	static final int MAX_STUDENTS = 100;
	static Double [] altered_strudents_grades;

	public static void main(String[] args) throws IOException{
		String [] altered_strudents;

		altered_strudents = comopare_grades();
		generate_output(altered_strudents);

		System.out.println("altered_strudents: " + Arrays.toString(altered_strudents));	
	}
	public static String[] comopare_grades() throws IOException{
		String file1, file2;
		String [] result;

		String [] running_list = new String[MAX_STUDENTS];
		Double [] running_list_grades = new Double[MAX_STUDENTS];

		int index = 0;

		Scanner kbd = new Scanner(System.in);
		file1 = "old_grades.txt"; //kbd.nextLine();

		file2 =  "new_grades.txt";//kbd.nextLine();
		try
		{
			if ( (file1 != null && file2 != null) && (file1 != file2) ) {
				BufferedReader in1 = new BufferedReader(new FileReader(file1));
				BufferedReader in2 = new BufferedReader(new FileReader(file2));

				String line1, line2;
				line1 = in1.readLine();
				line2 = in2.readLine();
				String [] contents_a, contents_b;
				while(line1 != null){
					if (line2 != null) {
						contents_b = line2.trim().split("\\s+");
						contents_a = line1.trim().split("\\s+");

						if (Double.parseDouble(contents_a[1]) != Double.parseDouble(contents_b[1])) {
							running_list[index] = contents_b[0];
							running_list_grades[index++] = Double.parseDouble(contents_b[1]);
						}
					}
					else {
						System.out.println("there are extra lines in: "+ file1);
					}
					line2 = in2.readLine();
					line1 = in1.readLine();
				}
				if (line1 != null) {
					System.out.println("there are extra lines in: "+file2);
				}
			}

		}
		catch(IOException ioe){
			System.out.println(ioe.getMessage());
		}
		result = new String[index];
		altered_strudents_grades = new Double[index];
		System.arraycopy(running_list_grades,0,altered_strudents_grades,0,index);
		System.arraycopy(running_list,0,result,0,index);
		return result;
	}
	public static void generate_output(String [] students) throws IOException{
		final double MAX_GRADE = 30;
		double student_grade;


		Scanner kbd = new Scanner(System.in);
		String file = "feedback.txt"; 

		try
		{
			if ( file != null ) {
				BufferedReader in = new BufferedReader(new FileReader(file));

				String line;
				line = in.readLine();
				String [] contents;
				String template_begin = "Hi,\n\n"+"Your mark for COMP 2160 Assignment 2 is [";
				String template_end = "]\n"+"\nFeedback:";

				System.out.println(Arrays.toString(students));

				while(line != null){
					if (line.length()>0) {
						if (line.indexOf("===>")>=0) {
							contents= line.trim().split("\\s+");
							
							String file_name = ""+contents[1]+".txt";
							int position = java.util.Arrays.binarySearch(students, contents[1]);
							if (position>=0) {
								student_grade = altered_strudents_grades[ position ];
								PrintWriter out = new PrintWriter(new FileWriter(file_name));
								out.print(template_begin);
								out.print(student_grade);
								out.print("/");
								out.print(MAX_GRADE);
								out.println(template_end);

								line = in.readLine();
								while(line.length() > 0 ){
									out.println(line);
									line = in.readLine();
								}
								out.close();
							}
							else
								line = in.readLine();
						}
					}
					line = in.readLine();
				}
			}
		}
		catch(IOException ioe){
			System.out.println(ioe.getMessage());
		}
	}
}