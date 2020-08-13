package com.zez.backend.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.UnknownHostException;
import java.net.InetAddress;

/**
 * @Author Purple
 * @Date 2020/8/5
 */
@Configuration
public class ElasticSearchConfig {

    @Bean
    public RestClient restClient(){
        RestClient restClient = RestClient.builder(
                new HttpHost("localhost", 9200, "http")).build();
        return restClient;

    }



//    @Bean
//    TransportClient transportClient() throws UnknownHostException{
//        TransportClient client;
//        Settings settings = Settings.builder().put("cluster.name", "my-application").build();
//
//        client = new PreBuiltTransportClient(settings);
//        client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"),9300));
//        return client;
//    }

}
