package cc.mrbird.basicInfo.dao;

import cc.mrbird.basicInfo.domain.Area;
import cc.mrbird.common.config.MyMapper;

import java.util.List;

public interface AreaMapper extends MyMapper<Area> {

    List<Area> findArea(Area area);
}