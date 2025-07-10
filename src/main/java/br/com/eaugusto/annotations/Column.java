package br.com.eaugusto.annotations;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(ElementType.FIELD)
/**
 * Specifies the database column name for a field.
 * 
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 10, 2025
 */
public @interface Column {
	String value();
}
