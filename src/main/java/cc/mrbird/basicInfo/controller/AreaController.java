package cc.mrbird.basicInfo.controller;

import cc.mrbird.basicInfo.domain.Area;
import cc.mrbird.basicInfo.service.AreaService;
import cc.mrbird.common.annotation.Log;
import cc.mrbird.common.controller.BaseController;
import cc.mrbird.common.domain.ResponseBo;
import cc.mrbird.common.util.FileUtils;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class AreaController extends BaseController {

	@Autowired
	private AreaService areaService;

	@Log("获取区域信息  ")
	@RequestMapping("area")
	@RequiresPermissions("area:list")
	public String index() {

		return "basicInfo/area/area";
	}

	@RequestMapping("area/getArea")
	@ResponseBody
	public ResponseBo getArea(String areaCode) {
		try {
			Area area = this.areaService.findByCode(areaCode);
			return ResponseBo.ok(area);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseBo.error("获取地区信息失败，请联系网站管理员！");
		}
	}
	
	@RequestMapping("area/list")
	@ResponseBody
	public Map<String, Object> areaList(Area area) {
		try {
			System.out.println("测试。。。。。");
			List<Area> areas = this.areaService.findAllAreas(area);
			PageInfo<Area> pageInfo = new PageInfo<>(areas);
			return getDataTable(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping("area/excel")
	@ResponseBody
	public ResponseBo areaExcel(Area area) {
		try {
			List<Area> list = this.areaService.findAllAreas(area);
			return FileUtils.createExcelByPOIKit("地区表", list, Area.class);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseBo.error("导出Excel失败，请联系网站管理员！");
		}
	}

	@RequestMapping("area/csv")
	@ResponseBody
	public ResponseBo areaCsv(Area area){
		try {
			List<Area> list = this.areaService.findAllAreas(area);
			return FileUtils.createCsv("地区表", list, Area.class);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseBo.error("导出Csv失败，请联系网站管理员！");
		}
	}

	@RequestMapping("area/checkAreaName")
	@ResponseBody
	public boolean checkAreaName(String areaCode, String oldAreaCode) {
		if (StringUtils.isNotBlank(oldAreaCode) && areaCode.equalsIgnoreCase(oldAreaCode)) {
			return true;
		}
		Area result = this.areaService.findByCode(areaCode);
		return result == null;
	}

	@Log("新增区域 ")
	@RequiresPermissions("area:add")
	@RequestMapping("area/add")
	@ResponseBody
	public ResponseBo addArea(Area area) {
		try {
			this.areaService.addArea(area);
			return ResponseBo.ok("新增区域成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseBo.error("新增区域失败，请联系网站管理员！");
		}
	}

	@Log("删除区域")
	@RequiresPermissions("area:delete")
	@RequestMapping("area/delete")
	@ResponseBody
	public ResponseBo deleteArea(String ids) {
		try {
			this.areaService.deleteAreas(ids);
			return ResponseBo.ok("删除区域成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseBo.error("删除区域失败，请联系网站管理员！");
		}
	}
	
	@Log("修改区域 ")
	@RequiresPermissions("area:update")
	@RequestMapping("area/update")
	@ResponseBody
	public ResponseBo updateArea(Area area) {
		try {
			this.areaService.updateArea(area);
			return ResponseBo.ok("修改区域成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseBo.error("修改区域失败，请联系网站管理员！");
		}
	}
}
