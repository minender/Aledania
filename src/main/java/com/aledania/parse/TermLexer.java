package com.aledania.parse; 

// $ANTLR 3.2 Sep 23, 2009 12:02:23 Term.g 2014-06-18 02:21:40

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class TermLexer extends Lexer {
    public static final int WORD=6;
    public static final int T__16=16;
    public static final int T__15=15;
    public static final int T__17=17;
    public static final int LETTERUSCORE=13;
    public static final int L=4;
    public static final int LETTER=12;
    public static final int CAPITALLETTER=7;
    public static final int NUMBER=9;
    public static final int INITIALDIGIT=10;
    public static final int WHITESPACE=14;
    public static final int INT=8;
    public static final int DIGIT=11;
    public static final int EOF=-1;
    public static final int X=5;

    // delegates
    // delegators

    public TermLexer() {;} 
    public TermLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public TermLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "Term.g"; }

    // $ANTLR start "T__15"
    public final void mT__15() throws RecognitionException {
        try {
            int _type = T__15;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Term.g:3:7: ( '.' )
            // Term.g:3:9: '.'
            {
            match('.'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__15"

    // $ANTLR start "T__16"
    public final void mT__16() throws RecognitionException {
        try {
            int _type = T__16;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Term.g:4:7: ( '(' )
            // Term.g:4:9: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__16"

    // $ANTLR start "T__17"
    public final void mT__17() throws RecognitionException {
        try {
            int _type = T__17;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Term.g:5:7: ( ')' )
            // Term.g:5:9: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__17"

    // $ANTLR start "X"
    public final void mX() throws RecognitionException {
        try {
            int _type = X;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Term.g:59:2: ( 'X' NUMBER | 'x' NUMBER )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0=='X') ) {
                alt1=1;
            }
            else if ( (LA1_0=='x') ) {
                alt1=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // Term.g:59:4: 'X' NUMBER
                    {
                    match('X'); 
                    mNUMBER(); 

                    }
                    break;
                case 2 :
                    // Term.g:61:5: 'x' NUMBER
                    {
                    match('x'); 
                    mNUMBER(); 

                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "X"

    // $ANTLR start "INT"
    public final void mINT() throws RecognitionException {
        try {
            int _type = INT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Term.g:63:4: ( 'N' NUMBER | 'n' NUMBER )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0=='N') ) {
                alt2=1;
            }
            else if ( (LA2_0=='n') ) {
                alt2=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // Term.g:63:6: 'N' NUMBER
                    {
                    match('N'); 
                    mNUMBER(); 

                    }
                    break;
                case 2 :
                    // Term.g:65:7: 'n' NUMBER
                    {
                    match('n'); 
                    mNUMBER(); 

                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INT"

    // $ANTLR start "L"
    public final void mL() throws RecognitionException {
        try {
            int _type = L;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Term.g:67:2: ( 'lambda' )
            // Term.g:67:4: 'lambda'
            {
            match("lambda"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "L"

    // $ANTLR start "INITIALDIGIT"
    public final void mINITIALDIGIT() throws RecognitionException {
        try {
            int _type = INITIALDIGIT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Term.g:69:13: ( '1' .. '9' )
            // Term.g:69:15: '1' .. '9'
            {
            matchRange('1','9'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "INITIALDIGIT"

    // $ANTLR start "DIGIT"
    public final void mDIGIT() throws RecognitionException {
        try {
            int _type = DIGIT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Term.g:71:6: ( '0' | INITIALDIGIT )
            // Term.g:
            {
            if ( (input.LA(1)>='0' && input.LA(1)<='9') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DIGIT"

    // $ANTLR start "NUMBER"
    public final void mNUMBER() throws RecognitionException {
        try {
            int _type = NUMBER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Term.g:73:7: ( '0' | INITIALDIGIT ( DIGIT )* )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0=='0') ) {
                alt4=1;
            }
            else if ( ((LA4_0>='1' && LA4_0<='9')) ) {
                alt4=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // Term.g:73:9: '0'
                    {
                    match('0'); 

                    }
                    break;
                case 2 :
                    // Term.g:73:15: INITIALDIGIT ( DIGIT )*
                    {
                    mINITIALDIGIT(); 
                    // Term.g:73:28: ( DIGIT )*
                    loop3:
                    do {
                        int alt3=2;
                        int LA3_0 = input.LA(1);

                        if ( ((LA3_0>='0' && LA3_0<='9')) ) {
                            alt3=1;
                        }


                        switch (alt3) {
                    	case 1 :
                    	    // Term.g:73:29: DIGIT
                    	    {
                    	    mDIGIT(); 

                    	    }
                    	    break;

                    	default :
                    	    break loop3;
                        }
                    } while (true);


                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "NUMBER"

    // $ANTLR start "CAPITALLETTER"
    public final void mCAPITALLETTER() throws RecognitionException {
        try {
            int _type = CAPITALLETTER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Term.g:75:14: ( 'A' .. 'Z' )
            // Term.g:75:16: 'A' .. 'Z'
            {
            matchRange('A','Z'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "CAPITALLETTER"

    // $ANTLR start "LETTER"
    public final void mLETTER() throws RecognitionException {
        try {
            int _type = LETTER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Term.g:77:7: ( 'a' .. 'z' )
            // Term.g:77:9: 'a' .. 'z'
            {
            matchRange('a','z'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LETTER"

    // $ANTLR start "LETTERUSCORE"
    public final void mLETTERUSCORE() throws RecognitionException {
        try {
            int _type = LETTERUSCORE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Term.g:79:13: ( LETTER | '-' LETTER )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( ((LA5_0>='a' && LA5_0<='z')) ) {
                alt5=1;
            }
            else if ( (LA5_0=='-') ) {
                alt5=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // Term.g:79:15: LETTER
                    {
                    mLETTER(); 

                    }
                    break;
                case 2 :
                    // Term.g:81:4: '-' LETTER
                    {
                    match('-'); 
                    mLETTER(); 

                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LETTERUSCORE"

    // $ANTLR start "WORD"
    public final void mWORD() throws RecognitionException {
        try {
            int _type = WORD;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Term.g:83:5: ( CAPITALLETTER ( LETTERUSCORE )+ '_' | CAPITALLETTER ( LETTERUSCORE )+ | CAPITALLETTER '_' )
            int alt8=3;
            alt8 = dfa8.predict(input);
            switch (alt8) {
                case 1 :
                    // Term.g:83:11: CAPITALLETTER ( LETTERUSCORE )+ '_'
                    {
                    mCAPITALLETTER(); 
                    // Term.g:83:25: ( LETTERUSCORE )+
                    int cnt6=0;
                    loop6:
                    do {
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( (LA6_0=='-'||(LA6_0>='a' && LA6_0<='z')) ) {
                            alt6=1;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // Term.g:83:26: LETTERUSCORE
                    	    {
                    	    mLETTERUSCORE(); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt6 >= 1 ) break loop6;
                                EarlyExitException eee =
                                    new EarlyExitException(6, input);
                                throw eee;
                        }
                        cnt6++;
                    } while (true);

                    match('_'); 

                    }
                    break;
                case 2 :
                    // Term.g:85:11: CAPITALLETTER ( LETTERUSCORE )+
                    {
                    mCAPITALLETTER(); 
                    // Term.g:85:25: ( LETTERUSCORE )+
                    int cnt7=0;
                    loop7:
                    do {
                        int alt7=2;
                        int LA7_0 = input.LA(1);

                        if ( (LA7_0=='-'||(LA7_0>='a' && LA7_0<='z')) ) {
                            alt7=1;
                        }


                        switch (alt7) {
                    	case 1 :
                    	    // Term.g:85:26: LETTERUSCORE
                    	    {
                    	    mLETTERUSCORE(); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt7 >= 1 ) break loop7;
                                EarlyExitException eee =
                                    new EarlyExitException(7, input);
                                throw eee;
                        }
                        cnt7++;
                    } while (true);


                    }
                    break;
                case 3 :
                    // Term.g:87:3: CAPITALLETTER '_'
                    {
                    mCAPITALLETTER(); 
                    match('_'); 

                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WORD"

    // $ANTLR start "WHITESPACE"
    public final void mWHITESPACE() throws RecognitionException {
        try {
            int _type = WHITESPACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // Term.g:89:11: ( ( ' ' | '\\r' )+ )
            // Term.g:89:13: ( ' ' | '\\r' )+
            {
            // Term.g:89:13: ( ' ' | '\\r' )+
            int cnt9=0;
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0=='\r'||LA9_0==' ') ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // Term.g:
            	    {
            	    if ( input.LA(1)=='\r'||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt9 >= 1 ) break loop9;
                        EarlyExitException eee =
                            new EarlyExitException(9, input);
                        throw eee;
                }
                cnt9++;
            } while (true);

            _channel = HIDDEN;

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "WHITESPACE"

    public void mTokens() throws RecognitionException {
        // Term.g:1:8: ( T__15 | T__16 | T__17 | X | INT | L | INITIALDIGIT | DIGIT | NUMBER | CAPITALLETTER | LETTER | LETTERUSCORE | WORD | WHITESPACE )
        int alt10=14;
        alt10 = dfa10.predict(input);
        switch (alt10) {
            case 1 :
                // Term.g:1:10: T__15
                {
                mT__15(); 

                }
                break;
            case 2 :
                // Term.g:1:16: T__16
                {
                mT__16(); 

                }
                break;
            case 3 :
                // Term.g:1:22: T__17
                {
                mT__17(); 

                }
                break;
            case 4 :
                // Term.g:1:28: X
                {
                mX(); 

                }
                break;
            case 5 :
                // Term.g:1:30: INT
                {
                mINT(); 

                }
                break;
            case 6 :
                // Term.g:1:34: L
                {
                mL(); 

                }
                break;
            case 7 :
                // Term.g:1:36: INITIALDIGIT
                {
                mINITIALDIGIT(); 

                }
                break;
            case 8 :
                // Term.g:1:49: DIGIT
                {
                mDIGIT(); 

                }
                break;
            case 9 :
                // Term.g:1:55: NUMBER
                {
                mNUMBER(); 

                }
                break;
            case 10 :
                // Term.g:1:62: CAPITALLETTER
                {
                mCAPITALLETTER(); 

                }
                break;
            case 11 :
                // Term.g:1:76: LETTER
                {
                mLETTER(); 

                }
                break;
            case 12 :
                // Term.g:1:83: LETTERUSCORE
                {
                mLETTERUSCORE(); 

                }
                break;
            case 13 :
                // Term.g:1:96: WORD
                {
                mWORD(); 

                }
                break;
            case 14 :
                // Term.g:1:101: WHITESPACE
                {
                mWHITESPACE(); 

                }
                break;

        }

    }


    protected DFA8 dfa8 = new DFA8(this);
    protected DFA10 dfa10 = new DFA10(this);
    static final String DFA8_eotS =
        "\2\uffff\1\5\4\uffff\1\5";
    static final String DFA8_eofS =
        "\10\uffff";
    static final String DFA8_minS =
        "\1\101\2\55\1\141\3\uffff\1\55";
    static final String DFA8_maxS =
        "\1\132\3\172\3\uffff\1\172";
    static final String DFA8_acceptS =
        "\4\uffff\1\3\1\2\1\1\1\uffff";
    static final String DFA8_specialS =
        "\10\uffff}>";
    static final String[] DFA8_transitionS = {
            "\32\1",
            "\1\3\61\uffff\1\4\1\uffff\32\2",
            "\1\3\61\uffff\1\6\1\uffff\32\2",
            "\32\7",
            "",
            "",
            "",
            "\1\3\61\uffff\1\6\1\uffff\32\2"
    };

    static final short[] DFA8_eot = DFA.unpackEncodedString(DFA8_eotS);
    static final short[] DFA8_eof = DFA.unpackEncodedString(DFA8_eofS);
    static final char[] DFA8_min = DFA.unpackEncodedStringToUnsignedChars(DFA8_minS);
    static final char[] DFA8_max = DFA.unpackEncodedStringToUnsignedChars(DFA8_maxS);
    static final short[] DFA8_accept = DFA.unpackEncodedString(DFA8_acceptS);
    static final short[] DFA8_special = DFA.unpackEncodedString(DFA8_specialS);
    static final short[][] DFA8_transition;

    static {
        int numStates = DFA8_transitionS.length;
        DFA8_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA8_transition[i] = DFA.unpackEncodedString(DFA8_transitionS[i]);
        }
    }

    class DFA8 extends DFA {

        public DFA8(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 8;
            this.eot = DFA8_eot;
            this.eof = DFA8_eof;
            this.min = DFA8_min;
            this.max = DFA8_max;
            this.accept = DFA8_accept;
            this.special = DFA8_special;
            this.transition = DFA8_transition;
        }
        public String getDescription() {
            return "83:1: WORD : ( CAPITALLETTER ( LETTERUSCORE )+ '_' | CAPITALLETTER ( LETTERUSCORE )+ | CAPITALLETTER '_' );";
        }
    }
    static final String DFA10_eotS =
        "\4\uffff\1\17\1\22\1\17\2\22\1\25\1\uffff\1\17\14\uffff";
    static final String DFA10_eofS =
        "\30\uffff";
    static final String DFA10_minS =
        "\1\15\3\uffff\1\55\1\60\1\55\1\60\1\141\1\60\1\uffff\1\55\14\uffff";
    static final String DFA10_maxS =
        "\1\172\3\uffff\1\172\1\71\1\172\1\71\1\141\1\71\1\uffff\1\172\14"+
        "\uffff";
    static final String DFA10_acceptS =
        "\1\uffff\1\1\1\2\1\3\6\uffff\1\10\1\uffff\1\13\1\14\1\16\1\12\1"+
        "\15\1\4\1\13\1\5\1\6\1\7\1\11\1\10";
    static final String DFA10_specialS =
        "\30\uffff}>";
    static final String[] DFA10_transitionS = {
            "\1\16\22\uffff\1\16\7\uffff\1\2\1\3\3\uffff\1\15\1\1\1\uffff"+
            "\1\12\11\11\7\uffff\15\13\1\6\11\13\1\4\2\13\6\uffff\13\14\1"+
            "\10\1\14\1\7\11\14\1\5\2\14",
            "",
            "",
            "",
            "\1\20\2\uffff\12\21\45\uffff\1\20\1\uffff\32\20",
            "\12\21",
            "\1\20\2\uffff\12\23\45\uffff\1\20\1\uffff\32\20",
            "\12\23",
            "\1\24",
            "\12\26",
            "",
            "\1\20\61\uffff\1\20\1\uffff\32\20",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA10_eot = DFA.unpackEncodedString(DFA10_eotS);
    static final short[] DFA10_eof = DFA.unpackEncodedString(DFA10_eofS);
    static final char[] DFA10_min = DFA.unpackEncodedStringToUnsignedChars(DFA10_minS);
    static final char[] DFA10_max = DFA.unpackEncodedStringToUnsignedChars(DFA10_maxS);
    static final short[] DFA10_accept = DFA.unpackEncodedString(DFA10_acceptS);
    static final short[] DFA10_special = DFA.unpackEncodedString(DFA10_specialS);
    static final short[][] DFA10_transition;

    static {
        int numStates = DFA10_transitionS.length;
        DFA10_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA10_transition[i] = DFA.unpackEncodedString(DFA10_transitionS[i]);
        }
    }

    class DFA10 extends DFA {

        public DFA10(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 10;
            this.eot = DFA10_eot;
            this.eof = DFA10_eof;
            this.min = DFA10_min;
            this.max = DFA10_max;
            this.accept = DFA10_accept;
            this.special = DFA10_special;
            this.transition = DFA10_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__15 | T__16 | T__17 | X | INT | L | INITIALDIGIT | DIGIT | NUMBER | CAPITALLETTER | LETTER | LETTERUSCORE | WORD | WHITESPACE );";
        }
    }
 

}