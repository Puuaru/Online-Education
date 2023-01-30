package com.puuaru.center.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.puuaru.center.thirdparty.Email;

public interface EmailService extends IService<Email> {
    String sendMail(String target);
}
