function validate() {
    let addBtn = document.getElementById("myInput").value;
    let cIds = document.getElementById("cIds").value;

    if (addBtn === '' || addBtn == null) {
        alert('Заполните описание задачи');
        return false;
    }
    if (cIds === '' || cIds == null) {
        alert('Выберите хотя бы одну категорию');
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
                let currentUser = getCurrentUser();
                if (currentUser === null) {
                    return;
                }
                mainCheckbox.prop('checked', true);
                let idNum = data[data.length - 1].id;
                let message = data[data.length - 1].desc;
                console.log(message);
                let date = data[data.length - 1].created;
                let state = data[data.length - 1].done;
                let username = data[data.length - 1].user.name;
                let userid = data[data.length - 1].user.id;
                let status;
                let el;
                state ? status = ' в работе' : status = " выполнено";
                userid === currentUser ? el = `<label><input type="checkbox" id="${idNum}"><span>${status}</span></label>`
                    : el = `<label><input type="checkbox" disabled id="${idNum}"><span>${status}</span></label>`;
                $("#table").find('tbody')
                    .append($('<tr>')
                        .append($('<td>')
                            .text(message)
                        ).append($('<td>')
                            .text(date)
                        ).append($('<td >')
                            .append($(el)))
                        .append($('<td>')
                            .text(username)
                        )
                    );
                if (!state) {
                    $(`#${idNum}`).prop('checked', true);
                }

                $('input:checkbox').click(function () {
                    if (this.id === 'showTasks') {
                        return;
                    }
                    // let id = $(this).get()[0].id;
                    let id = $(this).attr("id");
                    console.log("id " + id);
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

function getCurrentUser() {
    let result = null;
    $.ajax({
        async: false,
        type: 'GET',
        crossdomain: true,
        url: 'http://localhost:8080/job4j_todo/auth.do',
    }).done(function (data) {
        if (data != null) {
            console.log("data " + data);
            result = data.id;
        }

    });
    return result;
}

function updatePage() {
    $.ajax({
        type: 'GET',
        crossdomain: true,
        url: 'http://localhost:8080/job4j_todo/task',
    }).done(function (data) {
        let mainCheckbox = $('#showTasks');
        let currentUser = getCurrentUser();
        if (currentUser === null) {
            return;
        }
        mainCheckbox.prop('checked', true);
        for (let i = 0; i < data.length; data[i++]) {
            let categoryJoin = null;
            let idNum = data[i].id;
            let message = data[i].desc;
            console.log(message);
            console.log("id " + idNum);
            let date = data[i].created;
            let state = data[i].done;
            let username = data[i].user.name;
            let userid = data[i].user.id;
            let parse = [];
            for (let j = 0; j < data[i].categories.length; j++) {
                let category = data[i].categories[j].name;
                if (categoryJoin == null){
                    categoryJoin = category;
                } else {
                    categoryJoin = categoryJoin + " " + category;
                }
            }

           // console.log("categor " + category1);
            //console.log('data ' , data);
            //console.log('category ' + category);
            let status;
            let el;
            state ? status = ' в работе' : status = " выполнено";
            userid === currentUser ? el = `<label><input type="checkbox" id="${idNum}"><span>${status}</span></label>`
                : el = `<label><input type="checkbox" disabled id="${idNum}"><span>${status}</span></label>`;

            $("#table").find('tbody')
                .append($('<tr> id = "task-id"')
                    .append($('<td>')
                        .text(message)
                    ).append($('<td>')
                        .text(date)
                    ).append($('<td >')
                        .append($(el)))
                    .append($('<td>')
                        .text(username)
                    ).append($('<td>')
                        .text(categoryJoin))
                );
            if (!state) {
                $(`#${idNum}`).prop('checked', true);
            }
        }
        $('input:checkbox').click(function () {
            if (this.id === 'showTasks') {
                return;
            }
            let id = $(this).attr("id");
            console.log("id2 " + id);
            // console.log("id3 " + id2);
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

        }
    ).fail(function () {
        alert("Get error")
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


function doSend2() {
    if (validate()) {
        console.log("button");
        let category
        let describe = document.getElementById("myInput").value;
        // category = document.getElementById("cIds").value;
        // category2 = document.getElementById("cIds").name;
        $.ajax({
            type: 'POST',
            crossdomain: true,
            url: 'http://localhost:8080/job4j_todo/task',
            data: {
                id: 0,
                desc: describe,
                category: $('#cIds').val().join(",")
            }
        }).done(function () {
            console.log("success");
            window.location = 'http://localhost:8080/job4j_todo/index.do';
        }).fail(function () {
            alert("error do send");
        });
    }
}