package ru.gb.inetch.shoppee.util;

import ru.gb.inetch.shoppee.entities.PriceListItem;
import ru.gb.inetch.shoppee.entities.Product;

import java.lang.annotation.Target;
import java.util.HashMap;

public class ColumnMap extends HashMap<String, String> {

    private final StringBuilder allTableColumnsSb;
    private final StringBuilder updatableTableColumnsSb;
    private final StringBuilder updatableEntityFieldsSb;
    private final StringBuilder updatePairsSb;
    private final String tableName;
    private final String pkColumnName;

    public ColumnMap(String tableName){
        allTableColumnsSb = new StringBuilder();
        updatableTableColumnsSb = new StringBuilder();
        updatableEntityFieldsSb = new StringBuilder();
        updatePairsSb = new StringBuilder();
        this.tableName = tableName;
        pkColumnName = "id";
    }

    public ColumnMap(String tableName, String pkColumnName){
        allTableColumnsSb = new StringBuilder();
        updatableTableColumnsSb = new StringBuilder();
        updatableEntityFieldsSb = new StringBuilder();
        updatePairsSb = new StringBuilder();
        this.tableName = tableName;
        this.pkColumnName = pkColumnName;
    }

    private void makeUpdatable(String columnName, String propertyName){
        if(!columnName.equalsIgnoreCase(pkColumnName)){
            if(updatableTableColumnsSb.length() == 0){
                updatableTableColumnsSb.append(columnName);
                updatableEntityFieldsSb.append(":").append(propertyName);
                updatePairsSb.append(columnName).append(" = :").append(propertyName);
            }else{
                updatableTableColumnsSb.append(", ").append(columnName);
                updatableEntityFieldsSb.append(", ").append(":").append(propertyName);
                updatePairsSb.append(", ").append(columnName).append(" = :").append(propertyName);
            }
        }
    }

    private String superPut(String columnName, String propertyName){
        super.put(columnName, propertyName);
        return columnName;
    }

    @Override
    public String put(String columnName, String propertyName){
        if(isEmpty()){
            allTableColumnsSb.append(columnName);
        }else{
            allTableColumnsSb.append(", ").append(columnName);
        }
        makeUpdatable(columnName, propertyName);
        return superPut(columnName, propertyName);
    }

    public String put(String columnName, String propertyName, String phrase){
        if(phrase.isEmpty()){
            allTableColumnsSb.append(phrase).append(" ").append(columnName);
            makeUpdatable(columnName, propertyName);
        }else{
            allTableColumnsSb.append(", ").append(phrase).append(" ").append(columnName);
        }

        return superPut(columnName, propertyName);
    }

    public String getAllTableColumns(){
        return allTableColumnsSb.toString();
    }
    public String getUpdatableTableColumns(){
        return updatableTableColumnsSb.toString();
    }
    public String getUpdatableEntityFields(){
        return updatableEntityFieldsSb.toString();
    }
    public String getUpdatePairs(){
        return updatePairsSb.toString();
    }

    public String selectAllQuery(){
        return "select " + getAllTableColumns() + " from " + tableName;
    }

    public String selectAllByIdQuery(String paramName){
        return "select " + getAllTableColumns() + " from " + tableName + " where " + pkColumnName + " = :" + paramName;
    }

    public String updateByIdQuery(String paramName){
        return "update " + tableName + " set " + getUpdatePairs() + " where " + pkColumnName + " = :" + paramName;
    }

    public String insertQuery(){
        return "insert into " + tableName + " (" + getUpdatableTableColumns() + ") values (" + getUpdatableEntityFields() + ")";
    }
}
