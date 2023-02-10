package ch.zhaw.fswd.backend.foodbase.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;
import ch.zhaw.fswd.backend.foodbase.entity.Measure;
import ch.zhaw.fswd.backend.foodbase.entity.MeasureRepository;

import java.util.List;

@RestController
@CrossOrigin
public class MeasureEndPoint {
        
    @Autowired
    private MeasureRepository measureRepository;

    @RequestMapping(path = "/api/measures",method=RequestMethod.GET)
    @PreAuthorize("isAuthenticated() AND hasRole('USER')")
    public List<Measure> getAllMeasures() {

        List<Measure> measures = measureRepository.findAll();

        return measures;
    } 
}
