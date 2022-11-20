$(document).ready(function () {

    loadData();

    $("body").on("click", "#search-course", function () {
        searchCourse();
    })

    $("body").on("click", ".get-swap", function () {
        var courseId = $(this).parent().parent().data("course-id");
        showSwapByCourseId(courseId);
    })

    $("body").on("click", ".get-info", function () {
        var id = $(this).data('id');
        showSwapWishPreView(id);
    })

    $("body").on("click", ".get-info", function () {
        var hasBubble = $(".bubble")
        if (hasBubble.length > 0) {
            hasBubble.removeClass("bubble")
        }
        $(this).parent().parent().addClass("bubble")
    })

    $("body").on("click", ".paginbtn", function () {
        var page = $(this).data('page');
        loadData(page);
    })

    $("body").on("click", ".joinSwap", function () {
        if (userId >= 1) {
            var id = $(this).data('id');
            createJoinSwap(id, this);
            document.getElementById("modal-body").innerHTML = 'Tham gia đổi môn thành công!'

            $('#exampleModal').modal('show');
            $('#exampleModal').on("click", ".close", () => {
                $('#exampleModal').modal('hide')
            })
        }
        else {
            window.location.href = "/login"
        }
    })

    $("body").on("click", ".outJoin", function () {
        document.getElementById("modal-body").innerHTML = 'Hủy tham gia thành công!'

        var id = $(this).data("id")
        deleteJoinSwap(id);

        $('#exampleModal').modal('show');
        $('#exampleModal').on("click", ".close", () => {
            $('#exampleModal').modal('hide')
        })
    })

    $("body").on("click", ".disJoin", function () {
        document.getElementById("modal-body").innerHTML = 'Hãy hủy tham gia nhóm môn bạn đã chọn trước khi tham gia nhóm môn khác!'

        $('#exampleModal').modal('show');
        $('#exampleModal').on("click", ".close", () => {
            $('#exampleModal').modal('hide')
        })
    })

});
const userId = parseInt($("#userbutton").attr("data-id"));


