package me.travisgray.Controller;

import me.travisgray.Models.Apartment;
import me.travisgray.Repositories.ApartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by ${TravisGray} on 2/13/2018.
 */
@Controller
public class ApartmentController {

    @Autowired
    ApartmentRepository apartmentRepository;



    @RequestMapping("/")
    public String showApartmentpage(Model model){
        model.addAttribute("apartmentlist",apartmentRepository.findAll());
        return "index2";
    }

    @GetMapping("/ListApartment")
    public String listApartments(Model model){
        model.addAttribute("apartmentlist",apartmentRepository.findAll());
//        Storing Book entries correctly
        return "Apartmentlist";
    }

    @GetMapping("/AddApartment")
    public String apartmentForm(Model model){
        model.addAttribute("apartment",new Apartment());
        return "addapartmentform";
    }



    //    Must pass created book entry here then save to repository model for thymeleaf loop
    @PostMapping("/AddApartment")
    public String processapartmentForm(@Valid @ModelAttribute("apartment") Apartment apartment, BindingResult result, Model model){

        if (result.hasErrors()){
            return "addapartmentform";
        }

////        Check to see if image value is empty if it is then set default image string for thymeleaf add form
//        if(apartment.getImage().isEmpty()){
//            readingBook.setImage(defaultimage);
//        }
//
//        bookRepository.save(readingBook);
//        System.out.println("Test to see checkout status text field being stored correctly"+readingBook.getCheckoutstatus().equalsIgnoreCase("Borrow"));
//        Need to make sure to add all books to model for thymeleaf access after this route is complete
        apartmentRepository.save(apartment);
        model.addAttribute("apartment",apartmentRepository.findAll());
        return "Apartmentlist";
    }

    @GetMapping("/detail/{id}")
    public String showBook(@PathVariable("id") long id, Model model){
//        model.addAttribute("readingbook",bookRepository.findOne(id));

//        Test to see if route fine all books including new user generated book
//        Working correctly now
        model.addAttribute("apartmentlist",apartmentRepository.findAll());

        return "Apartmentlist";
    }

    @GetMapping("/update/{id}")
    public String updateBooks(@PathVariable("id") long id, Model model){
        model.addAttribute("apartment",apartmentRepository.findOne(id));
        return "addapartmentform";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") long id, Model model){
        model.addAttribute("apartment",apartmentRepository.findOne(id));
        apartmentRepository.delete(id);
        return "Apartmentlist";
    }




}