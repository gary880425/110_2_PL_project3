package PL110_10627153;

import java.util.Locale;
import java.util.Scanner;
import java.util.Vector;

class Global {

  private Global() throws Throwable {

  } // Global

  // Blow is Token Type
  static final int s_T_LEFT_PAREN = 1;
  static final int s_T_RIGHT_PAREN = 2;
  static final int s_T_BOOLEANOPERATOE = 3; // &&, ||, !, ==, !=
  static final int s_T_ID = 4;
  static final int s_T_OPERATOR = 5; // +, -, *, /, %
  static final int s_T_SEMICOLON = 6; // ;
  static final int s_T_NUMVALUE = 7; // 10, 1, 2, 3, 10.1, 0.001
  static final int s_T_CHARVALUE = 8; // 'A', 'b', 'c'
  static final int s_T_STRINGVALUE = 9; // "abcd123"
  static final int s_T_BOOLEANVALUE = 10; // true, flase
  static final int s_T_ASSIGN = 11; // =, +=, -=, *=, /=, %=
  static final int s_T_VARTYPR = 12; // int, String, float, char, bool
  // undefined token: ?:

  // Blow is Variable Type
  static final int s_V_INT = 1;
  static final int s_V_STRING = 2;
  static final int s_V_FLOAT = 3;
  static final int s_V_CHAR = 4;
  static final int s_V_BOOL = 5;

  static public Scanner sc = new Scanner( System.in );

  static public Vector<Variable> s_Variables = new Vector<Variable>();

  static public Vector<Function> s_functions = new Vector<Function>();

  static public boolean G_IsWhitSpace( char charStr ) throws Throwable {

    if ( charStr == ' ' || charStr == '\t' )
      return true;

    if ( charStr == '\r' || charStr == '\n' )
      return true;

    if ( charStr == '\u000B' || charStr == '\f' )
      return true;

    if ( charStr == '\u001C' || charStr == '\u001D' )
      return true;

    if ( charStr == '\u001E' || charStr == '\u001F' )
      return true;

    return false;

  } // G_IsWhitSpace()

  public static String G_PrintRegularFloat( double num ) throws Throwable {

    if ( num > 0 )
      num = num + 0.0005;
    else if ( num < 0 )
      num = num - 0.0005;

    String srum = String.format( "%.4f", num );
    // String srum = Float.toString( rnum );


    if ( srum.length() - srum.indexOf( "." ) == 3 ) {
      srum = srum + "0";
    } // if
    else if ( srum.length() - srum.indexOf( "." ) == 2 ) {
      srum = srum + "00";
    } // else if
    else if ( srum.length() - srum.indexOf( "." ) == 1 ) {
      srum = srum + "000";
    } // else if
    else if ( srum.length() - srum.indexOf( "." ) == 5 ) {
      srum = srum.substring( 0, srum.length() - 1 );
    } // else if

    boolean isAll0 = true;
    for ( int i = 0 ; i < srum.length() ; i++ ) {
      if ( srum.charAt( i ) >= '1' && srum.charAt( i ) <= '9' )
        isAll0 = false;
    } // for

    if ( isAll0 )
      ; // return "0";

    return srum;

  } // G_PrintRegularFloat()

  public static String G_CompareRegularFloat( String srum ) throws Throwable {

    if ( srum.length() - srum.indexOf( "." ) == 3 ) {
      srum = srum + "0";
    } // if
    else if ( srum.length() - srum.indexOf( "." ) == 2 ) {
      srum = srum + "00";
    } // else if
    else if ( srum.length() - srum.indexOf( "." ) == 1 ) {
      srum = srum + "000";
    } // else if
    else if ( srum.length() - srum.indexOf( "." ) == 5 ) {
      srum = srum.substring( 0, srum.indexOf( "." ) + 4 );
    } // else if

    int indexp = srum.indexOf( "." );

    if ( srum.contains( "+" ) )
      srum.replace( "+", "" );

    try {

      if ( srum.charAt( indexp ) == '.' && srum.charAt( indexp + 1 ) == '0' &&
           srum.charAt( indexp + 2 ) == '0' && srum.charAt( indexp + 3 ) == '0' )
        srum = srum.substring( 0, indexp );

    } // try
    catch ( Throwable throwable2 ) {

    } // catch

    boolean isAll0 = true;
    for ( int i = 0 ; i < srum.length() ; i++ ) {
      if ( srum.charAt( i ) >= '1' && srum.charAt( i ) <= '9' )
        isAll0 = false;
    } // for

    if ( isAll0 )
      ; // return "0";

    return srum;

  } // G_CompareRegularFloat()

  public static int G_FindVarible( Vector<Variable> vList, String vName ) throws Throwable {

    for ( int i = 0 ; i < vList.size() ; i++ ) {
      if ( vList.get( i ).GetName().equals( vName ) )
        return i;

    } // for

    return - 1;
  } // G_FindVarible()

  public static int G_FindFunction( Vector<Function> fList, String fName ) throws Throwable {

    for ( int i = 0 ; i < fList.size() ; i++ ) {
      if ( fList.get( i ).GetName().equals( fName ) )
        return i;

    } // for

    return - 1;
  } // G_FindVarible()

} // class Global

class TOKEN {
  private String m_token;
  private int m_type;

  public TOKEN( String input, int defineType ) throws Throwable {
    m_token = new String( input );
    m_type = defineType;
  } // ATOM()

  public String GetToken() throws Throwable {
    return m_token;
  } // GetToken()

  public int GetType() throws Throwable {
    return m_type;
  } // GetType()

  public void SetToken( String token ) throws Throwable {
    m_token = new String( token );
  } // SetToken()

} // class ATOM

class Stament {
  private Vector<TokenList> m_Line;
  private int m_Sive;


  public TOKEN GetToken( int index ) throws Throwable {
    for ( int i = 0 ; i < m_Line.size() ; i++ ) {
      for ( int j = 0 ; j < m_Line.get( i ).GetSize() ; j++ ) {
        if ( index == 0 )
          return m_Line.get( i ).GetToken( j );
        index--;
      } // for count the amount of token
    } // for count the amount of rows

    throw new Throwable();
  } // GetToken()

  public TokenList GetTokenRow( int index ) throws Throwable {
    return null;
  } // GetTokenRow()

  public void PushLine( Vector<TOKEN> Line ) throws Throwable {

  } // PushLine()

  public int GetSize() throws Throwable {
    return m_Sive;
  } // GetSize()

} // class Stamen

class TokenList {
  private Vector<TOKEN> m_token;

  public TOKEN GetToken( int index ) throws Throwable {
    return null;
  } // GetToken()

  public int GetSize() throws Throwable {
    return m_token.size();
  } // GetSize()

} // class TokenList

abstract class Variable {

  private String m_Name;
  private int m_Type;
  protected boolean m_IsNull;

  public Variable( int type, String name ) throws Throwable {
    this.m_Name = new String( name );
    this.m_Type = type;

  } // Variable()

  public Variable( int type, String name, boolean isNull ) throws Throwable {
    this.m_Name = new String( name );
    this.m_Type = type;
    this.m_IsNull = isNull;

  } // Variable()

  public String GetName() throws Throwable {

    return this.m_Name;

  } // GetName()

  public int GetType() throws Throwable {

    return m_Type;

  } // GetType()

  public abstract String GetValue() throws Throwable; // VarType()

  /*
  public int GetINTValue() throws Throwable {
    throw new Throwable();
  } // GetINTValue()

  public float GetFLOATEValue() throws Throwable {
    throw new Throwable();
  } // GetFLOATEValue()

  public String GetSTRINGValue() throws Throwable {
    throw new Throwable();
  } // GetSTRINGValue()

  public char GetCHARValue() throws Throwable {
    throw new Throwable();
  } // GetCHARValue()

  public boolean GetBOOLValue() throws Throwable {
    throw new Throwable();
  } // GetBOOLValue()
  */

} // class Variable

class VarINT extends Variable {

  private int m_value;

  public VarINT( int type, String name, String valueStr ) throws Throwable {
    super( type, name );
    if ( valueStr.contains( "+" ) )
      this.m_value = Integer.parseInt( valueStr.substring( 1 ) );
    else
      this.m_value = Integer.parseInt( valueStr );

  } // VarINT()

  public VarINT( int type, String name, boolean isNull ) throws Throwable {
    super( type, name, isNull );
  } // VarINT()

  public String GetValue() throws Throwable {
    if ( super.m_IsNull )
      throw new Throwable();
    else
      return Integer.toString( m_value );
  } // GetValue()

  public int GetINTValue() throws Throwable {
    if ( super.m_IsNull )
      throw new Throwable();
    else
      return this.m_value;
  } // GetINTValue()

} // class VarINT

class VarFLOT extends Variable {

  private float m_value;

  public VarFLOT( int type, String name, String valueStr ) throws Throwable {
    super( type, name );
    try {
      if ( valueStr.charAt( 0 ) == '.' )
        valueStr = "0" + valueStr;

      if ( valueStr.contains( "+" ) )
        this.m_value = Integer.parseInt( valueStr.substring( 1 ) );
      else
        this.m_value = Integer.parseInt( valueStr );

    } // try
    catch ( Throwable throwable ) {

    } // catch

  } // VarFLOT()

  public VarFLOT( int type, String name, boolean isNull ) throws Throwable {
    super( type, name, isNull );
  } // VarFLOT()

  public String GetValue() throws Throwable {
    if ( super.m_IsNull )
      throw new Throwable();
    else
      return Float.toString( m_value );
  } // GetValue()

  public float GetFLOATEValue() throws Throwable {
    if ( super.m_IsNull )
      throw new Throwable();
    else
      return this.m_value;
  } // GetFLOATEValue()

} // class VarFLOT

class VarSTRING extends Variable {

  private String m_value;

  public VarSTRING( int type, String name, String valueStr ) throws Throwable {
    super( type, name );

    for ( int i = 0 ; i < valueStr.length() ; i++ ) {
      this.m_value = valueStr.replaceAll( "\\\\n", "\n" );
    } // for

  } // VarSTRING()

