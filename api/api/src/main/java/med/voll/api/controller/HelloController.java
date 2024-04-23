package med.voll.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Mapeador de requests para o diretório /Hello
@RestController
@RequestMapping("/hello")
public class HelloController {

    //Ao receber uma solicitação do tipo GET, ele retorna isso
    @GetMapping
    public String olaMundo(){
        return "Hello world Spring";
    }

}
