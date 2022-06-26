package com.unzer.payment.exceptionhandler;

import static com.unzer.payment.constant.MessageCode.ERR_MESSAGE_NOT_ALLOWED;
import static org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED;

import javax.servlet.ServletException;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.xml.sax.helpers.DefaultHandler;

import com.unzer.payment.constant.MessageCode;
import com.unzer.payment.customresponse.ApiResponse;
import com.unzer.payment.customresponse.ErrorSubResponse;


@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)

/**
 * 
 * @author Rishi 
 * customizing the default handler to display the error response
 *         in unique and elegant way
 *
 */
public class ServletExceptionHandler extends DefaultHandler {

	/**
	 * 
	 */

	@ExceptionHandler(ServletException.class)
	public final ResponseEntity<Object> handleServletException(Exception ex) {

		return buildResponseEntity(ERR_MESSAGE_NOT_ALLOWED, ERR_MESSAGE_NOT_ALLOWED.getError(),
				METHOD_NOT_ALLOWED);

	}

	private ResponseEntity<Object> buildResponseEntity(MessageCode code, String message, HttpStatus status) {

		ApiResponse<?> apiResponse = ApiResponse.builder().status(false)
				.error(ErrorSubResponse.builder().code(code).text(message).build()).build();

		return new ResponseEntity<>(apiResponse, status);

	}

}
