package com.fengtoos.ppgraduate.auth.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fengtoos.ppgraduate.auth.entity.Role;
import com.fengtoos.ppgraduate.auth.entity.UserRole;
import com.fengtoos.ppgraduate.auth.resp.RestResponseBo;
import com.fengtoos.ppgraduate.auth.service.IRoleService;
import com.fengtoos.ppgraduate.auth.service.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  角色控制器
 * </p>
 *
 * @author fengtoos
 * @since 2020-04-07
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    IRoleService roleService;
    @Autowired
    IUserRoleService userRoleService;

    @GetMapping("/{id}")
    public RestResponseBo findOne(@PathVariable String id){
        return RestResponseBo.ok(this.roleService.findById(id));
    }

    @GetMapping("/list")
    public RestResponseBo list(@RequestParam(name = "page", defaultValue = "0") Integer pageNumber,
                               @RequestParam(name = "limit", defaultValue = "10") Integer pageSize){
        Page<Role> page = new Page<>(pageNumber, pageSize);
        return RestResponseBo.ok(this.roleService.page(page), 0);
    }

    @PostMapping("/add")
    public RestResponseBo add(@RequestBody Role entity){
        return RestResponseBo.normal(this.roleService.saveOrUpdate(entity));
    }

    @PostMapping("/update")
    public RestResponseBo update(@RequestBody Role entity){
        return RestResponseBo.normal(this.roleService.updateById(entity));
    }

    @DeleteMapping("/{id}")
    public RestResponseBo delete(@PathVariable String id){
        return RestResponseBo.normal(this.roleService.removeById(id));
    }

    @PostMapping("/add/user/{id}")
    public RestResponseBo addUser(@RequestBody List<UserRole> data, @PathVariable String id){
        this.userRoleService.remove(new QueryWrapper<UserRole>().eq("role_id", id));
        return RestResponseBo.normal(this.userRoleService.saveBatch(data));
    }
}
