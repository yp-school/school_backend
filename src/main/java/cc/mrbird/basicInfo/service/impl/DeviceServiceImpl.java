package cc.mrbird.basicInfo.service.impl;

import cc.mrbird.basicInfo.dao.DeviceMapper;
import cc.mrbird.basicInfo.domain.Device;
import cc.mrbird.basicInfo.service.DeviceService;
import cc.mrbird.common.service.impl.BaseService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service("deviceService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class DeviceServiceImpl extends BaseService<Device> implements DeviceService {
    @Autowired
    private DeviceMapper deviceMapper;

    @Override
    public List<Device> findAllDevice(Device device) {
        try {
            return this.deviceMapper.findAllDevice(device);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Device findByCode(String deviceId) {
        Example example = new Example(Device.class);
        example.createCriteria().andCondition("device_id =", deviceId);
        List<Device> list = this.selectByExample(example);
        if (list.size() == 0) {
            return null;
        } else {
            return list.get(0);
        }
    }

    @Override
    public void addDevice(Device device) {
        this.save(device);
    }

    @Override
    public void updateDevice(Device device) {
        this.updateNotNull(device);
    }

    @Override
    public void deleteDevice(String deviceId) {
        List<String> list = Arrays.asList(deviceId.split(","));
        this.batchDelete(list, "deviceId",Device.class);
    }

}

