package edu.school21.chat.exceptions;

public class NotSavedEntityException extends RuntimeException{
    public NotSavedEntityException(){
        System.out.println("Data hasn't been stored");
    }
}
