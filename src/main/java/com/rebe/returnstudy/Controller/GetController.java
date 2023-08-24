package com.rebe.returnstudy.Controller;


import com.rebe.returnstudy.DTO.MemberRequestDto;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/return")
public class GetController {

    //http://localhost:8080/return/hello
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String helloworld() {
        return "hello";
    }


    // http://localhost:8080/return/study-name
    @GetMapping(value = "/study-name")
    public String getStudyName() {
        return "SpringBoot";
    }

    //http://localhost:8080/return/member/2019102236/name/최현영
    @GetMapping(value = "/member/{studentId}/name/{name}")
    public HashMap<String, Integer> getStudentIdAndName(@PathVariable(value = "studentId") Integer studentId, @PathVariable(value = "name") String name){
        System.out.println(studentId);
        System.out.println(name);
        HashMap<String, Integer> response = new HashMap<>();
        response.put(name, studentId);
        return response;
    }

    //http://localhost:8080/return/member?id=2019102236&name=최현영
    @GetMapping(value = "/member")
    public HashMap<String, Integer> getStudentInfo(@RequestParam(value = "id") Integer studentId, @RequestParam(value = "name") String name){
        System.out.println(studentId);
        System.out.println(name);
        HashMap<String, Integer> response = new HashMap<>();
        response.put(name, studentId);
        return response;
    }


    //http://localhost:8080/return/member-info?id=2019102236&name=최현영?year=32?club=return
    @GetMapping(value = "/member-info")
    public MemberRequestDto getStudentFromDTO(MemberRequestDto memberRequestDto){
        return memberRequestDto;
    }



}