  public VarSTRING( int type, String name, boolean isNull ) throws Throwable {
    super( type, name, isNull );
  } // VarSTRING()

  public String GetValue() throws Throwable {
    if ( super.m_IsNull )
      throw new Throwable();
    else
      return m_value;
  } // GetValue()

  public String GetSTRINGValue() throws Throwable {
    if ( super.m_IsNull )
      throw new Throwable();
    else
      return this.m_value;
  } // GetSTRINGValue()

} // class VarSTRING

class VarCHAR extends Variable {
  private String m_value;
  private int m_arraySize;

  public VarCHAR( int type, String name, String valueChar, int arraySize ) throws Throwable {
    super( type, name );
    this.m_arraySize = arraySize;
    if ( valueChar.length() > arraySize )
      throw new Throwable();
    else
      m_value = valueChar;

  } // VarCHAR()

  public VarCHAR( int type, String name, boolean isNull ) throws Throwable {
    super( type, name, isNull );
  } // VarCHAR()

  public String GetValue() throws Throwable {
    if ( super.m_IsNull )
      throw new Throwable();
    else
      return Character.toString( m_value.charAt( 0 ) );
  } // GetValue()

  public String GetValue( int index ) throws Throwable {
    if ( super.m_IsNull )
      throw new Throwable();
    else {
      if ( index + 1 > m_arraySize ) {
        System.out.println( "needmodi Stack over full." );
        throw new Throwable();
      } // if
      else {
        if ( index + 1 > m_value.length() )
          return Character.toString( '0' );
        else
          return Character.toString( m_value.charAt( index ) );

      } // else
    }

  } // GetValue()

  public char GetCHARValue( int index ) throws Throwable {
    if ( super.m_IsNull )
      throw new Throwable();
    else {
      if ( index + 1 > m_arraySize ) {
        System.out.println( "needmodi Stack over full." );
        throw new Throwable();
      } // if
      else {
        if ( index + 1 > m_value.length() )
          return '\0';
        else
          return m_value.charAt( index );

      } // else
    }

  } // GetCHARValue()

} // class VarCHAR

class VarBOOL extends Variable {

  private boolean m_value;

  public VarBOOL( int type, String name, String valueStr ) throws Throwable {
    super( type, name );
    if ( valueStr.equals( "true" ) )
      this.m_value = true;

    if ( valueStr.equals( "false" ) )
      this.m_value = false;

    if ( valueStr.equals( "1" ) )
      this.m_value = true;

    if ( valueStr.equals( "0" ) )
      this.m_value = false;

  } // VarBOOL()

  public VarBOOL( int type, String name, boolean isNull ) throws Throwable {
    super( type, name, isNull );
  } // VarBOOL()

  public String GetValue() throws Throwable {
    if ( super.m_IsNull )
      throw new Throwable();
    else
      return Boolean.toString( m_value );
  } // GetValue()

  public boolean GetBOOLValue() throws Throwable {
    if ( super.m_IsNull )
      throw new Throwable();
    else
      return this.m_value;
  } // GetBOOLValue()

} // class VarBOOL

class Function {
  private String m_name;
  private Vector<Variable> m_localVar;
  private Vector<Stament> m_commendLine;


  public Function( String fName, Vector<Variable> lVar, Vector<Stament> stament ) throws Throwable {
    this.m_name = new String( fName );
    this.m_localVar = lVar;
    this.m_commendLine = stament;
  } // Function()

  public String GetName() throws Throwable {
    return this.m_name;
  } // GetName()

} // class Function

class CutToken {

  private Vector<TOKEN> mBuffer;
  private Vector<TOKEN> mErrorBuffer;
  private Vector<Variable> mVariables;
  private String mnowLine;
  private Scanner msc;

  public CutToken( Scanner inputSc, Vector<Variable> iV ) throws Throwable {

    mBuffer = new Vector<TOKEN>();
    mErrorBuffer = new Vector<TOKEN>();
    msc = inputSc;
    mVariables = iV;
    mnowLine = new String();

  } // CutToken()

