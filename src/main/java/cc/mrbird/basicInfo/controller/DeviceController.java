package cc.mrbird.basicInfo.controller;

import cc.mrbird.basicInfo.domain.Device;
import cc.mrbird.basicInfo.service.DeviceService;
import cc.mrbird.common.annotation.Log;
import cc.mrbird.common.controller.BaseController;
import cc.mrbird.common.domain.QueryRequest;
import cc.mrbird.common.domain.ResponseBo;
import cc.mrbird.common.util.FileUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.jdbc.Null;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class DeviceController extends BaseController {

    @Autowired
    private DeviceService deviceService;

    @Log("获取设备信息  ")
    @RequestMapping("device")
    @RequiresPermissions("device:list")
    public String index() {

        return "basicInfo/device/device";
    }

    @RequestMapping("device/list")
    @ResponseBody
    public Map<String, Object> DeviceList(QueryRequest request, Device dict) {
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<Device> list = this.deviceService.findAllDevice(dict);
        PageInfo<Device> pageInfo = new PageInfo<>(list);
        return getDataTable(pageInfo);
    }

    @RequestMapping("device/getDevice")
    @ResponseBody
    public ResponseBo getArea(String deviceId) {
        try {
            Device device = this.deviceService.findByCode(deviceId);
            return ResponseBo.ok(device);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBo.error("获取地区设备失败，请联系网站管理员！");
        }
    }

    @Log("新增设备信息 ")
    @RequiresPermissions("device:add")
    @RequestMapping("device/add")
    @ResponseBody
    public ResponseBo addDevice(Device device) throws ParseException {
        Timestamp timestamp = new Timestamp(device.getBuytTime().getTime());
        device.setBuytTime(timestamp);
        try {
            this.deviceService.addDevice(device);
            return ResponseBo.ok("新增设备信息成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBo.error("新增设备信息失败，请联系网站管理员！");
        }
    }

    @Log("修改设备信息 ")
    @RequiresPermissions("device:update")
    @RequestMapping("device/update")
    @ResponseBody
    public ResponseBo updateDevice(Device device) throws ParseException {
        Timestamp timestamp = new Timestamp(device.getBuytTime().getTime());
        device.setBuytTime(timestamp);
        try {
            this.deviceService.updateDevice(device);
            return ResponseBo.ok("修改设备信息成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBo.error("修改设备信息失败，请联系网站管理员！");
        }
    }

    @Log("删除设备信息")
    @RequiresPermissions("device:delete")
    @RequestMapping("device/delete")
    @ResponseBody
    public ResponseBo deleteDevice(String deviceId) {
        try {
            this.deviceService.deleteDevice(deviceId);
            return ResponseBo.ok("删除设备信息成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBo.error("删除设备信息失败，请联系网站管理员！");
        }
    }

    @RequestMapping("device/excel")
    @ResponseBody
    public ResponseBo deviceExcel(Device device) {
        try {
            List<Device> list = this.deviceService.findAllDevice(device);
            return FileUtils.createExcelByPOIKit("设备表", list, Device.class);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBo.error("导出Excel失败，请联系网站管理员！");
        }
    }

    @RequestMapping("device/csv")
    @ResponseBody
    public ResponseBo deviceCsv(Device device){
        try {
            List<Device> list = this.deviceService.findAllDevice(device);
            return FileUtils.createCsv("设备表", list, Device.class);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBo.error("导出Csv失败，请联系网站管理员！");
        }
    }
}
