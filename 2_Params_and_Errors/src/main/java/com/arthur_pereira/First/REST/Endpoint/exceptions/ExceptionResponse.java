package com.arthur_pereira.First.REST.Endpoint.exceptions;

import java.util.Date;

public record ExceptionResponse(Date timestamp, String message, String details) {

}
