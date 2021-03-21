package ru.gb.inetch.shoppee.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import ru.gb.inetch.shoppee.entities.Role;

@Repository
public class RoleRepositoryImpl implements RoleRepository{
    private final Sql2o sql2o;

    private static final String QUERY_SELECT_ROLE =
            "select " + Role.COLUMN_MAPPINGS.getAllTableColumns() + " from com_role where role_name = :r_name";

    private static final String QUERY_GET_DEFAULT_ROLE_NAME =
            "select role_name from com_role where is_default = 1";

    private static final String QUERY_SELECT_DEFAULT_ROLE =
            "select " + Role.COLUMN_MAPPINGS.getAllTableColumns() + " from com_role where is_default = 1";

    @Autowired
    public RoleRepositoryImpl(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Role getRole(String roleName){
        try (Connection connection = sql2o.open()) {
            Role role = connection.createQuery(QUERY_SELECT_ROLE, false)
                    .addParameter("r_name", roleName)
                    .setColumnMappings(Role.COLUMN_MAPPINGS)
                    .executeAndFetchFirst(Role.class);
            return role;
        }
    }

    @Override
    public Role getRole(Long id){
        try (Connection connection = sql2o.open()) {
            Role role = connection.createQuery(Role.COLUMN_MAPPINGS.selectAllByIdQuery("r_id"), false)
                    .addParameter("r_id", id)
                    .setColumnMappings(Role.COLUMN_MAPPINGS)
                    .executeAndFetchFirst(Role.class);
            return role;
        }
    }

    @Override
    public Role getDefaultRole(){
        try (Connection connection = sql2o.open()) {
            Role role = connection.createQuery(QUERY_SELECT_DEFAULT_ROLE, false)
                    .setColumnMappings(Role.COLUMN_MAPPINGS)
                    .executeAndFetchFirst(Role.class);
            return role;
        }
    }

    @Override
    public String getDefaultRoleName(){
        try (Connection connection = sql2o.open()) {
            return connection.createQuery(QUERY_GET_DEFAULT_ROLE_NAME, false)
                    .executeScalar(String.class);
        }
    }
}
