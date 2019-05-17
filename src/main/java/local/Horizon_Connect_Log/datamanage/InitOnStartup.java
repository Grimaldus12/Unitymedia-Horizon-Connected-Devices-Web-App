package local.Horizon_Connect_Log.datamanage;

import local.Horizon_Connect_Log.Server.HttpConnectionClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Timer;
import java.util.TimerTask;

@Component
public class InitOnStartup implements ServletContextInitializer {

    @Autowired
    ConnectionRepo connectionRepo;

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                String html = "";
                HttpConnectionClient http = new HttpConnectionClient();
                try {
                    html = http.runConnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                DatabaseInit dataInit = new DatabaseInit();
                dataInit.findDevices(html, connectionRepo);
            }
        },0, 30*1000);
    }
}
