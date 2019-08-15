function updateDevice() {
    var selected = $("#deviceTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要修改的信息！');
        return;
    }
    if (selected_length > 1) {
        $MB.n_warning('一次只能修改一个信息！');
        return;
    }
    var deviceId = selected[0].deviceId;
    $.post(ctx + "device/getDevice", {"deviceId": deviceId}, function (r) {
        if (r.code === 0) {
            var $form = $('#device-add');
            $form.modal();
            var device = r.msg;
            $("#device-add-modal-title").html('修改设备');
            $form.find("input[name='deviceId']").val(device.deviceId);
            $form.find("input[name='deviceName']").val(device.deviceName);
            $form.find("input[name='schoolId']").val(device.schoolId);
            $form.find("input[name='username']").val(device.username);
            $form.find("input[name='buytTime']").val(device.buytTime);
            $form.find("input[name='deviceType']").val(device.deviceType);
            $form.find("input[name='firmId']").val(device.firmId);
            $form.find("input[name='classroomId']").val(device.classroomId);
            $form.find("input[name='state']").val(device.state);
            $form.find("input[name='num']").val(device.num);
            $("#device-add-button").attr("name", "update");
        } else {
            $MB.n_danger(r.msg);
        }
    });
}