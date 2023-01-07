package com.puuaru.edu.service.impl;

import com.puuaru.edu.entity.EduVideo;
import com.puuaru.edu.feign.VodClient;
import com.puuaru.edu.mapper.EduVideoMapper;
import com.puuaru.edu.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author puuaru
 * @since 2022-12-05
 */
@Service
@Transactional
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    private final VodClient vodClient;

    @Autowired
    public EduVideoServiceImpl(VodClient vodClient) {
        this.vodClient = vodClient;
    }

    @Override
    public Boolean removeVideo(Long videoId) {
        EduVideo video = this.getById(videoId);
        String videoSourceId = video.getVideoSourceId();
        vodClient.deleteSourceVideo(videoSourceId);
        boolean result = this.removeById(videoId);
        return result;
    }
}
