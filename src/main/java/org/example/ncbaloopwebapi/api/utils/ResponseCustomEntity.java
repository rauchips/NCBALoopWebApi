package org.example.ncbaloopwebapi.api.utils;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
@Component
public class ResponseCustomEntity {
    private int status;
    private String message;
    private Object data;
    private boolean error;
    private Timestamp timestamp;

    public void setStatus(int status){
        this.status = status;
    }
    public int getStatus(){
        return status;
    }
    public void setMessage(String message){
        this.message = message;
    }
    public String getMessage(){
        return message;
    }
    public void setData(Object data){
        this.data = data;
    }
    public Object getData(){
        return data;
    }
    public void setError(boolean error){
        this.error = error;
    }
    public boolean getError(){
        return error;
    }
    public void setTimestamp(Timestamp timestamp){
        this.timestamp = timestamp;
    }
    public Timestamp getTimestamp(){
        return timestamp;
    }
}
