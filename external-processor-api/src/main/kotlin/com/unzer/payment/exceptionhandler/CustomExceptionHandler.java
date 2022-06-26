package com.unzer.payment.exceptionhandler;

import static com.unzer.payment.constant.MessageCode.ERR_GENERAL_ERROR;
import static com.unzer.payment.constant.MessageCode.ERR_RESOURCE_NOT_FOUND;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.unzer.payment.constant.MessageCode;
import com.unzer.payment.customeexception.PaymentNotFoundException;
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
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex) {
		
		return buildResponseEntity(ERR_GENERAL_ERROR, ERR_GENERAL_ERROR.getError()  , UNPROCESSABLE_ENTITY);

	}
	
	@ExceptionHandler(PaymentNotFoundException.class)
	public final ResponseEntity<Object> handlePaymentNotFoundException(Exception ex) {

		return buildResponseEntity(ERR_RESOURCE_NOT_FOUND, ERR_RESOURCE_NOT_FOUND.getError(), NOT_FOUND);

	}

	private ResponseEntity<Object> buildResponseEntity(MessageCode code, String message, HttpStatus status) {

		ApiResponse<?> apiResponse = ApiResponse.builder().status(false)
				.error(ErrorSubResponse.builder().code(code).text(message).build()).build();

		return new ResponseEntity<>(apiResponse, status);

	}

}
