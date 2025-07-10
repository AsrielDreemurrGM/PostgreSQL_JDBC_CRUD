package br.com.eaugusto.annotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target(TYPE)
/**
 * Specifies the database table name for an entity class.
 * 
 * @author Eduardo Augusto (github.com/AsrielDreemurrGM/)
 * @since July 10, 2025
 */
public @interface Table {
	String value();
}
