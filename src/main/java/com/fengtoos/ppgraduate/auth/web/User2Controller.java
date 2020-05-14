package com.fengtoos.ppgraduate.auth.web;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fengtoos.ppgraduate.auth.entity.User;
import com.fengtoos.ppgraduate.auth.exception.FengtoosException;
import com.fengtoos.ppgraduate.auth.resp.RestResponseBo;
import com.fengtoos.ppgraduate.auth.service.IUser2Service;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/user2")
public class User2Controller {

    @Autowired
    IUser2Service userService;
    @Autowired
    PasswordEncoder encoder;
    @Value("${fengtoos.default.avaterUrl}")
    String defalutAvaterUrl;

    @GetMapping("/list")
    public RestResponseBo list(@RequestParam(name = "page", defaultValue = "0") Integer pageNumber,
                               @RequestParam(name = "limit", defaultValue = "10") Integer pageSize){
        Page<User> page = new Page<>(pageNumber, pageSize);
        return RestResponseBo.ok(this.userService.page(page), 0);
    }

    @GetMapping("/{id}")
    public RestResponseBo findOne(@PathVariable String id){
        User u = this.userService.getById(id);
        u.setPassword(null);
        return RestResponseBo.ok(u);
    }

    @PostMapping("/add")
    public RestResponseBo add(@RequestBody User entity){
        entity.setStatus(1);
        entity.setCreateDate(LocalDateTime.now());
        entity.setScreenName(entity.getName());
        entity.setLogo(StringUtils.isEmpty(entity.getLogo())?defalutAvaterUrl:entity.getLogo());
        if(StringUtils.isNotEmpty(entity.getPassword())){
            entity.setPassword(encoder.encode(entity.getPassword()));
        } else {
            entity.setPassword(null);
        }
        return RestResponseBo.normal(this.userService.saveOrUpdate(entity));
    }

    @PostMapping("/update")
    public RestResponseBo update(@RequestBody User entity){
        return RestResponseBo.normal(this.userService.updateById(entity));
    }

    @DeleteMapping("/{id}")
    public RestResponseBo delete(@PathVariable String id){
        return RestResponseBo.normal(this.userService.removeById(id));
    }

    @PostMapping("/action/{id}")
    public RestResponseBo disable(@PathVariable String id, Integer status){
        return RestResponseBo.normal(this.userService.action(id, status));
    }

    @GetMapping("/role/{id}")
    public RestResponseBo findByRoleId(@PathVariable String id){
        return RestResponseBo.ok(this.userService.findByRoleId(id));
    }

    /**
     * 重置密码
     * @param id
     * @param orgpass  旧密码
     * @param password 新密码
     * @return
     */
    @PostMapping("/reset/{id}")
    public RestResponseBo reset(@PathVariable String id, String orgpass, String password){
        if(StringUtils.isEmpty(orgpass) || StringUtils.isEmpty(password)){
            throw new FengtoosException("密码不能为空");
        }

        User user = this.userService.getById(id);
        if(user == null){
            throw new FengtoosException("用户不存在");
        }

        boolean flag = this.encoder.matches(orgpass, user.getPassword());
        if(!flag){
            throw new FengtoosException("用户旧密码错误");
        }

        user.setPassword(this.encoder.encode(password));
        return RestResponseBo.normal(this.userService.updateById(user));
    }
}
