package csen1002.main.task7;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import csen1002.main.task7.LL1CFG;

/**
 * Write your info here
 * 
 * @name Omar 
 * @id 
 * @labNumber 
 */
class Key<K1, K2> {
	public K1 key1;
	public K2 key2;	

	public Key(K1 key1, K2 key2) {
		this.key1 = key1;
		this.key2 = key2;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}

		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Key key = (Key) o;
		if (key1 != null ? !key1.equals(key.key1) : key.key1 != null) {
			return false;
		}

		if (key2 != null ? !key2.equals(key.key2) : key.key2 != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int result = key1 != null ? key1.hashCode() : 0;
		result = 31 * result + (key2 != null ? key2.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "[" + key1 + ", " + key2 + "]";
	}
}

public class LL1CFG {
	public static void main(String[] args) {
		LL1CFG ll1cfg1 = new LL1CFG("S,zToS,n,e;T,zTo,No;N,n,e#S,z,n,e;T,z,no;N,n,e#S,$;T,o;N,o");
//		ll1cfg1.parse("zzznoooon");
//		System.out.println("S,zToS,zzTooS,zzzToooS,zzzNooooS,zzznooooS,zzznoooon");

		ll1cfg1.parse("zzz");
		System.out.println("S,zToS,zzTooS,zzzToooS,ERROR");


//		LL1CFG ll1cfg2 = new LL1CFG("S,ipD,oSmDc,e;D,VmS,LxS;V,n,e;L,oSc,e#S,i,o,e;D,mn,ox;V,n,e;L,o,e#S,cm$;D,cm$;V,m;L,x");
//		ll1cfg2.parse("omocxc");
//		System.out.println("S,oSmDc,ooSmDcmDc,oomDcmDc,ERROR");

//		LL1CFG ll1cfg3 = new LL1CFG("S,iST,e;T,cS,a#S,i,e;T,c,a#S,ca$;T,ca$");
//		ll1cfg3.parse("iiac");
//		System.out.println("S,iST,iiSTT,iiTT,iiaT,iiacS,iiac");
	}

	Map<Key, String> multiKeyMap = new HashMap<>();
	ArrayList<String> variables = new ArrayList<String>();
	ArrayList<String> terminals = new ArrayList<String>();

	public LL1CFG(String description) {
		Map<String, ArrayList<String>> trans = new ConcurrentHashMap<String, ArrayList<String>>();
		Map<String, ArrayList<String>> first2match = new ConcurrentHashMap<String, ArrayList<String>>();
		Map<String, String> first = new ConcurrentHashMap<String, String>();
		Map<String, String> follow = new ConcurrentHashMap<String, String>();

		String[] hashSplit = description.split("#");

		String cfgDesc = hashSplit[0];
		String firstDesc = hashSplit[1];
		String followDesc = hashSplit[2];

		String[] firstSplit = firstDesc.split(";");
		String[] followSplit = followDesc.split(";");
		String[] cfgSplit = cfgDesc.split(";");

		for (int i = 0; i < cfgSplit.length; i++) {
			String transition = cfgSplit[i];

			String V = Character.toString(transition.charAt(0));
			transition = transition.substring(2);

			String[] commaSplit = transition.split(",");

			ArrayList<String> list = new ArrayList<String>();
			Collections.addAll(list, commaSplit);

			trans.put(V, list);
		}
		System.out.println(trans);

		for (int i = 0; i < firstSplit.length; i++) {
			String transition = firstSplit[i];

			String V = Character.toString(transition.charAt(0));
			transition = transition.substring(2);

			String[] commaSplit = transition.split(",");

			ArrayList<String> list = new ArrayList<String>();
			Collections.addAll(list, commaSplit);

			first2match.put(V, list);
		}
		System.out.println(first2match);

		for (HashMap.Entry<String, ArrayList<String>> entry : trans.entrySet()) {
			String var = entry.getKey();
			ArrayList<String> rhs = entry.getValue();
			for (int j = 0; j < rhs.size(); j++) {
//				System.out.println( rhs.get(j));
//				System.out.println( first2match.get(var).get(j) );
				first.put(rhs.get(j), first2match.get(var).get(j));
			}
		}
		System.out.println(first);

		for (int i = 0; i < followSplit.length; i++) {
			String transition = followSplit[i];

			String V = Character.toString(transition.charAt(0));
			transition = transition.substring(2);

			follow.put(V, transition);
		}
		System.out.println(follow);

		for (int i = 0; i < cfgDesc.length(); i++) {
			if (cfgDesc.charAt(i) >= 'a') {
//				System.out.println(cfgDesc.charAt(i));
				terminals.add(cfgDesc.charAt(i) + "");
			}
		}
		Set<String> set = new HashSet<>(terminals);
		terminals.clear();
		terminals.addAll(set);
		System.out.println(terminals);

		for (int i = 0; i < cfgDesc.length(); i++) {
			if (cfgDesc.charAt(i) <= 'Z' && cfgDesc.charAt(i) >= 'A') {
				System.out.println(cfgDesc.charAt(i));
				variables.add(cfgDesc.charAt(i) + "");
			}
		}
		Set<String> set2 = new HashSet<>(variables);
		variables.clear();
		variables.addAll(set2);
		System.out.println(variables);

		for (HashMap.Entry<String, ArrayList<String>> entry : trans.entrySet()) {

			String var = entry.getKey();
			ArrayList<String> rhs = entry.getValue();

			System.out.println(var + "-->" + rhs);

			for (int i = 0; i < rhs.size(); i++) {
				System.out.println(var + "-->" + rhs.get(i));
				String rule = rhs.get(i);
//					System.out.println(first.get(rule));

				for (int j = 0; j < terminals.size(); j++) {
					String nontermTerm = terminals.get(j);
					System.out.println(nontermTerm);
					if (first.get(rule).contains(nontermTerm)) {
						if (nontermTerm.equals("e")) {
							Key k = new Key(var, "$");
							multiKeyMap.put(k, rule);
						} else {
							Key k = new Key(var, nontermTerm);
							multiKeyMap.put(k, rule);
						}
					}
					if (first.get(rule).contains("e")) {
						if (follow.get(var).contains(nontermTerm)) {
							Key k = new Key(var, nontermTerm);
							multiKeyMap.put(k, rule);
						}

					}

				}

			}
			System.out.println(multiKeyMap);

		}

	}

