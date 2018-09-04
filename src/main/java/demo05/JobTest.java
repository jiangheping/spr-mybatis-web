package demo05;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class JobTest {
    @Test
    public void test() {
        Job baseJob = new BaseJobExample();
        Map<String, Object> context1 = new HashMap<String, Object>();
        context1.put("jobName", "baseJobTest");
        Job batchJob = new BatchJobExample();
        Map<String, Object> context2 = new HashMap<String, Object>();
        context2.put("jobName", "batchJobTest");
        baseJob.execute(context1);
        batchJob.execute(context2);
    }
}
