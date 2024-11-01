package labs.spring.spacecatsecommerce.web;

import labs.spring.spacecatsecommerce.service.exception.CustomerNotFoundException;
import labs.spring.spacecatsecommerce.web.exception.CatNotFoundException;
import labs.spring.spacecatsecommerce.web.exception.ParamsViolationDetails;
import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static labs.spring.spacecatsecommerce.util.PaymentDetailsUtils.getValidationErrorsProblemDetail;
import static java.net.URI.create;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.ProblemDetail.forStatusAndDetail;

@ControllerAdvice
@Slf4j
public class ExceptionTranslator extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    ProblemDetail handleStoreConfigurationNotFoundException(CustomerNotFoundException ex) {
        log.info("Customer Not Found exception raised");
        ProblemDetail problemDetail = forStatusAndDetail(NOT_FOUND, ex.getMessage());
        problemDetail.setType(create("customer-not-found"));
        problemDetail.setTitle("Customer Not Found");
        return problemDetail;
    }

    @ExceptionHandler(CatNotFoundException.class)
    ProblemDetail handleStoreConfigurationNotFoundException(CatNotFoundException ex) {
        log.info("Customer Not Found exception raised");
        ProblemDetail problemDetail = forStatusAndDetail(NOT_FOUND, ex.getMessage());
        problemDetail.setType(create("cat-not-found"));
        problemDetail.setTitle("Cat Not Found");
        return problemDetail;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status,
        WebRequest request) {
        List<FieldError> errors = ex.getBindingResult().getFieldErrors();
        List<ParamsViolationDetails> validationResponse =
            errors.stream().map(err -> ParamsViolationDetails.builder().reason(err.getDefaultMessage()).fieldName(err.getField()).build()).toList();
        log.info("Input params validation failed");
        return ResponseEntity.status(BAD_REQUEST).body(getValidationErrorsProblemDetail(validationResponse));
    }

}