  public boolean Cutting( Vector<TOKEN> stament ) throws Throwable {

    boolean notGetSEMICOLON = true;
    boolean gettingRun = true;

    if ( mBuffer.size() > 0 ) {
      if ( mBuffer.get( 0 ).GetToken().equals( "quit" ) ) {
        System.out.print( "> " );
        return false;
      } // if

    } // if


    if ( Buffer1HasFullCommend( stament ) ) {
      System.out.print( "> " );

      return true;

    } // if
    else {
      System.out.print( "> " );
    } // else

    if ( mnowLine.isEmpty() )
      InputNextLineTomNowLine();

    while ( notGetSEMICOLON ) {
      if ( mnowLine.isEmpty() )
        InputNextLineTomNowLine();
      try {
        if ( mnowLine.charAt( 0 ) == '(' ) {
          mBuffer.add( new TOKEN( "(", 1 ) );
          IsGotTokenProcessFormNowLine( 1 );
          if ( mBuffer.size() > 1 ) {
            if ( mBuffer.get( mBuffer.size() - 2 ).GetType() == Global.s_T_ID ) {

              if ( FindVariable( mBuffer.get( mBuffer.size() - 2 ).GetToken() ) == - 1 ) {
                System.out.println( "Undefined identifier : '"
                                    + mBuffer.get( mBuffer.size() - 2 ).GetToken() + "'" );
                System.out.print( "> " );
                ProcessBeforSEMICOLONFormNowLine(); // mBuffer.clear() ;
                throw new Throwable();
              } // if

            } // if

          } // if
        } // if
        else if ( mnowLine.charAt( 0 ) == ')' ) {
          mBuffer.add( new TOKEN( ")", 2 ) );
          IsGotTokenProcessFormNowLine( 1 );
          if ( mBuffer.size() > 1 ) {
            if ( mBuffer.get( mBuffer.size() - 2 ).GetType() == Global.s_T_LEFT_PAREN ) {
              System.out.println( "Unexpected token : '"
                                  + mBuffer.get( mBuffer.size() - 1 ).GetToken() + "'" );
              System.out.print( "> " );
              ProcessBeforSEMICOLONFormNowLine(); // mBuffer.clear() ;
              throw new Throwable();
            } // if

          } // if

          if ( mBuffer.size() > 1 ) {
            if ( mBuffer.get( mBuffer.size() - 2 ).GetType() == Global.s_T_ID ) {

              if ( FindVariable( mBuffer.get( mBuffer.size() - 2 ).GetToken() ) == - 1 ) {
                System.out.println( "Undefined identifier : '"
                                    + mBuffer.get( mBuffer.size() - 2 ).GetToken() + "'" );
                System.out.print( "> " );
                ProcessBeforSEMICOLONFormNowLine(); // mBuffer.clear() ;
                throw new Throwable();
              } // if

            } // if

          } // if

          int count = 0;
          for ( int i = 0 ; i < mBuffer.size() ; i++ ) {
            if ( mBuffer.get( i ).GetType() == Global.s_T_LEFT_PAREN )
              count = count + 1;
            else if ( mBuffer.get( i ).GetType() == Global.s_T_RIGHT_PAREN )
              count = count - 1;

          } // for

          if ( count < 0 ) {
            System.out.println( "Unexpected token : '"
                                + mBuffer.get( mBuffer.size() - 1 ).GetToken() + "'" );
            System.out.print( "> " );
            ProcessBeforSEMICOLONFormNowLine(); // mBuffer.clear() ;
            throw new Throwable();
          } // if

        } // else if
        else if ( mnowLine.charAt( 0 ) == ';' ) {
          mBuffer.add( new TOKEN( ";", 6 ) );
          IsGotTokenProcessFormNowLine( 1 );

          notGetSEMICOLON = false;

          if ( mBuffer.size() == 1 ) {
            System.out.println( "Unexpected token : ';'" );
            System.out.print( "> " );  // >>isproblem
            mBuffer.clear();
            notGetSEMICOLON = true;
            throw new Throwable();
          } // if

          if ( Buffer1HasFullCommend( stament ) )
            return true;

        } // else if
        else if ( IsASSIGNInmNowLineFirst() ) {
          if ( mnowLine.length() == 1 ) {
            System.out.println( "Unrecognized token with first char : ':'" );
            System.out.print( "> " );
            ProcessBeforSEMICOLONFormNowLine(); // mBuffer.clear();
            throw new Throwable();
          } // if

          if ( mnowLine.charAt( 1 ) != '=' ) {
            System.out.println( "Unrecognized token with first char : ':'" );
            System.out.print( "> " );
            ProcessBeforSEMICOLONFormNowLine(); // mBuffer.clear();
            throw new Throwable();
          } // if

          mBuffer.add( new TOKEN( mnowLine.substring( 0, 2 ), 8 ) );
          IsGotTokenProcessFormNowLine( 2 );
          if ( mBuffer.size() > 1 ) {
            if ( mBuffer.get( mBuffer.size() - 2 ).GetType() != 4 ) {
              System.out.println( "Unexpected token : '"
                                  + mBuffer.get( mBuffer.size() - 1 ).GetToken() + "'" );
              System.out.print( "> " );
              ProcessBeforSEMICOLONFormNowLine(); // mBuffer.clear();
              throw new Throwable();
            } // if

          } // if

        } // else if
        else if ( IsOPERATORInmNowLineFirst() ) {
          String gotOPERATOR = mnowLine.substring( 0, 1 );
          mBuffer.add( new TOKEN( gotOPERATOR, 5 ) );
          IsGotTokenProcessFormNowLine( 1 );

          if ( mBuffer.size() > 1 ) {
            if ( mBuffer.get( mBuffer.size() - 2 ).GetToken().equals( "+" ) ||
                 mBuffer.get( mBuffer.size() - 2 ).GetToken().equals( "-" ) ||
                 mBuffer.get( mBuffer.size() - 2 ).GetToken().equals( "*" ) ||
                 mBuffer.get( mBuffer.size() - 2 ).GetToken().equals( "/" ) ) {
              if ( gotOPERATOR.equals( "*" ) || gotOPERATOR.equals( "/" ) ) {
                System.out.println( "Unexpected token : '"
                                    + mBuffer.get( mBuffer.size() - 1 ).GetToken() + "'" );
                System.out.print( "> " );
                ProcessBeforSEMICOLONFormNowLine(); // mBuffer.clear() ;
                throw new Throwable();
              } // if

            } // if

          } // if


          if ( mBuffer.size() > 2 ) {
            if ( mBuffer.get( mBuffer.size() - 3 ).GetType() == Global.s_T_OPERATOR &&
                 mBuffer.get( mBuffer.size() - 2 ).GetType() == Global.s_T_OPERATOR ) {

              System.out.println( "Unexpected token : '"
                                  + mBuffer.get( mBuffer.size() - 1 ).GetToken() + "'" );
              System.out.print( "> " );
              ProcessBeforSEMICOLONFormNowLine(); // mBuffer.clear() ;
              throw new Throwable();

            } // if

          } // if

          if ( mBuffer.size() > 1 ) {
            if ( mBuffer.get( mBuffer.size() - 2 ).GetType() == Global.s_T_ID ) {

              if ( FindVariable( mBuffer.get( mBuffer.size() - 2 ).GetToken() ) == - 1 ) {
                System.out.println( "Undefined identifier : '"
                                    + mBuffer.get( mBuffer.size() - 2 ).GetToken() + "'" );
                System.out.print( "> " );
                ProcessBeforSEMICOLONFormNowLine(); // mBuffer.clear() ;
                throw new Throwable();
              } // if

            } // if

          } // if

        } // else if
        else if ( IsNUMInmNowLineFirst() ) {
          String gotNUM;
          gotNUM = GetNUMTokenInmNowLine();

          if ( mBuffer.size() > 1 ) {
            if ( mBuffer.get( mBuffer.size() - 1 ).GetType() == Global.s_T_NUMVALUE ) {
              System.out.println( "Unexpected token : '"
                                  + gotNUM + "'" );
              System.out.print( "> " );
              ProcessBeforSEMICOLONFormNowLine(); // mBuffer.clear() ;
              throw new Throwable();
            } // if

          } // if

          if ( gotNUM.charAt( 0 ) == '.' ) {
            mBuffer.add( new TOKEN( "0" + gotNUM, 7 ) );
            gotNUM = "0" + gotNUM;
          } // if
          else {
            mBuffer.add( new TOKEN( gotNUM, 7 ) );

          } // else

          int nowIndex = mBuffer.size() - 1;

          try {

            if ( mBuffer.get( nowIndex ).GetToken().contains( "." ) ) {
              Double rv = Double.parseDouble( gotNUM );
              gotNUM = String.format( "%.10f", rv );

            } // if

            int rv = Integer.valueOf( mBuffer.get( nowIndex ).GetToken() ).intValue();
            mBuffer.get( nowIndex ).SetToken( Integer.toString( rv ) );

          } // try
          catch ( Throwable throwable ) {

          } // catch

          if ( mBuffer.size() > 1 ) {
            if ( mBuffer.get( mBuffer.size() - 2 ).GetToken().equals( "/" ) ) {
              boolean isnnotAll0orp = false;
              int nowtheIndex = mBuffer.size() - 1;
              for ( int i = 0 ; i < mBuffer.get( nowtheIndex ).GetToken().length() ; i++ ) {

                if ( gotNUM.charAt( i ) >= '1' && gotNUM.charAt( i ) <= '9' )
                  isnnotAll0orp = true;

              } // for

              if ( ! isnnotAll0orp ) {
                System.out.println( "Error" );
                System.out.print( "> " );
                ProcessBeforSEMICOLONFormNowLine(); // mBuffer.clear() ;
                throw new Throwable();
              } // if
            } // if

          } // if


        } // else if
        else if ( IsBOOLEANInmNowLineFirst() ) {
          String gotBOOLEANOPERATOE;
          gotBOOLEANOPERATOE = GetBOOLEANOPERATOETokenInmNowLine();
          mBuffer.add( new TOKEN( gotBOOLEANOPERATOE, 3 ) );
          if ( mBuffer.size() > 1 ) {
            if ( mBuffer.get( mBuffer.size() - 2 ).GetType() == Global.s_T_BOOLEANOPERATOE ) {
              System.out.println( "Unexpected token : '"
                                  + mBuffer.get( mBuffer.size() - 1 ).GetToken() + "'" );
              System.out.print( "> " );
              ProcessBeforSEMICOLONFormNowLine(); // mBuffer.clear() ;
              throw new Throwable();
            } // if

          } // if

          if ( mBuffer.size() == 1 ) {
            if ( mBuffer.get( 0 ).GetType() == Global.s_T_BOOLEANOPERATOE ) {
              System.out.println( "Unexpected token : '"
                                  + mBuffer.get( 0 ).GetToken() + "'" );
              System.out.print( "> " );
              ProcessBeforSEMICOLONFormNowLine(); // mBuffer.clear() ;
              throw new Throwable();
            } // if

          } // if

        } // else if
        else if ( IsIDInmNowLineFirst() ) {
          String gotID;
          gotID = GetIDTOETokenInmNowLine();
          mBuffer.add( new TOKEN( gotID, 4 ) );

          if ( mBuffer.size() > 1 ) {
            if ( mBuffer.get( mBuffer.size() - 2 ).GetType() == Global.s_T_ID ) {
              System.out.println( "Unexpected token : '"
                                  + mBuffer.get( mBuffer.size() - 1 ).GetToken() + "'" );
              System.out.print( "> " );
              ProcessBeforSEMICOLONFormNowLine(); // mBuffer.clear() ;
              throw new Throwable();
            } // if

          } // if

          if ( mBuffer.size() > 1 ) {

            if ( FindVariable( mBuffer.get( mBuffer.size() - 1 ).GetToken() ) == - 1 ) {
              System.out.println( "Undefined identifier : '"
                                  + mBuffer.get( mBuffer.size() - 1 ).GetToken() + "'" );
              System.out.print( "> " );
              ProcessBeforSEMICOLONFormNowLine(); // mBuffer.clear() ;
              throw new Throwable();
            } // if

          } // if

          try {
            int nowIndex = mBuffer.size();

            if ( nowIndex > 2 ) {
              if ( mBuffer.get( nowIndex - 2 ).GetType() == Global.s_T_OPERATOR &&
                   mBuffer.get( nowIndex - 1 ).GetType() == Global.s_T_OPERATOR ) {
                System.out.println( "Unexpected token : '"
                                    + mBuffer.get( mBuffer.size() - 1 ).GetToken() + "'" );
                System.out.print( "> " );
                ProcessBeforSEMICOLONFormNowLine(); // mBuffer.clear() ;
                throw new Throwable();
              } // if

            } // if


          } // try
          catch ( Throwable throwable2 ) {
            throw new Throwable();
          } // catch

          if ( gotID.equals( "quit" ) ) {
            if ( mBuffer.size() > 1 ) {
              if ( mBuffer.get( mBuffer.size() - 2 ).GetToken().equals( ";" ) ) {
                mnowLine = new String(); // throw new Throwable();
              } // if

            } // if

            if ( mBuffer.size() == 1 && mBuffer.get( 0 ).GetToken().equals( "quit" ) )
              return false;

            if ( mBuffer.get( 0 ).GetToken().equals( "quit" ) )
              throw new Throwable();

          } // if

        } // else if
        else {
          if ( ! mnowLine.isEmpty() ) {
            System.out.println( "Unrecognized token with first char : '" + mnowLine.charAt( 0 ) + "'" );
            System.out.print( "> " );
            ProcessBeforSEMICOLONFormNowLine(); // mBuffer.clear() ;
            throw new Throwable();
          } // if

        } // else

      }
      catch ( Throwable throwable ) {

        // System.out.print( "> " );
        // notGetSEMICOLON = true;
        mnowLine = new String();

      } // catch

    } // while

    if ( mBuffer.size() > 0 ) {
      if ( mBuffer.get( 0 ).GetToken().equals( "quit" ) ) {
        System.out.print( "> " );
        return false;
      } // if

    } // if

    if ( Buffer1HasFullCommend( stament ) ) {

      return true;

    } // if


    return true;

  } // Cutting()

  private void ProcessBeforSEMICOLONFormNowLine() throws Throwable {

    int lestSEMICOLONIndex = - 1;
    for ( int i = 0 ; i < mBuffer.size() ; i++ ) {
      if ( mBuffer.get( i ).GetType() == Global.s_T_SEMICOLON )
        lestSEMICOLONIndex = i;
    } // for

    if ( lestSEMICOLONIndex < mBuffer.size() - 1 ) {
      for ( int i = lestSEMICOLONIndex + 1 ; mBuffer.size() > lestSEMICOLONIndex - 1 ; )
        mBuffer.remove( i );

    } // if

    if ( lestSEMICOLONIndex == - 1 )
      mBuffer.clear();


  } // ProcessBeforSEMICOLONFormNowLine()

  private String GetIDTOETokenInmNowLine() throws Throwable {
    String gotID = new String();

    gotID = gotID + mnowLine.substring( 0, 1 );
    RemoveFirstCherFormNowLine();

    while ( IsIDLegalCharInmNowLineFirst() ) {

      gotID = gotID + mnowLine.substring( 0, 1 );
      RemoveFirstCherFormNowLine();

    } // while

    RemoveHeadWhiteCherFormNowLine();

    return gotID;

  } // GetIDTOETokenInmNowLine()

  private int FindVariable( String findVarName ) throws Throwable {
    for ( int i = 0 ; i < mVariables.size() ; i++ ) {
      if ( mVariables.get( i ).GetName().equals( findVarName ) )
        return i;
    } // for

    return - 1;

  } // FindVariable()

