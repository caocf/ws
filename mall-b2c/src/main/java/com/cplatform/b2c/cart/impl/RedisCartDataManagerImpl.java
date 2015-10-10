package com.cplatform.b2c.cart.impl;

import com.cplatform.b2c.cart.CartBox;
import com.cplatform.b2c.cart.impl.redis.CartKeySerializer;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;

/**
 * Title. <br>
 * Description.
 * <p/>
 * Copyright: Copyright (c) 13-6-9 上午9:13
 * <p/>
 * Company: 北京宽连十方数字技术有限公司
 * <p/>
 * Author: nicky
 * <p/>
 * Version: 1.0
 * <p/>
 */
public class RedisCartDataManagerImpl extends AbstractCartDataManager {

    private RedisTemplate redisTemplate;

    private CartKeySerializer boxSerializer = new CartKeySerializer("cartbox");

    public RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    CartBox getObject(final String identity) {
        if (!StringUtils.hasText(identity)) return null;
        return (CartBox) redisTemplate.execute(new RedisCallback<CartBox>() {
            @Override
            public CartBox doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] key = boxSerializer.serialize(identity);
                if (connection.exists(key)) {
                    byte[] data = connection.get(key);
                    try {
                        return CartBox.fromByteArray(data);
                    } catch (Exception e) {
                        // ignore
                    }
                    return null;
                }
                return null;
            }
        });
    }

    @Override
    void putObject(final String identity, final CartBox cartBox, final long expireSeconds) {
        if (!StringUtils.hasText(identity)) return;
        redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] key = boxSerializer.serialize(identity);
                connection.set(key, cartBox.toByteArray());
                connection.expire(key, (int) expireSeconds);
                return null;
            }
        });
    }

    @Override
    void delObject(final String identity) {
        if (!StringUtils.hasText(identity)) return;
        redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                connection.del(boxSerializer.serialize(identity));
                return null;
            }
        });
    }



}
