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
	
	$("body").on("click", ".get-info", function() {
		var hasBubble = $(".bubble")
		if(hasBubble.length > 0) {
			hasBubble.removeClass("bubble")
		}
		$(this).parent().parent().addClass("bubble")
	})

    $("body").on("click", ".paginbtn", function () {
        var page = $(this).data('page');
        loadData(page);
    })

});

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
    courseId = $("#courseid")[0].value;
    listCourseWishID = [$("#wishid")[0].value];

    var formData = {
        courseId: courseId,
        userId: 0,
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
            $("#ul_swapInfo").fadeOut(() => {
                if (swapWishPreviews.length == 0) ul_swapInfo.innerHTML = 'Không có dữ liệu'
                else {
                    ul_swapInfo.innerHTML = ''
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
                    \n\t\t\t\t\t\t\t\t\t\t<div class="col-md-5">Số người tham gia đổi: `+swapWishPreview.listJoinSwapPreview.length+`</div>
                    \n\t\t\t\t\t\t\t\t\t\t<div class="col-md-2"><a href="`+swapWishPreview.ID+`" class="btn btn-danger float-right">Tham gia</a></div>
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