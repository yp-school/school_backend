function updateArea() {
    var selected = $("#areaTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要修改的区域！');
        return;
    }
    if (selected_length > 1) {
        $MB.n_warning('一次只能修改一个区域！');
        return;
    }
    var areaCode = selected[0].areaCode;
    $.post(ctx + "area/getArea", {"areaCode": areaCode}, function (r) {
        if (r.code === 0) {
            var $form = $('#area-add');
            $form.modal();
            var area = r.msg;
            $("#area-add-modal-title").html('修改区域');
            $form.find("input[name='areaCode']").val(area.areaCode);
            $form.find("input[name='provinceName']").val(area.provinceName);
            $form.find("input[name='cityName']").val(area.cityName);
            $form.find("input[name='country']").val(area.country);
            $("#area-add-button").attr("name", "update");
        } else {
            $MB.n_danger(r.msg);
        }
    });
}