package ai.dnai.io.pseudorandomfilegenerator.api.controllers

import ai.dnai.io.pseudorandomfilegenerator.api.commands.FilesPropertiesCommand
import ai.dnai.io.pseudorandomfilegenerator.api.commands.ShifterParameterCommand
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@Controller
class IndexController {
    @GetMapping("/")
    fun genIndex(model: Model): String{
        model.addAttribute("filesPropertiesCommand", FilesPropertiesCommand())
        return "index.html";
    }

    @GetMapping("/dateShiftPage")
    fun getFileShifterPage(model: Model): String{
        model.addAttribute("shifterParameterCommand", ShifterParameterCommand())
        return "shifter.html";
    }
}