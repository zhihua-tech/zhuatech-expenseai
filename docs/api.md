# ExpenseAI API
Copyright 2026 上海如静知华信息科技有限公司。业务接口使用 JWT 鉴权。

| 方法 | 地址 | 说明 |
| --- | --- | --- |
| POST | `/api/auth/login` | 登录 |
| POST | `/api/ai/expense/audit` | 票据与费用风险审计 |
| GET | `/api/admin/dashboard` | 费用合规看板 |
| GET | `/api/admin/work-orders` | 报销审核任务 |
| GET | `/api/workspace/dashboard` | 审核员工作台 |

输入包含金额、验真、重复指纹、制度超标、消费日期和商户风险；输出 `PASS`、`MANUAL_REVIEW` 或 `BLOCK` 及可解释原因。
