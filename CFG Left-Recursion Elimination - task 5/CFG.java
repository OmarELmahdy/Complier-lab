package csen1002.main.task5;

import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.*;

/**
 * Write your info here
 * 
 * @name John Smith
 * @id 43-0234
 * @labNumber 07
 */
public class CFG {
	/**
	 * CFG constructor
	 * 
	 * @param description is the string describing a CFG
	 */
	public static void main(String[]args) {
		CFG cfg = new CFG("S,SaT,T;T,TzG,G;G,i");
		//CFG cfg = new CFG("S,ScT,Sm,T,n;T,mSn,imLn,i;L,SdL,S");

		cfg.lre();
//		System.out.println("S,TS';S',aTS',e;T,GT';T',zGT',e;G,i");
	}

	
	public CFG(String description) {
        Map<String, ArrayList<String> > trans = new ConcurrentHashMap<String, ArrayList<String>>();
        Map<String, ArrayList<String> > transDash = new ConcurrentHashMap<String, ArrayList<String>>();
    	ArrayList<String> passed = new ArrayList<String>(); 

        
		
		String[]semiSplit=description.split(";");
		
		for(int i=0;i<semiSplit.length;i++) {
			String transition=semiSplit[i];
			
			
			String V=Character.toString(transition.charAt(0));
			transition=transition.substring(2);
			
			
			String[]commaSplit=transition.split(",");
		     
		     ArrayList<String> list = new ArrayList<String>();
		     Collections.addAll(list, commaSplit);
		      			
			trans.put(V, list);
		}
		
	      System.out.println(trans);
	    
	      

	      for ( HashMap.Entry <String, ArrayList<String> > entry : trans.entrySet()) {
	      		      System.out.println("..............");

	    	  System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
		      System.out.println(trans);

	    	  String var=entry.getKey();
	    	  ArrayList<String> rhs=entry.getValue();
	    	  
	    	  boolean ruleChanged=false;
	    	  
	    	  for (int i = 0; i < rhs.size(); i++) { 		      

	    		  if (rhs.get(i).indexOf(var)!=-1) {
			      
			      
	    			  if(rhs.get(i).indexOf(var)==0) {
			    	  
			    	  ruleChanged=true;
			    	  
			    	  String alpha= rhs.get(i);
				      alpha=alpha.substring(1);
				      String dash=var+"'";
				      alpha=alpha+dash;

				      ArrayList<String> arrPut= new ArrayList<String>();
				      arrPut.add(alpha);

				      transDash.put(dash, arrPut);
				      
				    
			      		}
 
	    	  		}else {
		      			if(ruleChanged==true) {
		      				//adding V' to end of rules not starting with V
		      				
					    	  String alpha= entry.getValue().get(i);
					    	  
					    	  entry.getValue().remove(i);
						      
						      String dash=var+"'";
					    	  alpha=alpha+dash;
					    	  
					    	  entry.getValue().add(i, alpha);
					    	  

		      				
		      			}
		      		}

	    	      String firstVar=rhs.get(i).charAt(0)+"";

	    		  if(passed.contains(firstVar)){
		    	      System.out.println("passed contains"+  firstVar);
		    	      
		    	      

	    		  }
	    		  
		    	  passed.add(var);

			     

	          	} 
	    	  
	      }
	      for ( HashMap.Entry <String, ArrayList<String> > entry : trans.entrySet()) {
	    	  
			  String var=entry.getKey();
			  ArrayList<String> rhs=entry.getValue();
			  
			  for (int i = 0; i < rhs.size(); i++) { 		      
		    			  if(rhs.get(i).indexOf(var)==0) {
				    	  				    	  
		    				  trans.get(var).remove(i);
				    	  		}
		    			  
			  }
			  
        	} 
	      
  	      for ( HashMap.Entry <String, ArrayList<String> > entry : transDash.entrySet()) {
			  ArrayList<String> rhs=entry.getValue();
			  rhs.add("e");
			  entry.setValue(rhs); 
  	      }
  	    
  	    Map<String, ArrayList<String>> map3 = Stream.of(trans, transDash)
  			  .flatMap(map -> map.entrySet().stream())
  			  .collect(Collectors.toMap(
  			    Map.Entry::getKey,
  			    Map.Entry::getValue));

	    System.out.println(""+map3);
	    
	    Map<String, ArrayList<String>> treeMap = new TreeMap<>(map3);
	    System.out.println(""+treeMap);





		
		
	}

	/**
	 * Returns a string of elimnated left recursion.
	 * 
	 * @param input is the string to simulate by the CFG.
	 * @return string of elimnated left recursion.
	 */
	public String lre() {
		// TODO Write Your Code Here
		return null;
	}
}
