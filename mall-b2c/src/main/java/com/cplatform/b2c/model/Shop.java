package com.cplatform.b2c.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 商品评论
 * 标题、简要说明. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) May 29, 2013 3:25:40 PM
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * @author zhangyinf@c-platform.com
 * @version 1.0.0
 */

@Entity
@Table(name="T_SHOP")
public class Shop {
	
	@Id
	private Long id;
}
