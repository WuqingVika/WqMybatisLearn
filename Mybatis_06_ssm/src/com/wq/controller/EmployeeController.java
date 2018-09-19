package com.wq.controller;

import com.wq.bean.Employee;
import com.wq.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by qwu on 2018/9/15.
 */
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/getAllEmps")
    public String getEmps(Map<String,Object> map){
        List<Employee> emps = employeeService.getEmps();
        map.put("emps",emps);
        return "list";
    }

    @RequestMapping("/imgUrl")
    public void testImg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        FileInputStream input=new FileInputStream("C:\\Users\\Administrator\\Desktop\\wq\\aaa.jpg");
        int i= 0;
        try {
            i = input.available();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] buff=new byte[i];
        input.read(buff);
        input.close();
        response.setContentType("image/jpeg");
        //response.setContentLength(buff.length);
        OutputStream out=response.getOutputStream();
        out.write(buff);
        out.close();
    }
}
