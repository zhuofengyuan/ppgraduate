layui.use(['form', 'layer', 'upload'], function() {
    var $ = layui.jquery,
        layer = layui.layer,
        form = layui.form,
        upload = layui.upload;

    //普通图片上传
    let uploadInst = upload.render({
        elem: '#logo'
        ,url: upload_path //改成您自己的上传接口
        ,auto: true
        ,headers:  getToken()
        ,before: function(obj){
            //预读本地文件示例，不支持ie8
            obj.preview(function(index, file, result){
                $('#demo1').attr('src', result); //图片链接（base64）
            });
        }
        ,done: function(res){
            //如果上传失败
            if(!res.success){
                return layer.msg('上传失败');
            }
            //上传成功
            $("#logo_v").val(res.payload);
        }
        ,error: function(){
            //演示失败状态，并实现重传
            var demoText = $('#demoText');
            demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
            demoText.find('.demo-reload').on('click', function(){
                uploadInst.upload();
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
                url: base_path + 'admin/brand/' + fengtoos.getQueryString("id"),
                type: 'get',
                success: function(data) {
                    form.val("main-form", data.payload);
                    $("#demo1").attr('src', image_path + data.payload.image);
                }
            });
        }

        //自定义验证规则
        form.verify({
            name: function(value) {
                if (value.length < 2) {
                    return '昵称至少得2个字符啊';
                }
            }
        });

        //监听提交
        form.on('submit(add)', function(data) {
            var params = data.field;
            delete params.file;
            if(params.id == ""){
                delete params.id;
            }
            fengtoos.server({
                url: base_path + 'admin/brand/add',
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