grammar task8;

//Parser Rule start to parse the input and check whether it is accepted or rejected
start: (Q2 | Q3 | Q4) + EOF ;

//Lexer Rule Q2 which has the Regular Expression of the accepted state Q2
Q2:  ;

//Lexer Rule Q3 which has the Regular Expression of the accepted state Q3
Q3:  ;

//Lexer Rule Q4 which has the Regular Expression of the accepted state Q4
Q4:  ;

//Fragments representing the zeros and ones
fragment ZERO: '0' ;
fragment ONE: '1' ;