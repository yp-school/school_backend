package cc.mrbird.basicInfo.service.impl;

import cc.mrbird.basicInfo.dao.AreaMapper;
import cc.mrbird.basicInfo.domain.Area;
import cc.mrbird.basicInfo.service.AreaService;
import cc.mrbird.common.service.impl.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("areaService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class AreaServiceImpl extends BaseService<Area> implements AreaService {

	@Autowired
	private AreaMapper areaMapper;

	@Override
	public List<Area> findAllAreas(Area area) {
		try {
			return this.areaMapper.findArea(area);
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}

	@Override
	public Area findByCode(String areaCode) {
		Example example = new Example(Area.class);
		example.createCriteria().andCondition("lower(area_code) =", areaCode.toLowerCase());
		List<Area> list = this.selectByExample(example);
		if (list.size() == 0) {
			return null;
		} else {
			return list.get(0);
		}
	}

	@Override
	@Transactional
	public void addArea(Area area) {

		this.save(area);
	}

	@Override
	@Transactional
	public void deleteAreas(String areaIds) {
		List<String> list = Arrays.asList(areaIds.split(","));
		this.batchDelete(list, "areaCode", Area.class);
	}

	@Override
	@Transactional
	public void updateArea(Area area) {

		this.updateNotNull(area);
	}

}
