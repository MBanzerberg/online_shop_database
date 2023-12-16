package online_shop.springapplication.product_manager;

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
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @RequestMapping("/products")
    public String getAllForAdmin(Model model) {
        model.addAttribute("productList",productRepository.getAll());
        return "/admin/product_manager/products";
    }

    @RequestMapping("/main_user")
    public String getAllForUser(Model model) {
        model.addAttribute("productList2",productRepository.getAll());
        return "/user/main_user";
    }

    @RequestMapping("/new_product")
    public String showNewForm(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "admin/product_manager/new_form";
    }

    @RequestMapping(value = "/save_product", method = RequestMethod.POST)
    public String save(@ModelAttribute("product") Product product) {
        productRepository.save(product);

        return "redirect:/products";
    }

    @RequestMapping("/edit_product/{id}")
    public ModelAndView showEditForm(@PathVariable(name="id") int id) {
        ModelAndView mav = new ModelAndView("admin/product_manager/edit_form");
        mav.addObject("product",productRepository.get(id));
        return mav;
    }

    @RequestMapping(value = "/update_product", method = RequestMethod.POST)
    public String update(@ModelAttribute("product") Product product) {
        productRepository.update(product);
        return "redirect:/products";
    }

    @RequestMapping("/delete_product/{id}")
    public String delete(@PathVariable(name="id") int id) {
        try {
            productRepository.delete(id);
        }catch (DataIntegrityViolationException e){return "redirect:/integrity_error";}

        return "redirect:/products";
    }
}

