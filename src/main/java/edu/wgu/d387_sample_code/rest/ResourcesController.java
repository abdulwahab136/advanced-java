package edu.wgu.d387_sample_code.rest;

import edu.wgu.d387_sample_code.locale.FetchResources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/resources")
@CrossOrigin
public class ResourcesController {

    // Create 2 executors
    private Executor exec = Executors.newFixedThreadPool(2);

    @GetMapping("welcome")
    public ResponseEntity<List<String>>getWelcomeMessage() {
        List<String> lst = new ArrayList<String>();

        // Read en_US
        exec.execute(() -> {
            FetchResources rEN = new FetchResources("en", "US");
            lst.add(rEN.getWelcomeMessage());
            System.out.println("en_US Message Received");
        });

        exec.execute(() -> {
            FetchResources rFR = new FetchResources("fr", "CA");
            lst.add(rFR.getWelcomeMessage());
            System.out.println("fr_CA Message Received!");
        });

        return ResponseEntity.ok(lst);

    }
}
