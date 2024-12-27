package task;
import java.util.Scanner;
public class task2 {

	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		int numSubjects = 5;
		int[] Marks = new int[numSubjects];
		int totalMarks = 0;
		double averagePercentage;
		String grade = "";
		
		for(int s=1; s<=numSubjects; s++) {
			System.out.println("Enter marks for subjects" + s + ": ");
			totalMarks +=  scanner.nextInt();
			
		}
		averagePercentage = (double) totalMarks / numSubjects;
		if (averagePercentage >= 90) {
			grade = "A+";	
		}else if (averagePercentage >= 80) {
			grade = "A";
		}else if (averagePercentage >= 70) {
			grade = "B+";
		}else if (averagePercentage >= 60) {
			grade = "B";
		}else if (averagePercentage >= 50) {
			grade = "C";
			
		}else {
			grade = "F";
		}
		System.out.println("\nTotal Marks: "+ totalMarks );
		System.out.println("Average Percentage: " + averagePercentage + "%");
		System.out.println("Grade: "+ grade);
		
		scanner.close();

	}

}

	


