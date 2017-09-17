package io.pivotal.fe.demo.basic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@Controller
@RequestMapping(value = "/", produces = "application/json")
public class BasicApplication {

    public static void main(String[] args) {
        SpringApplication.run(BasicApplication.class, args);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Map<String,String> info() {
        Map<String,String> map = new HashMap<>();
        map.put("time", ""+System.currentTimeMillis());
        return map;
    }

  @RequestMapping(value = "/github", method = RequestMethod.GET)
  @ResponseBody
  public String github() {
    RestTemplate template = new RestTemplate();
    return template.getForEntity("https://status.github.com/api.json", String.class).getBody();
  }

  @RequestMapping(value = "/github/{endpoint}", method = RequestMethod.GET)
  @ResponseBody
  public String githubReq(@PathVariable String endpoint) {
    RestTemplate template = new RestTemplate();
    return template.getForEntity("https://status.github.com/api/"+endpoint+".json", String.class).getBody();
  }
}
