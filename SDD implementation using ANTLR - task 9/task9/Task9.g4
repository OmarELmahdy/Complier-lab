/**
 * Write your info here
 *
 * @name Omar Walid
 * @id 46-8026
 * @labNumber 14
 */
grammar Task9;


//@members {
//	/**
//	 * Compares two integer numbers
//	 *
//	 * @param x the first number to compare
//	 * @param y the second number to compare
//	 * @return 1 if x is equal to y, and 0 otherwise
//	 */
//	public static int equals(int x, int y) {
//	    return x == y ? 1 : 0;
//	}
//}

s returns [int check]: a c b  ;
a returns [int check]:  ;
b returns [int check]:  ;
c returns [int check]:  ;

// Write additional lexer and parser rules here
B :[01];
WS : [\r\n\t]+ -> skip;