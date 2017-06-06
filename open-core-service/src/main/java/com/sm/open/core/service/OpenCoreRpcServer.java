package com.sm.open.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * RPC服务启动入口
 */
public class OpenCoreRpcServer {

    private final static Logger     LOGGER = LoggerFactory.getLogger(OpenCoreRpcServer.class);

    private final static String     SERVERNAME = "opencore-service";

    private static volatile boolean RUNNING = true;

    public static void main(String[] args) {
        try {
            LOGGER.info(SERVERNAME + " server is starting …… ");
            final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/opencore_service.xml");
            context.start();
            LOGGER.info(SERVERNAME + " server is started successful.");
            Runtime.getRuntime().addShutdownHook(new Thread() {
                public void run() {
                    context.close();
                    LOGGER.info(SERVERNAME + " server has stoped !");
                    synchronized (OpenCoreRpcServer.class) {
                        RUNNING = false;
                        OpenCoreRpcServer.class.notifyAll();
                    }
                }
            });
        } catch (RuntimeException re) {
            String errorMsg = SERVERNAME + " server start with Exception ! " + re.getMessage();
            LOGGER.error(errorMsg, re);
            System.exit(1);
        }
        synchronized (OpenCoreRpcServer.class) {
            while (RUNNING) {
                try {
                    OpenCoreRpcServer.class.wait();
                } catch (InterruptedException ie) {
                    String errorMsg = SERVERNAME + " server main thread waiting Exception ! " + ie.getMessage();
                    LOGGER.error(errorMsg, ie);
                }
            }
        }
    }
    
    /* @SuppressWarnings("resource")
    public static void main(String[] args) throws InterruptedException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "spring/opencore_service.xml" });
        context.start();
        while(true){
            Thread.sleep(300*1000);
        }
    }*/

}
