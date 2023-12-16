package online_shop.springapplication.address_manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AddressController {
    @Autowired
    private AddressRepository addressRepository;

    @RequestMapping("/addresses")
    public String getAll(Model model) {
        model.addAttribute("addressList",addressRepository.getAll());
        return "/admin/address_manager/addresses";
    }

    @RequestMapping("/new_address")
    public String showNewForm(Model model) {
        Address address = new Address();
        model.addAttribute("address", address);
        return "admin/address_manager/new_form";
    }

    @RequestMapping(value = "/save_address", method = RequestMethod.POST)
    public String save(@ModelAttribute("address") Address address) {
        addressRepository.save(address);

        return "redirect:/addresses";
    }

    @RequestMapping("/edit_address/{id}")
    public ModelAndView showEditForm(@PathVariable(name="id") int id) {
        ModelAndView mav = new ModelAndView("admin/address_manager/edit_form");
        mav.addObject("address",addressRepository.get(id));
        return mav;
    }

    @RequestMapping(value = "/update_address", method = RequestMethod.POST)
    public String update(@ModelAttribute("address") Address address) {
        addressRepository.update(address);
        return "redirect:/addresses";
    }

    @RequestMapping("/delete_address/{id}")
    public String delete(@PathVariable(name="id") int id) {
        try {
            addressRepository.delete(id);
        }catch (DataIntegrityViolationException e){return "redirect:/integrity_error";}
        return "redirect:/addresses";
    }

    @RequestMapping("/client_address/{id}")
    public ModelAndView getAddressFromClientByID(@PathVariable(name="id") int id) {
        ModelAndView mav = new ModelAndView("admin/address_manager/show_address");
        mav.addObject("address",addressRepository.get(id));
        return mav;
    }

    @RequestMapping("/employee_address/{id}")
    public ModelAndView getAddressFromEmployeeByID(@PathVariable(name="id") int id) {
        ModelAndView mav = new ModelAndView("admin/address_manager/show_address");
        mav.addObject("address",addressRepository.get(id));
        return mav;
    }

    @RequestMapping("/shop_address/{id}")
    public ModelAndView getAddressFromShopInformationByID(@PathVariable(name="id") int id) {
        ModelAndView mav = new ModelAndView("admin/address_manager/show_address");
        mav.addObject("address",addressRepository.get(id));
        return mav;
    }

}
