package PL110_10627153;
// 20220618 01:37

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

  static public Scanner sc = new Scanner( System.in );
  static public Function s_Fundefin = null;

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

  public static void G_ClearVars() throws Throwable {
    while ( s_Variables.size() > 1 ) {
      s_Variables.remove( s_Variables.size() - 1 );
    } // while

  } // G_ClearVars()

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
    Vector<Variable> varlists = null;
    Variable var = null;
    VarSTRING varSTRING = null;
    VarINT varINT = null;
    /*
    // Done()
    s_Functions.add( new Function( "Done", new Vector<Variable>(), new Vector<Stament>() ) );

    // ListAllVariables()
    s_Functions.add( new Function( "ListAllVariables",
                                   new Vector<Variable>(), new Vector<Stament>() ) );

    // ListVariable()
    varlists = new Vector<Variable>();
    var = varSTRING = new VarSTRING( s_V_STRING, "str1" );
    varlists.add( var );
    s_Functions.add( new Function( "ListVariable",
                                   varlists, new Vector<Stament>() ) );

    varlists = null;
    var = null;
    varSTRING = null;
    varINT = null;

    // ListAllFunctions()
    s_Functions.add( new Function( "ListAllFunctions",
                                   new Vector<Variable>(), new Vector<Stament>() ) );

    // ListFunction()
    varlists = new Vector<Variable>();
    var = varSTRING = new VarSTRING( s_V_STRING, "str1" );
    varlists.add( var );
    s_Functions.add( new Function( "ListFunction",
                                   varlists, new Vector<Stament>() ) );
    varlists = null;
    var = null;
    varSTRING = null;
    varINT = null;
    */
    // Variable
    s_Variables.add( new VarList() );
    // s_Functions.add( new Function( "cout", new Vector<Variable>(), new Vector<Stament>() ) );
    // s_Functions.add( new Function( "cin", new Vector<Variable>(), new Vector<Stament>() ) );

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
  public Vector<TOKEN> m_Line = new Vector<TOKEN>();

  public Stament( Vector<TOKEN> List ) throws Throwable {
    for ( int i = 0 ; i < List.size() ; i++ )
      m_Line.add( List.get( i ) );
  } // Stament()

  public Stament() throws Throwable {
    ;
  } // Stament()

} // class Stament

abstract class Variable {

  private String m_Name;
  private int m_Type;
  protected String m_arraySize;
  public boolean m_isArray = false;

  public Variable( int type, String name ) throws Throwable {
    this.m_Name = new String( name );
    this.m_Type = type;
    m_arraySize = new String();
    m_isArray = false;
  } // Variable()

  public String GetName() throws Throwable {

    return this.m_Name;

  } // GetName()

  public int GetType() throws Throwable {

    return m_Type;

  } // GetType()

  public String GetTypeName() throws Throwable {
    if ( m_Type == Global.s_V_INT ) {
      return "int";
    } // if
    else if ( m_Type == Global.s_V_STRING ) {
      return "string";
    } // else if
    else if ( m_Type == Global.s_V_FLOAT ) {
      return "float";
    } // else if
    else if ( m_Type == Global.s_V_CHAR ) {
      return "char";
    } // else if
    else if ( m_Type == Global.s_V_BOOL ) {
      return "bool";
    } // else if
    else {
      return "Type error";
    } // else
  } // GetTypeName()

  public boolean IsArray() throws Throwable {
    return m_isArray;
  } // IsArray()

  public String GetArraySize() throws Throwable {
    return m_arraySize;
  } // GetArraySize()

  public void SetArraySize( String size ) throws Throwable {
    m_isArray = true;
    m_arraySize = size;
  } // SetArraySize()

  public boolean SetType( int type ) throws Throwable {
    if ( m_Type != type ) {
      m_Type = type;
      return true;
    } // if
    else {
      return false;
    } // else

  } // SetType()

  public void SetName( String name ) throws Throwable {
    m_Name = name;
  } // SetName()

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

