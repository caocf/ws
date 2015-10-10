CREATE package "LIFE".xw_data_sync is

  -- Author  : ZHANGJIAN
  -- Created : 2013/7/1 17:01:55
  -- Purpose :

  -- Public type declarations
  --type <TypeName> is <Datatype>;

  -- Public constant declarations
  --<ConstantName> constant <Datatype> := <Value>;

  -- Public variable declarations
  --<VariableName> <Datatype>;

  -- Public function and procedure declarations
  procedure cleardata;
  function xwcatory_id2klid(xwid in varchar2) return number;
  function xwproduct_id2klid(xwid in varchar2) return number;
  function xwstore_id2klid(xwid in varchar2) return number;
  function xwmember_id2klid(xwid in varchar2) return number;
  function xwstore_catory_id2klid(xwid in varchar2) return number;
  function xwstore_temp_id2klid(xwtempid in varchar2,xwposition in number) return number;
  function xwfee_id2klid(xwid in varchar2) return number;
  FUNCTION MD5(passwd IN VARCHAR2) RETURN VARCHAR2;
  FUNCTION xw_fee2kl_fee(m_id in varchar2,p_typ in varchar2,f_rate1 in varchar2) return number;
  function xwmapid2xwmerid(mapp_id in varchar2) return varchar2;
  procedure covertdata;
  procedure logdata(log_step in number,vtable in varchar2,op_type in varchar2,op_result in varchar2);


end xw_data_sync;
/