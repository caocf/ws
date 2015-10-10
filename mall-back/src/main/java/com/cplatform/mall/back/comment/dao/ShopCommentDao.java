package com.cplatform.mall.back.comment.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.back.comment.entity.ShopComment;
/**
 * 门店评论dao
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-6-4 上午10:31:38
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: LiuDong@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */

public interface ShopCommentDao  extends PagingAndSortingRepository<ShopComment, Long> {

}
