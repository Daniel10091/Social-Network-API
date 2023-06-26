package com.yolo.Yolo.domain.config.exceptionHandler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.yolo.Yolo.domain.entity.dto.ErrorDTO;
import com.yolo.Yolo.domain.exception.PersonAlreadyExistException;
import com.yolo.Yolo.domain.exception.PersonNotFoundException;

@ControllerAdvice
public class ControllerExceptionHandler {
  
  @ExceptionHandler({ PersonNotFoundException.class })
  public ResponseEntity<ErrorDTO> handleUserNotFound(RuntimeException ex, WebRequest request) {
    var errorDTO = new ErrorDTO(404, "Pessoa não encontrada", ex.getMessage(), request.getDescription(false));
    return ResponseEntity.status(errorDTO.getStatus()).body(errorDTO);
  }

  @ExceptionHandler(PersonAlreadyExistException.class)
  public ResponseEntity<ErrorDTO> handleUserAlreadyExist(RuntimeException ex, WebRequest request) {
    var errorDTO = new ErrorDTO(409, "Usuário já existe", ex.getMessage(), request.getDescription(false));
    return ResponseEntity.status(errorDTO.getStatus()).body(errorDTO);
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ErrorDTO> handleOtherExceptions(RuntimeException ex, WebRequest request) {
    var errorDTO = new ErrorDTO(500, "Erro interno do servidor", ex.getMessage(), request.getDescription(false));
    return ResponseEntity.status(errorDTO.getStatus()).body(errorDTO);
  }

}
