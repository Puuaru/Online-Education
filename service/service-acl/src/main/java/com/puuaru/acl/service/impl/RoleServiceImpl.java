package com.puuaru.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.puuaru.acl.entity.Role;
import com.puuaru.acl.mapper.RoleMapper;
import com.puuaru.acl.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.puuaru.servicebase.vo.PageData;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author puuaru
 * @since 2023-04-21
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public PageData<Role> getRolesWithQuery(Long current, Long limit, String nameQuery) {
        Page<Role> page = new Page<>(current, limit);
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        wrapper.like("role_name", nameQuery);
        Page<Role> result = super.page(page, wrapper);
        long total = result.getTotal();
        List<Role> records = result.getRecords();
        return new PageData<>(records, total);
    }
}