  private boolean IsIDLegalCharInmNowLineFirst() throws Throwable {
    try {
      char firstChar = mnowLine.charAt( 0 );
      if ( IsIDLegalWord( firstChar ) )
        return true;
      else
        return false;

    } // try
    catch ( Throwable throwable ) {
      return false;
    } // catch

  } // IsIDLegalCharInmNowLineFirst()

  private String GetNUMTokenInmNowLine() throws Throwable {
    String gotNum = new String();
    boolean gettingRun = true;
    boolean gotPoint = false;

    while ( IsNumberAndNumberLegalWordInmNowLineFirstChar() && gettingRun ) {

      if ( ! gotPoint && mnowLine.charAt( 0 ) != '.' ) {
        gotNum = gotNum + mnowLine.substring( 0, 1 );
        RemoveFirstCherFormNowLine();
      } // if
      else if ( ! gotPoint && mnowLine.charAt( 0 ) == '.' ) {
        gotNum = gotNum + mnowLine.substring( 0, 1 );
        RemoveFirstCherFormNowLine();
        gotPoint = true;
      } // else if
      else if ( gotPoint && mnowLine.charAt( 0 ) != '.' ) {
        gotNum = gotNum + mnowLine.substring( 0, 1 );
        RemoveFirstCherFormNowLine();
      } // else if
      else if ( gotPoint && mnowLine.charAt( 0 ) == '.' ) {
        gettingRun = false;
      } // else if

    } // while

    RemoveHeadWhiteCherFormNowLine();

    // if ( mBuffer.size() > 0 ) {
    //   if ( mBuffer.get( 0 ).GetType() == Global.s_T_NUM )
    //     return false;
    // } // if

    if ( IsLegalNUM( gotNum ) ) {
      if ( gotNum.charAt( gotNum.length() - 1 ) == '.' )
        gotNum = gotNum.substring( 0, gotNum.length() - 1 );
      return gotNum;
    } // if
    else {
      System.out.println( "Unexpected token : '" + gotNum + "'" );
      System.out.print( "> " );
      mBuffer.clear();
      throw new Throwable();
    } // else


  } // GetNUMTokenInmNowLine()

  private String GetBOOLEANOPERATOETokenInmNowLine() throws Throwable {

    String gotBOOLEANOPERATOE = new String();
    int findIndxe = - 1;
    if ( FindStrStarIndex( ">=" ) > - 1 )
      findIndxe = FindStrStarIndex( ">=" );
    else if ( FindStrStarIndex( "<=" ) > - 1 )
      findIndxe = FindStrStarIndex( "<=" );

    if ( findIndxe > - 1 ) {
      gotBOOLEANOPERATOE = mnowLine.substring( 0, 2 );
      IsGotTokenProcessFormNowLine( 2 );
    } // if
    else {
      gotBOOLEANOPERATOE = mnowLine.substring( 0, 1 );
      IsGotTokenProcessFormNowLine( 1 );
    } // else

    return gotBOOLEANOPERATOE;

  } // GetBOOLEANOPERATOETokenInmNowLine()

  private boolean IsIDInmNowLineFirst() throws Throwable {
    try {

      char charStr = mnowLine.charAt( 0 );

      if ( IsIDLegalStar( charStr ) )
        return true;
      else
        return false;

    } // try
    catch ( Throwable throwable ) {

      return false;

    } // catch

  } // IsIDInmNowLineFirst()

  private boolean IsBOOLEANInmNowLineFirst() throws Throwable {
    try {

      char charStr = mnowLine.charAt( 0 );

      if ( IsBOOLEANOPERATOE( charStr ) )
        return true;
      else
        return false;

    } // try
    catch ( Throwable throwable ) {

      return false;

    } // catch

  } // IsBOOLEANInmNowLineFirst()

  private boolean IsBOOLEANOPERATOE( char theWord ) throws Throwable {

    if ( theWord == '>' || theWord == '<' || theWord == '=' )
      return true;
    else
      return false;

  } // IsBOOLEANOPERATOE()

  private boolean IsASSIGNInmNowLineFirst() throws Throwable {

    try {
      if ( mnowLine.charAt( 0 ) == ':' )
        return true;
      else
        return false;

    } // try
    catch ( Throwable throwable ) {
      return false;

    } // catch

  } // IsASSIGNInmNowLineFirst()

  private boolean Buffer1HasFullCommend( Vector<TOKEN> buffer ) throws Throwable {

    int semicolonindex = - 1;
    boolean isHas = false;

    for ( int i = 0 ; i < mBuffer.size() ; i++ ) {
      if ( mBuffer.get( i ).GetType() == Global.s_T_SEMICOLON ) {
        semicolonindex = i;
        isHas = true;
        i = mBuffer.size();
      } // if
    } // for

    if ( ! isHas ) {
      return false;
    } // if

    for ( int i = 0 ; i < mBuffer.size() ; i++ )
      buffer.add( mBuffer.get( i ) );

    if ( ( buffer.size() - 1 ) != semicolonindex ) {
      int count = buffer.size() - 1 - semicolonindex;

      for ( int i = 0 ; i < count ; i++ )
        buffer.remove( semicolonindex + 1 );

    } // if

    for ( int i = 0 ; i <= semicolonindex ; i++ )
      mBuffer.remove( 0 );

    return isHas;

  } // Buffer1HasFullCommend()

  private boolean IsIDLegalStar( char theWord ) throws Throwable {

    if ( theWord >= 'A' && theWord <= 'Z' )
      return true;

    if ( theWord >= 'a' && theWord <= 'z' )
      return true;

    if ( theWord == '_' )
      return true;

    return false;

  } // IsIDLegalStar()

  private boolean IsIDLegalWord( char theWord ) throws Throwable {

    if ( theWord >= 'A' && theWord <= 'Z' )
      return true;

    if ( theWord >= 'a' && theWord <= 'z' )
      return true;

    if ( theWord >= '0' && theWord <= '9' )
      return true;

    if ( theWord == '_' )
      return true;

    return false;

  } // IsIDLegalWord()

  private boolean IsLegalNUMStar( char theWord ) throws Throwable {

    if ( theWord >= '0' && theWord <= '9' ) {
      return true;
    } // if
    else {
      return false;
    } // else

  } // IsLegalNUMStar()

  private boolean IsLegalNUM( String fNUM ) throws Throwable {

    try {
      if ( fNUM.isEmpty() )
        return false;

      if ( fNUM.equals( "." ) )
        return false;

      // if ( fNUM.charAt( 0 ) == '.' ) {
      //   return false;

      // } // if

      // if ( fNUM.charAt( fNUM.length() - 1 ) == '.' )
      //  return false;

      return true;
    } // try
    catch ( Throwable throwable ) {
      return false;
    } // catch

  } // IsLegalNUM()

  private boolean IsNumberAndNumberLegalWordInmNowLineFirstChar() throws Throwable {

    try {
      char firstChar = mnowLine.charAt( 0 );
      if ( firstChar >= '0' && firstChar <= '9' )
        return true;
      else if ( firstChar == '.' )
        return true;
      else
        return false;

    } // try
    catch ( Throwable throwable ) {
      return false;
    } // catch

  } // IsNumberAndNumberLegalWordInmNowLineFirstChar()

  private boolean IsOPERATORInmNowLineFirst() throws Throwable {
    char charStr = mnowLine.charAt( 0 );
    boolean isCon1 = false;
    boolean isCon2 = false;

    if ( charStr == '+' || charStr == '-' )
      isCon1 = true;

    if ( charStr == '*' || charStr == '/' )
      isCon2 = true;

    if ( isCon1 || isCon2 )
      return true;
    else
      return false;

  } // IsOPERATORInmNowLineFirst()

  private boolean IsNUMInmNowLineFirst() throws Throwable {
    try {

      char charStr = mnowLine.charAt( 0 );

      if ( IsLegalNUMStar( charStr ) )
        return true;

      if ( charStr == '.' )
        return true;

      else
        return false;

    } // try
    catch ( Throwable throwable ) {

      return false;

    } // catch

  } // IsNUMInmNowLineFirst()

  private void InputNextLineTomNowLine() throws Throwable {

    mnowLine = msc.nextLine();
    mnowLine = RemoveCommend( mnowLine );
    RemoveHeadWhiteCherFormNowLine();
    RemoveTailWhiteCherFormNowLine();

    while ( mnowLine.isEmpty() ) {
      mnowLine = msc.nextLine();
      mnowLine = RemoveCommend( mnowLine );
      RemoveHeadWhiteCherFormNowLine();
      RemoveTailWhiteCherFormNowLine();
    } // while

  } // InputNextLineTomNowLine()

  private String RemoveCommend( String comm ) throws Throwable {

    int findCommend = FindStrStarIndex( comm, "//" );

    if ( findCommend == 0 ) {
      comm = new String();
    } // if
    else if ( findCommend > - 1 ) {
      comm = mnowLine.substring( 0, findCommend );
    } // else if

    return comm;

  } // RemoveCommend()

  private int FindStrStarIndex( String lineStr, String targetStr ) {

    try {
      for ( int i = 0 ; i < lineStr.length() ; i++ ) {
        String subLine = lineStr.substring( i, i + targetStr.length() );
        if ( subLine.equals( targetStr ) )
          return i;

      } // for

    } // try
    catch ( Throwable throwable ) {
      return - 1;
    } // catch

    return - 1;

  } // FindStrStarIndex()

  private int FindStrStarIndex( String targetStr ) {

    String lineStr = mnowLine;

    try {
      for ( int i = 0 ; i < lineStr.length() ; i++ ) {
        String subLine = lineStr.substring( i, i + targetStr.length() );
        if ( subLine.equals( targetStr ) )
          return i;

      } // for

    } // try
    catch ( Throwable throwable ) {
      return - 1;
    } // catch

    return - 1;

  } // FindStrStarIndex()