	public String parse(String input) {
		System.out.println("              IN Parse   		 ");
		String OGinput = input;
		boolean errFlag = false;

		input += "$";
		int inPos = 0;
		ArrayList<String> outArr = new ArrayList<String>();

		String lefters = "";
		String output = "";
		String outputErr = "ERROR OUT";

		Stack<String> stack = new Stack<String>();
		stack.push("$");
		stack.push("S");

		int count = 0;
		while (!stack.isEmpty()) {
			System.out.println(" ");

			String A = stack.peek();
			String r = Character.toString(input.charAt(inPos));

			if (A.equals("e")) {
				System.out.println("stack peek = e ,POPPED");
				stack.pop();
				A = stack.peek();
			}
			System.out.println(count + " Stack: " + stack);
			count++;
			System.out.println("input.charAt(inPos): " + input.charAt(inPos));

			if (terminals.contains(A) || A.equals("$")) {
				if (A.equals(r)) {
					lefters += input.charAt(inPos);
					System.out.println("POPPED AND INPOS++");
					stack.pop();
					inPos++;
				} else {
					errFlag=true;
					System.out.println("EROORO");
					String total = lefters + stackPrint(stack);
					outArr.add(total);
					outArr.add("ERROR");
					break;
//					return "outputERR";
				}

			} else {
				if (variables.contains(A)) {

					Key k = new Key(A, r);
					String value = multiKeyMap.get(k);
					System.out.println("value " + value);
					if (value == null) {
						errFlag=true;
						System.out.println("EROORO");
						String total = lefters + stackPrint(stack);
						outArr.add(total);
						outArr.add("ERROR");
						break;
//						return "outputERR";

					} else {
						System.out.println("total:   			     " + lefters + stackPrint(stack));
						String total = lefters + stackPrint(stack);
						outArr.add(total);
						System.out.println("outArr:   			     " + outArr.toString());

						stack.pop();
						for (int j = value.length() - 1; j >= 0; j--) {
							stack.push(value.charAt(j) + "");
						}
						System.out.println("stack aftr: " + stackPrint(stack));
					}

				}
			}
		}
		System.out.println("PASSESSSSSSSSS");
		for (int i = 0; i <= outArr.size() - 1; i++) {
			output = output + "," + outArr.get(i);
		}
		output = output.substring(1);
		outputErr = output;
		output = output + "," + OGinput;
		System.out.println(output);

		if (errFlag) {
			System.out.println("ERROUTTT");
			System.out.println(outputErr);

			return outputErr;

		}else {
			return output;
		}
	}

	
	
	
	public String stackPrint(Stack<String> input) {
		String out = "";
		for (int i = input.size() - 1; i >= 1; i--) {
			out += input.get(i);

		}
		return out;
	}

}
