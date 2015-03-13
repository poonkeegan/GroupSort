import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class SortClass {

	public static void main(String[] args) {
		
		int groupSize;
		int classSize;
		int groups;
		int prevGroups;
		
		
		try {
			Scanner scanner = new Scanner(new File("Data.txt"));
			
			//skip first line of text and grab group size
			scanner.nextLine();
			groupSize = Integer.parseInt(scanner.nextLine());
			
			//grab size of class
			scanner.nextLine();
			classSize = Integer.parseInt(scanner.nextLine());
			
			//how many groups are there (due to int the decimal is taken care of)
			groups = classSize/groupSize;
			
			//student array made of names in list
			scanner.nextLine();
			//create list
			Student students[] = new Student[classSize];
			//grab name and insert
			for(int i = 0; i < classSize; i++){
				students[i] = new Student(scanner.nextLine());
			}
			
			//grab previous groups
			scanner.nextLine();
			prevGroups = Integer.parseInt(scanner.nextLine());
			
			//get already worked with partners
			scanner.nextLine();
			for(int i = 0; i < groups; i++){
				
				//split group into seperate names
				String temp[] = scanner.nextLine().split(" ");
				
				//check each student in the group
				for(int j = 0; j < temp.length; j++){
					
					//find student in list of students
					int index = 0;
					for(int k = 0; k < groups; k++){
						if(temp[j].equals(students[k].getName())){
							index = k;
							k = groups;
						}
					}
					
					//add other group members to the list of partners
					for(int k = 0; k < temp.length; k++){
						if(k != j)
						students[index].addPartner(temp[k]);
					}
				}
			}
			
			
			
//			for(int i = 0; i < students.length; i++){
//				print(students[i].alreadyWorked());
//			}
			
			print(students[0].alreadyWorked());
			
			
			//no Data list of students
		} catch (FileNotFoundException e) {
			System.out.println("No Data.txt file found please place in this folder and try again");
		}
		
	}
	
	public static void print(String print){
		System.out.println(print);
	}
	
	public static void print(String print []){
		for(String s: print){
			System.out.print(s+ " ");
		}
		System.out.println();
	}
}
