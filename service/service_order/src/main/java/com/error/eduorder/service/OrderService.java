package com.error.eduorder.service;

import com.error.eduorder.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-09-03
 */
public interface OrderService extends IService<Order> {
    //1 生成订单的方法
    String createOrders(String courseId, String memberIdByJwtToken);

}
