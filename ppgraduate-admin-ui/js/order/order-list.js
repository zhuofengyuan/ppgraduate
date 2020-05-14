layui.use(['laydate', 'table', 'layer', 'form'], function () {
    let laydate = layui.laydate, //日期
        table = layui.table, //表格
        layer = layui.layer,
        form = layui.form,
        $ = layui.jquery; //jquery
    window.$ = $;

    //执行一个 table 实例
    let userTable = table.render({
        elem: '#admin_table'
        , id: 'table'
        , url: base_path + 'admin/order/list' //数据接口
        , title: '商品表'
        , page: true //开启分页
        // ,toolbar: 'default' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
        // ,totalRow: true //开启合计行
        , parseData: function (res) {
            return {
                data: res.payload.records,
                count: res.payload.total,
                code: res.code
            }
        }
        , height: 'full-200'
        , limit: 10
        , limits: [10, 20, 50, 100]
        , headers: getToken()
        , cols: [[ //表头
            {type: 'checkbox'}
            , {field: 'orderId', title: '订单编码'}
            , {field: 'goodsName', title: '商品名称'}
            , {title: '图片', templet: function(v){
                    return '<img class="layui-nav-img" src="' + image_path + v.goodsPhoto + '">';
                }}
            , {field: 'price', title: '总金额'}
            , {title: '下单人', templet : function(v) {
                var rs = {};
                    fengtoos.server({
                        url: base_path + 'admin/member/' + v.openId,
                        type: 'get',
                        async : true,
                        success: function(resp) {
                            if(resp && resp.success){
                                rs = resp.payload.userName;
                            } else {
                                layer.msg(resp.msg, {icon: 2});
                            }
                        }
                    })
                    return rs;
            }}
            , {field: 'goodsNum', title: '下单数量'}
            , {field: 'orderTime', title: '下单时间'}
        ]]
    });
    $("#admin_add").click(function () {
        x_admin_show_back({title: '添加商品', url: './task-add.html', end: function () {
                reloadTable();
            }
        });
    });
    function reloadTable(){
        userTable.reload();
    }
    //监听行工具事件
    table.on('tool(table)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
        var data = obj.data //获得当前行数据
            , layEvent = obj.event; //获得 lay-event 对应的值
        if (layEvent === 'del') {
            layer.confirm('真的删除行么', function (index) {
                // obj.del(); //删除对应行（tr）的DOM结构
                //向服务端发送删除指令
                fengtoos.server({
                    url: base_path + 'admin/goods/' + data.goodsId,
                    type: 'delete',
                    success: function(resp) {
                        if(resp && resp.success){
                            layer.msg('删除成功', {icon: 1});
                            reloadTable();
                        } else {
                            layer.msg(resp.msg, {icon: 2});
                        }
                        layer.close(index);
                    }
                })
            });
        } else if (layEvent === 'edit') {
            xadmin.open('编辑商品','./goods-add.html?id=' + data.goodsId,590,530)
        }
    });

    //监听提交
    form.on('submit(search)', function(data) {
        var params = data.field;
        table.reload('table', {where: params})
        return false;
    });

});