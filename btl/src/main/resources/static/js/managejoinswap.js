$(document).ready(function () {

    loadData();

    $("body").on("click", ".get-info", function () {
        var hasBubble = $(".bubble")
        if (hasBubble.length > 0) {
            hasBubble.removeClass("bubble")
        }
        $(this).parent().parent().addClass("bubble")
    })
    
    $("body").on("click", ".delete-joinswap", function () {
		var swapId = $(this).data("swap-id");
		deleteJoinSwap(swapId);
    })
});
const userId = parseInt($("#userbutton").attr("data-id"));


function loadData(page) {
    $.ajax({
        url: 'http://localhost:8080/joinswap/manage',
        type: 'GET',
        success: function (swaps) {
			ul_listSwapPreview = document.getElementById("ul_listSwapPreview")
            swaps.map(function (swap) {
                var htmlString = `\n\t\t\t\t\t\t\t\t<li class="list-group-item mb-4" id="swapId-` + swap.id + `">
                \n\t\t\t\t\t\t\t\t\t<ul class="list">
                \n\t\t\t\t\t\t\t\t\t\t<li class="list__left">Nguời tham gia: `+ swap.userName + `</li>
                \n\t\t\t\t\t\t\t\t\t\t<li class="list__left">Môn học: `+ swap.courseName + `</li>
                \n\t\t\t\t\t\t\t\t\t\t<li class="list__left">Nhóm môn học: `+ swap.studyGroup + `</li>
                \n\t\t\t\t\t\t\t\t\t\t<li class="list__left">Nhóm thực hành: `+ swap.practiceGroup + `</li>
                \n\t\t\t\t\t\t\t\t\t\t<li class="list__left">Đổi sang nhóm môn học: `+ swap.studyGroupWish + `</li>
                \n\t\t\t\t\t\t\t\t\t\t<li class="list__left">Đổi sang nhóm thực hành: `+ swap.practiceGroupWish + `</li>
                \n\t\t\t\t\t\t\t\t\t</ul>
                \n\t\t\t\t\t\t\t\t\t<br />
                \n\t\t\t\t\t\t\t\t\t<div class="row">
                \n\t\t\t\t\t\t\t\t\t\t<button href="" class="btn btn-danger float-right delete-joinswap" type="button" data-swap-id=`+ swap.id + `>Xóa</a>
                \n\t\t\t\t\t\t\t\t\t</div>
                \n\t\t\t\t\t\t\t\t</li>`;
                ul_listSwapPreview.innerHTML += htmlString;
            })
        }
    })
}

function deleteJoinSwap(joinSwapId) {
	$.ajax({
        url: `http://localhost:8080/joinswap/delete/${joinSwapId}`,
        type: "DELETE",
        success: function () {
			console.log("done")
			location.reload();
        }
    })
}
