package com.cplatform.b2c.cart.impl.redis;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.util.Assert;

import java.nio.charset.Charset;

/**
 * Title. <br>
 * Description.
 * <p/>
 * Copyright: Copyright (c) 13-7-31 下午4:14
 * <p/>
 * Company: 北京宽连十方数字技术有限公司
 * <p/>
 * Author: nicky
 * <p/>
 * Version: 1.0
 * <p/>
 */
public class CartKeySerializer implements RedisSerializer<String> {

    private final String space;

    private final Charset charset;

    public Charset getCharset() {
        return charset;
    }

    public CartKeySerializer() {
        this(Charset.forName("UTF8"), null);
    }

    public CartKeySerializer(String space) {
        this(Charset.forName("UTF8"), space);
    }

    public CartKeySerializer(Charset charset, String space) {
        Assert.notNull(charset);
        this.charset = charset;
        this.space = space == null ? null : space + ":";
    }

    public String deserialize(byte[] bytes) {
        if (bytes == null) return null;
        String tmp = new String(bytes, charset);
        if (space == null) return tmp;
        if (tmp.startsWith(space)) {
            return tmp.substring(space.length());
        } else {
            return tmp;
        }
    }

    public byte[] serialize(String string) {
        if (string == null) return null;
        string = (space == null ? string : space.concat(string));
        return string.getBytes(charset);
    }

}
