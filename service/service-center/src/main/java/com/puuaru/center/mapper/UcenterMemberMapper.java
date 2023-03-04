package com.puuaru.center.mapper;

import com.puuaru.servicebase.entity.UcenterMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author puuaru
 * @since 2023-01-27
 */
@Mapper
public interface UcenterMemberMapper extends BaseMapper<UcenterMember> {
    int statRegister(String date);
}
