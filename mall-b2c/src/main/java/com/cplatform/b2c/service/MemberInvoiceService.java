package com.cplatform.b2c.service;

import com.cplatform.b2c.dto.CreateOrderDTO;
import com.cplatform.b2c.model.MemberInvoice;
import com.cplatform.b2c.model.SessionUser;
import com.cplatform.dbhelp.DbHelper;
import com.cplatform.order.ActOrderInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

/**
 * Title. <br>
 * Description.
 * <p/>
 * Copyright: Copyright (c) 13-6-15 下午3:00
 * <p/>
 * Company: 北京宽连十方数字技术有限公司
 * <p/>
 * Author: nicky
 * <p/>
 * Version: 1.0
 * <p/>
 */
@Service
public class MemberInvoiceService {

    @Autowired
    DbHelper dbHelper;

    @Autowired
    CommonCacheService commonCacheService;

    public List<MemberInvoice> findInvoiceByUser(Long userId) throws SQLException {
        return dbHelper.getBeanList("select * from t_member_invoice where mid = ? order by id", MemberInvoice.class, userId);
    }

    public int findInvoiceCount(Long userId) throws SQLException {
        String count = dbHelper.queryScalar("select count(1) from t_member_invoice where mid = ?", userId);
        return Integer.valueOf(count);
    }

    public void delInvoice(String id, Long userId) throws SQLException {
        dbHelper.execute("delete from t_member_invoice where id = ? and mid = ?", id, userId);
    }

    public MemberInvoice findInvoice(String id, Long userId) throws SQLException {
        return dbHelper.getBean("select * from t_member_invoice where id = ? and mid = ?", MemberInvoice.class, id, userId);
    }

    public MemberInvoice addInvoice(MemberInvoice invoice, HttpServletResponse response) throws SQLException {
        invoice.setMid(SessionUser.getSessionUser(response).getId());
        String sql = "select SEQ_SYS_COMM_ID.nextval from dual";
        String id = dbHelper.queryScalar(sql);
        invoice.setId(Long.valueOf(id));
        sql = "insert into t_member_invoice (id, mid, invoice_type, invoice_title_type, invoice_title) values (?, ?, ?, ?, ?)";
        dbHelper.execute(sql, invoice.getId(), invoice.getMid(), invoice.getInvoiceType(), invoice.getInvoiceTitleType(), invoice.getInvoiceTitle());
        return invoice;
    }

    /**
     * 填充订单的发票信息
     *
     * @param orderInfo
     * @param id           用户发票数据ID
     * @param sysInvoiceId 发票内容ID
     * @throws SQLException
     */
    public void fillOrder(ActOrderInfo orderInfo, String id, String sysInvoiceId, HttpServletResponse response) throws SQLException {
        if (StringUtils.isBlank(id) || "0".equals(id)) {
            orderInfo.setInvoiceType(ActOrderInfo.INVOICE_TYPE_NO_INVOICE);
        } else {
            MemberInvoice invoice = findInvoice(id, SessionUser.getSessionUser(response).getId());
            if (invoice == null) {
                orderInfo.setInvoiceType(ActOrderInfo.INVOICE_TYPE_NO_INVOICE);
            } else {
                orderInfo.setInvoiceType("1".equals(invoice.getInvoiceType()) ? ActOrderInfo.INVOICE_TYPE_NORMAL : ActOrderInfo.INVOICE_TYPE_VAT);
                orderInfo.setInvoiceSubject(buildText4Invoice(invoice));

                if (StringUtils.isBlank(sysInvoiceId) || "0".equals(sysInvoiceId)) {
                    orderInfo.setInvoiceContent("商品明细");
                } else {
                    orderInfo.setInvoiceContent(commonCacheService.getInvoiceName(sysInvoiceId));
                }
            }
        }
    }

    public CreateOrderDTO fillInvoiceInfo(CreateOrderDTO createOrderDTO, String id, String sysInvoiceId, Long userId) throws SQLException {
        if (StringUtils.isBlank(id) || "0".equals(id)) {
            createOrderDTO.setInvoiceType(ActOrderInfo.INVOICE_TYPE_NO_INVOICE);
        } else {
            MemberInvoice invoice = findInvoice(id, userId);
            if (invoice == null) {
                createOrderDTO.setInvoiceType(ActOrderInfo.INVOICE_TYPE_NO_INVOICE);
            } else {
                createOrderDTO.setInvoiceType("1".equals(invoice.getInvoiceType()) ? ActOrderInfo.INVOICE_TYPE_NORMAL : ActOrderInfo.INVOICE_TYPE_VAT);
                createOrderDTO.setInvoiceSubject(buildText4Invoice(invoice));

                if (StringUtils.isBlank(sysInvoiceId) || "0".equals(sysInvoiceId)) {
                    createOrderDTO.setInvoiceContent("商品明细");
                } else {
                    createOrderDTO.setInvoiceContent(commonCacheService.getInvoiceName(sysInvoiceId));
                }
            }
        }
        return createOrderDTO;
    }

    private String buildText4Invoice(MemberInvoice invoice) {
        String invoiceTypeName = "";
        if ("1".equals(invoice.getInvoiceTitleType())) invoiceTypeName = "个人";
        if ("2".equals(invoice.getInvoiceTitleType())) invoiceTypeName = "单位";

        String invoiceTitleName = StringUtils.isBlank(invoice.getInvoiceTitle()) ? "" : invoice.getInvoiceTitle();

        String result;
        if (StringUtils.isBlank(invoiceTitleName)) {
            result = invoiceTypeName;
        } else {
            result = invoiceTitleName + " - " + invoiceTypeName;
        }
        return result;
    }

}
