package goit.com.shorturlproject.v1.caching;


import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class JedisCacheService {

    private final JedisPool pool;


    public JedisCacheService(JedisPool pool) {
        this.pool = pool;
    }

    public void set(String key, String value) {

        try (Jedis jedis = pool.getResource()) {
            jedis.set("foo", "bar");
            System.out.println(jedis.get("foo")); // prints bar
            jedis.expire("foo", 3000L);


        }
    }

}
