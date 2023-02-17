package com.example.td2.controllers

import com.example.td2.entities.Organization
import com.example.td2.entities.User
import com.example.td2.repositories.OrganizationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.stereotype.Repository
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.view.RedirectView
import java.lang.ProcessBuilder.Redirect
import javax.management.modelmbean.ModelMBean

@Controller
@RequestMapping("/orgas/")
class OrgaController {

    @Autowired
    lateinit var orgaRepository: OrganizationRepository


    @RequestMapping(path =["","index"])
    fun indexAction(model:ModelMap):String{
        model["orgas"]=orgaRepository.findAll()
        return "/orgas/index"
    }


    @GetMapping("/new")
    fun newAction(model:ModelMap):String{
        model["orga"]=Organization()
        return "/orgas/form";
    }

    @PostMapping("/new")
    fun submitnewAction(
            @ModelAttribute orga:Organization,
            @ModelAttribute users:String
    ):RedirectView{
        val usersArray=users.split("\n").forEach{
            val user = User()
            val values=it.split(" ")
            user.firstname=values.getOrNull(0)
            user.lastname=values.getOrNull(1)
            user.email="${user.lastname}.${user.lastname}.@${orga.domain}"
            orga.addUser(user)
        }

        orgaRepository.save(orga)
        return RedirectView("/orgas/")
    }

}