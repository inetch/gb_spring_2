package ru.gb.inetch.shoppee.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;
import ru.gb.inetch.shoppee.entities.PriceListItem;
import ru.gb.inetch.shoppee.entities.User;

import java.util.Collection;
import java.util.Optional;

//@Repository
//public class PriceListItemRepositoryImpl implements PriceListItemRepository{
//    private final Sql2o sql2o;
//    private Long lastQueryTotalCount;
//
//    @Autowired
//    public PriceListItemRepositoryImpl(Sql2o sql2o) {
//        this.sql2o = sql2o;
//    }
//
//    private static final String QUERY_SELECT_ITEM_BY_PRODUCT_ID =
//            "select " + PriceListItem.COLUMN_MAPPINGS.getAllTableColumns() + " from prd_default_price_vw where product_id = :id";
//
//    private static final String QUERY_INSERT_ITEM =
//            "insert into prd_price_list_item (product_id, price_list_id, price)" +
//                    "values (:product_id, :price_list_id, :price)";
//
//    private static final String QUERY_UPDATE_ITEM_PRICE_BY_ID =
//            "update prd_price_list_item set price = :price where id = :id";
//
//    @Override
//    public Collection<PriceListItem> getAll(){
//        try (Connection connection = sql2o.open()) {
//            Collection<PriceListItem> all = connection.createQuery(PriceListItem.COLUMN_MAPPINGS.selectAllQuery(), false)
//                    .setColumnMappings(PriceListItem.COLUMN_MAPPINGS)
//                    .executeAndFetch(PriceListItem.class);
//            all.stream().findFirst().ifPresentOrElse(item -> lastQueryTotalCount = item.getTotalCount(), () -> lastQueryTotalCount = 0L);
//            return all;
//        }
//    }
//
//    @Override
//    public Collection<PriceListItem> getAll(Optional<Double> minPrice,
//                                     Optional<Double> maxPrice,
//                                     Optional<String> titleWildcard,
//                                     Optional<Integer> pageNumber,
//                                     Optional<Integer> pageSize,
//                                     Optional<Integer> orderColumnNumber
//    ){
//        final String minPriceParamName = "minPrice";
//        final String maxPriceParamName = "maxPrice";
//        final String titleParamName = "titleLike";
//
//        String limitation = "";
//        if(pageNumber.isPresent() && pageSize.isPresent()){
//            limitation = " limit " + pageSize.get().toString() + " offset " + pageNumber.get() * pageSize.get();
//        }
//
//        String order = "";
//        if(orderColumnNumber.isPresent()){
//            order = " order by " + orderColumnNumber.get().toString();
//        }
//
//        StringBuilder where = new StringBuilder();
//        if(minPrice.isPresent()){
//            where.append(" price >= :").append(minPriceParamName);
//        }
//
//        if(maxPrice.isPresent()){
//            if(where.length() > 0){
//                where.append(" and");
//            }
//            where.append(" price <= :").append(maxPriceParamName);
//        }
//        if(titleWildcard.isPresent()){
//            if(where.length() > 0){
//                where.append(" and");
//            }
//            where.append(" title like :").append(titleParamName);
//        }
//
//        StringBuilder stringQuery = new StringBuilder();
//
//        stringQuery.append(PriceListItem.COLUMN_MAPPINGS.selectAllQuery());
//        if(where.length() > 0){
//            stringQuery.append(" where ").append(where);
//        }
//        if(!order.isEmpty()){
//            stringQuery.append(order);
//        }
//        if(!limitation.isEmpty()){
//            stringQuery.append(limitation);
//        }
//
//        try (Connection connection = sql2o.open()) {
//            Query query = connection.createQuery(stringQuery.toString(), false);
//
//            System.out.println(query);
//
//            minPrice.ifPresent(aDouble -> query.addParameter(minPriceParamName, aDouble));
//            maxPrice.ifPresent(aDouble -> query.addParameter(maxPriceParamName, aDouble));
//            titleWildcard.ifPresent(aString -> query.addParameter(titleParamName, "%" + aString + "%"));
//            Collection<PriceListItem> result = query.setColumnMappings(PriceListItem.COLUMN_MAPPINGS)
//                    .executeAndFetch(PriceListItem.class);
//
//            result.stream().findFirst().ifPresentOrElse(item -> lastQueryTotalCount = item.getTotalCount(), () -> lastQueryTotalCount = 0L);
//            return result;
//        }
//    }
//
//    @Override
//    public PriceListItem getByProductId(Long productId) {
//        try (Connection connection = sql2o.open()) {
//            PriceListItem item = connection.createQuery(QUERY_SELECT_ITEM_BY_PRODUCT_ID, false)
//                    .addParameter("id", productId)
//                    .setColumnMappings(PriceListItem.COLUMN_MAPPINGS)
//                    .executeAndFetchFirst(PriceListItem.class);
//            lastQueryTotalCount = item.getTotalCount();
//            return item;
//        }
//    }
//
//    @Override
//    public PriceListItem getById(Long id) {
//        try (Connection connection = sql2o.open()) {
//            PriceListItem item = connection.createQuery(PriceListItem.COLUMN_MAPPINGS.selectAllByIdQuery("id"), false)
//                    .addParameter("id", id)
//                    .setColumnMappings(PriceListItem.COLUMN_MAPPINGS)
//                    .executeAndFetchFirst(PriceListItem.class);
//            if (item == null) {
//                lastQueryTotalCount = 0L;
//            }else {
//                lastQueryTotalCount = item.getTotalCount();
//            }
//            return item;
//        }
//    }
//
//    @Override
//    public Long getLastQueryTotalCount(){
//        return lastQueryTotalCount;
//    }
//
//    @Override
//    public void save(PriceListItem item){
//        Long cnt;
//        try (Connection connection = sql2o.open()) {
//            cnt = connection.createQuery("select count(id) from prd_price_list_item where id = :id")
//                    .addParameter("id", item.getId())
//                    .executeScalar(Long.class);
//        }
//        if(cnt == 0){
//            Long id = create(item);
//            item.setId(id);
//        }else{
//            update(item);
//        }
//    }
//
//    @Override
//    @Transactional
//    public void update(PriceListItem item){
//        try(Connection connection = sql2o.beginTransaction()) {
//            Long id = connection.createQuery(QUERY_UPDATE_ITEM_PRICE_BY_ID)
//                    .addParameter("price", item.getOriginalPrice())
//                    .addParameter("id", item.getId())
//                    .executeUpdate()
//                    .getKey(Long.class);
//            connection.commit();
//        }
//    }
//
//    @Override
//    @Transactional
//    public Long create(PriceListItem item){
//        try(Connection connection = sql2o.beginTransaction()) {
//            Long id = connection.createQuery(QUERY_INSERT_ITEM)
//                    .addParameter("product_id", item.getProductId())
//                    .addParameter("price_list_id", item.getPriceListId())
//                    .addParameter("price", item.getOriginalPrice())
//                    .executeUpdate()
//                    .getKey(Long.class);
//            connection.commit();
//            return id;
//        }
//    }
//}
