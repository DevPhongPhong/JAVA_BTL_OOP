package com.group5.btl.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group5.btl.dto.swap.SwapCreate;
import com.group5.btl.dto.swap.SwapInfo;
import com.group5.btl.dto.swap.SwapPreview;
import com.group5.btl.dto.swap.SwapUpdate;
import com.group5.btl.dto.swap.SwapWishPreview;
import com.group5.btl.model.Course;
import com.group5.btl.model.Student;
import com.group5.btl.model.Swap;
import com.group5.btl.model.SwapWish;
import com.group5.btl.repository.CourseRepository;
import com.group5.btl.repository.JoinSwapRepository;
import com.group5.btl.repository.StudentRepository;
import com.group5.btl.repository.SwapRepository;
import com.group5.btl.repository.SwapWishRepository;

@Service
public class SwapServiceImpl implements SwapService {
    @Autowired
    private SwapRepository _swapRepository;
    @Autowired
    private SwapWishRepository _swapWishRepository;
    @Autowired
    private StudentRepository _studentRepository;
    @Autowired
    private CourseRepository _courseRepository;
    @Autowired
    private JoinSwapRepository _joinSwapRepository;
    @Autowired
    private SwapWishService _sws;

    @Autowired
    private CourseService courseService;

    // #region Private Method
    private void sortByCreatedDate(String type, List<Swap> listSwap) throws Exception {
        if (type == "ASC" || type == "DESC") {
            Collections.sort(listSwap, (s1, s2) -> {
                if (type == "ASC")
                    return s1.getCreatedDate().compareTo(s2.getCreatedDate());
                else {
                    return s2.getCreatedDate().compareTo(s1.getCreatedDate());
                }
            });
        } else {
            throw new Exception("Wrong type of sort");
        }
    }

    private List<Swap> paging(List<Swap> listSwap, int page, int size) {
        List<Swap> res = new ArrayList<>();
        try {
            for (int i = (page - 1) * size; i < page * size; i++) {
                res.add(listSwap.get(i));
            }
        }
        // Xử lý lỗi khi i vượt quá size list thì sẽ return
        catch (Exception e) {

        }
        return res;
    }
    // #endregion

    // #region Override

    @Override
    public Swap getById(int id) {
        return _swapRepository.findById(id).get();
    }

    @Override
    public List<Swap> getAll() {
        var res = _swapRepository.findAll();
        for (var item : res) {
            Timestamp now = new Timestamp(System.currentTimeMillis());

            Timestamp old = item.getCreatedDate();
            ZonedDateTime zonedDateTime = old.toInstant().atZone(ZoneId.of("UTC"));
            Timestamp newTime = Timestamp.from(zonedDateTime.plus(14, ChronoUnit.DAYS).toInstant());

            if (newTime.before(now)) {
                delete(item.getId());
            }
        }
        return _swapRepository.findAll();
    }

    @Override
    public SwapPreview getPreview(Swap swap) {
        var sp = new SwapPreview(swap.getId(),
                swap.getUserId().getName(),
                swap.getCreatedDate().toString(),
                swap.getCourseId().getCourseCode(),
                swap.getCourseId().getCourseName(),
                swap.getCourseId().getStudyGroup(),
                swap.getCourseId().getPracticeGroup());
        return sp;
    }

    @Override
    public SwapInfo getInfo(Swap swap) {
        var list = new ArrayList<SwapWishPreview>();
        for (var item : swap.getListSwapWishs()) {
            list.add(_sws.GetSwapWishPreview(item));
        }
        return new SwapInfo(swap.getId(), list);
    }

    @Override
    public List<SwapPreview> getPreviews(List<Swap> listSwap, int page, int size) {
        try {
            sortByCreatedDate("DESC", listSwap);
        } catch (Exception e) {

        }

        var paging = paging(listSwap, page, size);

        List<SwapPreview> sps = new ArrayList<>();
        for (Swap s : paging) {
            sps.add(getPreview(s));
        }
        return sps;
    }

    @Override
    public int create(SwapCreate swapCreate) throws IllegalArgumentException {
        int res = 0;
        Student user = _studentRepository.findById(swapCreate.getUserId()).get();
        Course course = _courseRepository.findById(swapCreate.getCourseId()).get();
        Swap swap = new Swap();
        swap.setId(0);
        swap.setUserId(user);
        swap.setCourseId(course);
        swap.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        try {
            var swapRes = _swapRepository.save(swap);
            res++;
            for (int i : swapCreate.getListCourseWishID()) {
                var courseWish = _courseRepository.findById(i).get();
                SwapWish sw = new SwapWish(null, swapRes, courseWish, null);
                _swapWishRepository.save(sw);
                res++;
            }
        } catch (Exception e) {
            throw e;
        }
        return res;
    }

    @Override
    public void update(SwapUpdate swapUpdate) {
        var oldSwap = _swapRepository.findById(swapUpdate.getSwapId()).get();
        var listNewCourseID = swapUpdate.getListCourseID();

        for (SwapWish sw : oldSwap.getListSwapWishs()) {
            if (!listNewCourseID.contains(sw.getCourseId().getId())) {
                if (sw.getListJoinSwaps() != null)
                    for (var item : sw.getListJoinSwaps()) {
                        _joinSwapRepository.delete(item);
                    }
                _swapWishRepository.delete(sw);
            } else
                listNewCourseID.remove(sw.getId());
        }

        for (int i : listNewCourseID) {
            var sw = new SwapWish(null, oldSwap, _courseRepository.findById(i).get(), null);
            _swapWishRepository.save(sw);
        }
    }

    @Override
    public int delete(int id) {
        var swap = _swapRepository.findById(id).get();
        var listSwapWishs = swap.getListSwapWishs();
        var res = 0;
        for (SwapWish sw : listSwapWishs) {
            if (sw.getListJoinSwaps() != null && sw.getListJoinSwaps().size() > 0) {
                for (var item : sw.getListJoinSwaps()) {
                    _joinSwapRepository.deleteById(item.getId());
                    res++;
                }
            }
            _swapWishRepository.delete(sw);
            res++;
        }
        _swapRepository.delete(swap);
        res++;
        return res;
    }
    // #endregion

    @Override
    public List<SwapPreview> getByCourseId(Integer courseId) {
        Course course = courseService.getById(courseId);
        List<Swap> listSwaps = _swapRepository.findByCourseId(course);
        List<SwapPreview> resList = new ArrayList<>();
        for (Swap xSwap : listSwaps) {
            resList.add(new SwapPreview(xSwap.getId(), xSwap.getUserId().getName(),
                    xSwap.getCreatedDate().toString(), xSwap.getCourseId().getCourseCode(),
                    xSwap.getCourseId().getCourseName(),
                    xSwap.getCourseId().getStudyGroup(), xSwap.getCourseId().getPracticeGroup()));
        }
        return resList;
    }

    @Override
    public List<SwapPreview> getByUserId(Student student) {
        List<Swap> listSwaps = _swapRepository.findByUserId(student);
        List<SwapPreview> resList = new ArrayList<>();
        for (Swap xSwap : listSwaps) {
            resList.add(new SwapPreview(xSwap.getId(), xSwap.getUserId().getName(),
                    xSwap.getCreatedDate().toString(), xSwap.getCourseId().getCourseCode(),
                    xSwap.getCourseId().getCourseName(),
                    xSwap.getCourseId().getStudyGroup(), xSwap.getCourseId().getPracticeGroup()));
        }
        return resList;
    }

}
