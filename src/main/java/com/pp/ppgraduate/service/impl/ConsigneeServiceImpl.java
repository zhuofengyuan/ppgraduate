package com.pp.ppgraduate.service.impl;

import com.pp.ppgraduate.dao.ConsigneeDao;
import com.pp.ppgraduate.entity.ConsigneeModel;
import com.pp.ppgraduate.entity.UserModel;
import com.pp.ppgraduate.service.ConsigneeService;
import com.pp.ppgraduate.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ConsigneeServiceImpl implements ConsigneeService {
    @Autowired
    ConsigneeDao consigneeDao;

    public boolean insertConsignee(ConsigneeModel consigneeModel){
        return consigneeDao.insertConsignee(consigneeModel);
    }

    public boolean updateConsignee(ConsigneeModel consigneeModel){
        return consigneeDao.updateConsignee(consigneeModel);
    }

    public boolean deleteConsignee(ConsigneeModel consigneeModel){
        return consigneeDao.deleteConsignee(consigneeModel);
    }

    public List<ConsigneeModel> selectAllConsignee (UserModel userModel){
        return consigneeDao.selectAllConsignee(userModel);
    }

    public ConsigneeModel selectConsignee (ConsigneeModel consigneeModel){
        return consigneeDao.selectConsignee(consigneeModel);
    }
}
