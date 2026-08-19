/* Copyright 2026 上海如静知华信息科技有限公司 */
package cn.zhuatech.expenseai.controller;

import cn.zhuatech.expenseai.common.ApiResponse;
import cn.zhuatech.expenseai.service.ExpenseAnalysisService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai/expense")
@PreAuthorize("hasAnyRole('DOMAIN_USER','DOMAIN_OPERATOR','ADMIN')")
public class ExpenseAnalysisController {
    private final ExpenseAnalysisService service;
    public ExpenseAnalysisController(ExpenseAnalysisService service) { this.service = service; }
    @PostMapping("/audit")
    public ApiResponse<ExpenseAnalysisService.Result> audit(@Valid @RequestBody ExpenseAnalysisService.Request request) {
        return ApiResponse.ok("费用智能审计完成", service.audit(request));
    }
}
