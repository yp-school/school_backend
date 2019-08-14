$(function () {
    var settings = {
        url: ctx + "area/list",
        pageSize: 10,
        queryParams: function (params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                cityName: $(".area-table-form").find("input[name='cityName']").val().trim(),
                country: $(".area-table-form").find("input[name='country']").val().trim()
            };
        },
        columns: [{
            checkbox: true
        }, {
            field: 'areaCode',
            title: '地区编码'
        }, {
            field: 'provinceName',
            title: '省名'
        }, {
            field: 'cityName',
            title: '市名'
        }, {
            field: 'country',
            title: '区/县名'
        }]
    };

    $MB.initTable('areaTable', settings);
});

function search() {
    $MB.refreshTable('areaTable');
}

function refresh() {
    $(".area-table-form")[0].reset();
    search();
}

function deleteArea() {
    var selected = $("#areaTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要删除的地区！');
        return;
    }
    var ids = "";
    for (var i = 0; i < selected_length; i++) {
        ids += selected[i].areaCode;
        if (i !== (selected_length - 1)) ids += ",";
    }

    $MB.confirm({
        text: "删除选中地区，确定删除？",
        confirmButtonText: "确定删除"
    }, function () {
        $.post(ctx + 'area/delete', {"ids": ids}, function (r) {
            if (r.code === 0) {
                $MB.n_success(r.msg);
                refresh();
            } else {
                $MB.n_danger(r.msg);
            }
        });
    });
}

function exportAreaExcel() {
    $.post(ctx + "area/excel", $(".area-table-form").serialize(), function (r) {
        if (r.code === 0) {
            window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}

function exportAreaCsv() {
    $.post(ctx + "area/csv", $(".area-table-form").serialize(), function (r) {
        if (r.code === 0) {
            window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}