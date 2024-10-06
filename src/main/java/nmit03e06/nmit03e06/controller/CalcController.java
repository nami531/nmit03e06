package nmit03e06.nmit03e06.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import nmit03e06.nmit03e06.service.MathService;





@Controller
public class CalcController {
    
    @Autowired
    private MathService s; 

    private Status status = Status.OP1; 

    @GetMapping({"/","/home", "/index", " "})
    public String showIndex() {
        return "index";
    }
    
    @GetMapping("/digit/{number}")
    public String addNumber(Model model, @PathVariable String number) { 
        if (status == Status.OP1){
            s.setOperand1(s.getOperand1() + number); 
            System.out.println(s.getOperand1()); 
            
        } else {
            s.setOperand2(s.getOperand2() + number); 
            
        }
        model.addAttribute("operand1", s.getOperand1());
        model.addAttribute("operand2", s.getOperand2());
        return "index";
    }

    @GetMapping("/addition")
    public String additionPressed(Model model) {
        status =Status.OP2; 
        model.addAttribute("operand1", s.getOperand1());
        model.addAttribute("operand2", s.getOperand2());
        return "index";
    }
    
    @GetMapping("/clear")
    public String clearIndex(){
        s.setOperand1(""); 
        s.setOperand2(""); 
        status =Status.OP2;  
        return "redirect:/index"; 
    }

    @GetMapping("/equals")
    public String showResult(Model model){
        int result = s.add(); 
        status = Status.EQUALS; 
        model.addAttribute("operand1", s.getOperand1());
        model.addAttribute("operand2", s.getOperand2());
        model.addAttribute("result", result); 
        return "index"; 
    }

}
