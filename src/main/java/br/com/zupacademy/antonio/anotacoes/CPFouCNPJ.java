package br.com.zupacademy.antonio.anotacoes;

import org.hibernate.validator.constraints.CompositionType;
import org.hibernate.validator.constraints.ConstraintComposition;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;

import java.lang.annotation.*;

@CPF
@ConstraintComposition(CompositionType.OR)
@CNPJ

@ReportAsSingleViolation 
@Constraint(validatedBy = { }) 
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)

public @interface CPFouCNPJ {

    String message() default "CPF ou CNPJ não é válido!";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
	
}
