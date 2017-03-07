package baseProject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/")
public class homeController {
    private HttpServletRequest httpServletRequest;
    private HttpServletResponse httpServletResponse;

    @Autowired
    public homeController(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse){
        this.httpServletRequest = httpServletRequest;
        this.httpServletResponse = httpServletResponse;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView getIndex(){
        ModelAndView model = new ModelAndView("pages/index");
        model.addObject("helloMsg","Hello from the home page");
        return model;
    }

    @RequestMapping(value = "/e", method = RequestMethod.GET)
    public ModelAndView getePage(){
        ModelAndView model = new ModelAndView("pages/index");
        model.addObject("helloMsg","Hello from E page !!!");
        return model;
    }

}
