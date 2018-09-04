package demo05;

import java.util.Map;

/**
 * 假设该类为job框架的接口
 * 当定时任务启动时，调用该接口方法
 */
public interface Job {
    /**
     * 执行作业
     *
     * @param context 分片上下文
     */
    void execute(Map<String, Object> context);
}
