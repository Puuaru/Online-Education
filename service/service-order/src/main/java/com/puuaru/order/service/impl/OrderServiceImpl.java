package com.puuaru.order.service.impl;

import com.alibaba.nacos.common.utils.UuidUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.puuaru.order.entity.Order;
import com.puuaru.order.mapper.OrderMapper;
import com.puuaru.order.feign.CourseFrontClient;
import com.puuaru.order.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.puuaru.order.feign.MemberClient;
import com.puuaru.servicebase.entity.UcenterMember;
import com.puuaru.servicebase.vo.CourseFrontInfo;
import com.puuaru.utils.FeignUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author puuaru
 * @since 2023-02-21
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    private final CourseFrontClient courseClient;
    private final MemberClient memberClient;

    @Autowired
    public OrderServiceImpl(CourseFrontClient courseClient, MemberClient memberClient) {
        this.courseClient = courseClient;
        this.memberClient = memberClient;
    }

    @Override
    public String saveOrder(Long courseId, String memberId) {
        CourseFrontInfo courseInfo = FeignUtils.parseResult(courseClient.getCourseFrontInfo(courseId), "items", CourseFrontInfo.class);
        UcenterMember memberInfo = FeignUtils.parseResult(memberClient.getMemberInfo(memberId), "items", UcenterMember.class);
        Order order = new Order();

        order.setOrderNo(UuidUtils.generateUuid());
        order.setCourseId(courseId);
        order.setCourseCover(courseInfo.getCover());
        order.setCourseTitle(courseInfo.getTitle());
        order.setTeacherName(courseInfo.getTeacherName());
        order.setMemberId(memberId);
        order.setNickname(memberInfo.getNickname());
        order.setEmail(memberInfo.getEmail());
        order.setTotalFee(courseInfo.getPrice());
        super.save(order);

        String orderNo = order.getOrderNo();
        return orderNo;
    }

    @Override
    public Order getOrderByNo(String orderNo) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no", orderNo);
        Order order = super.getOne(wrapper);
        return order;
    }

    @Override
    public Boolean isPurchased(Long courseId, String memberId) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId)
                .eq("member_id", memberId)
                .eq("status", 1)
        ;
        int count = super.count(wrapper);
        return count > 0;
    }
}
