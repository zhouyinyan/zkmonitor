
1. 系统架构图

_**TODO**_

2. 模块说明

- network -- 负责网络交互（比如ssh，监控目标api等）

- collect -- 负责信息收集，状态收集，监控目标探测等

- model -- 定义领域模型

- repository -- 负责信息的持久化

- aggregate -- 负责的持久化后信息的分析、聚合等处理

- web -- 负责处理后信息的UI

- alarm -- 负责告警规则配置、告警逻辑

- event -- 负责事件发布、消费等

- schedule -- 负责任务调度

- appconfig -- 应用配置



