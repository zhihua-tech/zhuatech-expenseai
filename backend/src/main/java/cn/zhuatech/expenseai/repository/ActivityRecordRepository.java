/* Copyright 2026 上海如静知华信息科技有限公司 */
package cn.zhuatech.expenseai.repository; import cn.zhuatech.expenseai.model.ActivityRecord; import org.springframework.data.jpa.repository.JpaRepository;
public interface ActivityRecordRepository extends JpaRepository<ActivityRecord,Long>{}
