package PL110_10627153;
// 20220613 10:40

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
  static final int s_T_OURCCOMMAND = 24;

  // Blow is Variable Type
  static final int s_V_INT = 1;
  static final int s_V_STRING = 2;
  static final int s_V_FLOAT = 3;
  static final int s_V_CHAR = 4;
  static final int s_V_BOOL = 5;

  static public int s_NOWLINE = 0;

  static public Scanner sc = new Scanner( System.in );

  static public Vector<VarList> s_Variables = new Vector<VarList>();

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

  public static void G_AddVariable( Variable var ) throws Throwable {
    int buttonList = s_Variables.size();
    s_Variables.get( buttonList - 1 ).m_Var.add( var );
  } // G_AddVariable()

  public static Variable G_FindVariable( Vector<VarList> vList, String vName ) throws Throwable {
    for ( int r = vList.size() - 1 ; r >= 0 ; r-- ) {
      for ( int i = 0 ; i < vList.get( r ).m_Var.size() ; i++ ) {
        if ( vList.get( r ).m_Var.get( i ).GetName().equals( vName ) )
          return vList.get( r ).m_Var.get( i );
      } // for()

    } // for

    return null;
  } // G_FindVariable()

  public static Function G_FindFunction( Vector<Function> fList, String fName ) throws Throwable {

    for ( int i = 0 ; i < fList.size() ; i++ ) {
      if ( fList.get( i ).GetName().equals( fName ) )
        return fList.get( i );

    } // for

    return null;
  } // G_FindFunction()

  public static boolean G_IDHASDEFINED( String fName ) throws Throwable {
    if ( G_FindVariable( s_Variables, fName ) != null )
      return true;
    if ( G_FindFunction( s_Functions, fName ) != null )
      return true;

    return false;
  } // G_IDHASDEFINED()

  public static void G_OurCInitialize() throws Throwable {
    s_Functions.add( new Function( "Done", new Vector<VarList>(), new Vector<Stament>() ) );
    s_Variables.add( new VarList() );
    // G_AddVariable( new VarINT( s_V_INT, "i" ) );
    // G_AddVariable( new VarINT( s_V_INT, "x" ) );
    s_Functions.add( new Function( "cout", new Vector<VarList>(), new Vector<Stament>() ) );
    s_Functions.add( new Function( "cin", new Vector<VarList>(), new Vector<Stament>() ) );

  } // G_OurCInitialize()

} // class Global

class TOKEN {
  private String m_token;
  private int m_type;
  private int m_line;

  public TOKEN( String input, int defineType, int m_line ) throws Throwable {
    m_token = new String( input );
    m_type = defineType;
    this.m_line = m_line;
  } // TOKEN()

  public String GetToken() throws Throwable {
    return m_token;
  } // GetToken()

  public int GetType() throws Throwable {
    return m_type;
  } // GetType()

  public int Getline() throws Throwable {
    return m_line;
  } // Getline()

  public void SetToken( String token ) throws Throwable {
    m_token = new String( token );
  } // SetToken()

} // class TOKEN

class TokenString {
  public Vector<TOKEN> m_tokenString;

  public TokenString() throws Throwable {
    m_tokenString = new Vector<TOKEN>();
  } // TokenString()

} // class TokenString

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
  } // Size()

} // class Stament

abstract class Variable {

  private String m_Name;
  private int m_Type;
  protected int m_arraySize;

  public Variable( int type, String name ) throws Throwable {
    this.m_Name = new String( name );
    this.m_Type = type;

  } // Variable()

  public String GetName() throws Throwable {

    return this.m_Name;

  } // GetName()

  public int GetType() throws Throwable {

    return m_Type;

  } // GetType()

  public boolean SetType( int type ) throws Throwable {
    if ( m_Type != type ) {
      m_Type = type;
      return true;
    } // if
    else {
      return false;
    } // else

  } // SetType()

  public abstract String GetValue() throws Throwable; // VarType()

} // class Variable

class VarINT extends Variable {

  private Integer m_value = null;

  public VarINT( int type, String name ) throws Throwable {
    super( type, name );
  } // VarINT()

  public String GetValue() throws Throwable {
    if ( m_value == null )
      throw new Throwable();
    else
      return m_value.toString();
  } // GetValue()

  public int GetINTValue() throws Throwable {
    if ( m_value == null )
      throw new Throwable();
    else
      return this.m_value.intValue();
  } // GetINTValue()

} // class VarINT

class VarFLOT extends Variable {

  private Float m_value;

  public VarFLOT( int type, String name, boolean isNull ) throws Throwable {
    super( type, name );
  } // VarFLOT()

  public String GetValue() throws Throwable {
    if ( m_value == null )
      throw new Throwable();
    else
      return m_value.toString();
  } // GetValue()

  public float GetFLOATEValue() throws Throwable {
    if ( m_value == null )
      throw new Throwable();
    else
      return this.m_value.floatValue();
  } // GetFLOATEValue()

} // class VarFLOT

class VarSTRING extends Variable {

  private String m_value;

  public VarSTRING( int type, String name, boolean isNull ) throws Throwable {
    super( type, name );
  } // VarSTRING()

  public String GetValue() throws Throwable {
    if ( m_value == null )
      throw new Throwable();
    else
      return m_value;
  } // GetValue()

  public String GetSTRINGValue() throws Throwable {
    if ( m_value == null )
      throw new Throwable();
    else
      return this.m_value;
  } // GetSTRINGValue()

} // class VarSTRING

class VarCHAR extends Variable {
  private String m_value;

  public VarCHAR( int type, String name, boolean isNull ) throws Throwable {
    super( type, name );
  } // VarCHAR()

  public String GetValue() throws Throwable {
    if ( m_value == null )
      throw new Throwable();
    else
      return Character.toString( m_value.charAt( 0 ) );
  } // GetValue()

  public String GetValue( int index ) throws Throwable {
    if ( m_value == null )
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
    } // else

  } // GetValue()

  public char GetCHARValue( int index ) throws Throwable {
    if ( m_value == null )
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
    } // else

  } // GetCHARValue()

} // class VarCHAR

class VarBOOL extends Variable {

  private Boolean m_value;

  public VarBOOL( int type, String name, boolean isNull ) throws Throwable {
    super( type, name );
  } // VarBOOL()

  public String GetValue() throws Throwable {
    if ( m_value == null )
      throw new Throwable();
    else
      return m_value.toString();
  } // GetValue()

  public boolean GetBOOLValue() throws Throwable {
    if ( m_value == null )
      throw new Throwable();
    else
      return this.m_value.booleanValue();
  } // GetBOOLValue()

} // class VarBOOL

class VarList {
  public Vector<Variable> m_Var = new Vector<Variable>();
} // class VarList

class Function {
  private String m_name;
  private Vector<VarList> m_localVarList;
  private Vector<Stament> m_commendLine;


  public Function( String fName, Vector<VarList> lVar, Vector<Stament> stament ) throws Throwable {
    this.m_name = new String( fName );
    this.m_localVarList = lVar;
    this.m_commendLine = stament;
  } // Function()

  public String GetName() throws Throwable {
    return this.m_name;
  } // GetName()

} // class Function

class CutToken {

  protected Vector<TOKEN> mBuffer;
  private Vector<TOKEN> mBuffer2;
  protected int mLineCount;
  protected String mnowLine;
  protected Scanner msc;

  public CutToken() throws Throwable {

    mBuffer = new Vector<TOKEN>();
    msc = Global.sc;
    // mVariables = iV;
    mnowLine = new String();
    mLineCount = 0;
    mBuffer2 = null;

  } // CutToken()

