package com.arthur_pereira.custom_json_serialization.exceptions;

import java.util.Date;

public record ExceptionResponse(Date timestamp, String message, String details) {

}
