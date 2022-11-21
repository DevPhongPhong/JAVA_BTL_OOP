package com.group5.btl.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group5.btl.dto.swap.JoinSwapPreview;
import com.group5.btl.dto.swap.SwapWishPreview;
import com.group5.btl.dto.user.UserPreview;
import com.group5.btl.model.JoinSwap;
import com.group5.btl.model.Student;
import com.group5.btl.model.SwapWish;
import com.group5.btl.repository.SwapWishRepository;
@Service
public class SwapWishServiceImpl implements SwapWishService {
    @Autowired
    private SwapWishRepository _swr;
    @Autowired
    private JoinSwapService _jss;
    
    

    @Override
    public SwapWish GetSwapWishByID(int id) {
        return _swr.findById(id).get();
    }

    @Override
    public SwapWishPreview GetSwapWishPreview(SwapWish sw) {
        var list = new ArrayList<JoinSwapPreview>();
        for(var item:sw.getListJoinSwaps()){
            list.add(_jss.GetPreview(item));
        }
        return new SwapWishPreview(sw.getId(), sw.getCourseId().getStudyGroup(),sw.getCourseId().getPracticeGroup() , list);
    }

	@Override
	public void deleteSwapWishById(Integer id) {
		_swr.deleteById(id);
	}

	@Override
	public List<UserPreview> getUserPreviewsJoinSwapWish(Integer swapWishId) {
		// TODO Auto-generated method stub
		List<JoinSwap> list = _swr.findById(swapWishId).get().getListJoinSwaps();
		List<UserPreview> res = new ArrayList<>();
		for(JoinSwap xJoinSwap : list) {
			Student student = xJoinSwap.getUserId();
			res.add(new UserPreview(student.getId(), student.getName(), student.getEmail(), student.getPhoneNumber()));
		}
		return res;
	}

}
