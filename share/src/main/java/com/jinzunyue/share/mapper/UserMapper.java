package com.jinzunyue.share.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jinzunyue.share.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    User selectList(String username);
}
