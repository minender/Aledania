// $ANTLR 3.2 Sep 23, 2009 12:02:23 Term.g 2014-06-18 02:21:40
package com.aledania.parse; 

import com.aledania.lambdacalculo.App;
import com.aledania.lambdacalculo.Bracket;
import com.aledania.lambdacalculo.Term;
import com.aledania.lambdacalculo.Var;
import com.aledania.entity.Termino;
import com.aledania.entity.TerminoId;
import com.aledania.service.TerminoManager;
import java.util.Iterator;

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class TermParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "L", "X", "WORD", "CAPITALLETTER", "INT", "NUMBER", "INITIALDIGIT", "DIGIT", "LETTER", "LETTERUSCORE", "WHITESPACE", "'.'", "'('", "')'"
    };
    public static final int WORD=6;
    public static final int T__16=16;
    public static final int T__15=15;
    public static final int LETTERUSCORE=13;
    public static final int T__17=17;
    public static final int L=4;
    public static final int LETTER=12;
    public static final int NUMBER=9;
    public static final int CAPITALLETTER=7;
    public static final int INITIALDIGIT=10;
    public static final int WHITESPACE=14;
    public static final int INT=8;
    public static final int DIGIT=11;
    public static final int EOF=-1;
    public static final int X=5;
    public static String login;
    //public static int nCurrentAlias=1;
    
    // delegates
    // delegators


        public TermParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public TermParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return TermParser.tokenNames; }
    public String getGrammarFileName() { return "Term.g"; }



    // $ANTLR start "t"
    // Term.g:17:1: t[TerminoId terminoid, TerminoManager terminoManager] returns [Term value] : ( atom[terminoid, terminoManager] | abs[terminoid, terminoManager] | app[terminoid, terminoManager] );
    public final Term t(TerminoId terminoid, TerminoManager terminoManager) throws RecognitionException {
        Term value = null;
        login = terminoid.getLogin();
        
        Term atom1 = null;

        Term abs2 = null;

        Term app3 = null;


        try {
            // Term.g:17:75: ( atom[terminoid, terminoManager] | abs[terminoid, terminoManager] | app[terminoid, terminoManager] )
            int alt1=3;
            switch ( input.LA(1) ) {
            case X:
                {
                int LA1_1 = input.LA(2);

                if ( ((LA1_1>=X && LA1_1<=INT)||LA1_1==16) ) {
                    alt1=3;
                }
                else if ( (LA1_1==EOF) ) {
                    alt1=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 1, 1, input);

                    throw nvae;
                }
                }
                break;
            case WORD:
                {
                int LA1_2 = input.LA(2);

                if ( ((LA1_2>=X && LA1_2<=INT)||LA1_2==16) ) {
                    alt1=3;
                }
                else if ( (LA1_2==EOF) ) {
                    alt1=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 1, 2, input);

                    throw nvae;
                }
                }
                break;
            case CAPITALLETTER:
                {
                int LA1_3 = input.LA(2);

                if ( ((LA1_3>=X && LA1_3<=INT)||LA1_3==16) ) {
                    alt1=3;
                }
                else if ( (LA1_3==EOF) ) {
                    alt1=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 1, 3, input);

                    throw nvae;
                }
                }
                break;
            case INT:
                {
                int LA1_4 = input.LA(2);

                if ( (LA1_4==EOF) ) {
                    alt1=1;
                }
                else if ( ((LA1_4>=X && LA1_4<=INT)||LA1_4==16) ) {
                    alt1=3;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 1, 4, input);

                    throw nvae;
                }
                }
                break;
            case L:
                {
                alt1=2;
                }
                break;
            case 16:
                {
                alt1=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }

            switch (alt1) {
                case 1 :
                    // Term.g:17:77: atom[terminoid, terminoManager]
                    {
                    pushFollow(FOLLOW_atom_in_t28);
                    atom1=atom(terminoid, terminoManager);

                    state._fsp--;

                     value =atom1;

                    }
                    break;
                case 2 :
                    // Term.g:19:6: abs[terminoid, terminoManager]
                    {
                    pushFollow(FOLLOW_abs_in_t39);
                    abs2=abs(terminoid, terminoManager);

                    state._fsp--;

                     value =abs2; 

                    }
                    break;
                case 3 :
                    // Term.g:21:6: app[terminoid, terminoManager]
                    {
                    pushFollow(FOLLOW_app_in_t50);
                    app3=app(terminoid, terminoManager);

                    state._fsp--;

                     value =app3; 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            //reportError(re);
            recover(input,re);
            throw re;
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "t"


    // $ANTLR start "ter"
    // Term.g:23:1: ter[TerminoId terminoid, TerminoManager terminoManager] returns [Term value] : ( abs[terminoid, terminoManager] | app[terminoid, terminoManager] );
    public final Term ter(TerminoId terminoid, TerminoManager terminoManager) throws RecognitionException {
        Term value = null;

        Term abs4 = null;

        Term app5 = null;


        try {
            // Term.g:23:77: ( abs[terminoid, terminoManager] | app[terminoid, terminoManager] )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==L) ) {
                alt2=1;
            }
            else if ( ((LA2_0>=X && LA2_0<=INT)||LA2_0==16) ) {
                alt2=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // Term.g:25:4: abs[terminoid, terminoManager]
                    {
                    pushFollow(FOLLOW_abs_in_ter70);
                    abs4=abs(terminoid, terminoManager);

                    state._fsp--;

                     value =abs4; 

                    }
                    break;
                case 2 :
                    // Term.g:27:6: app[terminoid, terminoManager]
                    {
                    pushFollow(FOLLOW_app_in_ter81);
                    app5=app(terminoid, terminoManager);

                    state._fsp--;

                     value =app5; 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            //reportError(re);
            recover(input,re);
	    throw re;            
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "ter"


    // $ANTLR start "abs"
    // Term.g:29:1: abs[TerminoId terminoid, TerminoManager terminoManager] returns [Term value] : ( L xa= X '.' atom[terminoid, terminoManager] rest[terminoid, terminoManager] | L X '.' c= abs[terminoid, terminoManager] | L X '.' '(' ter[terminoid, terminoManager] ')' rest[terminoid, terminoManager] );
    public final Term abs(TerminoId terminoid, TerminoManager terminoManager) throws RecognitionException {
        Term value = null;

        Token xa=null;
        Token X8=null;
        Token X11=null;
        Term c = null;

        Term atom6 = null;

        ArrayList<Term> rest7 = null;

        Term ter9 = null;

        ArrayList<Term> rest10 = null;


        try {
            // Term.g:29:77: ( L xa= X '.' atom[terminoid, terminoManager] rest[terminoid, terminoManager] | L X '.' c= abs[terminoid, terminoManager] | L X '.' '(' ter[terminoid, terminoManager] ')' rest[terminoid, terminoManager] )
            int alt3=3;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==L) ) {
                int LA3_1 = input.LA(2);

                if ( (LA3_1==X) ) {
                    int LA3_2 = input.LA(3);

                    if ( (LA3_2==15) ) {
                        switch ( input.LA(4) ) {
                        case 16:
                            {
                            alt3=3;
                            }
                            break;
                        case X:
                        case WORD:
                        case CAPITALLETTER:
                        case INT:
                            {
                            alt3=1;
                            }
                            break;
                        case L:
                            {
                            alt3=2;
                            }
                            break;
                        default:
                            NoViableAltException nvae =
                                new NoViableAltException("", 3, 3, input);

                            throw nvae;
                        }

                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 3, 2, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 3, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // Term.g:31:5: L xa= X '.' atom[terminoid, terminoManager] rest[terminoid, terminoManager]
                    {
                    match(input,L,FOLLOW_L_in_abs102); 
                    xa=(Token)match(input,X,FOLLOW_X_in_abs106); 
                    match(input,15,FOLLOW_15_in_abs108); 
                    pushFollow(FOLLOW_atom_in_abs110);
                    atom6=atom(terminoid, terminoManager);

                    state._fsp--;

                    pushFollow(FOLLOW_rest_in_abs113);
                    rest7=rest(terminoid, terminoManager);

                    state._fsp--;

                    Term aux=atom6; for(Iterator<Term> i = rest7.iterator(); i.hasNext();) aux=new App(aux,i.next()); value =new Bracket(new Var((new Integer((xa!=null?xa.getText():null).substring(1))).intValue()), aux);

                    }
                    break;
                case 2 :
                    // Term.g:33:6: L X '.' c= abs[terminoid, terminoManager]
                    {
                    match(input,L,FOLLOW_L_in_abs124); 
                    X8=(Token)match(input,X,FOLLOW_X_in_abs126); 
                    match(input,15,FOLLOW_15_in_abs128); 
                    pushFollow(FOLLOW_abs_in_abs132);
                    c=abs(terminoid, terminoManager);

                    state._fsp--;

                    value =new Bracket(new Var((new Integer((X8!=null?X8.getText():null).substring(1))).intValue()),c);

                    }
                    break;
                case 3 :
                    // Term.g:35:6: L X '.' '(' ter[terminoid, terminoManager] ')' rest[terminoid, terminoManager]
                    {
                    match(input,L,FOLLOW_L_in_abs143); 
                    X11=(Token)match(input,X,FOLLOW_X_in_abs145); 
                    match(input,15,FOLLOW_15_in_abs147); 
                    match(input,16,FOLLOW_16_in_abs149); 
                    pushFollow(FOLLOW_ter_in_abs151);
                    ter9=ter(terminoid, terminoManager);

                    state._fsp--;

                    match(input,17,FOLLOW_17_in_abs154); 
                    pushFollow(FOLLOW_rest_in_abs156);
                    rest10=rest(terminoid, terminoManager);

                    state._fsp--;

                    Term aux=ter9; for(Iterator<Term> i = rest10.iterator(); i.hasNext();) aux=new App(aux,i.next()); value =new Bracket(new Var((new Integer((X11!=null?X11.getText():null).substring(1))).intValue()),aux);

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            //reportError(re);
            recover(input,re);
	    throw re;            
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "abs"


    // $ANTLR start "app"
    // Term.g:37:1: app[TerminoId terminoid, TerminoManager terminoManager] returns [Term value] : ( atom[terminoid, terminoManager] d[terminoid, terminoManager] | '(' ter[terminoid, terminoManager] ')' d[terminoid, terminoManager] );
    public final Term app(TerminoId terminoid, TerminoManager terminoManager) throws RecognitionException {
        Term value = null;

        Term atom12 = null;

        ArrayList<Term> d13 = null;

        Term ter14 = null;

        ArrayList<Term> d15 = null;


        try {
            // Term.g:37:77: ( atom[terminoid, terminoManager] d[terminoid, terminoManager] | '(' ter[terminoid, terminoManager] ')' d[terminoid, terminoManager] )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( ((LA4_0>=X && LA4_0<=INT)) ) {
                alt4=1;
            }
            else if ( (LA4_0==16) ) {
                alt4=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // Term.g:39:7: atom[terminoid, terminoManager] d[terminoid, terminoManager]
                    {
                    pushFollow(FOLLOW_atom_in_app179);
                    atom12=atom(terminoid, terminoManager);

                    state._fsp--;

                    pushFollow(FOLLOW_d_in_app182);
                    d13=d(terminoid, terminoManager);

                    state._fsp--;

                    Term aux=atom12; for(Iterator<Term> i = d13.iterator(); i.hasNext();) aux=new App(aux,i.next()); value =aux;

                    }
                    break;
                case 2 :
                    // Term.g:41:8: '(' ter[terminoid, terminoManager] ')' d[terminoid, terminoManager]
                    {
                    match(input,16,FOLLOW_16_in_app195); 
                    pushFollow(FOLLOW_ter_in_app197);
                    ter14=ter(terminoid, terminoManager);

                    state._fsp--;

                    match(input,17,FOLLOW_17_in_app200); 
                    pushFollow(FOLLOW_d_in_app202);
                    d15=d(terminoid, terminoManager);

                    state._fsp--;

                    Term aux=ter14; for(Iterator<Term> i = d15.iterator(); i.hasNext();) aux=new App(aux,i.next()); value =aux;

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            //reportError(re);
            recover(input,re);
	    throw re;            
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "app"


    // $ANTLR start "d"
    // Term.g:43:1: d[TerminoId terminoid, TerminoManager terminoManager] returns [ArrayList<Term> value] : ( atom[terminoid, terminoManager] rest[terminoid, terminoManager] | '(' ter[terminoid, terminoManager] ')' rest[terminoid, terminoManager] );
    public final ArrayList<Term> d(TerminoId terminoid, TerminoManager terminoManager) throws RecognitionException {
        ArrayList<Term> value = null;

        ArrayList<Term> rest16 = null;

        Term atom17 = null;

        ArrayList<Term> rest18 = null;

        Term ter19 = null;


        try {
            // Term.g:43:86: ( atom[terminoid, terminoManager] rest[terminoid, terminoManager] | '(' ter[terminoid, terminoManager] ')' rest[terminoid, terminoManager] )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( ((LA5_0>=X && LA5_0<=INT)) ) {
                alt5=1;
            }
            else if ( (LA5_0==16) ) {
                alt5=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // Term.g:43:88: atom[terminoid, terminoManager] rest[terminoid, terminoManager]
                    {
                    pushFollow(FOLLOW_atom_in_d217);
                    atom17=atom(terminoid, terminoManager);

                    state._fsp--;

                    pushFollow(FOLLOW_rest_in_d220);
                    rest16=rest(terminoid, terminoManager);

                    state._fsp--;

                    ArrayList<Term> aux=rest16; aux.add(0,atom17); value =aux;

                    }
                    break;
                case 2 :
                    // Term.g:45:5: '(' ter[terminoid, terminoManager] ')' rest[terminoid, terminoManager]
                    {
                    match(input,16,FOLLOW_16_in_d230); 
                    pushFollow(FOLLOW_ter_in_d232);
                    ter19=ter(terminoid, terminoManager);

                    state._fsp--;

                    match(input,17,FOLLOW_17_in_d235); 
                    pushFollow(FOLLOW_rest_in_d237);
                    rest18=rest(terminoid, terminoManager);

                    state._fsp--;

                    ArrayList<Term> aux=rest18; aux.add(0,ter19); value =aux;

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            //reportError(re);
            recover(input,re);
	    throw re;            
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "d"


    // $ANTLR start "rest"
    // Term.g:47:1: rest[TerminoId terminoid, TerminoManager terminoManager] returns [ArrayList<Term> value] : ( d[terminoid, terminoManager] | );
    public final ArrayList<Term> rest(TerminoId terminoid, TerminoManager terminoManager) throws RecognitionException {
        ArrayList<Term> value = null;

        ArrayList<Term> d20 = null;


        try {
            // Term.g:47:89: ( d[terminoid, terminoManager] | )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( ((LA6_0>=X && LA6_0<=INT)||LA6_0==16) ) {
                alt6=1;
            }
            else if ( (LA6_0==EOF||LA6_0==17) ) {
                alt6=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // Term.g:47:91: d[terminoid, terminoManager]
                    {
                    pushFollow(FOLLOW_d_in_rest252);
                    d20=d(terminoid, terminoManager);

                    state._fsp--;

                    value = d20;

                    }
                    break;
                case 2 :
                    // Term.g:49:5: 
                    {
                    value =new ArrayList<Term>();

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            //reportError(re);
            recover(input,re);
	    throw re;            
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "rest"


    // $ANTLR start "atom"
    // Term.g:51:1: atom[TerminoId terminoid, TerminoManager terminoManager] returns [Term value] : ( X | WORD | CAPITALLETTER | INT );
    public final Term atom(TerminoId terminoid, TerminoManager terminoManager) throws RecognitionException {
        Term value = null;

        Token X21=null;
        Token WORD22=null;
        Token CAPITALLETTER23=null;
        Token INT24=null;

        try {
            // Term.g:51:78: ( X | WORD | CAPITALLETTER | INT )
            int alt7=4;
            switch ( input.LA(1) ) {
            case X:
                {
                alt7=1;
                }
                break;
            case WORD:
                {
                alt7=2;
                }
                break;
            case CAPITALLETTER:
                {
                alt7=3;
                }
                break;
            case INT:
                {
                alt7=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }

            switch (alt7) {
                case 1 :
                    // Term.g:51:80: X
                    {
                    X21=(Token)match(input,X,FOLLOW_X_in_atom274); 
                     value =new Var((new Integer((X21!=null?X21.getText():null).substring(1))).intValue());

                    }
                    break;
                case 2 :
                    // Term.g:53:6: WORD
                    {
                    WORD22=(Token)match(input,WORD,FOLLOW_WORD_in_atom285); 
                    terminoid.setLogin(login); terminoid.setAlias((WORD22!=null?WORD22.getText():null)); Termino terminoEnBD=terminoManager.getTermino(terminoid);if(terminoEnBD==null) {throw new IsNotInDBException("", 1, 1, input,terminoid.getAlias());} value = terminoEnBD.getTermObject(); /*value.alias+="@"+nCurrentAlias;nCurrentAlias++;*/

                    }
                    break;
                case 3 :
                    // Term.g:55:6: CAPITALLETTER
                    {
                    CAPITALLETTER23=(Token)match(input,CAPITALLETTER,FOLLOW_CAPITALLETTER_in_atom295); 
                    terminoid.setLogin(login); terminoid.setAlias((CAPITALLETTER23!=null?CAPITALLETTER23.getText():null)); Termino terminoEnBD=terminoManager.getTermino(terminoid);if(terminoEnBD==null) {throw new IsNotInDBException("", 1, 1, input,terminoid.getAlias());} value = terminoEnBD.getTermObject(); /*value.alias+="@"+nCurrentAlias;nCurrentAlias++;*/

                    }
                    break;
                case 4 :
                    // Term.g:57:6: INT
                    {
                    INT24=(Token)match(input,INT,FOLLOW_INT_in_atom305); 
                    value = Term.natural((new Integer((INT24!=null?INT24.getText():null).substring(1))).intValue());

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            //reportError(re);
            recover(input,re);
	    throw re;            
        }
        finally {
        }
        return value;
    }
    // $ANTLR end "atom"

    // Delegated rules


 

    public static final BitSet FOLLOW_atom_in_t28 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_abs_in_t39 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_app_in_t50 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_abs_in_ter70 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_app_in_ter81 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_L_in_abs102 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_X_in_abs106 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_abs108 = new BitSet(new long[]{0x00000000000001E0L});
    public static final BitSet FOLLOW_atom_in_abs110 = new BitSet(new long[]{0x00000000000101E0L});
    public static final BitSet FOLLOW_rest_in_abs113 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_L_in_abs124 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_X_in_abs126 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_abs128 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_abs_in_abs132 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_L_in_abs143 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_X_in_abs145 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15_in_abs147 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_abs149 = new BitSet(new long[]{0x00000000000101F0L});
    public static final BitSet FOLLOW_ter_in_abs151 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_abs154 = new BitSet(new long[]{0x00000000000101E0L});
    public static final BitSet FOLLOW_rest_in_abs156 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_atom_in_app179 = new BitSet(new long[]{0x00000000000101E0L});
    public static final BitSet FOLLOW_d_in_app182 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_app195 = new BitSet(new long[]{0x00000000000101F0L});
    public static final BitSet FOLLOW_ter_in_app197 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_app200 = new BitSet(new long[]{0x00000000000101E0L});
    public static final BitSet FOLLOW_d_in_app202 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_atom_in_d217 = new BitSet(new long[]{0x00000000000101E0L});
    public static final BitSet FOLLOW_rest_in_d220 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_d230 = new BitSet(new long[]{0x00000000000101F0L});
    public static final BitSet FOLLOW_ter_in_d232 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_17_in_d235 = new BitSet(new long[]{0x00000000000101E0L});
    public static final BitSet FOLLOW_rest_in_d237 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_d_in_rest252 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_X_in_atom274 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_WORD_in_atom285 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CAPITALLETTER_in_atom295 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_in_atom305 = new BitSet(new long[]{0x0000000000000002L});

}