  public boolean Cutting( Vector<TOKEN> stament ) throws Throwable {
    boolean notGetSEMICOLON = true;
    if ( mnowLine.length() == 0 || mnowLine == null )
      mLineCount = 0;

    System.out.print( "> " );

    if ( mBuffer2 != null )
      if ( ReturnBuffer2Stament( stament ) )
        return true;

    if ( mnowLine.isEmpty() ) {
      InputNextLineTomNowLine();
    } // if

    while ( notGetSEMICOLON ) {
      if ( mnowLine.isEmpty() ) {
        InputNextLineTomNowLine();
      } // if

      try {
        if ( mnowLine.charAt( 0 ) == '(' ) {
          mBuffer.add( new TOKEN( "(", Global.s_T_SMALL_LEFT_PAREN, mLineCount ) );
          IsGotTokenProcessFormNowLine( 1 );
          // SMALLLEFTPARENFINDERROR();
        } // if
        else if ( mnowLine.charAt( 0 ) == ')' ) {
          mBuffer.add( new TOKEN( ")", Global.s_T_SMALL_RIGHT_PAREN, mLineCount ) );
          IsGotTokenProcessFormNowLine( 1 );
          SMALLRIGHTPARENFINDERROR();
          if ( ISFUNDEFORIFWHILEEND() )
            return Buffer1HasFullCommend( stament );
        } // else if
        else if ( mnowLine.charAt( 0 ) == '[' ) {
          mBuffer.add( new TOKEN( "[", Global.s_T_MID_LEFT_PAREN, mLineCount ) );
          IsGotTokenProcessFormNowLine( 1 );
          // MIDLEFTPARENFINDERROR();
        } // else if
        else if ( mnowLine.charAt( 0 ) == ']' ) {
          mBuffer.add( new TOKEN( "]", Global.s_T_MID_RIGHT_PAREN, mLineCount ) );
          IsGotTokenProcessFormNowLine( 1 );
          MIDRIGHTPARENFINDERROR();
        } // else if
        else if ( mnowLine.charAt( 0 ) == '{' ) {
          mBuffer.add( new TOKEN( "{", Global.s_T_BIG_LEFT_PAREN, mLineCount ) );
          IsGotTokenProcessFormNowLine( 1 );
          HASOTHERTOKENISERROR();
          stament.add( mBuffer.get( 0 ) );
          mBuffer.clear();
          return true;
        } // else if
        else if ( mnowLine.charAt( 0 ) == '}' ) {
          mBuffer.add( new TOKEN( "}", Global.s_T_BIG_RIGHT_PAREN, mLineCount ) );
          IsGotTokenProcessFormNowLine( 1 );
          HASOTHERTOKENISERROR();
          stament.add( mBuffer.get( 0 ) );
          mBuffer.clear();
          return true;
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
        else if ( IsBOOLEANCONDITION1InmNowLineFirst() ) {
          String gotBOOLEANCONDITION;
          gotBOOLEANCONDITION = mnowLine.substring( 0, 2 );
          mBuffer.add( new TOKEN( gotBOOLEANCONDITION, Global.s_T_BOOLEANCONDITION, mLineCount ) );
          IsGotTokenProcessFormNowLine( gotBOOLEANCONDITION.length() );
        } // else if
        else if ( IsASSISNOPERATORInmNowLineFirst() ) {
          String gotASSISNOPERATOR = mnowLine.substring( 0, 2 );
          mBuffer.add( new TOKEN( gotASSISNOPERATOR, Global.s_T_ASSIGNOPERATOR, mLineCount ) );
          IsGotTokenProcessFormNowLine( gotASSISNOPERATOR.length() );
        } // else if
        else if ( IsOPERATORInmNowLineFirst() ) {
          String gotOPERATOR = GetOPERATORToken();
          mBuffer.add( new TOKEN( gotOPERATOR, Global.s_T_OPERATOR, mLineCount ) );
          IsGotTokenProcessFormNowLine( gotOPERATOR.length() );
          OPERATORFINDERROR( gotOPERATOR );
        } // else if
        else if ( IsBOOLEANRELATIONALInmNowLineFirst() ) {
          String gotBOOLEANRELATIONAL = GetBOOLEANRELATIONALToken();
          mBuffer.add( new TOKEN( gotBOOLEANRELATIONAL, Global.s_T_BOOLEANRELATIONAL, mLineCount ) );
          IsGotTokenProcessFormNowLine( gotBOOLEANRELATIONAL.length() );
          OPERATORFINDERROR( gotBOOLEANRELATIONAL );
        } // else if
        else if ( mnowLine.charAt( 0 ) == '!' ) {
          mBuffer.add( new TOKEN( mnowLine.substring( 0, 1 ),
                                  Global.s_T_BOOLEANCONDITION, mLineCount ) );
          IsGotTokenProcessFormNowLine( 1 );
        } // else if
        else if ( mnowLine.charAt( 0 ) == '=' ) {
          mBuffer.add( new TOKEN( mnowLine.substring( 0, 1 ), Global.s_T_ASSIGN, mLineCount ) );
          IsGotTokenProcessFormNowLine( 1 );
        } // else if
        else if ( IsCONSTANTInmNowLineFirst() ) {
          String gotCONSTANT;
          gotCONSTANT = GetCONSTANTTokenInmNowLine();
          mBuffer.add( new TOKEN( gotCONSTANT, Global.s_T_CONSTANT, mLineCount ) );
          CONSTANTFINDERROR( gotCONSTANT );
        } // else if
        else if ( IsIDInmNowLineFirst() ) {
          String gotID;
          gotID = GetIDTOETokenInmNowLine();
          DISTINGUISHANDPUSHTOKEN( gotID );
          IDFINDERROR();
          if ( gotID.equals( "do" ) || gotID.equals( "else" ) ) {
            HASOTHERTOKENISERROR();
            stament.add( mBuffer.get( 0 ) );
            mBuffer.clear();
            return true;
          } // if

        } // else if
        else {
          if ( ! mnowLine.isEmpty() ) {
            System.out.println( "Line " + mLineCount + " : " + "Unrecognized token with first char : '" +
                                mnowLine.charAt( 0 ) + "'" );
            // System.out.print( "> " );
            mBuffer.clear();
            throw new Throwable();
          } // if

        } // else

      }
      catch ( Throwable throwable ) {

        // System.out.print( "> " );
        mnowLine = new String();
        return false;

      } // catch

    } // while

    if ( Buffer1HasFullCommend( stament ) )
      return true;

    return true;

  } // Cutting()

  public boolean GetStament( Vector<TOKEN> stament ) throws Throwable {

    boolean notGetSEMICOLON = true;

    if ( Buffer1HasFullCommend( stament ) ) {
      // System.out.print( "> " );
      return true;
    } // if
    else {
      // System.out.print( "> " );
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
          // SMALLLEFTPARENFINDERROR();
        } // if
        else if ( mnowLine.charAt( 0 ) == ')' ) {
          mBuffer.add( new TOKEN( ")", Global.s_T_SMALL_RIGHT_PAREN, mLineCount ) );
          IsGotTokenProcessFormNowLine( 1 );
          SMALLRIGHTPARENFINDERROR();
          if ( ISFUNDEFORIFWHILEEND() )
            return Buffer1HasFullCommend( stament );
        } // else if
        else if ( mnowLine.charAt( 0 ) == '[' ) {
          mBuffer.add( new TOKEN( "[", Global.s_T_MID_LEFT_PAREN, mLineCount ) );
          IsGotTokenProcessFormNowLine( 1 );
          // MIDLEFTPARENFINDERROR();
        } // else if
        else if ( mnowLine.charAt( 0 ) == ']' ) {
          mBuffer.add( new TOKEN( "]", Global.s_T_MID_RIGHT_PAREN, mLineCount ) );
          IsGotTokenProcessFormNowLine( 1 );
          MIDRIGHTPARENFINDERROR();
        } // else if
        else if ( mnowLine.charAt( 0 ) == '{' ) {
          mBuffer.add( new TOKEN( "{", Global.s_T_BIG_LEFT_PAREN, mLineCount ) );
          IsGotTokenProcessFormNowLine( 1 );
          HASOTHERTOKENISERROR();
          stament.add( mBuffer.get( 0 ) );
          mBuffer.clear();
          return true;
        } // else if
        else if ( mnowLine.charAt( 0 ) == '}' ) {
          mBuffer.add( new TOKEN( "}", Global.s_T_BIG_RIGHT_PAREN, mLineCount ) );
          IsGotTokenProcessFormNowLine( 1 );
          HASOTHERTOKENISERROR();
          stament.add( mBuffer.get( 0 ) );
          mBuffer.clear();
          return true;
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
        else if ( IsBOOLEANCONDITION1InmNowLineFirst() ) {
          String gotBOOLEANCONDITION;
          gotBOOLEANCONDITION = mnowLine.substring( 0, 2 );
          mBuffer.add( new TOKEN( gotBOOLEANCONDITION, Global.s_T_BOOLEANCONDITION, mLineCount ) );
          IsGotTokenProcessFormNowLine( gotBOOLEANCONDITION.length() );
        } // else if
        else if ( IsASSISNOPERATORInmNowLineFirst() ) {
          String gotASSISNOPERATOR = mnowLine.substring( 0, 2 );
          mBuffer.add( new TOKEN( gotASSISNOPERATOR, Global.s_T_ASSIGNOPERATOR, mLineCount ) );
          IsGotTokenProcessFormNowLine( gotASSISNOPERATOR.length() );
        } // else if
        else if ( IsOPERATORInmNowLineFirst() ) {
          String gotOPERATOR = GetOPERATORToken();
          mBuffer.add( new TOKEN( gotOPERATOR, Global.s_T_OPERATOR, mLineCount ) );
          IsGotTokenProcessFormNowLine( gotOPERATOR.length() );
          OPERATORFINDERROR( gotOPERATOR );
        } // else if
        else if ( IsBOOLEANRELATIONALInmNowLineFirst() ) {
          String gotBOOLEANRELATIONAL = GetBOOLEANRELATIONALToken();
          mBuffer.add( new TOKEN( gotBOOLEANRELATIONAL, Global.s_T_BOOLEANRELATIONAL, mLineCount ) );
          IsGotTokenProcessFormNowLine( gotBOOLEANRELATIONAL.length() );
          OPERATORFINDERROR( gotBOOLEANRELATIONAL );
        } // else if
        else if ( mnowLine.charAt( 0 ) == '!' ) {
          mBuffer.add( new TOKEN( mnowLine.substring( 0, 1 ),
                                  Global.s_T_BOOLEANCONDITION, mLineCount ) );
          IsGotTokenProcessFormNowLine( 1 );
        } // else if
        else if ( mnowLine.charAt( 0 ) == '=' ) {
          mBuffer.add( new TOKEN( mnowLine.substring( 0, 1 ), Global.s_T_ASSIGN, mLineCount ) );
          IsGotTokenProcessFormNowLine( 1 );
        } // else if
        else if ( IsCONSTANTInmNowLineFirst() ) {
          String gotCONSTANT;
          gotCONSTANT = GetCONSTANTTokenInmNowLine();
          mBuffer.add( new TOKEN( gotCONSTANT, Global.s_T_CONSTANT, mLineCount ) );
          CONSTANTFINDERROR( gotCONSTANT );
        } // else if
        else if ( IsIDInmNowLineFirst() ) {
          String gotID;
          gotID = GetIDTOETokenInmNowLine();
          DISTINGUISHANDPUSHTOKEN( gotID );
          IDFINDERROR();
          if ( gotID.equals( "do" ) || gotID.equals( "else" ) ) {
            HASOTHERTOKENISERROR();
            stament.add( mBuffer.get( 0 ) );
            mBuffer.clear();
            return true;
          } // if

        } // else if
        else {
          if ( ! mnowLine.isEmpty() ) {
            System.out.println( "Line " + mLineCount + " : " + "Unrecognized token with first char : '" +
                                mnowLine.charAt( 0 ) + "'" );
            // System.out.print( "> " );
            mBuffer.clear();
            throw new Throwable();
          } // if

        } // else

      }
      catch ( Throwable throwable ) {

        // System.out.print( "> " );
        mnowLine = new String();
        return false;

      } // catch

    } // while

    if ( Buffer1HasFullCommend( stament ) )
      return true;

    return true;

  } // GetStament()

  public void ReturnmBuffer2( Vector<TOKEN> stament ) throws Throwable {
    mBuffer2 = new Vector<TOKEN>();
    for ( int i = 0 ; i < stament.size() ; i++ )
      mBuffer2.add( stament.get( i ) );

  } // ReturnmBuffer2()

  protected void OPERATORFINDERROR( String gotOPERATOR ) throws Throwable {
    if ( mBuffer.size() > 1 ) {
      if ( mBuffer.get( mBuffer.size() - 2 ).GetToken().equals( "+" ) ||
           mBuffer.get( mBuffer.size() - 2 ).GetToken().equals( "-" ) ||
           mBuffer.get( mBuffer.size() - 2 ).GetToken().equals( "*" ) ||
           mBuffer.get( mBuffer.size() - 2 ).GetToken().equals( "/" ) ) {
        if ( gotOPERATOR.equals( "*" ) || gotOPERATOR.equals( "/" ) ) {
          System.out.println( "Line " + mLineCount + " : " + "unexpected token : '"
                              + mBuffer.get( mBuffer.size() - 1 ).GetToken() + "'" );
          // System.out.print( "> " );
          mBuffer.clear();
          throw new Throwable();
        } // if

      } // if

    } // if


    if ( mBuffer.size() > 2 ) {
      if ( mBuffer.get( mBuffer.size() - 3 ).GetType() == Global.s_T_OPERATOR &&
           mBuffer.get( mBuffer.size() - 2 ).GetType() == Global.s_T_OPERATOR ) {

        System.out.println( "Line " + mLineCount + " : " + "unexpected token : '"
                            + mBuffer.get( mBuffer.size() - 1 ).GetToken() + "'" );
        // System.out.print( "> " );
        mBuffer.clear();
        throw new Throwable();

      } // if

    } // if

    /*
    if ( mBuffer.size() > 1 ) {
      if ( mBuffer.get( mBuffer.size() - 2 ).GetType() == Global.s_T_ID ) {

        if ( Global.G_FindVariable( Global.s_Variables,
                                    mBuffer.get( mBuffer.size() - 2 ).GetToken() ) == null ) {
          System.out.println("Line "+ mLineCount +" : " +"undefined identifier : '"
                              + mBuffer.get( mBuffer.size() - 2 ).GetToken() + "'" );
          System.out.print( "> " );
          mBuffer.clear() ;
          throw new Throwable();
        } // if

      } // if

    } // if
    */

  } // OPERATORFINDERROR()

