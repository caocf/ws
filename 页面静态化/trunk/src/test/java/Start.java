import org.springframework.beans.factory.annotation.Autowired;

import com.cplatform.jettyrun.ServerRunner;


public class Start {

	
	
    public static void main(String[] args) {
        ServerRunner sr = new ServerRunner();
        sr.setPort(8080);
        sr.setContextPath("/mall-back");

        //sr.addContext("/data", "e:\\data");

        sr.start();
        
       
    }


}
