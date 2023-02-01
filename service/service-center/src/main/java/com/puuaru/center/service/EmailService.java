package com.puuaru.center.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.puuaru.center.entity.ThreePartyProperties;

public interface EmailService extends IService<ThreePartyProperties> {
    String sendMail(String target);
}