function loadData(page) {
    console.log('http://localhost:8080/swap' + (page != undefined ? '/' + page : ''));
    $.ajax({
        url: 'http://localhost:8080/swap' + (page != undefined ? '/' + page : ''),
        type: 'GET',
        success: function (res) {
            swaps = res.listObject
            countPage = res.countPage
            pageNow = res.page
            ul_listSwapPreview = document.getElementById("ul_listSwapPreview")
            swaps.map(function (swap) {
                var htmlString = `\n\t\t\t\t\t\t\t\t<li class="list-group-item mb-4" id="swapId-` + swap.id + `">
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
            $("#ul_listSwapPreview").fadeOut(() => {
                if (swaps == undefined) ul_listSwapPreview.innerHTML = 'Không có dữ liệu'
                ul_listSwapPreview.innerHTML = ''
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
            })
            $("#ul_listSwapPreview").fadeIn();


            paging = document.getElementById("paging")
            paging.innerHTML = ''
            for (let i = 1; i <= countPage; i++) {
                paging.innerHTML += `\n\t\t\t\t\t\t\t\t<div class="col-sm paging">
                \n\t\t\t\t\t\t\t\t\t<button class="btn btn`+ (pageNow == i ? '' : '-outline') + `-danger paginbtn" data-page=` + i + `>` + i + `</button>
                \n\t\t\t\t\t\t\t\t</div>`
            }
        }
    })
}

function searchCourse() {
	courseCode = $("#search-course-code")[0].value;
	studyGroup = $("#search-study-group")[0].value === "" ? 0 : $("#search-study-group")[0].value;
	practiceGroup = $("#search-practice-group")[0].value === "" ? 0 : $("#search-practice-group")[0].value;
	$.ajax({
        url: `http://localhost:8080/swap/${courseCode}/${studyGroup}/${practiceGroup}`,
        type: 'GET',
        success: function (swaps) {
            ul_listSwapPreview = document.getElementById("ul_listSwapPreview")
            swaps.map(function (swap) {
                var htmlString = `\n\t\t\t\t\t\t\t\t<li class="list-group-item mb-4" id="swapId-` + swap.id + `">
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
            $("#ul_listSwapPreview").fadeOut(() => {
                if (swaps == undefined) ul_listSwapPreview.innerHTML = 'Không có dữ liệu'
                ul_listSwapPreview.innerHTML = ''
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
            })
            $("#ul_listSwapPreview").fadeIn();
        }
    })
}

function showSwapByCourseId(courseId) {
    $.ajax({
        url: `http://localhost:8080/swap/get/${courseId}`,
        type: 'GET',
        success: function (swapList) {
            ul_swapInfo = document.getElementById("ul_swapInfo")
            $("#ul_swapInfo").fadeOut(() => {
                if (swapList.length == 0) ul_swapInfo.innerHTML = 'Không có dữ liệu'
                else {
                    ul_swapInfo.innerHTML = ''
                    swapList.map(function (swap) {
                        var htmlString = `
                    \n\t\t\t\t\t\t\t\t<li class="list-group-item">
                    \n\t\t\t\t\t\t\t\t\t<div class="row">
                    \n\t\t\t\t\t\t\t\t\t\t<div class="col-md-8">
                    \n\t\t\t\t\t\t\t\t\t\t\t<ul class="list">
                    \n\t\t\t\t\t\t\t\t\t\t\t\t<li>Người đăng: `+ swap.userName + `</li>
                    \n\t\t\t\t\t\t\t\t\t\t\t\t<li>Ngày tạo: `+ swap.createdDate + `</li>
                    \n\t\t\t\t\t\t\t\t\t\t\t</ul>
                    \n\t\t\t\t\t\t\t\t\t\t</div>
                    \n\t\t\t\t\t\t\t\t\t</div>
                    \n\t\t\t\t\t\t\t\t</li>`;
                        ul_swapInfo.innerHTML += htmlString;
                    })
                }
            });

            $("#ul_swapInfo").fadeIn();
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
            $("#ul_swapInfo").fadeOut(() => {
                if (swapWishPreviews.length == 0) ul_swapInfo.innerHTML = 'Không có dữ liệu'
                else {
                    ul_swapInfo.innerHTML = ''
                    var checkHas = false
                    swapWishPreviews.map(function (swapWishPreview) {
                        var checkJoined = false
                        listjoin = swapWishPreview.listJoinSwapPreview

                        for (var i = 0; i < listjoin.length;) {
                            if (listjoin[i].UserID == userId) {
                                checkJoined = true;
                                checkHas = true;
                                break;
                            }
                        }

                        var htmlString = `
                            \n\t\t\t\t\t\t\t\t<li class="list-group-item">
                            \n\t\t\t\t\t\t\t\t\t<div class="row">
                            \n\t\t\t\t\t\t\t\t\t\t<div class="col-md-5">
                            \n\t\t\t\t\t\t\t\t\t\t\t<ul class="list">
                            \n\t\t\t\t\t\t\t\t\t\t\t\t<li>Nhóm học: `+ swapWishPreview.studyGroup + `</li>
                            \n\t\t\t\t\t\t\t\t\t\t\t\t<li>Nhóm thực hành: `+ swapWishPreview.practiceGroup + `</li>
                            \n\t\t\t\t\t\t\t\t\t\t\t</ul>
                            \n\t\t\t\t\t\t\t\t\t\t</div>
                            \n\t\t\t\t\t\t\t\t\t\t<div class="col-md-5">Số người tham gia đổi: 
                            \n\t\t\t\t\t\t\t\t\t\t\t<span id="countJoin-`+ swapWishPreview.ID + `">` + swapWishPreview.listJoinSwapPreview.length + `</span></div>
                            \n\t\t\t\t\t\t\t\t\t\t<div class="col-md-2"><button data-id=`+ swapWishPreview.ID + ` class="btn btn-` + (checkJoined ? `outline-danger float-right outJoin">Hủy` : `danger float-right disJoin">Tham gia`) + `</button></div>
                            \n\t\t\t\t\t\t\t\t\t</div>
                            \n\t\t\t\t\t\t\t\t</li>`;
                        ul_swapInfo.innerHTML += htmlString;
                    })
                    if (!checkHas) {
                        var list = document.getElementsByClassName("disJoin");
                        if (list.length > 0) {
                            for (var i = 0; i < list.length; ) {
                                list[i].classList.add("joinSwap");
                                list[i].classList.remove("disJoin");
                            }
                        }
                    }
                }
            });

            $("#ul_swapInfo").fadeIn();
        }
    })
}

function createJoinSwap(swapWishId, element) {
    var formData = {
        userId: 1,
        swapWishId: swapWishId
    }

    $.ajax({
        url: "http://localhost:8080/swapwish/join",
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(formData)
    }).done(function (res) {
        var count = document.getElementById("countJoin-" + res.swapWishId);
        count.innerHTML = parseInt(count.innerHTML) + 1;

        element.classList.add("outJoin");
        element.classList.add("btn-outline-danger");
        element.classList.remove("joinSwap");
        element.classList.remove("btn-danger");
        element.innerHTML = "Hủy"

        var listJoinSwap = document.getElementsByClassName("joinSwap");
        for (var i = 0; i < listJoinSwap.length;) {
            listJoinSwap[i].classList.add("disJoin");
            listJoinSwap[i].classList.remove("joinSwap");
        }
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

function deleteJoinSwap(swapWishId) {
    $.ajax({
        url: "http://localhost:8080/swapwish/delete/" + swapWishId,
        type: "DELETE",
        success: function (rs) {
            var thisBtn = document.getElementsByClassName("outJoin")[0];
            var listBlockedBtn = document.getElementsByClassName("disJoin");

            var count = document.getElementById("countJoin-" + swapWishId);
            count.innerHTML = parseInt(count.innerHTML) - 1;

            thisBtn.classList.add("joinSwap");
            thisBtn.classList.add("btn-danger");
            thisBtn.classList.remove("outJoin");
            thisBtn.classList.remove("btn-outline-danger");

            thisBtn.innerHTML = "Tham gia"

            for (var i = 0; i < listBlockedBtn.length;) {
                listBlockedBtn[i].classList.add("joinSwap");
                listBlockedBtn[i].classList.remove("disJoin");
            }

        }
    })
}