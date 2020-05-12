package com.pp.ppgraduate.dao;

import com.pp.ppgraduate.entity.ConsigneeModel;
import com.pp.ppgraduate.entity.UserModel;
import com.pp.ppgraduate.utils.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsigneeDao {
    public boolean insertConsignee(ConsigneeModel consigneeModel);

    public List<ConsigneeModel> selectAllConsignee(UserModel userModel);

    public ConsigneeModel selectConsignee(ConsigneeModel consigneeModel);

    public boolean updateConsignee(ConsigneeModel consigneeModel);

    public boolean deleteConsignee(ConsigneeModel consigneeModel);
}
