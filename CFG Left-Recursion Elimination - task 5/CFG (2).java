package csen1002.main.task5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

public class CFG
{

	final static Character EPSILON = 'e';
	final static String EPSILON_RULE = "e";

	private static <T> boolean isSubset(LinkedHashSet<T> setA, LinkedHashSet<T> setB)
	{
		@SuppressWarnings({ "unchecked" })
		LinkedHashSet<T> temp = (LinkedHashSet<T>) setA.clone();
		temp.remove(EPSILON);
		return setB.containsAll(temp);
	}

	Map<Character, LinkedHashSet<String>> cfg;
	Map<Character, LinkedHashSet<Character>> first = new LinkedHashMap<>();
	Map<Character, LinkedHashSet<Character>> follow = new LinkedHashMap<>();
	LinkedHashSet<Character> sigma;

	public CFG(String input)
	{
		// input is in the form 'S,ScT,T;T,aSb,iaLb,e;L,SdL,S'
		this.cfg = new LinkedHashMap<>();
		this.sigma = new LinkedHashSet<Character>();
		for (Character c : input.toCharArray())
		{
			if (isTerminal(c))
				this.sigma.add(c);
		}
		String[] rules = input.split(";");
		for (String rule : rules)
		{
			cfg.put(rule.charAt(0),
					new LinkedHashSet<String>(Arrays.asList(rule.substring(2, rule.length()).split(","))));
		}
		this.first = new LinkedHashMap<>();
		this.follow = new LinkedHashMap<>();
		this.First();
		this.Follow();
	}

	public String First()
	{
		for (Character variable : this.cfg.keySet())
		{
			first.put(variable, new LinkedHashSet<Character>());
		}
		for (Character terminal : this.sigma)
		{
			first.put(terminal, new LinkedHashSet<Character>());
			first.get(terminal).add(terminal);
		}

		boolean change = true;
		while (change)
		{
			change = false;
			for (Character key : cfg.keySet())
			{
				for (String production : cfg.get(key))
				{
					if (hasAllEpsilon(production) && !first.get(key).contains(EPSILON))
					{
						first.get(key).add(EPSILON);
						change = true;
					} else
					{
						for (int i = 0; i < production.toCharArray().length; i++)
						{
							if (i == 0 || hasAllEpsilon(production.substring(0, i)))
							{
								char currentSymbol = production.charAt(i);
								@SuppressWarnings("unchecked")
								LinkedHashSet<Character> current = (LinkedHashSet<Character>) first.get(currentSymbol)
										.clone();
								current.remove(EPSILON);
								if (!first.get(key).containsAll(current))
								{
									first.get(key).addAll(current);
									change = true;
								}
							}
						}
					}
				}
			}
		}
		String output = "";
		for (char variable : this.cfg.keySet())
		{
			output += variable + ",";
			List<Character> sortedList = new ArrayList<>(first.get(variable));
			Collections.sort(sortedList);
			for (char c : sortedList)
			{
				output += c;
			}
			output += ";";
		}
		return output.substring(0, output.length() - 1);

	}

