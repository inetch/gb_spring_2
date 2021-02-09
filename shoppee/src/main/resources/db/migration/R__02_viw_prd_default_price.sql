drop view if exists prd_default_price_vw;
create view prd_default_price_vw as
select pli.id price_list_item_id
     , pl.id price_list_id
     , p.id product_id
     , p.title
     , pli.price / power(10, c.exponent) price
     , c.currency_symbol
     , c.code
     , pli.price original_price
  from prd_price_list pl
       join prd_price_list_item pli on (pli.price_list_id = pl.id)
       join prd_product p on (pli.product_id = p.id)
       join com_currency c on (pl.currency_code = c.code)
 where pl.is_default;
