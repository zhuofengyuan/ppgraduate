package com.pp.ppgraduate.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pp.ppgraduate.dao.CollectDao;
import com.pp.ppgraduate.entity.CollectModel;
import com.pp.ppgraduate.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CollectServiceImpl extends ServiceImpl<CollectDao, CollectModel> implements CollectService {

    @Autowired
    CollectDao collectDao;

    public boolean insertCollect(CollectModel collectModel){
        return collectDao.insertCollect(collectModel);
    }

    public boolean deleteCollect(CollectModel collectModel){
        return collectDao.deleteCollect(collectModel);
    }

    public List<CollectModel> selectAllCollect(CollectModel collectModel){
        return collectDao.selectAllCollect(collectModel);
    }

    public CollectModel isCollect(CollectModel collectModel){
        return collectDao.isCollect(collectModel);
    }
}

