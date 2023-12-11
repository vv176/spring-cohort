package exceptionhandling.classroom.controllers;

import org.springframework.web.bind.annotation.*;

// ppa.com/courses?courseid=22
// ppa.com/courses/22
// optional fields
// strings too long

@RestController // class-level
@RequestMapping(value = "/greet")
public class SimpleController {
// Spring MVC
    @GetMapping
    //@ResponseBody // signal to include result in HTTP response body
    public String greet() {
        System.out.println("received");
        return "Hello"; //
    }

    @RequestMapping(method = RequestMethod.GET, path = "/say")
    public String greetWithId(@RequestParam("identifier") int id) {
        System.out.println("received by id");
        return "Hello " + id;
    }
    @RequestMapping(method = RequestMethod.GET, path = "/bye/{id}")
    public String greetByeWithId(@PathVariable int id) {
        System.out.println("received for Bye by id");
        return "Bye " + id;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/result", produces = "application/xml")
    public ExamResult getResult() {
        System.out.println("received by result");
        return new ExamResult(90, 98, 97);
    }
    // cleanly binding the HTTP req attributes/params to a Java object
    @RequestMapping(method = RequestMethod.GET, path = "/result/examine")
    public String examineResult(ExamResult examResult) {
        System.out.println("received by examineResult");
        if (examResult.getPhy() > 90 && examResult.getChem() > 90 && examResult.getMaths() > 90)
            return "Good Result :)";
        return "Bad Result :(";
    }

}
// DS .......... Handler
// HandlerExecutionChain (list<intercep>, handler(Object))
