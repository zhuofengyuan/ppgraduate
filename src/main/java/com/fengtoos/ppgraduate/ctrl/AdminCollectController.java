package com.fengtoos.ppgraduate.ctrl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fengtoos.ppgraduate.auth.entity.User;
import com.fengtoos.ppgraduate.auth.exception.FengtoosException;
import com.fengtoos.ppgraduate.auth.resp.RestResponseBo;
import com.fengtoos.ppgraduate.auth.service.IUser2Service;
import com.pp.ppgraduate.entity.CollectModel;
import com.pp.ppgraduate.service.CollectService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/admin/collect")
public class AdminCollectController {

    @Autowired
    CollectService collectService;

    @GetMapping("/list")
    public @ResponseBody RestResponseBo list(@RequestParam(name = "page", defaultValue = "0") Integer pageNumber,
                                             @RequestParam(name = "limit", defaultValue = "10") Integer pageSize){
        Page<CollectModel> page = new Page<>(pageNumber, pageSize);
        return RestResponseBo.ok(this.collectService.page(page), 0);
    }

    @DeleteMapping("/{id}")
    public RestResponseBo delete(@PathVariable String id){
        return RestResponseBo.normal(this.collectService.removeById(id));
    }

}
