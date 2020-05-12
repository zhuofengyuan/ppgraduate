package com.pp.ppgraduate.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fengtoos.ppgraduate.auth.entity.Authorization;
import com.pp.ppgraduate.entity.CollectModel;

import java.util.List;

public interface CollectService  extends IService<CollectModel>  {
    public boolean insertCollect(CollectModel collectModel);

    public boolean deleteCollect(CollectModel collectModel);

    public List<CollectModel> selectAllCollect(CollectModel collectModel);

    public CollectModel isCollect(CollectModel collectModel);
}