  protected void DISTINGUISHANDPUSHTOKEN( String gotID ) throws Throwable {
    if ( gotID.equals( "int" ) || gotID.equals( "string" ) || gotID.equals( "float" ) ||
         gotID.equals( "char" ) || gotID.equals( "bool" ) ) {
      mBuffer.add( new TOKEN( gotID, Global.s_T_TYPE, mLineCount ) );
    } // if
    else if ( gotID.equals( "void" ) ) {
      mBuffer.add( new TOKEN( gotID, Global.s_T_VOID, mLineCount ) );
    } // else if
    else if ( gotID.equals( "if" ) ) {
      mBuffer.add( new TOKEN( gotID, Global.s_T_IF, mLineCount ) );
    } // else if
    else if ( gotID.equals( "else" ) ) {
      mBuffer.add( new TOKEN( gotID, Global.s_T_ELSE, mLineCount ) );
    } // else if
    else if ( gotID.equals( "while" ) ) {
      mBuffer.add( new TOKEN( gotID, Global.s_T_WHILE, mLineCount ) );
    } // else if
    else if ( gotID.equals( "do" ) ) {
      mBuffer.add( new TOKEN( gotID, Global.s_T_DO, mLineCount ) );
    } // else if
    else if ( gotID.equals( "return" ) ) {
      mBuffer.add( new TOKEN( gotID, Global.s_T_RETURN, mLineCount ) );
    } // else if
    else if ( gotID.equals( "cin" ) ) {
      mBuffer.add( new TOKEN( gotID, Global.s_T_ID, mLineCount ) );
    } // else if
    else if ( gotID.equals( "cout" ) ) {
      mBuffer.add( new TOKEN( gotID, Global.s_T_ID, mLineCount ) );
    } // else if
    else {
      mBuffer.add( new TOKEN( gotID, Global.s_T_ID, mLineCount ) );
    } // else

  } // DISTINGUISHANDPUSHTOKEN()

  protected void TERNARYOPERATORFINDERROR() throws Throwable {
    int count = 0;
    for ( int i = 0 ; i < mBuffer.size() ; i++ ) {
      if ( mBuffer.get( i ).GetToken().equals( "?" ) )
        count = count + 1;
      else if ( mBuffer.get( i ).GetToken().equals( ":" ) )
        count = count - 1;

    } // for

    if ( count < 0 ) {
      System.out.println( "Line " + mLineCount + " : " + "unexpected token : '"
                          + mBuffer.get( mBuffer.size() - 1 ).GetToken() + "'" );
      // System.out.print( "> " );
      mBuffer.clear();
      throw new Throwable();
    } // if

  } // TERNARYOPERATORFINDERROR()

  protected void IDFINDERROR() throws Throwable {
    if ( mBuffer.get( mBuffer.size() - 1 ).GetType() == Global.s_T_ID ) {
      if ( mBuffer.size() > 1 ) {
        if ( ! ( mBuffer.get( mBuffer.size() - 2 ).GetType() == Global.s_T_TYPE ||
                 mBuffer.get( mBuffer.size() - 2 ).GetType() == Global.s_T_VOID ) ) {
          if ( ! Global.G_IDHASDEFINED( mBuffer.get( mBuffer.size() - 1 ).GetToken() ) ) {
            System.out.println( "Line " + mLineCount + " : " + "undefined identifier : '"
                                + mBuffer.get( mBuffer.size() - 1 ).GetToken() + "'" );
            // System.out.print( "> " );
            mBuffer.clear();
            throw new Throwable();
          } // if

        } // if

      } // if
      else {
        if ( ! Global.G_IDHASDEFINED( mBuffer.get( mBuffer.size() - 1 ).GetToken() ) ) {
          System.out.println( "Line " + mLineCount + " : " + "undefined identifier : '"
                              + mBuffer.get( mBuffer.size() - 1 ).GetToken() + "'" );
          // System.out.print( "> " );
          mBuffer.clear();
          throw new Throwable();
        } // if

      } // else

    } // if

  } // IDFINDERROR()

  protected void SMALLLEFTPARENFINDERROR() throws Throwable {
    if ( mBuffer.size() > 1 ) {
      if ( mBuffer.get( mBuffer.size() - 2 ).GetType() == Global.s_T_ID ) {

        if ( ! Global.G_IDHASDEFINED( mBuffer.get( mBuffer.size() - 1 ).GetToken() ) ) {
          System.out.println( "Line " + mLineCount + " : " + "undefined identifier : '"
                              + mBuffer.get( mBuffer.size() - 2 ).GetToken() + "'" );
          // System.out.print( "> " );
          mBuffer.clear();
          throw new Throwable();
        } // if

      } // if

    } // if

  } // SMALLLEFTPARENFINDERROR()

  protected void HASOTHERTOKENISERROR() throws Throwable {
    if ( mBuffer.size() > 1 ) {
      System.out.println( "Line " + mLineCount + " : " + "undefined identifier : '"
                          + mBuffer.get( mBuffer.size() - 1 ).GetToken() + "'" );
      // System.out.print( "> " );
      mBuffer.clear();
      throw new Throwable();

    } // if

  } // HASOTHERTOKENISERROR()

  protected void SMALLRIGHTPARENFINDERROR() throws Throwable {
    int count = 0;
    for ( int i = 0 ; i < mBuffer.size() ; i++ ) {
      if ( mBuffer.get( i ).GetType() == Global.s_T_SMALL_LEFT_PAREN )
        count = count + 1;
      else if ( mBuffer.get( i ).GetType() == Global.s_T_SMALL_RIGHT_PAREN )
        count = count - 1;

    } // for

    if ( count < 0 ) {
      System.out.println( "Line " + mLineCount + " : " + "unexpected token : '"
                          + mBuffer.get( mBuffer.size() - 1 ).GetToken() + "'" );
      // System.out.print( "> " );
      mBuffer.clear();
      throw new Throwable();
    } // if

  } // SMALLRIGHTPARENFINDERROR()

  protected boolean ISFUNDEFORIFWHILEEND() throws Throwable {
    int count = 0;
    for ( int i = 0 ; i < mBuffer.size() ; i++ ) {
      if ( mBuffer.get( i ).GetType() == Global.s_T_SMALL_LEFT_PAREN )
        count = count + 1;
      else if ( mBuffer.get( i ).GetType() == Global.s_T_SMALL_RIGHT_PAREN )
        count = count - 1;

    } // for

    if ( count == 0 ) {
      if ( mBuffer.get( 0 ).GetType() == Global.s_T_IF ||
           mBuffer.get( 0 ).GetType() == Global.s_T_WHILE ) {
        return true;
      } // if
      else if ( mBuffer.get( 0 ).GetType() == Global.s_T_TYPE ||
                mBuffer.get( 0 ).GetType() == Global.s_T_VOID ) {
        return true;
      } // else if
    } // if

    return false;
  } // ISFUNDEFORIFWHILEEND()

