package local.Horizon_Connect_Log.web;

import local.Horizon_Connect_Log.datamanage.ConnectionRepo;
import local.Horizon_Connect_Log.model.Connection;
import local.Horizon_Connect_Log.model.Credentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HorizonConnectController {

    @Autowired
    ConnectionRepo connect;

    Credentials credentials = new Credentials();

    public void initCred() {
        credentials.setPassword("admin");
        credentials.setUsername("admin");
    }

    @GetMapping("/")
    public String first(Model model) {
        initCred();
        Credentials c = new Credentials();
        model.addAttribute("cred", c);
        return "login";
    }

    @PostMapping("/")
    public String list(Model model, @ModelAttribute("cred") Credentials in) {
        if(!(in.getPassword().equals(credentials.getPassword()) && in.getUsername().equals(credentials.getUsername()))) {
           return "wrong";
        }
        List<Connection> devices= new ArrayList<>();
        for (Connection c : connect.findAll()) {
            devices.add(c);
        }
        model.addAttribute("device", devices);
        return "connections";
    }

    @GetMapping("/device/{ip}")
    public String link(Model model, @PathVariable String ip) {
        Connection device = connect.findByIp(ip);
        model.addAttribute("device", device);
        return "device";
    }

    @PostMapping("/device/{ip}")
    public String changeName (@ModelAttribute("device") Connection c) {
        Connection conn = connect.findByIp(c.getIp());
        conn.setName(c.getName());
        connect.save(conn);
        return "device";
    }
}
