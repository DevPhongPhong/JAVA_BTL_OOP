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
});
const userId = parseInt($("#userbutton").attr("data-id"));


function loadData(page) {
    $.ajax({
        url: 'http://localhost:8080/swap/manage',
        type: 'GET',
        success: function (swaps) {
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

function deleteSwap() {
    $.ajax({
        url: "http://localhost:8080/swap/delete/6",
        type: "DELETE",
        success: function (rs) {
            console.log(rs)
        }
    })
}