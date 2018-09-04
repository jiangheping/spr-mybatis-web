package demo05;

import org.apache.log4j.Logger;

import java.util.Map;

/**
 * 基础job，不分页
 * protected修饰的变量，子类和同一包下都可以访问
 */
public abstract class BaseJob implements Job {

    private Logger log = Logger.getLogger(BaseJob.class);
    protected String jobName = this.getClass().getName();
    protected static final int BATCH_COUNT = 100;//批处理条数

    @Override
    public void execute(Map<String, Object> context) {
        log.info("job start,job name is :" + context.get("jobName"));
        execute();
        log.info("job end,job name is :" + context.get("jobName"));
    }

    protected abstract void execute();//定义成子类可以访问到

}
