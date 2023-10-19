package goit.com.shorturlproject.v1.caching;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
@Configuration
public class JedishConfig {

    @Bean
    public JedisPool pool(){
         return new JedisPool("localhost", 6379);
    }
}
