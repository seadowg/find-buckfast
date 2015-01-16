package com.seadowg.findbuckfast.web;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class MainController {

    @RequestMapping("/")
    @ResponseBody
    public String home() throws IOException {
        try(Jedis jedis = new Jedis("localhost")) {
            Long visitorsCount = jedis.incr("visitors_count");

            Handlebars handlebars = new Handlebars();
            Template homeTemplate = handlebars.compile("home");

            Map map = new HashMap();
            map.put("count", visitorsCount);
            return homeTemplate.apply(map);
        }
    }
}
