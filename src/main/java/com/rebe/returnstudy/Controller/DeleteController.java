package com.rebe.returnstudy.Controller;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/return")
public class DeleteController {

    //http://localhost:8080/return/member/withdraw/32nd?studentId=2019102236
    @DeleteMapping("/member/withdraw/{year}")
    public String dropOutMember(@PathVariable(value = "year") String year, @RequestParam(value = "studentId") Long studentId){
        return "삭제되었습니다.";

    }


}
