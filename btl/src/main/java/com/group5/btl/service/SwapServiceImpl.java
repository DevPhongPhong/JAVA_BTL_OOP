package com.group5.btl.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group5.btl.dto.swap.SwapCreate;
import com.group5.btl.dto.swap.SwapInfo;
import com.group5.btl.dto.swap.SwapPreview;
import com.group5.btl.dto.swap.SwapUpdate;
import com.group5.btl.model.Course;
import com.group5.btl.model.JoinSwap;
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

    // #region Private Method
    private void sortByCreatedDate(String type, List<Swap> listSwap) throws Exception {
        if (type == "ASC" || type == "DESC") {
            Collections.sort(listSwap, (s1, s2) -> {
                if (type == "ASC")
                    // compare two instance of `Score` and return `int` as result.
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
        return _swapRepository.findAll();
    }

    @Override
    public SwapPreview getPreview(Swap swap) {
        var sp = new SwapPreview(swap.getId(), swap.getUserId(), swap.getCreatedDate().toString(), swap.getCourseId());
        return sp;
    }

    @Override
    public SwapInfo getInfo(Swap swap) {
        var si = new SwapInfo(swap.getId(), swap.getListSwapWishs(), swap.getListJoinSwaps());
        return si;
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
            for (int i : swapCreate.getListCourseID()) {
                var courseWish = _courseRepository.findById(i).get();
                SwapWish sw = new SwapWish(0, swapRes, courseWish);
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
        // var oldSwap = _swapRepository.findById(swapUpdate.getSwapId()).get();
        // var listOldSwapWish = oldSwap.getListSwapWishs();
        // var listNewCourseId = swapUpdate.getListCourseID();

        // for (SwapWish sw : listOldSwapWish) {
        //     if (!listNewCourseId.contains(sw.getCourseId().getId())) {
        //         listNewCourseId.remove(sw.getCourseId().getId());
        //         listOldSwapWish.remove(sw);
                
        //     }
        // }

        // for (int i : listNewCourseId) {
        //     listOldSwapWish.add(new SwapWish(0, oldSwap, _courseRepository.findById(i).get()));
        // }
        // oldSwap.setListSwapWishs(listOldSwapWish);
        // _swapRepository.saveAndFlush(oldSwap);
    }

    @Override
    public int delete(int id) {
        var swap = _swapRepository.findById(id).get();
        var listSwapWishs = swap.getListSwapWishs();
        var listJoinSwaps = swap.getListJoinSwaps();
        var res = 0;

        for (JoinSwap js : listJoinSwaps) {
            _joinSwapRepository.delete(js);
            res++;
        }
        for (SwapWish sw : listSwapWishs) {
            _swapWishRepository.delete(sw);
            res++;
        }
        _swapRepository.delete(swap);
        res++;
        return res;
    }
    // #endregion

}
