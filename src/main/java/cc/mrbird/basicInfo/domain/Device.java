package cc.mrbird.basicInfo.domain;

import cc.mrbird.common.annotation.ExportConfig;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name="jcc_device_info")
public class Device implements Serializable {

    @Id
    @Column(name = "device_id")
    @ExportConfig(value = "设备ID")
    private int deviceId;

    @Column(name = "device_name")
    @ExportConfig(value = "设备名称")
    private String deviceName;

    @Column(name = "school_id")
    @ExportConfig(value = "所属学校")
    private Integer schoolId;

    @Column(name = "username")
    @ExportConfig(value = "负责人")
    private String username;

    @Column(name = "buyt_time")
    @ExportConfig(value = "购买时间")
    private Date buytTime;

    @Column(name = "device_type")
    @ExportConfig(value = "设备类型")
    private String deviceType;

    @Column(name = "firm_id")
    @ExportConfig(value = "所属厂商")
    private Integer firmId;

    @Column(name = "classroom_id")
    @ExportConfig(value = "教室编号")
    private Integer classroomId;

    @Column(name = "state")
    @ExportConfig(value = "设备状态")
    private int state;//0:关闭 1:开启

    @Column(name = "num")
    @ExportConfig(value = "使用次数")
    private int num;

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getBuytTime() {
        return buytTime;
    }

    public void setBuytTime(Date buytTime) {
        this.buytTime = buytTime;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public Integer getFirmId() {
        return firmId;
    }

    public void setFirmId(Integer firmId) {
        this.firmId = firmId;
    }

    public Integer getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(Integer classroomId) {
        this.classroomId = classroomId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
