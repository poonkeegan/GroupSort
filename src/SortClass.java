import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
public class SortClass {

	public static void main(String[] args) {
		
		int groupSize;
		int classSize;
		int groups;
		int prevGroups;
		int sets;
		int largeGroup;
		int groupSizing;
		
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
			
			//how many groups need to be larger to fit remaining people
			largeGroup = classSize%groupSize;
			
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
			for(int l = 0; l < prevGroups; l++){
				scanner.nextLine();
				for(int i = 0; i < groups; i++){
					
					//split group into separate names
					String temp[] = scanner.nextLine().split(" ");
					
					//check each student in the group
					for(int j = 0; j < temp.length; j++){
						
						//find student in list of students
						int index = 0;
						for(int k = 0; k < classSize; k++){
							if(temp[j].equals(students[k].getName())){
								index = k;
								k = classSize;
							}
						}
						
						//add other group members to the list of partners
						for(int k = 0; k < temp.length; k++){
							if(k != j)
							students[index].addPartner(temp[k]);
						}
					}
				}
			}
			
			scanner.close();
			scanner = new Scanner(System.in);
			
			
			//Start generating sets of random groups
			print("How many sets?");
			sets = Integer.parseInt(scanner.nextLine());
			for(int l = 0; l < sets; l++){
				
				//create remaining list of students
				ArrayList<String> stuList = new ArrayList<String>();
				for(Student s : students){
					stuList.add(s.getName());
				}
				
				//generate random groups
				for(int i = 0; i < groups; i++){
					
					//how big is the group (is there list overs who need to be put in a group)
					if(i+largeGroup >= groups){
						groupSizing = groupSize + 1;
					}else{
						groupSizing = groupSize;
					}
					
					//find where current student is in array
					int index = 0;
					for(int j = 0; j < students.length; j++){
						if(students[j].getName().equals(stuList.get(i))){
							index=i;
							j = students.length;
						}
					}
					
					//create new list to compare remaining students to students who can be worked with
					ArrayList<String> temp = new ArrayList<String>();
					for(int j = 0; j < stuList.size(); j++){
						if(!students[index].haveWorked(stuList.get(j)))
							temp.add(stuList.get(j));
					}
					//insert random students into group
					if(temp.size() >= groupSizing){
						String group [] = new String [groupSizing];
						for(int j = 0; j < group.length; j++){
							int randStu = (int) (Math.random()*temp.size());
							group[j] = temp.get(randStu);
							//take the student out of the remaining list
							temp.remove(randStu);
						}
						
						//check each student in the group
						for(int j = 0; j < group.length; j++){
							//find student in list of students
							int indx = 0;
							for(int k = 0; k < classSize; k++){
								if(group[j].equals(students[k].getName())){
									index = k;
									k = classSize;
								}
							}
							
							//add other group members to the list of partners
							for(int k = 0; k < group.length; k++){
								if(k != j){
								students[indx].addPartner(group[k]);
								//take the student out of the remaining list
								if(stuList.indexOf(group[k]) != -1)
								stuList.remove(stuList.indexOf(group[k]));
								}
								
							}
						}
						
						print(group);
						
					}
					//reset partners and re make this set continuing on
					else{
						print("Can only make " + l + " fully randomized sets");
						
						l--;
						for(int j = 0; j < students.length; j++){
							students[j].resetPartner();
						}
					}
				}
				print("");
			}
			scanner.close();
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
