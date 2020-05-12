package com.pp.ppgraduate.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fengtoos.ppgraduate.auth.entity.Authorization;
import com.pp.ppgraduate.entity.CollectModel;

import java.util.List;

public interface CollectDao extends BaseMapper<CollectModel>  {
    public boolean insertCollect(CollectModel collectModel);

    public boolean deleteCollect(CollectModel collectModel);

    public List<CollectModel> selectAllCollect(CollectModel collectModel);

    public CollectModel isCollect(CollectModel collectModel);
}
