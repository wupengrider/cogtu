package com.cogtu.realtime.tools;

import com.cogtu.realtime.conf.PublicConf;
import redis.clients.jedis.Jedis;
import scala.Serializable;

/**
 * Created by lenovo on 2015/6/9.
 */
public class SingleJedis implements Serializable {

    private static Jedis uniqueJedis = null;

    private SingleJedis() {
    }

    public static Jedis getInstance() {
        if (null == uniqueJedis) {
            uniqueJedis = new Jedis(PublicConf.SC_REDIS_IP(), PublicConf.SC_REDIS_PORT());
        }
        return uniqueJedis;
    }
}
