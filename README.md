# Online-Education

An Online-Education application using Spring Cloud Alibaba.

## 模块说明

```
online-education
├── common              // 公共模块
│   ├── common-utils    // 通用工具类
│   └── service-base    // 全局Bean及配置类
└── service             // 业务服务模块
    ├── service-edu     // 8001 提供主要服务的api
    ├── service-oss     // 8002 提供oss存储服务
    └── service-vod     // 8003 提供视频点播服务
```

## TODO-LIST

* sentinel-dashboard 使用 push 模式的改造