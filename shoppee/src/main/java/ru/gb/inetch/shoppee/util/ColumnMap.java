package ru.gb.inetch.shoppee.util;

import java.util.HashMap;

public class ColumnMap extends HashMap<String, String> {

    private final StringBuilder allTableColumnsSb;
    private final StringBuilder updatableTableColumnsSb;
    private final StringBuilder updatableEntityFieldsSb;
    private final StringBuilder updatePairsSb;

    public ColumnMap(){
        allTableColumnsSb = new StringBuilder();
        updatableTableColumnsSb = new StringBuilder();
        updatableEntityFieldsSb = new StringBuilder();
        updatePairsSb = new StringBuilder();
    }

    private void makeUpdatable(String columnName, String propertyName){
        if(!columnName.equalsIgnoreCase("id")){
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
        if(isEmpty()){
            allTableColumnsSb.append(phrase).append(" ").append(columnName);
        }else{
            allTableColumnsSb.append(", ").append(phrase).append(" ").append(columnName);
        }
        makeUpdatable(columnName, propertyName);
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

    //COLUMN_MAPPINGS.put(columnName, propertyName);
}
