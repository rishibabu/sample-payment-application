package com.unzer.payment.exceptionhandler;

import static com.unzer.payment.constant.MessageCode.ERR_MESSAGE_NOT_ALLOWED;
import static com.unzer.payment.constant.MessageCode.ERR_REQUEST_BODY_VALID;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED;

import org.eclipse.jetty.server.handler.DefaultHandler;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.unzer.payment.constant.MessageCode;
import com.unzer.payment.customresponse.ApiResponse;
import com.unzer.payment.customresponse.ErrorSubResponse;

/**
 * 
 * @author Rishi 
 * custom error handler to display the error response in unique
 *         and elegant way
 *
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RequestBodyExceptionHandler extends DefaultHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public final ResponseEntity<Object> handleRequestBodyException(Exception ex) {

		return buildResponseEntity(ERR_REQUEST_BODY_VALID, ERR_REQUEST_BODY_VALID.getError(),
				BAD_REQUEST);

	}
	
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public final ResponseEntity<Object> handleMethodMismatchException(Exception ex) {

		return buildResponseEntity(ERR_MESSAGE_NOT_ALLOWED, ERR_MESSAGE_NOT_ALLOWED.getError(),
				METHOD_NOT_ALLOWED);

	}

	private ResponseEntity<Object> buildResponseEntity(MessageCode code, String message, HttpStatus status) {

		ApiResponse<?> apiResponse = ApiResponse.builder().status(false)
				.error(ErrorSubResponse.builder().code(code).text(message).build()).build();

		return new ResponseEntity<>(apiResponse, status);

	}

}
