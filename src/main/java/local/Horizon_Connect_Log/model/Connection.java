package local.Horizon_Connect_Log.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OrderBy;

@Data
@Entity
public class Connection {
    @Id
    @OrderBy("ip ASC")
    private String ip;
    private String mac;
    private String date;
    private String name;
}
