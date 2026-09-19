/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.expenseai.service;
import jakarta.validation.constraints.*; import org.springframework.stereotype.Service; import java.math.BigDecimal; import java.util.*;
/**
 * 结合票据、制度、重复报销和消费行为进行费用风险审计。
 *
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Service public class ExpenseAnalysisService {
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 public Result audit(Request r){int s=0;List<String>x=new ArrayList<>();if(!r.receiptVerified()){s+=30;x.add("票据验真未通过");}if(r.duplicateReceipt()){s+=45;x.add("命中重复票据指纹");}if(r.policyOverrunPercent()>0){s+=Math.min(25,r.policyOverrunPercent());x.add("金额超过费用制度标准");}if(r.weekendOrHoliday()){s+=8;x.add("消费发生在非工作日");}if(r.merchantRiskScore()>=70){s+=20;x.add("商户风险等级较高");}if(r.amount().compareTo(new BigDecimal("10000"))>=0){s+=12;x.add("达到大额费用复核门槛");}s=Math.min(100,s);String level=s>=70?"HIGH":s>=35?"MEDIUM":"LOW",d=s>=70?"BLOCK":s>=35?"MANUAL_REVIEW":"PASS";if(x.isEmpty())x.add("票据与费用制度校验通过");return new Result(r.expenseNo(),s,level,d,x,s>=35,s<35?r.amount():BigDecimal.ZERO);}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 public record Request(@NotBlank String expenseNo,@NotBlank String employeeNo,@DecimalMin("0.01")BigDecimal amount,boolean receiptVerified,boolean duplicateReceipt,@Min(0)@Max(500)int policyOverrunPercent,boolean weekendOrHoliday,@Min(0)@Max(100)int merchantRiskScore){}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 public record Result(String expenseNo,int riskScore,String riskLevel,String decision,List<String>reasons,boolean auditorReviewRequired,BigDecimal autoApprovedAmount){}
}
