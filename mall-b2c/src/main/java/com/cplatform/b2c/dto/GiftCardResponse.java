package com.cplatform.b2c.dto;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;
import java.util.List;

/**
 * User: cuikai
 * Date: 13-9-29
 * Time: 上午10:58
 */
public class GiftCardResponse {
    private String flag;
    private String msg;
    private List<Data> data = Lists.newArrayList();

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Data> getData() {
        return data;
    }

    public void setDatas(List<Data> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public static class Data {
        private Long giftId;
        private String cardName;
        private BigDecimal price;
        private List<Long> suitItems;

        public Long getGiftId() {
            return giftId;
        }

        public void setGiftId(Long giftId) {
            this.giftId = giftId;
        }

        public String getCardName() {
            return cardName;
        }

        public void setCardName(String cardName) {
            this.cardName = cardName;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }

        public List<Long> getSuitItems() {
            return suitItems;
        }

        public void setSuitItems(List<Long> suitItems) {
            this.suitItems = suitItems;
        }
    }
}
