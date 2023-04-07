package evon.api.userauth.services;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorService {
    private static Logger logger = LoggerFactory.getLogger(ErrorService.class);
//    @GetMapping("/error")
//    public String error(HttpServletRequest request) {
//        logger.info(request.);
//        return "";
//    }
}
