package PL110_10627153;

import java.util.Scanner;
import java.util.Vector;

class Global {
  // Blow is Token Type
  static final int s_T_ID = 1;
  static final int s_T_CONSTANT = 2;
  static final int s_T_TYPE = 9; // int, String, float, char, bool
  static final int s_T_VOID = 10; // void
  static final int s_T_IF = 11;
  static final int s_T_ELSE = 12;
  static final int s_T_WHILE = 13;
  static final int s_T_DO = 14;
  static final int s_T_RETURN = 15;
  static final int s_T_SMALL_LEFT_PAREN = 3; // (
  static final int s_T_SMALL_RIGHT_PAREN = 4; // )
  static final int s_T_MID_LEFT_PAREN = 5; // [
  static final int s_T_MID_RIGHT_PAREN = 6; // ]
  static final int s_T_BIG_LEFT_PAREN = 7; // {
  static final int s_T_BIG_RIGHT_PAREN = 8; // }
  static final int s_T_OPERATOR = 16; // +, -, *, /, %, >>, <<
  static final int s_T_BOOLEANRELATIONAL = 17; // >, <, >=, <=, ==, !=
  static final int s_T_ASSIGN = 18; // =
  static final int s_T_BOOLEANCONDITION = 19; // !, &&, ||
  static final int s_T_ASSIGNOPERATOR = 20; // +=, -=, *=, /=, %=, ++, --
  static final int s_T_SEMICOLON = 21; // ;
  static final int s_T_COMMA = 22; // ,
  static final int s_T_TERNARYOPERATOR = 23; // ?:

  // Blow is Variable Type
  static final int s_V_INT = 1;
  static final int s_V_STRING = 2;
  static final int s_V_FLOAT = 3;
  static final int s_V_CHAR = 4;
  static final int s_V_BOOL = 5;

  static public int s_NOWLINE = 0;
  static public Scanner sc = new Scanner( System.in );

  static public Vector<VarString> s_Variables = new Vector<VarString>();

  static public Vector<Function> s_Functions = new Vector<Function>();

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

