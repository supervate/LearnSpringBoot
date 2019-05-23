package com.learn.JMXLearn;

import com.learn.JMXLearn.mbeanImpl.Hello;

import javax.management.*;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;
import java.lang.management.ManagementFactory;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class HelloAgent {
    public static void main(String[] args) throws Exception {
        MBeanServer server = addMBean();
        startServer(server);
    }

    public static MBeanServer addMBean() throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException {
        // 首先建立一个MBeanServer，MBeanServer用来管理我们的MBean，通常是通过MBeanServer来获取我们MBean的信息
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();

        String domainName = "MyMBean";
        // 为MBean（下面的new Hello()）创建ObjectName实例
        ObjectName helloName = new ObjectName(domainName+":name=HelloWorld");

        // 将new Hello()这个对象注册到MBeanServer上去
//        server.registerMBean(new Hello(),helloName);
        return server;
    }

    public static void startServer(MBeanServer server) throws Exception{
        /**
         * JMXConnectorServer service
         */
        //这句话非常重要，不能缺少！注册一个端口，绑定url后，客户端就可以使用rmi通过url方式来连接JMXConnectorServer
        //创建RMI注册表
        Registry registry = LocateRegistry.createRegistry(1099);

        //构造JMXServiceURL
        JMXServiceURL jmxServiceURL = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:1099/jmxrmi");
        //创建JMXConnectorServer
        JMXConnectorServer cs = JMXConnectorServerFactory.newJMXConnectorServer(jmxServiceURL, null, server);
        //启动
        cs.start();
    }
}