  public VarSTRING( int type, String name ) throws Throwable {
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

  public VarCHAR( int type, String name ) throws Throwable {
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
      if ( index + 1 > 0 ) {
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
      if ( index + 1 > 0 ) {
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

  public VarBOOL( int type, String name ) throws Throwable {
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
  public String m_name;
  public Vector<Variable> m_localVarList;
  public Vector<Stament> m_commLine;
  public int m_type = Global.s_T_VOID;

  public Function( String fName, Vector<Variable> lVar, Vector<Stament> stament ) throws Throwable {
    this.m_name = new String( fName );
    this.m_localVarList = lVar;
    this.m_commLine = stament;
  } // Function()

  public Function( int type, String fName, Vector<Variable> lVar,
                   Vector<Stament> stament ) throws Throwable {
    m_type = type;
    this.m_name = new String( fName );
    this.m_localVarList = lVar;
    this.m_commLine = stament;
  } // Function()

  public String GetName() throws Throwable {
    return this.m_name;
  } // GetName()

  public int LocalVarSum() throws Throwable {
    return m_localVarList.size();
  } // LocalVarSum()

  public Variable GetVar( int index ) throws Throwable {
    return m_localVarList.get( index );
  } // GetVar()

  public String GetTypeName() throws Throwable {
    if ( m_type == 1 ) {
      return "int";
    } // if
    else if ( m_type == 2 ) {
      return "string";
    } // else if
    else if ( m_type == 3 ) {
      return "float";
    } // else if
    else if ( m_type == 4 ) {
      return "char";
    } // else if
    else if ( m_type == 5 ) {
      return "bool";
    } // else if
    else if ( m_type == 10 ) {
      return "void";
    } // else if
    else {
      return "Type error";
    } // else
  } // GetTypeName()

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

  public boolean Cutting( Vector<TOKEN> stament, boolean isExctuting,
                          boolean wontelse ) throws Throwable {

    if ( ( mnowLine.length() == 0 || mnowLine == null || mnowLine.isEmpty() ) && ! isExctuting )
      mLineCount = 0;

    if ( ! isExctuting )
      System.out.print( "> " );

    if ( mBuffer2 != null )
      return ReturnBuffer2Stament( stament );

    if ( mnowLine.isEmpty() ) {
      InputNextLineTomNowLine();
    } // if

    if ( wontelse ) {
      if ( IsIDInmNowLineFirst() ) {
        String gotID;
        gotID = GetIDTOETokenInmNowLine();
        if ( gotID.equals( "else" ) ) {
          stament.add( new TOKEN( gotID, Global.s_T_ELSE, mLineCount ) );
          return true;
        } // if
        else {
          mnowLine = gotID + " " + mnowLine;
          mLineCount = 1;
          return false;
        } // else
      } // if
      else
        return false;
    } // if

    while ( true ) {
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
          mLineCount = 1;
          return true;
        } // else if
        else if ( mnowLine.charAt( 0 ) == ',' ) {
          mBuffer.add( new TOKEN( ",", Global.s_T_COMMA, mLineCount ) );
          IsGotTokenProcessFormNowLine( 1 );
          // IsReppet();
        } // else if
        else if ( mnowLine.charAt( 0 ) == '?' || mnowLine.charAt( 0 ) == ':' ) {
          mBuffer.add( new TOKEN( mnowLine.substring( 0, 1 ), Global.s_T_TERNARYOPERATOR, mLineCount ) );
          IsGotTokenProcessFormNowLine( 1 );
          TERNARYOPERATORFINDERROR();
        } // else if
        else if ( mnowLine.charAt( 0 ) == ';' ) {
          mBuffer.add( new TOKEN( ";", Global.s_T_SEMICOLON, mLineCount ) );
          IsGotTokenProcessFormNowLine( 1 );
          return Buffer1HasFullCommend( stament );
        } // else if
        else if ( IsBOOLEANCONDITION1InmNowLineFirst() ) {
          String gotBOOLEANCONDITION;
          gotBOOLEANCONDITION = mnowLine.substring( 0, 2 );
          mBuffer.add( new TOKEN( gotBOOLEANCONDITION, Global.s_T_BOOLEANCONDITION, mLineCount ) );
          IsGotTokenProcessFormNowLine( gotBOOLEANCONDITION.length() );
          IsReppet();
        } // else if
        else if ( IsASSISNOPERATORInmNowLineFirst() ) {
          String gotASSISNOPERATOR = mnowLine.substring( 0, 2 );
          mBuffer.add( new TOKEN( gotASSISNOPERATOR, Global.s_T_ASSIGNOPERATOR, mLineCount ) );
          IsGotTokenProcessFormNowLine( gotASSISNOPERATOR.length() );
          IsReppet();
        } // else if
        else if ( IsOPERATORInmNowLineFirst() ) {
          String gotOPERATOR = GetOPERATORToken();
          mBuffer.add( new TOKEN( gotOPERATOR, Global.s_T_OPERATOR, mLineCount ) );
          IsGotTokenProcessFormNowLine( gotOPERATOR.length() );
          OPERATORFINDERROR( gotOPERATOR );
          // IsReppet();
        } // else if
        else if ( IsBOOLEANRELATIONALInmNowLineFirst() ) {
          String gotBOOLEANRELATIONAL = GetBOOLEANRELATIONALToken();
          mBuffer.add( new TOKEN( gotBOOLEANRELATIONAL, Global.s_T_BOOLEANRELATIONAL, mLineCount ) );
          IsGotTokenProcessFormNowLine( gotBOOLEANRELATIONAL.length() );
          OPERATORFINDERROR( gotBOOLEANRELATIONAL );
          IsReppet();
        } // else if
        else if ( mnowLine.charAt( 0 ) == '!' ) {
          mBuffer.add( new TOKEN( mnowLine.substring( 0, 1 ),
                                  Global.s_T_BOOLEANCONDITION, mLineCount ) );
          IsGotTokenProcessFormNowLine( 1 );
          IsReppet();
        } // else if
        else if ( mnowLine.charAt( 0 ) == '=' ) {
          mBuffer.add( new TOKEN( mnowLine.substring( 0, 1 ), Global.s_T_ASSIGN, mLineCount ) );
          IsGotTokenProcessFormNowLine( 1 );
          // AssignFindError();
        } // else if
        else if ( IsCONSTANTInmNowLineFirst() ) {
          String gotCONSTANT;
          gotCONSTANT = GetCONSTANTTokenInmNowLine();
          mBuffer.add( new TOKEN( gotCONSTANT, Global.s_T_CONSTANT, mLineCount ) );
          CONSTANTFINDERROR( gotCONSTANT );
          IsReppet();
        } // else if
        else if ( IsIDInmNowLineFirst() ) {
          String gotID;
          gotID = GetIDTOETokenInmNowLine();
          DISTINGUISHANDPUSHTOKEN( gotID );
          // ----------
          if ( IsOurcComm() )
            return Buffer1HasFullCommend( stament );
          // ----------
          if ( ! CinCoutFirst() )
            IDUUDEFINED();
          // VOIDTYPENOTONFIRST();
          // thisfun
          if ( gotID.equals( "do" ) || gotID.equals( "else" ) ) {
            HASOTHERTOKENISERROR();
            stament.add( mBuffer.get( 0 ) );
            mBuffer.clear();
            return true;
          } // if


        } // else if
        else {
          if ( ! mnowLine.isEmpty() ) {
            System.out.println( "Line " + mLineCount + " : " + "unrecognized token with first char : '" +
                                mnowLine.charAt( 0 ) + "'" );
            // mBuffer.clear();
            throw new Throwable();
          } // if

        } // else

      }
      catch ( Throwable throwable ) {
        mnowLine = new String();
        mBuffer.clear();
        return false;
      } // catch

    } // while

  } // Cutting()

  public void ReturnmBuffer2( Vector<TOKEN> stament ) throws Throwable {
    mBuffer2 = new Vector<TOKEN>();
    for ( int i = 0 ; i < stament.size() ; i++ )
      mBuffer2.add( stament.get( i ) );

  } // ReturnmBuffer2()

  private boolean IsOurcComm() throws Throwable {
    if ( mBuffer.size() > 1 || mBuffer.get( 0 ).GetType() != Global.s_T_ID )
      return false;
    else {
      String funname = mBuffer.get( 0 ).GetToken();
      if ( funname.equals( "Done" ) ) {
        DoneFirst();
        return true;
      } // if
      else if ( funname.equals( "ListAllVariables" ) ) {
        ListAllVariablesFirst();
        return true;
      } // else if
      else if ( funname.equals( "ListAllFunctions" ) ) {
        ListAllFunctionsFirst();
        return true;
      } // else if
      else if ( funname.equals( "ListVariable" ) ) {
        ListVariableFirst();
        return true;
      } // else if
      else if ( funname.equals( "ListFunction" ) ) {
        ListFunctionFirst();
        return true;
      } // else if

    } // else

    return false;
  } // IsOurcComm()

  private void GetToken() throws Throwable {
    if ( mnowLine.isEmpty() ) {
      InputNextLineTomNowLine();
    } // if

    if ( mnowLine.charAt( 0 ) == '(' ) {
      mBuffer.add( new TOKEN( "(", Global.s_T_SMALL_LEFT_PAREN, mLineCount ) );
      IsGotTokenProcessFormNowLine( 1 );
      // SMALLLEFTPARENFINDERROR();
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
      HASOTHERTOKENISERROR();
    } // else if
    else if ( mnowLine.charAt( 0 ) == '}' ) {
      mBuffer.add( new TOKEN( "}", Global.s_T_BIG_RIGHT_PAREN, mLineCount ) );
      IsGotTokenProcessFormNowLine( 1 );
      HASOTHERTOKENISERROR();
    } // else if
    else if ( mnowLine.charAt( 0 ) == ',' ) {
      mBuffer.add( new TOKEN( ",", Global.s_T_COMMA, mLineCount ) );
      IsGotTokenProcessFormNowLine( 1 );
      IsReppet();
    } // else if
    else if ( mnowLine.charAt( 0 ) == '?' || mnowLine.charAt( 0 ) == ':' ) {
      mBuffer.add( new TOKEN( mnowLine.substring( 0, 1 ), Global.s_T_TERNARYOPERATOR, mLineCount ) );
      IsGotTokenProcessFormNowLine( 1 );
      TERNARYOPERATORFINDERROR();
    } // else if
    else if ( mnowLine.charAt( 0 ) == ';' ) {
      mBuffer.add( new TOKEN( ";", Global.s_T_SEMICOLON, mLineCount ) );
      IsGotTokenProcessFormNowLine( 1 );
    } // else if
    else if ( IsBOOLEANCONDITION1InmNowLineFirst() ) {
      String gotBOOLEANCONDITION;
      gotBOOLEANCONDITION = mnowLine.substring( 0, 2 );
      mBuffer.add( new TOKEN( gotBOOLEANCONDITION, Global.s_T_BOOLEANCONDITION, mLineCount ) );
      IsGotTokenProcessFormNowLine( gotBOOLEANCONDITION.length() );
      IsReppet();
    } // else if
    else if ( IsASSISNOPERATORInmNowLineFirst() ) {
      String gotASSISNOPERATOR = mnowLine.substring( 0, 2 );
      mBuffer.add( new TOKEN( gotASSISNOPERATOR, Global.s_T_ASSIGNOPERATOR, mLineCount ) );
      IsGotTokenProcessFormNowLine( gotASSISNOPERATOR.length() );
      IsReppet();
    } // else if
    else if ( IsOPERATORInmNowLineFirst() ) {
      String gotOPERATOR = GetOPERATORToken();
      mBuffer.add( new TOKEN( gotOPERATOR, Global.s_T_OPERATOR, mLineCount ) );
      IsGotTokenProcessFormNowLine( gotOPERATOR.length() );
      OPERATORFINDERROR( gotOPERATOR );
      // IsReppet();
    } // else if
    else if ( IsBOOLEANRELATIONALInmNowLineFirst() ) {
      String gotBOOLEANRELATIONAL = GetBOOLEANRELATIONALToken();
      mBuffer.add( new TOKEN( gotBOOLEANRELATIONAL, Global.s_T_BOOLEANRELATIONAL, mLineCount ) );
      IsGotTokenProcessFormNowLine( gotBOOLEANRELATIONAL.length() );
      OPERATORFINDERROR( gotBOOLEANRELATIONAL );
      IsReppet();
    } // else if
    else if ( mnowLine.charAt( 0 ) == '!' ) {
      mBuffer.add( new TOKEN( mnowLine.substring( 0, 1 ),
                              Global.s_T_BOOLEANCONDITION, mLineCount ) );
      IsGotTokenProcessFormNowLine( 1 );
      IsReppet();
    } // else if
    else if ( mnowLine.charAt( 0 ) == '=' ) {
      mBuffer.add( new TOKEN( mnowLine.substring( 0, 1 ), Global.s_T_ASSIGN, mLineCount ) );
      IsGotTokenProcessFormNowLine( 1 );
      IsReppet();
    } // else if
    else if ( IsCONSTANTInmNowLineFirst() ) {
      String gotCONSTANT;
      gotCONSTANT = GetCONSTANTTokenInmNowLine();
      mBuffer.add( new TOKEN( gotCONSTANT, Global.s_T_CONSTANT, mLineCount ) );
      CONSTANTFINDERROR( gotCONSTANT );
      IsReppet();
    } // else if
    else if ( IsIDInmNowLineFirst() ) {
      String gotID;
      gotID = GetIDTOETokenInmNowLine();
      DISTINGUISHANDPUSHTOKEN( gotID );
      // IDUUDEFINED();
      // VOIDTYPENOTONFIRST();
    } // else if
    else {
      if ( ! mnowLine.isEmpty() ) {
        System.out.println( "Line " + mLineCount + " : " + "unrecognized token with first char : '" +
                            mnowLine.charAt( 0 ) + "'" );
        // mBuffer.clear();
        throw new Throwable();
      } // if

    } // else

  } // GetToken()

  private void DoneFirst() throws Throwable {
    try {
      GetToken();
      if ( mBuffer.get( mBuffer.size() - 1 ).GetToken().equals( "(" ) ) {
        GetToken();
        if ( mBuffer.get( mBuffer.size() - 1 ).GetToken().equals( ")" ) ) {
          GetToken();
          if ( mBuffer.get( mBuffer.size() - 1 ).GetToken().equals( ";" ) ) {
            ;
          } // if
          else {
            throw new Throwable();
          } // else
        } // if
        else {
          throw new Throwable();
        } // else
      } // if
      else {
        throw new Throwable();
      } // else

    } // try
    catch ( Throwable throwable ) {
      System.out.println( "Line " + mLineCount + " : " + "unexpected token : '"
                          + mBuffer.get( mBuffer.size() - 1 ).GetToken() + "'" );
      // System.out.print( "> " );
      mBuffer.clear();
      throw new Throwable();

    } // catch

  } // DoneFirst()

  private void ListAllVariablesFirst() throws Throwable {
    try {
      GetToken();
      if ( mBuffer.get( mBuffer.size() - 1 ).GetToken().equals( "(" ) ) {
        GetToken();
        if ( mBuffer.get( mBuffer.size() - 1 ).GetToken().equals( ")" ) ) {
          GetToken();
          if ( mBuffer.get( mBuffer.size() - 1 ).GetToken().equals( ";" ) ) {
            ;
          } // if
          else {
            throw new Throwable();
          } // else
        } // if
        else {
          throw new Throwable();
        } // else
      } // if
      else {
        throw new Throwable();
      } // else

    } // try
    catch ( Throwable throwable ) {
      System.out.println( "Line " + mLineCount + " : " + "unexpected token : '"
                          + mBuffer.get( mBuffer.size() - 1 ).GetToken() + "'" );
      // System.out.print( "> " );
      mBuffer.clear();
      throw new Throwable();

    } // catch

  } // ListAllVariablesFirst()

  private void ListAllFunctionsFirst() throws Throwable {
    try {
      GetToken();
      if ( mBuffer.get( mBuffer.size() - 1 ).GetToken().equals( "(" ) ) {
        GetToken();
        if ( mBuffer.get( mBuffer.size() - 1 ).GetToken().equals( ")" ) ) {
          GetToken();
          if ( mBuffer.get( mBuffer.size() - 1 ).GetToken().equals( ";" ) ) {
            ;
          } // if
          else {
            throw new Throwable();
          } // else
        } // if
        else {
          throw new Throwable();
        } // else
      } // if
      else {
        throw new Throwable();
      } // else

    } // try
    catch ( Throwable throwable ) {
      System.out.println( "Line " + mLineCount + " : " + "unexpected token : '"
                          + mBuffer.get( mBuffer.size() - 1 ).GetToken() + "'" );
      // System.out.print( "> " );
      mBuffer.clear();
      throw new Throwable();

    } // catch

  } // ListAllFunctionsFirst()

  private void ListVariableFirst() throws Throwable {
    try {
      GetToken();
      if ( mBuffer.get( mBuffer.size() - 1 ).GetToken().equals( "(" ) ) {
        GetToken();
        if ( mBuffer.get( mBuffer.size() - 1 ).GetType() == Global.s_T_CONSTANT ) {
          if ( mBuffer.get( mBuffer.size() - 1 ).GetToken().charAt( 0 ) != '"' )
            throw new Throwable();

          GetToken();
          if ( mBuffer.get( mBuffer.size() - 1 ).GetToken().equals( ")" ) ) {
            GetToken();
            if ( mBuffer.get( mBuffer.size() - 1 ).GetToken().equals( ";" ) ) {
              ;
            } // if
            else {
              throw new Throwable();
            } // else
          } // if
          else {
            throw new Throwable();
          } // else
        } // if
        else {
          throw new Throwable();
        } // else
      } // if
      else {
        throw new Throwable();
      } // else

    } // try
    catch ( Throwable throwable ) {
      System.out.println( "Line " + mLineCount + " : " + "unexpected token : '"
                          + mBuffer.get( mBuffer.size() - 1 ).GetToken() + "'" );
      // System.out.print( "> " );
      mBuffer.clear();
      throw new Throwable();

    } // catch

  } // ListVariableFirst()

  private void ListFunctionFirst() throws Throwable {
    try {
      GetToken();
      if ( mBuffer.get( mBuffer.size() - 1 ).GetToken().equals( "(" ) ) {
        GetToken();
        if ( mBuffer.get( mBuffer.size() - 1 ).GetType() == Global.s_T_CONSTANT ) {
          if ( mBuffer.get( mBuffer.size() - 1 ).GetToken().charAt( 0 ) != '"' )
            throw new Throwable();

          GetToken();
          if ( mBuffer.get( mBuffer.size() - 1 ).GetToken().equals( ")" ) ) {
            GetToken();
            if ( mBuffer.get( mBuffer.size() - 1 ).GetToken().equals( ";" ) ) {
              ;
            } // if
            else {
              throw new Throwable();
            } // else
          } // if
          else {
            throw new Throwable();
          } // else
        } // if
        else {
          throw new Throwable();
        } // else
      } // if
      else {
        throw new Throwable();
      } // else

    } // try
    catch ( Throwable throwable ) {
      System.out.println( "Line " + mLineCount + " : " + "unexpected token : '"
                          + mBuffer.get( mBuffer.size() - 1 ).GetToken() + "'" );
      // System.out.print( "> " );
      mBuffer.clear();
      throw new Throwable();
    } // catch

  } // ListFunctionFirst()

  private boolean CinCoutFirst() throws Throwable {
    if ( mBuffer.size() == 1 && mBuffer.get( 0 ).GetToken().equals( "cin" ) ) {
      GetToken();
      if ( ! mBuffer.get( mBuffer.size() - 1 ).GetToken().equals( ">>" ) ) {
        System.out.println( "Line " + mBuffer.get( 1 ).Getline() + " : " + "unexpected token : '"
                            + mBuffer.get( 1 ).GetToken() + "'" );
        throw new Throwable();
        /*
        if ( Global.G_FindVariable( Global.s_Variables, "cin" ) == null ) {
          System.out.println( "Line " + mBuffer.get( 1 ).Getline() + " : " + "unexpected token : '"
                              + mBuffer.get( 1 ).GetToken() + "'" );
          throw new Throwable();
        } // if

        return true;
        */
      } // if
      else {
        return true;
      } // else
    } // if
    else if ( mBuffer.size() == 1 && mBuffer.get( 0 ).GetToken().equals( "cout" ) ) {
      GetToken();
      if ( ! mBuffer.get( mBuffer.size() - 1 ).GetToken().equals( "<<" ) ) {
        System.out.println( "Line " + mBuffer.get( 1 ).Getline() + " : " + "unexpected token : '"
                            + mBuffer.get( 1 ).GetToken() + "'" );
        throw new Throwable();
        /*
        if ( Global.G_FindVariable( Global.s_Variables, "cout" ) == null ) {
          System.out.println( "Line " + mBuffer.get( 1 ).Getline() + " : " + "unexpected token : '"
                              + mBuffer.get( 1 ).GetToken() + "'" );
          throw new Throwable();
        } // if

        return true;
        */
      } // if
      else {
        return true;
      } // else
    } // else if
    else
      return false;

  } // CinCoutFirst()

  private void AssignFindError() throws Throwable {
    if ( mBuffer.size() > 2 || mBuffer.size() == 1 ) {
      System.out.println( "Line " + mLineCount + " : " + "unexpected token : '"
                          + mBuffer.get( mBuffer.size() - 1 ).GetToken() + "'" );
      mBuffer.clear();
      throw new Throwable();
    } // if

    if ( mBuffer.get( 0 ).GetType() != Global.s_T_ID ) {
      System.out.println( "Line " + mLineCount + " : " + "unexpected token : '"
                          + mBuffer.get( mBuffer.size() - 1 ).GetToken() + "'" );
      mBuffer.clear();
      throw new Throwable();
    } // if

  } // AssignFindError()

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

  protected void IDUUDEFINED() throws Throwable {
    if ( mBuffer.get( mBuffer.size() - 1 ).GetType() == Global.s_T_ID ) {
      if ( mBuffer.size() == 1 ) {
        if ( ! Global.G_IDHASDEFINED( mBuffer.get( mBuffer.size() - 1 ).GetToken() ) ) {
          System.out.println( "Line " + mLineCount + " : " + "undefined identifier : '"
                              + mBuffer.get( mBuffer.size() - 1 ).GetToken() + "'" );
          // System.out.print( "> " );
          mBuffer.clear();
          throw new Throwable();
        } // if
      } // if
      else if ( ! ( mBuffer.get( 0 ).GetType() == Global.s_T_TYPE ) &&
                ! ( mBuffer.get( 0 ).GetType() == Global.s_T_VOID ) ) {
        if ( ! Global.G_IDHASDEFINED( mBuffer.get( mBuffer.size() - 1 ).GetToken() ) ) {
          System.out.println( "Line " + mLineCount + " : " + "undefined identifier : '"
                              + mBuffer.get( mBuffer.size() - 1 ).GetToken() + "'" );
          // System.out.print( "> " );
          mBuffer.clear();
          throw new Throwable();
        } // else if
      } // else if
    } // if

  } // IDUUDEFINED()

  protected void VOIDTYPENOTONFIRST() throws Throwable {
    if ( ( mBuffer.get( mBuffer.size() - 1 ).GetType() == Global.s_T_VOID ||
           mBuffer.get( mBuffer.size() - 1 ).GetType() == Global.s_T_IF ||
           mBuffer.get( mBuffer.size() - 1 ).GetType() == Global.s_T_WHILE ||
           mBuffer.get( mBuffer.size() - 1 ).GetType() == Global.s_T_DO ||
           mBuffer.get( mBuffer.size() - 1 ).GetType() == Global.s_T_ELSE ) && mBuffer.size() != 1 ) {
      System.out.println( "Line " + mLineCount + " : " + "unexpected token : '"
                          + mBuffer.get( mBuffer.size() - 1 ).GetToken() + "'" );
      mBuffer.clear();
      throw new Throwable();
    } // if

  } // VOIDTYPENOTONFIRST()

  protected void HASOTHERTOKENISERROR() throws Throwable {
    if ( mBuffer.size() > 1 ) {
      System.out.println( "Line " + mLineCount + " : " + "unexpected token : '"
                          + mBuffer.get( mBuffer.size() - 1 ).GetToken() + "'" );
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
      if ( mBuffer.size() > 3 ) {
        if ( mBuffer.get( 2 ).GetType() == Global.s_T_SMALL_LEFT_PAREN &&
             mBuffer.get( 1 ).GetType() == Global.s_T_ID &&
             ( mBuffer.get( 0 ).GetType() == Global.s_T_TYPE ||
               mBuffer.get( 0 ).GetType() == Global.s_T_VOID ) ) {
          return true;
        } // if
      } // if

      if ( mBuffer.size() > 2 ) {
        if ( mBuffer.get( 1 ).GetType() == Global.s_T_SMALL_LEFT_PAREN &&
             ( mBuffer.get( 0 ).GetType() == Global.s_T_IF ||
               mBuffer.get( 0 ).GetType() == Global.s_T_WHILE ) ) {
          return true;
        } // if
      } // if

    } // if

    return false;
  } // ISFUNDEFORIFWHILEEND()

  protected boolean IsDefStam() throws Throwable {
    try {
      for ( int i = mBuffer.size() - 2 ; i >= 0 ; i = i - 2 ) {
        if ( mBuffer.get( i ).GetType() == Global.s_T_TYPE ) {
          return true;
        } // if
        else if ( mBuffer.get( i ).GetType() == Global.s_T_COMMA ) {
          if ( ! ( mBuffer.get( i - 1 ).GetType() == Global.s_T_ID ) ) {
            return false;
          } // if
        } // else if
        else
          return false;
      } // for
    } // try
    catch ( Throwable throwable ) {
      return false;
    } // catch()
    return false;
  } // IsDefStam()

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

        /*
        if ( ! isnnotAll0orp ) {
          System.out.println( "Error" );
          // System.out.print( "> " );
          mBuffer.clear();
          throw new Throwable();
        } // if
        */

      } // if

    } // if

  } // CONSTANTFINDERROR()

