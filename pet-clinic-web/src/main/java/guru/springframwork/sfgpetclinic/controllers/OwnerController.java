package guru.springframwork.sfgpetclinic.controllers;

import guru.springframwork.sfgpetclinic.model.Owner;
import guru.springframwork.sfgpetclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/owners")
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder){
        dataBinder.setDisallowedFields("id");
    }

//    @RequestMapping({"", "/index", "/index.html"})
//    public String listOwners(Model model){
//        model.addAttribute("owners", ownerService.findAll());
//        return "owners/index";
//    }

    @RequestMapping("/find")
    public String findOwners(Model model){
        model.addAttribute("owner", new Owner());
        return "owners/findOwners";
    }

    @GetMapping
    public String processFindForm(Owner owner, BindingResult result, Model model){
        if(owner.getLastName() == null)
            owner.setLastName("");
        List<Owner> results = ownerService.findAllByLastNameLike("%"+owner.getLastName()+"%");
        if(results.isEmpty()){
            result.rejectValue("lastName","notFound", "notFound");
            return "owners/findOwners";
        } else if(results.size() == 1){
            owner = results.get(0);
            return "redirect:/owners/"+owner.getId();
        } else {
            model.addAttribute("selections", results);
            return "owners/ownerList";
        }

    }

    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable Long ownerId){
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        mav.addObject(ownerService.findById(ownerId));
        return mav;
    }

    @GetMapping("/new")
    public String initCreationForm(Model model){
        model.addAttribute("owner", new Owner());
        return "owners/createOrUpdateOwnerForm";
    }

    @PostMapping("/new")
    public String processCreationForm(@Valid Owner owner, BindingResult result){
        if(result.hasErrors())
            return "owners/createOrUpdateOwnerForm";
        else {
            Owner savedOwner = ownerService.save(owner);
            return "redirect:/owners/"+savedOwner.getId();
        }
    }

     @GetMapping("/{ownerId}/edit")
    public String initUpdateOwnerForm(@PathVariable Long ownerId,
              Model model){
        model.addAttribute("owner", ownerService.findById(ownerId));
        return "owners/createOrUpdateOwnerForm";
     }

     @PostMapping("/{ownerId}/edit")
    public String processUpdateOwnerForm(@Valid Owner owner, BindingResult result,
             @PathVariable Long ownerId){
        if(result.hasErrors())
            return "owners/createOrUpdateOwnerForm";
        else {
            owner.setId(ownerId);
            Owner savedOwner = ownerService.save(owner);
            return "redirect:/owners/"+savedOwner.getId();
        }
     }


}
