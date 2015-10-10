import com.cplatform.jettyrun.ServerRunner;

public class Start {

    public static void main(String[] args) {
        ServerRunner sr = new ServerRunner();
        sr.setPort(8084);
        sr.setContextPath("/storeuc");

        //sr.addContext("/data", "e:\\data");

        sr.start();
    }


}