  public static Variable G_FindVariable( Vector<VarString> vList, String vName ) throws Throwable {
    for ( int r = vList.size() - 1 ; r >= 0 ; r-- ) {
      for ( int i = 0 ; i < vList.get( r ).m_Var.size() ; i++ ) {
        if ( vList.get( r ).m_Var.get( i ).GetName().equals( vName ) )
          return vList.get( r ).m_Var.get( i );
      } // for()

    } // for

    return null;
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
  private int m_line;

  public TOKEN( String input, int defineType, int m_line ) throws Throwable {
    m_token = new String( input );
    m_type = defineType;
    this.m_line = m_line;
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

class TokenString {
  public Vector<TOKEN> m_tokenString;

  public TokenString() throws Throwable {
    m_tokenString = new Vector<TOKEN>();
  } // TokenLine()

} // class TokenList

class Stament {
  private Vector<TokenString> m_Line;
  private int m_Size;

  public Stament() throws Throwable {
    m_Size = 0;
  } // Stament()

  public TOKEN GetToken( int index ) throws Throwable {
    for ( int i = 0 ; i < m_Line.size() ; i++ ) {
      for ( int j = 0 ; j < m_Line.get( i ).m_tokenString.size() ; j++ ) {
        if ( index == 0 )
          return m_Line.get( i ).m_tokenString.get( j );
        index--;
      } // for count the amount of token
    } // for count the amount of rows

    throw new Throwable();
  } // GetToken()

  public TokenString GetTokenRow( int index ) throws Throwable {
    return null;
  } // GetTokenRow()

  public void PushLine( TokenString line ) throws Throwable {
    m_Line.add( line );
    m_Size = m_Size + line.m_tokenString.size();
  } // PushLine()

  public void PushToken( TOKEN token ) throws Throwable {
    if ( token != null ) {
      m_Line.get( m_Line.size() ).m_tokenString.add( token );
      m_Size = m_Size + 1;
    } // if
  } // PushToken()

  public void AddLine() throws Throwable {
    m_Line.add( new TokenString() );
  } // AddLine()

  public int Size() throws Throwable {
    return m_Size;
  } // GetSize()

} // class Stamen

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

class VarString {
  public Vector<Variable> m_Var;
} // class LocalVar

class Function {
  private String m_name;
  private Vector<VarString> m_localVarList;
  private Vector<Stament> m_commendLine;


  public Function( String fName, Vector<VarString> lVar, Vector<Stament> stament ) throws Throwable {
    this.m_name = new String( fName );
    this.m_localVarList = lVar;
    this.m_commendLine = stament;
  } // Function()

  public String GetName() throws Throwable {
    return this.m_name;
  } // GetName()

} // class Function

class CutToken {

  private Vector<TOKEN> mBuffer;
  private Vector<Variable> mVariables;
  private int mLineCount;
  private String mnowLine;
  private Scanner msc;

  public CutToken( Scanner inputSc, Vector<Variable> iV ) throws Throwable {

    mBuffer = new Vector<TOKEN>();
    msc = inputSc;
    mVariables = iV;
    mnowLine = new String();
    mLineCount = 0;

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
          mBuffer.add( new TOKEN( "(", Global.s_T_SMALL_LEFT_PAREN, mLineCount ) );
          IsGotTokenProcessFormNowLine( 1 );
          SMALLLEFTPARENFINDERROR();
        } // if
        else if ( mnowLine.charAt( 0 ) == ')' ) {
          mBuffer.add( new TOKEN( ")", Global.s_T_SMALL_RIGHT_PAREN, mLineCount ) );
          IsGotTokenProcessFormNowLine( 1 );
          SMALLRIGHTPARENFINDERROR();
        } // else if
        else if ( mnowLine.charAt( 0 ) == '[' ) {
          mBuffer.add( new TOKEN( "[", Global.s_T_MID_LEFT_PAREN, mLineCount ) );
          IsGotTokenProcessFormNowLine( 1 );
          MIDLEFTPARENFINDERROR();
        } // else if
        else if ( mnowLine.charAt( 0 ) == ']' ) {
          mBuffer.add( new TOKEN( "]", Global.s_T_MID_RIGHT_PAREN, mLineCount ) );
          IsGotTokenProcessFormNowLine( 1 );
          MIDRIGHTPARENFINDERROR();
        } // else if
        else if ( mnowLine.charAt( 0 ) == '{' ) {
          mBuffer.add( new TOKEN( "{", Global.s_T_BIG_LEFT_PAREN, mLineCount ) );
          IsGotTokenProcessFormNowLine( 1 );
        } // else if
        else if ( mnowLine.charAt( 0 ) == '}' ) {
          mBuffer.add( new TOKEN( "}", Global.s_T_BIG_RIGHT_PAREN, mLineCount ) );
          IsGotTokenProcessFormNowLine( 1 );
        } // else if
        else if ( mnowLine.charAt( 0 ) == ',' ) {
          mBuffer.add( new TOKEN( ",", Global.s_T_COMMA, mLineCount ) );
          IsGotTokenProcessFormNowLine( 1 );
        } // else if
        else if ( mnowLine.charAt( 0 ) == '?' || mnowLine.charAt( 0 ) == ':' ) {
          mBuffer.add( new TOKEN( mnowLine.substring( 0, 1 ), Global.s_T_TERNARYOPERATOR, mLineCount ) );
          IsGotTokenProcessFormNowLine( 1 );
          TERNARYOPERATORFINDERROR();
        } // else if
        else if ( mnowLine.charAt( 0 ) == ';' ) {
          mBuffer.add( new TOKEN( ";", Global.s_T_SEMICOLON, mLineCount ) );
          IsGotTokenProcessFormNowLine( 1 );
          notGetSEMICOLON = false;
          if ( Buffer1HasFullCommend( stament ) )
            return true;
        } // else if
        else if ( mnowLine.charAt( 0 ) == '=' ) {
          mBuffer.add( new TOKEN( mnowLine.substring( 0, 1 ), Global.s_T_ASSIGN, mLineCount ) );
          IsGotTokenProcessFormNowLine( 1 );
        } // else if
        else if ( IsASSISNOPERATORInmNowLineFirst() ) {
          String gotOPERATOR = GetOPERATORToken();
          mBuffer.add( new TOKEN( gotOPERATOR, 5, mLineCount ) );
          IsGotTokenProcessFormNowLine( gotOPERATOR.length() );
          OPERATORFINDERROR( gotOPERATOR );
        } // else if
        else if ( IsOPERATORInmNowLineFirst() ) {
          String gotOPERATOR = GetOPERATORToken();
          mBuffer.add( new TOKEN( gotOPERATOR, 5, mLineCount ) );
          IsGotTokenProcessFormNowLine( gotOPERATOR.length() );
          OPERATORFINDERROR( gotOPERATOR );
        } // else if
        else if ( IsNUMInmNowLineFirst() ) {
          String gotNUM;
          gotNUM = GetNUMTokenInmNowLine();

          if ( mBuffer.size() > 1 ) {
            if ( mBuffer.get( mBuffer.size() - 1 ).GetType() == Global.s_T_CONSTANT ) {
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
            if ( mBuffer.get( mBuffer.size() - 2 ).GetType() == Global.s_T_BOOLEANRELATIONAL ) {
              System.out.println( "Unexpected token : '"
                                  + mBuffer.get( mBuffer.size() - 1 ).GetToken() + "'" );
              System.out.print( "> " );
              ProcessBeforSEMICOLONFormNowLine(); // mBuffer.clear() ;
              throw new Throwable();
            } // if

          } // if

          if ( mBuffer.size() == 1 ) {
            if ( mBuffer.get( 0 ).GetType() == Global.s_T_BOOLEANRELATIONAL ) {
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

    if ( Buffer1HasFullCommend( stament ) )
      return true;

    return true;

  } // Cutting()

  private void OPERATORFINDERROR( String gotOPERATOR ) throws Throwable {
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

        if ( Global.G_FindVariable( Global.s_Variables,
                                    mBuffer.get( mBuffer.size() - 2 ).GetToken() ) == null ) {
          System.out.println( "Undefined identifier : '"
                              + mBuffer.get( mBuffer.size() - 2 ).GetToken() + "'" );
          System.out.print( "> " );
          ProcessBeforSEMICOLONFormNowLine(); // mBuffer.clear() ;
          throw new Throwable();
        } // if

      } // if

    } // if

  } // OPERATORFINDERROR()

  private void TERNARYOPERATORFINDERROR() throws Throwable {
    int count = 0;
    for ( int i = 0 ; i < mBuffer.size() ; i++ ) {
      if ( mBuffer.get( i ).GetToken().equals( "?" ) )
        count = count + 1;
      else if ( mBuffer.get( i ).GetToken().equals( ":" ) )
        count = count - 1;

    } // for

    if ( count < 0 ) {
      System.out.println( "Unexpected token : '"
                          + mBuffer.get( mBuffer.size() - 1 ).GetToken() + "'" );
      System.out.print( "> " );
      ProcessBeforSEMICOLONFormNowLine(); // mBuffer.clear() ;
      throw new Throwable();
    } // if

  } // TERNARYOPERATORFINDERROR()

  private void SMALLLEFTPARENFINDERROR() throws Throwable {
    if ( mBuffer.size() > 1 ) {
      if ( mBuffer.get( mBuffer.size() - 2 ).GetType() == Global.s_T_ID ) {

        if ( Global.G_FindVariable( Global.s_Variables, mBuffer.get( mBuffer.size() - 2 ).GetToken() ) ==
             null ) {
          System.out.println( "Undefined identifier : '"
                              + mBuffer.get( mBuffer.size() - 2 ).GetToken() + "'" );
          System.out.print( "> " );
          ProcessBeforSEMICOLONFormNowLine(); // mBuffer.clear() ;
          throw new Throwable();
        } // if

      } // if

    } // if

  } // SMALLLEFTPARENFINDERROR()

  private void SMALLRIGHTPARENFINDERROR() throws Throwable {

    if ( mBuffer.size() > 1 ) {
      if ( mBuffer.get( mBuffer.size() - 2 ).GetType() == Global.s_T_ID ) {

        if ( Global.G_FindVariable( Global.s_Variables, mBuffer.get( mBuffer.size() - 2 ).GetToken() )
             == null ) {
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
      if ( mBuffer.get( i ).GetType() == Global.s_T_SMALL_LEFT_PAREN )
        count = count + 1;
      else if ( mBuffer.get( i ).GetType() == Global.s_T_SMALL_RIGHT_PAREN )
        count = count - 1;

    } // for

    if ( count < 0 ) {
      System.out.println( "Unexpected token : '"
                          + mBuffer.get( mBuffer.size() - 1 ).GetToken() + "'" );
      System.out.print( "> " );
      ProcessBeforSEMICOLONFormNowLine(); // mBuffer.clear() ;
      throw new Throwable();
    } // if

  } // SMALLRIGHTPARENFINDERROR()

  private void MIDLEFTPARENFINDERROR() throws Throwable {
    if ( mBuffer.size() > 1 ) {
      if ( mBuffer.get( mBuffer.size() - 2 ).GetType() == Global.s_T_ID ) {

        if ( Global.G_FindVariable( Global.s_Variables, mBuffer.get( mBuffer.size() - 2 ).GetToken() ) ==
             null ) {
          System.out.println( "Undefined identifier : '"
                              + mBuffer.get( mBuffer.size() - 2 ).GetToken() + "'" );
          System.out.print( "> " );
          ProcessBeforSEMICOLONFormNowLine(); // mBuffer.clear() ;
          throw new Throwable();
        } // if

      } // if

    } // if

  } // MIDLEFTPARENFINDERROR()

  private void MIDRIGHTPARENFINDERROR() throws Throwable {

    if ( mBuffer.size() > 1 ) {
      if ( mBuffer.get( mBuffer.size() - 2 ).GetType() == Global.s_T_ID ) {

        if ( Global.G_FindVariable( Global.s_Variables, mBuffer.get( mBuffer.size() - 2 ).GetToken() )
             == null ) {
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
      if ( mBuffer.get( i ).GetType() == Global.s_T_MID_LEFT_PAREN )
        count = count + 1;
      else if ( mBuffer.get( i ).GetType() == Global.s_T_MID_RIGHT_PAREN )
        count = count - 1;

    } // for

    if ( count < 0 ) {
      System.out.println( "Unexpected token : '"
                          + mBuffer.get( mBuffer.size() - 1 ).GetToken() + "'" );
      System.out.print( "> " );
      ProcessBeforSEMICOLONFormNowLine(); // mBuffer.clear() ;
      throw new Throwable();
    } // if

  } // MIDRIGHTPARENFINDERROR()

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

  private String GetOPERATORToken() throws Throwable {
    char charStr = mnowLine.charAt( 0 );
    if ( charStr == '+' || charStr == '-' || charStr == '*' || charStr == '/' || charStr == '%' )
      return mnowLine.substring( 0,1 );

    if ( charStr == '>' || charStr == '<')
      return mnowLine.substring( 0,1 );

    return new String();
  } // GetOPERATORToken()

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

    if ( charStr == '+' || charStr == '-' )
      return true;

    if ( charStr == '*' || charStr == '/' || charStr == '%' )
      return true;

    if ( charStr == '>' ) {
      try {
        char secCharStr = mnowLine.charAt( 0 );
        if ( secCharStr == '>' )
          return true;
        else
          return false;
      } // try
      catch ( Throwable throwable ) {
        return false;
      } // catch

    } // if

    if ( charStr == '<' ) {
      try {
        char secCharStr = mnowLine.charAt( 0 );
        if ( secCharStr == '<' )
          return true;
        else
          return false;
      } // try
      catch ( Throwable throwable ) {
        return false;
      } // catch

    } // if

    return false;

  } // IsOPERATORInmNowLineFirst()

  private boolean IsASSISNOPERATORInmNowLineFirst() throws Throwable {
    char charStr = mnowLine.charAt( 0 );

    if ( charStr == '>' ) {
      try {
        char secCharStr = mnowLine.charAt( 0 );
        if ( secCharStr == '>' )
          return true;
        else
          return false;
      } // try
      catch ( Throwable throwable ) {
        return false;
      } // catch

    } // if

    if ( charStr == '<' ) {
      try {
        char secCharStr = mnowLine.charAt( 0 );
        if ( secCharStr == '<' )
          return true;
        else
          return false;
      } // try
      catch ( Throwable throwable ) {
        return false;
      } // catch

    } // if

    return false;

  } // IsASSISNOPERATORInmNowLineFirst()

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

class Main {

  public static void main( String[] args ) throws Throwable {
    // Test test = new Test();
    // test.test();

  } // main()

} // class Main