package com.cplatform.b2c.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * User: cuikai
 * Date: 13-12-11
 * Time: 下午5:08
 */
public class MarketGoodsDTO {

    private String storePrice;
    private String promotionPrice;
    private int promotionStock;
    private String goodsId;
    private int numLimit;              //购买数量(0为不限制购买数量)
    private String buyLimit;            //0为无限制，1为商盟会员  2为红砖会员
    private String isdel;               //是否已经删除，1为已经删除

    private MarketPromotion marketPromotion;

    public String getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(String promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public int getPromotionStock() {
        return promotionStock;
    }

    public void setPromotionStock(int promotionStock) {
        this.promotionStock = promotionStock;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public int getNumLimit() {
        return numLimit;
    }

    public void setNumLimit(int numLimit) {
        this.numLimit = numLimit;
    }

    public String getBuyLimit() {
        return buyLimit;
    }

    public void setBuyLimit(String buyLimit) {
        this.buyLimit = buyLimit;
    }

    public String getStorePrice() {
        return storePrice;
    }

    public void setStorePrice(String storePrice) {
        this.storePrice = storePrice;
    }

    public String getIsdel() {
        return isdel;
    }

    public void setIsdel(String isdel) {
        this.isdel = isdel;
    }

    public MarketPromotion getMarketPromotion() {
        return marketPromotion;
    }

    public void setMarketPromotion(MarketPromotion marketPromotion) {
        this.marketPromotion = marketPromotion;
    }

    public static class MarketPromotion {
        private String promotionName;
        private String promotionStartTime;
        private String promotionEndTime;
        private String createTime;
        private String promotionArea;
        private int discount;
        private String status;              //0未审核 1待审核 2审核通过 3审核未通过
        private String checkContent;
        private String checkMan;
        private String checkTime;
        private String isdel;              //是否已经删除，1为已经删除

        public String getPromotionName() {
            return promotionName;
        }

        public void setPromotionName(String promotionName) {
            this.promotionName = promotionName;
        }

        public String getPromotionStartTime() {
            return promotionStartTime;
        }

        public void setPromotionStartTime(String promotionStartTime) {
            this.promotionStartTime = promotionStartTime;
        }

        public String getPromotionEndTime() {
            return promotionEndTime;
        }

        public void setPromotionEndTime(String promotionEndTime) {
            this.promotionEndTime = promotionEndTime;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getPromotionArea() {
            return promotionArea;
        }

        public void setPromotionArea(String promotionArea) {
            this.promotionArea = promotionArea;
        }

        public int getDiscount() {
            return discount;
        }

        public void setDiscount(int discount) {
            this.discount = discount;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCheckContent() {
            return checkContent;
        }

        public void setCheckContent(String checkContent) {
            this.checkContent = checkContent;
        }

        public String getCheckMan() {
            return checkMan;
        }

        public void setCheckMan(String checkMan) {
            this.checkMan = checkMan;
        }

        public String getCheckTime() {
            return checkTime;
        }

        public void setCheckTime(String checkTime) {
            this.checkTime = checkTime;
        }

        public String getIsdel() {
            return isdel;
        }

        public void setIsdel(String isdel) {
            this.isdel = isdel;
        }
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
