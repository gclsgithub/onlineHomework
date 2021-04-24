package edu.hytc.moon.service.impl;

import edu.hytc.moon.domain.InfoEntity;
import edu.hytc.moon.mapper.HomeWorkMapper;
import edu.hytc.moon.mapper.InfoMapper;
import edu.hytc.moon.service.InfoService;
import edu.hytc.moon.vo.InfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfoServiceImpl implements InfoService {

    @Autowired
    private InfoMapper infoMapper;


    @Override
    public List<InfoEntity> findALlInfos() {
        return infoMapper.findAllInfos();
    }

    @Override
    public void deleteInfo(Integer infoId, Integer upper) {
        infoMapper.deteInfoById(infoId,upper);
    }


    @Override
    public InfoEntity finInfoById(Integer infoId) {
        return infoMapper.findAllInfoById(infoId);
    }

    @Override
    public void saveInfoEntiy(InfoEntity infoEntity) {
        infoMapper.inserInfo(infoEntity);
    }

    @Override
    public void updateEntity(InfoEntity infoEntity) {
        infoMapper.modifyInfoById(infoEntity);
    }

    @Override
    public List<InfoEntity> findInfoByCondition(InfoVo infoVo) {
        return infoMapper.findInfoByCondition(infoVo);
    }
}
