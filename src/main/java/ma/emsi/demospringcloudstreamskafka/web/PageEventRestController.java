package ma.emsi.demospringcloudstreamskafka.web;

import ma.emsi.demospringcloudstreamskafka.entities.PageEvent;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Random;

@RestController
public class PageEventRestController {
    public PageEventRestController(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }
    private final StreamBridge streamBridge;
    @GetMapping("/publish/{topic}/{name}")
    public PageEvent publish(@PathVariable String name, @PathVariable String topic){

        PageEvent pageEvent = new PageEvent(name, Math.random()>0.5?"U1":"U2", new Date(), new Random().nextInt(9000)) ;
        streamBridge.send(topic, pageEvent);
        return pageEvent;
    }
}
