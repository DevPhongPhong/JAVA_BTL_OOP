package com.group5.btl.controller.swap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.group5.btl.dto.PagingDto;
import com.group5.btl.dto.course.CourseInfo;
import com.group5.btl.dto.swap.SwapCreate;
import com.group5.btl.dto.swap.SwapCreateFromView;
import com.group5.btl.dto.swap.SwapPreview;
import com.group5.btl.dto.swap.SwapWishPreview;
import com.group5.btl.model.Course;
import com.group5.btl.model.Student;
import com.group5.btl.model.Swap;
import com.group5.btl.repository.SwapRepository;
import com.group5.btl.service.CourseService;
import com.group5.btl.service.StudentService;
import com.group5.btl.dto.user.UserPreview;
import com.group5.btl.service.CourseService;
import com.group5.btl.service.SwapService;
import com.group5.btl.service.UserSevice;

import java.util.*;

@RestController
@RequestMapping("/swap")
public class SwapController {
	@Autowired
	private SwapService swapService;

	@Autowired
	private CourseService courseService;

	@Autowired
	private CourseService _cs;

	@Autowired
	private UserSevice _us;

	@Autowired
	private StudentService studentService;

	@CrossOrigin(origins = "http://127.0.0.1:5500/")
	@GetMapping("/{courseCode}/{studyGroup}/{practiceGroup}/{page}")
	public PagingDto<SwapPreview> getSwapByCourse(@PathVariable(name = "courseCode") String courseCode,
			@PathVariable(name = "studyGroup") Short studyGroup,
			@PathVariable(name = "practiceGroup") Short practiceGroup,
			@PathVariable(name = "page") int page) {

		List<SwapPreview> listSwapPreviews = new ArrayList<>();
		List<Course> list = courseService.getByCodeAndPracticeAndStudy(courseCode, practiceGroup, studyGroup);
		for (Course xCourse : list) {
			listSwapPreviews.addAll(swapService.getByCourseId(xCourse.getId()));
		}

		var listSp = new ArrayList<SwapPreview>();

		for (int i = 3 * (page - 1); i < 3 * page && i < listSwapPreviews.size(); i++)
			listSp.add(listSwapPreviews.get(i));

		var res = new PagingDto<SwapPreview>();
		res.page = page;
		res.countPage = listSwapPreviews.size() / 3;
		res.listObject = listSp;
		
		return res;
	}

	@CrossOrigin(origins = "http://127.0.0.1:5500/")
	@GetMapping("/get/{id}")
	public List<SwapPreview> getListSwapByCourseID(@PathVariable(name = "id") Integer courseId) {
		// List<SwapPreview> list = swapService.getByCourseId(courseId);
		// return new PagingDto<SwapPreview>(1, list.size() / 3 + 1, list.subList(0,3));
		return swapService.getByCourseId(courseId);
	}

	@CrossOrigin(origins = "http://127.0.0.1:5500/")
	@GetMapping
	public PagingDto<SwapPreview> getListSwap() {
		var list = swapService.getAll();
		var res = swapService.getPreviews(list, 1, 3);
		return new PagingDto<SwapPreview>(1, list.size() / 3 + 1, res);
	}

	@CrossOrigin(origins = "http://127.0.0.1:5500/")
	@GetMapping("/{page}")
	public PagingDto<SwapPreview> getListSwap(@PathVariable(name = "page") int page) {
		var list = swapService.getAll();
		var res = swapService.getPreviews(list, page, 3);
		return new PagingDto<SwapPreview>(page, list.size() / 3 + 1, res);
	}

	@CrossOrigin(origins = "http://127.0.0.1:5500/")
	@PostMapping("/add")
	public ResponseEntity addSwap(@RequestBody SwapCreateFromView swapCreateFromView) {
		var swapCreate = new SwapCreate(0, 0, new ArrayList<Integer>());
		var list = new ArrayList<Integer>();
		int temp1 = 0;
		int temp2 = 0;
		try {
			temp1 = swapCreateFromView.getGroupSwap().getPracticeGroup();
			temp2 = swapCreateFromView.getGroupSwap().getStudyGroup();

			swapCreate.setCourseId(_cs.getByCodeAndPracticeAndStudy(swapCreateFromView.getCourseCode(),
					swapCreateFromView.getGroupSwap().getPracticeGroup(),
					swapCreateFromView.getGroupSwap().getStudyGroup())
					.get(0).getId());
		} catch (Exception e) {
			return ResponseEntity.badRequest()
					.body("Không tim thầy môn có mã: " + swapCreateFromView.getCourseCode() + ", nhóm học: "
							+ temp2 + ", nhóm thực hành: "
							+ temp1);
		}
		try {
			for (var item : swapCreateFromView.getSwapWish()) {
				temp1 = item.getPracticeGroup();
				temp2 = item.getStudyGroup();

				list.add(_cs.getByCodeAndPracticeAndStudy(swapCreateFromView.getCourseCode(),
						item.getPracticeGroup(),
						item.getStudyGroup())
						.get(0).getId());
			}
		} catch (Exception e) {
			return ResponseEntity.badRequest()
					.body("Không tim thầy môn có mã: " + swapCreateFromView.getCourseCode() + ", nhóm học: "
							+ temp2 + ", nhóm thực hành: "
							+ temp1);
		}

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication instanceof AnonymousAuthenticationToken)
			return null;

		String userName = authentication.getName();
		UserPreview userPreview = _us.getUserPreviewByEmail(userName);

		swapCreate.setUserId(userPreview.getId());
		swapCreate.setListCourseWishID(list);
		swapService.create(swapCreate);
		return ResponseEntity.ok().body(swapCreate);
	}

	@CrossOrigin(origins = "http://127.0.0.1:5500/")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity deleteSwap(@PathVariable(name = "id") Integer swapId) {
		int res = swapService.delete(swapId);
		return ResponseEntity.ok().build();
	}

	@CrossOrigin(origins = "http://127.0.0.1:5500/")
	@GetMapping("/manage")
	public List<SwapPreview> getListSwapByUserID() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		Student student = studentService.findByEmail(userName);
		return swapService.getByUserId(student);
	}
}