  protected void CONSTANTFINDERROR( String gotNUM ) throws Throwable {
    if ( mBuffer.size() > 1 ) {
      if ( mBuffer.get( mBuffer.size() - 2 ).GetType() == Global.s_T_CONSTANT ) {
        System.out.println( "Line " + mLineCount + " : " + "unexpected token : '"
                            + gotNUM + "'" );
        // System.out.print( "> " );
        mBuffer.clear();
        throw new Throwable();
      } // if

    } // if

    if ( gotNUM.charAt( gotNUM.length() - 1 ) == '.' )
      gotNUM = gotNUM.substring( 0, gotNUM.length() - 1 );

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
          // System.out.print( "> " );
          mBuffer.clear();
          throw new Throwable();
        } // if

      } // if

    } // if

  } // CONSTANTFINDERROR()

  protected void MIDLEFTPARENFINDERROR() throws Throwable {
    if ( mBuffer.size() > 1 ) {
      if ( mBuffer.get( mBuffer.size() - 2 ).GetType() == Global.s_T_ID ) {

        if ( ! Global.G_IDHASDEFINED( mBuffer.get( mBuffer.size() - 1 ).GetToken() ) ) {
          System.out.println( "Line " + mLineCount + " : " + "undefined identifier : '"
                              + mBuffer.get( mBuffer.size() - 2 ).GetToken() + "'" );
          // System.out.print( "> " );
          mBuffer.clear();
          throw new Throwable();
        } // if

      } // if

    } // if

  } // MIDLEFTPARENFINDERROR()

  protected void MIDRIGHTPARENFINDERROR() throws Throwable {
    int count = 0;
    for ( int i = 0 ; i < mBuffer.size() ; i++ ) {
      if ( mBuffer.get( i ).GetType() == Global.s_T_MID_LEFT_PAREN )
        count = count + 1;
      else if ( mBuffer.get( i ).GetType() == Global.s_T_MID_RIGHT_PAREN )
        count = count - 1;

    } // for

    if ( count < 0 ) {
      System.out.println( "Line " + mLineCount + " : " + "unexpected token : '"
                          + mBuffer.get( mBuffer.size() - 1 ).GetToken() + "'" );
      // System.out.print( "> " );
      mBuffer.clear();
      throw new Throwable();
    } // if

  } // MIDRIGHTPARENFINDERROR()

  protected String GetIDTOETokenInmNowLine() throws Throwable {
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

  protected String GetBOOLEANRELATIONALToken() throws Throwable {
    char charStr = mnowLine.charAt( 0 );

    if ( charStr == '>' || charStr == '<' || charStr == '=' || charStr == '!' ) {
      try {
        char secCharStr = mnowLine.charAt( 1 );
        if ( secCharStr == '=' )
          return new String() + charStr + secCharStr;
        else if ( charStr == '>' || charStr == '<' )
          return new String() + charStr;
      } // try
      catch ( Throwable throwable ) {
        if ( charStr == '>' || charStr == '<' )
          return new String() + charStr;
      } // catch

    } // if

    return new String();
  } // GetBOOLEANRELATIONALToken()

  protected String GetOPERATORToken() throws Throwable {
    char charStr = mnowLine.charAt( 0 );
    if ( charStr == '+' || charStr == '-' || charStr == '*' || charStr == '|' || charStr == '&' ||
         charStr == '/' || charStr == '%' )
      return mnowLine.substring( 0, 1 );

    if ( charStr == '>' || charStr == '<' )
      return mnowLine.substring( 0, 2 );

    return new String();
  } // GetOPERATORToken()

  protected boolean IsIDLegalCharInmNowLineFirst() throws Throwable {
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

  protected String GetCONSTANTTokenInmNowLine() throws Throwable {
    String gotCONSTANT = new String();

    if ( mnowLine.charAt( 0 ) == '.' )
      gotCONSTANT = gotCONSTANT + "0";

    if ( IsNumberAndNumberLegalWordInmNowLineFirstChar() ) {
      while ( IsNumberAndNumberLegalWordInmNowLineFirstChar() ) {
        gotCONSTANT = gotCONSTANT + mnowLine.substring( 0, 1 );
        RemoveFirstCherFormNowLine();
      } // while
    } // if
    else if ( mnowLine.charAt( 0 ) == '"' ) {
      gotCONSTANT += "\"";
      RemoveFirstCherFormNowLine();
      try {
        while ( mnowLine.charAt( 0 ) != '"' ) {
          gotCONSTANT += mnowLine.charAt( 0 );
          RemoveFirstCherFormNowLine();
        } // while

        gotCONSTANT += "\"";
        RemoveFirstCherFormNowLine();
      } // try
      catch ( Throwable throwable ) {
        System.out.println( "String 要在同一行" );
        // System.out.print( "> " );
        mBuffer.clear();
        throw new Throwable();
      } // catch

    } // else if
    else if ( mnowLine.charAt( 0 ) == '\'' ) {
      gotCONSTANT += "\'";
      RemoveFirstCherFormNowLine();
      try {
        for ( int i = 0 ; i < 2 ; i++ ) {
          if ( i == 0 ) {
            gotCONSTANT += mnowLine.charAt( 0 );
            RemoveFirstCherFormNowLine();
          } // if

          if ( i == 1 ) {
            if ( mnowLine.charAt( 0 ) != '\'' )
              throw new Throwable();
            else {
              gotCONSTANT += mnowLine.charAt( 0 );
              RemoveFirstCherFormNowLine();
            } // else

          } // if

        } // for

      } // try
      catch ( Throwable throwable ) {
        System.out.println( "Char只能是一個charter" );
        // System.out.print( "> " );
        mBuffer.clear();
        throw new Throwable();
      } // catch

    } // else if

    RemoveHeadWhiteCherFormNowLine();

    if ( gotCONSTANT.length() > 0 ) {
      return gotCONSTANT;
    } // if
    else {
      System.out.println( "Line " + mLineCount + " : " + "unexpected token : '" + gotCONSTANT + "'" );
      // System.out.print( "> " );
      mBuffer.clear();
      throw new Throwable();
    } // else


  } // GetCONSTANTTokenInmNowLine()

  protected boolean IsIDInmNowLineFirst() throws Throwable {
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

  protected boolean IsBOOLEANCONDITION1InmNowLineFirst() throws Throwable {
    char charStr = mnowLine.charAt( 0 );

    if ( charStr == '&' || charStr == '|' ) {
      try {
        char secCharStr = mnowLine.charAt( 1 );
        if ( charStr == secCharStr )
          return true;
        else
          return false;
      } // try
      catch ( Throwable throwable ) {
        return false;
      } // catch

    } // if

    return false;
  } // IsBOOLEANCONDITION1InmNowLineFirst()

  protected boolean Buffer1HasFullCommend( Vector<TOKEN> buffer ) throws Throwable {

    if ( mBuffer.size() > 0 ) {
      buffer.clear();
      for ( int i = 0 ; i < mBuffer.size() ; i++ )
        buffer.add( mBuffer.get( i ) );
      mBuffer.clear();
      return true;
    } // if

    return false;

  } // Buffer1HasFullCommend()

  private boolean ReturnBuffer2Stament( Vector<TOKEN> buffer ) throws Throwable {

    if ( mBuffer2.size() > 1 ) {
      buffer.clear();
      for ( int i = 0 ; i < mBuffer2.size() ; i++ )
        buffer.add( mBuffer2.get( i ) );
      mBuffer2 = null;
      return true;
    } // if

    return false;

  } // ReturnBuffer2Stament()

  protected boolean IsIDLegalStar( char theWord ) throws Throwable {

    if ( theWord >= 'A' && theWord <= 'Z' )
      return true;

    if ( theWord >= 'a' && theWord <= 'z' )
      return true;

    if ( theWord == '_' )
      return true;

    return false;

  } // IsIDLegalStar()

  protected boolean IsIDLegalWord( char theWord ) throws Throwable {

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

  protected boolean IsLegalNUMStar( char theWord ) throws Throwable {

    if ( theWord >= '0' && theWord <= '9' ) {
      return true;
    } // if
    else {
      return false;
    } // else

  } // IsLegalNUMStar()

  protected boolean IsLegalNUM( String fNUM ) throws Throwable {

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

  protected boolean IsNumberAndNumberLegalWordInmNowLineFirstChar() throws Throwable {

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

  protected boolean IsOPERATORInmNowLineFirst() throws Throwable {
    char charStr = mnowLine.charAt( 0 );

    if ( charStr == '+' || charStr == '-' )
      return true;

    if ( charStr == '*' || charStr == '/' || charStr == '%' )
      return true;

    if ( charStr == '|' || charStr == '&' )
      return true;

    if ( charStr == '>' ) {
      try {
        char secCharStr = mnowLine.charAt( 1 );
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
        char secCharStr = mnowLine.charAt( 1 );
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

  protected boolean IsBOOLEANRELATIONALInmNowLineFirst() throws Throwable {
    char charStr = mnowLine.charAt( 0 );

    if ( charStr == '>' || charStr == '<' || charStr == '=' || charStr == '!' ) {
      try {
        char secCharStr = mnowLine.charAt( 1 );
        if ( secCharStr == '=' )
          return true;
        else if ( charStr == '>' || charStr == '<' )
          return true;
        else
          return false;
      } // try
      catch ( Throwable throwable ) {
        if ( charStr == '>' || charStr == '<' )
          return true;
        else
          return false;
      } // catch

    } // if

    return false;

  } // IsBOOLEANRELATIONALInmNowLineFirst()

  protected boolean IsASSISNOPERATORInmNowLineFirst() throws Throwable {
    char charStr = mnowLine.charAt( 0 );

    if ( charStr == '+' || charStr == '-' ) {
      try {
        char secCharStr = mnowLine.charAt( 1 );
        if ( secCharStr == '=' || charStr == secCharStr )
          return true;
        else
          return false;
      } // try
      catch ( Throwable throwable ) {
        return false;
      } // catch

    } // if

    if ( charStr == '*' || charStr == '/' || charStr == '%' ) {
      try {
        char secCharStr = mnowLine.charAt( 1 );
        if ( secCharStr == '=' )
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

  protected boolean IsCONSTANTInmNowLineFirst() throws Throwable {
    try {

      char charStr = mnowLine.charAt( 0 );

      if ( IsLegalNUMStar( charStr ) )
        return true;

      if ( charStr == '"' || charStr == '\'' )
        return true;

      if ( charStr == '.' )
        return true;

      else
        return false;

    } // try
    catch ( Throwable throwable ) {

      return false;

    } // catch

  } // IsCONSTANTInmNowLineFirst()

  protected void InputNextLineTomNowLine() throws Throwable {

    mnowLine = msc.nextLine();
    mLineCount += 1;
    mnowLine = RemoveCommend( mnowLine );
    RemoveHeadWhiteCherFormNowLine();
    RemoveTailWhiteCherFormNowLine();

    while ( mnowLine.isEmpty() ) {
      mnowLine = msc.nextLine();
      mLineCount += 1;
      mnowLine = RemoveCommend( mnowLine );
      RemoveHeadWhiteCherFormNowLine();
      RemoveTailWhiteCherFormNowLine();
    } // while

  } // InputNextLineTomNowLine()

  protected String RemoveCommend( String comm ) throws Throwable {

    int findCommend = FindStrStarIndex( comm, "//" );

    if ( findCommend == 0 ) {
      comm = new String();
    } // if
    else if ( findCommend > - 1 ) {
      comm = mnowLine.substring( 0, findCommend );
    } // else if

    return comm;

  } // RemoveCommend()

  protected int FindStrStarIndex( String lineStr, String targetStr ) {

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

  protected int FindStrStarIndex( String targetStr ) {

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

  protected void RemoveHeadWhiteCherFormNowLine() throws Throwable {

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

  protected void RemoveTailWhiteCherFormNowLine() throws Throwable {

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

  protected void RemoveFirstCherFormNowLine() throws Throwable {

    try {

      if ( mnowLine.length() == 1 || mnowLine.isEmpty() )
        mnowLine = new String();
      else
        mnowLine = mnowLine.substring( 1 );

    } // try
    catch ( Throwable throwable ) {

    } // catch


  } // RemoveFirstCherFormNowLine()

  protected void IsGotTokenProcessFormNowLine( int gotCharIndex ) throws Throwable {
    for ( int i = 0 ; i < gotCharIndex ; i++ )
      RemoveFirstCherFormNowLine();

    RemoveHeadWhiteCherFormNowLine();

  } // IsGotTokenProcessFormNowLine()

  protected boolean IsWhitSpace( char charStr ) throws Throwable {

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
  public CutToken m_cuttoken;

  public Parser( Vector<TOKEN> mBuffer, CutToken cutToken ) throws Throwable {
    m_statement = mBuffer;
    m_step = 0; // check which token is wrong
    m_cuttoken = cutToken;
  } // Parser()

  public boolean GrammarParser() throws Throwable {
    try {
      if ( this.UserInput() ) {
        return true;
      } // if ()
      else {
        if ( ! m_statement.isEmpty() ) {
          System.out.println( "Line " + m_statement.get( m_step ).Getline() + " : " +
                              "unexpected token : '" + m_statement.get( m_step ).GetToken() + "'" );
        } // if
        // System.out.print( "> " );
        return false;
      } // else

    } // try
    catch ( Throwable throwable ) {
      if ( ! m_statement.isEmpty() ) {
        System.out.println( "Line " + m_statement.get( m_step ).Getline() + " : " +
                            "unexpected token : '" + m_statement.get( m_step ).GetToken() + "'" );
      } // if
      // System.out.print( "> " );
      return false;
    } // catch
  } // GrammarParser()

  private boolean IsStepEnd() throws Throwable {
    try {
      if ( m_step + 1 >= m_statement.size() ) {
        m_step = m_statement.size() - 1;
        return true;
      } // if
      else
        return false;
    } // try
    catch ( Throwable throwable ) {
      return false;
    } // catch
  } // IsStepEnd()

  private boolean UserInput() throws Throwable {
    try {
      int curStep = 0;

      if ( Definition() ) {
        ;
      } // if
      else if ( Statement() ) {
        ;
      } // else if
      else {
        return false;
      } // else

      if ( IsStepEnd() ) {
        return true;
      } // if

      while ( ( Definition() || Statement() ) ) {
        // m_step += 1;
        if ( IsStepEnd() ) {
          return true;
        } // if
      } // while

      return true;
    } // try
    catch ( Throwable throwable ) {
      return false;
    } // catch
  } // UserInput()

  private boolean Definition() throws Throwable {
    try {
      if ( m_statement.get( m_step ).GetToken().toLowerCase().equals( "void" ) &&
           m_statement.get( m_step ).GetType() == 10 ) {
        m_step += 1;
        if ( m_statement.get( m_step ).GetType() == 1 ) { // ID
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
        // m_step += 1;
        if ( m_statement.get( m_step ).GetType() == 1 ) { // ID
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
      if ( m_statement.get( m_step ).GetType() == 9 &&
           ( m_statement.get( m_step ).GetToken().toLowerCase().equals( "int" ) ||
             m_statement.get( m_step ).GetToken().toLowerCase().equals( "char" ) ||
             m_statement.get( m_step ).GetToken().toLowerCase().equals( "float" ) ||
             m_statement.get( m_step ).GetToken().toLowerCase().equals( "string" ) ||
             m_statement.get( m_step ).GetToken().toLowerCase().equals( "bool" ) ) ) {
        m_step += 1;
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

  private boolean Rest_of_Declarators() throws Throwable {
    try {
      if ( m_statement.get( m_step ).GetToken().equals( "[" ) &&
           m_statement.get( m_step ).GetType() == 5 ) {
        m_step += 1;
        if ( m_statement.get( m_step ).GetType() == 2 ) { // Constant
          m_step += 1;
          if ( m_statement.get( m_step ).GetToken().equals( "]" ) &&
               m_statement.get( m_step ).GetType() == 6 ) {
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

      while ( m_statement.get( m_step ).GetToken().equals( "," ) ) {
        m_step += 1;
        if ( m_statement.get( m_step ).GetType() == 1 ) { // ID
          m_step += 1;
          if ( m_statement.get( m_step ).GetToken().equals( "[" ) &&
               m_statement.get( 0 ).GetType() == 5 ) {
            m_step += 1;
            if ( m_statement.get( m_step ).GetType() == 2 ) { // Constant
              m_step += 1;
              if ( m_statement.get( m_step ).GetToken().equals( "]" ) &&
                   m_statement.get( 0 ).GetType() == 6 ) {
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
      } // while

      if ( m_statement.get( m_step ).GetToken().equals( ";" ) &&
           m_statement.get( m_step ).GetType() == 21 ) {
        m_step += 1;
        return true;
      } // if
      else {
        return false;
      } // else
    } // try
    catch ( Throwable throwable ) {
      return false;
    } // catch
  } // Rest_of_Declarators()

  private boolean Function_Definition_Without_ID() throws Throwable {
    try {
      if ( m_statement.get( m_step ).GetToken().equals( "(" ) &&
           m_statement.get( m_step ).GetType() == 3 ) {
        m_step += 1;
        if ( m_statement.get( m_step ).GetToken().toLowerCase().equals( "void" ) &&
             m_statement.get( m_step ).GetType() == 10 ) {
          m_step += 1;
          if ( m_statement.get( m_step ).GetToken().equals( ")" ) &&
               m_statement.get( m_step ).GetType() == 4 ) {
            m_step += 1;
          } // if
          else {
            return false;
          } // else
        } // if
        else if ( Formal_Parameter_List() ) {
          // m_step += 1;
          if ( m_statement.get( m_step ).GetToken().equals( ")" ) &&
               m_statement.get( m_step ).GetType() == 3 ) {
            m_step += 1;
          } // if
          else {
            return false;
          } // else
        } // else if
        else if ( m_statement.get( m_step ).GetToken().equals( ")" ) &&
                  m_statement.get( m_step ).GetType() == 4 ) {
          m_step += 1;
        } // else if
        else {
          return false;
        } // else
      } // if
      else {
        return false;
      } // else

      m_statement = new Vector<TOKEN>();
      m_step = 0;
      if ( m_cuttoken.GetStament( m_statement ) ) {
        if ( Compound_Statement() ) {
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
  } // Function_Definition_Without_ID()

  private boolean Formal_Parameter_List() throws Throwable {
    try {
      if ( Type_Specifier() ) {
        // m_step += 1;

        if ( m_statement.get( m_step ).GetToken().equals( "&" ) )
          m_step += 1;

        if ( m_statement.get( m_step ).GetType() == 1 ) { // ID
          m_step += 1;

          if ( IsStepEnd() ) {
            return true;
          } // if

          if ( m_statement.get( m_step ).GetToken().equals( "[" ) &&
               m_statement.get( m_step ).GetType() == 5 ) {
            m_step += 1;

            if ( m_statement.get( m_step ).GetType() == 2 ) { // Constant
              m_step += 1;

              if ( m_statement.get( m_step ).GetToken().equals( "]" ) &&
                   m_statement.get( m_step ).GetType() == 6 ) {
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

          boolean isIn = false;
          while ( m_statement.get( m_step ).GetToken().equals( "," ) ) {
            isIn = true;
            m_step += 1;
            if ( Type_Specifier() ) {
              // m_step += 1;
              if ( m_statement.get( m_step ).GetToken().equals( "&" ) )
                m_step += 1;

              if ( m_statement.get( m_step ).GetType() == 1 ) { // ID
                m_step += 1;
                if ( m_statement.get( m_step ).GetToken().equals( "[" ) &&
                     m_statement.get( m_step ).GetType() == 5 ) {
                  m_step += 1;
                  if ( m_statement.get( m_step ).GetType() == 2 ) { // Constant
                    m_step += 1;
                    if ( m_statement.get( m_step ).GetToken().equals( "]" ) &&
                         m_statement.get( m_step ).GetType() == 6 ) {
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

          if ( isIn ) {
            m_step -= 1;
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
    } // try
    catch ( Throwable throwable ) {
      return false;
    } // catch()
  } // Formal_Parameter_List()

  private boolean Compound_Statement() throws Throwable {
    try {
      if ( m_statement.get( m_step ).GetToken().equals( "{" ) &&
           m_statement.get( m_step ).GetType() == 7 ) {
        m_step += 1;

        Global.s_Variables.add( new VarList() );
        boolean isHave = false;
        m_statement = new Vector<TOKEN>();
        m_step = 0;

        if ( m_cuttoken.GetStament( m_statement ) ) {
          isHave = true;
        } // if

        while ( isHave ) {
          boolean issucess = false;
          if ( Declaration() || Statement() ) {
            Excute excute = new Excute( m_statement );
            if ( excute.ExcuteCommWithoutoutput() ) {
              issucess = true;
              isHave = false;
            } // if
            else {
              Global.s_Variables.remove( Global.s_Variables.size() - 1 );
              return false;
            } // else
          } // if
          else {
            issucess = false;
            isHave = false;
          } // else

          if ( issucess ) {
            m_statement = new Vector<TOKEN>();
            m_step = 0;
            if ( m_cuttoken.GetStament( m_statement ) )
              isHave = true;
          } // if

        } // while

        if ( m_statement.get( m_step ).GetToken().equals( "}" ) &&
             m_statement.get( m_step ).GetType() == 8 ) {
          m_step += 1;
          Global.s_Variables.remove( Global.s_Variables.size() - 1 );
          return true;
        } // if
        else {
          Global.s_Variables.remove( Global.s_Variables.size() - 1 );
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

  private boolean Declaration() throws Throwable {
    try {
      if ( Type_Specifier() ) {
        m_step += 1;
        if ( m_statement.get( m_step ).GetType() == 1 ) { // ID
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
  } // Declaration()

  private boolean Statement() throws Throwable {
    try {
      if ( m_statement.get( m_step ).GetToken().equals( ";" ) &&
           m_statement.get( m_step ).GetType() == 21 ) {
        m_step += 1;
        return true;
      } // if
      else if ( Expression() ) {
        // m_step += 1;
        if ( m_statement.get( m_step ).GetToken().equals( ";" ) &&
             m_statement.get( m_step ).GetType() == 21 ) {
          m_step += 1;
          return true;
        } // if
        else {
          return false;
        } // else
      } // else if
      else if ( m_statement.get( m_step ).GetToken().equals( "return" ) ) {
        m_step += 1;
        if ( Expression() )
          m_step += 1;
        if ( m_statement.get( m_step ).GetToken().equals( ";" ) &&
             m_statement.get( m_step ).GetType() == 21 ) {
          m_step += 1;
          return true;
        } // if
        else {
          return false;
        } // else
      } // else if
      else if ( Compound_Statement() ) {
        return true;
      } // else if
      else if ( m_statement.get( m_step ).GetToken().equals( "if" ) &&
                m_statement.get( m_step ).GetType() == 11 ) {
        m_step += 1;
        if ( m_statement.get( m_step ).GetToken().equals( "(" ) &&
             m_statement.get( m_step ).GetType() == 3 ) {
          m_step += 1;
          if ( Expression() ) {
            // m_step += 1;
            if ( m_statement.get( m_step ).GetToken().equals( ")" ) &&
                 m_statement.get( m_step ).GetType() == 4 ) {
              m_step += 1;

              m_statement = new Vector<TOKEN>();
              m_step = 0;
              if ( m_cuttoken.GetStament( m_statement ) ) {
                if ( Statement() ) {
                  // m_step += 1; // 可能會out of range

                  m_statement = new Vector<TOKEN>();
                  m_step = 0;
                  boolean isGetOK = false;
                  if ( m_cuttoken.GetStament( m_statement ) )
                    isGetOK = true;

                  if ( isGetOK && m_statement.get( m_step ).GetToken().equals( "else" ) &&
                       m_statement.get( m_step ).GetType() == 12 ) {
                    m_step += 1;

                    m_statement = new Vector<TOKEN>();
                    m_step = 0;
                    if ( m_cuttoken.GetStament( m_statement ) ) {
                      if ( Statement() ) {
                        return true;
                      } // if
                      else {
                        return false;
                      } // else
                    } // if
                    else
                      return false;

                  } // if
                  else if ( isGetOK ) {
                    m_cuttoken.ReturnmBuffer2( m_statement );
                    m_statement = new Vector<TOKEN>();
                  } // else if

                  return true; // ?_?
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
      else if ( m_statement.get( m_step ).GetType() == Global.s_T_WHILE ) {
        m_step += 1;
        if ( m_statement.get( m_step ).GetToken().equals( "(" ) &&
             m_statement.get( m_step ).GetType() == 3 ) {
          if ( Expression() ) {
            // m_step += 1;
            if ( m_statement.get( m_step ).GetToken().equals( ")" ) &&
                 m_statement.get( m_step ).GetType() == 4 ) {
              m_step += 1;

              m_statement = new Vector<TOKEN>();
              m_step = 0;
              if ( m_cuttoken.GetStament( m_statement ) ) {
                if ( Statement() ) {
                  return true;
                } // if
                else {
                  return false;
                } // else
              } // if
              else
                return false;
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
      else if ( m_statement.get( m_step ).GetType() == Global.s_T_DO ) {
        m_step += 1;

        m_statement = new Vector<TOKEN>();
        m_step = 0;
        if ( m_cuttoken.GetStament( m_statement ) ) {
          if ( Statement() ) {
            // m_step += 1;
            if ( m_statement.get( m_step ).GetType() == Global.s_T_WHILE ) {
              m_step += 1;
              if ( m_statement.get( m_step ).GetToken().equals( "(" ) &&
                   m_statement.get( m_step ).GetType() == 3 ) {
                m_step += 1;
                if ( Expression() ) {
                  // m_step += 1;
                  if ( m_statement.get( m_step ).GetToken().equals( ")" ) &&
                       m_statement.get( m_step ).GetType() == 4 ) {
                    m_step += 1;
                    if ( m_statement.get( m_step ).GetToken().equals( ";" ) &&
                         m_statement.get( m_step ).GetType() == 21 ) {
                      m_step += 1;
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
        } // if
        else
          return false;
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
      if ( Basic_Expression() ) {
        ;
      } // if
      else {
        return false;
      } // else

      if ( IsStepEnd() ) {
        return true;
      } // if

      while ( m_statement.get( m_step ).GetToken().equals( "," ) ) {
        m_step += 1;
        if ( Basic_Expression() ) {
          ;
        } // if
        else {
          return false;
        } // else

        if ( IsStepEnd() ) {
          return true;
        } // if
      } // while

      return true;
    } // try
    catch ( Throwable throwable ) {
      return false;
    } // catch()
  } // Expression()

  private boolean Basic_Expression() throws Throwable {
    try {
      if ( m_statement.get( m_step ).GetType() == 1 ) { // ID
        m_step += 1;
        if ( Rest_of_Identifier_Started_Basic_Exp() ) {
          return true;
        } // if
        else {
          return false;
        } // else
      } // if
      else if ( ( m_statement.get( m_step ).GetToken().equals( "++" ) &&
                  m_statement.get( m_step ).GetType() == 20 ) ||
                ( m_statement.get( m_step ).GetToken().equals( "--" ) &&
                  m_statement.get( m_step ).GetType() == 20 ) ) {
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
        // m_step += 1;
        while ( Sign() ) {
          // m_step += 1;
        } // while

        if ( Signed_Unary_Exp() ) {
          // m_step += 1;
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
      else if ( m_statement.get( m_step ).GetType() == 2 ) { // Constant
        m_step += 1;
        if ( Romce_and_Romloe() ) {
          return true;
        } // if
        else {
          return false;
        } // else
      } // else if
      else if ( m_statement.get( m_step ).GetToken().equals( "(" ) &&
                m_statement.get( m_step ).GetType() == 3 ) {
        m_step += 1;
        if ( Expression() ) {
          // m_step += 1;
          if ( m_statement.get( m_step ).GetToken().equals( ")" ) &&
               m_statement.get( m_step ).GetType() == 4 ) {
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
      if ( m_statement.get( m_step ).GetToken().equals( "[" ) &&
           m_statement.get( m_step ).GetType() == 5 ) {
        m_step += 1;
        if ( ! Expression() ) {
          return false;
        } // if

        if ( m_statement.get( m_step ).GetToken().equals( "]" ) &&
             m_statement.get( m_step ).GetType() == 6 ) {
          m_step += 1;
        } // if
        else
          return false;
      } // if

      if ( Assignment_Operator() ) {
        if ( ! Basic_Expression() )
          return false;
        else
          return true;
      } // if
      else if ( m_statement.get( m_step ).GetToken().equals( "++" ) ||
                m_statement.get( m_step ).GetToken().equals( "--" ) ) {
        m_step += 1;
        if ( ! Romce_and_Romloe() )
          return false; // print error
        else
          return true;
      } // else if
      else if ( m_statement.get( m_step ).GetToken().equals( "(" ) ) {
        m_step += 1;
        Actual_Parameter_List();
        if ( ! m_statement.get( m_step ).GetToken().equals( ")" ) )
          return false;
        else
          m_step += 1;

        if ( Romce_and_Romloe() )
          return true;
        else
          return false;

      } // else if
      else if ( Romce_and_Romloe() ) {
        return true;
      } // else if
      else
        return false;

    } // try
    catch ( Throwable throwable ) {
      return false;
    } // catch()
  } // Rest_of_Identifier_Started_Basic_Exp()

  private boolean Rest_of_PPMM_Identifier_Started_Basic_Exp() throws Throwable {
    try {
      if ( m_statement.get( m_step ).GetToken().equals( "[" ) &&
           m_statement.get( m_step ).GetType() == 5 ) {
        m_step += 1;
        if ( Expression() ) {
          // m_step += 1;
          if ( m_statement.get( m_step ).GetToken().equals( "]" ) &&
               m_statement.get( m_step ).GetType() == 6 ) {
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
    if ( ( m_statement.get( m_step ).GetToken().equals( "+" ) &&
           m_statement.get( m_step ).GetType() == 16 ) ||
         ( m_statement.get( m_step ).GetToken().equals( "-" ) &&
           m_statement.get( m_step ).GetType() == 16 ) ||
         ( m_statement.get( m_step ).GetToken().equals( "!" ) &&
           m_statement.get( m_step ).GetType() == 19 ) ) {
      m_step += 1;
      return true;
    } // if
    else {
      return false;
    } // else
  } // Sign()

  private boolean Actual_Parameter_List() throws Throwable {
    try {
      if ( Basic_Expression() ) {
        // m_step += 1;

        while ( m_statement.get( m_step ).GetToken().equals( "," ) ) {
          m_step += 1;
          if ( Basic_Expression() ) {
            // m_step += 1;
          } // if
          else {
            return false;
          } // else

          if ( IsStepEnd() ) {
            return true;
          } // if
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

  } // Actual_Parameter_List()

  private boolean Assignment_Operator() throws Throwable {
    if ( m_statement.get( m_step ).GetToken().equals( "=" )
         && m_statement.get( m_step ).GetType() == 18 ) {
      m_step += 1;
      return true;
    } // if
    else if ( m_statement.get( m_step ).GetToken().equals( "*=" )
              && m_statement.get( m_step ).GetType() == 20 ) { // TE
      m_step += 1;
      return true;
    } // else if
    else if ( m_statement.get( m_step ).GetToken().equals( "/=" )
              && m_statement.get( m_step ).GetType() == 20 ) { // DE
      m_step += 1;
      return true;
    } // else if
    else if ( m_statement.get( m_step ).GetToken().equals( "%=" ) &&
              m_statement.get( m_step ).GetType() == 20 ) { // RE
      m_step += 1;
      return true;
    } // else if
    else if ( m_statement.get( m_step ).GetToken().equals( "+=" ) &&
              m_statement.get( m_step ).GetType() == 20 ) { // PE
      m_step += 1;
      return true;
    } // else if
    else if ( m_statement.get( m_step ).GetToken().equals( "-=" ) &&
              m_statement.get( m_step ).GetType() == 20 ) { // ME
      m_step += 1;
      return true;
    } // else if
    else {
      return false;
    } // else
  } // Assignment_Operator()

  private boolean Romce_and_Romloe() throws Throwable {
    try {
      if ( Rest_of_Maybe_Logical_OR_Exp() ) {
        // m_step += 1; // 可能會out of range

        if ( IsStepEnd() ) {
          return true;
        } // if

        if ( m_statement.get( m_step ).GetToken().equals( "?" ) &&
             m_statement.get( m_step ).GetType() == 23 ) {
          m_step += 1;
          if ( Basic_Expression() ) {
            // m_step += 1;
            if ( m_statement.get( m_step ).GetToken().equals( ":" ) &&
                 m_statement.get( m_step ).GetType() == 23 ) {
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
        // m_step += 1;
      } // if
      else {
        return false;
      } // else

      if ( IsStepEnd() ) {
        return true;
      } // if

      while ( m_statement.get( m_step ).GetToken().toLowerCase().equals( "||" ) &&
              m_statement.get( m_step ).GetType() == 19 ) { // 可能會out of range
        m_step += 1;
        if ( Maybe_Logical_AND_Exp() ) {
          // m_step += 1;
        } // if
        else {
          return false;
        } // else

        if ( IsStepEnd() ) {
          return true;
        } // if
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
        // m_step += 1;
      } // if
      else {
        return false;
      } // else

      if ( IsStepEnd() ) {
        return true;
      } // if

      while ( m_statement.get( m_step ).GetToken().toLowerCase().equals( "&&" ) &&
              m_statement.get( m_step ).GetType() == 19 ) { // maybe will out of range
        m_step += 1;
        if ( Maybe_Bit_OR_Exp() ) {
          // m_step += 1;
        } // if
        else {
          return false;
        } // else

        if ( IsStepEnd() ) {
          return true;
        } // if
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
        // m_step += 1;
      } // if
      else {
        return false;
      } // else

      if ( IsStepEnd() ) {
        return true;
      } // if

      while ( m_statement.get( m_step ).GetToken().toLowerCase().equals( "&&" ) &&
              m_statement.get( m_step ).GetType() == 19 ) { // maybe will out of range
        m_step += 1;

        if ( Maybe_Bit_OR_Exp() ) {
          // m_step += 1;
        } // if
        else {
          return false;
        } // else

        if ( IsStepEnd() ) {
          return true;
        } // if
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
        // m_step += 1;
      } // if
      else {
        return false;
      } // else

      if ( IsStepEnd() ) {
        return true;
      } // if

      while ( m_statement.get( m_step ).GetToken().equals( "|" ) ) { // maybe will out of range
        m_step += 1;

        if ( Maybe_Bit_Ex_OR_Exp() ) {
          // m_step += 1;
        } // if
        else {
          return false;
        } // else

        if ( IsStepEnd() ) {
          return true;
        } // if
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
        // m_step += 1;
      } // if
      else {
        return false;
      } // else

      if ( IsStepEnd() ) {
        return true;
      } // if

      while ( m_statement.get( m_step ).GetToken().equals( "|" ) ) {
        m_step += 1;

        if ( Maybe_Bit_Ex_OR_Exp() ) {
          // m_step += 1;
        } // if
        else {
          return false;
        } // else

        if ( IsStepEnd() ) {
          return true;
        } // if
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
        // m_step += 1;
      } // if
      else {
        return false;
      } // else

      if ( IsStepEnd() ) {
        return true;
      } // if

      while ( m_statement.get( m_step ).GetToken().equals( "^" ) ) { // maybe will out of range
        m_step += 1;
        if ( Maybe_Bit_AND_Exp() ) {
          // m_step += 1;
        } // if
        else {
          return false;
        } // else

        if ( IsStepEnd() ) {
          return true;
        } // if
      } // while

      return true;
    } // try
    catch ( Throwable throwable ) {
      return false;
    } // catch()
  } // Maybe_Bit_Ex_OR_Exp()

  private boolean Rest_of_Maybe_Bit_Ex_OR_Exp() throws Throwable {
    try {
      if ( Rest_of_Maybe_Bit_AND_Exp() ) {
        // m_step += 1;
      } // if
      else {
        return false;
      } // else

      if ( IsStepEnd() ) {
        return true;
      } // if

      while ( m_statement.get( m_step ).GetToken().equals( "^" ) ) { // maybe will out of range
        m_step += 1;

        if ( Maybe_Bit_AND_Exp() ) {
          // m_step += 1;
        } // if
        else {
          return false;
        } // else

        if ( IsStepEnd() ) {
          return true;
        } // if
      } // while

      return true;
    } // try
    catch ( Throwable throwable ) {
      return false;
    } // catch
  } // Rest_of_Maybe_Bit_Ex_OR_Exp()

  private boolean Maybe_Bit_AND_Exp() throws Throwable {
    try {
      if ( Maybe_Equality_Exp() ) {
        // m_step += 1;
      } // if
      else {
        return false;
      } // else

      if ( IsStepEnd() ) {
        return true;
      } // if

      while ( m_statement.get( m_step ).GetToken().equals( "&" ) ) { // maybe will out of range
        m_step += 1;

        if ( Maybe_Equality_Exp() ) {
          // m_step += 1;
        } // if
        else {
          return false;
        } // else

        if ( IsStepEnd() ) {
          return true;
        } // if
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
        // m_step += 1;
      } // if
      else {
        return false;
      } // else

      if ( IsStepEnd() ) {
        return true;
      } // if

      while ( m_statement.get( m_step ).GetToken().equals( "&" ) ) { // maybe will out of range
        m_step += 1;

        if ( Maybe_Equality_Exp() ) {
          // m_step += 1;
        } // if
        else {
          return false;
        } // else

        if ( IsStepEnd() ) {
          return true;
        } // if
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
        // m_step += 1;
      } // if
      else {
        return false;
      } // else

      if ( IsStepEnd() ) {
        return true;
      } // if

      while ( ( m_statement.get( m_step ).GetToken().equals( "==" ) ||
                m_statement.get( m_step ).GetToken().equals( "!=" ) ) &&
              m_statement.get( m_step ).GetType() == 17 ) {
        m_step += 1;
        if ( Maybe_Relational_Exp() ) {
          // m_step += 1;
        } // if
        else {
          return false;
        } // else

        if ( IsStepEnd() ) {
          return true;
        } // if
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
        // m_step += 1;
      } // if
      else {
        return false;
      } // else

      if ( IsStepEnd() ) {
        return true;
      } // if

      while ( ( m_statement.get( m_step ).GetToken().equals( "==" ) ||
                m_statement.get( m_step ).GetToken().equals( "!=" ) ) &&
              m_statement.get( m_step ).GetType() == 17 ) {
        m_step += 1;
        if ( Maybe_Relational_Exp() ) {
          // m_step += 1;
        } // if
        else {
          return false;
        } // else

        if ( IsStepEnd() ) {
          return true;
        } // if
      } // while

      return true;
    } // try
    catch ( Throwable throwable ) {
      return false;
    } // catch()
  } // Rest_of_Maybe_Equality_Exp()

  private boolean Maybe_Relational_Exp() throws Throwable {
    try {
      if ( Maybe_Shift_Exp() ) {
        // m_step += 1;
      } // if
      else {
        return false;
      } // else

      if ( IsStepEnd() ) {
        return true;
      } // if

      while ( ( m_statement.get( m_step ).GetToken().equals( "<" ) ||
                m_statement.get( m_step ).GetToken().equals( ">" ) ||
                m_statement.get( m_step ).GetToken().equals( "<=" ) ||
                m_statement.get( m_step ).GetToken().equals( ">=" ) ) &&
              m_statement.get( m_step ).GetType() == 17 ) {
        m_step += 1;
        if ( Maybe_Shift_Exp() ) {
          // m_step += 1;
        } // if
        else {
          return false;
        } // else

        if ( IsStepEnd() ) {
          return true;
        } // if
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
        // m_step += 1;
      } // if
      else {
        return false;
      } // else

      if ( IsStepEnd() ) {
        return true;
      } // if

      while ( ( m_statement.get( m_step ).GetToken().equals( "<" ) ||
                m_statement.get( m_step ).GetToken().equals( ">" ) ||
                m_statement.get( m_step ).GetToken().equals( "<=" ) ||
                m_statement.get( m_step ).GetToken().equals( ">=" ) ) &&
              m_statement.get( m_step ).GetType() == 17 ) {
        m_step += 1;
        if ( Maybe_Shift_Exp() ) {
          // m_step += 1;
        } // if
        else {
          return false;
        } // else

        if ( IsStepEnd() ) {
          return true;
        } // if
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
        // m_step += 1;
      } // if
      else {
        return false;
      } // else

      if ( IsStepEnd() ) {
        return true;
      } // if

      while ( ( m_statement.get( m_step ).GetToken().equals( ">>" ) ||
                m_statement.get( m_step ).GetToken().equals( "<<" ) ) &&
              m_statement.get( m_step ).GetType() == 16 ) {
        m_step += 1;
        if ( Maybe_Additive_Exp() ) {
          // m_step += 1;
        } // if
        else {
          return false;
        } // else

        if ( IsStepEnd() ) {
          return true;
        } // if
      } // while

      return true;
    } // try
    catch ( Throwable throwable ) {
      return false;
    } // catch()
  } // Maybe_Shift_Exp()

  private boolean Rest_of_Maybe_Shift_Exp() throws Throwable {
    try {
      if ( Rest_of_Maybe_Additive_Exp() ) {
        // m_step += 1;
      } // if
      else {
        return false;
      } // else

      if ( IsStepEnd() ) {
        return true;
      } // if

      while ( ( m_statement.get( m_step ).GetToken().equals( ">>" ) ||
                m_statement.get( m_step ).GetToken().equals( "<<" ) ) &&
              m_statement.get( m_step ).GetType() == 16 ) {
        m_step += 1;
        if ( Maybe_Additive_Exp() ) {
          // m_step += 1;
        } // if
        else {
          return false;
        } // else

        if ( IsStepEnd() ) {
          return true;
        } // if
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
        // m_step += 1;
      } // if
      else {
        return false;
      } // else

      if ( IsStepEnd() ) {
        return true;
      } // if

      while ( ( m_statement.get( m_step ).GetToken().equals( "+" ) ||
                m_statement.get( m_step ).GetToken().equals( "-" ) ) &&
              m_statement.get( m_step ).GetType() == 16 ) {
        m_step += 1;
        if ( Maybe_Mult_Exp() ) {
          // m_step += 1;
        } // if
        else {
          return false;
        } // else

        if ( IsStepEnd() ) {
          return true;
        } // if
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
        // m_step += 1;
      } // if
      else {
        return false;
      } // else

      if ( IsStepEnd() ) {
        return true;
      } // if

      while ( ( m_statement.get( m_step ).GetToken().equals( "+" ) ||
                m_statement.get( m_step ).GetToken().equals( "-" ) ) &&
              m_statement.get( m_step ).GetType() == 16 ) {
        m_step += 1;
        if ( Maybe_Mult_Exp() ) {
          // m_step += 1;
        } // if
        else {
          return false;
        } // else

        if ( IsStepEnd() ) {
          return true;
        } // if
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
        // m_step += 1;
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
      if ( IsStepEnd() ) {
        return true;
      } // if

      while ( ( m_statement.get( m_step ).GetToken().equals( "*" ) ||
                m_statement.get( m_step ).GetToken().equals( "/" ) ||
                m_statement.get( m_step ).GetToken().equals( "%" ) ) &&
              m_statement.get( m_step ).GetType() == 16 ) {
        m_step += 1;
        if ( Unary_Exp() ) {
          // m_step += 1;
        } // if
        else {
          return false;
        } // else

        if ( IsStepEnd() ) {
          return true;
        } // if
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
        // m_step += 1;

        while ( Sign() ) {
          // m_step += 1;
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
      else if ( ( m_statement.get( m_step ).GetToken().equals( "++" ) ||
                  m_statement.get( m_step ).GetToken().equals( "--" ) ) &&
                m_statement.get( m_step ).GetType() == 20 ) {
        m_step += 1;
        if ( m_statement.get( m_step ).GetType() == 4 ) { // ID
          m_step += 1;

          if ( IsStepEnd() ) {
            return true;
          } // if

          if ( m_statement.get( m_step ).GetToken().equals( "[" ) &&
               m_statement.get( m_step ).GetType() == 5 ) { // 可能會out of range
            m_step += 1;
            if ( Expression() ) {
              // m_step += 1;
              if ( m_statement.get( m_step ).GetToken().equals( "]" ) &&
                   m_statement.get( m_step ).GetType() == 6 ) {
                m_step += 1;
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
        m_step += 1;

        if ( IsStepEnd() ) {
          return true;
        } // if

        if ( m_statement.get( m_step ).GetToken().equals( "(" ) &&
             m_statement.get( m_step ).GetType() == 3 ) {
          m_step += 1;

          if ( Actual_Parameter_List() ) {
            // m_step += 1;
          } // if

          if ( m_statement.get( m_step ).GetToken().equals( ")" ) &&
               m_statement.get( m_step ).GetType() == 4 ) {
            m_step += 1;
            return true;
          } // if
          else {
            return false;
          } // else
        } // if
        else if ( m_statement.get( m_step ).GetToken().equals( "[" ) &&
                  m_statement.get( m_step ).GetType() == 5 ) {
          m_step += 1;
        } // else if

        return false;
      } // if
      else if ( m_statement.get( m_step ).GetType() == 13 ) { // Constant
        m_step += 1;
        return true;
      } // else if
      else if ( m_statement.get( m_step ).GetToken().equals( "(" ) &&
                m_statement.get( m_step ).GetType() == 3 ) {
        m_step += 1;
        if ( Expression() ) {
          // m_step += 1;
          if ( m_statement.get( m_step ).GetToken().equals( ")" ) &&
               m_statement.get( m_step ).GetType() == 4 ) {
            m_step += 1;
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
      if ( m_statement.get( m_step ).GetType() == 1 ) {  // id
        m_step += 1;

        if ( IsStepEnd() ) {
          return true;
        } // if

        if ( m_statement.get( m_step ).GetToken().equals( "(" ) &&
             m_statement.get( m_step ).GetType() == 3 ) {
          m_step += 1;

          if ( Actual_Parameter_List() ) {
            // m_step += 1;
          } // if

          if ( m_statement.get( m_step ).GetToken().equals( ")" ) &&
               m_statement.get( m_step ).GetType() == 4 ) {
            m_step += 1;
            return true;
          } // if
          else {
            return false;
          } // else
        } // if
        else if ( m_statement.get( m_step ).GetToken().equals( "[" ) &&
                  m_statement.get( m_step ).GetType() == 5 ) {
          m_step += 1;

          if ( Expression() ) {
            // m_step += 1;

            if ( m_statement.get( m_step ).GetToken().equals( "]" ) &&
                 m_statement.get( m_step ).GetType() == 6 ) {
              m_step += 1;

              if ( IsStepEnd() ) {
                return true;
              } // if

              if ( ( m_statement.get( m_step ).GetToken().equals( "++" ) ||
                     m_statement.get( m_step ).GetToken().equals( "--" ) ) &&
                   m_statement.get( m_step ).GetType() == 20 ) {
                m_step += 1;
                return true;
              } // if

              return false;
            } // if
            else {
              return false;
            } // else
          } // if
          else {
            return false;
          } // else
        } // else if
        else if ( ( m_statement.get( m_step ).GetToken().equals( "++" ) ||
                    m_statement.get( m_step ).GetToken().equals( "--" ) ) &&
                  m_statement.get( m_step ).GetType() == 20 ) {
          m_step += 1;
          return true;
        } // else if
        else {
          return true;  // need check
        } // else
      } // if
      else if ( m_statement.get( m_step ).GetType() == 2 ) {
        m_step += 1;
        return true;
      } // else if
      else if ( m_statement.get( m_step ).GetToken().equals( "(" ) &&
                m_statement.get( m_step ).GetType() == 3 ) {
        m_step += 1;

        if ( Expression() ) {
          // m_step += 1;

          if ( m_statement.get( m_step ).GetToken().equals( ")" ) &&
               m_statement.get( m_step ).GetType() == 4 ) {
            m_step += 1;
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
        return true;
      } // else
    } // try
    catch ( Throwable throwable ) {
      return false;
    } // catch()
  } // Unsigned_Unary_Exp()

} // class Parser

class Excute {

  private Vector<TOKEN> mStament;

  public Excute( Vector<TOKEN> stament ) throws Throwable {
    this.mStament = stament;
  } // Excute()

  public boolean ExcuteCommWithoutput() throws Throwable {

    IsDoneFun();
    if ( mStament.get( 0 ).GetType() == Global.s_T_TYPE ) {
      VerDefinWithoutput();
    } // if
    else
      System.out.println( "Statement executed ..." );

    return true;
  } // ExcuteCommWithoutput()

  public boolean ExcuteCommWithoutoutput() throws Throwable {

    IsDoneFun();
    if ( mStament.get( 0 ).GetType() == Global.s_T_TYPE ) {
      VerDefinWithoutoutput();
    } // if
    else
      ; // System.out.println( "Statement executed ..." );

    return true;
  } // ExcuteCommWithoutoutput()

  private void IsDoneFun() throws Throwable {
    if ( mStament.get( 0 ).GetToken().equals( "Done" ) ) {
      if ( mStament.size() == 4 ) {
        if ( mStament.get( 1 ).GetToken().equals( "(" ) && mStament.get( 2 ).GetToken().equals( ")" )
             && mStament.get( 3 ).GetToken().equals( ";" ) ) {
          System.out.println( "Our-C exited ..." );
          System.exit( 0 );
        } // if

      } // if

    } // if

  } // IsDoneFun()

  private void IsCinFun() throws Throwable {
    if ( mStament.get( 0 ).GetToken().equals( "Done" ) ) {
      if ( mStament.size() == 4 ) {
        if ( mStament.get( 1 ).GetToken().equals( "(" ) && mStament.get( 2 ).GetToken().equals( ")" )
             && mStament.get( 3 ).GetToken().equals( ";" ) ) {
          System.out.println( "Our-C exited ..." );
          System.exit( 0 );
        } // if

      } // if

    } // if

  } // IsCinFun()

  private void VerDefinWithoutput() throws Throwable {
    Variable var = Global.G_FindVariable( Global.s_Variables, mStament.get( 1 ).GetToken() );
    if ( var == null ) {
      if ( mStament.get( 0 ).GetToken().equals( "int" ) ) {
        var = new VarINT( Global.s_V_INT, mStament.get( 1 ).GetToken() );
        Global.G_AddVariable( var );
      } // if
      else if ( mStament.get( 0 ).GetToken().equals( "string" ) ) {
        var = new VarINT( Global.s_V_STRING, mStament.get( 1 ).GetToken() );
        Global.G_AddVariable( var );
      } // else if
      else if ( mStament.get( 0 ).GetToken().equals( "float" ) ) {
        var = new VarINT( Global.s_V_FLOAT, mStament.get( 1 ).GetToken() );
        Global.G_AddVariable( var );
      } // else if
      else if ( mStament.get( 0 ).GetToken().equals( "char" ) ) {
        var = new VarINT( Global.s_V_CHAR, mStament.get( 1 ).GetToken() );
        Global.G_AddVariable( var );
      } // else if
      else if ( mStament.get( 0 ).GetToken().equals( "bool" ) ) {
        var = new VarINT( Global.s_V_BOOL, mStament.get( 1 ).GetToken() );
        Global.G_AddVariable( var );
      } // else if

      System.out.println( "Definition of " + mStament.get( 1 ).GetToken() + " entered ..." );
    } // if
    else {
      if ( mStament.get( 0 ).GetToken().equals( "int" ) ) {
        var.SetType( Global.s_V_INT );
      } // if
      else if ( mStament.get( 0 ).GetToken().equals( "string" ) ) {
        var.SetType( Global.s_V_STRING );
      } // else if
      else if ( mStament.get( 0 ).GetToken().equals( "float" ) ) {
        var.SetType( Global.s_V_FLOAT );
      } // else if
      else if ( mStament.get( 0 ).GetToken().equals( "char" ) ) {
        var.SetType( Global.s_V_CHAR );
      } // else if
      else if ( mStament.get( 0 ).GetToken().equals( "bool" ) ) {
        var.SetType( Global.s_V_BOOL );
      } // else if

      System.out.println( "New definition of " + mStament.get( 1 ).GetToken() + " entered ..." );
    } // else

  } // VerDefinWithoutput()

  private void VerDefinWithoutoutput() throws Throwable {
    Variable var = Global.G_FindVariable( Global.s_Variables, mStament.get( 1 ).GetToken() );
    if ( var == null ) {
      if ( mStament.get( 0 ).GetToken().equals( "int" ) ) {
        var = new VarINT( Global.s_V_INT, mStament.get( 1 ).GetToken() );
        Global.G_AddVariable( var );
      } // if
      else if ( mStament.get( 0 ).GetToken().equals( "string" ) ) {
        var = new VarINT( Global.s_V_STRING, mStament.get( 1 ).GetToken() );
        Global.G_AddVariable( var );
      } // else if
      else if ( mStament.get( 0 ).GetToken().equals( "float" ) ) {
        var = new VarINT( Global.s_V_FLOAT, mStament.get( 1 ).GetToken() );
        Global.G_AddVariable( var );
      } // else if
      else if ( mStament.get( 0 ).GetToken().equals( "char" ) ) {
        var = new VarINT( Global.s_V_CHAR, mStament.get( 1 ).GetToken() );
        Global.G_AddVariable( var );
      } // else if
      else if ( mStament.get( 0 ).GetToken().equals( "bool" ) ) {
        var = new VarINT( Global.s_V_BOOL, mStament.get( 1 ).GetToken() );
        Global.G_AddVariable( var );
      } // else if

      // System.out.println( "Definition of " + mStament.get( 1 ).GetToken() + " entered ..." );
    } // if
    else {
      if ( mStament.get( 0 ).GetToken().equals( "int" ) ) {
        var.SetType( Global.s_V_INT );
      } // if
      else if ( mStament.get( 0 ).GetToken().equals( "string" ) ) {
        var.SetType( Global.s_V_STRING );
      } // else if
      else if ( mStament.get( 0 ).GetToken().equals( "float" ) ) {
        var.SetType( Global.s_V_FLOAT );
      } // else if
      else if ( mStament.get( 0 ).GetToken().equals( "char" ) ) {
        var.SetType( Global.s_V_CHAR );
      } // else if
      else if ( mStament.get( 0 ).GetToken().equals( "bool" ) ) {
        var.SetType( Global.s_V_BOOL );
      } // else if

      // System.out.println( "New definition of " + mStament.get( 1 ).GetToken() + " entered ..." );
    } // else

  } // VerDefinWithoutoutput()

} // class Excute

class Main {

  public static void main( String[] args ) throws Throwable {
    CutToken cutToken = new CutToken();
    Global.G_OurCInitialize();
    Global.sc.nextLine();
    System.out.println( "Our-C running ..." );
    int isInIfWhileElse = 0;
    while ( true ) {
      Vector<TOKEN> stament = new Vector<TOKEN>();
      // System.out.println( "main CutToken Part! " );
      if ( cutToken.Cutting( stament ) ) {
        Parser parser = new Parser( stament, cutToken );
        // System.out.println( "main Parser Part! " );
        if ( parser.GrammarParser() ) {
          Excute excute = new Excute( stament );
          excute.ExcuteCommWithoutput();
        } // if
      } // if
    } // while

  } // main()

} // class Main