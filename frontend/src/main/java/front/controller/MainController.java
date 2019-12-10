package front.controller;

import constant.PathConstants;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(PathConstants.FRONTEND_REST)
public class MainController {

    private RestTemplate restTemplate = new RestTemplate();

    @RequestMapping("/user")
    public String showUserMsg()
    {
//        restTemplate.getForObject(shoppingServiceProductUrl,Object.class);

        return "User has logged in!!!";

    }

    @RequestMapping("/admin")
    public String showAdminMsg()
    {
        return "Admin has logged in!!!";
    }
}
