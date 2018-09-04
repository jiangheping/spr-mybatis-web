package demo05;

/**
 * 当数据并不多时，可以直接在job里调用execute方法在service层进行处理
 */
public class BaseJobExample extends BaseJob{

    @Override
    protected void execute() {
        //调用service层方法
        System.out.println("BaseJobTest execute");
    }

}
