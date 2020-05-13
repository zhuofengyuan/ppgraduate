layui.use(['form', 'eleTree', 'layer'], function () {
    var form = layui.form,
        $ = layui.$,
        eleTree = layui.eleTree,
        layer = layui.layer;
    window.$ = $;

    let auth = getAuth();

    var el = eleTree.render({
        elem: '#ele',
        url: base_path + 'admin/sort/tree',
        headers: {"Authorization": auth.token_type + ' ' + auth.access_token},
        response: {
            statusName: "code",
            statusCode: 200,
            dataName: "payload"
        },
        highlightCurrent: true,
        emptText: '暂无数据',
        request: {
            name: "sortName"
        },
        contextmenuList: [
            {eventName: "create", text: "新增"},
            {eventName: "update", text: "编辑"},
            {eventName: "delete", text: "删除"},
            {eventName: "cp", text: "复制"},
        ],
        showCheckbox: true,
        lazy: true,
        load: function(data, callback){
            // this.filter=options.elem.attr("lay-filter");
            fengtoos.server({
                url: base_path + 'admin/sort/tree',
                type: 'get',
                data: {parent: data.sortId},
                success: function(resp){
                    if(resp && resp.success){
                        setTimeout(function() {
                            var list = resp.payload;
                            for(var i in list){
                                list[i].isLeaf = true;
                                list[i].sortName = list[i].sortItemName;
                                list[i].parentName = data.sortName;
                                list[i].parent = data.sortId;
                            }
                            callback(list);
                        },100);
                    } else {
                        callback(null);
                    }
                }
            })
        }
    });

    var resetForm = function(){
        form.val("tree-form", {
            id: "",
            parent: "",
            parentName: "根目录",
            name: "",
            sortId: "",
            sortItemId: ""
        })
        $("#icon-show").attr("class", "")
    }

    $("#newRoot").click(function(){
        resetForm();
    });

    //查看事件
    eleTree.on("nodeClick(tree)",function(d) {
        if(d.data.currentData.parent == "" || d.data.currentData.parent == null){
            d.data.currentData.parent = "";
            d.data.currentData.parentName = "根目录";
        }
        form.val("tree-form", d.data.currentData)
        $("#icon-show").attr("class", "layui-icon " + d.data.currentData.icon)
    })

    //新增事件
    eleTree.on("nodeCreate(tree)",function(d) {
        if(d.data.parent){
            layer.msg("当前已经是最末级，不允许新增", {icon: 2, time: 1500});
            return false;
        }

        form.val("tree-form", {
            id: "",
            parent: d.data.sortId,
            parentName: d.data.sortName,
            sortName: "",
            sortItemId: ""
        })
        $("#icon-show").attr("class", "")
    })

    //复制事件
    eleTree.on("nodeCp(tree)",function(d) {
        d.data.id = "";
        if(d.data.parent == "" || d.data.parent == null){
            d.data.parent = "";
            d.data.parentName = "根目录";
        }
        form.val("tree-form", d.data)
        $("#icon-show").attr("class", "layui-icon " + d.data.icon)
    })

    //编辑事件
    eleTree.on("nodeUpdate(tree)",function(d) {
        if(d.data.parent == "" || d.data.parent == null){
            d.data.parent = "";
            d.data.parentName = "根目录";
        }
        form.val("tree-form", d.data)
        $("#icon-show").attr("class", "layui-icon " + d.data.icon)
    })

    //删除事件
    eleTree.on("nodeDelete(tree)",function(d) {
        var id = d.data.sortId;
        var url = base_path + 'admin/sort/' + id;
        if(d.data.parent){
            id = d.data.sortItemId;
            url = base_path + 'admin/sort/item/' + id;
        }

        layer.confirm('真的删除行么', function (index) {
            //向服务端发送删除指令
            fengtoos.server({
                url: url,
                type: 'delete',
                success: function(resp) {
                    console.log(resp)
                    if(resp && resp.success){
                        layer.alert("删除成功", {icon: 6}, function(index) {
                            el = el.reload();
                            layer.close(index);
                        });
                    } else {
                        layer.msg(resp.msg, {icon: 2});
                    }
                    layer.close(index);
                }
            })
        });
    })

    //自定义验证规则
    form.verify({
        name: function(value) {
            if (value.length < 5) {
                return '昵称至少得5个字符啊';
            }
        },
        pass: [/(.+){6,12}$/, '密码必须6到12位'],
        repass: function(value) {
            if ($('#L_pass').val() != $('#L_repass').val()) {
                return '两次密码不一致';
            }
        }
    });

    //监听提交
    form.on('submit(submit)', function(data) {
        let parent = data.field.parent;
        let param = {
            sortName: data.field.sortName,
            sortId: data.field.sortId==""?null:data.field.sortId
        };
        let url = base_path + 'admin/sort/add';
        if(parent){
            url = base_path + 'admin/sort/addItem';
            param = {
                sortItemName: data.field.sortName,
                sortId: parent,
                sortItemId: data.field.sortItemId==""?null:data.field.sortItemId
            }
        }
        fengtoos.server({
            url: url,
            data: JSON.stringify(param),
            contentType: 'application/json',
            success: function(resp){
                if(resp && resp.success){
                    layer.alert("保存成功", {
                        icon: 6
                    }, function(index) {
                        el = el.reload();
                        resetForm();
                        layer.close(index);
                    });
                } else {
                    layer.msg(resp.msg, {icon: 2});
                }
            }
        })
        return false;
    });
});