package com.cplatform.b2c.service;

/**
 * User: cuikai
 * Date: 13-10-28
 * Time: 下午12:13
 */
public enum PayFormChoose {

    CASH_AND_COIN("cash_and_coin"),

    CASH_AND_SCORE("cash_and_score"),

    CASH_AND_BALANCE("cash_and_balance"),

    COIN_AND_BALANCE("coin_and_balance"),

    SCORE_AND_BALANCE("score_and_balance"),

    ONLY_CASH("only_cash"),

    ONLY_COIN("only_coin"),

    ONLY_SCORE("only_score"),

    ONLY_BALANCE("only_balance");

    PayFormChoose(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
