package com.francis.padelapp.controller;

        import com.francis.padelapp.model.request.EmailRequest;
        import com.francis.padelapp.service.EmailService;
        import io.swagger.annotations.Api;
        import io.swagger.annotations.ApiOperation;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.http.MediaType;
        import org.springframework.web.bind.annotation.PostMapping;
        import org.springframework.web.bind.annotation.RequestBody;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RestController;

        import java.util.HashMap;

@Api(value = "Email Controller", tags = "Email Controller")
@RestController
@RequestMapping(value = "/email", produces = MediaType.APPLICATION_JSON_VALUE)
public class EmailController {

    @Autowired
    private EmailService service;

    @ApiOperation("Send a sample email")
    @PostMapping
    public void send(@RequestBody EmailRequest request) {
        HashMap<String, Object> values = new HashMap<>();
        values.put("msg", request.getMessage());
        service.send("sample", request.getEmail(), request.getSubject(), values);
    }
}
