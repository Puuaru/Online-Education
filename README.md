# Online-Education

An Online-Education application using Spring Cloud Alibaba.

<!-- markdown-toc GFM -->

* [模块说明](#模块说明)
* [容器端口](#容器端口)
* [TODO-LIST](#todo-list)

<!-- markdown-toc -->

## 模块说明

```
online-education
├── common                     // 公共模块
│   ├── common-utils           // 通用工具类
│   └── service-base           // 全局Bean及配置类
└── service                    // 业务服务模块
    ├── service-edu            // 8001 提供主要服务的api
    ├── service-oss            // 8002 提供oss存储服务
    ├── service-vod            // 8003 提供视频点播服务
    ├── service-cms            // 8004 提供首页控制服务
    ├── service-center         // 8005 提供用户登录注册服务
    ├── service-order          // 8006 提供订单提交及支付确认服务
    └── service-statistics     // 8007 提供数据统计服务
```

## 容器端口

|   容器   |     宿主端口:容器端口     |                   备注                    |
| :------: | :-----------------------: | :---------------------------------------: |
|  MySQL   |         3307:3006         |   username: root<br/>password: password   |
|  Nacos   |         8848:8848         |    username: nacos<br/>password: nacos    |
|  nginx   |           80:80           |                                           |
| sentinel |         8858:8858         | username: sentinel<br/>password: sentinel |
|  redis   |         6379:6379         |                                           |
| rabbitmq | 5672:5672<br/>15672:15672 |   username: puuaru<br/>password: puuaru   |

## TODO-LIST

- [ ] sentinel-dashboard 使用 push 模式的改造
- [ ] services 配置上传至 nacos
