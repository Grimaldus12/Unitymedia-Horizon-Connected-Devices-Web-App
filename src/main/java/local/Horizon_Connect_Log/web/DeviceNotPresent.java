package local.Horizon_Connect_Log.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Device nicht gefunden")
public class DeviceNotPresent extends RuntimeException {
}
