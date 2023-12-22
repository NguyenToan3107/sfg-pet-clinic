package com.thomas.sfgpetclinic.controllers;

import com.thomas.sfgpetclinic.model.Owner;
import com.thomas.sfgpetclinic.services.OwnerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/owners")
public class OwnerController {
    private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateOwnerForm";
    private final OwnerService ownerService;
    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

//    @RequestMapping({"", "/", "/index", "/index.html"})
//    public String listOwners(Model model) {
//        model.addAttribute("owners", ownerService.findAll());
//        return "owners/index";
//    }
    @RequestMapping("/find")
    public String findOwners(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return "owners/findOwners";
    }

    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId) {
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        mav.addObject(this.ownerService.findById(ownerId));
        return mav;
    }
    @GetMapping
    public String processFindForm(Owner owner, BindingResult result, Model model) {
        if(owner.getLastName() == null) {
            owner.setLastName("");
        }
        // find owners by lastname
        List<Owner> results = ownerService.findAllByLastNameLike("%" + owner.getLastName() + "%");
        if(results.isEmpty()) {
            // no owners found
            result.rejectValue("lastName", "notFound", "not found");
            return "owners/findOwners";
        }else if(results.size() == 1) {
            owner = results.get(0);
            return "redirect:/owners/" + owner.getId();
        }else {
            // multiple owners found
            model.addAttribute("selections", results);
            return "owners/ownersList";
        }
    }
    @GetMapping("/new")
    public String initCreationForm(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/new")
    public String processCreationForm(@Valid Owner owner, BindingResult result) {
        if(result.hasErrors()) {
            return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
        }else {
            Owner savedOwner = ownerService.save(owner);
            return "redirect:/owners/" + owner.getId();
        }
    }
    @GetMapping("/{ownerId}/edit")
    public String initUpdateOwnerForm(@PathVariable Long ownerId, Model model) {
        Owner owner = ownerService.findById(ownerId);
        model.addAttribute("owner", owner);
        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
    }
    @PostMapping("/{ownerId}/edit")
    public String processUpdateForm(@Valid Owner owner, BindingResult result, @PathVariable Long ownerId) {
        if(result.hasErrors()) {
            return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
        }else {
            owner.setId(ownerId);
            Owner savedOwner = ownerService.save(owner);
            return "redirect:/owners/" + owner.getId();
        }
    }
}
