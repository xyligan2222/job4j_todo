function validate() {
    let addBtn = document.getElementById("myInput").value;
    if (addBtn === '' || addBtn == null) {
        alert('Заполните описание задачи');
        return false;
    }
    return true;
}
function doSend() {
    if (validate()) {
        console.log("button");
        let describe = document.getElementById("myInput").value;
        console.log(describe);
        $.ajax({
            type: 'POST',
            crossdomain: true,
            url: 'http://localhost:8080/job4j_todo/task',
            data: {
                id: 0,
                desc: describe
            }
        }).done(function () {
            console.log("success");
            $.ajax({
                type: 'GET',
                crossdomain: true,
                url: 'http://localhost:8080/job4j_todo/task',
            }).done(function (data) {
                let mainCheckbox = $('#showTasks');
                mainCheckbox.prop('checked', true);

                    let idNum = data[data.length - 1].id;
                    let message = data[data.length - 1].desc;
                    console.log(message);
                    let date = data[data.length - 1].created;
                    let state = data[data.length - 1].done;
                    let status;
                    state ? status = ' в работе' : status = " выполнено";
                    let el = `<label><input type="checkbox" id="${idNum}"><span>${status}</span></label>`;
                    $("#table").find('tbody')
                        .append($('<tr>')
                            .append($('<td>')
                                .text(message)
                            ).append($('<td>')
                                .text(date)
                            ).append($('<td >')
                                .append($(el)))
                        );
                    if (!state) {
                        $(`#${idNum}`).prop('checked', true);
                    }

                $('input:checkbox').click(function () {
                    if (this.id === 'showTasks') {
                        return;
                    }
                    let id = $(this).get()[0].id;
                    let status = $(this).parent().find('span').html();
                    if ($(this).is(':checked')) {
                        status = " выполнено";
                        $(this).parent().css('color', 'blue');
                        $(this).parent().find('span').empty().html(`${status}`);
                        updateStatus(id, false);
                    } else {
                        let status = " в работе";
                        $(this).parent().css('color', 'black');
                        $(this).parent().find('span').empty().html(`${status}`);
                        updateStatus(id, true);
                    }
                });
                mainCheckbox.click(function () {
                    if (!mainCheckbox.is(':checked')) {
                        $('input:checkbox:checked').each(function () {
                            $(this).parent().parent().parent().hide();
                        });
                    }
                    if (mainCheckbox.is(':checked')) {
                        $('input:checkbox:checked').each(function () {
                            $(this).parent().parent().parent().show();
                        });
                    }
                });
            })
        }).fail(function () {
            alert("err");
        });
    }
}

$(document).ready(function () {
    updatePage();
});

function updatePage() {
    $.ajax({
        type: 'GET',
        crossdomain: true,
        url: 'http://localhost:8080/job4j_todo/task',
    }).done(function (data) {
        let mainCheckbox = $('#showTasks');
        mainCheckbox.prop('checked', true);
        for (let i = 0; i < data.length; data[i++]) {
            let idNum = data[i].id;
            let message = data[i].desc;
            console.log(message);
            let date = data[i].created;
            let state = data[i].done;
            let status;
            state ? status = ' в работе' : status = " выполнено";
            let el = `<label><input type="checkbox" id="${idNum}"><span>${status}</span></label>`;
            $("#table").find('tbody')
                .append($('<tr>')
                    .append($('<td>')
                        .text(message)
                    ).append($('<td>')
                        .text(date)
                    ).append($('<td >')
                        .append($(el)))
                );
            if (!state) {
                $(`#${idNum}`).prop('checked', true);
            }
        }
        $('input:checkbox').click(function () {
            if (this.id === 'showTasks') {
                return;
            }
            let id = $(this).get()[0].id;
            let status = $(this).parent().find('span').html();
            if ($(this).is(':checked')) {
                status = " выполнено";
                $(this).parent().css('color', 'blue');
                $(this).parent().find('span').empty().html(`${status}`);
                updateStatus(id, false);
            } else {
                let status = " в работе";
                $(this).parent().css('color', 'black');
                $(this).parent().find('span').empty().html(`${status}`);
                updateStatus(id, true);
            }
        });
        mainCheckbox.click(function () {
            if (!mainCheckbox.is(':checked')) {
                $('input:checkbox:checked').each(function () {
                    $(this).parent().parent().parent().hide();
                });
            }
            if (mainCheckbox.is(':checked')) {
                $('input:checkbox:checked').each(function () {
                    $(this).parent().parent().parent().show();
                });
            }
        });
    }).fail (function () {
        alert ("Get error")
    });
}






function updateStatus(id, state) {
    $.ajax({
        type: 'POST',
        crossdomain: true,
        url: 'http://localhost:8080/job4j_todo/task',
        data: {
            id: id + "",
            desc: "text",
            done: state + ""
        }
    }).done(function () {
        console.log("updateStatus OK");
    }).fail(function (err) {
        alert("updateStatus Error" + err);
    })
}


