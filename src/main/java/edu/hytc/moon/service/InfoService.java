package edu.hytc.moon.service;

import edu.hytc.moon.domain.InfoEntity;
import edu.hytc.moon.vo.InfoVo;

import java.util.List;

public interface InfoService {
    List<InfoEntity> findALlInfos();

    void deleteInfo(Integer infoId,Integer upper);

    InfoEntity finInfoById(Integer infoId);

    void saveInfoEntiy(InfoEntity infoEntity);

    void updateEntity(InfoEntity infoEntity);

    List<InfoEntity> findInfoByCondition(InfoVo infoVo);
}
