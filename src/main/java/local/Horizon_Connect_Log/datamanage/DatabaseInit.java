package local.Horizon_Connect_Log.datamanage;

import local.Horizon_Connect_Log.model.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class DatabaseInit {

    public void findDevices(String html, ConnectionRepo connectionRepo) {
        Document doc = Jsoup.parse(html);
        Element table = doc.getElementById("basicDHCPDescriptionTable");
        Elements devices = table.getElementsByTag("tr");
        devices.remove(0);
        Elements attributes;
        for (Element device : devices) {
            attributes = device.getElementsByTag("td");
            List<String> databaseDevice = new ArrayList<>();
            for (Element attribute: attributes) {
                String attr = attribute.toString();
                attr = attr.replace("<td>", "");
                attr = attr.replace("</td>", "");
                databaseDevice.add(attr);
            }
            manageDevice(databaseDevice, connectionRepo);
        }
    }

    private void manageDevice(List<String> databaseDevice, ConnectionRepo connectionRepo) {
        Connection c;
        if((c = connectionRepo.findByIp(databaseDevice.get(1))) != null) {
            c.setDate(databaseDevice.get(4));
        } else {
            c = new Connection();
            c.setMac(databaseDevice.get(0));
            c.setIp(databaseDevice.get(1));
            c.setDate(databaseDevice.get(3));
        }
        connectionRepo.save(c);
    }
}
