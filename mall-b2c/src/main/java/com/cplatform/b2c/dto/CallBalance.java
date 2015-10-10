package com.cplatform.b2c.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * User: cuikai
 * Date: 13-10-21
 * Time: 下午3:01
 */
public class CallBalance {
    /**
     * 余额.
     */
    private int balance;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 商城币余额.
     */
    private int coin;

    /**
     * 有效期.
     */
    private String invalidDate;

    /**
     * 限额.
     */
    private int limitFee;

    /**
     * 可用金额.
     */
    private int payFee;

    /**
     * 积分余额
     */
    private int score;

    /**
     * 当前已用费用.
     */
    private int useFee;

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public String getInvalidDate() {
        return invalidDate;
    }

    public void setInvalidDate(String invalidDate) {
        this.invalidDate = invalidDate;
    }

    public int getLimitFee() {
        return limitFee;
    }

    public void setLimitFee(int limitFee) {
        this.limitFee = limitFee;
    }

    public int getPayFee() {
        return payFee;
    }

    public void setPayFee(int payFee) {
        this.payFee = payFee;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getUseFee() {
        return useFee;
    }

    public void setUseFee(int useFee) {
        this.useFee = useFee;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
