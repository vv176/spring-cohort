package exceptionhandling.classroom.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/view")
public class ViewReturningController {

    @GetMapping
    public String render() {
        return "vivek";
    }

}
