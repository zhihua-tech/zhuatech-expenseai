/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.expenseai.controller;

import cn.zhuatech.expenseai.common.ApiResponse;
import cn.zhuatech.expenseai.service.ExpenseAnalysisService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController
@RequestMapping("/api/ai/expense")
@PreAuthorize("hasAnyRole('DOMAIN_USER','DOMAIN_OPERATOR','ADMIN')")
public class ExpenseAnalysisController {
    private final ExpenseAnalysisService service;
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public ExpenseAnalysisController(ExpenseAnalysisService service) { this.service = service; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @PostMapping("/audit")
    public ApiResponse<ExpenseAnalysisService.Result> audit(@Valid @RequestBody ExpenseAnalysisService.Request request) {
        return ApiResponse.ok("费用智能审计完成", service.audit(request));
    }
}
