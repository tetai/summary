package cn.zkz.mq.rabbitmq;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Rabbitmq集群监控
 */
public class RabbitmqMonitorApplication {

    // HttpGet请求的封装方法
    public static String httpGet(String url , String userName , String password ) throws IOException {

        // 构建HttpClient对象
        HttpHost httpHost = new HttpHost("192.168.23.131" , 15672) ;
        BasicCredentialsProvider provider = new BasicCredentialsProvider() ;
        provider.setCredentials(new AuthScope(httpHost), new UsernamePasswordCredentials(userName, password) );
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultCredentialsProvider(provider).build();      // 构建一个HttpClient对象

        // 构建请求对象,发送请求
        HttpGet httpGet = new HttpGet(url) ;
        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

        // 解析结果
        HttpEntity entity = httpResponse.getEntity();
        String result = EntityUtils.toString(entity, Charset.forName("UTF-8"));

        // 返回
        return result ;
		
    }

    public static void main(String[] args) throws IOException {

        // 获取所有的队列信息
        String result = httpGet("http://192.168.23.131:15672/api/queues", "admin", "admin") ;
        System.out.println(result);

    }

}
