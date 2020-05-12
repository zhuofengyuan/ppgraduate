package com.pp.ppgraduate.service;

import com.pp.ppgraduate.entity.ConsigneeModel;
import com.pp.ppgraduate.entity.UserModel;

import java.util.List;

public interface ConsigneeService {
    public boolean insertConsignee(ConsigneeModel consigneeModel);

    public boolean updateConsignee(ConsigneeModel consigneeModel);

    public boolean deleteConsignee(ConsigneeModel consigneeModel);

    public List<ConsigneeModel> selectAllConsignee (UserModel userModel);

    public ConsigneeModel selectConsignee (ConsigneeModel consigneeModel);
}
