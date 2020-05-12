layui.use(['form', 'layer', 'upload'], function() {
    var $ = layui.jquery,
        layer = layui.layer,
        form = layui.form,
        upload = layui.upload;

    fengtoos.server({
        url: base_path + 'admin/sort/tree',
        type: 'get',
        success: function(resp) {
            if(resp && resp.success){
                var str = "";
                var data = resp.payload;
                for(var i in data){
                    str += '<option value="' + data[i].sortId + '">' + data[i].sortName + '</option>';
                }
                $("#sort").html(str)
            } else {
                layer.msg(resp.msg, {icon: 2});
            }
        }
    });

    form.render('select');
    form.on('select(sort)', function(data){
        fengtoos.server({
            url: base_path + 'admin/sort/tree?parent=' + data.value,
            type: 'get',
            success: function(resp) {
                if(resp && resp.success){
                    var str = "";
                    var data = resp.payload;
                    for(var i in data){
                        str += '<option value="' + data[i].sortItemId + '">' + data[i].sortItemName + '</option>';
                    }
                    $("#sortItem").html(str);
                    form.render('select');
                } else {
                    layer.msg(resp.msg, {icon: 2});
                }
            }
        });
    });

    //普通图片上传
    let uploadInst = upload.render({
        elem: '#goodsPhoto1'
        ,url: upload_path //改成您自己的上传接口
        ,auto: true
        ,headers:  getToken()
        ,before: function(obj){
            //预读本地文件示例，不支持ie8
            obj.preview(function(index, file, result){
                $('#goodsPhoto1_i').attr('src', result); //图片链接（base64）
            });
        }
        ,done: function(res){
            //如果上传失败
            if(!res.success){
                return layer.msg('上传失败');
            }
            //上传成功
            $("#goodsPhoto1_v").val(res.payload);
        }
        ,error: function(){
            //演示失败状态，并实现重传
            var demoText = $('#goodsPhoto1_t');
            demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
            demoText.find('.demo-reload').on('click', function(){
                uploadInst.upload();
            });
        }
    });

    //普通图片上传
    let uploadInst2 = upload.render({
        elem: '#goodsPhoto2'
        ,url: upload_path //改成您自己的上传接口
        ,auto: true
        ,headers:  getToken()
        ,before: function(obj){
            //预读本地文件示例，不支持ie8
            obj.preview(function(index, file, result){
                $('#goodsPhoto2_i').attr('src', result); //图片链接（base64）
            });
        }
        ,done: function(res){
            //如果上传失败
            if(!res.success){
                return layer.msg('上传失败');
            }
            //上传成功
            $("#goodsPhoto2_v").val(res.payload);
        }
        ,error: function(){
            //演示失败状态，并实现重传
            var demoText = $('#goodsPhoto2_t');
            demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
            demoText.find('.demo-reload').on('click', function(){
                uploadInst2.upload();
            });
        }
    });

    let uploadInst3 = upload.render({
        elem: '#goodsPhoto3'
        ,url: upload_path //改成您自己的上传接口
        ,auto: true
        ,headers:  getToken()
        ,before: function(obj){
            //预读本地文件示例，不支持ie8
            obj.preview(function(index, file, result){
                $('#goodsPhoto3_i').attr('src', result); //图片链接（base64）
            });
        }
        ,done: function(res){
            //如果上传失败
            if(!res.success){
                return layer.msg('上传失败');
            }
            //上传成功
            $("#goodsPhoto3_v").val(res.payload);
        }
        ,error: function(){
            //演示失败状态，并实现重传
            var demoText = $('#goodsPhoto3_t');
            demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
            demoText.find('.demo-reload').on('click', function(){
                uploadInst3.upload();
            });
        }
    });

    fengtoos.server({
        url: base_path + 'admin/sort/tree?parent=' + $("#sort").val(),
        type: 'get',
        success: function(resp) {
            if(resp && resp.success){
                var str = "";
                var data = resp.payload;
                for(var i in data){
                    str += '<option value="' + data[i].sortItemId + '">' + data[i].sortItemName + '</option>';
                }
                $("#sortItem").html(str);
                form.render('select');

            } else {
                layer.msg(resp.msg, {icon: 2});
            }
        }
    });

        //渲染表单
        if(fengtoos.getQueryString("id")){
            fengtoos.server({
                url: base_path + 'admin/goods/' + fengtoos.getQueryString("id"),
                type: 'get',
                success: function(data) {
                    form.val("main-form", data.payload);
                    $("#goodsPhoto1_i").attr("src", image_path + data.payload.goodsPhoto1)
                    $("#goodsPhoto2_i").attr("src", image_path + data.payload.goodsPhoto2)
                    $("#goodsPhoto3_i").attr("src", image_path + data.payload.goodsPhoto3)

                    fengtoos.server({
                        url: base_path + 'admin/sort/tree?parent=' + $("#sort").val(),
                        type: 'get',
                        success: function(resp) {
                            if(resp && resp.success){
                                var str = "";
                                var data1 = resp.payload;
                                for(var i in data1){
                                    if(data.payload.sortItemId == data1[i].sortItemId){
                                        str += '<option value="' + data1[i].sortItemId + '" selected="true">' + data1[i].sortItemName + '</option>';
                                    } else {
                                        str += '<option value="' + data1[i].sortItemId + '">' + data1[i].sortItemName + '</option>';
                                    }
                                }
                                $("#sortItem").html(str);
                                form.render('select');
                            } else {
                                layer.msg(resp.msg, {icon: 2});
                            }
                        }
                    });
                }
            });
        }

        //自定义验证规则
        form.verify({
            name: function(value) {
                if (value.length < 5) {
                    return '昵称至少得5个字符啊';
                }
            }
        });

        //监听提交
        form.on('submit(add)', function(data) {
            var params = data.field;
            delete params.file;
            if(params.goodsId == ""){
                delete params.goodsId;
            }
            fengtoos.server({
                url: base_path + 'admin/goods/add',
                data: JSON.stringify(data.field),
                contentType: 'application/json',
                success: function(resp){
                    if(resp && resp.success){
                        //发异步，把数据提交给php
                        layer.alert("保存成功", {
                            icon: 6
                        }, function() {
                            //关闭当前frame
                            xadmin.close();
                            // 可以对父窗口进行刷新
                            xadmin.father_reload();
                        });
                    } else {
                        layer.msg(resp.msg, {icon: 2});
                    }
                }
            })
            return false;
        });
    });