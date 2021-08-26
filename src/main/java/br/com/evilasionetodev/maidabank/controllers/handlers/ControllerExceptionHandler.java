package br.com.evilasionetodev.maidabank.controllers.handlers;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.evilasionetodev.maidabank.service.exceptions.BadRequestException;
import br.com.evilasionetodev.maidabank.service.exceptions.ForbiddenException;
import br.com.evilasionetodev.maidabank.service.exceptions.NotFoundException;
import br.com.evilasionetodev.maidabank.service.exceptions.UnauthorizedException;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<StandardError> badRequestException(BadRequestException e, HttpServletRequest request){
		String error = "BAD_REQUEST";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(),request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<StandardError> notFound(NotFoundException e, HttpServletRequest request){
		String error = "NOT_FOUND";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(),request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(ForbiddenException.class)
	public ResponseEntity<StandardError> forbidden(BadRequestException e, HttpServletRequest request){
		String error = "FORBIDDEN";
		HttpStatus status = HttpStatus.FORBIDDEN;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(),request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<StandardError> unauthorized(UnauthorizedException e, HttpServletRequest request){
		String error = "UNAUTHORIZED";
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(),request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> notValid(MethodArgumentNotValidException e, HttpServletRequest request){
		String error = "BAD_REQUEST";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getFieldError().getDefaultMessage(),request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
}
