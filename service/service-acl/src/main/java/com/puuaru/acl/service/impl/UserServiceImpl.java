package com.puuaru.acl.service.impl;

import com.puuaru.acl.entity.User;
import com.puuaru.acl.mapper.UserMapper;
import com.puuaru.acl.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author puuaru
 * @since 2023-04-21
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
