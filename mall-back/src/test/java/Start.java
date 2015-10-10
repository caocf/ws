import com.cplatform.jettyrun.ServerRunner;

public class Start {

	public static void main(String[] args) {
		ServerRunner sr = new ServerRunner();
		sr.setPort(8111);
		sr.setContextPath("/mall-back");

		// sr.addContext("/data", "d:\\data");

		sr.start();
	}

}
