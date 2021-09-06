package cn.zkz.structure;

import javax.security.auth.Subject;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public interface Jdkproxy {
    void doSomething();
}

/**
 * RealSubject
 * 真实主题类
 * @author
 * @create 2018-03-29 14:21
 **/
class RealSubject implements Jdkproxy {
    @Override
    public void doSomething() {
        System.out.println("RealSubject do something");
    }
}



/**
 * JDKDynamicProxy
 * jdkd动态代理
 *
 * @author
 * @create 2018-03-29 16:17
 **/
class JDKDynamicProxy implements InvocationHandler {

    private Object target;

    public JDKDynamicProxy(Object target) {
        this.target = target;
    }

    /**
     * 获取被代理接口实例对象
     * @param <T>
     * @return
     */
    public <T> T getProxy() {
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Do something before");
        Object result = method.invoke(target, args);
        System.out.println("Do something after");
        return result;
    }
}

class Test{
    public static void main(String[] args) {


        // 保存生成的代理类的字节码文件
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        // jdk动态代理测试
        Jdkproxy subject = new JDKDynamicProxy(new RealSubject()).getProxy();
        subject.doSomething();
    }
}