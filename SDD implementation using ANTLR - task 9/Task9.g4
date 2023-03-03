grammar Task9;
@parser::header{import java.lang.Math;import java.util.ArrayList;}

start: s  EOF ;

s returns [int check]:
                a c b { $check=1;
                       if($a.n!=$b.n){$check=0;};
                       if($a.n!=$c.n){$check=0;};
                       System.out.println("ANTLR??");
                      } ;


a returns [int n]:  'a' a1=a {$n=$a.n+1;}
                        | {$n=0;}
                        ;

b returns [int n]:  'b' b1=b{$n=$b.n+1;}
                        | {$n=0;}
                        ;

c returns [int n]:  'c' c1=c{$n=$c.n+1;}
                        | {$n=0;}
                        ;

// Write additional lexer and parser rules here
B :[01];
WS : [\r\n\t]+ -> skip;