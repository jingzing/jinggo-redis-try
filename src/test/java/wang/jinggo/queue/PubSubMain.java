package wang.jinggo.queue;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import wang.jinggo.JinggoRedisTryApplication;
import wang.jinggo.util.RedisCacheManager;
import wang.jinggo.util.RedisCachePool;
import wang.jinggo.util.RedisDataBaseType;

/**
 * @author wangyj
 * @description
 * @create 2018-08-24 9:12
 **/
public class PubSubMain {

    /**
     * 启动嵌入式的Tomcat并初始化Spring环境
     */
    public static void main(String[] args) throws Exception {
/*        long num = 17;
        byte[] byteNum = new byte[8];
        for (int ix = 0; ix < 8; ++ix) {
            int offset = 64 - (ix + 1) * 8;
            byteNum[ix] = (byte) ((num >> offset) & 0xff);
        }
        System.out.println(byteNum);*/
        // main 方法里面加载application
        ApplicationContext app = SpringApplication.run(JinggoRedisTryApplication.class, args);
        RedisCacheManager redisCacheManager = (RedisCacheManager) app.getBean("redisCacheManager");
        RedisCachePool pool = redisCacheManager.getRedisPoolMap().get(RedisDataBaseType.defaultType.toString());
        System.out.println("pool=========>>>" + pool.toString());

        SubThread subThread = new SubThread(redisCacheManager);
        subThread.start();

        Publisher publisher = new Publisher(redisCacheManager);
        publisher.start();
    }

}
