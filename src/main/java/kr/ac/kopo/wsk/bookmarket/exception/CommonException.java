package kr.ac.kopo.wsk.bookmarket.exception;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class CommonException {
    @ExceptionHandler (value= RuntimeException.class)
    private ModelAndView HandleException (Exception e) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e.toString());
        mav.setViewName("errorCommon");
        return mav;
    }
}
