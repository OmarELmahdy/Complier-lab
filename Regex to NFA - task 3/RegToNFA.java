package csen1002.main.task3;

import java.util.*;


/**
 * Write your info here
 * 
 * @name Omar 
 * @id 
 * @labNumber 
 */

public class RegToNFA {

	
	private static String output = null;
	
	public static void main(String[]args) {
		//RegToNFA regToNFA = new RegToNFA("1e00.1||.");
		//RegToNFA regToNFA = new RegToNFA("100.|e|");
		RegToNFA regToNFA = new RegToNFA("11*1.|1|");
		
	   
	}
	
	
	public RegToNFA(String regex) {
		int lastCreated=0;
		 
		Stack<String> stack = new Stack<String>();
		Stack<transition> transStack = new Stack<transition>();
		
		for(int i=regex.length()-1;i>=0;i--) {
			 stack.push(Character.toString(regex.charAt(i)));	 
		}
		
		System.out.println("Stack: " + stack);
		String zeroString = null;
		String oneString = null;
		String epsilonString = null;

        Iterator<String> itr = stack.iterator();
		while(itr.hasNext()) {

			System.out.println("-------------------" );
			 
			
		if(stack.peek().equals("0")) {
			System.out.println("Stack.peek == 0 ");
			  System.out.println("Popped element: " +stack.pop() );
			//creates two new states... or not
			  if(zeroString==null) {
				 String from=Integer.toString(lastCreated);
				 String to=Integer.toString(lastCreated+1);
				  zeroString=from+","+to;
				  lastCreated+=2;
					System.out.println("Zerostruing: " +zeroString );
					transition x =new transition();
					x.first=from;
					x.last=to;
					x.kind="zero";
					transStack.push(x);
					//System.out.println("trans Stack: " + transStack.peek().first);


			  }else {
				  String from=Integer.toString(lastCreated);
					 String to=Integer.toString(lastCreated+1);
					  zeroString+=";"+from+","+to;
					  lastCreated+=2;
						System.out.println("Zerostruing: " +zeroString );
						transition x =new transition();
						x.first=from;
						x.last=to;
						x.kind="zero";
						transStack.push(x);
						System.out.println("trans Stack: " + transStack);
						System.out.println("trans Stack: " + transStack.peek().first);


			  }
			  
			 
			
		}
		else if(stack.peek().equals("1")) {
			System.out.println("Stack.peek == 1 ");			
			  System.out.println("Popped element: " +stack.pop() );
			//creates two new states
			  if(oneString==null) {
					 String from=Integer.toString(lastCreated);
					 String to=Integer.toString(lastCreated+1);
					  oneString=from+","+to;
					  lastCreated+=2;
						System.out.println("ONE  struing: " +oneString );
						transition x =new transition();
						x.first=from;
						x.last=to;
						x.kind="one";
						transStack.push(x);
						System.out.println("trans Stack: " + transStack);
						System.out.println("trans Stack: " + transStack.peek().first);
						System.out.println("trans Stack: " + transStack.peek().kind);


				  }else {
					  String from=Integer.toString(lastCreated);
						 String to=Integer.toString(lastCreated+1);
						  oneString+=";"+from+","+to;
						  lastCreated+=2;
							System.out.println("ONE struing: " +oneString );
							transition x =new transition();
							x.first=from;
							x.last=to;
							x.kind="one";
							transStack.push(x);
							System.out.println("trans Stack: " + transStack);
							System.out.println("trans Stack: " + transStack.peek().first);
							System.out.println("trans Stack: " + transStack.peek().kind);


				  }
				  

		}
		else if(stack.peek().equals("e")) {
			System.out.println("Stack.peek == e ");
			  System.out.println("Popped element: " +stack.pop() );
			  System.out.println("epsilon  string b4: " +epsilonString );

			//creates two new states
			  String from=Integer.toString(lastCreated);
			  String to=Integer.toString(lastCreated+1);
			  lastCreated+=2;
			  transition x =new transition();
				x.first=from;
				x.last=to;
				x.kind="epsilon";
				transStack.push(x);
				System.out.println("trans Stack: " + transStack);
				System.out.println("trans Stack: " + transStack.peek().first);

			  if(epsilonString==null) {
					 epsilonString=from+","+to;
						
				  }else {
					epsilonString+=";"+from+","+to;
				  }
			  
				  System.out.println("epsilon strng aftr: " +epsilonString );
				  
				  
		}
		else if(stack.peek().equals("*")) {
			System.out.println("Stack.peek == * ");
			  System.out.println("Popped element: " +stack.pop() );
			// 2 new states 4 epsilon transitions
			  String first=Integer.toString(lastCreated);
			  String last=Integer.toString(lastCreated+1);
			  lastCreated+=2;
			  
			transition a = transStack.pop();
			
			String from1=first;
			String to1=a.first;
			String from2=first;
			String to2=last;
			String from3=a.last;
			String to3=a.first;
			String from4=a.last;
			String to4=last;
			if(epsilonString==null) {
				 epsilonString=from1+","+to1+";"+from2+","+to2+";"+from3+","+to3+";"+from4+","+to4;
					
			  }else {
				epsilonString+=";"+from1+","+to1+";"+from2+","+to2+";"+from3+","+to3+";"+from4+","+to4;
			  }
		  transition x =new transition();
				x.first=first;
				x.last=last;
				x.kind="Kleen(star)";
			transStack.push(x);	
			  System.out.println("epsilon  struing: " +epsilonString );
			
			
		}
		else if(stack.peek().equals("|")) {
			System.out.println("Stack.peek == | ");
			  System.out.println("Popped element: " +stack.pop() );
			  System.out.println("epsilon  struing: " +epsilonString );

			  // 2 new states 4 epsilon transitions
			  String first=Integer.toString(lastCreated);
			  String last=Integer.toString(lastCreated+1);
			  lastCreated+=2;
			  
			transition a = transStack.pop();
			transition b = transStack.pop();
			String from1=first;
			String to1=a.first;
			String from2=first;
			String to2=b.first;
			String from3=a.last;
			String to3=last;
			String from4=b.last;
			String to4=last;
			if(epsilonString==null) {
				 epsilonString=from1+","+to1+";"+from2+","+to2+";"+from3+","+to3+";"+from4+","+to4;
					
			  }else {
				epsilonString+=";"+from1+","+to1+";"+from2+","+to2+";"+from3+","+to3+";"+from4+","+to4;
			  }
		  transition x =new transition();
				x.first=first;
				x.last=last;
				x.kind="Union";
			transStack.push(x);	
			  System.out.println("epsilon  struing: " +epsilonString );
			
			

				
			
				
			  
			
			
		}
		
		else if(stack.peek().equals(".")) {
			System.out.println("Stack.peek == . ");
			  System.out.println("Popped element: " +stack.pop() );
			  
			  //Switched these last minute may just may cause a problem...
			  transition b = transStack.pop();
			  transition a = transStack.pop();
			  
			  String from = a.last;
			  String to=b.first;
			  if(epsilonString==null) {
					 epsilonString=from+","+to;

				  }else {
						 epsilonString+=";"+from+","+to;
				  }
			  System.out.println("epsilon  struing: " +epsilonString );
				transition x =new transition();
				x.first=a.first;
				x.last=b.last;
				x.kind="epsilon";
				transStack.push(x);
				System.out.println("trans Stack: " + transStack);
				System.out.println("trans Stack: " + transStack.peek().first);

			
			
		}
		
		for (int i = 0; i < transStack.size(); i++) {
			System.out.println("first: "+transStack.elementAt(i).first+" last: "+transStack.elementAt(i).last+" kind: "+transStack.elementAt(i).kind);
			
			
		}

		


		
		}//end of loop
	
		System.out.println("noOfStates: "+lastCreated+" first: "+transStack.peek().first+" last: "+transStack.peek().last+" ZeroSring: "+zeroString+" oneStriong: "+oneString+" epsilonString: "+epsilonString);
			System.out.println(lastCreated+"#"+transStack.peek().first+"#"+transStack.peek().last+"#"+zeroString+"#"+oneString+"#"+epsilonString);
				
				oneString=replaceNull(oneString) ;
				zeroString=replaceNull(zeroString) ;
				epsilonString=replaceNull(epsilonString) ;
				
			String[]epsilonArr=epsilonString.split(";");
			String[]oneArr=oneString.split(";");
			String[]zeroArr=zeroString.split(";");
			System.out.println("---------------------SORTINGVGGFGFGFGF------------------------------------------------------------------------");
			
			
	
		
			
	        HashMap<Integer, SortedSet<Integer> > epsilonHash = new HashMap<Integer, SortedSet<Integer>>();
	    	ArrayList<String> epsilonArr2 = new ArrayList<String>(); 

			for(int i=0;i<epsilonArr.length;i++) {
				String[]epsilonItem=epsilonArr[i].split(",");
				 System.out.println("epsilon item : " + epsilonItem[0]+" ," + epsilonItem[1]);
				int from=Integer.parseInt(epsilonItem[0]);
		        int to=Integer.parseInt(epsilonItem[1]);
		        if(!epsilonHash.containsKey(from)) {
		        SortedSet<Integer> ss = new TreeSet<Integer>();
		        ss.add(to);
		        epsilonHash.put(from,ss);
		        }else {
		        	epsilonHash.get(from).add(to);
		        }
			}
			//reconstruct as array
			//the +2 could cause an issue bs GREEN BAR , الحمد لله
			for(int i=0;i<epsilonArr.length+2;i++) {
				//epsilonHash.values();
			    if(epsilonHash.get(i)!=null) {
			    	System.out.println(i+" : "+epsilonHash.get(i));
						for (Integer value : epsilonHash.get(i)) {
				          //  System.out.println(value+ ", ");
						    String r=i+","+value;
						    epsilonArr2.add(r);
							}
			    	}
				}

			
		    //System.out.println("1eps arr "+Arrays.toString(epsilonArr));
		    //System.out.println("2eps arr "+epsilonArr2);
				
			
			/*
			HashMap<Integer, SortedSet<Integer> > zeroHash = new HashMap<Integer, SortedSet<Integer>>();
	    	ArrayList<String> zeroArr2 = new ArrayList<String>(); 

			for(int i=0;i<zeroArr.length;i++) {
				String[]zeroItem=zeroArr[i].split(",");
				// System.out.println("zero item : " + zeroItem[0]+" ," + zeroItem[1]);
				int from=Integer.parseInt(zeroItem[0]);
		        int to=Integer.parseInt(zeroItem[1]);
		        if(!zeroHash.containsKey(from)) {
		        SortedSet<Integer> ss = new TreeSet<Integer>();
		        ss.add(to);
		        zeroHash.put(from,ss);
		        }else {
		        	zeroHash.get(from).add(to);
		        }
			}
			//reconstruct as array
			for(int i=1;i<zeroArr.length;i++) {
				//zeroHash.values();
			    if(zeroHash.get(i)!=null) {
			    	//System.out.println(i+" : "+zeroHash.get(i));
						for (Integer value : zeroHash.get(i)) {
				          //  System.out.println(value+ ", ");
						    String r=i+","+value;
						    zeroArr2.add(r);
							}
			    	}
				}

			*/
			//////////////////////////////////////////////////////////////////////////////////////////////////////
	        		

	        		/*
			HashMap<Integer, SortedSet<Integer> > oneHash = new HashMap<Integer, SortedSet<Integer>>();
	    	ArrayList<String> oneArr2 = new ArrayList<String>(); 

			for(int i=0;i<oneArr.length;i++) {
				String[]oneItem=oneArr[i].split(",");
				// System.out.println("one item : " + oneItem[0]+" ," + oneItem[1]);
				int from=Integer.parseInt(oneItem[0]);
		        int to=Integer.parseInt(oneItem[1]);
		        if(!oneHash.containsKey(from)) {
		        SortedSet<Integer> ss = new TreeSet<Integer>();
		        ss.add(to);
		        oneHash.put(from,ss);
		        }else {
		        	oneHash.get(from).add(to);
		        }
			}
			//reconstruct as array
			for(int i=0;i<oneArr.length;i++) {
				//oneHash.values();
			    if(oneHash.get(i)!=null) {
			    	//System.out.println(i+" : "+oneHash.get(i));
						for (Integer value : oneHash.get(i)) {
				          //  System.out.println(value+ ", ");
						    String r=i+","+value;
						    oneArr2.add(r);
							}
			    	}
				}
	
	        		*/
	        		
	        		
	        Arrays.sort(zeroArr);
	        Arrays.sort(oneArr);
	        
	        
	        //System.out.println(Arrays.toString(epsilonArr));
	        
	        
	        
	        
	        
	        String zeroOut=null;
	        String epsilonOut=null;
	        String oneOut=null;
			//for(int i=0;i<epsilonArr.length;i++) {
				for(int i=0;i<epsilonArr2.size();i++) {
				if(epsilonOut==null) {
					epsilonOut=epsilonArr2.get(i);
					//epsilonOut=epsilonArr[i];
				  }else {
					  epsilonOut+=";"+epsilonArr2.get(i);
					  //epsilonOut+=";"+epsilonArr[i];
				  }
			}
			for(int i=0;i<zeroArr.length;i++) {
				if(zeroOut==null) {
					zeroOut=zeroArr[i];
					//zeroOut=zeroArr2.get(i);
				  }else {
					  zeroOut+=";"+zeroArr[i];
					  //zeroOut+=";"+zeroArr2.get(i);
				  }
			}
			for(int i=0;i<oneArr.length;i++) {
				if(oneOut==null) {
					oneOut=oneArr[i];
					//oneOut=oneArr2.get(i);

				  }else {
					  oneOut+=";"+oneArr[i];
					  //oneOut+=";"+oneArr2.get(i);
				  }
			}
			System.out.println(lastCreated+"#"+transStack.peek().first+"#"+transStack.peek().last+"#"+zeroOut+"#"+oneOut+"#"+epsilonOut);
			
			//System.out.println("14#0#13#4,5;6,7#0,1;8,9#1,12;2,3;3,13;5,6;7,11;9,11;10,4;10,8;11,13;12,2;12,10");
			//System.out.println("12#10#11#2,3;4,5#0,1#1,7;3,4;5,7;6,0;6,2;7,11;8,9;9,11;10,6;10,8");
			System.out.println("14#12#13##0,1;2,3;6,7;10,11#1,9;3,2;3,5;4,2;4,5;5,6;7,9;8,0;8,4;9,13;11,13;12,8;12,10");
			
			 output=lastCreated+"#"+transStack.peek().first+"#"+transStack.peek().last+"#"+zeroOut+"#"+oneOut+"#"+epsilonOut;

			output.toString();
	}

	
	@Override
	public String toString() {
		return output;
	}
	public String replaceNull(String input) {
		  return input == null ? "" : input;
		}
	public String sortString(String input) {
		String out="";
		
		return out;
		
	}
	
	class transition{
		String first;
		String last;
		String kind;
		
	}

}
