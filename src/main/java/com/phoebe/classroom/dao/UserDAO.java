package com.phoebe.classroom.dao;

import com.phoebe.classroom.base.dao.BaseDAO;
import com.phoebe.classroom.entity.UserEntity;
import org.apache.commons.collections4.CollectionUtils;

import javax.ejb.Stateless;
import java.util.List;
import java.util.Optional;

@Stateless
public class UserDAO extends BaseDAO<UserEntity> {
    public UserDAO() {
        super(UserEntity.class);
    }

    public Optional<UserEntity> findByUserName(String userName) {
        List<UserEntity> userList = em.createQuery("SELECT u FROM UserEntity u WHERE u.userName = :userName", UserEntity.class)
                .setParameter("userName", userName)
                .getResultList();
        if(CollectionUtils.isNotEmpty(userList)) {
            return Optional.of(userList.get(0));
        } else {
            return Optional.empty();
        }
    }
}
