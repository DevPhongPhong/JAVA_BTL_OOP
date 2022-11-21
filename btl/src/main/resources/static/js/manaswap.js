$(document).ready(function () {

    loadData();

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

    $("body").on("click", ".delete-swap", function () {
        var swapId = $(this).parent().parent().data("swap-id");
        deleteSwap(swapId);
    })
});
const userId = parseInt($("#userbutton").attr("data-id"));


function loadData(page) {
    $.ajax({
        url: 'http://localhost:8080/swap/manage',
        type: 'GET',
        success: function (swaps) {
            if (swaps.length > 0) {
                ul_listSwapPreview.innerHTML = ""
                swaps.map(function (swap) {
                    var htmlString = `\n\t\t\t\t\t\t\t\t<li class="list-group-item mb-4" data-swap-id="${swap.id}" id="liSwap${swap.id}">
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
                    \n\t\t\t\t\t\t\t\t\t<div class="row">
                    \n\t\t\t\t\t\t\t\t\t\t<button href="" class="btn btn-outline-danger float-right delete-swap" type="button" data-id=`+ swap.id + `>Xóa yêu cầu</a>
                    \n\t\t\t\t\t\t\t\t\t</div>
                    \n\t\t\t\t\t\t\t\t</li>`;
                    ul_listSwapPreview.innerHTML += htmlString;
                })
            }
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

                        for (var i = 0; i < listjoin.length;i++) {
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
                            \n\t\t\t\t\t\t\t\t\t\t<div class="col-md-2"><button data-id=`+ swapWishPreview.ID + ` class="btn btn-` + `danger float-right disJoin">Người tham gia` + `</button></div>
                            \n\t\t\t\t\t\t\t\t\t</div>
                            \n\t\t\t\t\t\t\t\t</li>`;
                        ul_swapInfo.innerHTML += htmlString;
                    })
                    if (!checkHas) {
                        var list = document.getElementsByClassName("disJoin");
                        if (list.length > 0) {
                            for (var i = 0; i < list.length;) {
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

function deleteSwap(swapId) {
    $.ajax({
        url: `http://localhost:8080/swap/delete/${swapId}`,
        type: "DELETE",
        success: function (res) {
            if (res > 0) {
                swap = document.getElementById(`liSwap${swapId}`);
                swap.remove();
                document.getElementById("modal-body").innerHTML = 'Xóa thành công!'

                $('#exampleModal').modal('show');
                $('#exampleModal').on("click", ".close", () => {
                    $('#exampleModal').modal('hide');
                    window.location.href = '/danh-sach-yeu-cau'
                })
            }
        }
    })
}

function showUserJoined(swapWishId) {
	$.ajax({
        url: `http://localhost:8080/swapwish/joinswap/${swapWishId}`,
        type: 'GET',
        success: function (listUser) {
			let listUserJoined = $(".list-user-join")[0]
			listUser.map(function (user) {
                var htmlString = `\n\t\t\t\t\t\t\t\t<li class="list-group-item mb-4" data-user-id="${user.userId}">
                \n\t\t\t\t\t\t\t\t\t<ul class="list">
                \n\t\t\t\t\t\t\t\t\t\t<li class="list__left">Nguời tham gia: `+ user.userName + `</li>
                \n\t\t\t\t\t\t\t\t\t\t<li class="list__left">Email: `+ user.userEmail + `</li>
                \n\t\t\t\t\t\t\t\t\t\t<li class="list__left">Số điện thoại: `+ user.userPhoneNumber + `</li>
                \n\t\t\t\t\t\t\t\t\t</ul>
                \n\t\t\t\t\t\t\t\t\t<br />
                \n\t\t\t\t\t\t\t\t\t<div class="row">
                \n\t\t\t\t\t\t\t\t\t\t<button href="" class="btn btn-danger float-right select-swap" type="button" data-id=`+ user.id + `>Đổi</a>
                \n\t\t\t\t\t\t\t\t\t</div>
                \n\t\t\t\t\t\t\t\t</li>`;
                listUserJoined.innerHTML += htmlString;
            })
            $(".list-user-join")[0].fadeIn();
        }
    })
}