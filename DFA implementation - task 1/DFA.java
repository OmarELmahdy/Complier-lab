package csen1002.main.task1;

import java.util.ArrayList;

/**
 * Write your info here
 * 
 * @name Omar ELmahdy
 * @id 
 * @labNumber 
 */
public class DFA {
	
	int currStateName=0;
	ArrayList<State> statesArr = new ArrayList<State>(); 

	public static void main(String[]args) {
		DFA dfa1 = new DFA("0,0,1;1,2,1;2,0,3;3,3,3#1,3");
		dfa1.run("101");
	}
	
	public DFA(String description) {
		
		
		String[]hashSplit=description.split("#");
		String allStates=hashSplit[0];
		String acceptStates=hashSplit[1];
		String[]semiSplit=allStates.split(";");

		for(int i=0;i<semiSplit.length;i++) {
			State curr= new State();
			curr.stateName=semiSplit[i].charAt(0)-48;
			curr.ifZero=semiSplit[i].charAt(2)-48;
			curr.ifOne=semiSplit[i].charAt(4)-48;
			
			statesArr.add(curr);
		
			/*
			System.out.println(curr.stateName);
			System.out.println(curr.ifZero);
			System.out.println(curr.ifOne);
			System.out.println("    ");
			*/
		}
		
		
		for(int i=0;i<acceptStates.length();i++) {
			if (acceptStates.charAt(i)!=','){
				
				int x =acceptStates.charAt(i)-48;
				
				for (int j = 0; j < statesArr.size(); j++) {
					if (statesArr.get(j).stateName==x) {
						
						statesArr.get(j).accept=true;

						}
					
					}
			}
		}
		
		for (int i = 0; i < statesArr.size(); i++) {
		      System.out.println("StateName: "+statesArr.get(i).stateName);
		      System.out.println("ifZero: "+statesArr.get(i).ifZero);
		      System.out.println("ifOne: "+statesArr.get(i).ifOne);
		      System.out.println("accept: "+statesArr.get(i).accept);
				System.out.println("    ");

		    }
		//String[]commaSplit=    .split(",");
		
		
		
	}

	/**
	 * Returns true if the string is accepted by the DFA and false otherwise.
	 * 
	 * @param input is the string to check by the DFA.
	 * @return if the string is accepted or not.
	 */
	public boolean run(String input) {
		boolean acceptRun=false;
		
		for(int i=0;i<input.length();i++) {
			
				System.out.println("input.charAt(i)"+input.charAt(i));
		
				State curr = statesArr.get(currStateName);
					
				System.out.println("currStateName"+currStateName);
		
				if(input.charAt(i)=='1') {
					
					currStateName= curr.ifOne;
				
						System.out.println("currStateName: "+currStateName);
				}
				if(input.charAt(i)=='0') {
					
					currStateName= curr.ifZero;
				
						System.out.println("currStateName: "+currStateName);
				}
		
			
		}
		System.out.println("currStateName LAST: "+currStateName);
		State last = statesArr.get(currStateName);
		System.out.println(" LAST.statename: "+last.stateName);
		System.out.println(" LAST.accept: "+last.accept);

		acceptRun=last.accept;

		
		System.out.println("acceptRun: "+acceptRun);

		return acceptRun;
	}
}
class State{
	boolean accept;
	int stateName;
	int ifZero;
	int ifOne;
	//sth i dont remember
	
	
	
}
