package ch.zhaw.fswd.backend.foodbase.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.zhaw.fswd.backend.foodbase.entity.Measure;
import ch.zhaw.fswd.backend.foodbase.entity.MeasureRepository;

import java.util.List;

@RestController
@CrossOrigin
public class MeasureEndPoint {
        
    @Autowired
    private MeasureRepository measureRepository;

    @GetMapping(path = "/api/measures")
    public List<Measure> getAllMeasures() {

        List<Measure> measures = measureRepository.findAll();

        return measures;
    } 
}
