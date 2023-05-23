package com.jinzunyue.share.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jinzunyue.share.entity.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {
    @Select("SELECT p.* FROM permission p JOIN user_permission up ON p.id = up.permission_id WHERE up.user_id = #{userId}")
    List<Permission> selectByUserId(int userId);
}
