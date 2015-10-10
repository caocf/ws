import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.text.translate.NumericEntityEscaper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Arrays;

/**
 * Title. <br>
 * Description.
 * <p/>
 * Copyright: Copyright (c) 13-6-8 下午3:10
 * <p/>
 * Company: 北京宽连十方数字技术有限公司
 * <p/>
 * Author: nicky
 * <p/>
 * Version: 1.0
 * <p/>
 */
public class VerTest {

    public static void main(String[] args) {
            String[] k = new String[] { "1", "2" };
            String[] v = (String[]) ArrayUtils.subarray(k, 2, k.length);
            System.out.println(Arrays.toString(v));


        int coinMoney = 0;

        int x = coinMoney / 100;
        System.out.println(x);


        String a = "\\u6d41\\u884c";
        a = a.replaceAll("\\\\u", "\\u");
        String y = StringEscapeUtils.unescapeJava("\\u6d41\\u884c");
        System.out.println(y);


        String e = StringEscapeUtils.ESCAPE_XML.with( NumericEntityEscaper.between(0x7f, Integer.MAX_VALUE) ).translate("\\u6d41\\u884c");
        System.out.println(e);

        String t = "{'after_service': '售后服务','imgs': [],'item_intro': '强效去污，增加50%洁净力，轻松清除10大死角。安全呵护。<br />强效去污，增加50%洁净力，轻松清除10大死角。安全呵护。<br />强效去污，增加50%洁净力，轻松清除10大死角。安全呵护。<br />强效去污，增加50%洁净力，轻松清除10大死角。安全呵护。','item_mode': '0','item_name': '奥妙 净蓝全效水清莲香深层洁净洗衣液 500gX(2+1)','item_no': '737','item_param': {},'item_type': '101735','pay_hint': '强效去污，增加50%洁净力，轻松清除10大死角。安全呵护。','pay_method': '现金','shop_id': '300043','shop_name': '苏州欧尚超市有限公司张家港东二环店'}";

        HttpURLConnection conn = null;
        try {
            URI uri = URI.create ("http://192.168.1.14/notice/item/gen.chtml");


            URL url = uri.toURL();
            conn = (HttpURLConnection) url.openConnection();

            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("event-transaction-id", "1000");
            conn.setRequestProperty("event-type-id", "1000");
            conn.setRequestProperty("Content-Length", Integer.toString(t.toString().length()));
            OutputStream outputStream = conn.getOutputStream();

            outputStream.write(t.toString().getBytes("UTF-8"));
            outputStream.flush();
            outputStream.close();


            final BufferedReader in;
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line;
            final StringBuilder stringBuffer = new StringBuilder(255);

            while ((line = in.readLine()) != null) {
                stringBuffer.append(line);
                stringBuffer.append("\n");
            }
            System.out.println(stringBuffer.toString());
        } catch (final Exception ee) {
            throw new RuntimeException(ee);
        } finally {
            if (conn != null && conn instanceof HttpURLConnection) {
                ((HttpURLConnection)conn).disconnect();
            }
        }

    }
}
