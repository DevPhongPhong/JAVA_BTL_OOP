$(document).ready(function () {
	$("body").on("click", "#add-course", function () {
        addCourse();
    })
    $("body").on("click", "#add-sector", function () {
        addSector();
    })
});

function addCourse() {
    var formData = {
		courseCode: $("#add-course-code")[0].value,
		courseName: $("#add-course-name")[0].value,
		studyGroup: $("#add-study-group")[0].value,
		practiceGroup: $("#add-practice-group")[0].value
    }
	
	$.ajax({
        url: "http://localhost:8080/course/add",
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(formData)
    }).done(function (ketqua) {
        console.log("done")
    })
}

function addSector() {
    var formData = {
		sectorCode: $("#add-sector-code")[0].value,
		sectorName: $("#add-sector-name")[0].value
    }
	
	$.ajax({
        url: "http://localhost:8080/sector/add",
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(formData)
    }).done(function (ketqua) {
        console.log("done")
    })
}
