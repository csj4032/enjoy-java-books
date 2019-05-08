package com.genius.database.dao.user;

import com.genius.database.domain.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {

    void truncate();

    User get(long id);

    List<User> getByName(String name);

    int save(User user);

    int update(User genius) throws SQLException;

    void saveAndUpdate(User user1, User user2) throws SQLException;
}
