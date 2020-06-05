package com.sm.open.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName: OpenCoreRpcServer
 * @Description: RPC服务启动入口
 * @Author yangtongbin
 * @Date 2018/9/14 10:44
 */
public class OpenCoreRpcServer {

    private final static Logger     LOGGER = LoggerFactory.getLogger(OpenCoreRpcServer.class);

    private final static String     SERVER_NAME = "openCore-service";

    private static volatile boolean RUNNING = true;

    public static void main(String[] args) {
        try {
            LOGGER.info(SERVER_NAME + " server is starting …… ");
            final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/core-spring-context.xml");
            context.start();
            LOGGER.info(SERVER_NAME + " server is started successful.");
            Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    context.close();
                    LOGGER.info(SERVER_NAME + " server has stopped !");
                    synchronized (OpenCoreRpcServer.class) {
                        RUNNING = false;
                        OpenCoreRpcServer.class.notifyAll();
                    }
                }
            });
        } catch (RuntimeException re) {
            String errorMsg = SERVER_NAME + " server start with Exception ! " + re.getMessage();
            LOGGER.error(errorMsg, re);
            System.exit(1);
        }
        synchronized (OpenCoreRpcServer.class) {
            while (RUNNING) {
                try {
                    OpenCoreRpcServer.class.wait();
                } catch (InterruptedException ie) {
                    String errorMsg = SERVER_NAME + " server main thread waiting Exception ! " + ie.getMessage();
                    LOGGER.error(errorMsg, ie);
                }
            }
        }
    }

}
