
import com.cplatform.jettyrun.ServerRunner;

public class Start {

	public static void main(String[] args) {
		ServerRunner sr = new ServerRunner();
		sr.setPort(8080);
		sr.setContextPath("/mall-b2c");

//		sr.addContext("/data", "e:\\data");
        System.setProperty("spring.profiles.active","development");
		sr.start();

	
	}

}