  private void RemoveHeadWhiteCherFormNowLine() throws Throwable {

    try {

      for ( int i = 0 ; i < mnowLine.length() ; ) {
        if ( IsWhitSpace( mnowLine.charAt( i ) ) ) {
          if ( mnowLine.length() == 1 )
            mnowLine = new String();
          else
            mnowLine = mnowLine.substring( i + 1 );

        } // if
        else
          i = mnowLine.length();

      } // for

    } // try
    catch ( Throwable throwable ) {

    } // catch


  } // RemoveHeadWhiteCherFormNowLine()

  private void RemoveTailWhiteCherFormNowLine() throws Throwable {

    try {

      for ( int i = mnowLine.length() - 1 ; i > - 1 ; i = i ) {
        if ( IsWhitSpace( mnowLine.charAt( i ) ) ) {
          if ( mnowLine.length() == 1 )
            mnowLine = new String();
          else
            mnowLine = mnowLine.substring( 0, i );

          i = mnowLine.length() - 1;

        } // if
        else
          i = - 1;

      } // for

    } // try
    catch ( Throwable throwable ) {

    } // catch


  } // RemoveTailWhiteCherFormNowLine()

  private void RemoveFirstCherFormNowLine() throws Throwable {

    try {

      if ( mnowLine.length() == 1 || mnowLine.isEmpty() )
        mnowLine = new String();
      else
        mnowLine = mnowLine.substring( 1 );

    } // try
    catch ( Throwable throwable ) {

    } // catch


  } // RemoveFirstCherFormNowLine()

  private void IsGotTokenProcessFormNowLine( int gotCharIndex ) throws Throwable {
    for ( int i = 0 ; i < gotCharIndex ; i++ )
      RemoveFirstCherFormNowLine();

    RemoveHeadWhiteCherFormNowLine();

  } // IsGotTokenProcessFormNowLine()

  private boolean IsWhitSpace( char charStr ) throws Throwable {

    if ( charStr == ' ' || charStr == '\t' )
      return true;

    if ( charStr == '\r' || charStr == '\n' )
      return true;

    if ( charStr == '\u000B' || charStr == '\f' )
      return true;

    if ( charStr == '\u001C' || charStr == '\u001D' )
      return true;

    if ( charStr == '\u001E' || charStr == '\u001F' )
      return true;

    return false;

  } // IsWhitSpace()

} // class CutToken

class Parser {
  public Vector<TOKEN> m_statement;
  public int m_step;

  public Parser( Vector<TOKEN> inputStatement ) throws Throwable {
    m_statement = inputStatement;
    m_step = 0; // check which token is wrong
  } // Parser()

  public boolean GrammarParser() throws Throwable {
    try {
      if ( m_statement.get( 0 ).GetToken().equals( ";" ) ) {
        System.out.println( "Unexpected token : ';'" );
        m_statement.clear();
        return false;
      } // if

      if ( this.UserInput() ) {
        return true;
      } // if ()
      else {
        System.out.println( "Unexpected token : '" + m_statement.get( m_step ).GetToken() + "'" );
        return false;
      } // else

    } // try
    catch ( Throwable throwable ) {
      System.out.println( "Unexpected token : '" + m_statement.get( m_step ).GetToken() + "'" );
      return false;
    } // catch
  } // GrammarParser()

  private boolean UserInput() throws Throwable {
    try {
      int curStep = 0;

      if ( Definition() ) {
        ;
      } //if
      else if ( Statement() ) {
        ;
      } // else if
      else {
        return false;
      } // else

      while ( ( Definition() || Statement() ) ) {
        ;
      } // while

      if ( m_step != m_statement.size() )
        return false;

      return true;
    } // try
    catch ( Throwable throwable ) {
      return false;
    } // catch
  } // UserInput()

  private boolean Definition() throws Throwable {
    try {
      if ( m_statement.get( m_step ).GetToken().toLowerCase().equals( "void" )
           && m_statement.get( m_step ).GetType() == 12 ) {
        m_step += 1;
        if ( m_statement.get( m_step ).GetType() == 4 ) {
          m_step += 1;
          if ( Function_Definition_Without_ID() )
            return true;
          else {
            return false;
          } // else
        } // if
        else {
          return false;
        } // else
      } // if
      else if ( Type_Specifier() ) {
        m_step += 1;
        if ( m_statement.get( m_step ).GetType() == 4 ) {
          m_step += 1;
          if ( Function_Definition_OR_Declarators() ) {
            return true;
          } // if
          else {
            return false;
          } // else
        } // if
        else {
          return false;
        } // else
      } // else if
      else {
        return false;
      } // else
    } // try
    catch ( Throwable throwable ) {
      return false;
    } // catch
  } // Definition()

  private boolean Type_Specifier() throws Throwable {
    try {
      if ( m_statement.get( m_step ).GetType() == 12
           && m_statement.get( m_step ).GetToken().toLowerCase().equals( "int" )
           && m_statement.get( m_step ).GetToken().toLowerCase().equals( "char" )
           && m_statement.get( m_step ).GetToken().toLowerCase().equals( "float" )
           && m_statement.get( m_step ).GetToken().toLowerCase().equals( "string" )
           && m_statement.get( m_step ).GetToken().toLowerCase().equals( "bool" ) ) {
        return true;
      } // if
      else
        return false;
    } // try
    catch ( Throwable throwable ) {
      return false;
    } // catch
  } // Type_Specifier()

  private boolean Function_Definition_OR_Declarators() throws Throwable {
    try {
      if ( Function_Definition_Without_ID() ) {
        return true;
      } // if
      else if ( Rest_of_Declarators() ) {
        return true;
      } // else if
      else {
        return false;
      } // else
    } // try
    catch ( Throwable throwable ) {
      return false;
    } // catch
  } // Function_Definition_OR_Declarators()

  // 未檢察[ ]的type
  private boolean Rest_of_Declarators() throws Throwable {
    try {
      if ( m_statement.get( m_step ).GetToken().equals( "[" ) ) {
        m_step += 1;
        if ( m_statement.get( m_step ).GetType() == 13 ) { // Constant(還未定義)
          m_step += 1;
          if ( m_statement.get( m_step ).GetToken().equals( "]" ) ) {
            m_step += 1;
          } // if
          else {
            return false;
          } // else
        } // if
        else {
          return false;
        } // else
      } // if

      while ( m_statement.get( m_step ).GetToken().equals( ", " ) ) {
        m_step += 1;
        if ( m_statement.get( m_step ).GetType() == 4 ) { // ID
          m_step += 1;
          if ( m_statement.get( m_step ).GetToken().equals( "[" ) ) {
            m_step += 1;
            if ( m_statement.get( m_step ).GetType() == 13 ) { // Constant (尚未定義)
              m_step += 1;
              if ( m_statement.get( m_step ).GetToken().equals( "]" ) ) {
                m_step += 1;
              } // if
              else {
                return false;
              } // else
            } // if
            else {
              return false;
            } // else
          } //if
        } // if
        else {
          return false;
        } // else
      } // while

      if ( m_statement.get( m_step ).GetToken().equals( ";" )
           && m_statement.get( m_step ).GetType() == 6 ) {
        return true;
      } // if
      else {
        return false;
      } // else
    } // try
    catch ( Throwable throwable ) {
      return false;
    } // catch
  } // Rest_of_Declarators

  private boolean Function_Definition_Without_ID() throws Throwable {
    try {
      if ( m_statement.get( m_step ).GetToken().equals( "(" )
           && m_statement.get( m_step ).GetType() == 1 ) {
        m_step += 1;
        if ( m_statement.get( m_step ).GetToken().toLowerCase().equals( "void" )
             && m_statement.get( m_step ).GetType() == 12 ) {
          m_step += 1;
          if ( m_statement.get( m_step ).GetToken().equals( ")" )
               && m_statement.get( m_step ).GetType() == 2 ) {
            m_step += 1;
          } // if
          else {
            return false;
          } // else
        } // if
        else if ( Formal_Parameter_List() ) {
          m_step += 1;
          if ( m_statement.get( m_step ).GetToken().equals( ")" )
               && m_statement.get( m_step ).GetType() == 2 ) {
            m_step += 1;
          } // if
          else {
            return false;
          } // else
        } // else if
        else if ( m_statement.get( m_step ).GetToken().equals( ")" )
                  && m_statement.get( m_step ).GetType() == 2 ) {
          m_step += 1;
        } // else if
        else {
          return false;
        } //else
      } // if
      else {
        return false;
      } // else

      if ( Compound_Statement() ) {
        return true;
      } // if
      else {
        return false;
      } // else
    } // try
    catch ( Throwable throwable ) {
      return false;
    } // catch()
  } // Function_Definition_Without_ID()

  private boolean Formal_Parameter_List() throws Throwable {
    try {
      if ( Type_Specifier() ) {
        m_step += 1;
        if ( m_statement.get( m_step ).GetToken().equals( "&" ) )
          m_step += 1;

        if ( m_statement.get( m_step ).GetType() == 4 ) { // ID
          m_step += 1;
          if ( m_statement.get( m_step ).GetToken().equals( "[" ) ) {
            m_step += 1;
            if ( m_statement.get( m_step ).GetType() == 13 ) { // Constant (尚未定義)
              m_step += 1;
              if ( m_statement.get( m_step ).GetToken().equals( "]" ) ) {
                m_step += 1;
              } // if
              else {
                return false;
              } // else
            } // if
            else {
              return false;
            } // else
          } // if

          while ( m_statement.get( m_step ).GetToken().equals( ", " ) ) {
            m_step += 1;
            if ( Type_Specifier() ) {
              m_step += 1;
              if ( m_statement.get( m_step ).GetToken().equals( "&" ) )
                m_step += 1;

              if ( m_statement.get( m_step ).GetType() == 4 ) { // ID
                m_step += 1;
                if ( m_statement.get( m_step ).GetToken().equals( "[" ) ) {
                  m_step += 1;
                  if ( m_statement.get( m_step ).GetType() == 13 ) { // Constant (尚未定義)
                    m_step += 1;
                    if ( m_statement.get( m_step ).GetToken().equals( "]" ) ) {
                      m_step += 1;
                    } // if
                    else {
                      return false;
                    } // else
                  } // if
                  else {
                    return false;
                  } // else
                } // if
              } // if
              else {
                return false;
              } // else
            } // if
            else {
              return false;
            } // else
          } // while

          return true;
        } // if
        else {
          return false;
        } // else
      } // if
      else {
        return false;
      } // else
    } // try
    catch ( Throwable throwable ) {
      return false;
    } // catch()
  } // Formal_Parameter_List()

