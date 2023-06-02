package com.puuaru.acl.service;

import com.puuaru.acl.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.puuaru.servicebase.vo.PageData;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author puuaru
 * @since 2023-04-21
 */
public interface RoleService extends IService<Role> {

    /**
     * 分页查找角色
     * @param current 当前页数
     * @param limit 每页数据上限
     * @param nameQuery 角色名查找项
     * @return
     */
    PageData<Role> getRolesWithQuery(Long current, Long limit, String nameQuery);
}
