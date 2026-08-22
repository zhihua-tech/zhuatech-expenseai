/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.expenseai; import cn.zhuatech.expenseai.service.ExpenseAnalysisService; import org.junit.jupiter.api.Test; import java.math.BigDecimal; import static org.assertj.core.api.Assertions.assertThat;
class ExpenseAnalysisServiceTests {private final ExpenseAnalysisService s=new ExpenseAnalysisService();
 @Test void blocksDuplicateHighRiskExpense(){var r=s.audit(new ExpenseAnalysisService.Request("EX-88","E-09",new BigDecimal("18000"),false,true,60,true,88));assertThat(r.decision()).isEqualTo("BLOCK");assertThat(r.auditorReviewRequired()).isTrue();}
 @Test void passesVerifiedExpense(){var r=s.audit(new ExpenseAnalysisService.Request("EX-20","E-20",new BigDecimal("260"),true,false,0,false,10));assertThat(r.decision()).isEqualTo("PASS");}}
