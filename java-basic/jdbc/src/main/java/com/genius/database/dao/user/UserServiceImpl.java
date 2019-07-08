package com.genius.database.dao.user;

import com.genius.database.domain.User;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.sql.SQLException;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Transactional
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private DataSourceTransactionManager dataSourceTransactionManager;
    private TransactionStatus status;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
        dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(userDao.getJdbcTemplate().getDataSource());
    }

    @Override
    public void truncate() {
        userDao.truncate();
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public User get(long id) {
        DefaultTransactionDefinition defaultTransactionDefinition = new DefaultTransactionDefinition();
        defaultTransactionDefinition.setPropagationBehavior(3);
        defaultTransactionDefinition.setName("get");
        return userDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    public List<User> getByName(String name) {
        return userDao.findByName(name);
    }

    @Override
    public int save(User user) {
        DefaultTransactionDefinition defaultTransactionDefinition = new DefaultTransactionDefinition();
        defaultTransactionDefinition.setPropagationBehavior(3);
        defaultTransactionDefinition.setName("save");
        int result = userDao.insert(user);
        dataSourceTransactionManager.commit(dataSourceTransactionManager.getTransaction(defaultTransactionDefinition));
        return result;
    }

    @Override
    public int update(User user) throws SQLException {
        DefaultTransactionDefinition defaultTransactionDefinition = new DefaultTransactionDefinition();
        defaultTransactionDefinition.setPropagationBehavior(4);
        defaultTransactionDefinition.setName("update");
        dataSourceTransactionManager.getTransaction(defaultTransactionDefinition);
        userDao.update(user);
        if (1 == 1) {
            dataSourceTransactionManager.rollback(dataSourceTransactionManager.getTransaction(defaultTransactionDefinition));
            throw new SQLException();
        }
        dataSourceTransactionManager.commit(dataSourceTransactionManager.getTransaction(defaultTransactionDefinition));
        return 0;
    }

    @Override
    public void saveAndUpdate(User user1, User user2) throws SQLException {
        DefaultTransactionDefinition defaultTransactionDefinition = new DefaultTransactionDefinition();
        defaultTransactionDefinition.setPropagationBehavior(0);
        defaultTransactionDefinition.setName("saveAndUpdate");
        dataSourceTransactionManager.getTransaction(defaultTransactionDefinition);
        save(user1);
        update(user2);
        dataSourceTransactionManager.commit(dataSourceTransactionManager.getTransaction(defaultTransactionDefinition));
    }
}