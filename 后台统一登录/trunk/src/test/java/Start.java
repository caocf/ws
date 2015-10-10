import com.cplatform.jettyrun.ServerRunner;

public class Start {

    public static void main(String[] args) {
        ServerRunner sr = new ServerRunner();
        sr.setPort(8084);
        sr.setContextPath("/backuc");

        //sr.addContext("/data", "e:\\data");

        sr.start();
    }


}
