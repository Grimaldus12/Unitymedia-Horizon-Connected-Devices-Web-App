package local.Horizon_Connect_Log.datamanage;

import local.Horizon_Connect_Log.model.Connection;
import org.springframework.data.repository.CrudRepository;

public interface ConnectionRepo extends CrudRepository<Connection, Long> {
    public Connection findByIp(String ip);
}
