var validator;
var $deviceAddForm = $("#device-add-form");

$(function () {
    validateRule();

    $("#device-add .btn-save").click(function () {
        var name = $(this).attr("name");
        validator = $deviceAddForm.validate();
        var flag = validator.form();
        if (flag) {
            var deviceId=$("input[name='deviceId']").val();
            var deviceName=$("input[name='deviceName']").val();
            /*var schoolId=$("input[name='schoolId']").val();*/
            var username=$("input[name='username']").val();
            var buytTime=$("input[name='buytTime']").val();
            var date = new Date(buytTime);
            var deviceType=$("input[name='deviceType']").val();
            /*var firmId=$("input[name='firmId']").val();*/
            /*var classroomId=$("input[name='classroomId']").val();*/
            var state=$("input[name='state']").val();
            var num=$("input[name='num']").val();
            if (name === "save") {
                $.post(ctx + "device/add", {"deviceId":deviceId,"deviceName":deviceName,"username":username,"deviceType":deviceType,"buytTime":date,"state":state,"num":num}, function (r) {
                    if (r.code === 0) {
                        closeModal();
                        refresh();
                        $MB.n_success(r.msg);
                    } else $MB.n_danger(r.msg);
                });
            }
            if (name === "update") {
                $.post(ctx + "device/update",{"deviceId":deviceId,"deviceName":deviceName,"username":username,"deviceType":deviceType,"buytTime":date,"state":state,"num":num} , function (r) {
                    if (r.code === 0) {
                        closeModal();
                        refresh();
                        $MB.n_success(r.msg);
                    } else $MB.n_danger(r.msg);
                });
            }
        }
    });

    $("#device-add .btn-close").click(function () {
        closeModal();
    });

});

function closeModal() {
    $("#device-add-button").attr("name", "save");
    $("#device-add-modal-title").html('新增设备');
    validator.resetForm();
    $MB.closeAndRestModal("device-add");
}

function validateRule() {
    var icon = "<i class='zmdi zmdi-close-circle zmdi-hc-fw'></i> ";
    validator = $deviceAddForm.validate({
        rules: {
            deviceId: {
                required: true,
                digits: true,
                maxlength: 10
            },
            deviceName: {
                required: true,
                maxlength: 30
            },
            schoolId: {
                required: false,
                maxlength: 10
            },
            username: {
                required: true,
                maxlength: 30
            },
            buytTime: {
                required: true,
                maxlength: 30
            }
            ,
            deviceType: {
                required: true,
                maxlength: 30
            }
            ,
            firmId: {
                required: false,
                maxlength: 10
            }
            ,
            classroomId: {
                required: false,
                maxlength: 10
            }
            ,
            state: {
                required: true,
                maxlength: 10
            },
            num: {
                required: true,
                maxlength: 10
            }
        },
        messages: {
            deviceId: {
                required: icon + "请输入设备ID",
                digits: icon + "请输入整数",
                maxlength: icon + "长度不能超过10个字符"
            },
            deviceName: {
                required: icon + "请输入设备名称",
                maxlength: icon + "长度不能超过30个字符"
            },
            schoolId: {
                required: icon + "请输入所属学校",
                maxlength: icon + "长度不能超过10个字符"
            },
            username: {
                required: icon + "请输入负责人",
                maxlength: icon + "长度不能超过30个字符"
            },
            buytTime: {
                required: icon + "请输入购买时间",
                maxlength: icon + "长度不能超过30个字符"
            },
            deviceType: {
                required: icon + "请输入设备类型",
                maxlength: icon + "长度不能超过30个字符"
            },
            firmId: {
                required: icon + "请输入所属厂商",
                maxlength: icon + "长度不能超过10个字符"
            },
            classroomId: {
                required: icon + "请输入教室编号",
                maxlength: icon + "长度不能超过10个字符"
            },
            state: {
                required: icon + "请输入设备状态",
                maxlength: icon + "长度不能超过10个字符"
            },
            num: {
                required: icon + "请输入使用次数",
                maxlength: icon + "长度不能超过10个字符"
            }
        }
    });
}
