package online_shop.springapplication.client_information;

import online_shop.springapplication.address_manager.Address;
import online_shop.springapplication.address_manager.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;

    @RequestMapping("/clients")
    public String getAll(Model model) {
        model.addAttribute("clientList",clientRepository.getAll());
        return "/admin/client_manager/clients";
    }

    @RequestMapping("/edit_client/{id}")
    public ModelAndView showEditForm(@PathVariable(name="id") int id) {
        ModelAndView mav = new ModelAndView("user/user_manager/edit_form");
        mav.addObject("user",clientRepository.get(id));
        return mav;
    }

    @RequestMapping(value = "/update_user", method = RequestMethod.POST)
    public String update(@ModelAttribute("user") Client client) {
        clientRepository.update(client);
        return "redirect:/user_information";
    }

    @RequestMapping("/delete_client/{id}")
    public String delete(@PathVariable(name="id") int id) {
        clientRepository.delete(id);

        return "redirect:/user_information";
    }

    @RequestMapping("/user_information")
    public String getUserInformation(Model model) {
        model.addAttribute("userList", clientRepository.getAll());
        return "/user/user_manager/user";
    }

}
