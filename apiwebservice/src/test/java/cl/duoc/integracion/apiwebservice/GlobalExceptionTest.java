package cl.duoc.integracion.apiwebservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import cl.duoc.integracion.apiwebservice.Global.GlobalExceptionHandler;

@ExtendWith(MockitoExtension.class)
public class GlobalExceptionTest {

    private GlobalExceptionHandler handler;

    @BeforeEach
    void setUp(){
        handler = new GlobalExceptionHandler();
    }

    @Test
    void manejarErrorValidacion_deberiaRetornarMapaDeErrores(){

        BindingResult bindingResult = mock(BindingResult.class);
        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);

        List<FieldError> errores = List.of(
            new FieldError("empleadoDTO", "nombreEmpleado", "El nombre del empleado es obligatorio"),
            new FieldError("empleadoDTO", "correoEmpleado", "El correo del empleado es obligatorio")
        );

        when(bindingResult.getFieldErrors()).thenReturn(errores);
        when(ex.getBindingResult()).thenReturn(bindingResult);

        ResponseEntity<Map<String,String>> respuesta = handler.handleValidationErrors(ex);

        assertEquals(HttpStatus.BAD_REQUEST, respuesta.getStatusCode());
        assertNotNull(respuesta.getBody());
        assertEquals(2, respuesta.getBody().size());
        assertEquals("El nombre del empleado es obligatorio", respuesta.getBody().get("nombreEmpleado"));
        assertEquals("El correo del empleado es obligatorio", respuesta.getBody().get("correoEmpleado"));
    }

    @Test
    void manejarIllegalArgumentException_deberiaRetornarConflict(){
        IllegalArgumentException ex = new IllegalArgumentException("Empleado ya existe");

        ResponseEntity<String> respuesta = handler.handleIllegalArgument(ex);

        assertEquals(HttpStatus.CONFLICT, respuesta.getStatusCode());
        assertEquals("Empleado ya existe", respuesta.getBody());
    }

    @Test
    void manejarRuntimeException_deberiaRetornarErrorInterno(){
        RuntimeException ex = new RuntimeException("Falla grave");

        ResponseEntity<String> respuesta = handler.handleRuntimeException(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, respuesta.getStatusCode());
        assertEquals("Error interno del servidor: Falla grave", respuesta.getBody());
    }
    
}
    


