package com.puuaru.center.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.puuaru.center.mapper.EmailMapper;
import com.puuaru.center.service.EmailService;
import com.puuaru.center.thirdparty.Email;
import org.springframework.stereotype.Service;

/**
 * @Description: EmailServiceImpl
 * @Author: puuaru
 * @Date: 2023/1/29
 */
@Service
public class EmailServiceImpl extends ServiceImpl<EmailMapper, Email> implements EmailService {
}
