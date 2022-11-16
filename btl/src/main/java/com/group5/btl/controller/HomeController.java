package com.group5.btl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.group5.btl.repository.UserRepository;
import com.group5.btl.service.StudentService;
import com.group5.btl.service.sector.SectorService;
import com.group5.btl.service.SwapService;

@Controller
@RequestMapping("home")
public class HomeController {
	@Autowired
    private SwapService _ss;

    @GetMapping()
    public String Index() {
        var listSwap = _ss.getAll();
        //page start=1
        var listSwapPreview = _ss.getPreviews(listSwap,1,100);
        for(var item:listSwapPreview){
            var sw = _ss.getById(item.id);
            var swif = _ss.getInfo(sw);
            var listSwapWishPreview = swif.listSwapWishPreview;
            for(var item2:listSwapWishPreview){
                var listJoinSwapPreview = item2.listJoinSwapPreview;
                System.out.println("");
            }
        }
        return "home/index";
    }
}
