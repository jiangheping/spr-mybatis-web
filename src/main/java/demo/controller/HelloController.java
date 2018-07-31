package demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import demo.entity.Person;
import demo.service.IPersonService;

@Controller
public class HelloController {
     
	@Autowired  
	public IPersonService personService;
    
    @RequestMapping(value="/hello",method=RequestMethod.GET)
    public String sayHello(Model model) {
        model.addAttribute("msg", "Hello World java!");
        Person person = personService.queryById(3);
        System.out.println("这个中文会乱码吗");
        System.out.println(person.getDescription()+"---"+person.getId());
        return "hello";
    }

}