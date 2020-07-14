package com.example.demo.controller;

import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springfox.documentation.annotations.ApiIgnore;

@Api(description = "学生Controller")
@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @ApiOperation(value = "查询学生" ,  notes="查询学生")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "学生Id", required = true, paramType = "query", dataType = "Integer")
    })
    @GetMapping("/findById")
    public ResponseEntity queryUser(@RequestParam("userId") Integer userId){
        Student student = studentService.selectById(userId);
        return new ResponseEntity(student, HttpStatus.OK);
    }

    @ApiOperation(value = "新增学生")
    @PostMapping(value = "/insert")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "字典名字", required = true,
                    paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "sex", value = "性别，1男，2女", required = true,
                    paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "status", value = "状态:1禁用,2启用", required = true,
                    paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "isDelete", value = "是否删除，0未删除，1已删除", required = true,
                    paramType = "query", dataType = "int")})
    public ResponseEntity insertDictionary(@ApiIgnore Student student) {
        studentService.insert(student);
        return ResponseEntity.ok("添加成功");
    }
}

