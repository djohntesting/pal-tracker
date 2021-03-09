package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import io.pivotal.pal.tracker.TimeEntryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate) {
        return created(null)
                .body(timeEntryRepository.create(timeEntryToCreate));
    }

    @GetMapping("{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable long id){
        var timeEntryFound = timeEntryRepository.find(id);
        if(timeEntryFound==null){
            return notFound().build();
        }else{
            return ok(timeEntryFound);
        }
    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> timeEntryLists = timeEntryRepository.list();
        return ok(timeEntryLists);
    }

    @PutMapping("{id}")
    public ResponseEntity<TimeEntry> update(@PathVariable long id, @RequestBody TimeEntry timeEntry) {
        var timeEntryUpdated = timeEntryRepository.update(id, timeEntry);

        if (timeEntryUpdated ==null ){
            return notFound().build();
        }
        else {
            return ok(timeEntryUpdated);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        timeEntryRepository.delete(id);
        return noContent().build();
    }
}
