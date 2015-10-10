package com.cplatform.b2c.dto;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * User: cuikai
 * Date: 13-8-12
 * Time: 上午10:48
 */
@XmlRootElement(name = "xml")
public class kuaidi100_DTO {

    private String message;
    private String number;
    private String ischeck;
    private String com;
    private String status;
    private String condition;
    private String state;
    private List<kuaidi100_Detail> data = Lists.newArrayList();

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @XmlElement(name = "nu")
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getIscheck() {
        return ischeck;
    }

    public void setIscheck(String ischeck) {
        this.ischeck = ischeck;
    }

    public String getCom() {
        return com;
    }

    public void setCom(String com) {
        this.com = com;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<kuaidi100_Detail> getData() {
        return data;
    }

    public void setData(List<kuaidi100_Detail> data) {
        this.data = data;
    }

    @XmlRootElement(name = "data")
    public static class kuaidi100_Detail {
        private String time;
        private String context;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getContext() {
            return context;
        }

        public void setContext(String context) {
            this.context = context;
        }
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