  private boolean Compound_Statement() throws Throwable {
    try {
      if ( m_statement.get( m_step ).GetToken().equals( "{" ) ) {
        m_step += 1;
        if ( declaration() ) {
          return true;
        } // if
        else if ( Statement() ) {
          return true;
        } // else if
        else if ( m_statement.get( m_step ).GetToken().equals( "}" ) ) {
          return true;
        } // else if
        else {
          return false;
        } // else
      } // if
      else {
        return false;
      } // else
    } // try
    catch ( Throwable throwable ) {
      return false;
    } // catch()
  } // Compound_Statement()

  private boolean declaration() throws Throwable {
    try {
      if ( Type_Specifier() ) {
        m_step += 1;
        if ( m_statement.get( m_step ).GetType() == 4 ) { // ID
          m_step += 1;
          if ( Rest_of_Declarators() ) {
            return true;
          } // if
          else {
            return false;
          } // else
        } // if
        else {
          return false;
        } // else
      } // if
      else {
        return false;
      } // else
    } // try
    catch ( Throwable throwable ) {
      return false;
    } // catch()
  } // declaration()

  private boolean Statement() throws Throwable {
    try {
      if ( m_statement.get( m_step ).GetToken().equals( ";" )
           && m_statement.get( m_step ).GetType() == 6 ) {
        return true;
      } // if
      else if ( m_statement.get( m_step ).GetToken().equals( "return" ) ) {
        m_step += 1;
        if ( Expression() )
          m_step += 1;
        if ( m_statement.get( m_step ).GetToken().equals( ";" )
             && m_statement.get( m_step ).GetType() == 6 ) {
          return true;
        } // if
        else {
          return false;
        } // else
      } // else if
      else if ( Compound_Statement() ) {
        return true;
      } // else if
      else if ( m_statement.get( m_step ).GetToken().equals( "if" ) ) {
        m_step += 1;
        if ( m_statement.get( m_step ).GetToken().equals( "(" )
             && m_statement.get( m_step ).GetType() == 1 ) {
          m_step += 1;
          if ( Expression() ) {
            m_step += 1;
            if ( m_statement.get( m_step ).GetToken().equals( ")" )
                 && m_statement.get( m_step ).GetType() == 2 ) {
              m_step += 1;
              if ( Statement() ) {
                m_step += 1; // 可能會out of range
                if ( m_statement.get( m_step ).GetToken().equals( "else" ) ) {
                  m_step += 1;
                  if ( Statement() ) {
                    return true;
                  } // if
                  else {
                    return false;
                  } // else
                } // if
                return true;
              } // if
              else {
                return false;
              } // else
            } // if
            else {
              return false;
            } // else
          } // if
          else {
            return false;
          } // else
        } // if
        else {
          return false;
        } // else
      } // else if
      else if ( m_statement.get( m_step ).equals( "while" ) ) {
        m_step += 1;
        if ( m_statement.get( m_step ).GetToken().equals( "(" )
             && m_statement.get( m_step ).GetType() == 1 ) {
          if ( Expression() ) {
            m_step += 1;
            if ( m_statement.get( m_step ).GetToken().equals( ")" ) ) {
              m_step += 1;
              if ( Statement() ) {
                return true;
              } // if
              else {
                return false;
              } // else
            } // if
            else {
              return false;
            } // else
          } // if
          else {
            return false;
          } // else
        } // if
        else {
          return false;
        } // else
      } // else if
      else if ( m_statement.get( m_step ).GetToken().equals( "do" ) ) {
        m_step += 1;
        if ( Statement() ) {
          m_step += 1;
          if ( m_statement.get( m_step ).GetToken().equals( "while" ) ) {
            m_step += 1;
            if ( m_statement.get( m_step ).GetToken().equals( "(" )
                 && m_statement.get( m_step ).GetType() == 1 ) {
              m_step += 1;
              if ( Expression() ) {
                m_step += 1;
                if ( m_statement.get( m_step ).GetToken().equals( ")" )
                     && m_statement.get( m_step ).GetType() == 2 ) {
                  m_step += 1;
                  if ( m_statement.get( m_step ).GetToken().equals( ";" )
                       && m_statement.get( m_step ).GetType() == 6 ) {
                    return true;
                  } // if
                  else {
                    return false;
                  } // else
                } // if
                else {
                  return false;
                } // else
              } // if
              else {
                return false;
              } // else
            } // if
            else {
              return false;
            } // else
          } // if
          else {
            return false;
          } // else
        } // if
        else {
          return false;
        } // else
      } // else if
      else {
        return false;
      } // else
    } // try
    catch ( Throwable throwable ) {
      return false;
    } // catch()
  } // Statement()

  private boolean Expression() throws Throwable {
    try {
      boolean hasWrong = false;
      if ( Basic_Expression() ) {
        m_step += 1;
      } // if
      else {
        hasWrong = true;
      } // else

      if ( hasWrong ) {
        return false;
      } // if

      while ( m_statement.get( m_step ).GetToken().equals( ", " ) ) {
        m_step += 1;
        if ( Basic_Expression() ) {
          m_step += 1;
        } // if
        else {
          return false;
        } // else
      } // while
      return true;
    } // try
    catch ( Throwable throwable ) {
      return false;
    } // catch()
  } // Expression()

  private boolean Basic_Expression() throws Throwable {
    try {
      if ( m_statement.get( m_step ).GetType() == 4 ) { // ID
        if ( Rest_of_Identifier_Started_Basic_Exp() ) {
          return true;
        } // if
        else {
          return false;
        } // else
      } // if
      else if ( m_statement.get( m_step ).GetToken().equals( "++" )
                || m_statement.get( m_step ).GetToken().equals( "--" ) ) {
        m_step += 1;
        if ( m_statement.get( m_step ).GetType() == 4 ) { // ID
          m_step += 1;
          if ( Rest_of_PPMM_Identifier_Started_Basic_Exp() ) {
            return true;
          } // if
          else {
            return false;
          } // else
        } // if
        else {
          return false;
        } // else
      } // else if
      else if ( Sign() ) {
        m_step += 1;
        while ( Sign() ) {
          m_step += 1;
        } // while

        if ( Signed_Unary_Exp() ) {
          m_step += 1;
          if ( Romce_and_Romloe() ) {
            return true;
          } // if
          else {
            return false;
          } // else
        } // if
        else {
          return false;
        } // else
      } // else if
      else if ( m_statement.get( m_step ).GetType() == 13 ) { // Constant (尚未被定義)
        m_step += 1;
        if ( Romce_and_Romloe() ) {
          return true;
        } // if
        else {
          return false;
        } // else
      } // else if
      else if ( m_statement.get( m_step ).GetToken().equals( "(" )
                && m_statement.get( m_step ).GetType() == 1 ) {
        m_step += 1;
        if ( Expression() ) {
          m_step += 1;
          if ( m_statement.get( m_step ).GetToken().equals( ")" )
               && m_statement.get( m_step ).GetType() == 2 ) {
            m_step += 1;
            if ( Romce_and_Romloe() ) {
              return true;
            } // if
            else {
              return false;
            } // else
          } // if
          else {
            return false;
          } // else
        } // if
        else {
          return false;
        } // else
      } // else if
      else {
        return false;
      } // else
    } // try
    catch ( Throwable throwable ) {
      return false;
    } // catch()
  } // Basic_Expression()

  private boolean Rest_of_Identifier_Started_Basic_Exp() throws Throwable {
    try {
      boolean notOverYet = false;

      if ( m_statement.get( m_step ).GetToken().equals( "[" ) ) {
        m_step += 1;
        if ( Expression() ) {
          m_step += 1;
          if ( m_statement.get( m_step ).GetToken().equals( "]" ) ) {
            m_step += 1;
          } // if
          else {
            return false;
          } // else
        } // if
        else {
          return false;
        } // else
      } // if

      if ( Assignment_Operator() ) {
        m_step += 1;
        if ( Basic_Expression() ) {
          return true;
        } // if
        else {
          return false;
        } // else
      } // if
      else if ( m_statement.get( m_step ).GetToken().equals( "++" )
                || m_statement.get( m_step ).equals( "--" ) ) {
        m_step += 1;
        if ( Romce_and_Romloe() ) {
          return true;
        } // if
        else {
          return false;
        } // else
      } // if
      else if ( Romce_and_Romloe() ) {
        return true;
      } // if
      else if ( m_statement.get( m_step ).GetToken().equals( "(" )
                || m_statement.get( m_step ).GetType() == 1 ) {
        m_step += 1;
        if ( Actual_Parameter_List() ) {
          m_step += 1;
        } // if

        if ( m_statement.get( m_step ).GetToken().equals( ")" )
             || m_statement.get( m_step ).GetType() == 2 ) {
          m_step += 1;
          if ( Romce_and_Romloe() ) {
            return true;
          } // if
          else {
            return false;
          } // else
        } // if
        else {
          return false;
        } // else
      } // else if
      else {
        return false;
      } // else
    } // try
    catch ( Throwable throwable ) {
      return false;
    } // catch()
  } // Rest_of_Identifier_Started_Basic_Exp()

  private boolean Rest_of_PPMM_Identifier_Started_Basic_Exp() throws Throwable {
    try {
      if ( m_statement.get( m_step ).GetToken().equals( "[" ) ) {
        m_step += 1;
        if ( Expression() ) {
          m_step += 1;
          if ( m_statement.get( m_step ).GetToken().equals( "]" ) ) {
            m_step += 1;
          } // if
          else {
            return false;
          } // else
        } // if
        else {
          return false;
        } // else
      } // if

      if ( Romce_and_Romloe() ) {
        return true;
      } // if
      else {
        return false;
      } // else
    } // try
    catch ( Throwable throwable ) {
      return false;
    } // catch()
  } // Rest_of_PPMM_Identifier_Started_Basic_Exp()

