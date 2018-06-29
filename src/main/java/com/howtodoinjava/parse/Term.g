


grammar Term;

@header {package com.howtodoinjava.parse; 

import com.howtodoinjava.entity.Termino;
import com.howtodoinjava.entity.TerminoId;
import com.howtodoinjava.lambdacalculo.*;
import com.howtodoinjava.service.TerminoManager;
import java.util.Iterator;}
 
/*@header {import java.util.Iterator;}*/

// Parser Rules
t[TerminoId terminoid, TerminoManager terminoManager] returns [Term value]: atom[terminoid, terminoManager] { $value=$atom.value;}

   | abs[terminoid, terminoManager] { $value=$abs.value; }

   | app[terminoid, terminoManager] { $value=$app.value; };

ter[TerminoId terminoid, TerminoManager terminoManager] returns [Term value]: 

   abs[terminoid, terminoManager] { $value=$abs.value; }

   | app[terminoid, terminoManager] { $value=$app.value; };

abs[TerminoId terminoid, TerminoManager terminoManager] returns [Term value]: 

    L xa=X '.' atom[terminoid, terminoManager] rest[terminoid, terminoManager] {Term aux=$atom.value; for(Iterator<Term> i = $rest.value.iterator(); i.hasNext();) aux=new App(aux,i.next()); $value=new Bracket(new Var((new Integer($xa.text.substring(1))).intValue()), aux);}

   | L X '.' c=abs[terminoid, terminoManager] {$value=new Bracket(new Var((new Integer($X.text.substring(1))).intValue()),$c.value);}

   | L X '.' '(' ter[terminoid, terminoManager] ')' rest[terminoid, terminoManager] {Term aux=$ter.value; for(Iterator<Term> i = $rest.value.iterator(); i.hasNext();) aux=new App(aux,i.next()); $value=new Bracket(new Var((new Integer($X.text.substring(1))).intValue()),aux);};

app[TerminoId terminoid, TerminoManager terminoManager] returns [Term value]: 

      atom[terminoid, terminoManager] d[terminoid, terminoManager] {Term aux=$atom.value; for(Iterator<Term> i = $d.value.iterator(); i.hasNext();) aux=new App(aux,i.next()); $value=aux;}

     | '(' ter[terminoid, terminoManager] ')' d[terminoid, terminoManager] {Term aux=$ter.value; for(Iterator<Term> i = $d.value.iterator(); i.hasNext();) aux=new App(aux,i.next()); $value=aux;};

d[TerminoId terminoid, TerminoManager terminoManager] returns [ArrayList<Term> value]: atom[terminoid, terminoManager] rest[terminoid, terminoManager] {ArrayList<Term> aux=$rest.value; aux.add(0,$atom.value); $value=aux;}

  | '(' ter[terminoid, terminoManager] ')' rest[terminoid, terminoManager] {ArrayList<Term> aux=$rest.value; aux.add(0,$ter.value); $value=aux;};

rest[TerminoId terminoid, TerminoManager terminoManager] returns [ArrayList<Term> value]: d[terminoid, terminoManager] {$value = $d.value;}

  | {$value=new ArrayList<Term>();};

atom[TerminoId terminoid, TerminoManager terminoManager] returns [Term value]: X { $value =new Var((new Integer($X.text.substring(1))).intValue());} 

   | WORD {terminoid.setLogin(login); terminoid.setAlias($WORD.text+"@"+nCurrentAlias); Termino terminoEnBD=terminoManager.getTermino(terminoid);if(terminoEnBD==null) {throw new IsNotInDBException("", 1, 1, input,terminoid.getAlias());} $value = terminoEnBD.getTermObject();}

   | CAPITALLETTER {terminoid.setLogin(login); terminoid.setAlias($CAPITALLETTER.text+"@"+nCurrentAlias); Termino terminoEnBD=terminoManager.getTermino(terminoid);if(terminoEnBD==null) {throw new IsNotInDBException("", 1, 1, input,terminoid.getAlias());} $value = terminoEnBD.getTermObject();}

   | INT {$value = Term.natural((new Integer($INT.text.substring(1))).intValue());};

X: 'X' NUMBER

  | 'x' NUMBER;

INT: 'N' NUMBER

    | 'n' NUMBER;

L: 'lambda';

INITIALDIGIT: '1'..'9';

DIGIT: '0'|INITIALDIGIT;

NUMBER: '0' | INITIALDIGIT (DIGIT)*;

CAPITALLETTER: 'A'..'Z';

LETTER: 'a'..'z';

LETTERUSCORE: LETTER

	| '-' LETTER; 

WORD:     CAPITALLETTER (LETTERUSCORE)+ '_'

        | CAPITALLETTER (LETTERUSCORE)+

	|CAPITALLETTER '_';

WHITESPACE: (' ' | '\r')+ {$channel = HIDDEN;};
