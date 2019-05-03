package com.genius.database.dao.user;

import com.genius.database.domain.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {

    User get(long id);

    List<User> getByName(String name);

    int save(User user) throws SQLException;

    int update(User genius) throws SQLException;

    void saveAndUpdate(User user1, User user2) throws SQLException;
}
