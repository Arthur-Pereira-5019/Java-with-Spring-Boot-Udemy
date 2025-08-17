package com.arthur_pereira.DTO_Pattern.exceptions;

import java.util.Date;

public record ExceptionResponse(Date timestamp, String message, String details) {

}
