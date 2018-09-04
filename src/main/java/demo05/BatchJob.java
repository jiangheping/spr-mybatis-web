package demo05;

import org.apache.log4j.Logger;

import java.util.List;

/**
 * 分页处理数据的job
 */
public abstract class BatchJob<T> extends BaseJob {
    private Logger log = Logger.getLogger(BaseJob.class);

    @Override
    public void execute() {
        int rows = getTotalCount();
        int pages = getPages(rows);
        if (pages == 0) {//如果没有重写getTotalCount()方法，pages则为0，那么就当直接一次请求查询解决
            pages = 1;
        }
        log.info("job :" + jobName + ", pages is " + pages);
        for (int i = 0; i < pages; i++) {
            List<T> dataList = fetchList(getBatchCount());
            if (dataList.isEmpty()) {
                return;
            }
            processList(dataList);
        }
        afterExecute();
    }

    //待处理数据总条数
    protected int getTotalCount() {
        return 0;
    }

    //获取到总的分页数
    protected int getPages(int rows) {
        int batchCount = getBatchCount();
        int mod = rows % batchCount;
        if (mod == 0) {
            return rows / batchCount;
        }
        return rows / batchCount + 1;
    }

    //获取批处理条数
    protected int getBatchCount() {
        return BATCH_COUNT;
    }

    //获取待处理数据
    protected abstract List<T> fetchList(int batchCount);

    //批量处理数据
    protected void processList(List<T> dataList) {
        if (dataList.isEmpty()) {
            return;
        }
        for (T item : dataList) {
            try {
                process(item);
            } catch (Exception e) {
                log.error("Job " + jobName + " : run Exception e", e);
                errProcess(item, e);
            }
        }
    }

    //处理单条数据
    protected void process(T item) {
    }

    //错误处理
    protected void errProcess(T item, Exception e) {
    }

    //任务处理完之后进行回调
    protected void afterExecute(){}
}
