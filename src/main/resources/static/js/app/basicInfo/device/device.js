$(function () {
    var settings = {
        url: ctx + "device/list",
        pageSize: 10,
        queryParams: function (params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                username: $(".device-table-form").find("input[name='username']").val().trim(),
                deviceName: $(".device-table-form").find("input[name='deviceName']").val().trim()
            };
        },
        columns: [{
            checkbox: true
        }, {
            field: 'deviceId',
            title: '设备ID'
        }, {
            field: 'deviceName',
            title: '设备名称'
        }, {
            field: 'schoolId',
            title: '所属学校'
        }, {
            field: 'username',
            title: '负责人'
        }, {
            field: 'buytTime',
            title: '购买时间'
        }, {
            field: 'deviceType',
            title: '设备类型'
        }, {
            field: 'firmId',
            title: '所属厂商'
        }, {
            field: 'classroomId',
            title: '教室编号'
        }, {
            field: 'state',
            title: '设备状态'
        }, {
            field: 'num',
            title: '使用次数'
        }]
    };

    $MB.initTable('deviceTable', settings);
});

function search() {
    $MB.refreshTable('deviceTable');
}

function refresh() {
    $(".device-table-form")[0].reset();
    search();
}

function deleteDevice() {
    var selected = $("#deviceTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要删除的设备信息！');
        return;
    }
    var deviceId = "";
    for (var i = 0; i < selected_length; i++) {
        deviceId += selected[i].deviceId;
        if (i !== (selected_length - 1)) deviceId += ",";
    }

    $MB.confirm({
        text: "删除选中设备信息，确定删除？",
        confirmButtonText: "确定删除"
    }, function () {
        $.post(ctx + 'device/delete', {"deviceId": deviceId}, function (r) {
            if (r.code === 0) {
                $MB.n_success(r.msg);
                refresh();
            } else {
                $MB.n_danger(r.msg);
            }
        });
    });
}

function exportDeviceExcel() {
    $.post(ctx + "device/excel", $(".device-table-form").serialize(), function (r) {
        if (r.code === 0) {
            window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}

function exportDeviceCsv() {
    $.post(ctx + "device/csv", $(".device-table-form").serialize(), function (r) {
        if (r.code === 0) {
            window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}