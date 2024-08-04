package com.application.usedallea.zzim.service;

import com.application.usedallea.zzim.dao.ZzimDAO;
import com.application.usedallea.zzim.dto.ZzimDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ZzImServiceImpl implements ZzimService{

    @Autowired
    private ZzimDAO zzimDAO;

    private static Logger logger = LoggerFactory.getLogger(ZzImServiceImpl.class);

    @Override
    public void insertZzim(ZzimDTO zzimDTO) {
        zzimDAO.insertZzim(zzimDTO);
    }

    @Override
    public int getZzimCount(long productId) {
        return zzimDAO.getZzimCount(productId);
    }

    @Override
    public boolean checkZzim(ZzimDTO zzimDTO) {
        boolean isCheckZzim = false;

        if(zzimDAO.getZzimId(zzimDTO) > 1){  // userId와productId의 zzimId가 한개라도 있으면 찜의 중복
            isCheckZzim = true;
        }
        return isCheckZzim;
    }

    @Override
    public void removeZzim(ZzimDTO zzimDTO) {
        zzimDAO.deleteZzim(zzimDTO);
    }

}
