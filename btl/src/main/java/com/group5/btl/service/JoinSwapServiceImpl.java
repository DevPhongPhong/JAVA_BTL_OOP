package com.group5.btl.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group5.btl.dto.swap.JoinSwapManage;
import com.group5.btl.dto.swap.JoinSwapPreview;
import com.group5.btl.model.Course;
import com.group5.btl.model.JoinSwap;
import com.group5.btl.model.Student;
import com.group5.btl.model.SwapWish;
import com.group5.btl.repository.JoinSwapRepository;

@Service
public class JoinSwapServiceImpl implements JoinSwapService {
    @Autowired
    private EntityManager _em;

    @Autowired
    private JoinSwapRepository _jsr;

    @Override
    public JoinSwap GetByID(int id) {
        return _jsr.findById(id).get();
    }

    @Override
    public JoinSwapPreview GetPreview(JoinSwap js) {
        return new JoinSwapPreview(js.getId(), js.getUserId().getId(), js.getUserId().getName());
    }

    @Override
    public void CreateJoinSwap(SwapWish sw, Student s) {
        var js = new JoinSwap(0, s, sw);
        _jsr.save(js);
    }

    @Override
    public void DeleteJoinSwap(JoinSwap js) {
        _jsr.delete(js);
    }

    @Override
    public JoinSwap GetByStudentAndSwapWish(int stuId, int swID) {
        TypedQuery<JoinSwap> query = _em.createQuery("SELECT js FROM JoinSwap js " +
                "WHERE js.swapWish = " + swID + " AND js.userId = " + stuId, JoinSwap.class);
        return (JoinSwap) query.getSingleResult();
    }

	@Override
	public List<JoinSwapManage> getJoinSwapByUser(Student student) {
		List<JoinSwap> list = _jsr.findByUserId(student);
		List<JoinSwapManage> res = new ArrayList<>();
		for(JoinSwap xJoinSwap : list) {
			Student st = xJoinSwap.getUserId();
			Course courseWish = xJoinSwap.getSwapWish().getCourseId();
			Course course = xJoinSwap.getSwapWish().getSwapId().getCourseId();
			
			res.add(new JoinSwapManage(xJoinSwap.getId(), st.getName(), course.getCourseName(), 
					course.getPracticeGroup(), course.getStudyGroup(), courseWish.getPracticeGroup(), courseWish.getStudyGroup()));
		}
		return res;
	}

}
