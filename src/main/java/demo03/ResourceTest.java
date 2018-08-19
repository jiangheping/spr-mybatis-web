package demo03;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ResourceTest {

    /**
     * Sping获取资源的接口
     * 继承的接口有：WritableResource
     * 实现类有：PathResource
     * ClassPathResource
     * FileSystemResource等
     */
    @Test
    public void test_resource() throws IOException {
        //使用系统文件路径方式加载资源
        //WritableResource接口继承接口Resource，添加了 isWritable／getOutputStream 方法
        String filePath = "/Users/admin/spr-mybatis-web/src/main/resources/note/note.txt";
        WritableResource res1 = new PathResource(filePath);

        //使用类路径方式加载资源
        Resource res2 = new ClassPathResource("note/note.txt");

        //使用WritableResource接口读取资源文件
        OutputStream stream1 = res1.getOutputStream();
        stream1.write("这个是测试".getBytes());
        stream1.close();

        //使用Resource接口读取资源
        InputStream ins1 = res1.getInputStream();
        InputStream ins2 = res2.getInputStream();

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int i;
        while ((i = ins1.read()) != -1) {
            bos.write(i);
        }
        System.out.println(bos.toString());
        System.out.println(res1.getFilename());
        System.out.println(res2.getFilename());
    }

    /**
     * ResourceLoader接口，仅有方法getResource()方法，只支持资源前缀表达式，不支持匹配模式表达式
     * ResourcePatternResolver,继承与ResourceLoader接口，添加了资源匹配模式表达式，提供了多资源文件的载入
     * PathMatchingResourcePatternResolver,ResourcePatternResolver的实现类
     */
    @Test
    public void test_resource_loader() throws IOException {
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resourcePatternResolver.getResources("classpath*:note/not?.txt");
        for(Resource resource:resources){
            System.out.println(resource.getFilename());
        }
    }

}
