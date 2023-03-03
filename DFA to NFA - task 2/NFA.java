package csen1002.main.task2;

import java.util.*;

/**
 * 
 * @name Omar Elmahdy
 * @id 
 * @labNumber 
 */
public class NFA{
	
	int currStateName=0;
	ArrayList<State> statesArr = new ArrayList<State>(); 
	
	
	
	
	public static void main(String[]args) {
		//NFA NFA1 = new NFA("0,0;1,2;3,3#0,0;0,1;2,3;3,3#1,2#3");
		//NFA NFA1 = new NFA("2,3#4,5;7,8#0,1;0,7;1,2;1,4;3,6;5,6;6,1;6,7#8");
		NFA NFA2 = new NFA("0,1;9,10;11,12;12,13#2,3;13,14#1,2;1,4;3,6;4,5;5,6;6,7;6,19;7,8;7,16;8,9;8,11;10,15;14,15;15,18;18,7;18,19#19");

		//NFA1.run("000001");
	}

	
	public NFA(String description) {

		String[]hashSplit=description.split("#");
		
		String zeroString=hashSplit[0];
		String[]zeroSplit=zeroString.split(";");
		String []zeroArr=new String[30];
		
		String oneString=hashSplit[1];
		String[]oneSplit=oneString.split(";");
	    HashMap<Integer, ArrayList<String>> oneHash = new HashMap<Integer,ArrayList<String>>();

	    String [] oneArr=new String[30];
	    ArrayList<ArrayList<String> > oneList=new ArrayList<ArrayList<String> >();

	    
		String epsilonString=hashSplit[2];
		String[]epsilonSplit=epsilonString.split(";");
		String []epsilonArr=new String[30];

		
		String acceptStates=hashSplit[3];
		
        Queue<Integer> q = new PriorityQueue<>();
        
        q.add(0);


		// ̶A̶S̶S̶U̶M̶I̶N̶G̶ ̶O̶N̶E̶ ̶T̶R̶A̶N̶S̶I̶T̶I̶O̶N̶ ̶O̶N̶L̶Y̶.̶.̶.̶C̶H̶A̶N̶G̶E̶ ̶L̶A̶T̶E̶R̶
		//filling zeroArr
		for(int i=0;i<zeroSplit.length;i++) {

			int from =zeroSplit[i].charAt(0)-48;
			String to =Character.toString(zeroSplit[i].charAt(2));
			zeroArr[from]=to;
			}
		//filling oneArr
		
		for(int i=0;i<oneSplit.length;i++) {
			
			//ArrayList h=oneHash.get(i);
			
			int fromOne =oneSplit[i].charAt(0)-48;
			String toOne =Character.toString(oneSplit[i].charAt(2));
			oneArr[fromOne]=toOne;
			//oneList.get(fromOne).add(toOne);
            //System.out.println(i+" oneArr: "+oneList.get(fromOne));


			//set.add(toOne);
			//oneHash.put(fromOne,toOne);
			}
		//filling epsilonArr
		for(int i=0;i<epsilonSplit.length;i++) {
			System.out.println(i+" epsilonSplit "+epsilonSplit[i]);
			int fromEps =epsilonSplit[i].charAt(0)-48;
			String toEps =Character.toString(epsilonSplit[i].charAt(2));
			if (epsilonSplit[i].charAt(1)!=',') {
				 toEps =Character.toString(epsilonSplit[i].charAt(3));

				
			}
			
			
//			if(epsilonSplit[i].charAt(3)==(1|2|3|4)) {
//				 toEps =Character.toString(epsilonSplit[i].charAt(4));
//				
//			}
			System.out.println(i+" fromEps "+fromEps);

			System.out.println(i+" toEps "+toEps);

			epsilonArr[fromEps]=toEps;
		}

		
		///PRINTING ZONE
		for (int i = 0; i <= epsilonArr.length-1; i++) {
            System.out.println(i+" epsilonArr: "+epsilonArr[i]);

		
		  }
        System.out.println(" oneHash: "+oneHash);
/*
		  for (int i = 0; i <= oneArr.length-1; i++) {
	            System.out.println(i+" oneArr: "+oneHash);
	            
	    }
	
		System.out.println("zeroString "+zeroString);
		System.out.println("oneString "+oneString);
		System.out.println("epsilonString "+epsilonString);
		System.out.println("ACCEPT STATES "+acceptStates);
	*/	
		
		//////
	//State creation zone
		  
	//while states queue not empty
	         System.out.println(" q.peek before WHILE"+q.peek());

		  while(q.peek()!=null) {
		         System.out.println("----------------");

			  int stateName=q.poll(); //el state ely 3aleha el dor mn el queue
		         System.out.println(" q.peek after POLL "+q.peek());

		State temp= new State();
		temp.stateName=stateName;
			//check for zero transitions
			if(zeroArr[stateName]!=null) {
				//System.out.println("zeroArr[stateName] "+zeroArr[stateName]);
				int y =Integer.parseInt(zeroArr[stateName]);
				
				//check for epsilon trans. for zeroArr[stateName]
				if(epsilonArr[y]==null) {
				temp.ifZero=y;
				
				System.out.println("State: "+stateName+" has a ZERO transition of state: "+y+" ,and this found state doesnt have an epsilon transition");
				System.out.println("SO, ifZero: "+y);
				
				if(temp.stateName!=y) {
				q.add(y);	
				System.out.println(" q.peek after ADD "+q.peek());

				}
				
				

				}else {
				//create a new joint state of the target(s) of the epsilon transitions of the state 
					System.out.println("state in one tra"+temp.stateName+"CREATE A JOINT FOR IF ONE");
					State join = new State();
					String yEps=epsilonArr[y];
					System.out.println(" yEps::::: "+yEps);

					int yEpsInt= Integer.parseInt(yEps);

					System.out.println(" y::::: "+y);
					String nameString=y+yEps;
					int name= Integer.parseInt(nameString);
					System.out.println(" name "+name);
					join.stateName=name;
				//  ifZero for join
					//System.out.println(" zero array y::::: "+zeroArr[y]);
					//System.out.println(" zero array yEps::::: "+zeroArr[yEpsInt]);
					String ifZeroString=zeroArr[y]+zeroArr[yEpsInt];					
					String ifZeroString3=ifZeroString.replaceAll("null", "");
					 String ifZeroString2=ifZeroString3.replaceAll(",", "");
					System.out.println(" zerostring2 y::::: "+ifZeroString2);
					int ifZeroInt;
					if(ifZeroString2!="") {
						 ifZeroInt=19;
					}else {
					  ifZeroInt= Integer.parseInt(ifZeroString2);}
					//System.out.println(" zero int y::::: "+ifZeroInt);
					join.ifZero=ifZeroInt;
					q.add(ifZeroInt);
				// ifOne for join
					//System.out.println(" one array y::::: "+oneArr[y]);
					//System.out.println(" one array yEps::::: "+oneArr[yEpsInt]);
					String ifOneString=oneArr[y]+oneArr[yEpsInt];					
					String ifOneString2=ifOneString.replaceAll("null", "");
					//System.out.println(" onestring2 y::::: "+ifOneString2);
					int ifOneInt= Integer.parseInt(ifOneString2);
					//System.out.println(" one int y::::: "+ifOneInt);
					join.ifOne=ifOneInt;
					q.add(ifOneInt);
				//TODO accept for join
					
					
				
				//add join to statesArr
					statesArr.add(join);
					

					
				}
				

			}
			else {
				//Dead state
				//temp.ifZero=19;
				
				temp.accept=false;
				
				System.out.println("state "+temp.stateName+" DEADDD   temp.ifZero=19; ");
			}
			
			//check for one transitions			
			if(oneArr[stateName]!=null) {
				//System.out.println("oneArr[stateName] "+oneArr[stateName]);
				int y =Integer.parseInt(oneArr[stateName]);
				
				
				//check for epsilon trans. for oneArr[stateName]
				if(epsilonArr[y]==null) {
				temp.ifOne=y;
				System.out.println("State: "+stateName+" has a ONE transition of state: "+y+" ,and this found state doesnt have an epsilon transition");
				System.out.println("SO, ifOne: "+y);

				}else {
				//create a new joint state of the target(s) of the epsilon transitions of the state 
					System.out.println("state in one tra"+temp.stateName+"CREATE A JOINT FOR IF ONE");
					State join = new State();
					String yEps=epsilonArr[y];
					int yEpsInt= Integer.parseInt(yEps);

					System.out.println(" y::::: "+y);
					System.out.println(" yEps::::: "+yEps);
					String nameString=y+yEps;
					int name= Integer.parseInt(nameString);
					System.out.println(" name "+name);
					join.stateName=name;
				//  ifZero for join
					//System.out.println(" zero array y::::: "+zeroArr[y]);
					//System.out.println(" zero array yEps::::: "+zeroArr[yEpsInt]);
					String ifZeroString=zeroArr[y]+zeroArr[yEpsInt];					
					String ifZeroString2=ifZeroString.replaceAll("null", "");
					//System.out.println(" zerostring2 y::::: "+ifZeroString2);
					int ifZeroInt= Integer.parseInt(ifZeroString2);
					System.out.println(" zero int y::::: "+ifZeroInt);
					join.ifZero=ifZeroInt;
					q.add(ifZeroInt);
				// ifOne for join
					//System.out.println(" one array y::::: "+oneArr[y]);
					//System.out.println(" one array yEps::::: "+oneArr[yEpsInt]);
					String ifOneString=oneArr[y]+oneArr[yEpsInt];					
					String ifOneString2=ifOneString.replaceAll("null", "");
					//System.out.println(" onestring2 y::::: "+ifOneString2);
					int ifOneInt= Integer.parseInt(ifOneString2);
					//System.out.println(" one int y::::: "+ifOneInt);
					join.ifOne=ifOneInt;
					q.add(ifOneInt);
				//TODO accept for join
					
					
				
				//add join to statesArr
					statesArr.add(join);
					

					
					
				}
				

			}
			else {
				//Dead state
				//temp.ifOne=19;
				temp.accept=false;
				System.out.println("DEADDD   temp.ifOne=19; ");
			}
			
		statesArr.add(temp)	;
			
		
		  }//while end
	
	
		for(int i=0;i<acceptStates.length();i++) {
			if (acceptStates.charAt(i)!=','){
				
				int x =acceptStates.charAt(i)-48;
				//System.out.println("accept state: "+x);

				for (int j = 0; j < statesArr.size(); j++) {

					if (statesArr.get(j).stateName==x) {
						
						statesArr.get(j).accept=true;

						}
					
					}
			}
		}
		
		State dead=new State();
		dead.stateName=19;
		dead.ifOne=19;
		dead.ifZero=19;
		dead.accept=false;
		statesArr.add(dead)	;		
		

		for (int j = 0; j < statesArr.size(); j++) {
			System.out.println("states Arr: "+statesArr.get(j).stateName+" IFZERO: "+statesArr.get(j).ifZero+" IFOne: "+statesArr.get(j).ifOne+" accept: "+statesArr.get(j).accept);
			}
		/*
		for (int j = 0; j < oneList.size(); j++) {
			for (int k = 0; k < oneList.get(j).size(); k++) {

			System.out.println("oneList: "+oneList.get(j).get(k));
			}}*/
		
	}

	
	
	public boolean run(String input) {
		boolean acceptRun=false;
		
		for(int i=0;i<input.length();i++) {
			
				System.out.println("input.charAt(i)"+input.charAt(i));
		
				State curr = statesArr.get(currStateName);
				if(currStateName==19) {
					 for (int j = 0; j < statesArr.size(); j++) {

					    	if(statesArr.get(j).stateName==currStateName){
								 curr = statesArr.get(j);

					    		}
					    	}
					
				}
					
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
	    for (int i = 0; i < statesArr.size(); i++) {

	    	if(statesArr.get(i).stateName==currStateName){
	    		State last = statesArr.get(i);
		System.out.println(" LAST.statename: "+last.stateName);
		System.out.println(" LAST.accept: "+last.accept);
	    		acceptRun=last.accept;
	    	}
	    	
	    }
	    

		

		

		
		System.out.println("acceptRun: "+acceptRun);

		return acceptRun;
	}
}
class State{
	boolean accept;
	int stateName;
	int ifZero;
	int ifOne;
	
	
	
}