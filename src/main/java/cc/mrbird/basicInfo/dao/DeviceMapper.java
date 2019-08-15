package cc.mrbird.basicInfo.dao;

import cc.mrbird.basicInfo.domain.Device;
import cc.mrbird.common.config.MyMapper;

import java.util.List;

public interface DeviceMapper extends MyMapper<Device> {
    List<Device> findAllDevice(Device device);
}
