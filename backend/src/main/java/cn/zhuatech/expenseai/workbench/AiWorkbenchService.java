/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.expenseai.workbench;

import static cn.zhuatech.expenseai.workbench.WorkbenchInput.*;

import java.math.*;
import java.util.*;
import org.springframework.stereotype.Service;

/** 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。 */
@Service
public class AiWorkbenchService {
  /** 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。 */
  public Map<String, Object> execute(Map<String, Object> r) {
    validate(r);
    keys(r, "receipts");
    Set<String> ids = new HashSet<>();
    Map<List<String>, List<Map<String, Object>>> groups =
        new TreeMap<>(
            Comparator.<List<String>, String>comparing(k -> k.get(0)).thenComparing(k -> k.get(1)));
    for (var x : rows(r, "receipts", 1, 500)) {
      keys(x, "id", "issuer", "receiptNo", "currency", "amount");
      String id = id(x, "id");
      unique(ids, id);
      String issuer = text(x, "issuer", 200).strip().toUpperCase(Locale.ROOT),
          number = text(x, "receiptNo", 100).strip().toUpperCase(Locale.ROOT);
      String currency = text(x, "currency", 3);
      if (!currency.matches("[A-Z]{3}")) throw new IllegalArgumentException("币种格式错误");
      var amount = money(x, "amount");
      groups
          .computeIfAbsent(List.of(issuer, number), k -> new ArrayList<>())
          .add(Map.of("id", id, "currency", currency, "amount", amount));
    }
    List<Map<String, Object>> out = new ArrayList<>();
    for (var e : groups.entrySet()) {
      var records = e.getValue();
      if (records.size() < 2) continue;
      Set<String> amounts = new HashSet<>();
      for (var x : records)
        amounts.add(x.get("currency") + ":" + ((BigDecimal) x.get("amount")).toPlainString());
      out.add(
          Map.of(
              "issuer",
              e.getKey().get(0),
              "receiptNo",
              e.getKey().get(1),
              "status",
              amounts.size() == 1 ? "DUPLICATE_CANDIDATE" : "NUMBER_AMOUNT_CONFLICT",
              "records",
              records));
    }
    return Map.of("groups", out, "flaggedGroupCount", out.size(), "authenticityVerified", false);
  }
}
