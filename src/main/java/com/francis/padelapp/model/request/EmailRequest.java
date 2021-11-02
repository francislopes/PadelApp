package com.francis.padelapp.model.request;

        import lombok.Data;

        import javax.validation.constraints.NotBlank;

@Data
public class EmailRequest {

    @NotBlank(message = "Email is mandatory!")
    private String email;

    @NotBlank(message = "Subject is mandatory!")
    private String subject;

    @NotBlank(message = "Message is mandatory!")
    private String message;
}
