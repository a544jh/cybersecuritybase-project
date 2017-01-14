package sec.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.domain.Account;
import sec.project.domain.TodoItem;
import sec.project.repository.AccountRepository;
import sec.project.repository.TodoRepository;

@Controller
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private AccountRepository accountRepository;

    @RequestMapping("*")
    public String defaultMapping() {
        return "redirect:/todos";
    }

    @RequestMapping(value = "/todos", method = RequestMethod.GET)
    public String loadForm(Authentication auth, Model model) {
        model.addAttribute("todos", accountRepository.findByUsername(auth.getName()).getTodos());
        return "todos";
    }

    @RequestMapping(value = "/todos", method = RequestMethod.POST)
    public String submitForm(Authentication auth, @RequestParam String todo) {
        Account acc = accountRepository.findByUsername(auth.getName());
        todoRepository.save(new TodoItem(acc, todo));
        return "redirect:/todos";
    }

}
