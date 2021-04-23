package ru.gb.inetch.shoppee.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import ru.gb.inetch.shoppee.entities.Role;
import ru.gb.inetch.shoppee.entities.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final Sql2o sql2o;
    private final ConcurrentMap<String, User> userMapByName = new ConcurrentHashMap<>();
    private final ConcurrentMap<Long, User> userMapById = new ConcurrentHashMap<>();

    private static final String QUERY_SELECT_USER =
            "select " + User.COLUMN_MAPPINGS.getAllTableColumns() + " from com_user where username = :u_name";

    private static final String QUERY_SELECT_USER_BY_ID =
            User.COLUMN_MAPPINGS.selectAllByIdQuery("u_id");

    private static final String QUERY_SELECT_USER_ROLES_BY_ID =
            "select " + Role.COLUMN_MAPPINGS.getAllTableColumns() +
            "  from com_user_role ur" +
            "       join com_role r on (ur.role_id = r.id)" +
            " where ur.user_id = :u_id" +
            " order by r.id";

    private static final String QUERY_USER_ROLES =
            "select count(1)" +
                    "  from com_user_role ur" +
                    "       join com_role r on (r.id = ur.role_id)" +
                    " where ur.user_id = :id" +
                    "   and r.role_name in ";

    private static final String QUERY_INSERT_USER =
            User.COLUMN_MAPPINGS.insertQuery();

    @Autowired
    public UserRepositoryImpl(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    private void fetchUserRoles(User user, Connection connection){
        user.setRoles(
                connection.createQuery(QUERY_SELECT_USER_ROLES_BY_ID, false)
                        .addParameter("u_id", user.getId())
                        .setColumnMappings(Role.COLUMN_MAPPINGS)
                        .executeAndFetch(Role.class)
        );
    }

    private void cacheUser(User user){
        userMapByName.put(user.getUserName(), user);
        userMapById.put(user.getId(), user);
    }

    private Collection<User> cacheAll(Collection<User> users){
        Collection<User> newCollection = new ArrayList<>();
        for(User user:users){
            newCollection.add(cacheUser(user, user.getId()));
        }
        return newCollection;
    }

    private User cacheUser(User user, Long id){
        User savedUser = userMapById.get(id);
        if(savedUser == null){
            savedUser = user;
        } else {
            user.copyTo(savedUser);
        }
        cacheUser(savedUser);
        return savedUser;
    }

    private User cacheUser(User user, String userName){
        User savedUser = userMapByName.get(userName);
        if(savedUser == null){
            savedUser = user;
        } else {
            user.copyTo(savedUser);
        }
        cacheUser(savedUser);
        return savedUser;
    }

    @Override
    public User getUser(String userName) {
        System.out.println(QUERY_SELECT_USER);
        try (Connection connection = sql2o.open()) {
            User user = connection.createQuery(QUERY_SELECT_USER, false)
                    .addParameter("u_name", userName)
                    .setColumnMappings(User.COLUMN_MAPPINGS)
                    .executeAndFetchFirst(User.class);
            if(user != null) {
                fetchUserRoles(user, connection);
            }
            return cacheUser(user, userName);
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
            return cacheUser(user, id);
        }

    }

    @Override
    public Collection<User> getAll() {
        try (Connection connection = sql2o.open()) {
            Collection<User> users =
                connection.createQuery(User.COLUMN_MAPPINGS.selectAllQuery(), false)
                    .setColumnMappings(User.COLUMN_MAPPINGS)
                    .executeAndFetch(User.class);
            return cacheAll(users);
        }
    }

    @Override
    @Transactional
    public void update(User user) {
        cacheUser(user, user.getId());
        try (Connection connection = sql2o.beginTransaction()) {
            connection.createQuery(User.COLUMN_MAPPINGS.updateByIdQuery("id")).bind(user).executeUpdate();
            connection.commit();
        }
    }

    @Override
    @Transactional
    public Long create(User user){
        System.out.println(QUERY_INSERT_USER);
        try (Connection connection = sql2o.beginTransaction()) {
            Long id = connection.createQuery(User.COLUMN_MAPPINGS.insertQuery(), true)
                    .bind(user)
                    .executeUpdate()
                    .getKey(Long.class);
            connection.commit();
            user.setId(id);
            cacheUser(user);
            return id;
        }
    }

    @Override
    public void save(User user) {
        Long cnt;
        try (Connection connection = sql2o.open()) {
            cnt = connection.createQuery("select count(id) from com_user where id = :u_id")
                    .addParameter("u_id", user.getId())
                    .executeScalar(Long.class);
        }
        if(cnt == 0){
            Long id = create(user);
            user.setId(id);
            cacheUser(user);
        }else{
            update(user);
            cacheUser(user, user.getId());
        }
    }

    @Override
    public boolean isOnUser(User user, String rolesLine){
        Long cnt;
        try (Connection connection = sql2o.open()) {
            cnt = connection.createQuery(QUERY_USER_ROLES + rolesLine)
                    .addParameter("id", user.getId())
                    .executeScalar(Long.class);
        }
        return cnt > 0;
    }
}
