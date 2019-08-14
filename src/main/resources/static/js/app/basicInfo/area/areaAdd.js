var validator;
var $areaAddForm = $("#area-add-form");

$(function () {
    validateRule();

    $("#area-add .btn-save").click(function () {
        var name = $(this).attr("name");
        validator = $areaAddForm.validate();
        var flag = validator.form();
        if (flag) {
            if (name === "save") {
                $.post(ctx + "area/add", $areaAddForm.serialize(), function (r) {
                    if (r.code === 0) {
                        closeModal();
                        refresh();
                        $MB.n_success(r.msg);
                    } else $MB.n_danger(r.msg);
                });
            }
            if (name === "update") {
                $.post(ctx + "area/update", $areaAddForm.serialize(), function (r) {
                    if (r.code === 0) {
                        closeModal();
                        refresh();
                        $MB.n_success(r.msg);
                    } else $MB.n_danger(r.msg);
                });
            }
        }
    });

    $("#area-add .btn-close").click(function () {
        closeModal();
    });

});

function closeModal() {
    $("#area-add-button").attr("name", "save");
    $("#area-add-modal-title").html('新增区域');
    validator.resetForm();
    $MB.closeAndRestModal("area-add");
}

function validateRule() {
    var icon = "<i class='zmdi zmdi-close-circle zmdi-hc-fw'></i> ";
    validator = $areaAddForm.validate({
        rules: {
            areaCode: {
                required: true,
                digits: true,
                maxlength: 10
            },
            province: {
                required: true,
                maxlength: 10
            },
            cityName: {
                required: true,
                maxlength: 10
            },
            country: {
                required: true,
                maxlength: 10
            }
        },
        messages: {
            areaCode: {
                required: icon + "请输入地区编码",
                digits: icon + "请输入整数",
                maxlength: icon + "长度不能超过10个字符"
            },
            provinceName: {
                required: icon + "请输入省名",
                maxlength: icon + "长度不能超过10个字符"
            },
            cityName: {
                required: icon + "请输入市名",
                maxlength: icon + "长度不能超过10个字符"
            },
            country: {
                required: icon + "请输入区/县名",
                maxlength: icon + "长度不能超过10个字符"
            }
        }
    });
}