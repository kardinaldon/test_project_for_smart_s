package front.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MainController {

    @Value("${shopping.service.product.url}")
    private String shoppingServiceProductUrl;

    private RestTemplate restTemplate = new RestTemplate();

    @RequestMapping("/user")
    public String showUserMsg()
    {
        restTemplate.getForObject(shoppingServiceProductUrl,Object.class);

        return "User has logged in!!!";

    }

    @RequestMapping("/admin")
    public String showAdminMsg()
    {
        return "Admin has logged in!!!";
    }
}
