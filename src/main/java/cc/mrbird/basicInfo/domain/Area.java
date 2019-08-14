package cc.mrbird.basicInfo.domain;

import cc.mrbird.common.annotation.ExportConfig;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "jcc_areacode_info")
public class Area implements Serializable {

	private static final long serialVersionUID = -7790334862410409053L;

	@Id
	@Column(name = "area_code")
	@ExportConfig(value = "地区编码")
	private String areaCode;

	@Column(name = "province_name")
	@ExportConfig(value = "省名")
	private String provinceName;

	@Column(name = "city_name")
	@ExportConfig(value = "市名")
	private String cityName;

	@Column(name = "country")
	private String country;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}