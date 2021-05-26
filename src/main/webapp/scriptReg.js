function validateReg() {
    let addBtn3 = document.getElementById("myInputEmail").value;
    let addBtn4 = document.getElementById("myInputName").value;
    let addBtn5 = document.getElementById("myInputPassword").value;
    console.log(addBtn4);
    console.log(addBtn3);
    console.log(addBtn5);

    if (addBtn3 === '' || addBtn4 === ''
        || addBtn5 === '') {
        console.log(addBtn4);
        console.log(addBtn3);
        console.log(addBtn5);
        alert('Заполните все поля');
        return false;
    }
    $.ajax({
        async: false,
        type: 'POST',
        crossdomain: true,
        url: 'http://localhost:8080/job4j_todo/check.do',
        data : {
            email : addBtn3
        }
    });
    $.ajax({
        async: false,
        type: 'GET',
        crossdomain: true,
        url: 'http://localhost:8080/job4j_todo/check.do',
    }).done(function (data) {
        if (data === "true") {
            console.log("data GET reg " + data);
            alert('Пользователь с данным email уже зарегистрирован');
            //result = data.id;
            return false;
        } else {
            console.log("data  GET2 reg else " + data);
            alert('Все корректно');
        }


    });
    return true;
}