  protected void MIDLEFTPARENFINDERROR() throws Throwable {
    if ( mBuffer.size() == 1 ) {
      System.out.println( "Line " + mLineCount + " : " + "unexpected token : '"
                          + mBuffer.get( mBuffer.size() - 1 ).GetToken() + "'" );
      // System.out.print( "> " );
      mBuffer.clear();
      throw new Throwable();
    } // if
    else if ( mBuffer.get( mBuffer.size() - 2 ).GetType() != Global.s_T_ID ) {
      System.out.println( "Line " + mLineCount + " : " + "unexpected token : '"
                          + mBuffer.get( mBuffer.size() - 1 ).GetToken() + "'" );
      // System.out.print( "> " );
      mBuffer.clear();
      throw new Throwable();
    } // else if

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
    int gotPoint = 0;

    if ( IsNumberAndNumberLegalWordInmNowLineFirstChar() || mnowLine.charAt( 0 ) == '.' ) {
      while ( gotPoint < 2 &&
              ( IsNumberAndNumberLegalWordInmNowLineFirstChar() || mnowLine.charAt( 0 ) == '.' ) ) {
        if ( mnowLine.charAt( 0 ) == '.' && gotPoint == 1 )
          gotPoint++;
        else {
          if ( mnowLine.charAt( 0 ) == '.' )
            gotPoint++;
          gotCONSTANT = gotCONSTANT + mnowLine.substring( 0, 1 );
          RemoveFirstCherFormNowLine();
          if ( mnowLine.length() == 0 )
            gotPoint = 2;
        } // else
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
        System.out.println( "Line " + mLineCount + " : " + "unrecognized token with first char : '\"'" );
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
        System.out.println( "Line " + mLineCount + " : " + "unrecognized token with first char : '''" );
        // System.out.print( "> " );
        mBuffer.clear();
        throw new Throwable();
      } // catch

    } // else if

    RemoveHeadWhiteCherFormNowLine();

    if ( gotCONSTANT.length() > 0 ) {
      if ( gotCONSTANT.charAt( 0 ) == '.' ) {
        System.out.println( "Line " + mLineCount + " : " + "unexpected token : '" + gotCONSTANT + "'" );
        // System.out.print( "> " );
        mBuffer.clear();
        throw new Throwable();
      } // if

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
    // mBuffer.clear();
    return false;

  } // Buffer1HasFullCommend()

  private boolean ReturnBuffer2Stament( Vector<TOKEN> buffer ) throws Throwable {

    if ( mBuffer2.size() > 0 ) {
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

  protected void IsReppet() throws Throwable {
    if ( mBuffer.size() > 1 ) {
      if ( mBuffer.get( mBuffer.size() - 1 ).GetType() == mBuffer.get( mBuffer.size() - 2 ).GetType() ) {
        System.out.println( "Line " + mLineCount + " : " + "unexpected token : '"
                            + mBuffer.get( mBuffer.size() - 1 ).GetToken() + "'" );
        throw new Throwable();
      } // if
    } // if

  } // IsReppet()

  protected void InputNextLineTomNowLine() throws Throwable {

    while ( ! msc.hasNext() ) {
      Global.sc = new Scanner( System.in );
      msc = Global.sc;
    } // while

    mnowLine = msc.nextLine();
    mLineCount += 1;
    mnowLine = RemoveCommend( mnowLine );
    RemoveHeadWhiteCherFormNowLine();
    RemoveTailWhiteCherFormNowLine();

    while ( mnowLine.isEmpty() ) {
      while ( ! msc.hasNext() ) {
        Global.sc = new Scanner( System.in );
        msc = Global.sc;
      } // while

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

  public void ClaerNowLine() throws Throwable {
    mnowLine = new String();
  } // ClaerNowLine()

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
          // System.out.println( "Token " +  m_step );
        } // if
        // System.out.print( "> " );
        m_cuttoken.ClaerNowLine();
        return false;
      } // else

    } // try
    catch ( Throwable throwable ) {
      if ( m_statement != null && ! m_statement.isEmpty() ) {
        System.out.println( "Line " + m_statement.get( m_step ).Getline() + " : " +
                            "unexpected token : '" + m_statement.get( m_step ).GetToken() + "'" );
        // System.out.println( "Token " +  m_step );
      } // if
      // System.out.print( "> " );
      m_cuttoken.ClaerNowLine();
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

      if ( Statement() ) {
        int it = 0;
        it++;
      } // if
      else if ( Definition() ) {
        int i = 0;
        i++;
      } // else if
      else {
        return false;
      } // else

      if ( IsStepEnd() ) {
        return true;
      } // if

      while ( Statement() || Definition() ) {
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
      throw new Throwable();
    } // catch
  } // Definition()

  private boolean Type_Specifier() throws Throwable {
    try {
      if ( m_statement.get( m_step ).GetType() == Global.s_T_TYPE ) {
        m_step += 1;
        return true;
      } // if
      else
        return false;
    } // try
    catch ( Throwable throwable ) {
      throw new Throwable();
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
      throw new Throwable();
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
          if ( m_statement.get( m_step ).GetType() == Global.s_T_MID_LEFT_PAREN ) {
            m_step += 1;
            if ( m_statement.get( m_step ).GetType() == 2 ) { // Constant
              m_step += 1;
              if ( m_statement.get( m_step ).GetType() == Global.s_T_MID_RIGHT_PAREN ) {
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
           m_statement.get( m_step ).GetType() == Global.s_T_SEMICOLON ) {
        m_step += 1;
        return true;
      } // if
      else {
        return false;
      } // else
    } // try
    catch ( Throwable throwable ) {
      throw new Throwable();
    } // catch
  } // Rest_of_Declarators()

  private boolean Function_Definition_Without_ID() throws Throwable {
    try {
      int sMALL_LEFT_PAREN = 0;
      int sMALL_RIGHT_PAREN = 0;

      if ( m_statement.get( m_step ).GetType() == Global.s_T_SMALL_LEFT_PAREN ) {
        m_step += 1;
        sMALL_LEFT_PAREN = m_step;
        if ( m_statement.get( m_step ).GetType() == Global.s_T_VOID ) {
          m_step += 1;
        } // if
        else if ( Formal_Parameter_List() ) {
          ;
        } // else if

        if ( ! ( m_statement.get( m_step ).GetType() == Global.s_T_SMALL_RIGHT_PAREN ) )
          return false;
        else
          m_step++;

      } // if
      else {
        return false;
      } // else

      sMALL_RIGHT_PAREN = m_step - 1;

      Global.s_Variables.add( new VarList() );

      if ( m_statement.get( 0 ).GetType() == Global.s_T_VOID )
        Global.s_Fundefin = new Function( m_statement.get( 1 ).GetToken(), new Vector<Variable>(),
                                          new Vector<Stament>() );
      else {
        if ( m_statement.get( 0 ).GetToken().equals( "int" ) )
          Global.s_Fundefin = new Function( Global.s_V_INT, m_statement.get( 1 ).GetToken(),
                                            new Vector<Variable>(), new Vector<Stament>() );
        else if ( m_statement.get( 0 ).GetToken().equals( "string" ) )
          Global.s_Fundefin = new Function( Global.s_V_STRING, m_statement.get( 1 ).GetToken(),
                                            new Vector<Variable>(), new Vector<Stament>() );
        else if ( m_statement.get( 0 ).GetToken().equals( "float" ) )
          Global.s_Fundefin = new Function( Global.s_V_FLOAT, m_statement.get( 1 ).GetToken(),
                                            new Vector<Variable>(), new Vector<Stament>() );
        else if ( m_statement.get( 0 ).GetToken().equals( "char" ) )
          Global.s_Fundefin = new Function( Global.s_V_CHAR, m_statement.get( 1 ).GetToken(),
                                            new Vector<Variable>(), new Vector<Stament>() );
        else if ( m_statement.get( 0 ).GetToken().equals( "bool" ) )
          Global.s_Fundefin = new Function( Global.s_V_BOOL, m_statement.get( 1 ).GetToken(),
                                            new Vector<Variable>(), new Vector<Stament>() );

      } // else

      for ( int i = sMALL_LEFT_PAREN ; i < sMALL_RIGHT_PAREN ; i = i ) {
        Vector<TOKEN> var = new Vector<TOKEN>();
        while ( i < sMALL_RIGHT_PAREN && m_statement.get( i ).GetType() != Global.s_T_COMMA ) {
          var.add( m_statement.get( i ) );
          i++;
        } // while

        if ( m_statement.get( i ).GetType() == Global.s_T_COMMA )
          i++;

        VarDefin( var );
      } // for

      for ( int i = 0 ; i < Global.s_Fundefin.m_localVarList.size() ; i++ )
        Global.G_AddVariable( Global.s_Fundefin.m_localVarList.get( i ) );


      m_statement = new Vector<TOKEN>();
      m_step = 0;
      if ( m_cuttoken.Cutting( m_statement, true, false ) ) {
        if ( Compound_Statement() ) {
          if ( Global.s_Fundefin != null ) {
            int i = Global.s_Fundefin.m_commLine.size();
            Global.s_Fundefin.m_commLine.add( new Stament() );
            Global.s_Fundefin.m_commLine.get( i ).m_Line.add( m_statement.get( m_step - 1 ) );
          } // if

          Global.s_Variables.remove( Global.s_Variables.size() - 1 );
          return true;
        } // if
        else {
          Global.s_Variables.remove( Global.s_Variables.size() - 1 );
          return false;
        } // else
      } // if
      else {
        Global.s_Variables.remove( Global.s_Variables.size() - 1 );
        return false;
      } // else
    } // try
    catch ( Throwable throwable ) {
      Global.G_ClearVars();
      throw new Throwable();
    } // catch

  } // Function_Definition_Without_ID()

  private boolean Formal_Parameter_List() throws Throwable {
    try {
      if ( ! Type_Specifier() )
        return false;
      if ( m_statement.get( m_step ).GetToken().equals( "&" ) )
        m_step += 1;
      if ( m_statement.get( m_step ).GetType() == Global.s_T_ID ) // ID
        m_step += 1;
      else
        return false;

      if ( m_statement.get( m_step ).GetType() == Global.s_T_MID_LEFT_PAREN ) {
        m_step += 1;
        if ( m_statement.get( m_step ).GetType() == Global.s_T_CONSTANT ) {
          m_step += 1;
        } // if
        else
          return false;
        if ( m_statement.get( m_step ).GetType() == Global.s_T_MID_RIGHT_PAREN ) {
          m_step += 1;
        } // if
        else
          return false;
      } // if

      while ( m_statement.get( m_step ).GetType() == Global.s_T_COMMA ) {
        m_step += 1;
        if ( ! Type_Specifier() )
          return false;
        if ( m_statement.get( m_step ).GetToken().equals( "&" ) )
          m_step += 1;
        if ( m_statement.get( m_step ).GetType() == Global.s_T_ID ) // ID
          m_step += 1;
        else
          return false;

        if ( m_statement.get( m_step ).GetType() == Global.s_T_MID_LEFT_PAREN ) {
          m_step += 1;
          if ( m_statement.get( m_step ).GetType() == Global.s_T_CONSTANT ) {
            m_step += 1;
          } // if
          else
            return false;
          if ( m_statement.get( m_step ).GetType() == Global.s_T_MID_RIGHT_PAREN ) {
            m_step += 1;
          } // if
          else
            return false;
        } // if
      } // while

      return true;
    } // try
    catch ( Throwable throwable ) {
      throw new Throwable();
    } // catch

  } // Formal_Parameter_List()

  private boolean Compound_Statement() throws Throwable {
    try {
      if ( m_statement.get( m_step ).GetToken().equals( "{" ) &&
           m_statement.get( m_step ).GetType() == 7 ) {
        if ( Global.s_Fundefin != null ) {
          int i = Global.s_Fundefin.m_commLine.size();
          Global.s_Fundefin.m_commLine.add( new Stament() );
          Global.s_Fundefin.m_commLine.get( i ).m_Line.add( m_statement.get( m_step ) );
        } // if

        m_step += 1;
        Global.s_Variables.add( new VarList() );
        boolean isHave = false;

        m_statement = new Vector<TOKEN>();
        m_step = 0;

        if ( m_cuttoken.Cutting( m_statement, true, false ) ) {
          isHave = true;
        } // if

        while ( isHave ) {
          boolean issucess = false;
          if ( ConditionalExpressionsStatement() || Declaration() ) {
            Excute excute = new Excute( m_statement );
            if ( ! m_statement.isEmpty() ) {
              if ( excute.ExcuteComm( false ) ) {
                if ( Global.s_Fundefin != null )
                  Global.s_Fundefin.m_commLine.add( new Stament( m_statement ) );
                issucess = true;
                isHave = false;
              } // if
              else {
                Global.s_Variables.remove( Global.s_Variables.size() - 1 );
                throw new Throwable();
              } // else
            } // if
            else {
              issucess = true;
              isHave = false;
            } // else

          } // if
          else {
            if ( m_statement.get( 0 ).GetType() != Global.s_T_BIG_RIGHT_PAREN ) {
              Global.G_ClearVars();
              throw new Throwable();
            } // if

            issucess = false;
            isHave = false;
          } // elseå

          if ( issucess ) {
            m_statement = new Vector<TOKEN>();
            m_step = 0;
            if ( m_cuttoken.Cutting( m_statement, true, false ) )
              // 應該要簪測有沒有}
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
      throw new Throwable();
    } // catch()
  } // Compound_Statement()

  private boolean Declaration() throws Throwable {
    try {
      if ( Type_Specifier() ) {
        // m_step += 1;
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
      throw new Throwable();
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
        Expression();
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
      else if ( m_statement.get( m_step ).GetType() == Global.s_T_IF ) {
        m_step += 1;
        if ( m_statement.get( m_step ).GetToken().equals( "(" ) &&
             m_statement.get( m_step ).GetType() == 3 ) {
          m_step += 1;
          if ( Expression() ) {
            // m_step += 1;
            if ( m_statement.get( m_step ).GetToken().equals( ")" ) &&
                 m_statement.get( m_step ).GetType() == 4 ) {
              m_step += 1;
              if ( Global.s_Fundefin != null )
                Global.s_Fundefin.m_commLine.add( new Stament( m_statement ) );
              m_statement = new Vector<TOKEN>();
              m_step = 0;
              if ( m_cuttoken.Cutting( m_statement, true, false ) ) {
                if ( ConditionalExpressionsStatement() ) {
                  // m_step += 1; // 可能會out of range
                  if ( Global.s_Fundefin != null )
                    Global.s_Fundefin.m_commLine.add( new Stament( m_statement ) );

                  Vector<TOKEN> pm_statement = m_statement;
                  int pm_step = m_step;
                  m_statement = new Vector<TOKEN>();
                  m_step = 0;

                  if ( m_cuttoken.Cutting( m_statement, true, true ) ) { // else
                    m_step += 1;

                    if ( Global.s_Fundefin != null )
                      Global.s_Fundefin.m_commLine.add( new Stament( m_statement ) );
                    m_statement = new Vector<TOKEN>();
                    m_step = 0;
                    if ( m_cuttoken.Cutting( m_statement, true, false ) ) {
                      if ( ConditionalExpressionsStatement() ) {
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
                    m_statement = new Vector<TOKEN>();
                    m_step = 0;
                  } // else

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
              if ( m_cuttoken.Cutting( m_statement, true, false ) ) {
                if ( ConditionalExpressionsStatement() ) {
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
        if ( m_cuttoken.Cutting( m_statement, true, false ) ) {
          if ( ConditionalExpressionsStatement() ) {
            // m_step += 1;
            m_statement = new Vector<TOKEN>();
            m_step = 0;
            if ( m_cuttoken.Cutting( m_statement, true, false ) ) {
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
                      m_statement = new Vector<TOKEN>();
                      m_step = 0;
                      if ( m_cuttoken.Cutting( m_statement, true, false ) ) {
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
      throw new Throwable();
    } // catch()

  } // Statement()

  private boolean ConditionalExpressionsStatement() throws Throwable {
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
          Excute excute = new Excute( m_statement );
          // Global.s_Variables.add( new VarList() );
          if ( excute.ExcuteComm( false ) ) {
            // Global.s_Variables.remove( Global.s_Variables.size() - 1 );
            return true;
          } // if
          else {
            // Global.s_Variables.remove( Global.s_Variables.size() - 1 );
            throw new Throwable();
          } // else
        } // if
        else {
          return false;
        } // else
      } // else if
      else if ( m_statement.get( m_step ).GetToken().equals( "return" ) ) {
        m_step += 1;
        Expression();
        if ( m_statement.get( m_step ).GetToken().equals( ";" ) &&
             m_statement.get( m_step ).GetType() == 21 ) {
          m_step += 1;
          Excute excute = new Excute( m_statement );
          Global.s_Variables.add( new VarList() );
          if ( excute.ExcuteComm( false ) ) {
            // Global.s_Variables.remove( Global.s_Variables.size() - 1 );
            /*
            if ( Global.s_Fundefin != null )
              Global.s_Fundefin.m_commLine.add( new Stament( m_statement ) );
            */
            return true;
          } // if
          else {
            // Global.s_Variables.remove( Global.s_Variables.size() - 1 );
            return false;
          } // else
        } // if
        else {
          return false;
        } // else
      } // else if
      else if ( Compound_Statement() ) {
        return true;
      } // else if
      else if ( m_statement.get( m_step ).GetType() == Global.s_T_IF ) {
        m_step += 1;
        if ( m_statement.get( m_step ).GetToken().equals( "(" ) &&
             m_statement.get( m_step ).GetType() == 3 ) {
          m_step += 1;
          if ( Expression() ) {
            // m_step += 1;
            if ( m_statement.get( m_step ).GetToken().equals( ")" ) &&
                 m_statement.get( m_step ).GetType() == 4 ) {
              m_step += 1;
              if ( Global.s_Fundefin != null )
                Global.s_Fundefin.m_commLine.add( new Stament( m_statement ) );
              m_statement = new Vector<TOKEN>();
              m_step = 0;
              if ( m_cuttoken.Cutting( m_statement, true, false ) ) {
                if ( ConditionalExpressionsStatement() ) {
                  // m_step += 1; // 可能會out of range
                  if ( Global.s_Fundefin != null )
                    Global.s_Fundefin.m_commLine.add( new Stament( m_statement ) );

                  Vector<TOKEN> pm_statement = m_statement;
                  int pm_step = m_step;
                  m_statement = new Vector<TOKEN>();
                  m_step = 0;

                  if ( m_cuttoken.Cutting( m_statement, true, true ) ) { // else
                    m_step += 1;

                    if ( Global.s_Fundefin != null )
                      Global.s_Fundefin.m_commLine.add( new Stament( m_statement ) );
                    m_statement = new Vector<TOKEN>();
                    m_step = 0;
                    if ( m_cuttoken.Cutting( m_statement, true, false ) ) {
                      if ( ConditionalExpressionsStatement() ) {
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
                    m_statement = new Vector<TOKEN>();
                    m_step = 0;
                  } // else

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
              if ( Global.s_Fundefin != null )
                Global.s_Fundefin.m_commLine.add( new Stament( m_statement ) );
              m_statement = new Vector<TOKEN>();
              m_step = 0;
              if ( m_cuttoken.Cutting( m_statement, true, false ) ) {
                if ( ConditionalExpressionsStatement() ) {
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
        if ( Global.s_Fundefin != null )
          Global.s_Fundefin.m_commLine.add( new Stament( m_statement ) );
        m_statement = new Vector<TOKEN>();
        m_step = 0;
        if ( m_cuttoken.Cutting( m_statement, true, false ) ) {
          if ( ConditionalExpressionsStatement() ) {
            // m_step += 1;
            if ( Global.s_Fundefin != null )
              Global.s_Fundefin.m_commLine.add( new Stament( m_statement ) );
            m_statement = new Vector<TOKEN>();
            m_step = 0;
            if ( m_cuttoken.Cutting( m_statement, true, false ) ) {
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
                      if ( Global.s_Fundefin != null )
                        Global.s_Fundefin.m_commLine.add( new Stament( m_statement ) );
                      m_statement = new Vector<TOKEN>();
                      m_step = 0;
                      if ( m_cuttoken.Cutting( m_statement, true, false ) ) {
                        if ( m_statement.get( m_step ).GetToken().equals( ";" ) &&
                             m_statement.get( m_step ).GetType() == 21 ) {
                          m_step += 1;
                          /*
                          if ( Global.s_Fundefin != null )
                            Global.s_Fundefin.m_commLine.add( new Stament( m_statement ) );
                          */
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
      throw new Throwable();
    } // catch()

  } // ConditionalExpressionsStatement()

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
      throw new Throwable();
    } // catch()
  } // Expression()

  private boolean Basic_Expression() throws Throwable {
    try {
      if ( m_statement.get( m_step ).GetType() == Global.s_T_ID ) { // ID
        m_step += 1;
        if ( Rest_of_Identifier_Started_Basic_Exp() ) {
          return true;
        } // if
        else {
          return false;
        } // else
      } // if
      else if ( m_statement.get( m_step ).GetToken().equals( "++" ) ||
                m_statement.get( m_step ).GetToken().equals( "--" ) ) {
        m_step += 1;
        if ( m_statement.get( m_step ).GetType() == Global.s_T_ID ) { // ID
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
      else if ( m_statement.get( m_step ).GetType() == Global.s_T_CONSTANT ) { // Constant
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
      throw new Throwable();
    } // catch()
  } // Basic_Expression()

  private boolean Rest_of_Identifier_Started_Basic_Exp() throws Throwable {
    try {
      if ( m_statement.get( m_step ).GetToken().equals( "[" ) ) {
        m_step += 1;
        if ( ! Expression() ) {
          return false;
        } // if

        if ( m_statement.get( m_step ).GetToken().equals( "]" ) ) {
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
      throw new Throwable();
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
      throw new Throwable();
    } // catch()
  } // Rest_of_PPMM_Identifier_Started_Basic_Exp()

  private boolean Sign() throws Throwable {
    try {
      if ( m_statement.get( m_step ).GetToken().equals( "+" ) ||
           m_statement.get( m_step ).GetToken().equals( "-" ) ||
           m_statement.get( m_step ).GetToken().equals( "!" ) ) {
        m_step += 1;
        return true;
      } // if
      else {
        return false;
      } // else
    } // try
    catch ( Throwable throwable ) {
      throw new Throwable();
    } // catch

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
      throw new Throwable();
    } // catch()

  } // Actual_Parameter_List()

  private boolean Assignment_Operator() throws Throwable {
    try {
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
    }
    catch ( Throwable throwable ) {
      throw new Throwable();
    } // catch

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
      throw new Throwable();
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

      while ( m_statement.get( m_step ).GetToken().equals( "||" ) ) { // 可能會out of range
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
      throw new Throwable();
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

      while ( m_statement.get( m_step ).GetToken().equals( "&&" ) ) { // maybe will out of range
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
      throw new Throwable();
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

      while ( m_statement.get( m_step ).GetToken().equals( "&&" ) ) { // maybe will out of range
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
      throw new Throwable();
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
      throw new Throwable();
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
      throw new Throwable();
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
      throw new Throwable();
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
      throw new Throwable();
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
      throw new Throwable();
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
      throw new Throwable();
    } // catch()
  } // Rest_of_Maybe_Bit_AND_Exp()

  private boolean Maybe_Equality_Exp() throws Throwable {
    try {
      if ( Maybe_Relational_Exp() ) {
        // m_step += 1;
      } // if
      else {
        return false;
      } // else

      if ( IsStepEnd() ) {
        return true;
      } // if

      while ( ( m_statement.get( m_step ).GetToken().equals( "==" ) ||
                m_statement.get( m_step ).GetToken().equals( "!=" ) ) ) {
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
      throw new Throwable();
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
                m_statement.get( m_step ).GetToken().equals( "!=" ) ) ) {
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
      throw new Throwable();
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
                m_statement.get( m_step ).GetToken().equals( ">=" ) ) ) {
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
      throw new Throwable();
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
                m_statement.get( m_step ).GetToken().equals( ">=" ) ) ) {
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
      throw new Throwable();
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
                m_statement.get( m_step ).GetToken().equals( "<<" ) ) ) {
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
      throw new Throwable();
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
                m_statement.get( m_step ).GetToken().equals( "<<" ) ) ) {
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
      throw new Throwable();
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
                m_statement.get( m_step ).GetToken().equals( "-" ) ) ) {
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
      throw new Throwable();
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
                m_statement.get( m_step ).GetToken().equals( "-" ) ) ) {
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
      throw new Throwable();
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
      throw new Throwable();
    } // catch()
  } // Maybe_Mult_Exp()

  private boolean Rest_of_Maybe_Mult_Exp() throws Throwable {
    try {
      if ( IsStepEnd() ) {
        return true;
      } // if

      while ( ( m_statement.get( m_step ).GetToken().equals( "*" ) ||
                m_statement.get( m_step ).GetToken().equals( "/" ) ||
                m_statement.get( m_step ).GetToken().equals( "%" ) ) ) {
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
      throw new Throwable();
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
                  m_statement.get( m_step ).GetToken().equals( "--" ) ) ) {
        m_step += 1;
        if ( m_statement.get( m_step ).GetType() == 4 ) { // ID
          m_step += 1;

          if ( IsStepEnd() ) {
            return true;
          } // if

          if ( m_statement.get( m_step ).GetToken().equals( "[" ) ) { // 可能會out of range
            m_step += 1;
            if ( Expression() ) {
              // m_step += 1;
              if ( m_statement.get( m_step ).GetToken().equals( "]" ) ) {
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
      throw new Throwable();
    } // catch
  } // Unary_Exp()

  private boolean Signed_Unary_Exp() throws Throwable {
    try {
      if ( m_statement.get( m_step ).GetType() == Global.s_T_ID ) { // ID
        m_step += 1;

        if ( IsStepEnd() ) {
          return true;
        } // if

        if ( m_statement.get( m_step ).GetToken().equals( "(" ) ) {
          m_step += 1;

          if ( Actual_Parameter_List() ) {
            // m_step += 1;
          } // if

          if ( m_statement.get( m_step ).GetToken().equals( ")" ) ) {
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
      else if ( m_statement.get( m_step ).GetType() == Global.s_T_CONSTANT ) { // Constant
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
      throw new Throwable();
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
                m_statement.get( m_step ).GetType() == Global.s_T_SMALL_LEFT_PAREN ) {
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
      throw new Throwable();
    } // catch()
  } // Unsigned_Unary_Exp()

  private void VarDefin( Vector<TOKEN> mStament ) throws Throwable {

    Variable var = null;
    String defname = new String( mStament.get( 1 ).GetToken() );

    if ( mStament.get( 0 ).GetToken().equals( "int" ) ) {
      var = new VarINT( Global.s_V_INT, mStament.get( 1 ).GetToken() );
    } // if
    else if ( mStament.get( 0 ).GetToken().equals( "string" ) ) {
      var = new VarINT( Global.s_V_STRING, mStament.get( 1 ).GetToken() );
    } // else if
    else if ( mStament.get( 0 ).GetToken().equals( "float" ) ) {
      var = new VarINT( Global.s_V_FLOAT, mStament.get( 1 ).GetToken() );
    } // else if
    else if ( mStament.get( 0 ).GetToken().equals( "char" ) ) {
      var = new VarINT( Global.s_V_CHAR, mStament.get( 1 ).GetToken() );
    } // else if
    else if ( mStament.get( 0 ).GetToken().equals( "bool" ) ) {
      var = new VarINT( Global.s_V_BOOL, mStament.get( 1 ).GetToken() );
    } // else if

    if ( mStament.size() == 5 ) {
      String arrsize = mStament.get( 3 ).GetToken();
      var.SetArraySize( arrsize );
    } // if

    Global.s_Fundefin.m_localVarList.add( var );

  } // VarDefin()

} // class Parser

class Excute {

  private Vector<TOKEN> mStament;

  public Excute( Vector<TOKEN> stament ) throws Throwable {
    this.mStament = stament;
  } // Excute()

  public boolean ExcuteComm( boolean isPrint ) throws Throwable {

    try {
      if ( IsFuncDefined() && isPrint ) {
        FuncDefined();
        return true;
      } // if
      else if ( IsFuncCommand( isPrint ) ) {
        if ( isPrint )
          System.out.println( "Statement executed ..." );
        return true;
      } // else if
      else if ( mStament.get( 0 ).GetType() == Global.s_T_ID &&
                mStament.get( 0 ).GetType() == Global.s_T_SMALL_LEFT_PAREN ) {
        if ( Global.G_FindFunction( Global.s_Functions, mStament.get( 0 ).GetToken() ) == null ) {
          System.out.println( "Line " + mStament.get( 0 ).Getline() + " : " + "undefined identifier : '"
                              + mStament.get( 0 ).GetToken() + "'" );
          throw new Throwable();
        } // if

        if ( isPrint )
          System.out.println( "Statement executed ..." );
        return true;
      } // else if
      else if ( mStament.get( 0 ).GetType() == Global.s_T_TYPE ) {
        IsVarDefinOK( isPrint );
        return true;
      } // else if
      else {
        if ( isPrint )
          System.out.println( "Statement executed ..." );
        return true;
      } // else

      // return true;

    } // try
    catch ( Throwable throwable ) {
      // System.out.println( "Statement executed ..." );
      return false;
    } // catch

  } // ExcuteComm()

  private boolean IsFuncDefined() throws Throwable {
    try {
      if ( Global.s_Fundefin == null )
        return false;
      if ( mStament.get( 0 ).GetType() == Global.s_T_TYPE ||
           mStament.get( 0 ).GetType() == Global.s_T_VOID ) {
        if ( mStament.get( 1 ).GetType() == Global.s_T_ID &&
             mStament.get( 2 ).GetType() == Global.s_T_SMALL_LEFT_PAREN )
          return true;
        else
          return false;
      } // if
      else
        return false;
    } // try
    catch ( Throwable throwable ) {
      return false;
    } // catch

  } // IsFuncDefined()

  private void FuncDefined() throws Throwable {
    Function fun = Global.G_FindFunction( Global.s_Functions, Global.s_Fundefin.m_name );
    if ( fun == null ) {
      fun = Global.s_Fundefin;
      Global.s_Functions.add( Global.s_Fundefin );
      System.out.println( "Definition of " + fun.m_name + "() entered ..." );
      Global.s_Fundefin = null;
    } // if
    else {
      fun.m_commLine = Global.s_Fundefin.m_commLine;
      fun.m_localVarList = Global.s_Fundefin.m_localVarList;
      fun.m_type = Global.s_Fundefin.m_type;
      System.out.println( "New definition of " + fun.m_name + "() entered ..." );
      Global.s_Fundefin = null;
    } // else

  } // FuncDefined()

  private boolean IsFuncCommand( boolean isPrint ) throws Throwable {
    try {
      if ( mStament.get( 0 ).GetType() == Global.s_T_ID &&
           mStament.get( 1 ).GetType() == Global.s_T_SMALL_LEFT_PAREN ) { // 判斷是否為function執行
        if ( mStament.get( 0 ).GetToken().equals( "Done" ) ) { // 程式結束

          System.out.println( "Our-C exited ..." );
          System.exit( 0 );
        } // if
        else if ( mStament.get( 0 ).GetToken().equals( "ListAllVariables" ) ) { // 列出所有變數
          if ( isPrint )
            ListAllVariables();
          return true;
        } // else if
        else if ( mStament.get( 0 ).GetToken().equals( "ListVariable" ) ) { // 印出特定variables
          if ( isPrint )
            ListVariable( mStament.get( 2 ).GetToken() );
          return true;
        } // else if
        else if ( mStament.get( 0 ).GetToken().equals( "ListAllFunctions" ) ) { // 印出所有functions
          if ( isPrint )
            ListAllFunctions();
          return true;
        } // else if
        else if ( mStament.get( 0 ).GetToken().equals( "ListFunction" ) ) {
          if ( isPrint )
            ListFunction( mStament.get( 2 ).GetToken() );
          return true;
        } // else if
        else if ( mStament.get( 0 ).GetToken().equals( "cin" ) ) {
          if ( IsFuncInputOk( "cin" ) ) {
            if ( ! mStament.get( 1 ).GetToken().equals( ">>" ) ) {
              System.out.println( "Line " + mStament.get( 1 ).Getline() + " : " + "unexpected token : '"
                                  + mStament.get( 1 ).GetToken() + "'" );
              throw new Throwable();
            } // if
            else
              return true;
          } // if
        } // else if
        else if ( mStament.get( 0 ).GetToken().equals( "cout" ) ) {
          if ( IsFuncInputOk( "cout" ) ) {
            if ( ! mStament.get( 1 ).GetToken().equals( "<<" ) ) {
              System.out.println( "Line " + mStament.get( 1 ).Getline() + " : " + "unexpected token : '"
                                  + mStament.get( 1 ).GetToken() + "'" );
              throw new Throwable();
            } // if
            else
              return true;
          } // if
        } // else if
      } // if

      return false;
    } // try
    catch ( Throwable throwable ) {
      throw new Throwable();
    } // catch
  } // IsFuncCommand()

  private void ListAllVariables() throws Throwable {
    Vector<String> varList = new Vector<String>();
    for ( int j = 0 ; j < Global.s_Variables.get( 0 ).m_Var.size() ; j++ ) {
      varList.add( Global.s_Variables.get( 0 ).m_Var.get( j ).GetName() );
    } // for

    // Sort
    String temp = "";
    for ( int i = 0 ; i < varList.size() ; i++ ) {
      for ( int j = i + 1 ; j < varList.size() ; j++ ) {
        if ( varList.get( i ).compareTo( varList.get( j ) ) > 0 ) { // if i > j
          temp = varList.get( i );
          varList.setElementAt( varList.get( j ), i );
          varList.setElementAt( temp, j );
        } // if
      } // for
    } // for

    for ( int i = 0 ; i < varList.size() ; i++ ) {
      System.out.println( varList.get( i ) );
    } // for

  } // ListAllVariables()

  private boolean ListVariable( String varName ) throws Throwable {
    varName = varName.substring( 1, varName.length() - 1 ); // 刪除頭尾的'"'
    boolean stop = false;
    boolean isFind = false;
    for ( int j = 0 ; j < Global.s_Variables.get( 0 ).m_Var.size() && ! stop ; j++ ) {
      if ( Global.s_Variables.get( 0 ).m_Var.get( j ).GetName().equals( varName ) ) {
        isFind = true;
        System.out.print( Global.s_Variables.get( 0 ).m_Var.get( j ).GetTypeName() + " " +
                          Global.s_Variables.get( 0 ).m_Var.get( j ).GetName() );
        if ( ! Global.s_Variables.get( 0 ).m_Var.get( j ).IsArray() ) {
          System.out.println( " ;" );
        } // if
        else {
          System.out.println( Global.s_Variables.get( 0 ).m_Var.get( j ).GetArraySize() + " ;" );
        } // else
      } // if
    } // for

    if ( ! isFind ) {
      // System.out.println( "Undefined identifier : '" + varName + "'" );
      return true;
    } // if

    return true;
  } // ListVariable()

  private void ListAllFunctions() throws Throwable {
    Vector<String> funcList = new Vector<String>();

    for ( int i = 0 ; i < Global.s_Functions.size() ; i++ ) {
      String funcName = Global.s_Functions.get( i ).GetName();
      if ( funcName.equals( "Done" ) || funcName.equals( "cin" ) || funcName.equals( "cout" ) ||
           funcName.equals( "ListAllVariables" ) || funcName.equals( "ListVariable" ) ||
           funcName.equals( "ListAllFunctions" ) || funcName.equals( "ListFunction" ) ) {
        ;
      } // if
      else {
        funcList.add( funcName );
      } // else
    } // for

    // sort
    String temp = "";
    for ( int i = 0 ; i < funcList.size() ; i++ ) {
      for ( int j = i + 1 ; j < funcList.size() ; j++ ) {
        if ( funcList.get( i ).compareTo( funcList.get( j ) ) > 0 ) {
          temp = funcList.get( i );
          funcList.setElementAt( funcList.get( j ), i );
          funcList.setElementAt( temp, j );
        } // if
      } // for
    } // for

    for ( int i = 0 ; i < funcList.size() ; i++ ) {
      System.out.println( funcList.get( i ) + "()" );
    } // for
  } // ListAllFunctions()

  private void ListFunction( String funcName ) throws Throwable {
    funcName = funcName.substring( 1, funcName.length() - 1 ); // 刪除頭尾的'"'
    int whiteSpace = 0; // 縮排的空白格數

    for ( int i = 0 ; i < Global.s_Functions.size() ; i++ ) {
      if ( Global.s_Functions.get( i ).GetName().equals( funcName ) ) {
        whiteSpace = 0;

        // 印出function define串
        System.out.print( Global.s_Functions.get( i ).GetTypeName() + " " + funcName );
        System.out.print( "(" );
        for ( int a = 0 ; a < Global.s_Functions.get( i ).m_localVarList.size() ; a++ ) {
          String typeName = Global.s_Functions.get( i ).m_localVarList.get( a ).GetTypeName();
          String fName = Global.s_Functions.get( i ).m_localVarList.get( a ).GetName();
          System.out.print( " " + typeName + " " + fName );

          // 印出array的[]
          if ( Global.s_Functions.get( i ).m_localVarList.get( a ).IsArray() ) {
            System.out.print( "[ " + Global.s_Functions.get( i ).m_localVarList.get( a ).GetArraySize()
                              + " ]" );
          } // if

          if ( Global.s_Functions.get( i ).m_localVarList.size() - a > 1 ) {
            System.out.print( "," );
          } // if
        } // for

        if ( Global.s_Functions.get( i ).m_localVarList.size() > 0 ) {
          System.out.print( " ) " );
        } // if
        else {
          System.out.print( ") " );
        } // else

        // 印出 {
        System.out.println( Global.s_Functions.get( i ).m_commLine.get( 0 ).m_Line.get( 0 ).GetToken() );

        whiteSpace += 2;
        for ( int b = 0 ; b < whiteSpace ; b++ ) { // 縮排用
          System.out.print( " " );
        } // for

        // 印出function內容物
        Vector<Stament> temp = Global.s_Functions.get( i ).m_commLine;
        boolean oneComm = false;
        boolean isDo = false;
        boolean isDoWhile = false;
        int doWhiteSpace = 0;
        for ( int j = 1 ; j < temp.size() ; j++ ) { // 行數
          for ( int k = 0 ; k < temp.get( j ).m_Line.size() ; k++ ) { // Statement
            String token = temp.get( j ).m_Line.get( k ).GetToken();
            int tkType = temp.get( j ).m_Line.get( k ).GetType();
            int lineSize = temp.get( j ).m_Line.size();
            String preT = "";

            if ( k > 0 ) {
              preT = temp.get( j ).m_Line.get( k - 1 ).GetToken();
            } // if

            // 判斷是否要空格
            // 下一個是 ++ -- [ 的後面不用空格
            // function後面如果緊接著 ( 不用空格
            if ( token.equals( "++" ) || token.equals( "--" ) || token.equals( "[" ) || k == 0 ||
                 ( token.equals( "(" ) && k > 0 &&
                   Global.G_FindFunction( Global.s_Functions, preT ) != null ) ) {
              ; // skip
            } // if
            else {
              System.out.print( " " );
            } // else

            System.out.print( token );

            if ( token.equals( "while" ) )
              System.out.print( "" );

            // 遇到以下這幾個要縮排
            if ( k == 0 && ( token.equals( "while" ) || token.equals( "do" ) || token.equals( "if" ) ||
                             token.equals( "else" ) ) && ( j + 1 < temp.size() &&
                                                           j + 1 < temp.size() ) ) {
              if ( ! temp.get( j + 1 ).m_Line.get( 0 ).GetToken().equals( ";" ) ) {
                whiteSpace += 2;

                // 下一行不為 {
                if ( j + 1 < temp.size() && temp.get( j + 1 ).m_Line.size() > 1 &&
                     ! temp.get( j + 1 ).m_Line.get( 0 ).GetToken().equals( "{" ) ) {
                  oneComm = true;
                } // if
              } // if
            } // if

            // 判斷此行第一個token是否為while do if else
            // k 是否已經指到最後一個token
            if ( k == lineSize - 1 &&
                 ( temp.get( j ).m_Line.get( 0 ).GetToken().equals( "while" ) ||
                   temp.get( j ).m_Line.get( 0 ).GetToken().equals( "do" ) ||
                   temp.get( j ).m_Line.get( 0 ).GetToken().equals( "if" ) ||
                   temp.get( j ).m_Line.get( 0 ).GetToken().equals( "else" ) ) ) {
              // 檢查下行token是否為 {
              // 是的話就緊接著print在後面
              if ( j + 1 < temp.size() - 1 &&
                   temp.get( j + 1 ).m_Line.size() == 1 &&
                   temp.get( j + 1 ).m_Line.get( 0 ).GetToken().equals( "{" ) ) {
                System.out.print( " {" );
                j += 1;
              } // if
            } // if

            if ( k == lineSize - 1 && j + 1 < temp.size() - 1 && temp.get( j + 1 ).m_Line.size() == 1 &&
                 temp.get( j + 1 ).m_Line.get( 0 ).GetToken().equals( ";" ) &&
                 temp.get( j ).m_Line.get( 0 ).GetToken().equals( "while" ) ) {
              System.out.print( " ;" );
              j += 1;
            } // if

            // 如果下一行token為 } , white space - 2
            if ( k == lineSize - 1 && j + 1 < temp.size() - 1 &&
                 temp.get( j + 1 ).m_Line.size() == 1 &&
                 temp.get( j + 1 ).m_Line.get( 0 ).GetToken().equals( "}" ) ) {
              whiteSpace -= 2;
            } // if
          } // for

          if ( j + 1 < temp.size() - 1 ) {
            System.out.println();
            for ( int b = 0 ; b < whiteSpace ; b++ ) { // 縮排用
              System.out.print( " " );
            } // for

            if ( oneComm ) {
              oneComm = false;
              whiteSpace -= 2;
            } // if
          } // if

          if ( j + 1 == temp.size() - 1 &&
               temp.get( j + 1 ).m_Line.size() == 1 &&
               temp.get( j + 1 ).m_Line.get( 0 ).GetToken().equals( "}" ) ) {
            System.out.println( "\n}" );
            j += 1;
          } // if
        } // for
      } // if
    } // for
  } // ListFunction()

  private void IsVarDefinOK( boolean isprint ) throws Throwable {

    boolean isRedef = false;
    boolean isStop = false;
    try {
      int step = 1;
      while ( ! isStop ) {
        Variable var = Global.G_FindVariable( Global.s_Variables, mStament.get( step ).GetToken() );
        String defname = new String( mStament.get( step ).GetToken() );
        if ( var == null ) { // 因為沒被define所以這邊要define
          if ( mStament.get( 0 ).GetToken().equals( "int" ) ) {
            var = new VarINT( Global.s_V_INT, mStament.get( step ).GetToken() );
          } // if
          else if ( mStament.get( 0 ).GetToken().equals( "string" ) ) {
            var = new VarINT( Global.s_V_STRING, mStament.get( step ).GetToken() );
          } // else if
          else if ( mStament.get( 0 ).GetToken().equals( "float" ) ) {
            var = new VarINT( Global.s_V_FLOAT, mStament.get( step ).GetToken() );
          } // else if
          else if ( mStament.get( 0 ).GetToken().equals( "char" ) ) {
            var = new VarINT( Global.s_V_CHAR, mStament.get( step ).GetToken() );
          } // else if
          else if ( mStament.get( 0 ).GetToken().equals( "bool" ) ) {
            var = new VarINT( Global.s_V_BOOL, mStament.get( step ).GetToken() );
          } // else if
        } // if
        else {
          isRedef = true;
          if ( mStament.get( 0 ).GetToken().equals( "int" ) ) {
            var.SetType( Global.s_V_INT );
            var.m_isArray = false;
            var.m_arraySize = "";
          } // if
          else if ( mStament.get( 0 ).GetToken().equals( "string" ) ) {
            var.SetType( Global.s_V_STRING );
            var.m_isArray = false;
            var.m_arraySize = "";
          } // else if
          else if ( mStament.get( 0 ).GetToken().equals( "float" ) ) {
            var.SetType( Global.s_V_FLOAT );
            var.m_isArray = false;
            var.m_arraySize = "";
          } // else if
          else if ( mStament.get( 0 ).GetToken().equals( "char" ) ) {
            var.SetType( Global.s_V_CHAR );
            var.m_isArray = false;
            var.m_arraySize = "";
          } // else if
          else if ( mStament.get( 0 ).GetToken().equals( "bool" ) ) {
            var.SetType( Global.s_V_BOOL );
            var.m_isArray = false;
            var.m_arraySize = "";
          } // else if
        } // else

        step++;
        if ( mStament.get( step ).GetType() == Global.s_T_COMMA ) {
          Global.G_AddVariable( var );
          step++;
        } // if
        else if ( mStament.get( step ).GetType() == Global.s_T_MID_LEFT_PAREN ) {
          step++;

          var.SetArraySize( mStament.get( step - 1 ).GetToken() + " " + mStament.get( step ).GetToken() +
                            " " + mStament.get( step + 1 ).GetToken() );
          step++;
          if ( mStament.get( step ).GetType() == Global.s_T_MID_RIGHT_PAREN ) {
            if ( ! isRedef )
              Global.G_AddVariable( var );
            step++;
          } // if
        } // else if
        else {
          if ( ! isRedef )
            Global.G_AddVariable( var );
        } // else

        if ( mStament.get( step ).GetType() == Global.s_T_COMMA ) {
          step++;
        } // if

        if ( ! ( mStament.get( step ).GetType() == Global.s_T_ID ) ) {
          isStop = true;
        } // if

        if ( isprint ) {
          if ( ! isRedef )
            System.out.println( "Definition of " + var.GetName() + " entered ..." );
          else
            System.out.println( "New definition of " + var.GetName() + " entered ..." );
        } // if

      } // while

    } // try
    catch ( Throwable throwable ) {
      // System.out.println( "define error!" );
      throw new Throwable();
    } // catch()

  } // IsVarDefinOK()

  private boolean IsFuncInputOk( String funcName ) throws Throwable {
    // return true;

    try {
      int funLocalVarSum = Global.G_FindFunction( Global.s_Functions, funcName ).LocalVarSum();
      int inputVarSum = 0;
      Vector<Variable> inputVar = new Vector<Variable>();
      int errortoken = 2;

      for ( int i = 2 ; mStament.get( i ).GetType() != Global.s_T_SMALL_RIGHT_PAREN ; i++ ) {
        errortoken = i;
        Variable nVar = null;
        while ( mStament.get( i ).GetType() != Global.s_T_ID &&
                mStament.get( i ).GetType() != Global.s_T_CONSTANT ) {
          i++;
        } // while

        if ( mStament.get( i ).GetType() == Global.s_T_ID ) {
          inputVar.add( Global.G_FindVariable( Global.s_Variables, mStament.get( i ).GetToken() ) );
        } // if
        else if ( mStament.get( i ).GetType() == Global.s_T_CONSTANT ) {
          if ( mStament.get( i ).GetToken().contains( "\"" ) ||
               mStament.get( i ).GetToken().contains( "'" ) ) {
            if ( mStament.get( i ).GetToken().charAt( 0 ) == '\'' ) {
              nVar = new VarCHAR( Global.s_V_CHAR, Integer.toString( i ) );
            } // if
            else if ( mStament.get( i ).GetToken().charAt( 0 ) == '"' ) {
              nVar = new VarSTRING( Global.s_V_STRING, Integer.toString( i ) );
            } // else if
          } // if
          else if ( mStament.get( i ).GetToken().equals( "true" ) ||
                    mStament.get( i ).GetToken().contains( "false" ) ) {
            nVar = new VarBOOL( Global.s_V_CHAR, Integer.toString( i ) );
          } // else if
          else
            nVar = new VarINT( Global.s_V_CHAR, Integer.toString( i ) );

          if ( ( mStament.get( i + 1 ).GetType() != Global.s_T_COMMA &&
                 mStament.get( i + 1 ).GetType() != Global.s_T_SMALL_RIGHT_PAREN ) &&
               mStament.get( i + 1 ).GetType() == Global.s_T_MID_LEFT_PAREN ) {
            i++;
            i++;
            nVar.SetArraySize( "[ " + mStament.get( i + 1 ).GetToken() + " ]" );
            i++;
          } // if
        } // else if

        inputVar.add( nVar );
        inputVarSum++;

        if ( inputVarSum > funLocalVarSum ) {
          System.out.println( "Line " + mStament.get( errortoken ).Getline() +
                              " : " + "unexpected token : '" + mStament.get( errortoken ).GetToken() +
                              "'" );
          throw new Throwable();
        } // if

      } // for

      if ( inputVarSum < funLocalVarSum ) {
        System.out.println( "Line " + mStament.get( errortoken ).Getline() +
                            " : " + "unexpected token : '" + mStament.get( errortoken ).GetToken() +
                            "'" );
        throw new Throwable();
      } // if

      return true;

    } // try
    catch ( Throwable throwable ) {
      // System.out.println( "請輸入正確的參數" );
      throw new Throwable();
      // return false;
    } // catch

  } // IsFuncInputOk()

} // class Excute

class Main {

  public static void main( String[] args ) throws Throwable {
    CutToken cutToken = new CutToken();
    Global.G_OurCInitialize();

    String tt = Global.sc.nextLine();
    if ( tt.contains( "2" ) )
      ; // System.exit( 0 );

    System.out.println( "Our-C running ..." );
    int isInIfWhileElse = 0;
    while ( true ) {
      Vector<TOKEN> stament = new Vector<TOKEN>();
      // System.out.println( "main CutToken Part! " );
      try {
        if ( cutToken.Cutting( stament, false, false ) ) {
          Parser parser = new Parser( stament, cutToken );
          // System.out.println( "main Parser Part! " );
          if ( parser.GrammarParser() ) {
            // Function g = Global.s_Fundefin;
            Excute excute = new Excute( stament );
            excute.ExcuteComm( true );
            Global.G_ClearVars();
            Global.s_Fundefin = null;
          } // if
          else {
            Global.G_ClearVars();
            Global.s_Fundefin = null;
          } // else
        } // if
      } // try
      catch ( Throwable throwable ) {
        Global.G_ClearVars();
      } // catch
    } // while

  } // main()

} // class Main