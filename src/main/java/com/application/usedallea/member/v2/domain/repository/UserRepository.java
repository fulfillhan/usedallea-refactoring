package com.application.usedallea.member.v2.domain.repository;

import com.application.usedallea.member.v2.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserRepository {

    void register(User user);

    void update(User user);

    void remove(User user);

    void deactivate(User user);

    List<User> findAll();

    User findById(String userId);

    boolean isExistById(String userId);
}
