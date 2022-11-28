package com.puuaru.oss.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.puuaru.oss.properties.OssProperties;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Description: PropertiesMapper
 * @Author: puuaru
 * @Date: 2022/11/26
 */
@Mapper
public interface PropertiesMapper extends BaseMapper<OssProperties> {
}
