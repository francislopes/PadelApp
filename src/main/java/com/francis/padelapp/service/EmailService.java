package com.francis.padelapp.service;

        import lombok.extern.slf4j.Slf4j;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.beans.factory.annotation.Value;
        import org.springframework.mail.javamail.JavaMailSender;
        import org.springframework.mail.javamail.MimeMessageHelper;
        import org.springframework.stereotype.Service;
        import org.thymeleaf.context.Context;
        import org.thymeleaf.spring5.SpringTemplateEngine;

        import java.nio.charset.StandardCharsets;
        import java.util.HashMap;

@Slf4j
@Service
public class EmailService {

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    private SpringTemplateEngine templateEngine;

    public void send(String template, String to, String subject, HashMap<String, Object> values) {

        try {
            var message = emailSender.createMimeMessage();
            var helper = new MimeMessageHelper(message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());

            var context = new Context();
            context.setVariables(values);

            String html = templateEngine.process(template, context);

            helper.setTo(to);
            helper.setFrom(from);
            helper.setSubject(subject);
            helper.setText(html, true);
            emailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
