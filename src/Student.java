import java.util.ArrayList;


public class Student {
	private String name;
	ArrayList<String> workedWith = new ArrayList<String>();
	public Student(String n){
		name = n;
		workedWith.add(name);
	}
	
	//returns the list of students this student has already worked with
	public String[] alreadyWorked(){
		String temp[] = new String[workedWith.size()];
		for(int i = 0; i < temp.length; i++){
			temp[i] = workedWith.get(i);
		}
		return temp;
	}
	
	
	//name of student
	public String getName(){
		return name;
	}
	
	
	//adds the partners the student has worked with into the list of students they have worked with
	public void addPartner(String list){
			workedWith.add(list);
	}
	
	//if they haved worked with a (string) student before
	public boolean haveWorked(String partner){
		if(workedWith.indexOf(partner) == -1)
			return false;
		else
			return true;
	}
	
	public void resetPartner(){
		workedWith.clear();
		workedWith.add(name);
	}
	
}
