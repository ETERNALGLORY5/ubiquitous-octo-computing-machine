package evon.api.userauth.interfaces;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hello")
public class HelloService {
    @GetMapping("/world")
    @ResponseBody
    public String helloWorld() {
        return "Hello World";
    }
}
