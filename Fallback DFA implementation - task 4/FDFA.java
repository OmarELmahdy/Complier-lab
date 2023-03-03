package csen1002.main.task4;

import java.util.*;



/**
 * Write your info here
 * 
 * @name Omar 
 * @id 
 * @labNumber 
 */
public class FDFA {
	int currStateName=0;

	ArrayList<State> statesArr = new ArrayList<State>(); 

	Stack<State> stack = new Stack<State>();
	
	Stack<Character> alphaStack = new Stack<Character>();
	Stack<Character> alphaStack2 = new Stack<Character>();

	public static void main(String[]args) {
		FDFA fdfa1 = new FDFA("0,1,3,N;1,2,1,O;2,4,4,A;3,4,5,P;4,4,4,Q;5,4,4,B#2,5");
		fdfa1.run("0011");
		

		//FDFA fdfa1 = new FDFA("0,0,1,A;1,2,1,B;2,0,3,C;3,3,3,N#0,1,2");
		//fdfa1.run("110101010");
		//fdfa1.run("0");

	}
	
	public FDFA(String description) {
		String[]hashSplit=description.split("#");
		String allStates=hashSplit[0];
		String acceptStates=hashSplit[1];
		String[]semiSplit=allStates.split(";");

		for(int i=0;i<semiSplit.length;i++) {
			State curr= new State();
			curr.stateName=semiSplit[i].charAt(0)-48;
			curr.ifZero=semiSplit[i].charAt(2)-48;
			curr.ifOne=semiSplit[i].charAt(4)-48;
			curr.action=semiSplit[i].charAt(6);
			if (semiSplit[i].charAt(6)<='N'){
				System.out.println("semiSplit[i].charAt(6)  "+semiSplit[i].charAt(6));

			}
			
			statesArr.add(curr);
		
			
			System.out.println("stateName "+curr.stateName);
			System.out.println("if zero "+curr.ifZero);
			System.out.println("if One "+curr.ifOne);
			System.out.println("accept "+curr.accept);
			System.out.println("action "+curr.action);
			
			System.out.println("    ");
			
			
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
				System.out.println("action: "+statesArr.get(i).action);

				System.out.println("    ");

		    }
		
	
	}

	public String run(String input) {
		
		System.out.println("    ");
		System.out.println("   IN RUN   ");
		System.out.println("    ");
		
		boolean flag=false;

		State next = statesArr.get(currStateName);
		stack.add(next);
		
		// filling alpaStack
		

		//for(int i=input.length()-1;i>=0;i--) {
			for(int i=0;i<input.length();i++) {

			alphaStack.push(input.charAt(i));
		
		}//alphaStack.push('0');
			System.out.println("alpha STACKKKc     "+alphaStack);

 
		
		for(int i=0;i<input.length();i++) {
			
				System.out.println("input.charAt(i)"+input.charAt(i));
		
				State curr = statesArr.get(currStateName);
					
				System.out.println("currStateName"+currStateName);
		
				if(input.charAt(i)=='1') {
					
					currStateName= curr.ifOne;
					
					 next = statesArr.get(currStateName);
					stack.add(next);
					 
				
						System.out.println("currStateName: "+currStateName+" ,stack.peek: "+stack.peek().stateName);
				}
				if(input.charAt(i)=='0') {
					
					currStateName= curr.ifZero;
				
					 next = statesArr.get(currStateName);
					stack.add(next);
					
				
						System.out.println("currStateName: "+currStateName+" ,stack.peek: "+stack.peek().stateName);				}
				
				if (next.accept==true) {
					flag=true;

				}
		
			
		}
		System.out.println("currStateName LAST: "+currStateName);
		State last = statesArr.get(currStateName);
		System.out.println(" LAST.statename: "+last.stateName);
		System.out.println(" LAST.accept: "+last.accept);
		
		
		
		///new stuff
		
		//PRINTING ZONE
		System.out.println(" stack.size(): "+stack.size());
		System.out.println(" alphaStack.size(): "+alphaStack.size());
		

		System.out.print("Stack: " );
		for(int i=0;i<stack.size();i++) {

			System.out.print(", "+ stack.elementAt(i).stateName);
			//System.out.println("Stack: " + stack.elementAt(i).stateName+" , " + alphaStack.elementAt(i));
			}
	
		System.out.println("");
		System.out.println("alphaStack: "+alphaStack.toString());

		
		//TOP ACCEPTED and all rejected 
		if(stack.peek().accept==true|| ! flag) {
			System.out.println("Stack Peek action: " + stack.peek().action);
			System.out.println("output: " +input+","+ stack.peek().action);
			
			String p=input+","+ stack.peek().action+";";
			return p;
			
			
		}else {
			
		
		String out=""; 
		String input2="";

		//ACCEPTED INSIDE 
		while( ! stack.isEmpty())	{
		
			State top=stack.peek();
			System.out.println("       top name: " +top.stateName );

			if(top.accept==true) {
				
				String alpha="";
				for(int i=0;i<alphaStack.size();i++) {
					//for(int i=alphaStack.size()-1;i>=0;i--) {

					alpha=alpha+alphaStack.elementAt(i).toString();
				}
				
			
				out=alpha+","+top.action+";";
				
				break;
							}
			if(!alphaStack.isEmpty()) {
							
			stack.pop();
			char c=alphaStack.pop();
			 input2=""+c;
			alphaStack2.push(c); 
			System.out.println("       ccccc: " +c );


			}
			
						}//while end
		
		System.out.println("       input2: " + input2 );

		String out2="";
		String alpha2string="";
		
		for(int i=0;i<alphaStack2.size();i++) {
		alpha2string=alpha2string+alphaStack2.elementAt(i).toString();
		}
		
		System.out.println("           alpha2string:"+alpha2string );
		
		currStateName=0;
		stack.clear();
		State next2 = statesArr.get(currStateName);
		stack.push(next2);

		
		
		//System.out.println("       stack: " +run(alpha2string) );
			
		out2=run(alpha2string);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

		
		System.out.println("out+out2: "+out+out2);

		return out+out2;

			
		
		
			
			}

		

	}

	
}


class State{
	boolean accept;
	int stateName;
	int ifZero;
	int ifOne;	
	char action;
	
	
}