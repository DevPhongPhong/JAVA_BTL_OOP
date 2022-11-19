$(document).ready(function() {
	
	loadData();
	
    $("#get-user").click(function() {
        showUserPreview(1);
    })

    $("#create-swap").click(function() {
        createSwap();
    })
    
    $("body").on("click", ".swap-btn-view-wish", function() {
		var id = $(this).data('id');
        showSwapWishPreView(id);
	})
});

function loadData() {
    $.ajax({
        url: 'http://localhost:8080/swap',
        type: 'GET',
        success: function(swaps) {
            containerSwap = $("#swap-list")[0]
            var htmls = swaps.map(function(swap) {
                return `
                    <div id="swap-${swap.id}" class="col-3">
                        <!-- <p class="swap-create-date">createdDate: ${swap.createdDate.split(" ")[0]}</p> -->
                        <!-- <p class="swap-username">userName: ${swap.userName}</p> -->
                        <p class="swap-course-code">courseCode: ${swap.courseCode}</p>
                        <p class="swap-course-name">courseName: ${swap.courseName}</p>
                        <p class="swap-study-group">studyGroup: ${swap.studyGroup}</p>
                        <p class="swap-practice-group">practiceGroup: ${swap.practiceGroup}</p>
                        <button data-id="${swap.id}" class="swap-btn-view-wish" type="button">View Wish</button>
                    </div>
                `;
            })
            containerSwap.innerHTML = htmls.join("")
        }
    })
}

function showUserPreview(id) {
    $.ajax({
        url: `http://localhost:8080/user/get/${id}`,
        type: 'GET',
        success: function(rs) {
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
    }).done(function(ketqua) {
        console.log("done")
    })
}

function showSwapWishPreView(id) {
    $.ajax({
        url: `http://localhost:8080/swapwish/${id}`,
        type: 'GET',
        success: function(rs) {
            console.log(rs)
        } 
    })
}