	public String Follow()
	{
		for (Character variable : this.cfg.keySet())
		{
			follow.put(variable, new LinkedHashSet<Character>());
		}
		for (Character terminal : this.sigma)
		{
			follow.put(terminal, new LinkedHashSet<Character>());
		}
		follow.get('S').add('$');
		boolean change = true;
		while (change)
		{
			change = false;
			for (Character key : cfg.keySet())
			{
				for (String production : cfg.get(key))
				{
					for (int i = 0, n = production.length(); i < n; i++)
					{

						if (i == n - 1)
						{

							if (production.charAt(i) != EPSILON)
							{
								if (!isSubset(follow.get(key), follow.get(production.charAt(i))))
								{
									@SuppressWarnings("unchecked")
									LinkedHashSet<Character> current = (LinkedHashSet<Character>) follow.get(key)
											.clone();
									current.remove(EPSILON);
									follow.get(production.charAt(i)).addAll(current);
									change = true;
								}
							}
						} else
						{
							if (!isSubset(first.get(production.charAt(i + 1)), follow.get(production.charAt(i))))
							{

								@SuppressWarnings("unchecked")
								LinkedHashSet<Character> current = (LinkedHashSet<Character>) first
										.get(production.charAt(i + 1)).clone();
								current.remove(EPSILON);
								follow.get(production.charAt(i)).addAll(current);
								change = true;

							}

							if (first.get(production.charAt(i + 1)).contains(EPSILON) && i + 1 != n - 1)
							{

								boolean flag = true;
								int count = 1;

								while (flag)
								{

									if (!isSubset(first.get(production.charAt(i + 1 + count)),
											follow.get(production.charAt(i))))
									{
										@SuppressWarnings("unchecked")
										LinkedHashSet<Character> current = (LinkedHashSet<Character>) first
												.get(production.charAt(i + 1 + count)).clone();
										current.remove(EPSILON);
										follow.get(production.charAt(i)).addAll(current);
										change = true;

									}

									if (i + 1 + count < n - 1
											&& first.get(production.charAt(i + 1 + count)).contains(EPSILON))
									{

										flag = true;
										count++;
									} else if (i + 1 + count == n - 1
											&& first.get(production.charAt(i + 1 + count)).contains(EPSILON))
									{

										if (!isSubset(follow.get(key), follow.get(production.charAt(i))))
										{
											@SuppressWarnings("unchecked")
											LinkedHashSet<Character> current = (LinkedHashSet<Character>) follow
													.get(key).clone();
											current.remove(EPSILON);
											follow.get(production.charAt(i)).addAll(current);
											change = true;

										}

										flag = false;
									} else
									{

										flag = false;
									}
								}
							} else if (first.get(production.charAt(i + 1)).contains(EPSILON) && i + 1 == n - 1)
							{

								if (!isSubset(follow.get(key), follow.get(production.charAt(i))))
								{
									@SuppressWarnings("unchecked")
									LinkedHashSet<Character> current = (LinkedHashSet<Character>) follow.get(key)
											.clone();
									current.remove(EPSILON);
									follow.get(production.charAt(i)).addAll(current);
									change = true;
								}
							}
						}
					}
				}
			}
		}

		String output = "";
		for (char variable : this.cfg.keySet())
		{
			output += variable + ",";
			List<Character> sortedList = new ArrayList<>(follow.get(variable));
			Collections.sort(sortedList);
			if (sortedList.contains('$'))
			{
				sortedList.remove(0);
				sortedList.add('$');
			}
			for (char c : sortedList)
			{
				output += c;
			}
			output += ";";
		}
		return output.substring(0, output.length() - 1);

	}

	private boolean hasAllEpsilon(String production)
	{
		for (Character symbol : production.toCharArray())
		{
			if (first.get(symbol) != null && !first.get(symbol).contains(EPSILON))
				return false;

		}
		return true;
	}

	private boolean isTerminal(char symbol)
	{
		return !isVariable(symbol) && symbol != ';' && symbol != ',';
	}

	private boolean isVariable(char symbol)
	{
		return Character.isUpperCase(symbol);
	}

	public static void main(String[] args)
	{
		String[] cfgs = { "S,tOlS,d;O,OQ,zSzQ,z,e;Q,Qz,s,e", "S,cOS,ftE,f;O,ccft,SES,e;E,tO,tw",
				"S,XSqX,SoXt,key;X,qtrX,mn,b", "S,SpHr,a;T,STH,pUr,yU;H,SrH,Uio,e;U,hi,a",
				"S,Szf,QzQQ,dok;Q,fScz,zie,zf" };
		for (int i = 0; i < cfgs.length; i++)
		{
			String grammar = cfgs[i];
			System.out.println(i + 1 + ") " + grammar);
			CFG cfg = new CFG(grammar);
			System.out.println("First: " + cfg.First());
			System.out.println("Follow: " + cfg.Follow());
			System.out.println("______________________________________");
		}
	}

}
