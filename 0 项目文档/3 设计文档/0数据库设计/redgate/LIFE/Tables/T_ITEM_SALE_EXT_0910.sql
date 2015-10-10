CREATE TABLE "LIFE".t_item_sale_ext_0910 (
  "ID" NUMBER(9) NOT NULL,
  sale_id NUMBER(8),
  sale_num NUMBER(8),
  click_num NUMBER(8),
  comment_num NUMBER(8),
  user_num NUMBER(8),
  collect_num NUMBER(8),
  "RANK" NUMBER(9,2),
  logistics_fee NUMBER(9,2),
  logistics_fee_type NUMBER(1)
);
ALTER TABLE "LIFE".t_item_sale_ext_0910 ADD SUPPLEMENTAL LOG GROUP ggs_245685 (click_num, collect_num, comment_num, "ID", logistics_fee, logistics_fee_type, "RANK", sale_id, sale_num, user_num) ALWAYS;