package online_shop.springapplication.employee_manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @RequestMapping("/employees")
    public String getAll(Model model) {
        model.addAttribute("employeeList",employeeRepository.getAll());
        return "/admin/employee_manager/employees";
    }

    @RequestMapping("/new_employee")
    public String showNewForm(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "admin/employee_manager/new_form";
    }


    @RequestMapping(value = "/save_employee", method = RequestMethod.POST)
    public String save(@ModelAttribute("employee") Employee employee) {
        employeeRepository.save(employee);

        return "redirect:/employees";
    }

    @RequestMapping("/edit_employee/{id}")
    public ModelAndView showEditForm(@PathVariable(name="id") int id) {
        ModelAndView mav = new ModelAndView("admin/employee_manager/edit_form");
        mav.addObject("employee",employeeRepository.get(id));
        return mav;
    }

    @RequestMapping(value = "/update_employee", method = RequestMethod.POST)
    public String update(@ModelAttribute("employee") Employee employee) {
        employeeRepository.update(employee);
        return "redirect:/employees";
    }

    @RequestMapping("/delete_employee/{id}")
    public String delete(@PathVariable(name="id") int id) {
        try {
            employeeRepository.delete(id);
        } catch(DataIntegrityViolationException e){return "redirect:/integrity_error";}
        return "redirect:/employees";
    }

}
