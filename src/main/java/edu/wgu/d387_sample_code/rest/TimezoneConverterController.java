package edu.wgu.d387_sample_code.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/time")
@CrossOrigin
public class TimezoneConverterController {


    private static final ZoneId ET_ZONE = ZoneId.of("America/New_York");
    private static final ZoneId MT_ZONE = ZoneId.of("America/Denver");
    private static final ZoneId UTC_ZONE = ZoneId.of("UTC");

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("hh:mm a z");

    @GetMapping("/presentation")
    public ResponseEntity<List<String>> getLivePresentationTimes() {
        List<String> presentationTimes = new ArrayList<String>();


        LocalDateTime localDateTime = LocalDateTime.now();
        ZoneId localZone = ZoneId.systemDefault();
        ZonedDateTime localZonedTime = localDateTime.atZone(localZone);


        ZonedDateTime etZonedTime = localZonedTime.withZoneSameInstant(ET_ZONE);
        ZonedDateTime mtZonedTime = localZonedTime.withZoneSameInstant(MT_ZONE);
        ZonedDateTime utcZonedTime = localZonedTime.withZoneSameInstant(UTC_ZONE);


        //presentationTimes.add("Local Time: " + localZonedTime.format(TIME_FORMATTER));
        presentationTimes.add("Eastern Time: " + etZonedTime.format(TIME_FORMATTER));
        presentationTimes.add("Mountain Time: " + mtZonedTime.format(TIME_FORMATTER));
        presentationTimes.add("UTC: " + utcZonedTime.format(TIME_FORMATTER));

        return ResponseEntity.ok(presentationTimes);
    }
}
