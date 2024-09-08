package com.application.usedallea.member.v2.domain.repository;

import com.application.usedallea.member.v2.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserRepository {

    void register(User user);

    void update(User user);

    void remove(User user);

    User findById(String userId);

    void updateDeactivate(User user);
}