  private boolean Sign() throws Throwable {
    if ( m_statement.get( m_step ).GetToken().equals( "+" )
         || m_statement.get( m_step ).GetToken().equals( "-" )
         || m_statement.get( m_step ).GetToken().equals( "!" ) ) {
      return true;
    } // if
    else {
      return false;
    } // else
  } // Sign()

  private boolean Actual_Parameter_List() throws Throwable {
    try {
      if ( Basic_Expression() ) {
        m_step += 1;

        while ( m_statement.get( m_step ).GetToken().equals( ", " ) ) {
          m_step += 1;
          if ( Basic_Expression() ) {
            m_step += 1;
          } // if
          else {
            return false;
          } // else
        } // while

        return true;
      } // if
      else {
        return false;
      } // else
    } // try
    catch ( Throwable throwable ) {
      return false;
    } // catch()
  } // Actual_Parameter_List

  private boolean Assignment_Operator() throws Throwable {
    if ( m_statement.get( m_step ).GetToken().equals( "=" ) ) {
      return true;
    } // if
    else if ( m_statement.get( m_step ).GetToken().equals( "*=" ) ) { // TE
      return true;
    } // else if
    else if ( m_statement.get( m_step ).GetToken().equals( "/=" ) ) { // DE
      return true;
    } // else if
    else if ( m_statement.get( m_step ).GetToken().equals( "%=" ) ) { // RE
      return true;
    } // else if
    else if ( m_statement.get( m_step ).GetToken().equals( "+=" ) ) { // PE
      return true;
    } // else if
    else if ( m_statement.get( m_step ).GetToken().equals( "-=" ) ) { // ME
      return true;
    } // else if
    else {
      return false;
    } // else
  } // Assignment_Operator()

  private boolean Romce_and_Romloe() throws Throwable {
    try {
      if ( Rest_of_Maybe_Logical_OR_Exp() ) {
        m_step += 1; // 可能會out of range

        if ( m_statement.get( m_step ).GetToken().equals( "?" ) ) {
          m_step += 1;
          if ( Basic_Expression() ) {
            m_step += 1;
            if ( m_statement.get( m_step ).GetToken().equals( ":" ) ) {
              m_step += 1;
              if ( Basic_Expression() ) {
                return true;
              } // if
              else {
                return false;
              } // else
            } // if
            else {
              return false;
            } // else
          } // if
          else {
            return false;
          } // else
        } // if

        return true;
      } // if
      else {
        return false;
      } // else
    } // try
    catch ( Throwable throwable ) {
      return false;
    } // catch
  } // Romce_and_Romloe()

  private boolean Rest_of_Maybe_Logical_OR_Exp() throws Throwable {
    try {
      if ( Rest_of_Maybe_Logical_AND_Exp() ) {
        m_step += 1;
      } // if
      else {
        return false;
      } // else

      while ( m_statement.get( m_step ).GetToken().toLowerCase().equals( "or" ) ) { // 可能會out of range
        m_step += 1;
        if ( Maybe_Logical_AND_Exp() ) {
          m_step += 1;
        } // if
        else {
          return false;
        } // else
      } // while

      return true;
    } // try
    catch ( Throwable throwable ) {
      return false;
    } // catch()
  } // Rest_of_Maybe_Logical_OR_Exp()

  private boolean Maybe_Logical_AND_Exp() throws Throwable {
    try {
      if ( Maybe_Bit_OR_Exp() ) {
        m_step += 1;
      } // if
      else {
        return false;
      } // else

      while ( m_statement.get( m_step ).GetToken().toLowerCase()
                         .equals( "and" ) ) { // maybe will out of range
        m_step += 1;
        if ( Maybe_Bit_OR_Exp() ) {
          m_step += 1;
        } // if
        else {
          return false;
        } // else
      } // while

      return true;
    } // try
    catch ( Throwable throwable ) {
      return false;
    } // catch()
  } // Maybe_Logical_AND_Exp()

  private boolean Rest_of_Maybe_Logical_AND_Exp() throws Throwable {
    try {
      if ( Rest_of_Maybe_Bit_OR_Exp() ) {
        m_step += 1;
      } // if
      else {
        return false;
      } // else

      while ( m_statement.get( m_step ).GetToken().toLowerCase()
                         .equals( "and" ) ) { // maybe will out of range
        m_step += 1;

        if ( Maybe_Bit_OR_Exp() ) {
          m_step += 1;
        } // if
        else {
          return false;
        } // else
      } // while

      return true;
    } // try
    catch ( Throwable throwable ) {
      return false;
    } // catch()
  } // Rest_of_Maybe_Logical_AND_Exp()

  private boolean Maybe_Bit_OR_Exp() throws Throwable {
    try {
      if ( Maybe_Bit_Ex_OR_Exp() ) {
        m_step += 1;
      } // if
      else {
        return false;
      } // else

      while ( m_statement.get( m_step ).GetToken().equals( "|" ) ) { // maybe will out of range
        m_step += 1;

        if ( Maybe_Bit_Ex_OR_Exp() ) {
          m_step += 1;
        } // if
        else {
          return false;
        } // else
      } // while

      return true;
    } // try
    catch ( Throwable throwable ) {
      return false;
    } // catch
  } // Maybe_Bit_OR_Exp()

  private boolean Rest_of_Maybe_Bit_OR_Exp() throws Throwable {
    try {
      if ( Rest_of_Maybe_Bit_Ex_OR_Exp() ) {
        m_step += 1;
      } // if
      else {
        return false;
      } // else

      while ( m_statement.get( m_step ).GetToken().equals( "|" ) ) {
        m_step += 1;

        if ( Maybe_Bit_Ex_OR_Exp() ) {
          m_step += 1;
        } // if
        else {
          return false;
        } // else
      } // while

      return true;
    } // try
    catch ( Throwable throwable ) {
      return false;
    } // catch()
  } // Rest_of_Maybe_Bit_OR_Exp()

  private boolean Maybe_Bit_Ex_OR_Exp() throws Throwable {
    try {
      if ( Maybe_Bit_AND_Exp() ) {
        m_step += 1;
      } // if
      else {
        return false;
      } // else

      while ( m_statement.get( m_step ).GetToken().equals( "^" ) ) { // maybe will out of range
        m_step += 1;
        if ( Maybe_Bit_AND_Exp() ) {
          m_step += 1;
        } // if
        else {
          return false;
        } // else
      } // while

      return true;
    } // try
    catch ( Throwable throwable ) {
      return false;
    } // catch()
  } // Maybe_Bit_Ex_OR_Exp

  private boolean Rest_of_Maybe_Bit_Ex_OR_Exp() throws Throwable {
    try {
      if ( Rest_of_Maybe_Bit_AND_Exp() ) {
        m_step += 1;
      } // if
      else {
        return false;
      } // else

      while ( m_statement.get( m_step ).GetToken().equals( "^" ) ) { // maybe will out of range
        m_step += 1;

        if ( Maybe_Bit_AND_Exp() ) {
          m_step += 1;
        } // if
        else {
          return false;
        } // else
      } // while

      return true;
    } // try
    catch ( Throwable throwable ) {
      return false;
    } // catch
  } // Rest_of_Maybe_Bit_OR_Exp

  private boolean Maybe_Bit_AND_Exp() throws Throwable {
    try {
      if ( Maybe_Equality_Exp() ) {
        m_step += 1;
      } // if
      else {
        return false;
      } // else

      while ( m_statement.get( m_step ).GetToken().equals( "&" ) ) { // maybe will out of range
        m_step += 1;

        if ( Maybe_Equality_Exp() ) {
          m_step += 1;
        } // if
        else {
          return false;
        } // else
      } // while

      return true;
    } // try
    catch ( Throwable throwable ) {
      return false;
    } // catch()
  } // Maybe_Bit_AND_Exp()

  private boolean Rest_of_Maybe_Bit_AND_Exp() throws Throwable {
    try {
      if ( Rest_of_Maybe_Equality_Exp() ) {
        m_step += 1;
      } // if
      else {
        return false;
      } // else

      while ( m_statement.get( m_step ).GetToken().equals( "&" ) ) { // maybe will out of range
        m_step += 1;

        if ( Maybe_Equality_Exp() ) {
          m_step += 1;
        } // if
        else {
          return false;
        } // else
      } // while

      return true;
    } // try
    catch ( Throwable throwable ) {
      return false;
    } // catch()
  } // Rest_of_Maybe_Bit_AND_Exp()

  private boolean Maybe_Equality_Exp() throws Throwable {
    try {
      if ( Maybe_Equality_Exp() ) {
        m_step += 1;
      } // if
      else {
        return false;
      } // else

      while ( m_statement.get( m_step ).GetToken().equals( "==" )
              || m_statement.get( m_step ).GetToken().equals( "!=" ) ) {
        m_step += 1;
        if ( Maybe_Relational_Exp() ) {
          m_step += 1;
        } // if
        else {
          return false;
        } // else
      } // while

      return true;
    } // try
    catch ( Throwable throwable ) {
      return false;
    } // catch()
  } // Maybe_Equality_Exp()

  private boolean Rest_of_Maybe_Equality_Exp() throws Throwable {
    try {
      if ( Rest_of_Maybe_Relational_Exp() ) {
        m_step += 1;
      } // if
      else {
        return false;
      } // else

      while ( m_statement.get( m_step ).GetToken().equals( "==" )
              || m_statement.get( m_step ).GetToken().equals( "!=" ) ) {
        m_step += 1;
        if ( Maybe_Relational_Exp() ) {
          m_step += 1;
        } // if
        else {
          return false;
        } // else
      } // while

      return true;
    } // try
    catch ( Throwable throwable ) {
      return false;
    } // catch()
  } // Rest_of_Maybe_Relational_Exp()

