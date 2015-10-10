CREATE function "LIFE".f_getFirstOrFullSpell(p_cnStr In varchar2,p_sign In number default null) return varchar2

as

lv_spell varchar2(200);
lv_temp Varchar2(10);
lv_char varchar2(10);
li_bytes Integer;

begin
if p_cnStr is null then
return '';
end if;
for i In 1..length(p_cnStr) loop
lv_char:=substr(p_cnStr,i,1);
if lengthb(lv_char) = 1 then
lv_spell:=lv_spell||lv_char;
elsif lengthb(lv_char) = 2 then
Select ascii(lv_char)-256*256 Into li_bytes From dual;
select nvl(max(spell),'###') Into lv_temp from table(f_getSpellcode) where code<=li_bytes;
if p_sign is null then
lv_spell:=lv_spell||substr(lv_temp,1,1);
else
lv_spell:=lv_spell||lv_temp;
end if;
elsif lengthb(lv_char) = 3 then
Select ascii(lv_char)-256*256 Into li_bytes From dual;
select max(spell) Into lv_temp from table(f_getSpellcode) where code<=li_bytes;
if p_sign is null then
lv_spell:=lv_spell||substr(lv_char,1,1);
else
lv_spell:=lv_spell||lv_char;
end if;
end if;
end loop;
return lv_spell;
end;

/