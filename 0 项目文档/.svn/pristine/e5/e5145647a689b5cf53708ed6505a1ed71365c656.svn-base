CREATE FUNCTION "LIFE".MD5(

passwd IN VARCHAR2)

RETURN VARCHAR2

IS

retval varchar2(32);

BEGIN

retval := utl_raw.cast_to_raw(DBMS_OBFUSCATION_TOOLKIT.MD5(INPUT_STRING => passwd)) ;

RETURN retval;

END;

/