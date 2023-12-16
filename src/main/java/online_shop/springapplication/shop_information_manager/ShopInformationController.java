package online_shop.springapplication.shop_information_manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ShopInformationController {
    @Autowired
    private ShopInformationRepository shopInformationRepository;

    @RequestMapping("/shop_information")
    public String getAll(Model model) {
        model.addAttribute("shopInformationList",shopInformationRepository.getAll());
        return "/admin/shop_information_manager/shop_information";
    }

    @RequestMapping("/new_shop_information")
    public String showNewForm(Model model) {
        ShopInformation shopInformation = new ShopInformation();
        model.addAttribute("shopInformation", shopInformation);
        return "admin/shop_information_manager/new_form";
    }

    @RequestMapping(value = "/save_shop_information", method = RequestMethod.POST)
    public String save(@ModelAttribute("shopInformation") ShopInformation shopInformation) {
        shopInformationRepository.save(shopInformation);

        return "redirect:/shop_information";
    }

    @RequestMapping("/edit_shop_information/{id}")
    public ModelAndView showEditForm(@PathVariable(name="id") int id) {
        ModelAndView mav = new ModelAndView("admin/shop_information_manager/edit_form");
        mav.addObject("shopInformation",shopInformationRepository.get(id));
        return mav;
    }

    @RequestMapping(value = "/update_shop_information", method = RequestMethod.POST)
    public String update(@ModelAttribute("shopInformation") ShopInformation shopInformation) {
        shopInformationRepository.update(shopInformation);
        return "redirect:/shop_information";
    }

    @RequestMapping("/delete_shop_information/{id}")
    public String delete(@PathVariable(name="id") int id) {
        shopInformationRepository.delete(id);

        return "redirect:/shop_information";
    }

    @RequestMapping("/show_shop_information/{id}")
    public ModelAndView getShopInformationFromEmployeeByID(@PathVariable(name="id") int id) {
        ModelAndView mav = new ModelAndView("admin/shop_information_manager/show_shop_information");
        mav.addObject("shopInformation",shopInformationRepository.get(id));
        return mav;
    }
}