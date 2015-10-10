CREATE FORCE VIEW "LIFE".v_region (region_code,region,shortname) AS
select r2.region_code,r.region_name||' '||r1.region_name||' '||r2.region_name region,
 r.short_name||' '||r1.short_name||' '||r2.short_name shortname from t_sys_region r, t_sys_region r1, t_sys_region r2 where r.region_code = r1.parent_region and r1.region_code = r2.parent_region
;