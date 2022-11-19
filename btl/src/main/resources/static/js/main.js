$(document).ready(function () {

    loadData();

    $("#get-user").click(function () {
        showUserPreview(1);
    })

    $("#create-swap").click(function () {
        createSwap();
    })

    $("body").on("click", ".get-info", function () {
        var id = $(this).data('id');
        showSwapWishPreView(id);
    })

});

function loadData() {
    $.ajax({
        url: 'http://localhost:8080/swap',
        type: 'GET',
        success: function (swaps) {
            ul_listSwapPreview = document.getElementById("ul_listSwapPreview")
            swaps.map(function (swap) {
                var htmlString = `\n\t\t\t\t\t\t\t\t<li class="list-group-item" id="swapId-` + swap.id + `">
                \n\t\t\t\t\t\t\t\t\t<ul class="list">
                \n\t\t\t\t\t\t\t\t\t\t<li class="list__left">Nguời đăng: `+ swap.userName + `</li>
                \n\t\t\t\t\t\t\t\t\t\t<li class="list__left">Môn học: `+ swap.courseName + `</li>
                \n\t\t\t\t\t\t\t\t\t\t<li class="list__left">Nhóm môn học: `+ swap.studyGroup + `</li>
                \n\t\t\t\t\t\t\t\t\t\t<li class="list__left">Nhóm thực hành: `+ swap.practiceGroup + `</li>
                \n\t\t\t\t\t\t\t\t\t\t<li class="list__left">Thời gian đăng: `+ swap.createdDate + `</li>
                \n\t\t\t\t\t\t\t\t\t</ul>
                \n\t\t\t\t\t\t\t\t\t<br />
                \n\t\t\t\t\t\t\t\t\t<div class="row">
                \n\t\t\t\t\t\t\t\t\t\t<button href="" class="btn btn-danger float-right get-info" type="button" data-id=`+ swap.id + `>Thông tin</a>
                \n\t\t\t\t\t\t\t\t\t</div>
                \n\t\t\t\t\t\t\t\t</li>`;
                ul_listSwapPreview.innerHTML += htmlString;
            })
        }
    })
}

function showUserPreview(id) {
    $.ajax({
        url: `http://localhost:8080/user/get/${id}`,
        type: 'GET',
        success: function (rs) {
            console.log(rs)
        }
    })
}

function createSwap() {
    courseId = $("#courseid")[0].value;
    userId = $("#userid")[0].value;
    listCourseWishID = [$("#wishid")[0].value];

    var formData = {
        courseId: courseId,
        userId: userId,
        listCourseWishID: listCourseWishID
    }

    $.ajax({
        url: "http://localhost:8080/swap/add",
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(formData)
    }).done(function (ketqua) {
        console.log("done")
    })
}

function showSwapWishPreView(id) {
    $.ajax({
        url: `http://localhost:8080/swapwish/${id}`,
        type: 'GET',
        success: function (swapWishPreviews) {
            ul_swapInfo = document.getElementById("ul_swapInfo")
            ul_swapInfo.innerHTML=''
            swapWishPreviews.map(function (swapWishPreview) {
                var htmlString = `
                \n\t\t\t\t\t\t\t\t<li class="list-group-item">
                \n\t\t\t\t\t\t\t\t\t<div class="row">
                \n\t\t\t\t\t\t\t\t\t\t<div class="col-md-5">
                \n\t\t\t\t\t\t\t\t\t\t\t<ul class="list">
                \n\t\t\t\t\t\t\t\t\t\t\t\t<li>Nhóm học: `+ swapWishPreview.studyGroup + `</li>
                \n\t\t\t\t\t\t\t\t\t\t\t\t<li>Nhóm thực hành: `+ swapWishPreview.practiceGroup + `</li>
                \n\t\t\t\t\t\t\t\t\t\t\t</ul>
                \n\t\t\t\t\t\t\t\t\t\t</div>
                \n\t\t\t\t\t\t\t\t\t\t<div class="col-md-5">Số người tham gia đổi: 80</div>
                \n\t\t\t\t\t\t\t\t\t\t<div class="col-md-2"><a href="" class="btn btn-danger float-right">Thông tin</a></div>
                \n\t\t\t\t\t\t\t\t\t</div>
                \n\t\t\t\t\t\t\t\t</li>`;
                ul_swapInfo.innerHTML += htmlString;
            })
        }
    })
}

function createJoinSwap() {
    var formData = {
        userId: 1,
        swapWishId: 9
    }

    $.ajax({
        url: "http://localhost:8080/swapwish/join",
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(formData)
    }).done(function (ketqua) {
        console.log("done")
    })
}

function deleteSwap() {
    $.ajax({
        url: "http://localhost:8080/swap/delete/6",
        type: "DELETE",
        success: function (rs) {
            console.log(rs)
        }
    })
}

function deleteJoinSwap() {
    $.ajax({
        url: "http://localhost:8080/swapwish/delete/1",
        type: "DELETE",
        success: function (rs) {
            console.log(rs)
        }
    })
}