  private boolean Maybe_Relational_Exp() throws Throwable {
    try {
      if ( Maybe_Shift_Exp() ) {
        m_step += 1;
      } // if
      else {
        return false;
      } // else

      while ( m_statement.get( m_step ).GetToken().equals( "<" )
              || m_statement.get( m_step ).GetToken().equals( ">" )
              || m_statement.get( m_step ).GetToken().equals( "<=" )
              || m_statement.get( m_step ).GetToken().equals( ">=" ) ) {
        m_step += 1;
        if ( Maybe_Shift_Exp() ) {
          m_step += 1;
        } // if
        else {
          return false;
        } // else
      } // while

      return true;
    } // try
    catch ( Throwable throwable ) {
      return false;
    } // catch
  } // Maybe_Relational_Exp()

  private boolean Rest_of_Maybe_Relational_Exp() throws Throwable {
    try {
      if ( Rest_of_Maybe_Shift_Exp() ) {
        m_step += 1;
      } // if
      else {
        return false;
      } // else

      while ( m_statement.get( m_step ).GetToken().equals( "<" )
              || m_statement.get( m_step ).GetToken().equals( ">" )
              || m_statement.get( m_step ).GetToken().equals( "<=" )
              || m_statement.get( m_step ).GetToken().equals( ">=" ) ) {
        m_step += 1;
        if ( Maybe_Shift_Exp() ) {
          m_step += 1;
        } // if
        else {
          return false;
        } // else
      } // while

      return true;
    } // try
    catch ( Throwable throwable ) {
      return false;
    } // catch()
  } // Rest_of_Maybe_Relational_Exp()

  private boolean Maybe_Shift_Exp() throws Throwable {
    try {
      if ( Maybe_Additive_Exp() ) {
        m_step += 1;
      } // if
      else {
        return false;
      } // else

      while ( m_statement.get( m_step ).GetToken().equals( ">>" )
              || m_statement.get( m_step ).GetToken().equals( "<<" ) ) {
        m_step += 1;
        if ( Maybe_Additive_Exp() ) {
          m_step += 1;
        } // if
        else {
          return false;
        } // else
      } // while

      return true;
    } // try
    catch ( Throwable throwable ) {
      return false;
    } // catch()
  } // Maybe_Shift_Exp()

  private boolean Rest_of_Maybe_Shift_Exp() throws Throwable {
    try {
      if ( Rest_of_Maybe_Shift_Exp() ) {
        m_step += 1;
      } // if
      else {
        return false;
      } // else

      while ( m_statement.get( m_step ).GetToken().equals( ">>" )
              || m_statement.get( m_step ).GetToken().equals( "<<" ) ) {
        m_step += 1;
        if ( Maybe_Additive_Exp() ) {
          m_step += 1;
        } // if
        else {
          return false;
        } // else
      } // while

      return true;
    } // try
    catch ( Throwable throwable ) {
      return false;
    } // catch()
  } // Rest_of_Maybe_Shift_Exp()

  private boolean Maybe_Additive_Exp() throws Throwable {
    try {
      if ( Maybe_Mult_Exp() ) {
        m_step += 1;
      } // if
      else {
        return false;
      } // else

      while ( m_statement.get( m_step ).GetToken().equals( "+" )
              || m_statement.get( m_step ).GetToken().equals( "-" ) ) {
        m_step += 1;
        if ( Maybe_Mult_Exp() ) {
          m_step += 1;
        } // if
        else {
          return false;
        } // else
      } // while

      return true;
    } // try
    catch ( Throwable throwable ) {
      return false;
    } // catch()
  } // Maybe_Additive_Exp()

  private boolean Rest_of_Maybe_Additive_Exp() throws Throwable {
    try {
      if ( Rest_of_Maybe_Mult_Exp() ) {
        m_step += 1;
      } // if
      else {
        return false;
      } // else

      while ( m_statement.get( m_step ).GetToken().equals( "+" )
              || m_statement.get( m_step ).GetToken().equals( "-" ) ) {
        m_step += 1;
        if ( Maybe_Mult_Exp() ) {
          m_step += 1;
        } // if
        else {
          return false;
        } // else
      } // while

      return true;
    } // try
    catch ( Throwable throwable ) {
      return false;
    } // catch()
  } // Rest_of_Maybe_Additive_Exp()

  private boolean Maybe_Mult_Exp() throws Throwable {
    try {
      if ( Unary_Exp() ) {
        m_step += 1;
        if ( Rest_of_Maybe_Mult_Exp() ) {
          return true;
        } // if
        else {
          return false;
        } // else
      } // if
      else {
        return false;
      } // else
    } // try
    catch ( Throwable throwable ) {
      return false;
    } // catch()
  } // Maybe_Mult_Exp()

  private boolean Rest_of_Maybe_Mult_Exp() throws Throwable {
    try {
      while ( m_statement.get( m_step ).GetToken().equals( "*" )
              || m_statement.get( m_step ).GetToken().equals( "/" )
              || m_statement.get( m_step ).GetToken().equals( "%" ) ) {
        m_step += 1;
        if ( Unary_Exp() ) {
          m_step += 1;
        } // if
        else {
          return false;
        } // else
      } // while

      return true;
    } // try
    catch ( Throwable throwable ) {
      return false;
    } // catch()
  } // Rest_of_Maybe_Mult_Exp()

  private boolean Unary_Exp() throws Throwable {
    try {
      if ( Sign() ) {
        m_step += 1;

        while ( Sign() ) {
          m_step += 1;
        } // while

        if ( Signed_Unary_Exp() ) {
          return true;
        } // if
        else {
          return false;
        } // else
      } // if
      else if ( Unsigned_Unary_Exp() ) {
        return true;
      } // else if
      else if ( m_statement.get( m_step ).GetToken().equals( "++" )
                || m_statement.get( m_step ).GetToken().equals( "--" ) ) {
        m_step += 1;
        if ( m_statement.get( m_step ).GetType() == 4 ) { // ID
          m_step += 1;
          if ( m_statement.get( m_step ).GetToken().equals( "[" ) ) { // 可能會out of range
            m_step += 1;
            if ( Expression() ) {
              m_step += 1;
              if ( m_statement.get( m_step ).GetToken().equals( "]" ) ) {
                return true;
              } // if
              else {
                return false;
              } // else
            } // if
            else {
              return false;
            } // else
          } // if
          return true;
        } // if
        else {
          return false;
        } // else
      } // else if
      else {
        return false;
      } // else
    } // try
    catch ( Throwable throwable ) {
      return false;
    } // catch
  } // Unary_Exp()

  private boolean Signed_Unary_Exp() throws Throwable {
    try {
      if ( m_statement.get( m_step ).GetType() == 4 ) { // ID
        m_step += 1; // maybe will out of range
        if ( m_statement.get( m_step ).GetToken().equals( "(" ) ) {
          m_step += 1;
          if ( Actual_Parameter_List() ) {
            m_step += 1;
            if ( m_statement.get( m_step ).GetToken().equals( ")" ) ) {
              return true;
            } // if
            else {
              return false;
            } // else
          } // if
          else {
            return false;
          } // else
        } // if
        else if ( m_statement.get( m_step ).GetToken().equals( "[" ) ) {
          m_step += 1;
          if ( Expression() ) {
            m_step += 1;
            if ( m_statement.get( m_step ).GetToken().equals( "]" ) ) {
              return true;
            } // if
            else {
              return false;
            } // else
          } // if
          else {
            return false;
          } // else
        } // else if

        return true;
      } // if
      else if ( m_statement.get( m_step ).GetType() == 13 ) { // Constant
        return true;
      } // else if
      else if ( m_statement.get( m_step ).GetToken().equals( "(" ) ) {
        m_step += 1;
        if ( Expression() ) {
          m_step += 1;
          if ( m_statement.get( m_step ).GetToken().equals( ")" ) ) {
            return true;
          } // if
          else {
            return false;
          } // else
        } // if
        else {
          return false;
        } // else
      } // else if
      else {
        return false;
      } // else
    } // try
    catch ( Throwable throwable ) {
      return false;
    } // catch()
  } // Signed_Unary_Exp()

  private boolean Unsigned_Unary_Exp() throws Throwable {
    try {
      if ( m_statement.get( m_step ).GetType() == 4 ) { // ID
        m_step += 1; // maybe will out of range
        if ( m_statement.get( m_step ).GetToken().equals( "(" ) ) {
          m_step += 1;
          if ( Actual_Parameter_List() ) {
            m_step += 1;
            if ( m_statement.get( m_step ).GetToken().equals( ")" ) ) {
              return true;
            } // if
            else {
              return false;
            } // else
          } // if
          else {
            return false;
          } // else
        } // if
        else if ( m_statement.get( m_step ).GetToken().equals( "[" ) ) {
          m_step += 1;
          if ( Expression() ) {
            m_step += 1;
            if ( m_statement.get( m_step ).GetToken().equals( "]" ) ) {
              m_step += 1; // maybe will out of range
              if ( m_statement.get( m_step ).GetToken().equals( "++" )
                   || m_statement.get( m_step ).GetToken().equals( "--" ) ) {
                return true;
              } // if
              return true;
            } // if
            else {
              return false;
            } // else
          } // if
          else {
            return false;
          } // else
        } // else if

        return true;
      } // if
      else if ( m_statement.get( m_step ).GetType() == 13 ) { // Constant
        return true;
      } // else if
      else if ( m_statement.get( m_step ).GetToken().equals( "(" ) ) {
        m_step += 1;
        if ( Expression() ) {
          m_step += 1;
          if ( m_statement.get( m_step ).GetToken().equals( ")" ) ) {
            return true;
          } // if
          else {
            return false;
          } // else
        } // if
        else {
          return false;
        } // else
      } // else if
      else {
        return false;
      } // else
    } // try
    catch ( Throwable throwable ) {
      return false;
    } // catch()
  } // Signed_Unary_Exp()
} // class Parser

class Main {

  public static void main( String[] args ) throws Throwable {
    // Test test = new Test();
    // test.test();

  } // main()

} // class Main