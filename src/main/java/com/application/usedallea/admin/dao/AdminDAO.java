package com.application.usedallea.admin.dao;

import com.application.usedallea.admin.dto.AdminDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminDAO {
    AdminDTO getLoginData(AdminDTO adminId);
}
