package cc.mrbird.basicInfo.service;

import cc.mrbird.basicInfo.domain.Area;
import cc.mrbird.common.domain.Tree;
import cc.mrbird.common.service.IService;
import cc.mrbird.system.domain.Dept;

import java.util.List;

public interface AreaService extends IService<Area> {

	List<Area> findAllAreas(Area area);

	Area findByCode(String areaCode);
	
	void addArea(Area area);
	
	void updateArea(Area area);

	void deleteAreas(String areaCode);
}
