package PL110_10627153;

import java.util.Scanner;
import java.util.Vector;

class Global {

  // Blow is Token Type
  static final int s_T_LEFT_PAREN = 1;
  static final int s_T_RIGHT_PAREN = 2;
  static final int s_T_BOOLEANOPERATOE = 3;
  static final int s_T_ID = 4;
  static final int s_T_OPERATOR = 5; //
  static final int s_T_SEMICOLON = 6; // ;
  static final int s_T_NUM = 7;
  static final int s_T_ASSIGN = 8; //
  static final int s_T_VARTYPR = 9; // int, String, float, char, bool

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

class ATOM {
  private String m_token;
  private int m_type;

  public ATOM( String input, int defineType ) throws Throwable {
    m_token = new String( input );
    m_type = defineType;
  } // ATOM()

  public String GetToken() throws Throwable {
    return m_token;
  } // GetToken()

  public int GetType() throws Throwable {
    return m_type;
  } // GetType()

} // class ATOM

class Stament {
  public Vector<ATOM> m_token;
} // class Stament

abstract class Variable {

  private String m_Name;
  private int m_Type;

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

  public String GetValue() throws Throwable {
    return Integer.toString( m_value );
  } // GetValue()

  public int GetINTValue() throws Throwable {
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

  public String GetValue() throws Throwable {
    return Float.toString( m_value );
  } // GetValue()

  public float GetFLOATEValue() throws Throwable {
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

  public String GetValue() throws Throwable {
    return m_value;
  } // GetValue()

  public String GetSTRINGValue() throws Throwable {
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

  public String GetValue() throws Throwable {
    return Character.toString( m_value.charAt( 0 ) );
  } // GetValue()

  public String GetValue( int index ) throws Throwable {
    if ( index + 1 > m_arraySize ) {
      System.out.println( "rErro Stack over full." );
      throw new Throwable();
    } // if
    else {
      if ( index + 1 > m_value.length() )
        return Character.toString( '0' );
      else
        return Character.toString( m_value.charAt( index ) );

    } // else

  } // GetValue()

  public char GetCHARValue( int index ) throws Throwable {
    if ( index + 1 > m_arraySize ) {
      System.out.println( "rErro Stack over full." );
      throw new Throwable();
    } // if
    else {
      if ( index + 1 > m_value.length() )
        return '\0';
      else
        return m_value.charAt( index );

    } // else

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

  public String GetValue() throws Throwable {
    return Boolean.toString( m_value );
  } // GetValue()

  public boolean GetBOOLValue() throws Throwable {
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

class Paser {
  public Vector<ATOM> m_stament;
  public int m_step;

  public Paser( Vector<ATOM> inputeStament ) throws Throwable {
    m_stament = inputeStament;
    m_step = 0;
  } // Paser()

  public boolean GrammarParser() throws Throwable {

    try {

      if ( m_stament.get( 0 ).GetToken().equals( ";" ) ) {
        System.out.println( "Unexpected token : ';'" );
        m_stament.clear();
        return false;
      } // if

      if ( this.Command() ) {
        return true;
      } // if ()
      else {
        System.out.println( "Unexpected token : '" + m_stament.get( m_step ).GetToken() + "'" );
        return false;
      } // else

    } // try
    catch ( Throwable throwable ) {
      System.out.println( "Unexpected token : '" + m_stament.get( m_step ).GetToken() + "'" );
      return false;
    } // catch

  } // GrammarParser()

  private boolean Command() throws Throwable {

    try {

      if ( m_stament.get( m_step ).GetType() == 4 ) {
        m_step++;
        if ( m_stament.get( m_step ).GetType() == 8 ) {
          m_step++;
          if ( ArithExp() ) {
            if ( m_stament.get( m_step ).GetType() == 6 ) {
              m_step++;
              return true;
            } // if ()
            else {
              // System.out.println("Undefined identifier : '" + stament.get(index).GetToken() + "'" );
              // throw new Throwable();
              return false;
            } // else

          } // if ()
          else {
            // System.out.println("Undefined identifier : '" + stament.get(index).GetToken() + "'" );
            // throw new Throwable();
            return false;
          } // else

        } // if ()
        else if ( IDlessArithExpOrBexp() ) {
          if ( m_stament.get( m_step ).GetType() == 6 ) {
            m_step++;
            return true;
          } // if ()
          else {
            // System.out.println("Undefined identifier : '" + stament.get(index).GetToken() + "'" );
            // throw new Throwable();
            return false;
          } // else

        } // else if
        else if ( m_stament.get( m_step ).GetType() == 6 ) {
          // System.out.println("Undefined identifier : '" + stament.get(index).GetToken() + "'" );
          // throw new Throwable();
          return true;

        } // else if
        else {
          return false;

        } // else

      } // if ()
      else if ( NOT_IDStartArithExpOrBexp() ) {
        if ( m_stament.get( m_step ).GetType() == 6 ) {
          m_step++;
          return true;
        } // if ()
        else {
          return false;
        } // else
      } // else if ()
      else {
        // System.out.println("Undefined identifier : '" + stament.get(index).GetToken() + "'" );
        // throw new Throwable();
        return false;
      } // else
    } // try
    catch ( Throwable throwable ) {
      throw new Throwable();
    } // catch
  } // Command()

  private boolean IDlessArithExpOrBexp() throws Throwable {

    boolean isOnePass = false;

    try {
      while ( m_stament.get( m_step ).GetToken().equals( "+" ) ||
              m_stament.get( m_step ).GetToken().equals( "-" ) ||
              m_stament.get( m_step ).GetToken().equals( "*" ) ||
              m_stament.get( m_step ).GetToken().equals( "/" ) ) {

        if ( m_stament.get( m_step ).GetToken().equals( "+" ) ||
             m_stament.get( m_step ).GetToken().equals( "-" ) ) {

          m_step++;

          if ( Term() ) {
            isOnePass = true;
          } // if ()
          else
            return false;

        } // if
        else if ( m_stament.get( m_step ).GetToken().equals( "*" ) ||
                  m_stament.get( m_step ).GetToken().equals( "/" ) ) {
          m_step++;
          if ( Factor() )
            isOnePass = true;
          else
            return false;
        } // else if

      } // while

      if ( BooleanOperator() ) {

        if ( ArithExp() )
          isOnePass = true;
        else
          return false;

      } // if ()

    } // try
    catch ( Throwable throwable ) {
      if ( ! isOnePass )
        return false;
    } // catch

    if ( isOnePass )
      return true;
    else
      return false;

  } // IDlessArithExpOrBexp()

  private boolean BooleanOperator() throws Throwable {

    try {

      if ( m_stament.get( m_step ).GetType() == 3 ) {
        m_step++;
        return true;
      } // if
      else {
        return false;
      } // else

    } // try
    catch ( Throwable throwable ) {
      return false;
    } // catch


  } // BooleanOperator()

  private boolean NOT_IDStartArithExpOrBexp() throws Throwable {

    boolean isOnePass = false;

    if ( ! NOT_ID_StartArithExp() )
      return false;

    if ( BooleanOperator() ) {
      if ( ArithExp() )
        return true;
      else
        return false;
    } // if

    return true;

  } // NOT_IDStartArithExpOrBexp()

  private boolean NOT_ID_StartArithExp() throws Throwable {

    if ( ! NOT_ID_StartTerm() )
      return false;

    try {

      while ( m_stament.get( m_step ).GetToken().equals( "+" ) ||
              m_stament.get( m_step ).GetToken().equals( "-" ) ) {

        if ( m_stament.get( m_step ).GetToken().equals( "+" ) ||
             m_stament.get( m_step ).GetToken().equals( "-" ) ) {

          m_step++;

          if ( ! Term() )
            return false;

        } // if

      } // while

    } // try
    catch ( Throwable throwable ) {
      ;
    } // catch

    return true;

  } // NOT_ID_StartArithExp()

  private boolean NOT_ID_StartTerm() throws Throwable {

    if ( ! NOT_ID_StartFactor() )
      return false;

    try {
      while ( m_stament.get( m_step ).GetToken().equals( "*" ) ||
              m_stament.get( m_step ).GetToken().equals( "/" ) ) {

        if ( m_stament.get( m_step ).GetToken().equals( "*" ) ||
             m_stament.get( m_step ).GetToken().equals( "/" ) ) {

          m_step++;

          if ( ! Factor() )
            return false;

        } // if

      } // while

    } // try
    catch ( Throwable throwable ) {
      ;
    } // catch
    return true;

  } // NOT_ID_StartTerm()

  private boolean NOT_ID_StartFactor() throws Throwable {

    boolean isOnePass = false;
    try {

      if ( m_stament.get( m_step ).GetToken().equals( "+" ) ||
           m_stament.get( m_step ).GetToken().equals( "-" ) ) {
        m_step++;
      } // if

      if ( m_stament.get( m_step ).GetType() == 7 ) {
        m_step++;
        isOnePass = true;
      } // if

    } // try
    catch ( Throwable throwable ) {
      ;
    } // catch

    if ( isOnePass )
      return true;

    try {
      if ( m_stament.get( m_step ).GetType() == 1 ) {
        m_step++;
        if ( ArithExp() ) {
          if ( m_stament.get( m_step ).GetType() == 2 ) {
            m_step++;
            return true;
          } // if
          else
            return false;
        } // if
        else
          return false;
      } // if
      else
        return false;
    } // try
    catch ( Throwable throwable ) {
      return false;
    } // catch

  } // NOT_ID_StartFactor()

  private boolean ArithExp() throws Throwable {
    if ( ! Term() )
      return false;

    try {
      while ( m_stament.get( m_step ).GetToken().equals( "+" ) ||
              m_stament.get( m_step ).GetToken().equals( "-" ) ) {

        if ( m_stament.get( m_step ).GetToken().equals( "+" ) ||
             m_stament.get( m_step ).GetToken().equals( "-" ) ) {

          m_step++;
          if ( ! Term() )
            return false;

        } // if

      } // while

    } // try
    catch ( Throwable throwable ) {
      ;
    } // catch
    return true;

  } // ArithExp()

  private boolean Term() throws Throwable {

    if ( ! Factor() )
      return false;

    try {
      while ( m_stament.get( m_step ).GetToken().equals( "*" ) ||
              m_stament.get( m_step ).GetToken().equals( "/" ) ) {

        if ( m_stament.get( m_step ).GetToken().equals( "*" ) ||
             m_stament.get( m_step ).GetToken().equals( "/" ) ) {

          m_step++;
          if ( ! Factor() )
            return false;

        } // if

      } // while

    } // try
    catch ( Throwable throwable ) {
      ;
    } // catch

    return true;

  } // Term()

  private boolean Factor() throws Throwable {

    boolean isOnePass = false;

    try {

      if ( m_stament.get( m_step ).GetType() == 4 ) {
        m_step++;
        isOnePass = true;
      } // if

    } // try
    catch ( Throwable throwable ) {
      return false;
    } // catch

    if ( isOnePass )
      return true;

    try {
      boolean haveSIGN = false;
      String signOperator = new String();

      if ( m_stament.get( m_step ).GetToken().equals( "+" ) || m_stament.get( m_step ).GetToken().equals( "-" ) ) {
        m_step++;
      } // if

      if ( m_stament.get( m_step ).GetType() == 7 ) {
        m_step++;
        isOnePass = true;
      } // if

    } // try
    catch ( Throwable throwable ) {
      return false;
    } // catch

    if ( isOnePass )
      return true;

    try {
      if ( m_stament.get( m_step ).GetType() == 1 ) {

        m_step++;
        if ( ArithExp() ) {

          if ( m_stament.get( m_step ).GetType() == 2 ) {
            m_step++;
            return true;
          } // if
          else
            return false;

        } // if
        else
          return false;

      } // if
      else
        return false;
    } // try
    catch ( Throwable throwable ) {
      return false;
    } // catch

  } // Factor()

} // class Paser

class Main {

  public static void main( String[] args ) throws Throwable {
    // Test test = new Test();
    // test.test();
    Global g = new Global();

  } // main()

} // class Main
