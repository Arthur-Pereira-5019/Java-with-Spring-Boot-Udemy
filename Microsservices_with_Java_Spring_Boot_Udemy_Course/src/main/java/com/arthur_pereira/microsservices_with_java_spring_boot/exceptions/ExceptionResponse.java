package com.arthur_pereira.microsservices_with_java_spring_boot.exceptions;

import java.util.Date;

public record ExceptionResponse(Date timestamp, String message, String details) {

}
