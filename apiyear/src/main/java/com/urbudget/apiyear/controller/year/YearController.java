package com.urbudget.apiyear.controller.year;

import com.urbudget.apiyear.domain.year.Year;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
@Controller
@RestController
@RequestMapping("api/v1/year")
public class YearController {

        @GetMapping("users/{email}/year")
        public Year getYearByUser(){
            return new Year();
        }
        @PostMapping("users/{email}/years")
        public Year createYear(@RequestBody Year year){
            return year;
        }
        @PatchMapping("users/{email}/budgets/{yearId}")
        public Year updateYear(@PathVariable String email, @PathVariable String yearId){
            return new Year();
        }
        @GetMapping("user/{email}/budgets/{yearId}")
        public Year getYear(@PathVariable String yearId){
            return new Year();
        }
        
}
