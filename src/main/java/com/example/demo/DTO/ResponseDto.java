package com.example.demo.DTO;

import java.io.Serializable;

public class ResponseDto implements Serializable {
    private String status;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ResponseDTO{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                '}';
    }


    public ResponseDto status(String status) {
        this.status = status;
        return this;
    }

    public ResponseDto message(String message) {
        this.message = message;
        return this;
    }
}
