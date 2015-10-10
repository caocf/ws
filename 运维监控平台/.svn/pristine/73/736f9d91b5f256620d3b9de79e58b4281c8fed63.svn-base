package com.cplatform.mall.dc.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PageStaticInterface {

	@Autowired
	private AppConfig config;

//	public String pageStatic(ItemEventBean obj) {
//		String msg = "";
//		String url = config.getPageStaticUrl();
//		HttpURLConnection con = null;
//		OutputStream out = null;
//		BufferedReader br = null;
//		try {
//			JSONObject jsonObject = JSONObject.fromObject(obj);
//			System.out.println("--------------json=: " + jsonObject.toString());
//			String code = jsonObject.toString();
//			con = (HttpURLConnection) new URL(url).openConnection();
//			con.setDoInput(true);
//			con.setDoOutput(true);
//			con.setRequestMethod("POST");
//			con.setConnectTimeout(1000 * 10);
//			con.setRequestProperty("event-transaction-id", "1000");
//			con.setRequestProperty("event-type-id", "1000");
//			con.setRequestProperty("Content-Length", Integer.toString(code.length()));
//			out = con.getOutputStream();
//			System.out.println("--------------code=: " + new String(code.getBytes("utf-8")));
//			out.write(code.getBytes("utf-8"));
//			out.flush();
//			out.close();
//			br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
//			final StringBuffer str = new StringBuffer(255);
//			String line = "";
//			while ((line = br.readLine()) != null) {
//				str.append(line);
//			}
//			System.out.println("-----------返回："+str.toString());
//			JSONObject json = JSONObject.fromObject(str.toString());
//			msg = json.getString("MSG");
//		}
//		catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			msg = "调用页面静态化接口失败";
//		}
//		finally {
//			if (out != null) {
//				try {
//					out.close();
//				}
//				catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//			if (br != null) {
//				try {
//					br.close();
//				}
//				catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
//		return msg;
//	}

}
