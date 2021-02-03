package ru.gb.inetch.shoppee.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import ru.gb.inetch.shoppee.entities.Role;
import ru.gb.inetch.shoppee.entities.User;

import java.util.Collection;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final Sql2o sql2o;

    private static final String QUERY_SELECT_USER =
            "select " + User.allTableColumns + " from com_user where username = :u_name";

    private static final String QUERY_SELECT_USER_BY_ID =
            "select " + User.allTableColumns + " from com_user where id = :u_id";

    private static final String QUERY_SELECT_ALL_USERS =
            "select " + User.allTableColumns + " from com_user";

    private static final String QUERY_SELECT_USER_ROLES_BY_ID =
            "select r.id" +
            "     , r.role_name" +
            "  from com_user_role ur" +
            "       join com_role r on (ur.role_id = r.id)" +
            " where ur.user_id = :u_id" +
            " order by r.id";

    private static final String QUERY_UPDATE_USER_BY_ID =
            "update com_user set " + User.updatePairs + " where id = :id";

    private static final String QUERY_INSERT_USER =
            "insert into com_user (" + User.updatableTableColumns + ") values (" + User.updatableEntityFields + ")";

    @Autowired
    public UserRepositoryImpl(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    private void fetchUserRoles(User user, Connection connection){
        user.setRoles(
                connection.createQuery(QUERY_SELECT_USER_ROLES_BY_ID, false)
                        .addParameter("u_id", user.getId())
                        .setColumnMappings(User.COLUMN_MAPPINGS)
                        .executeAndFetch(Role.class)
        );
    }

    @Override
    public User getUser(String userName) {
        try (Connection connection = sql2o.open()) {
            User user = connection.createQuery(QUERY_SELECT_USER, false)
                    .addParameter("u_name", userName)
                    .setColumnMappings(User.COLUMN_MAPPINGS)
                    .executeAndFetchFirst(User.class);
            fetchUserRoles(user, connection);
            return user;
        }
    }

    @Override
    public User getUser(Long id) {
        try (Connection connection = sql2o.open()) {
            User user = connection.createQuery(QUERY_SELECT_USER_BY_ID, false)
                    .addParameter("u_id", id)
                    .setColumnMappings(User.COLUMN_MAPPINGS)
                    .executeAndFetchFirst(User.class);
            fetchUserRoles(user, connection);
            return user;
        }
    }

    @Override
    public Collection<User> getAll() {
        try (Connection connection = sql2o.open()) {
            return connection.createQuery(QUERY_SELECT_ALL_USERS, false)
                    .setColumnMappings(User.COLUMN_MAPPINGS)
                    .executeAndFetch(User.class);
        }
    }

    @Override
    public void update(User user) {
        try (Connection connection = sql2o.beginTransaction()) {
            connection.createQuery(QUERY_UPDATE_USER_BY_ID).bind(user).executeUpdate();
            connection.commit();
        }
    }

    @Override
    public Long create(User user){
        try (Connection connection = sql2o.beginTransaction()) {
            Long id = connection.createQuery(QUERY_INSERT_USER, true)
                    .bind(user)
                    .executeUpdate()
                    .getKey(Long.class);
            connection.commit();
            return id;
        }
    }

    @Override
    public void save(User user) {
        Long id;
        try (Connection connection = sql2o.open()) {
            id = connection.createQuery("select count(id) from com_user where id = :u_id")
                    .addParameter("u_id", user.getId())
                    .executeScalar(Long.class);
        }
        if(id == null){
            id = create(user);
            user.setId(id);
        }else{
            update(user);
        }
    }
}
