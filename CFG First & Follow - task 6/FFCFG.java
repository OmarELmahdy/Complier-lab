package csen1002.main.task6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Write your info here
 * 
 * @name Omar ELmahdy
 * @id 
 * @labNumber 
 */

public class FFCFG {

	public static void main(String[] args) {

		FFCFG cfg = new FFCFG("S,aBDh;B,cA;A,bA,e;D,EF;E,g,e;F,f,e");
//		FFCFG cfg = new FFCFG("S,aTbS,e;T,aTb,e");
		for (int i = 0; i < 10; i++) {

		}
		System.out.println(cfg.first());

		System.out.println("S,a;B,c;A,be;D,efg;E,eg;F,ef");
	}

	Map<String, ArrayList<String>> trans = new ConcurrentHashMap<String, ArrayList<String>>();
	Map<String, String> first = new ConcurrentHashMap<String, String>();
	Map<String, String> follow = new ConcurrentHashMap<String, String>();

	public FFCFG(String description) {
		String[] semiSplit = description.split(";");

		for (int i = 0; i < semiSplit.length; i++) {
			String transition = semiSplit[i];

			String V = Character.toString(transition.charAt(0));
			transition = transition.substring(2);

			String[] commaSplit = transition.split(",");

			ArrayList<String> list = new ArrayList<String>();
			Collections.addAll(list, commaSplit);

			trans.put(V, list);
		}

		System.out.println(trans);

		for (int i = 0; i < description.length(); i++) {
			if (description.charAt(i) >= 'a'
//					&& description.charAt(i) != 'e'
			) {
				String V = Character.toString(description.charAt(i));
//			     System.out.println(description.charAt(i));

				first.put(V, V);

			}
		}
		System.out.println(first);

	}

	public String first() {

		for (HashMap.Entry<String, ArrayList<String>> entry : trans.entrySet()) {

			String var = entry.getKey();
			first.put(var, "");
		}

		System.out.println(first);

		boolean change = true;

		while (change) {
			System.out.println("1 WHILE iterationnnnn");

			change = false;

			for (HashMap.Entry<String, ArrayList<String>> entry : trans.entrySet()) { // line 8

				String var = entry.getKey();
				ArrayList<String> rhs = entry.getValue();

				String firstString;
				firstString = first.get(var);

				System.out.println(var + "-->" + rhs);

				boolean allEps = true;

				for (int j = 0; j < rhs.size(); j++) { // line 9
					for (int k = 0; k < rhs.get(j).length(); k++) {
						String rhsLetter = rhs.get(j).charAt(k) + "";
						if (first.get(rhsLetter) != null)
							if (!first.get(rhsLetter).contains("e")) {
								allEps = false;
								break;

							}
					}
				}
				if (allEps && !firstString.contains("e")) {
					firstString += "e";
					first.put(var, firstString);
					System.out.println("		change = true ");

					change = true;
				}

//				//MY ALGO
//				for (int w = 0; w < rhs.size(); w++) {
//					String firstLetter = rhs.get(w).charAt(0) + "";
//					if (rhs.get(w).charAt(0) >= 'a') {
//						firstString += rhs.get(w).charAt(0);
//						first.put(var, firstString);
////						change = true;
//					}else {
//						System.out.println(firstLetter);
//						System.out.println(first.get(firstLetter));
//
//					}
//				}

				for (int j = 0; j < rhs.size(); j++) { // line 13

					for (int k = 0; k < rhs.get(j).length(); k++) {

						System.out.println("rhs.get(j) " + rhs.get(j));
						System.out.println("rhs.get(j).charAt(k) " + rhs.get(j).charAt(k));

						String rhsLetter = rhs.get(j).charAt(k) + "";
						System.out.println("first.get(rhsLetter) " + first.get(rhsLetter));
//						if (first.get(rhsLetter) != null)

						if (first.get(rhsLetter).contains("e")) {

							// if (first.get(j).replace('e', (Character) null).isSubstring(first.get(var)))
							// { }
							if ((k + 1) < rhs.get(j).length()) {
								String bi = rhs.get(j).charAt(k + 1) + "";
								String noEps = first.get(bi).replace("e", "");
								for (int m = 0; m < noEps.length(); m++) {
									if (!firstString.contains(noEps.charAt(m) + "")) {
										firstString += noEps.charAt(m);
										first.put(var, firstString);
										System.out.println("		change = true ");

										change = true;
									}
								}

							}
						} else {
							System.out.println("		add to FIRESTSTS: " + first.get(rhsLetter));

							firstString += first.get(rhsLetter);
//								first.put(var, firstString);

						}
					}

				}

				System.out.println("firstString: " + firstString);
				first.put(var, firstString);

			}
			System.out.println("FIRST: " + first);
		}
		System.out.println("LAST FIRST: " + first);

		return null;
	}

	/**
	 * Calculates the Follow of each variable in the CFG.
	 * 
	 * @return A string representation of the Follow of each variable in the CFG,
	 *         formatted as specified in the task description.
	 */
	public String follow() {
		// TODO Auto-generated method stub
		return null;
	}

}
