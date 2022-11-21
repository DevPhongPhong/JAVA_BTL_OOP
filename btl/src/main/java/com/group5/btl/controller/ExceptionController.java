package com.group5.btl.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ExceptionController implements ErrorController {

    @RequestMapping("/error")
    public ModelAndView handleError(HttpServletResponse response) {
        int status = response.getStatus();
        if ( status == HttpStatus.NOT_FOUND.value()) {
            return new ModelAndView("error/error-404");
        } else if (status == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            return new ModelAndView("error/error-500");
        }
        return new ModelAndView("error/error-404");
    }
}
