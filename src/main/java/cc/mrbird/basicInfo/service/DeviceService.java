package cc.mrbird.basicInfo.service;

import cc.mrbird.basicInfo.domain.Device;
import cc.mrbird.common.config.MyMapper;
import cc.mrbird.common.service.IService;

import java.util.List;

public interface DeviceService extends IService<Device> {
    List<Device> findAllDevice(Device device);

    Device findByCode(String deviceId);

    void addDevice(Device device);

    void updateDevice(Device device);

    void deleteDevice(String deviceId);

}
